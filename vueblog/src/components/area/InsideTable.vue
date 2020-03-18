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
      <el-button icon="icon-zhyqadd" size="mini" style="margin-left: 3px;"  @click="add">新建区域</el-button>
      <!--      <el-button type="primary" icon="icon-zhyqcheck" size="mini" style="margin-left: 3px;"  @click="check">审核</el-button>-->
    </div>
    <div style="display: flex;justify-content: flex-start">
      <el-input
        placeholder="通过区域名称搜索"
        prefix-icon="el-icon-search"
        v-model="keywords" style="width: 400px" size="mini">
      </el-input>
      <el-button type="primary" icon="el-icon-search" size="mini" style="margin-left: 3px" @click="searchClick">搜索
      </el-button>
    </div>
    <!--<div style="width: 100%;height: 1px;background-color: #20a0ff;margin-top: 8px;margin-bottom: 0px"></div>-->
    <add-in-area v-if="showAdd" ref="addInArea" :state="states" :id="id"></add-in-area>
    <el-table
      ref="multipleTable"
      :data="inArea"
      tooltip-effect="dark"
      style="width: 100%;overflow-x: hidden; overflow-y: hidden;"
      max-height="390"
      @selection-change="handleSelectionChange" v-loading="loading">
      <el-table-column
        type="selection"
        width="35" align="left" v-if="showEdit || showDelete">
      </el-table-column>
      <el-table-column
        label="序号"
        width="80" align="center">
        <template slot-scope="scope"><span>{{ scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="parkName"
        label="园区"
        width="100" align="left">
      </el-table-column>
      <el-table-column
        prop="buildingName"
        label="楼栋"
        width="100" align="left">
      </el-table-column>
      <el-table-column
        prop="floorNumber"
        label="楼层"
        width="100" align="center">
      </el-table-column>
      <el-table-column
        prop="name"
        label="区域名称"
        width="200" align="center">
      </el-table-column>
      <el-table-column
        prop="explain"
        label="区域说明"
        width="200" align="center">
      </el-table-column>
      <el-table-column label="操作" align="center" v-if="showEdit || showDelete">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.row)" v-if="showEdit">编辑
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.row)" v-if="showDelete">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="blog_table_footer">
      <el-button type="danger" size="mini" style="margin: 0px;" v-show="this.inArea!=null && this.inArea.length>0"
                 :disabled="this.selItems!=null && this.selItems.length==0" @click="deleteMany">批量删除
      </el-button>
      <span></span>
      <el-pagination
        background
        :page-size="pageSize"
        layout="prev, pager, next"
        :total="totalCount" @current-change="currentChange" v-show="this.inArea!=null && this.inArea.length>0">
      </el-pagination>
    </div>
  </div>
</template>

<script>
    import {putRequest} from '@/utils/api'
    import {getRequest} from '@/utils/api'
    import '@/assets/icon/iconfont.css'
    import addInArea from './AddInArea'

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
                inArea: [],
                success: false,
                showAdd: false,
                states: "",
                id: ""
            }
        },
        mounted: function () {
            var _this = this;
            this.loading = true;
            this.loadInArea(1, this.pageSize);
            console.log()
            window.bus.$on('blogTableReload', function () {
                _this.loading = true;
                _this.loadInArea(_this.currentPage, _this.pageSize);
            })
        },
        methods: {
            searchClick(){
                this.loadInArea(1, this.pageSize);
            },
            add(){
                this.states = "add";
                this.showAdd = true;
                this.$nextTick(() => {
                    this.$refs.addInArea.init()
                })
            },
            deleteMany(){
                var selItems = this.selItems;
                for (var i = 0; i < selItems.length; i++) {
                    this.dustbinData.push(selItems[i].id)
                }
                this.deleteToDustBin()
            },
            //翻页
            currentChange(currentPage){
                this.currentPage = currentPage;
                this.loading = true;
                this.loadInArea(currentPage, this.pageSize);
            },
            loadInArea(page, count){
                var _this = this;
                var url = '';
                url = "/inArea/getAll" + "?page=" + page + "&count=" + count + "&keywords=" + this.keywords;
                getRequest(url).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.success = true;
                        _this.inArea = resp.data.inArea;
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
            handleEdit(row) {
                this.states = "update";
                this.showAdd = true;
                this.id = row.id;
                this.$nextTick(() => {
                    this.$refs.addInArea.init()
                })
            },
            handleDelete(row) {
                this.dustbinData.push(row.id);
                this.deleteToDustBin();
            },
            deleteToDustBin(){
                var _this = this;
                this.$confirm('永久删除该区域, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    _this.loading = true;
                    var url = "/inArea/delete";
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
        },
        components: {
            addInArea,
        },
        props: ['state', 'showEdit', 'showDelete', 'activeName', 'showRestore']
    }
</script>
