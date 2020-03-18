<style type="text/css">
  .blog_table_footer {
    display: flex;
    box-sizing: content-box;
    padding-top: 10px;
    padding-bottom: 0px;
    margin-bottom: 0px;
    justify-content: space-between;
  }
</style>

<template>
  <div>
    <div style="display: flex;justify-content: flex-start;margin-bottom: 10px">
      <el-button type="primary" icon="icon-zhyqadd" size="mini" style="margin-left: 3px;" @click="addStock">新增</el-button>
    </div>
    <div style="display: flex;justify-content: flex-start">
      <span style="font-size:16px">存管仓库：</span>
      <el-select v-model="keywords" style="width:100px" size="mini">
        <el-option
          v-for="item in warehouse"
          :key="item.id"
          :label="item.name"
          :value="item.id"
        ></el-option>
      </el-select>
      <el-button type="primary" icon="el-icon-search" size="mini" style="margin-left: 3px" @click="search">搜索
      </el-button>

      <el-button type="primary" v-if="!isZero" size="mini" style="margin-left:600px" @click="searchZeroClick">查看历史库存
      </el-button>
      <el-button type="primary" v-if="isZero" size="mini" style="margin-left:600px" @click="searchClick">查看现有库存
      </el-button>
    </div>
    <!--<div style="width: 100%;height: 1px;background-color: #20a0ff;margin-top: 8px;margin-bottom: 0px"></div>-->
    <add-warehouse-stock v-if="showReceipt" ref="addWarehouseStock" @reselected="reselect"></add-warehouse-stock>
    <query-warehouse-stock v-if="showHistory" ref="queryWarehouseStock" :stockId="stockId"></query-warehouse-stock>
    <el-table
      ref="multipleTable"
      :data="warehouseStock"
      tooltip-effect="dark"
      style="width: 100%;overflow-x: hidden; overflow-y: hidden;"
      max-height="390"
      v-loading="loading">
      <el-table-column
        label="编号"
        width="60" align="left">
        <template slot-scope="scope"><span style="color: #409eff;cursor: pointer" @click="query(scope.row)">{{ scope.row.id}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="warehouseName"
        label="存管仓库"
        width="80" align="left">
      </el-table-column>
      <el-table-column
        prop="materialType"
        label="物料类型"
        width="80" align="left">
      </el-table-column>
      <el-table-column
        prop="materialName"
        label="物品名称"
        width="80" align="left">
      </el-table-column>
      <el-table-column
        prop="materialModel"
        label="物料规格"
        width="80" align="left">
      </el-table-column>
      <el-table-column
        prop="stock"
        label="库存"
        width="80" align="left">
      </el-table-column>
      <el-table-column
        prop="price"
        label="单价"
        width="80" align="left">
      </el-table-column>
      <el-table-column
        prop="amount"
        label="总价"
        width="80" align="left">
      </el-table-column>
      <el-table-column
        prop="lastReceiptDate"
        label="最后入库时间"
        :formatter="dateFormat"
        width="160" align="left">
      </el-table-column>
      <el-table-column
        prop="lastOutDate"
        label="最后出库时间"
        :formatter="dateFormat"
        width="160" align="left">
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="query(scope.row)">详情
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="blog_table_footer">
      <span></span>
      <el-pagination
        background
        :page-size="pageSize"
        layout="prev, pager, next"
        :total="totalCount" @current-change="currentChange" v-show="this.warehouseStock!=null && this.warehouseStock.length>0">
      </el-pagination>
    </div>
  </div>
</template>

<script>
    import {putRequest} from '@/utils/api'
    import {getRequest} from '@/utils/api'
    import '@/assets/icon/iconfont.css'
    import moment from "moment";
    import addWarehouseStock from "./AddWarehouseStock";
    import queryWarehouseStock from "./QueryWarehouseStock"
    import Vue from 'vue'
    import {postRequest, postRequestJson} from "../../utils/api";
    //  var bus = new Vue()

    export default{
        data() {
            return {
                selItems: [],
                loading: false,
                currentPage: 1,
                totalCount: -1,
                pageSize: 6,
                keywords: '',
                dustbinData: [],
                warehouseStock: [],
                success: false,
                showReceipt: false,
                showHistory: false,
                warehouse: [],
                isZero: false,
                stockId: ""
            }
        },
        mounted: function () {
            var _this = this;
            this.loading = true;
            this.loadWarehouseStock(1, this.pageSize);
            this.loadData();
            window.bus.$on('blogTableReload', function () {
                _this.loading = true;
                _this.loadWarehouseStock(_this.currentPage, _this.pageSize);
            })
        },
        components: {
            addWarehouseStock,
            queryWarehouseStock
        },
        methods: {
            search(){
                if(!this.isZero){
                    this.loadWarehouseStock(1, this.pageSize);
                }else{
                    this.loadZeroStock(1, this.pageSize);
                }
            },
            searchClick(){
                this.loadWarehouseStock(1, this.pageSize);
                this.isZero=false;
            },
            searchZeroClick(){
                this.loadZeroStock(1, this.pageSize);
                this.isZero=true;
            },
            loadZeroStock(page, count){
                var _this = this;
                var url = '';
                if (this.state == 5) {
                    url = "/stock/getZero" + "?page=" + page + "&count=" + count + "&keywords=" + this.keywords;
                }
                getRequest(url).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.success = true;
                        _this.warehouseStock = resp.data.stock;
                        _this.totalCount = resp.data.totalCount;
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
                    if(!this.success){
                        _this.$message({type: 'error', message: '数据加载失败!!!'});
                    }
                })
            },
            itemClick(row){
                this.$router.push({path: '/queryWarehouseStock', query: {from: "query",id:row.id}})
            },
            //翻页
            currentChange(currentPage){
                this.currentPage = currentPage;
                this.loading = true;
                if(this.isZero){
                    this.loadZeroStock(currentPage, this.pageSize);
                }else{
                    this.loadWarehouseStock(currentPage, this.pageSize);
                }
            },
            loadWarehouseStock(page, count){
                var _this = this;
                var url = '';
                if (this.state == 5) {
                    url = "/stock/all" + "?page=" + page + "&count=" + count + "&keywords=" + this.keywords;
                }
                getRequest(url).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.success = true;
                        _this.warehouseStock = resp.data.stock;
                        _this.totalCount = resp.data.totalCount;
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
                    if(!this.success){
                        _this.$message({type: 'error', message: '数据加载失败!!!'});
                    }
                })
            },
            dateFormat(row, column, cellValue, index){
                const daterc = row[column.property];
                if(daterc!=null){
                    var date = row[column.property];
                    if(date == undefined){return ''}
                    return moment(date).format("YYYY-MM-DD HH:mm:ss")
                }
            },
            reselect(selected){
                var _this = this;
                var warehouseStock = [];
                var receiptDetail = [];
                if(selected!="" && selected!=null){
                    getRequest("/receiptDetail/"+selected).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200) {
                            receiptDetail = resp.data;
                            for(var i=0; i<receiptDetail.length; i++){
                                var temp = {};
                                Vue.set(temp, 'stock', receiptDetail[i].receipt);
                                Vue.set(temp, 'price', receiptDetail[i].price);
                                Vue.set(temp, 'amount', receiptDetail[i].amount);
                                Vue.set(temp, 'materialId', receiptDetail[i].materialId);
                                Vue.set(temp, 'warehouseId', receiptDetail[i].warehouseId);
                                Vue.set(temp, 'lastReceiptDate', new Date());
                                warehouseStock.push(temp);
                            }
                            _this.addWarehouseStock(warehouseStock, selected);
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
                }
            },
            addWarehouseStock(warehouseStock, receiptId){
                var _this = this;
                postRequestJson("/stock/", {
                    warehouseStock: warehouseStock,
                    receiptId: receiptId
                }).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200 && resp.data.status == 'success') {
                        _this.$message({type: 'success', message: '库存保存成功!'});
                        window.bus.$emit('blogTableReload')
                    }
                }, resp=> {
                    _this.loading = false;
                    _this.$message({type: 'error', message: '库存保存失败!'});
                });
            },
            addStock(){
                this.showReceipt = true;
                this.$nextTick(() => {
                    this.$refs.addWarehouseStock.init()
                })
            },
            query(row){
                this.showHistory = true;
                this.stockId = row.id;
                this.$nextTick(() => {
                    this.$refs.queryWarehouseStock.init()
                })
            },
            loadData(){
                var _this = this;
                getRequest("/warehouse/all").then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.warehouse = resp.data;
                        var temp = {};
                        Vue.set(temp, "id", "");
                        Vue.set(temp, "name", "");
                        _this.warehouse.push(temp);
                    } else {
                        _this.$message({type: 'error', message: '页面加载失败!'})
                    }
                }, resp=> {
                    _this.loading = false;
                    _this.$message({type: 'error', message: '页面加载失败!'})
                })
            }
        },
        props: ['state', 'showEdit', 'showDelete', 'activeName', 'showRestore']
    }
</script>
