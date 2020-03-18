<template>
  <el-dialog
    width="30%"
    :title="title"
    :visible.sync="dialogVisible">

    <el-form>
      <el-form-item>
        <span>园区：</span><span>{{this.park.name}}</span>
      </el-form-item>
      <el-form-item>
        <span>楼栋名称：</span><el-input :readonly="readonly" type="text" v-model="building.name" auto-complete="off" placeholder="名称" style="width: 150px"></el-input>
      </el-form-item>
      <el-form-item>
        <span>楼栋编号：</span><el-input :readonly="readonly" type="text" v-model="building.number" auto-complete="off" placeholder="编号" style="width: 150px"></el-input>
      </el-form-item>
      <el-form-item>
        <span style="margin-left: 15px">总楼层：</span><el-input :readonly="readonly" type="text" v-model="building.floor" auto-complete="off" placeholder="总楼层" style="width: 150px"></el-input>
      </el-form-item>
      <el-form-item>
        <span style="margin-left: 30px">备注：</span><textarea class="textarea" :readonly="readonly" v-model="building.explain"></textarea>
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
    import moment from "moment";

    export default{
        data() {
            return {
                dialogVisible: false,
                park: [],
                readonly: false,
                building: [],
                title: "",
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
                    postRequest("/building/", {
                        explain: _this.building.explain,
                        name: _this.building.name,
                        number: _this.building.number,
                        floor: _this.building.floor,
                        parkId: _this.park.id
                    }).then(resp=> {
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '楼栋保存成功!'});
                            window.bus.$emit('blogTableReload')
                        }
                    }, resp=> {
                        _this.$message({type: 'error', message: '楼栋保存失败!'});
                    });
                }
                if(this.state == "update"){
                    putRequest("/building/update", {
                        id: _this.id,
                        explain: _this.building.explain,
                        name: _this.building.name,
                        number: _this.building.number,
                        floor: _this.building.floor,
                        parkId: _this.park.id
                    }).then(resp=> {
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '楼栋修改成功!'});
                            window.bus.$emit('blogTableReload')
                        }
                    }, resp=> {
                        _this.$message({type: 'error', message: '楼栋修改失败!'});
                    });
                }
            },
            init(){
                var _this = this;
                _this.dialogVisible = true;
                if(_this.state == "query"){
                    _this.readonly = true;
                    _this.title = "查看楼栋";
                }
                if(_this.state == "add"){
                    _this.readonly = false;
                    _this.title = "新增楼栋";
                }
                if(_this.state == "update"){
                    _this.readonly = false;
                    _this.title = "修改楼栋";
                }
                _this.building = [];
                if(_this.state!="add"){
                    getRequest("/building/" + this.id).then(resp=> {
                        if (resp.status == 200) {
                            _this.building = resp.data;
                        } else {
                            _this.$message({type: 'error', message: '页面加载失败!'})
                        }
                    }, resp=> {
                        _this.$message({type: 'error', message: '页面加载失败!'})
                    });
                }
                getRequest("/park/" + _this.id).then(resp=> {
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
