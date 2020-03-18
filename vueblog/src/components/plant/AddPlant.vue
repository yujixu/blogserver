<template>
  <el-dialog
    width="50%"
    :title="title"
    :visible.sync="dialogVisible">
    <el-form>
      <el-form-item>
        <span>区域：</span><el-select :disabled="readonly" v-model="plant.areaId" style="width:400px">
          <el-option
            v-for="(item,index) in area"
            :key="index"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <span>名称：</span><el-input :readonly="readonly" type="text" v-model="plant.name" auto-complete="off" placeholder="名称" style="width: 400px"></el-input>
      </el-form-item>
      <el-form-item>
        <span>种类：</span><el-select :disabled="readonly" v-model="plant.plantTypeId" style="width:400px">
        <el-option
          v-for="(item,index) in type"
          :key="index"
          :label="item.name"
          :value="item.id"
        ></el-option>
      </el-select>
      </el-form-item>
      <el-form-item>
        <span>树龄(年)：</span><el-input :readonly="readonly" type="text" v-model="plant.age" auto-complete="off" placeholder="树龄" style="width: 400px; margin-right: 20px"></el-input>
      </el-form-item>
      <el-form-item>
        <span>数量：</span><el-input :readonly="readonly" type="text" v-model="plant.number" auto-complete="off" placeholder="数量" style="width: 400px"></el-input>
      </el-form-item>
      <el-form-item>
        <span>单位：</span><el-select :disabled="readonly" v-model="plant.plantUnitId" style="width:400px">
        <el-option
          v-for="(item,index) in unit"
          :key="index"
          :label="item.name"
          :value="item.id"
        ></el-option>
      </el-select>
      </el-form-item>
      <el-form-item>
        <span>习性：</span><textarea class="textarea" :readonly="readonly" v-model="plant.habit"></textarea>
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
                plant: [],
                readonly: false,
                area: [],
                title: "",
                type: [],
                unit: []
            }
        },
        methods: {
            cancel(){
                this.dialogVisible=false;
            },
            save(){
                var _this = this;
                _this.dialogVisible=false;
                var url = "";
                if(this.isOut){
                    url = "/outPlant";
                }else{
                    url = "/inPlant";
                }
                if(this.state == "add"){
                    postRequest(url+"/", {
                        areaId: _this.plant.areaId,
                        number: _this.plant.number,
                        plantTypeId: _this.plant.plantTypeId,
                        name: _this.plant.name,
                        plantUnitId: _this.plant.plantUnitId,
                        age: _this.plant.age,
                        habit: _this.plant.habit
                    }).then(resp=> {
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '植物保存成功!'});
                            window.bus.$emit('blogTableReload')
                        }
                    }, resp=> {
                        _this.$message({type: 'error', message: '植物保存失败!'});
                    });
                }
                if(this.state == "update"){
                    putRequest(url+"/update", {
                        id: _this.id,
                        areaId: _this.plant.areaId,
                        number: _this.plant.number,
                        plantTypeId: _this.plant.plantTypeId,
                        name: _this.plant.name,
                        plantUnitId: _this.plant.plantUnitId,
                        age: _this.plant.age,
                        habit: _this.plant.habit
                    }).then(resp=> {
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '植物编辑成功!'});
                            window.bus.$emit('blogTableReload')
                        }
                    }, resp=> {
                        _this.$message({type: 'error', message: '植物编辑失败!'});
                    });
                }
            },
            init(){
                var _this = this;
                _this.dialogVisible = true;
                if(_this.state == "query"){
                    _this.readonly = true;
                    _this.title = "查看植物";
                }
                if(_this.state == "add"){
                    _this.readonly = false;
                    _this.title = "新增植物";
                }
                if(_this.state == "update"){
                    _this.readonly = false;
                    _this.title = "编辑植物";
                }
                _this.plant = [];

                var areaUrl = "";
                var plantUrl = "";
                if(this.isOut){
                    areaUrl = "/outArea/all";
                    plantUrl = "/outPlant/"
                }else{
                    areaUrl = "/inArea/all";
                    plantUrl = "/inPlant/"
                }
                if(_this.state!="add" && _this.id!=null){
                    getRequest(plantUrl + _this.id).then(resp=> {
                        if (resp.status == 200) {
                            _this.plant = resp.data;
                        } else {
                            _this.$message({type: 'error', message: '页面加载失败!'})
                        }
                    }, resp=> {
                        _this.$message({type: 'error', message: '页面加载失败!'})
                    });
                }

                getRequest(areaUrl).then(resp=> {
                    if (resp.status == 200) {
                        _this.area = resp.data;
                    } else {
                        _this.$message({type: 'error', message: '页面加载失败!'})
                    }
                }, resp=> {
                    _this.$message({type: 'error', message: '页面加载失败!'})
                });

                getRequest("/plantUnit/all").then(resp=> {
                    if (resp.status == 200) {
                        _this.unit = resp.data;
                    } else {
                        _this.$message({type: 'error', message: '页面加载失败!'})
                    }
                }, resp=> {
                    _this.$message({type: 'error', message: '页面加载失败!'})
                });
                getRequest("/plantType/all").then(resp=> {
                    if (resp.status == 200) {
                        _this.type = resp.data;
                    } else {
                        _this.$message({type: 'error', message: '页面加载失败!'})
                    }
                }, resp=> {
                    _this.$message({type: 'error', message: '页面加载失败!'})
                });
            },
        },
        props: ['id', 'state', 'isOut']
    }
</script>

<style>
  .textarea {
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
