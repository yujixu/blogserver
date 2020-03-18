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
    <div style="display: flex;justify-content: flex-start;margin-bottom: 10px; margin-top: 1%">
      <el-button type="primary" icon="icon-zhyqadd" size="mini" style="margin-left: 3px;" @click="addCompany">新增</el-button>
    </div>
    <div style="display: flex;justify-content: flex-start">
      <el-input
        placeholder="通过名称搜索"
        prefix-icon="el-icon-search"
        v-model="keywords" style="width: 400px" size="mini">
      </el-input>
      <el-button type="primary" icon="el-icon-search" size="mini" style="margin-left: 3px" @click="search">搜索
      </el-button>
    </div>
    <!--<div style="width: 100%;height: 1px;background-color: #20a0ff;margin-top: 8px;margin-bottom: 0px"></div>-->
    <query-company v-if="showCompany" ref="queryCompany" :id="id"></query-company>
    <el-table
      ref="multipleTable"
      :data="company"
      tooltip-effect="dark"
      style="width: 100%;overflow-x: hidden; overflow-y: hidden;"
      max-height="390"
      v-loading="loading">
      <el-table-column
        prop="conumber"
        label="企业编号"
        width="100" align="left">
      </el-table-column>
      <el-table-column
        prop="coname"
        label="公司名称"
        :width="200" align="center">
      </el-table-column>
      <el-table-column
        prop="address"
        label="地址"
        width="250" align="center">
      </el-table-column>
      <el-table-column
        prop="contact"
        label="联系人"
        width="80" align="left">
      </el-table-column>
      <el-table-column
        prop="contactPhone"
        label="电话"
        width="150" align="center">
      </el-table-column>
      <el-table-column
        prop="establishTime"
        label="成立时间"
        :formatter="dateFormat"
        width="120" align="left">
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.row)">编辑
          </el-button>
          <el-button
            size="mini"
            type="primary"
            @click="itemClick(scope.row)">查看
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
        :total="totalCount" @current-change="currentChange" v-show="this.company!=null && this.company.length>0">
      </el-pagination>
    </div>
  </div>
</template>

<script>
    import {putRequest} from '@/utils/api'
    import {getRequest} from '@/utils/api'
    import '@/assets/icon/iconfont.css'
    import queryCompany from "./queryCompany";
    import Vue from 'vue'
    import {postRequest, postRequestJson} from "../../utils/api";
    import moment from "moment";
    //  var bus = new Vue()

    export default{
        data() {
            return {
                loading: false,
                currentPage: 1,
                totalCount: -1,
                pageSize: 6,
                keywords: '',
                dustbinData: [],
                company: [],
                success: false,
                id: "",
                showCompany: false,
            }
        },
        mounted: function () {
            var _this = this;
            this.loading = true;
            this.loadCompany(1, this.pageSize);
            window.bus.$on('blogTableReload', function () {
                _this.loading = true;
                _this.loadCompany(_this.currentPage, _this.pageSize);
            })
        },
        components: {
            queryCompany,
        },
        methods: {
            search(){
                this.loadCompany(1, this.pageSize);
            },
            //翻页
            currentChange(currentPage){
                this.currentPage = currentPage;
                this.loading = true;
                this.loadCompany(currentPage, this.pageSize);
            },
            addCompany(){
                this.$router.push({path: '/addCompany'})
            },
            itemClick(row){
                this.id = row.id;
                this.showCompany = true;
                this.$nextTick(() => {
                    this.$refs.queryCompany.init()
                })
            },
            loadCompany(page, count){
                var _this = this;
                var url = "/company/getAll" + "?page=" + page + "&count=" + count + "&keywords=" + this.keywords;
                getRequest(url).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.success = true;
                        _this.company = resp.data.company;
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
            handleEdit(row) {
                this.$router.push({path: '/addCompany', query: {from: "update", id: row.id}});
            },
            dateFormat(row, column, cellValue, index){
                const daterc = row[column.property];
                if(daterc!=null){
                    var date = row[column.property];
                    if(date == undefined){return ''}
                    return moment(date).format("YYYY-MM-DD")
                }
            },
        },
        props: ['state', 'showEdit', 'showDelete', 'activeName', 'showRestore']
    }
</script>
