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
      <el-button type="primary" icon="icon-zhyqadd" size="mini" style="margin-left: 3px;" @click="addPlant">新增</el-button>
    </div>
    <div style="display: flex;justify-content: flex-start">
      <span style="font-size:16px">区域：</span>
      <el-select v-model="keywords" style="width:180px" size="mini">
        <el-option
          v-for="item in area"
          :key="item.id"
          :label="item.name"
          :value="item.id"
        ></el-option>
      </el-select>
      <el-button type="primary" icon="el-icon-search" size="mini" style="margin-left: 3px" @click="search">搜索
      </el-button>

      <el-button type="primary" v-if="!isOut" size="mini" style="margin-left:600px" @click="searchOutClick">查看室外植物
      </el-button>
      <el-button type="primary" v-if="isOut" size="mini" style="margin-left:600px" @click="searchClick">查看室内植物
      </el-button>
    </div>
    <!--<div style="width: 100%;height: 1px;background-color: #20a0ff;margin-top: 8px;margin-bottom: 0px"></div>-->
    <add-plant v-if="showPlant" ref="addPlant" :state="states" :id="id" :isOut="isOut"></add-plant>
    <el-table
      ref="multipleTable"
      :data="plant"
      tooltip-effect="dark"
      style="width: 100%;overflow-x: hidden; overflow-y: hidden;"
      max-height="390"
      v-loading="loading"
      @selection-change="handleSelectionChange">
      <el-table-column
        type="selection"
        width="35" align="left" v-if="showEdit || showDelete">
      </el-table-column>
      <el-table-column
        label="序号"
        width="60" align="left">
        <template slot-scope="scope"><span style="color: #409eff;cursor: pointer" @click="itemClick(scope.row)">{{ scope.$index + 1}}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="areaName"
        label="区域"
        :width="width" align="center">
      </el-table-column>
      <el-table-column
        prop="name"
        label="名称"
        width="120" align="center">
      </el-table-column>
      <el-table-column
        prop="typeName"
        label="种类"
        width="80" align="left">
      </el-table-column>
      <el-table-column
        prop="age"
        label="树龄(年)"
        width="80" align="left">
      </el-table-column>
      <el-table-column
        prop="number"
        label="数量"
        width="80" align="left">
      </el-table-column>
      <el-table-column
        prop="unitName"
        label="单位"
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
            @click="handleDelete(scope.row)" v-if="showDelete">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="blog_table_footer">
      <el-button type="danger" size="mini" style="margin: 0px;" v-show="this.plant!=null && this.plant.length>0"
                 :disabled="this.selItems!=null && this.selItems.length==0" @click="deleteMany">批量删除
      </el-button>
      <span></span>
      <el-pagination
        background
        :page-size="pageSize"
        layout="prev, pager, next"
        :total="totalCount" @current-change="currentChange" v-show="this.plant!=null && this.plant.length>0">
      </el-pagination>
    </div>
  </div>
</template>

<script>
    import {putRequest} from '@/utils/api'
    import {getRequest} from '@/utils/api'
    import '@/assets/icon/iconfont.css'
    import addPlant from "./AddPlant";
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
                plant: [],
                success: false,
                isOut: true,
                id: "",
                states: "",
                area: [],
                showPlant: false,
                width: 80
            }
        },
        mounted: function () {
            var _this = this;
            this.loading = true;
            this.loadPlant(1, this.pageSize);
            window.bus.$on('blogTableReload', function () {
                _this.loading = true;
                _this.loadPlant(_this.currentPage, _this.pageSize);
            })
        },
        components: {
            addPlant,
        },
        methods: {
            search(){
                this.loadPlant(1, this.pageSize);
            },
            searchClick(){
                this.keywords = "";
                this.isOut=false;
                this.width = 150;
                this.loadPlant(1, this.pageSize);
            },
            searchOutClick(){
                this.keywords = "";
                this.isOut=true;
                this.width=80;
                this.loadPlant(1, this.pageSize);
            },
            //翻页
            currentChange(currentPage){
                this.currentPage = currentPage;
                this.loading = true;
                this.loadPlant(currentPage, this.pageSize);
            },
            addPlant(){
                this.states = "add";
                this.showPlant = true;
                this.$nextTick(() => {
                    this.$refs.addPlant.init()
                })
            },
            itemClick(row){
                this.states = "query";
                this.id = row.id;
                this.showPlant = true;
                this.$nextTick(() => {
                    this.$refs.addPlant.init()
                })
            },
            loadPlant(page, count){
                var _this = this;
                _this.loadData();

                var url = "";
                if(_this.isOut){
                    url = "/outPlant/getAll" + "?page=" + page + "&count=" + count + "&keywords=" + this.keywords;
                }
                else{
                    url = "/inPlant/getAll" + "?page=" + page + "&count=" + count + "&keywords=" + this.keywords;
                }
                getRequest(url).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.success = true;
                        if(_this.isOut){
                            _this.plant = resp.data.outPlant;
                        }else{
                            _this.plant = resp.data.inPlant;
                        }
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
            loadData(){
                var _this = this;
                var url = "";
                if(_this.isOut){
                    url = "/outArea/all";
                }else{
                    url = "/inArea/all";
                }
                getRequest(url).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.area = resp.data;
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
            handleEdit(row) {
                this.id = row.id;
                this.showPlant = true;
                this.states = "update";
                this.$nextTick(() => {
                    this.$refs.addPlant.init()
                })
            },
            deleteMany(){
                var selItems = this.selItems;
                for (var i = 0; i < selItems.length; i++) {
                    this.dustbinData.push(selItems[i].id)
                }
                this.deleteToDustBin()
            },
            handleDelete(row) {
                this.dustbinData.push(row.id);
                this.deleteToDustBin(row.state);
            },
            deleteToDustBin(){
                var _this = this;
                this.$confirm('永久删除该植物, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    _this.loading = true;
                    var url = "";
                    if(_this.isOut){
                        url = "/outPlant/delete";
                    }else{
                        url = "/inPlant/delete";
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
        },
        props: ['state', 'showEdit', 'showDelete', 'activeName', 'showRestore']
    }
</script>
