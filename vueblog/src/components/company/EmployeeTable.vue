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
      :data="employee"
      tooltip-effect="dark"
      style="width: 100%;overflow-x: hidden; overflow-y: hidden;"
      max-height="390"
      @selection-change="handleSelectionChange" v-loading="loading">
      <el-table-column
        type="selection"
        width="35" align="left" v-if="showEdit || showDelete">
      </el-table-column>
      <el-table-column
        prop="employeeType"
        label="员工类型"
        width="120" align="left">
      </el-table-column>
      <el-table-column
        label="员工名称"
        width="100" align="left">
        <template slot-scope="scope"><span style="color: #409eff;cursor: pointer" @click="itemClick(scope.row)">{{ scope.row.name}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="deptId"
        label="部门"
        :formatter="formatDept"
        width="120" align="left">
      </el-table-column>
      <el-table-column
        prop="number"
        label="员工编号"
        width="120" align="left">
      </el-table-column>
      <el-table-column
        prop="position"
        label="岗位"
        width="100" align="left">
      </el-table-column>
      <el-table-column
        prop="entryStatus"
        label="入职状态"
        width="100" align="center">
      </el-table-column>
      <el-table-column
        prop="entryDate"
        label="入职时间"
        :formatter="dateFormat"
        width="100" align="left">
      </el-table-column>
      <el-table-column
        prop="expiration"
        label="合同到期"
        :formatter="dateFormat"
        width="100" align="left">
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
      <el-button type="danger" size="mini" style="margin: 0px;" v-show="this.employee!=null && this.employee.length>0"
                 :disabled="this.selItems!=null && this.selItems.length==0" @click="deleteMany">批量删除
      </el-button>
      <span></span>
      <el-pagination
        background
        :page-size="pageSize"
        layout="prev, pager, next"
        :total="totalCount" @current-change="currentChange" v-show="this.employee!=null &&this.employee.length>0">
      </el-pagination>
    </div>
  </div>
</template>

<script>
    import {putRequest} from '@/utils/api'
    import {getRequest} from '@/utils/api'
    import '@/assets/icon/iconfont.css'
    import moment from 'moment'
    //  import Vue from 'vue'
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
                dept: [],
                employee: [],
                success: false,
                dustbinData: []
            }
        },
        mounted: function () {
            var _this = this;
            this.loading = true;
            this.loadDept();
            console.log(this.dept);
            this.loadEmployee(1, this.pageSize);
            var _this = this;
            window.bus.$on('blogTableReload', function () {
                _this.loading = true;
                _this.loadEmployee(_this.currentPage, _this.pageSize);
            })
        },
        methods: {
            searchClick(){
                this.loadEmployee(1, this.pageSize);
            },
            add(){
                this.$router.push({path: '/addEmployee'})
            },
            itemClick(row){
                this.$router.push({path: '/addEmployee', query: {from: "query",id:row.id}})
            },
            deleteMany(){
                var selItems = this.selItems;
                for (var i = 0; i < selItems.length; i++) {
                    this.dustbinData.push(selItems[i].id)
                }
                this.delete()
            },
            //翻页
            currentChange(currentPage){
                this.currentPage = currentPage;
                this.loading = true;
                this.loadEmployee(currentPage, this.pageSize);
            },
            loadEmployee(page, count){
                var _this = this;
                var url = '';
                if (this.state == -1) {
                    url = "/employee/getAll" + "?page=" + page + "&count=" + count + "&keywords=" + this.keywords;
                }
                getRequest(url).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.success = true;
                        _this.employee = resp.data.employee;
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
            loadDept(){
                var _this = this;
                getRequest("/dept/all").then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.dept = resp.data;
                    } else {
                        _this.$message({type: 'error', message: '页面加载失败!'})
                    }
                }, resp=> {
                    _this.loading = false;
                    _this.$message({type: 'error', message: '页面加载失败!'})
                })
            },
            handleSelectionChange(val) {
                this.selItems = val;
            },
            handleEdit(index, row) {
                this.$router.push({path: '/addEmployee', query: {from: this.activeName, id: row.id}});
            },
            handleDelete(index, row) {
                this.dustbinData.push(row.id);
                this.delete();
            },
            delete(){
                var _this = this;
                this.$confirm('永久删除该员工, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    _this.loading = true;
                    var url = '';
                    if (_this.state == -1) {
                        url = "/employee/delete";
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
            formatDept(row){
                // switch(row.deptId){
                //     case this.dept[0].id:
                //         return this.dept[0].name;
                //         break;
                //     case this.dept[1].id:
                //         return this.dept[1].name;
                //         break;
                //     case this.dept[2].id:
                //         return this.dept[2].name;
                //         break;
                //     default:
                //         return 'unknow';
                // }
                for(var i=0; i<this.dept.length; i++){
                    if(row.deptId == this.dept[i].id){
                        return this.dept[i].name;
                    }
                }
                return "unknow";
            },
            dateFormat(row, column, cellValue, index){
                const daterc = row[column.property];
                if(daterc!=null){
                    var date = row[column.property];
                    if(date == undefined){return ''}
                    return moment(date).format("YYYY-MM-DD")
                }
            }
        },
        props: ['state', 'showEdit', 'showDelete', 'activeName', 'showRestore']
    }
</script>
