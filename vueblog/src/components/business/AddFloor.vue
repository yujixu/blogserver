<template>
  <el-dialog
    width="30%"
    :title="title"
    :visible.sync="dialogVisible">

    <el-form>
      <el-form-item>
        <span>楼栋：</span><span>{{this.building.name}}</span>
      </el-form-item>
      <el-form-item>
        <span>楼层：</span><el-input :readonly="readonly" type="text" v-model="floor.number" auto-complete="off" placeholder="名称" style="width: 150px"></el-input>
      </el-form-item>
      <el-form-item>
        <span>备注：</span><textarea class="textarea" :readonly="readonly" v-model="floor.explain"></textarea>
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
                floor: [],
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
                    postRequest("/floor/", {
                        explain: _this.floor.explain,
                        number: _this.floor.number,
                        buildingId: _this.building.id
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
                    putRequest("/floor/update", {
                        id: _this.id,
                        explain: _this.floor.explain,
                        number: _this.floor.number,
                        building: _this.floor.buildingId
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
            init(){
                var _this = this;
                _this.dialogVisible = true;
                if(_this.state == "query"){
                    _this.readonly = true;
                    _this.title = "查看楼层";
                }
                if(_this.state == "add"){
                    _this.readonly = false;
                    _this.title = "新增楼层";
                }
                if(_this.state == "update"){
                    _this.readonly = false;
                    _this.title = "修改楼层";
                }

                _this.floor = [];
                if(_this.state!="add"){
                    getRequest("/floor/" + this.id).then(resp=> {
                        if (resp.status == 200) {
                            _this.floor = resp.data;
                        } else {
                            _this.$message({type: 'error', message: '页面加载失败!'})
                        }
                    }, resp=> {
                        _this.$message({type: 'error', message: '页面加载失败!'})
                    });
                }
                getRequest("/building/" + _this.id).then(resp=> {
                    if (resp.status == 200) {
                        _this.building = resp.data;
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
