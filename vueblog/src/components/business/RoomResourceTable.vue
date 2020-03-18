<style type="text/css">
  .blog_table_footer {
    display: flex;
    box-sizing: content-box;
    padding-top: 10px;
    padding-bottom: 0px;
    margin-bottom: 0px;
    justify-content: space-between;
  }

  .title {
    margin: 0px auto 40px auto;
    text-align: left;
    color: #505458;
  }

  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }

  .grid-content {
    border-radius: 5px;
    min-height: 70px;
    width: 110px;
    text-align:center;
    line-height: 70px;
    color: white;
    font-size: 20px;
  }

  .block-background-red{
    background: #FD5050;
  }

  .block-background-blue{
    background: #8689FF;
  }

  .block-background-green{
    background: #62AE63;
  }

  .block-background-purple{
    background: #BD6DFF;
  }

  .block-background-gray{
    background: #9C9C9E;
  }

  .small-block{
    border-radius: 3px;
    height: 25px;
    width: 40px;
    display:inline-block;
    vertical-align: middle;
  }

</style>

<template>
  <div>
    <h3 class="title" style="margin-top: 3%">楼宇房源信息</h3>
    <div style="display: flex;justify-content: flex-start;margin-bottom: 10px">
      <el-button type="primary" icon="icon-zhyqadd" size="mini" style="margin-left: 3px;" @click="addRoomRsrc">新增</el-button>
      <div v-if="isList">
        <el-input
          placeholder="通过房间号搜索"
          prefix-icon="el-icon-search"
          v-model="keywords"
          style="width: 400px; margin-left: 150px"
          size="mini">
        </el-input>
        <el-button type="primary" icon="el-icon-search" size="mini" style="margin-left: 3px" @click="search">搜索
        </el-button>
      </div>
      <div v-if="!isList" style="margin-left: 200px">
        <div class="small-block block-background-red"></div><span class="title" style="margin-right: 10px">未租</span>
        <div class="small-block block-background-blue"></div><span class="title" style="margin-right: 10px">已租</span>
        <div class="small-block block-background-green"></div><span class="title" style="margin-right: 10px">已订</span>
        <div class="small-block block-background-purple"></div><span class="title" style="margin-right: 10px">自留</span>
        <div class="small-block block-background-gray"></div><span class="title" style="margin-right: 10px">已售</span>
      </div>
    </div>
    <add-room v-if="showRoom" ref="addRoom" :state="states" :id="id"></add-room>

    <div style="display: flex;justify-content: flex-start">
      <span style="font-size:15px; color: #505458; margin-left: 200px">园区：</span>
      <el-select v-model="parkId" @change="parkChange" style="width:100px" size="mini">
        <el-option
          v-for="item in park"
          :key="item.id"
          :label="item.name"
          :value="item.id"
        ></el-option>
      </el-select>

      <span style="font-size:15px; color: #505458; margin-left: 20px">楼栋：</span>
      <el-select v-model="buildingId" @change="buildingChange" style="width:120px" size="mini">
        <el-option
          v-for="item in building"
          :key="item.id"
          :label="item.name"
          :value="item.id"
        ></el-option>
      </el-select>

      <span style="font-size:15px; color: #505458; margin-left: 20px">楼层：</span>
      <el-select v-model="floorId" @change="floorChange" style="width:90px" size="mini">
        <el-option
          v-for="item in floor"
          :key="item.id"
          :label="item.name"
          :value="item.id"
        ></el-option>
      </el-select>
      <el-button size="mini" @click="clear">重置
      </el-button>
      <el-button type="primary" v-if="isList" size="mini" style="margin-left:200px" @click="searchClick">切换视图模式
      </el-button>
      <el-button type="primary" v-if="!isList" size="mini" style="margin-left:200px" @click="searchListClick">切换列表模式
      </el-button>
    </div>

    <div v-if="isList">
      <el-table
        ref="multipleTable"
        :data="room"
        tooltip-effect="dark"
        style="width: 100%;overflow-x: hidden; overflow-y: hidden;"
        max-height="390"
        v-loading="loading"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          width="35" align="left">
        </el-table-column>
        <el-table-column
          label="序号"
          width="60" align="left">
          <template slot-scope="scope"><span style="color: #409eff;cursor: pointer" @click="itemClick(scope.row)">{{ scope.$index + 1}}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="parkName"
          label="园区"
          width="80" align="left">
        </el-table-column>
        <el-table-column
          prop="buildingName"
          label="楼栋"
          width="80" align="left">
        </el-table-column>
        <el-table-column
          prop="roomNumber"
          label="房间号"
          width="80" align="left">
        </el-table-column>
        <el-table-column
          prop="area"
          label="面积(㎡)"
          width="80" align="left">
        </el-table-column>
        <el-table-column
          prop="rent"
          label="房租(元/月)"
          width="120" align="left">
        </el-table-column>
        <el-table-column
          prop="renovation"
          label="装修"
          width="80" align="left">
        </el-table-column>
        <el-table-column
          prop="state"
          label="租赁状态"
          width="80" align="left">
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button
              size="mini"
              @click="handleEdit(scope.row)">编辑
            </el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(scope.row)">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="blog_table_footer">
        <el-button type="danger" size="mini" style="margin: 0px;" v-show="this.room!=null && this.room.length>0"
                   :disabled="this.selItems!=null && this.selItems.length==0" @click="deleteMany">批量删除
        </el-button>
        <span></span>
        <el-pagination
          background
          :page-size="pageSize"
          layout="prev, pager, next"
          :total="totalCount" @current-change="currentChange" v-show="this.room!=null && this.room.length>0">
        </el-pagination>
      </div>
    </div>

    <el-table
      v-if="!isList"
      :data="floorList"
      style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-row :gutter="20" justify=start>
              <el-col :span="3" v-for="row in props.row.list" :key="row.id">
                <el-popover
                  placement="right"
                  width="150"
                  trigger="hover">
                  <div style="border: 1px solid #795da3;box-shadow:0px 0px  10px 5px #aaa;">
                    <p style="margin-left: 10px">状态:{{row.state}}</p>
                    <p style="margin-left: 10px">房源面积:{{row.area+"㎡"}}</p>
                    <p style="margin-left: 10px">房租:{{row.rent+"元"}}</p>
                    <p style="margin-left: 10px">物业费:{{row.propertyFee+"元"}}</p>
                    <p style="margin-left: 10px">水费:{{row.waterCharge+"元"}}</p>
                    <p style="margin-left: 10px">电费:{{row.electricCharge+"元"}}</p>
                    <p style="margin-left: 10px">装修:{{row.renovation}}</p>
                  </div>
                  <div slot="reference" class="grid-content" :class="{'block-background-red': row.state=='未租',
                                                                      'block-background-blue': row.state=='已租',
                                                                      'block-background-green': row.state=='已订',
                                                                      'block-background-purple': row.state=='自留',
                                                                      'block-background-gray': row.state=='已售', }">
                  {{row.roomNumber}}</div>
                </el-popover>
              </el-col>
          </el-row>
        </template>
      </el-table-column>
      <el-table-column
        prop="number">
        <template slot-scope="scope"><span>{{ scope.row.number + "层"}}</span>
        </template>
      </el-table-column>
    </el-table>

  </div>
</template>

<script>
    import {putRequest} from '@/utils/api'
    import {getRequest} from '@/utils/api'
    import '@/assets/icon/iconfont.css'
    import addRoom from "./AddRoomResource";
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
                parkId: "",
                buildingId: "",
                floorId: "",
                dustbinData: [],
                room: [],
                success: false,
                isList: true,
                park: [],
                building: [],
                floor: [],
                showRoom: false,
                states: "",
                id: "",
                floorList: [],
            }
        },
        mounted: function () {
            var _this = this;
            _this.loading = true;
            _this.loadRoomRsrc(1, _this.pageSize);
            _this.loadPark();
            window.bus.$on('blogTableReload', function () {
                _this.loading = true;
                _this.loadRoomRsrc(_this.currentPage, _this.pageSize);
            })
        },
        components: {
            addRoom,
        },
        methods: {
            search(){
                this.loadRoomRsrc(1, this.pageSize);
            },
            clear(){
                this.keywords = "";
                this.parkId = "";
                this.buildingId = "";
                this.floorId = "";
                this.park = [];
                this.building = [];
                this.floor = [];
                this.loadPark();
                if(this.isList){
                    this.loadRoomRsrc(1, this.pageSize);
                }else{
                    this.floorList = [];
                }
            },
            searchClick(){
                this.isList=false;
                this.clear();
            },
            searchListClick(){
                this.isList=true;
                this.clear();
                this.loadRoomRsrc(1, this.pageSize);
            },
            parkChange(){
                this.building = this.park.find(
                    item=>{
                        return item.id==this.parkId;
                    }
                ).list;
                this.buildingId = "";
                this.floorId = "";
                this.floor = [];
                if(this.isList){
                    this.loadRoomRsrc(1, this.pageSize);
                }
            },
            buildingChange(){
                this.floor = this.building.find(
                    item=>{
                        return item.id==this.buildingId;
                    }
                ).list;
                if(!this.isList){
                    this.floorList = this.floor;
                }
                this.floorId = "";
                if(this.isList){
                    this.loadRoomRsrc(1, this.pageSize);
                }

            },
            floorChange(){
                if(this.isList){
                    this.loadRoomRsrc(1, this.pageSize);
                }else{
                    var temp = this.floor.find(
                        item=>{
                            return item.id==this.floorId;
                        }
                    );
                    this.floorList = [];
                    this.floorList.push(temp);
                }
            },
            //翻页
            currentChange(currentPage){
                this.currentPage = currentPage;
                this.loading = true;
                this.loadRoomRsrc(currentPage, this.pageSize);
            },
            addRoomRsrc(){
                this.states = "add";
                this.showRoom = true;
                this.$nextTick(() => {
                    this.$refs.addRoom.init()
                })
            },
            itemClick(row){
                this.states = "query";
                this.showRoom = true;
                this.id = row.id;
                this.$nextTick(() => {
                    this.$refs.addRoom.init()
                })
            },
            loadRoomRsrc(page, count){
                var _this = this;
                var url = "/roomRsrc/getAll" + "?page=" + page + "&count=" + count + "&parkId=" + this.parkId +
                          "&buildingId=" + this.buildingId + "&floorId=" + this.floorId + "&keywords=" + this.keywords;
                getRequest(url).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.success = true;
                        _this.room = resp.data.roomRsrc;
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
            loadPark(){
                var _this = this;
                if(_this.isList){
                    var url = "/park/all";
                }else{
                    var url = "/park/getRsrcAll";
                }
                getRequest(url).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.park = resp.data;
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
                this.states = "update";
                this.showRoom = true;
                this.id = row.id;
                this.$nextTick(() => {
                    this.$refs.addRoom.init()
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
                this.deleteToDustBin();
            },
            deleteToDustBin(){
                var _this = this;
                this.$confirm('永久删除该房源, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    _this.loading = true;
                    var url = "/roomRsrc/delete";
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
        props: ['showEdit', 'showDelete', 'activeName', 'showRestore']
    }
</script>
