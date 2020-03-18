<template>
  <el-container v-loading="loading">
    <el-main class="main">
      <update-use-apply v-if="showUseApply" ref="updateUseApply" @reselected="reselect"></update-use-apply>
      <el-form v-loading="loading">
        <h3 class="title" v-if="from!='out' && from!='query'">新增出库单</h3>
        <h3 class="title" v-if="from=='out'">修改出库单</h3>
        <h3 class="title" v-if="from=='query'">出库单</h3>
        <h4 class="title">基本信息</h4>
        <el-form-item>
          <span style="margin-left: 0px">出库单编号：</span><el-input v-bind:readonly="readonly" type="text" v-model="outForm.number" auto-complete="off" placeholder="出库单编号" style="width:200px;margin-left: 20px"></el-input>
          <span style="margin-left: 30px">出库单名称：</span><el-input v-bind:readonly="readonly" type="text" v-model="outForm.name" auto-complete="off" placeholder="出库单名称" style="width:200px;margin-left: 20px"></el-input>
          <span style="margin-left: 55px">出库原因：</span><el-select v-model="outForm.reason" disabled style="width:200px;margin-left: 20px">
        </el-select>
        </el-form-item>
        <el-form-item>
          <span style="margin-left: 30px">出库人：</span><el-select :disabled="readonly" v-model="outForm.employeeId" style="width:200px;margin-left: 20px">
          <el-option
            v-for="item in employees"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
          <span style="margin-left: 50px">领用申请：</span><el-input v-bind:readonly="!show" suffix-icon="el-icon-search" type="text" v-model="outForm.useApplyName" auto-complete="off"  @click.native="addUseApply" style="width:200px;margin-left: 15px"></el-input>
        </el-form-item>
        <el-form-item>
          <span>备注：</span><textarea class="textarea" :readonly="readonly" v-model="outDetail.remarks"></textarea>
        </el-form-item>
      </el-form>
      <h4 class="title">采购出库单明细</h4>
      <el-table
        ref="multipleTable"
        :data="outDetail"
        tooltip-effect="dark"
        style="width: 100%;overflow-x: hidden; overflow-y: hidden;"
        max-height="390"
        @selection-change="handleSelectionChange">
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
          label="现有库存"
          v-if="!readonly"
          width="100" align="center">
        </el-table-column>
        <el-table-column
          prop="useNum"
          label="出库数量"
          width="100" align="center">
        </el-table-column>
        <el-table-column
          prop="rest"
          label="剩余库存"
          v-if="!readonly"
          width="100" align="center">
        </el-table-column>

      </el-table>
      <div style="display: flex;align-items: center;margin-top: 15px;justify-content: flex-end">
        <template v-if=this.show>
          <el-button type="primary" @click="saveOut">保存</el-button>
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
    import updateUseApply from './UpdateUseApply'
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
            if(from==undefined || from=='out'){
                _this.show = true;
            }
            if (from != null && from != '' && from != undefined) {
                var id = this.$route.query.id;
                this.id = id;
                _this.loadOutDetail(this.id);
                this.loading = true;
                getRequest("/out/" + id).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.outForm = resp.data;
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
            updateUseApply
        },
        methods: {
            saveOut(){
                var _this = this;
                _this.loading = true;
                if(this.$route.query.id!=null){
                    putRequestJson("/out/update", {
                        warehouseOut: _this.outForm,
                        warehouseOutDetail: _this.outDetail
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '采购出库单保存成功!'});
                            window.bus.$emit('blogTableReload')
                            _this.$router.replace({path: '/warehouseList'});
                        }
                    }, resp=> {
                        _this.loading = false;
                        _this.$message({type: 'error', message: '采购出库单保存失败!'});
                    });
                }
                else{
                    postRequestJson("/out/", {
                        warehouseOut: _this.outForm,
                        warehouseOutDetail: _this.outDetail
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '采购出库单保存成功!'});
                            window.bus.$emit('blogTableReload')
                            _this.$router.replace({path: '/warehouseList'});
                        }
                    }, resp=> {
                        _this.loading = false;
                        _this.$message({type: 'error', message: '采购出库单保存失败!'});
                    });
                }
            },
            handleSelectionChange(val) {
                this.selItems = val;
            },
            loadOutDetail(pId){
                var _this = this;
                getRequest("/outDetail/"+pId).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.outDetail = resp.data;
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
            loadUseApplyDetail(pId){
                var _this = this;
                _this.outDetail = [];
                getRequest("/useDetail/"+pId).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        for(var i=0; i<resp.data.length; i++){
                            var temp = resp.data[i];
                            Vue.set(temp,"rest", resp.data[i].stock - resp.data[i].useNum);
                            Vue.set(temp,"useApplyDetailId", resp.data[i].id);
                            _this.outDetail.push(temp);
                        }
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
            addUseApply(){
                if(this.show){
                    this.showUseApply = true;
                    this.$nextTick(() => {
                        this.$refs.updateUseApply.init()
                    })
                }
            },
            reselect(selected){
                var _this = this;
                if(selected.id!=null){
                    _this.outForm.useApplyId = selected.id;
                    _this.outForm.useApplyName = selected.name;
                    _this.outForm.reason = selected.reason;
                    _this.loadUseApplyDetail(selected.id);
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
        },
        data() {
            return {
                outForm:{
                    name: "",
                    number: "",
                    outTime: new Date()
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
                from: '',
                loading: false,
                readonly: false,
                outDetail:[],
                selItems: [],
                show: false,
                showUseApply: false,
                employees: [],
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
  .textarea {
    resize: vertical;
    padding: 5px 15px;
    vertical-align: top;
    line-height: 1.5;
    box-sizing: border-box;
    width: 50%;
    font-size: 14px;
    color: #5a5e66;
    background-color: #fff;
    background-image: none;
    border: 1px solid #d8dce5;
    border-radius: 4px;
    transition: border-color .2s cubic-bezier(.645,.045,.355,1);
    margin-bottom: 20px;
  }
</style>
