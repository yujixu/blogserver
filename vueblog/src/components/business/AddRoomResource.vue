<template>
  <el-dialog
    width="50%"
    :title="title"
    :visible.sync="dialogVisible">
    <el-form>
      <el-form-item>
        <span>园区：</span><el-select :disabled="readonly" v-model="parkId" @change="parkChange" style="width:400px">
          <el-option
            v-for="(item,index) in park"
            :key="index"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <span>楼栋：</span><el-select :disabled="readonly" v-model="buildingId" @change="buildingChange" style="width:400px">
        <el-option
          v-for="(item,index) in building"
          :key="index"
          :label="item.name"
          :value="item.id"
        ></el-option>
      </el-select>
      </el-form-item>
      <el-form-item>
        <span>楼层：</span><el-select :disabled="readonly" v-model="floorId" @change="floorChange" style="width:400px">
        <el-option
          v-for="(item,index) in floor"
          :key="index"
          :label="item.number"
          :value="item.id"
        ></el-option>
      </el-select>
      </el-form-item>
      <el-form-item>
        <span>房间：</span><el-select :disabled="readonly" v-model="roomId" style="width:400px">
        <el-option
          v-for="(item,index) in rooms"
          :key="index"
          :label="item.number"
          :value="item.id"
        ></el-option>
      </el-select>
      </el-form-item>
      <el-form-item>
        <span>房间介绍：</span><textarea class="rsrcTextarea" :readonly="readonly" v-model="room.introduce" style="margin-right: 25px"></textarea>
      </el-form-item>
      <el-form-item>
        <span>面积(㎡)：</span><el-input :readonly="readonly" type="text" v-model="room.area" auto-complete="off" placeholder="面积(㎡)" style="width: 400px; margin-right: 20px"></el-input>
      </el-form-item>
      <el-form-item>
        <span>房租(元/月)：</span><el-input :readonly="readonly||isHold" type="text" v-model="room.rent" auto-complete="off" placeholder="房租(元/月)" style="width: 400px; margin-right: 35px"></el-input>
      </el-form-item>
      <el-form-item>
        <span>装修：</span><el-select :disabled="readonly" v-model="room.renovation" style="width:400px">
        <el-option
          v-for="(item,index) in renovation"
          :key="index"
          :label="item.label"
          :value="item.label"
        ></el-option>
      </el-select>
      </el-form-item>
      <el-form-item>
        <span>电梯费：</span><el-input :readonly="readonly" type="text" v-model="room.elevatorFee" auto-complete="off" placeholder="电梯费" style="width: 400px; margin-right: 15px"></el-input>
      </el-form-item>
      <el-form-item>
        <span>物业费：</span><el-input :readonly="readonly" type="text" v-model="room.propertyFee" auto-complete="off" placeholder="物业费" style="width: 400px; margin-right: 15px"></el-input>
      </el-form-item>
      <el-form-item>
        <span>宽带费：</span><el-input :readonly="readonly" type="text" v-model="room.broadbandFee" auto-complete="off" placeholder="宽带费" style="width: 400px; margin-right: 15px"></el-input>
      </el-form-item>
      <el-form-item>
        <span>水费：</span><el-input :readonly="readonly" type="text" v-model="room.waterCharge" auto-complete="off" placeholder="水费" style="width: 400px"></el-input>
      </el-form-item>
      <el-form-item>
        <span>电费：</span><el-input :readonly="readonly" type="text" v-model="room.electricCharge" auto-complete="off" placeholder="电费" style="width: 400px"></el-input>
      </el-form-item>
      <el-form-item>
        <span>租赁状态：</span><el-select :disabled="readonly" @change="stateChange" v-model="room.state" style="width:400px; margin-right: 25px">
        <el-option
          v-for="(item,index) in states"
          :key="index"
          :label="item.label"
          :value="item.label"
        ></el-option>
      </el-select>
      </el-form-item>

    </el-form>

    <div style="display: flex;align-items: center;margin-top: 15px;justify-content: flex-end">
      <el-button type="primary" @click="cancel" v-if="!readonly">取消</el-button>
      <el-button type="primary" @click="cancel" v-if="readonly">确定</el-button>
      <el-button type="primary" @click="save" v-if="!readonly">保存</el-button>
    </div>
  </el-dialog>


</template>

<script>
    import {getRequest, postRequest, putRequest} from "../../utils/api";

    export default{
        data() {
            return {
                dialogVisible: false,
                room: [],
                readonly: false,
                park: [],
                title: "",
                renovation: [{
                    value: '1',
                    label: '毛胚'
                }, {
                    value: '2',
                    label: '精装'
                }, {
                    value: '3',
                    label: '简装'
                }],
                states: [{
                    value: '1',
                    label: '已租'
                }, {
                    value: '2',
                    label: '未租'
                }, {
                    value: '3',
                    label: '已售'
                }, {
                    value: '4',
                    label: '已定'
                }, {
                    value: '5',
                    label: '自留'
                }],
                parkId: "",
                building: [],
                buildingId: "",
                floor: [],
                floorId: "",
                rooms: [],
                roomId: "",
                isHold: false,
            }
        },
        methods: {
            cancel(){
                this.dialogVisible=false;
            },
            parkChange(){
                this.building = this.park.find(
                    item=>{
                        return item.id==this.parkId;
                    }
                ).list;
                this.buildingId = "";
                this.floorId = "";
                this.roomId = "";
            },
            buildingChange(){
                this.floor = this.building.find(
                    item=>{
                        return item.id==this.buildingId;
                    }
                ).list;
                this.floorId = "";
                this.roomId = "";
            },
            floorChange(){
                this.rooms = this.floor.find(
                    item=>{
                        return item.id==this.floorId;
                    }
                ).list;
                this.roomId = "";
            },
            save(){
                var _this = this;
                _this.dialogVisible=false;
                if(this.state == "add"){
                    postRequest("/roomRsrc/", {
                        roomId: _this.roomId,
                        area: _this.room.area,
                        rent: _this.room.rent,
                        renovation: _this.room.renovation,
                        state: _this.room.state,
                        propertyFee: _this.room.propertyFee,
                        broadbandFee: _this.room.broadbandFee,
                        elevatorFee: _this.room.elevatorFee,
                        electricCharge: _this.room.electricCharge,
                        waterCharge: _this.room.waterCharge,
                        introduce: _this.room.introduce
                    }).then(resp=> {
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '房源保存成功!'});
                            window.bus.$emit('blogTableReload')
                        }
                    }, resp=> {
                        _this.$message({type: 'error', message: '房源保存失败!'});
                    });
                }
                if(this.state == "update"){
                    putRequest("/roomRsrc/update", {
                        id: _this.id,
                        roomId: _this.roomId,
                        area: _this.room.area,
                        rent: _this.room.rent,
                        renovation: _this.room.renovation,
                        state: _this.room.state,
                        propertyFee: _this.room.propertyFee,
                        broadbandFee: _this.room.broadbandFee,
                        elevatorFee: _this.room.elevatorFee,
                        electricCharge: _this.room.electricCharge,
                        waterCharge: _this.room.waterCharge,
                        introduce: _this.room.introduce
                    }).then(resp=> {
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '房源编辑成功!'});
                            window.bus.$emit('blogTableReload')
                        }
                    }, resp=> {
                        _this.$message({type: 'error', message: '房源编辑失败!'});
                    });
                }
            },
            loadFloor(roomId){
                var _this = this;
                getRequest("/room/"+roomId).then(resp=> {
                    if (resp.status == 200) {
                        var room = resp.data;
                        _this.floorId = room.floorId;
                        _this.loadBuilding(_this.floorId);
                    } else {
                        _this.$message({type: 'error', message: '页面加载失败!'})
                    }
                }, resp=> {
                    _this.$message({type: 'error', message: '页面加载失败!'})
                });
            },
            loadBuilding(floorId){
                var _this = this;
                getRequest("/floor/"+floorId).then(resp=> {
                    if (resp.status == 200) {
                        var floor = resp.data;
                        _this.buildingId = floor.buildingId;
                        _this.loadPark(this.buildingId);
                    } else {
                        _this.$message({type: 'error', message: '页面加载失败!'})
                    }
                }, resp=> {
                    _this.$message({type: 'error', message: '页面加载失败!'})
                });
            },
            stateChange(){
                if(this.room.state == "自留"){
                    this.room.rent = 0;
                    this.isHold = true;
                }
                else{
                    this.isHold = false;
                }
            },
            loadPark(buildingId){
                var _this = this;
                getRequest("/building/"+buildingId).then(resp=> {
                    if (resp.status == 200) {
                        var building = resp.data;
                        _this.parkId = building.parkId;
                        _this.building = _this.park.find(
                            item=>{
                                return item.id==this.parkId;
                            }
                        ).list;
                        this.floor = this.building.find(
                            item=>{
                                return item.id==this.buildingId;
                            }
                        ).list;
                        this.rooms = this.floor.find(
                            item=>{
                                return item.id==this.floorId;
                            }
                        ).list;
                    } else {
                        _this.$message({type: 'error', message: '页面加载失败!'})
                    }
                }, resp=> {
                    _this.$message({type: 'error', message: '页面加载失败!'})
                });
            },
            init(){
                var _this = this;

                _this.dialogVisible = true;
                if(_this.state == "query"){
                    _this.readonly = true;
                    _this.title = "查看房源";
                }
                if(_this.state == "add"){
                    _this.readonly = false;
                    _this.title = "新增房源";
                }
                if(_this.state == "update"){
                    _this.readonly = false;
                    _this.title = "编辑房源";
                }
                _this.building = [];
                _this.floor = [];
                _this.park = [];
                _this.roomId = "";
                _this.parkId = "";
                _this.buildingId = "";
                _this.floorId = "";
                this.room = [];

                getRequest("/park/all").then(resp=> {
                    if (resp.status == 200) {
                        _this.park = resp.data;
                        if(_this.state!="add" && _this.id!=null){
                            getRequest("/roomRsrc/" + _this.id).then(resp=> {
                                if (resp.status == 200) {
                                    _this.room = resp.data;
                                    _this.roomId = _this.room.roomId;
                                    _this.loadFloor(_this.roomId);
                                } else {
                                    _this.$message({type: 'error', message: '页面加载失败!'})
                                }
                            }, resp=> {
                                _this.$message({type: 'error', message: '页面加载失败!'})
                            });
                        }
                    } else {
                        _this.$message({type: 'error', message: '页面加载失败!'})
                    }
                }, resp=> {
                    _this.$message({type: 'error', message: '页面加载失败!'})
                });

            },

        },
        props: ['id', 'state']
    }
</script>

<style>
  .rsrcTextarea {
    resize: vertical;
    padding: 5px 15px;
    vertical-align: top;
    line-height: 1.5;
    box-sizing: border-box;
    width: 400px;
    min-height: 100px;
    font-size: 14px;
    color: #5a5e66;
    background-color: #fff;
    background-image: none;
    border: 1px solid #d8dce5;
    border-radius: 4px;
    transition: border-color .2s cubic-bezier(.645,.045,.355,1);
    margin-bottom: 20px;
  }

</style>
