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
      <el-button type="primary" icon="icon-zhyqcheck" size="mini" style="margin-left: 3px;"  @click="check">审核</el-button>
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
      :data="useApply"
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
        prop="state"
        label="审核状态"
        width="100" align="left">
      </el-table-column>
      <el-table-column
        prop="applicant"
        label="申请人"
        width="80" align="left">
      </el-table-column>
      <el-table-column
        prop="applyTime"
        label="申请时间"
        :formatter="dateFormat"
        width="160" align="center">
      </el-table-column>
      <el-table-column
        prop="isOut"
        label="是否出库"
        width="120" align="center">
      </el-table-column>
      <el-table-column label="操作" align="center" v-if="showEdit || showDelete">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)" v-if="showEdit">编辑
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)" v-if="showDelete">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="blog_table_footer">
      <el-button type="danger" size="mini" style="margin: 0px;" v-show="this.useApply!=null && this.useApply.length>0"
                 :disabled="this.selItems!=null && this.selItems.length==0" @click="deleteMany">批量删除
      </el-button>
      <span></span>
      <el-pagination
        background
        :page-size="pageSize"
        layout="prev, pager, next"
        :total="totalCount" @current-change="currentChange" v-show="this.useApply!=null && this.useApply.length>0">
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
                useApply: [],
                success: false
            }
        },
        mounted: function () {
            var _this = this;
            this.loading = true;
            this.loadUseApply(1, this.pageSize);
            window.bus.$on('blogTableReload', function () {
                _this.loading = true;
                _this.loadUseApply(_this.currentPage, _this.pageSize);
            })
        },
        methods: {
            searchClick(){
                this.loadUseApply(1, this.pageSize);
            },
            add(){
                this.$router.push({path: '/addUseApply'})
            },
            itemClick(row){
                this.$router.push({path: '/addUseApply', query: {from: "query",id:row.id}})
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
                this.loadUseApply(currentPage, this.pageSize);
            },
            loadUseApply(page, count){
                var _this = this;
                var url = '';
                if (this.state == -3) {
                    url = "/use/all" + "?page=" + page + "&count=" + count + "&keywords=" + this.keywords;
                }
                getRequest(url).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.success = true;
                        _this.useApply = resp.data.useApply;
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
                this.$router.push({path: '/addUseApply', query: {from: this.activeName, id: row.id}});
            },
            handleDelete(index, row) {
                if(row.state == "已驳回" || row.state=="已审核"){
                    this.$message({type: 'info', message: '已驳回或已审核状态不能删除'});
                }
                else if(row.state == "待审核"){
                    this.dustbinData.push(row.id);
                    this.deleteToDustBin(row.state);
                }
            },
            check(){
                var _this = this;
                if(this.selItems.length>1){
                    _this.$message({type: 'warning', message: '一次只能审核一张单据!'})
                }
                else if(this.selItems.length<1){
                    _this.$message({type: 'warning', message: '请选择一张单据!'})
                }
                else{
                    var id = this.selItems[0].id;
                    putRequest("/use/check/", {
                        id: id
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '审核成功!'});
                            window.bus.$emit('blogTableReload')
                        }
                        else{
                            _this.$message({type: 'error', message: resp.data.msg});
                        }
                    }, resp=> {
                        _this.loading = false;
                        _this.$message({type: 'error', message: '审核失败!'});
                    })
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
                    if (_this.state == -3) {
                        url = "/use/delete";
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
                    return moment(date).format("YYYY-MM-DD HH:mm")
                }
            },
        },
        props: ['state', 'showEdit', 'showDelete', 'activeName', 'showRestore']
    }
</script>
