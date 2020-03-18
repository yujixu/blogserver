<template>
  <div>
    <div style="justify-content: flex-start; margin-top: 1%">
      <el-input
        placeholder="通过名称搜索"
        prefix-icon="el-icon-search"
        v-model="keywords" style="width: 400px" size="mini">
      </el-input>
      <el-button type="primary" icon="el-icon-search" size="mini" style="margin-left: 3px" @click="loadPark">搜索
      </el-button>
    </div>
    <add-room v-if="showRoom" ref="addRoom" :id="id" :state="this.state"></add-room>
    <add-floor v-if="showFloor" ref="addFloor" :id="id" :state="this.state"></add-floor>
    <add-building v-if="showBuilding" ref="addBuilding" :id="id" :state="this.state"></add-building>
    <vxe-table
      row-keys
      ref="xTree3"
      border="none"
      style="margin-left: 25%; margin-top: 1%;width: 60%;"
      :show-header="false"
      :data="park"
      :checkbox-config="{labelField: 'name'}"
      :tree-config="{children: 'list', accordion: true, line: true, iconOpen: 'fa fa-minus-square-o', iconClose: 'fa fa-plus-square-o'}">
      <!--    :tree-config="{children: 'children', accordion: true, line: true}">-->
      <vxe-table-column tree-node>
        <template v-slot="{ row }">
          <span v-if="row.name!=null">{{ row.name }}</span>
          <span v-if="row.name==null && row.floorId==null">{{row.number}}层</span>
          <span v-if="row.name==null && row.floorId!=null">{{row.number}}</span>
        </template>
      </vxe-table-column>
      <vxe-table-column title="操作" width="200px">
        <template v-slot="{ row }">
          <vxe-button type="text" icon="fa fa-eye" v-if="row.floorId!=null || row.buildingId!=null || row.parkId!=null" @click.native="query(row)"></vxe-button>
          <vxe-button type="text" icon="fa fa-plus-circle" v-if="row.floorId==null" @click.native="add(row)"></vxe-button>
          <vxe-button type="text" icon="fa fa-edit" v-if="row.floorId!=null || row.buildingId!=null || row.parkId!=null" @click.native="update(row)"></vxe-button>
          <vxe-button type="text" icon="fa fa-trash-o" v-if="row.floorId!=null || row.buildingId!=null || row.parkId!=null" @click="removeRowEvent(row)"></vxe-button>
        </template>
      </vxe-table-column>
    </vxe-table>
  </div>

</template>
<script>
    import Vue from 'vue'
    import 'xe-utils'
    import VXETable from 'vxe-table'
    import 'vxe-table/lib/index.css'
    import {getRequest, putRequest} from "../../utils/api";
    import addRoom from "./AddRoom"
    import addFloor from "./AddFloor"
    import addBuilding from "./AddBuilding"
    Vue.use(VXETable)

    export default {
        data () {
            return {
                keywords: "",
                park: [],
                showRoom: false,
                showFloor: false,
                showBuilding: false,
                showPark: false,
                id: "",
                state: ""
            }
        },
        mounted: function () {
            var _this = this;
            this.loading = true;
            this.loadPark();
            window.bus.$on('blogTableReload', function () {
                _this.loading = true;
                _this.loadPark();
            })
        },
        components: {
          addRoom,
          addFloor,
          addBuilding
        },
        methods: {
            removeRowEvent (row) {
                var _this = this;
                this.$confirm('永久删除, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    _this.loading = true;
                    if(row.floorId!=null){
                        var url = "/room/delete/" + row.id;
                    }
                    if(row.buildingId!=null){
                        var url = "/floor/delete/" + row.id;
                    }
                    if(row.parkId!=null){
                        var url = "/building/delete/" + row.id;
                    }
                    putRequest(url).then(resp=> {
                        if (resp.status == 200) {
                            var data = resp.data;
                            _this.$message({type: data.status, message: data.msg});
                            if (data.status == 'success') {
                                window.bus.$emit('blogTableReload')//通过选项卡都重新加载数据
                            }
                        } else {
                            _this.$message({type: 'error', message: '删除失败!'});
                        }
                    }, resp=> {
                        _this.$message({type: 'error', message: '删除失败!'});
                    });
                }).catch(() => {
                    _this.$message({type: 'info',message: '已取消删除'});
                });
            },
            loadPark(){
                var _this = this;
                var url = '';
                url = "/park/getAll" + "?keywords=" + this.keywords;
                getRequest(url).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.success = true;
                        _this.park = resp.data;
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
            created () {
                // this.tableData = window.MOCK_TREE_DATA_LIST.slice(0)
            },
            query (row){
                this.state = "query";
                this.beforeDo(row);
            },
            add (row){
                this.state = "add";
                this.beforeDo(row);
            },
            update (row){
                this.state = "update";
                this.beforeDo(row);
            },
            beforeDo(row){
                this.id = row.id;
                if(row.buildingId!=null && this.state=="add" || row.floorId!=null){
                    this.showRoom = true;
                    this.$nextTick(() => {
                        this.$refs.addRoom.init()
                    })
                }
                else if(row.parkId!=null && this.state=="add" ||row.buildingId!=null){
                    this.showFloor = true;
                    this.$nextTick(() => {
                        this.$refs.addFloor.init()
                    })
                }
                else if(this.state == "add" || row.parkId!=null){
                    this.showBuilding = true;
                    this.$nextTick(() => {
                        this.$refs.addBuilding.init()
                    })
                }
            }
        }
    }
</script>

<style>
  .tree-node-icon {
    width: 24px;
    text-align: center;
  }
</style>
