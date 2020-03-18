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
      <el-button type="primary" icon="icon-zhyqadd" size="mini" style="margin-left: 3px;"  @click="add">新增</el-button>
    </div>
    <div style="display: flex;justify-content: flex-start">
      <el-input
        placeholder="通过名称搜索"
        prefix-icon="el-icon-search"
        v-model="keywords" style="width: 400px" size="mini">
      </el-input>
      <el-button type="primary" icon="el-icon-search" size="mini" style="margin-left: 3px" @click="searchClick">搜索
      </el-button>
    </div>
    <!--<div style="width: 100%;height: 1px;background-color: #20a0ff;margin-top: 8px;margin-bottom: 0px"></div>-->
    <el-table
      ref="multipleTable"
      :data="warehouseOut"
      tooltip-effect="dark"
      style="width: 100%;overflow-x: hidden; overflow-y: hidden;"
      max-height="390"
      @selection-change="handleSelectionChange" v-loading="loading">
      <el-table-column
        type="selection"
        width="35" align="left" v-if="showEdit || showDelete">
      </el-table-column>
      <el-table-column
        label="单据名称"
        width="100" align="left">
        <template slot-scope="scope"><span style="color: #409eff;cursor: pointer" @click="itemClick(scope.row)">{{ scope.row.name}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="number"
        label="单据编号"
        width="100" align="left">
      </el-table-column>
      <el-table-column
        prop="employeeName"
        label="出库人"
        width="80" align="left">
      </el-table-column>
      <el-table-column
        prop="reason"
        label="出库原因"
        width="80" align="left">
      </el-table-column>
      <el-table-column
        prop="outTime"
        label="出库时间"
        :formatter="dateFormat"
        width="160" align="left">
      </el-table-column>
      <el-table-column label="操作" align="center" v-if="showEdit || showDelete">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)" v-if="showEdit">编辑
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
        :total="totalCount" @current-change="currentChange" v-show="this.warehouseOut!=null && this.warehouseOut.length>0">
      </el-pagination>
    </div>
  </div>
</template>

<script>
    import {putRequest} from '@/utils/api'
    import {getRequest} from '@/utils/api'
    import '@/assets/icon/iconfont.css'
    import moment from "moment";
    import Vue from 'vue'
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
                warehouseOut: [],
                success: false
            }
        },
        mounted: function () {
            var _this = this;
            this.loading = true;
            this.loadWarehouseOut(1, this.pageSize);
            var _this = this;
            window.bus.$on('blogTableReload', function () {
                _this.loading = true;
                _this.loadWarehouseOut(_this.currentPage, _this.pageSize);
            })
        },
        methods: {
            searchClick(){
                this.loadWarehouseOut(1, this.pageSize);
            },
            add(){
                this.$router.push({path: '/addWarehouseOut'})
            },
            itemClick(row){
                this.$router.push({path: '/addWarehouseOut', query: {from: "query",id:row.id}})
            },
            deleteMany(){
                var selItems = this.selItems;
                for (var i = 0; i < selItems.length; i++) {
                    if(selItems[i].state == "下达" || selItems[i].state == "关闭"){
                        this.$message({type: 'info', message: '下达或关闭状态不能删除'});
                        return;
                    }
                    this.dustbinData.push(selItems[i].id)
                }
                this.deleteToDustBin()
            },
            //翻页
            currentChange(currentPage){
                this.currentPage = currentPage;
                this.loading = true;
                this.loadWarehouseOut(currentPage, this.pageSize);
            },
            loadWarehouseOut(page, count){
                var _this = this;
                var url = '';
                if (this.state == 1) {
                    url = "/out/all" + "?page=" + page + "&count=" + count + "&keywords=" + this.keywords;
                }
                getRequest(url).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.success = true;
                        _this.warehouseOut = resp.data.out;
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
            handleSelectionChange(val) {
                this.selItems = val;
            },
            handleEdit(index, row) {
                if(row.state == "下达" || row.state=="关闭"){
                    this.$message({type: 'info', message: '下达或关闭状态不能编辑'});
                }
                else if(row.state == "制单"){
                    this.$router.push({path: '/addWarehouseOut', query: {from: this.activeName, id: row.id}});
                }
            },
            handleDelete(index, row) {
                if(row.state == "下达" || row.state=="关闭"){
                    this.$message({type: 'info', message: '下达或关闭状态不能删除'});
                }
                else if(row.state == "制单"){
                    this.dustbinData.push(row.id);
                    this.deleteToDustBin(row.state);
                }
            },
            deleteToDustBin(){
                var _this = this;
                this.$confirm('永久删除该单据, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    _this.loading = true;
                    var url = '';
                    if (_this.state == 1) {
                        url = "/out/delete";
                    }
                    putRequest(url, {ids: _this.dustbinData}).then(resp=> {
                        if (resp.status == 200) {
                            var data = resp.data;
                            _this.$message({type: data.status, message: data.msg});
                            if (data.status == 'success') {
                                window.bus.$emit('blogTableReload')//通过选项卡都重新加载数据
                            }
                        } else {
                            _this.$message({type: 'error', message: '删除失败!'});
                        }
                        _this.loading = false;
                        _this.dustbinData = []
                    }, resp=> {
                        _this.loading = false;
                        _this.$message({type: 'error', message: '删除失败!'});
                        _this.dustbinData = []
                    });
                }).catch(() => {
                    _this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                    _this.dustbinData = []
                });
            },
            dateFormat(row, column, cellValue, index){
                const daterc = row[column.property];
                if(daterc!=null){
                    var date = row[column.property];
                    if(date == undefined){return ''}
                    return moment(date).format("YYYY-MM-DD HH:mm:ss")
                }
            },
        },
        props: ['state', 'showEdit', 'showDelete', 'activeName', 'showRestore']
    }
</script>
