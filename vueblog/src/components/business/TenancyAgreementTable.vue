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
      <el-button type="primary" icon="icon-zhyqadd" size="mini" style="margin-left: 3px;" @click="addAgreement">新增</el-button>
    </div>
    <div style="display: flex;justify-content: flex-start">
      <span style="font-size:16px">合同状态：</span>
      <el-select v-model="keywords" style="width:180px" size="mini">
        <el-option
          v-for="item in agreementType"
          :key="item.id"
          :label="item.name"
          :value="item.id"
        ></el-option>
      </el-select>
      <span style="font-size:16px">状态：</span>
      <el-select v-model="keywords" style="width:180px" size="mini">
        <el-option
          v-for="item in state"
          :key="item.id"
          :label="item.name"
          :value="item.id"
        ></el-option>
      </el-select>
      <el-button type="primary" icon="el-icon-search" size="mini" style="margin-left: 3px" @click="search">搜索
      </el-button>

    </div>
    <!--<div style="width: 100%;height: 1px;background-color: #20a0ff;margin-top: 8px;margin-bottom: 0px"></div>-->
<!--    <add-Agreement v-if="showAgreement" ref="addAgreement" :id="id" :isOut="isOut"></add-Agreement>-->
    <el-table
      ref="multipleTable"
      :data="agreement"
      tooltip-effect="dark"
      style="width: 100%;overflow-x: hidden; overflow-y: hidden;"
      max-height="390"
      v-loading="loading">
      <el-table-column
        label="合同编号"
        width="60" align="left">
      </el-table-column>
      <el-table-column
        prop="companyName"
        label="公司名称"
        :width="80" align="center">
      </el-table-column>
      <el-table-column
        prop="startDate"
        label="生效时间"
        :formatter="dateFormat"
        width="120" align="center">
      </el-table-column>
      <el-table-column
        prop="endDate"
        label="到期时间"
        :formatter="dateFormat"
        width="80" align="left">
      </el-table-column>
      <el-table-column
        prop="agreementType"
        label="合同类型"
        width="80" align="left">
      </el-table-column>
      <el-table-column
        prop="state"
        label="状态"
        width="80" align="left">
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
            @click="itemClick(scope.row)" v-if="showDelete">查看
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
        :total="totalCount" @current-change="currentChange" v-show="this.agreement!=null && this.agreement.length>0">
      </el-pagination>
    </div>
  </div>
</template>

<script>
    import {putRequest} from '@/utils/api'
    import {getRequest} from '@/utils/api'
    import '@/assets/icon/iconfont.css'
    // import addAgreement from "./AddAgreement";
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
                agreement: [],
                success: false,
                state: [{
                    value: '1',
                    label: '待审核'
                }, {
                    value: '2',
                    label: '已审核'
                }, {
                    value: '3',
                    label: '未通过'
                }, {
                    value: '4',
                    label: '已作废'
                }, {
                    value: '5',
                    label: '已生效'
                }],
                id: "",
                agreementType: [],
                showAgreement: false,
            }
        },
        mounted: function () {
            var _this = this;
            this.loading = true;
            this.loadAgreement(1, this.pageSize);
            window.bus.$on('blogTableReload', function () {
                _this.loading = true;
                _this.loadAgreement(_this.currentPage, _this.pageSize);
            })
        },
        components: {
            // addAgreement,
        },
        methods: {
            search(){
                this.loadAgreement(1, this.pageSize);
            },
            //翻页
            currentChange(currentPage){
                this.currentPage = currentPage;
                this.loading = true;
                this.loadAgreement(currentPage, this.pageSize);
            },
            addAgreement(){
                this.$router.push({path: '/addAgreement'})
            },
            itemClick(row){
                this.id = row.id;
                this.showAgreement = true;
                this.$nextTick(() => {
                    this.$refs.addAgreement.init()
                })
            },
            loadAgreement(page, count){
                var _this = this;
                var url = "/agreement/getAll" + "?page=" + page + "&count=" + count + "&keywords=" + this.keywords;
                getRequest(url).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.success = true;
                        _this.agreement = resp.data.agreement;
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
                if(row.state !="待审核"){
                    this.$message({type: 'info', message: '只有待审核状态能编辑'});
                }
                else if(row.state == "待审核"){
                    this.$router.push({path: '/addAgreement', query: {from: "update", id: row.id}});
                }
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
