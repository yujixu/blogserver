<template>
  <el-container v-loading="loading">
    <el-main class="main">
      <update-pur-contract v-if="showContract" ref="updatePurContract" @reselected="reselect"></update-pur-contract>
      <el-form v-loading="loading">
        <h3 class="title" v-if="from!='receipt' && from!='query'">新增采购入库单</h3>
        <h3 class="title" v-if="from=='receipt'">修改采购入库单</h3>
        <h3 class="title" v-if="from=='query'">采购入库单</h3>
        <h4 class="title">基本信息</h4>
        <el-form-item>
          <span style="margin-left: 0px">入库单编号：</span><el-input v-bind:readonly="readonly" type="text" v-model="receiptForm.number" auto-complete="off" placeholder="入库单编号" style="width:200px;margin-left: 20px"></el-input>
          <span style="margin-left: 30px">入库单名称：</span><el-input v-bind:readonly="readonly" type="text" v-model="receiptForm.name" auto-complete="off" placeholder="入库单名称" style="width:200px;margin-left: 20px"></el-input>
          <span style="margin-left: 55px">状态：</span><el-select v-model="receiptForm.state" disabled style="width:200px;margin-left: 20px">
          <el-option
            v-for="item in states"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
        </el-select>
        </el-form-item>
        <el-form-item>
          <span style="margin-left: 30px">采购人：</span><el-select disabled v-model="receiptForm.employeeId" style="width:200px;margin-left: 20px">
            <el-option
              v-for="item in employees"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
          <span style="margin-left: 60px">供应商：</span><el-select disabled v-model="receiptForm.supplierId" style="width:200px;margin-left: 20px">
            <el-option
              v-for="item in suppliers"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
          <span style="margin-left: 35px">入库时间：</span><el-date-picker style="width:200px;margin-left: 15px" v-model="receiptForm.receiptdate" v-bind:readonly="readonly" type="date" value-format="yyyy-MM-dd" placeholder="入库时间"></el-date-picker>
        </el-form-item>
        <el-form-item>
          <span style="margin-left: 20px">采购类型：</span><el-select disabled v-model="receiptForm.purType" style="width:200px;margin-left: 20px">
        </el-select>
          <span style="margin-left: 50px">采购合同：</span><el-input v-bind:readonly="!show" suffix-icon="el-icon-search" type="text" v-model="receiptForm.contractName" auto-complete="off"  @click.native="addContract" style="width:200px;margin-left: 15px"></el-input>
          <span style="margin-left: 30px">支付方式：</span><el-select disabled v-model="receiptForm.payment" style="width:200px;margin-left: 20px">
        </el-select>
        </el-form-item>
        <el-form-item>
          <span>入库人：</span><el-select v-bind:disabled="readonly" v-model="receiptForm.consigneeId" style="width:200px;margin-right: 630px;margin-left: 20px">
          <el-option
            v-for="item in employees"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>

        </el-form-item>
      </el-form>
      <h4 class="title">采购入库单明细</h4>
      <el-table
        ref="multipleTable"
        :data="receiptDetail"
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
          prop="warehouseId"
          label="存管仓库"
          width="120" align="center">
          <template slot-scope="scope">
            <el-select v-model="scope.row.warehouseId" :disabled="!show" >
              <el-option
                v-for="item in warehouse"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              ></el-option>
            </el-select>
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
        </el-table-column>
        <el-table-column
          prop="receipt"
          label="入库数量"
          width="100" align="center">
          <template slot-scope="scope">
            <el-input v-if="show" size="small" v-model="scope.row.receipt" @change="countAmount(scope.$index)"></el-input>
            <span v-else>{{scope.row.receipt}}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="price"
          label="单价"
          width="100" align="center">
        </el-table-column>
        <el-table-column
          prop="amount"
          label="金额"
          width="100" align="center">
        </el-table-column>
      </el-table>
      <div style="display: flex;align-items: center;margin-top: 15px;justify-content: flex-end">
        <template v-if=this.show>
          <el-button type="primary" @click="saveReceipt">保存</el-button>
          <el-button type="primary" @click="excuteReceipt">提交</el-button>
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
    import updatePurContract from './UpdatePurContract'
    import Vue from 'vue'
    import {postRequestJson, putRequest, putRequestJson} from "../../utils/api";

    export default {
        mounted: function () {
            var from = this.$route.query.from;
            this.from = from;
            var _this = this;
            _this.loadSupplierAndEmplAndWarehouse();
            if(from == "query"){
                this.readonly = true;
            }
            if(from==undefined || from=='receipt'){
                _this.show = true;
            }
            if (from != null && from != '' && from != undefined) {
                var id = this.$route.query.id;
                this.id = id;
                _this.loadReceiptDetail(this.id);
                this.loading = true;
                getRequest("/receipt/" + id).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.receiptForm = resp.data;
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
            updatePurContract
        },
        methods: {
            saveReceipt(){
                var _this = this;
                _this.loading = true;
                if(this.$route.query.id!=null){
                    putRequestJson("/receipt/update", {
                        warehouseReceipt: _this.receiptForm,
                        warehouseReceiptDetail: _this.receiptDetail
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
                    postRequestJson("/receipt/", {
                        warehouseReceipt: _this.receiptForm,
                        warehouseReceiptDetail: _this.receiptDetail
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
            excuteReceipt(){
                var _this = this;
                _this.loading = true;
                if(this.$route.query.id!=null){
                    putRequest("/receipt/submit", {
                        id: this.id,
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200) {
                            _this.$message({type: 'success', message: '提交成功!'});
                            window.bus.$emit('blogTableReload')
                            _this.$router.replace({path: '/warehouseList'});
                        }
                    }, resp=> {
                        _this.loading = false;
                        _this.$message({type: 'error', message: '提交失败!'});
                    })
                }
                else{
                    postRequestJson("/receipt/addAndSubmit", {
                        warehouseReceipt: _this.receiptForm,
                        warehouseReceiptDetail: _this.receiptDetail
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200) {
                            _this.$message({type: 'success', message: '提交成功!'});
                            window.bus.$emit('blogTableReload')
                            _this.$router.replace({path: '/warehouseList'});
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
            loadReceiptDetail(pId){
                var _this = this;
                getRequest("/receiptDetail/"+pId).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.receiptDetail = resp.data;
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
            loadContractDetail(pId){
                var _this = this;
                getRequest("/contractDetail/"+pId).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.receiptDetail = resp.data;
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
            addContract(){
                if(this.show){
                    this.showContract = true;
                    this.$nextTick(() => {
                        this.$refs.updatePurContract.init()
                    })
                }
            },
            reselect(selected){
                var _this = this;
                if(selected.id!=null){
                    _this.receiptForm.contractId = selected.id;
                    _this.receiptForm.contractName = selected.name;
                    _this.receiptForm.purType = selected.purType;
                    _this.receiptForm.supplierId = selected.supplierId;
                    _this.receiptForm.employeeId = selected.employeeId;
                    _this.receiptForm.payment = selected.payment;
                    _this.loadContractDetail(selected.id);
                }
            },
            loadSupplierAndEmplAndWarehouse(){
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
                getRequest("/warehouse/all").then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.warehouse = resp.data;
                    } else {
                        _this.$message({type: 'error', message: '页面加载失败!'})
                    }
                }, resp=> {
                    _this.loading = false;
                    _this.$message({type: 'error', message: '页面加载失败!'})
                })
            },
            countAmount(index){
                var receiptDetail = this.receiptDetail[index];
                if(receiptDetail.receipt<=0){
                    this.receiptDetail[index].receipt = "";
                    this.$message({type: 'info', message: '入库数量应大于0!!!'});
                }
                if(receiptDetail.receipt>receiptDetail.purOrder){
                    this.receiptDetail[index].receipt = "";
                    this.$message({type: 'info', message: '入库数量不能大于订购数量!!!'});
                }
                if(receiptDetail.receipt!=null && receiptDetail.receipt!=""
                    && receiptDetail.price!=null && receiptDetail.price!=""){
                    this.receiptDetail[index].amount = receiptDetail.receipt * receiptDetail.price;
                }
            }
        },
        data() {
            return {
                receiptForm:{
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
                receiptDetail:[],
                selItems: [],
                show: false,
                showContract: false,
                employees: [],
                suppliers: [],
                contractDetail: [],
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
