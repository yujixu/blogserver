<template>
  <el-container v-loading="loading">
    <el-main class="main">
      <update-pur-req-detail v-if="showMaterial" ref="updatePurReqDetail" @reselected="reselect"></update-pur-req-detail>
      <el-form v-loading="loading">
        <h3 class="title" v-if="from!='all' && from!='query'">新增采购申请单</h3>
        <h3 class="title" v-if="from=='all'">修改采购申请单</h3>
        <h3 class="title" v-if="from=='query'">采购申请单</h3>
        <h4 class="title">基本信息</h4>
        <el-form-item>
          申请单编号：<el-input v-bind:readonly="readonly" type="text" v-model="purReqForm.number" auto-complete="off" placeholder="申请单编号" style="width:200px"></el-input>
          申请单名称：<el-input v-bind:readonly="readonly" type="text" v-model="purReqForm.name" auto-complete="off" placeholder="申请单名称" style="width:200px"></el-input>
          状态：<el-select v-model="purReqForm.state" disabled>
                    <el-option
                      v-for="item in states"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                    ></el-option>
                </el-select>
        </el-form-item>
        <el-form-item>
        </el-form-item>
      </el-form>
      <h4 class="title">采购申请单明细</h4>
      <el-button type="primary" @click="addPurReqDetail" v-if="from==undefined || from=='all'" style="float: left;">修改</el-button>
      <el-table
        ref="multipleTable"
        :data="purReqDetail"
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
          <template slot-scope="scope">
            <el-input v-if="show" suffix-icon="el-icon-search" readonly size="small" v-model="scope.row.materialNumber" @click.native="addPurReqDetail(scope.$index,scope.row)"></el-input>
            <span v-else>{{scope.row.materialNumber}}</span>
          </template>
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
          prop="purQty"
          label="申请数量"
          width="100" align="center">
          <template slot-scope="scope">
            <el-input v-if="show" size="small" v-model="scope.row.purQty" @change="countAmount(scope.$index, 1)"></el-input>
            <span v-else>{{scope.row.purQty}}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="purOrdered"
          label="已订购数量"
          width="120" align="center">
        </el-table-column>
        <el-table-column
          prop="price"
          label="单价"
          width="100" align="center">
          <template slot-scope="scope">
              <el-input v-if="show" size="small" v-model="scope.row.price" @change="countAmount(scope.$index, 2)"></el-input>
              <span v-else>{{scope.row.price}}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="amount"
          label="金额"
          width="100" align="center">
          <template slot-scope="scope">
            <el-input v-if="show" size="small" v-model="scope.row.amount" readonly></el-input>
            <span v-else>{{scope.row.amount}}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="states"
          label="状态"
          width="100" align="center">
          <template slot-scope="scope">
            <el-select v-model="scope.row.states" disabled>
              <el-option
                v-for="item in states"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              ></el-option>
            </el-select>
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
          <el-button type="primary" @click="savePurReq">保存</el-button>
          <el-button type="primary" @click="excutePurReq">提交</el-button>
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
    import updatePurReqDetail from './UpdatePurReqDetail'
    import Vue from 'vue'
    import {postRequestJson, putRequest, putRequestJson} from "../../utils/api";

    export default {
        mounted: function () {
            var from = this.$route.query.from;
            this.from = from;
            var _this = this;
            if(from == "query"){
                this.readonly = true;
            }
            if(from==undefined || from=='all'){
                _this.show = true;
            }
            if (from != null && from != '' && from != undefined) {
                var id = this.$route.query.id;
                this.id = id;
                _this.loadPurReqDetail(this.id);
                this.loading = true;
                getRequest("/purReq/" + id).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.purReqForm = resp.data;
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
            updatePurReqDetail,
        },
        methods: {
            savePurReq(){
                var _this = this;
                _this.loading = true;
                _this.beforeSave();
                if(this.$route.query.id!=null){
                    putRequestJson("/purReq/update", {
                        purReq: _this.purReqForm,
                        purReqDetail: _this.purReqDetail
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '申请单保存成功!'});
                            window.bus.$emit('blogTableReload')
                            _this.$router.replace({path: '/purReqList'});
                        }
                    }, resp=> {
                        _this.loading = false;
                        _this.$message({type: 'error', message: '申请单保存失败!'});
                    });
                }
                else{
                    postRequestJson("/purReq/", {
                        purReq: _this.purReqForm,
                        purReqDetail: _this.purReqDetail
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '申请单保存成功!'});
                            window.bus.$emit('blogTableReload')
                            _this.$router.replace({path: '/purReqList'});
                        }
                    }, resp=> {
                        _this.loading = false;
                        _this.$message({type: 'error', message: '申请单保存失败!'});
                    });
                }
            },
            beforeSave(){
                for(var i; i<this.purReqDetail.length; i++){
                    if(this.purReqDetail.number=="" || this.purReqDetail.number==null){
                        _this.$message({type: 'info', message: '物料不能为空!'});
                        return;
                    }
                    if(this.purReqDetail.purQty=="" || this.purReqDetail.purQty==null){
                        _this.$message({type: 'info', message: '申请数量不能为空!'});
                        return;
                    }
                    if(this.purReqDetail.price=="" || this.purReqDetail.price==null){
                        _this.$message({type: 'info', message: '单价不能为空!'});
                        return;
                    }
                }
            },
            excutePurReq(){
                var _this = this;
                _this.loading = true;
                if(this.id==null){
                    postRequestJson("/purReq/addAndSubmit", {
                        purReq: _this.purReqForm,
                        purReqDetail: _this.purReqDetail
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200) {
                            _this.$message({type: 'success', message: '提交成功!'});
                            window.bus.$emit('blogTableReload')
                            _this.$router.replace({path: '/purReqList'});
                        }
                    }, resp=> {
                        _this.loading = false;
                        _this.$message({type: 'error', message: '提交失败!'});
                    })
                }
                else{
                    putRequest("/purReq/submit", {
                        id: this.id,
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200) {
                            _this.$message({type: 'success', message: '提交成功!'});
                            window.bus.$emit('blogTableReload')
                            _this.$router.replace({path: '/purReqList'});
                        }
                    }, resp=> {
                        _this.loading = false;
                        _this.$message({type: 'error', message: '提交失败!'});
                    })
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
                    this.purReqDetail.splice(index, 1);
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            loadPurReqDetail(pId){
                var _this = this;
                getRequest("/purReqDetail/"+pId).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.purReqDetail = resp.data;
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
                })
            },
            deleteMany(){
                var items = this.selItems;
                var details = this.purReqDetail;
                for(var i=0; i<items.length; i++){
                    for(var j=0; j<details.length; j++){
                        if(items[i].materialNumber == details[j].materialNumber
                            || items[i].materialNumber==null || items[i].materialNumber==""){
                            console.log(items[i].materialNumber, details[j].materialNumber)
                            this.purReqDetail.splice(j, 1);
                            continue;
                        }
                    }
                }
            },
            addPurReqDetail(){
                this.showMaterial = true;
                this.$nextTick(() => {
                    this.$refs.updatePurReqDetail.init()
                })
            },
            reselect(list){
                var _this = this;
                if(list.length>=1){
                    _this.purReqDetail.splice(0, this.purReqDetail.length);
                    for(var i=0; i<list.length; i++){
                        var tempDetail = {};
                        Vue.set(tempDetail, "materialId", list[i].id);
                        Vue.set(tempDetail, "materialNumber", list[i].number);
                        Vue.set(tempDetail, "materialName", list[i].name);
                        Vue.set(tempDetail, "materialModel", list[i].materialModel);
                        Vue.set(tempDetail, "materialType", list[i].materialTypeName);
                        Vue.set(tempDetail, "baseUnit", list[i].baseUnitName);
                        Vue.set(tempDetail, "pId", 8);
                        Vue.set(tempDetail, "purQty", null);
                        Vue.set(tempDetail, "amount", null);
                        Vue.set(tempDetail, "price", null);
                        Vue.set(tempDetail, "states", "INPUT");
                        _this.purReqDetail.push(tempDetail);
                    }
                }
                else if(list.length==0){
                    _this.purReqDetail.splice(_this.purReqDetail.length-1, _this.purReqDetail.length);
                }
            },
            countAmount(index, state){
                var purReqDetail = this.purReqDetail[index];
                if(state == 2 && purReqDetail.price<=0){
                    this.purReqDetail[index].price = "";
                    this.$message({type: 'info', message: '单价应大于0!!!'});
                }
                if(state == 1 && purReqDetail.purQty<=0){
                    this.purReqDetail[index].purQty = "";
                    this.$message({type: 'info', message: '申请数量应大于0!!!'});
                }
                if(purReqDetail.purQty!=null && purReqDetail.purQty!=""
                    && purReqDetail.price!=null && purReqDetail.price!=""){
                    this.purReqDetail[index].amount = purReqDetail.purQty * purReqDetail.price;
                }
            }
        },
        data() {
            return {
                purReqForm:{
                    name: "",
                    number: "",
                    state: "INPUT",
                },
                states: [{
                    value: 'INPUT',
                    label: '制单'
                }, {
                    value: 'EXCUTE',
                    label: '下达'
                }, {
                    value: 'CLOSE',
                    label: '关闭'
                }],
                from: '',
                loading: false,
                readonly: false,
                purReqDetail:[{
                    materialNumber: "",
                    materialId: "",
                    states: "INPUT"
                }],
                selItems: [],
                show: false,
                showMaterial: false,
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
