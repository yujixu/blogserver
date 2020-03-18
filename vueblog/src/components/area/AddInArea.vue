<template>
  <el-dialog
    width="30%"
    :title="title"
    :visible.sync="dialogVisible">
    <el-form>
      <el-form-item>
        <span style="margin-left: 25px">园区：</span><el-select @change="parkChange" :disabled="readonly" v-model="inArea.parkId" style="width:150px">
        <el-option
          v-for="(item,index) in park"
          :key="index"
          :label="item.name"
          :value="item.id"
        ></el-option>
      </el-select>
      </el-form-item>
      <el-form-item>
        <span style="margin-left: 25px">楼栋：</span><el-select @change="buildingChange" :disabled="readonly" v-model="inArea.buildingId" style="width:150px">
        <el-option
          v-for="(item,index) in building"
          :key="index"
          :label="item.name"
          :value="item.id"
        ></el-option>
      </el-select>
      </el-form-item>
      <el-form-item>
        <span style="margin-left: 25px">楼层：</span>
        <el-select :disabled="readonly" @change="setFloorId" v-model="floorId" style="width:150px">
          <el-option
            v-for="(item,index) in floor"
            :key="index"
            :label="item.number"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <span>区域名称：</span><el-input :readonly="readonly" type="text" v-model="inArea.name" auto-complete="off" placeholder="名称" style="width: 150px"></el-input>
      </el-form-item>
      <el-form-item>
        <span style="margin-left: 30px">备注：</span><textarea class="textarea" :readonly="readonly" v-model="inArea.explain"></textarea>
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
                inArea: [],
                readonly: false,
                park: [],
                title: "",
                building: [],
                floor: [],
                floorId: "",
                buildingName: "",
                parkName: ""
            }
        },
        methods: {
            cancel(){
                this.dialogVisible=false;
            },
            save(){
                var _this = this;
                _this.dialogVisible=false;
                if(this.state == "add"){
                    postRequest("/inArea/", {
                        explain: _this.inArea.explain,
                        number: _this.inArea.number,
                        parkId: _this.inArea.parkId,
                        name: _this.inArea.name,
                        floorId: _this.inArea.floorId,
                        buildingId: _this.inArea.buildingId
                    }).then(resp=> {
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '房间保存成功!'});
                            window.bus.$emit('blogTableReload')
                        }
                    }, resp=> {
                        _this.$message({type: 'error', message: '房间保存失败!'});
                    });
                }
                if(this.state == "update"){
                    putRequest("/inArea/update", {
                        id: _this.id,
                        explain: _this.inArea.explain,
                        number: _this.inArea.number,
                        parkId: _this.inArea.parkId,
                        name: _this.inArea.name,
                        floorId: _this.inArea.floorId,
                        buildingId: _this.inArea.buildingId
                    }).then(resp=> {
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '房间修改成功!'});
                            window.bus.$emit('blogTableReload')
                        }
                    }, resp=> {
                        _this.$message({type: 'error', message: '房间修改失败!'});
                    });
                }
            },
            buildingChange(){
                this.inArea.floorId = "";
                this.floorId = "";
                this.inArea.name = "";
                this.inArea.explain = "";
                this.floor = this.building.find(
                    item=>{
                        return item.id==this.inArea.buildingId;
                    }
                ).list;
                this.buildingName = this.building.find(
                    item=>{
                        return item.id==this.inArea.buildingId;
                    }
                ).name;
            },
            setFloorId(){
                this.inArea.floorId = this.floorId;
                var floor = this.floor.find(
                    item=>{
                        return item.id==this.inArea.floorId;
                    }
                ).number;
                this.inArea.name = this.parkName+"-"+this.buildingName+"-"+floor;
                this.inArea.explain = this.parkName+"-"+this.buildingName+"-"+floor
            },
            parkChange(){
                this.floor = [];
                this.inArea.buildingId = "";
                this.inArea.floorId = "";
                this.floorId = "";
                this.inArea.name = "";
                this.inArea.explain = "";
                this.building = this.park.find(
                    item=>{
                        return item.id==this.inArea.parkId;
                    }
                ).list;
                this.parkName = this.park.find(
                    item=>{
                        return item.id==this.inArea.parkId;
                    }
                ).name;
            },
            init(){
                var _this = this;
                _this.dialogVisible = true;
                if(_this.state == "query"){
                    _this.readonly = true;
                    _this.title = "查看区域";
                }
                if(_this.state == "add"){
                    _this.readonly = false;
                    _this.title = "新增区域";
                }
                if(_this.state == "update"){
                    _this.readonly = false;
                    _this.title = "修改区域";
                }
                _this.inArea = [];
                _this.floorId = "";
                if(_this.state!="add" && _this.id!=null){
                    getRequest("/inArea/" + _this.id).then(resp=> {
                        if (resp.status == 200) {
                            _this.inArea = resp.data;
                            _this.floorId = _this.inArea.floorId;
                        } else {
                            _this.$message({type: 'error', message: '页面加载失败!'})
                        }
                    }, resp=> {
                        _this.$message({type: 'error', message: '页面加载失败!'})
                    });
                }
                getRequest("/park/all").then(resp=> {
                    if (resp.status == 200) {
                        _this.park = resp.data;
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
  .textarea {
    resize: vertical;
    padding: 5px 15px;
    vertical-align: top;
    line-height: 1.5;
    box-sizing: border-box;
    width: 150px;
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
