<template>
  <el-container v-loading="loading">
    <el-main class="main">
      <update-warehouse-stock v-if="showStock" ref="updateWarehouseStock" @reselected="reselect"></update-warehouse-stock>
      <el-form v-loading="loading">
        <h3 class="title" v-if="from!='use' && from!='query'">新增领用申请单</h3>
        <h3 class="title" v-if="from=='use'">修改领用申请单</h3>
        <h3 class="title" v-if="from=='query'">领用申请单</h3>
        <h4 class="title">基本信息</h4>
        <el-form-item>
          <span style="margin-left: 0px">领用申请单编号：</span><el-input v-bind:readonly="readonly" type="text" v-model="useApplyForm.number" auto-complete="off" placeholder="领用申请单编号" style="width:200px;margin-left: 20px"></el-input>
          <span style="margin-left: 30px">领用申请单名称：</span><el-input v-bind:readonly="readonly" type="text" v-model="useApplyForm.name" auto-complete="off" placeholder="领用申请单名称" style="width:200px;margin-left: 20px"></el-input>
          <span style="margin-left: 55px">审核状态：</span><el-select v-model="useApplyForm.state" disabled style="width:200px;margin-left: 20px">
          <el-option
            v-for="item in states"
            :key="item.value"
            :label="item.label"
            :value="item.label"
          ></el-option>
        </el-select>
        </el-form-item>
        <el-form-item>
          <span style="margin-left: 55px">申请人：</span><el-select v-bind:disabled="readonly" v-model="useApplyForm.applicantId" style="width:200px;margin-left: 20px">
          <el-option
            v-for="item in employees"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
          <span style="margin-left: 70px">领用原因：</span><el-select v-bind:disabled="readonly" v-model="useApplyForm.reason" style="width:200px;margin-left: 20px">
          <el-option
            v-for="item in reasons"
            :key="item.value"
            :label="item.label"
            :value="item.label"
          ></el-option>
        </el-select>
          <span style="margin-left: 60px">申请时间：</span><el-date-picker style="width:200px;margin-left: 15px" v-model="useApplyForm.applyTime" v-bind:readonly="readonly" type="datetime" format="yyyy-MM-dd HH:mm" placeholder="申请时间"></el-date-picker>
        </el-form-item>

      </el-form>
      <h4 class="title">采购申请单明细</h4>
      <el-button type="primary" @click="addUseApplyDetail" v-if="from==undefined || from=='use'" style="float: left;">修改</el-button>
      <el-table
        ref="multipleTable"
        :data="useApplyDetail"
        tooltip-effect="dark"
        style="width: 100%;overflow-x: hidden; overflow-y: hidden;"
        max-height="390"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          width="35" align="center">
        </el-table-column>
        <el-table-column
          label="序号"
          width="100" align="center">
          <template slot-scope="scope"><span style="color: #409eff;cursor: pointer" >{{scope.$index +1 }}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="materialNumber"
          label="物料编号"
          width="100" align="center">
        </el-table-column>
        <el-table-column
          prop="materialName"
          label="物料名称"
          width="100" align="center">
        </el-table-column>
        <el-table-column
          prop="materialType"
          label="物料类型"
          width="100" align="center">
        </el-table-column>
        <el-table-column
          prop="materialModel"
          label="物料规格"
          width="100" align="center">
        </el-table-column>
        <el-table-column
          prop="baseUnit"
          label="计量单位"
          width="100" align="center">
        </el-table-column>
        <el-table-column
          prop="stock"
          label="库存"
          v-if="!readonly"
          width="100" align="center">
        </el-table-column>
        <el-table-column
          prop="useNum"
          label="申请数量"
          width="100" align="center">
          <template slot-scope="scope">
            <el-input v-if="show" size="small" v-model="scope.row.useNum" @change="check(scope.$index)"></el-input>
            <span v-else>{{scope.row.useNum}}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" v-if=this.show>
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.$index, scope.row)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-button type="danger" size="mini" style="float: left;" v-show=this.show
                 :disabled="this.selItems.length==0" @click="deleteMany">批量删除
      </el-button>
      <div style="display: flex;align-items: center;margin-top: 15px;justify-content: flex-end">
        <template v-if=this.show>
          <el-button type="primary" @click="saveUseApply">保存</el-button>
        </template>
      </div>
    </el-main>
  </el-container>
</template>
<script>
    import {postRequest} from '@/utils/api'
    import {getRequest} from '@/utils/api'
    // Local Registration
    import {mavonEditor} from 'mavon-editor'
    // 可以通过 mavonEditor.markdownIt 获取解析器markdown-it对象
    import 'mavon-editor/dist/css/index.css'
    import {isNotNullORBlank} from '@/utils/utils'
    import updateWarehouseStock from './UpdateWarehouseStock'
    import Vue from 'vue'
    import {postRequestJson, putRequest, putRequestJson} from "../../utils/api";

    export default {
        mounted: function () {
            var from = this.$route.query.from;
            this.from = from;
            var _this = this;
            _this.loadEmpl();
            if(from == "query"){
                this.readonly = true;
            }
            if(from==undefined || from=='use'){
                _this.show = true;
            }
            if (from != null && from != '' && from != undefined) {
                var id = this.$route.query.id;
                this.id = id;
                _this.loadUseApplyDetail(this.id);
                this.loading = true;
                getRequest("/use/" + id).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.useApplyForm = resp.data;
                    } else {
                        _this.$message({type: 'error', message: '页面加载失败!'})
                    }
                }, resp=> {
                    _this.loading = false;
                    _this.$message({type: 'error', message: '页面加载失败!'})
                })
            }
        },
        components: {
            mavonEditor,
            updateWarehouseStock,
        },
        methods: {
            saveUseApply(){
                var _this = this;
                _this.loading = true;
                if(this.$route.query.id!=null){
                    putRequestJson("/use/update", {
                        useApply: _this.useApplyForm,
                        useApplyDetail: _this.useApplyDetail
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '采购入库单保存成功!'});
                            window.bus.$emit('blogTableReload')
                            _this.$router.replace({path: '/warehouseList'});
                        }
                    }, resp=> {
                        _this.loading = false;
                        _this.$message({type: 'error', message: '采购入库单保存失败!'});
                    });
                }
                else{
                    postRequestJson("/use/", {
                        useApply: _this.useApplyForm,
                        useApplyDetail: _this.useApplyDetail
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '采购入库单保存成功!'});
                            window.bus.$emit('blogTableReload')
                            _this.$router.replace({path: '/warehouseList'});
                        }
                    }, resp=> {
                        _this.loading = false;
                        _this.$message({type: 'error', message: '采购入库单保存失败!'});
                    });
                }
            },
            handleSelectionChange(val) {
                this.selItems = val;
            },
            handleDelete(index, row){
                this.$confirm('删除该物料, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.useApplyDetail.splice(index, 1);
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            deleteMany(){
                var items = this.selItems;
                var details = this.useApplyDetail;
                for(var i=0; i<items.length; i++){
                    for(var j=0; j<details.length; j++){
                        if(items[i].materialNumber == details[j].materialNumber
                            || items[i].materialNumber==null || items[i].materialNumber==""){
                            console.log(items[i].materialNumber, details[j].materialNumber)
                            this.useApplyDetail.splice(j, 1);
                            continue;
                        }
                    }
                }
            },
            loadUseApplyDetail(pId){
                var _this = this;
                getRequest("/useDetail/"+pId).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.useApplyDetail = resp.data;
                    } else {
                        _this.$message({type: 'error', message: '数据加载失败!'});
                    }
                }, resp=> {
                    _this.loading = false;
                    if (resp.response.status == 403) {
                        _this.$message({type: 'error', message: resp.response.data});
                    } else {
                        _this.$message({type: 'error', message: '数据加载失败!!'});
                    }
                }).catch(resp=> {
                    //压根没见到服务器
                    _this.loading = false;
                    _this.$message({type: 'error', message: '数据加载失败!!!'});
                });
            },
            addUseApplyDetail(){
                if(this.show){
                    this.showStock = true;
                    this.$nextTick(() => {
                        this.$refs.updateWarehouseStock.init()
                    })
                }
            },
            loadEmpl(){
                var _this = this;
                getRequest("/employee/all").then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.employees = resp.data;
                    } else {
                        _this.$message({type: 'error', message: '页面加载失败!'})
                    }
                }, resp=> {
                    _this.loading = false;
                    _this.$message({type: 'error', message: '页面加载失败!'})
                });
            },
            check(index){
                var useApplyDetail = this.useApplyDetail[index];
                if(useApplyDetail.useNum<=0){
                    this.useApplyDetail[index].useNum = "";
                    this.$message({type: 'info', message: '申请数量应大于0!'});
                }
                if(useApplyDetail.useNum>useApplyDetail.stock){
                    this.useApplyDetail[index].useNum = "";
                    this.$message({type: 'info', message: '申请数量不能大于库存!!'});
                }
            },
            reselect(list){
                var _this = this;
                _this.useApplyDetail = list;
                for(var i=0; i<list.length; i++){
                    Vue.set(_this.useApplyDetail[i], "stockId", list[i].id);
                }
            }
        },
        data() {
            return {
                useApplyForm:{
                    name: "",
                    number: "",
                    state: "待审核",
                    applyTime: new Date()
                },
                states: [{
                    value: 1,
                    label: '已审核'
                }, {
                    value: 2,
                    label: '待审核'
                }, {
                    value: 3,
                    label: '已驳回'
                }],
                reasons: [{
                    value: 1,
                    label: '维修使用'
                }, {
                    value: 2,
                    label: '办公使用'
                }],
                from: '',
                loading: false,
                readonly: false,
                useApplyDetail:[],
                selItems: [],
                show: false,
                showStock: false,
                employees: [],
                suppliers: [],
                warehouse: []
            }
        }
    }
</script>
<style>
  .post-article > .main > #editor {
    width: 100%;
    height: 450px;
    text-align: left;
  }

  .post-article > .header {
    background-color: #ececec;
    margin-top: 10px;
    padding-left: 5px;
    display: flex;
    justify-content: flex-start;
  }

  .post-article > .main {
    /*justify-content: flex-start;*/
    display: flex;
    flex-direction: column;
    padding-left: 5px;
    background-color: #ececec;
    padding-top: 0px;
  }

  .post-article > .header > .el-tag + .el-tag {
    margin-left: 10px;
  }

  .post-article > .header > .button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }

  .post-article > .header > .input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }

  .title {
    margin: 0px auto 40px auto;
    text-align: left;
    color: #505458;
  }

</style>
