<template>
  <el-container v-loading="loading">
    <el-main class="main">
      <update-pur-req v-if="showPurReq" ref="updatePurReq" @reselected="reselect"></update-pur-req>
      <el-form v-loading="loading">
        <h3 class="title" v-if="from!='contract' && from!='query'">新增采购合同</h3>
        <h3 class="title" v-if="from=='contract'">修改采购合同</h3>
        <h3 class="title" v-if="from=='query'">采购合同</h3>
        <h4 class="title">基本信息</h4>
        <el-form-item>
          <span style="margin-left: 0px">采购合同编号：</span><el-input v-bind:readonly="readonly" type="text" v-model="contractForm.number" auto-complete="off" placeholder="采购合同编号" style="width:200px;margin-left: 10px"></el-input>
          <span style="margin-left: 30px">采购合同名称：</span><el-input v-bind:readonly="readonly" type="text" v-model="contractForm.name" auto-complete="off" placeholder="采购合同名称" style="width:200px;margin-left: 10px"></el-input>
          <span style="margin-left: 55px">状态：</span><el-select v-model="contractForm.state" disabled style="width:200px;margin-left: 20px">
          <el-option
            v-for="item in states"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
        </el-select>
        </el-form-item>

        <el-form-item>
          <span style="margin-left: 35px">采购人：</span><el-select v-bind:disabled="readonly" v-model="contractForm.employeeId" style="width:200px;margin-left: 20px">
          <el-option
            v-for="item in employees"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          ></el-option>
          </el-select>
          <span style="margin-left: 55px">供应商：</span><el-select v-bind:disabled="readonly" v-model="contractForm.supplierId" style="width:200px;margin-left: 25px">
          <el-option
            v-for="item in suppliers"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
          <span style="margin-left: 30px">签订时间：</span><el-date-picker style="width:200px;margin-left: 20px" v-model="contractForm.signingdate" v-bind:readonly="readonly" type="date" value-format="yyyy-MM-dd" placeholder="签订时间"></el-date-picker>
        </el-form-item>

        <el-form-item>
          <span style="margin-left: 25px">采购类型：</span><el-select v-bind:disabled="readonly" v-model="contractForm.purType" style="width:200px;margin-left: 15px">
          <el-option
            v-for="item in purtype"
            :key="item.value"
            :label="item.label"
            :value="item.label"
          ></el-option>
        </el-select>
          <span style="margin-left: 35px">采购申请单：</span><el-input v-bind:readonly="!show" suffix-icon="el-icon-search" type="text" v-model="contractForm.purReqName" auto-complete="off"  @click.native="addPurReq" style="width:200px;margin-left: 20px"></el-input>
          <span style="margin-left: 30px">支付方式：</span><el-select v-bind:disabled="readonly" v-model="contractForm.payment" style="width:200px;margin-left: 20px">
          <el-option
            v-for="item in payment"
            :key="item.value"
            :label="item.label"
            :value="item.label"
          ></el-option>
        </el-select>
        </el-form-item>
      </el-form>
      <h4 class="title">采购合同明细</h4>
      <el-table
        ref="multipleTable"
        :data="contractDetail"
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
          prop="purOrder"
          label="订购数量"
          width="100" align="center">
          <template slot-scope="scope">
            <el-input v-if="show" size="small" v-model="scope.row.purOrder" @change="countAmount(scope.$index, 1)"></el-input>
            <span v-else>{{scope.row.purOrder}}</span>
          </template>
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
          <el-button type="primary" @click="saveContract">保存</el-button>
          <el-button type="primary" @click="excuteContract">提交</el-button>
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
    import formatDate from '@/utils/utils'
    import updatePurReq from './UpdatePurReq'
    import Vue from 'vue'
    import {postRequestJson, putRequest, putRequestJson} from "../../utils/api";

    export default {
        mounted: function () {
            var from = this.$route.query.from;
            this.from = from;
            var _this = this;
            _this.loadSupplierAndEmpl();
            if(from == "query"){
                this.readonly = true;
            }
            if(from==undefined || from=='contract'){
                _this.show = true;
            }
            if (from != null && from != '' && from != undefined) {
                var id = this.$route.query.id;
                this.id = id;
                this.loading = true;
                this.loadContractDetail(this.id);
                getRequest("/contract/" + id).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.contractForm = resp.data;
                        _this.contractForm.signingdate = formatDate(_this.contractForm.signingdate);
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
            updatePurReq,
            mavonEditor,
        },
        methods: {
            saveContract(){
                var _this = this;
                _this.loading = true;
                if(this.$route.query.id!=null){
                    _this.beforeSave();
                    putRequestJson("/contract/update", {
                        purContract: _this.contractForm,
                        purContractDetail: _this.contractDetail
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '采购合同修改成功!'});
                            window.bus.$emit('blogTableReload')
                            _this.$router.replace({path: '/purReqList'});
                        }
                    }, resp=> {
                        _this.loading = false;
                        _this.$message({type: 'error', message: '采购合同修改失败!'});
                    });
                }else {
                    _this.beforeSave();
                    postRequestJson("/contract/", {
                        purContract: _this.contractForm,
                        purContractDetail: _this.contractDetail
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '采购合同保存成功!'});
                            window.bus.$emit('blogTableReload')
                            _this.$router.replace({path: '/purReqList'});
                        }
                    }, resp=> {
                        _this.loading = false;
                        _this.$message({type: 'error', message: '采购合同保存失败!'});
                    });
                }
            },
            beforeSave(){
                var _this = this;
                var tempDetail = _this.contractDetail;
                var amount = 0;
                for(let detail of tempDetail){
                    amount = amount+detail.amount;
                }
                Vue.set(_this.contractForm,'amount', amount);
                for(var i; i<this.contractDetail.length; i++){
                    if(this.contractDetail.number=="" || this.contractDetail.number==null){
                        _this.$message({type: 'info', message: '物料不能为空!'});
                        return;
                    }
                    if(this.contractDetail.purQty=="" || this.contractDetail.purQty==null){
                        _this.$message({type: 'info', message: '申请数量不能为空!'});
                        return;
                    }
                    if(this.contractDetail.price=="" || this.contractDetail.price==null){
                        _this.$message({type: 'info', message: '单价不能为空!'});
                        return;
                    }
                }
            },
            excuteContract(){
                var _this = this;
                _this.loading = true;
                _this.beforeSave();
                if(this.id==null){
                    postRequestJson("/contract/addAndSubmit", {
                        purContract: _this.contractForm,
                        purContractDetail: _this.contractDetail
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200 && resp.data.status == 'success') {
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
                    putRequest("/contract/submit", {
                        id: this.id,
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200 && resp.data.status == 'success') {
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
                    this.contractDetail.splice(index, 1);
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            loadPurReqDetail(pId){
                var _this = this;
                getRequest("/purReqDetail/unClose/"+pId).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.purReqDetail = resp.data;
                        for(let detail of _this.purReqDetail){
                            Vue.set(detail,'purOrder', detail.purQty);
                            _this.contractDetail.push(detail);
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
            deleteMany(){
                var items = this.selItems;
                var details = this.contractDetail;
                for(var i=0; i<items.length; i++){
                    for(var j=0; j<details.length; j++){
                        if(items[i].materialNumber == details[j].materialNumber
                            || items[i].materialNumber==null || items[i].materialNumber==""){
                            this.contractDetail.splice(j, 1);
                            continue;
                        }
                    }
                }
            },
            reselect(selected){
                var _this = this;
                if(selected.id!=null){
                    _this.contractForm.purreqId = selected.id;
                    _this.contractForm.purReqName = selected.name;
                    _this.loadPurReqDetail(selected.id);
                }
            },
            countAmount(index, state){
                var contractDetail = this.contractDetail[index];
                if(state == 2 && contractDetail.price<=0){
                    this.contractDetail[index].price = "";
                    this.$message({type: 'info', message: '单价应大于0!!!'});
                }
                if(state == 1 && contractDetail.purOrder<=0){
                    this.contractDetail[index].purOrder = "";
                    this.$message({type: 'info', message: '订购数量应大于0!!!'});
                }
                if(contractDetail.purOrder!=null && contractDetail.purOrder!=""
                    && contractDetail.price!=null && contractDetail.price!=""){
                    this.contractDetail[index].amount = contractDetail.purOrder * contractDetail.price;
                }
            },
            addPurReq(){
                if(this.show){
                    this.showPurReq = true;
                    this.$nextTick(() => {
                        this.$refs.updatePurReq.init()
                    })
                }
            },
            loadSupplierAndEmpl(){
                var _this = this;
                getRequest("/supplier/all").then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.suppliers = resp.data;
                    } else {
                        _this.$message({type: 'error', message: '页面加载失败!'})
                    }
                }, resp=> {
                    _this.loading = false;
                    _this.$message({type: 'error', message: '页面加载失败!'})
                });
                getRequest("/employee/pur").then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.employees = resp.data;
                    } else {
                        _this.$message({type: 'error', message: '页面加载失败!'})
                    }
                }, resp=> {
                    _this.loading = false;
                    _this.$message({type: 'error', message: '页面加载失败!'})
                })
            },
            loadContractDetail(pId){
                var _this = this;
                getRequest("/contractDetail/"+pId).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.contractDetail = resp.data;
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
        },
        data() {
            return {
                contractForm:{
                    name: "",
                    number: "",
                    state: "INPUT",
                    purreqId: "",
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
                contractDetail:[],
                purReqDetail: [],
                selItems: [],
                suppliers:[],
                employees: [],
                payment: [{
                    value: '1',
                    label: '网银转账'
                }, {
                    value: '2',
                    label: '现金'
                }, {
                    value: '3',
                    label: '支票'
                }],
                purtype: [{
                    value: '1',
                    label: '维修使用'
                }, {
                    value: '2',
                    label: '采购入库'
                }, {
                    value: '3',
                    label: '办公使用'
                }],
                show: false,
                showPurReq: false,
                material: {},
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
