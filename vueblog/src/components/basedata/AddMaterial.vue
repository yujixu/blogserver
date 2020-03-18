<template>
  <el-container v-loading="loading">
    <el-main class="main">
      <update-base-unit v-if="showBaseUnit" ref="updateBaseUnit" @selected="setBaseUnit"></update-base-unit>
      <update-material-type v-if="showMaterialType" ref="updateMaterialType" @selected="setMaterialType"></update-material-type>

      <el-form v-loading="loading">
        <h3 class="title" v-if="from!='material' && from!='query'">新增物料</h3>
        <h3 class="title" v-if="from=='material'">修改物料</h3>
        <h3 class="title" v-if="from=='query'">物料</h3>
        <h4 class="title">基本信息</h4>
        <el-form-item>
          物料编号：<el-input v-bind:readonly="readonly" type="text" v-model="material.number" auto-complete="off" placeholder="物料编号" style="width:200px;margin-right: 100px"></el-input>
          物料名称：<el-input v-bind:readonly="readonly" type="text" v-model="material.name" auto-complete="off" placeholder="物料名称" style="width:200px"></el-input>
        </el-form-item>
        <el-form-item>
          计量单位：<el-input readonly suffix-icon="el-icon-search" v-model="material.baseUnitName" placeholder="计量单位" @click.native="addBaseUnit" style="width:200px;margin-right: 100px"></el-input>
          物料类型：<el-input readonly suffix-icon="el-icon-search" v-model="material.materialTypeName" placeholder="物料类型" @click.native="addMaterialType" style="width:200px"></el-input>
        </el-form-item>
        <el-form-item>
          物料规格：<el-input v-bind:readonly="readonly" type="text" v-model="material.materialModel" auto-complete="off" placeholder="物料规格" style="width:200px;margin-right: 370px"></el-input>
        </el-form-item>
      </el-form>

      <div style="display: flex;align-items: center;margin-top: 15px;justify-content: flex-end">
        <template v-if=this.show>
          <el-button type="primary" @click="saveMaterial">保存</el-button>
        </template>
      </div>
    </el-main>
  </el-container>
</template>
<script>
    import {postRequest} from '@/utils/api'
    import {getRequest} from '@/utils/api'
    // Local Registration
    import {mavonEditor} from 'mavon-editor'
    // 可以通过 mavonEditor.markdownIt 获取解析器markdown-it对象
    import 'mavon-editor/dist/css/index.css'
    import {isNotNullORBlank} from '@/utils/utils'
    import Vue from 'vue'
    import {postRequestJson, putRequest} from "../../utils/api";
    import updateBaseUnit from "./updateBaseUnit";
    import updateMaterialType from "./updateMaterialType";

    export default {
        mounted: function () {
            var from = this.$route.query.from;
            this.from = from;
            var _this = this;
            if(from == "query"){
                this.readonly = true;
            }
            if(from==undefined || from=='material'){
                _this.show = true;
            }
            if (from != null && from != '' && from != undefined) {
                var id = this.$route.query.id;
                this.id = id;
                this.loading = true;
                getRequest("/material/" + id).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.material = resp.data;
                    } else {
                        _this.$message({type: 'error', message: '页面加载失败!'})
                    }
                }, resp=> {
                    _this.loading = false;
                    _this.$message({type: 'error', message: '页面加载失败!'})
                })
            }

        },
        components: {
            updateMaterialType,
            updateBaseUnit,
            mavonEditor,
        },
        methods: {
            saveMaterial(){
                var _this = this;
                _this.loading = true;
                if(this.$route.query.id!=null){
                    putRequest("/material/update", {
                        id: _this.id,
                        name: _this.material.name,
                        number: _this.material.number,
                        baseUnitName: _this.material.baseUnitName,
                        baseUnitId: _this.material.baseUnitId,
                        materialTypeId: _this.material.materialTypeId,
                        materialModel: _this.material.materialModel,
                        materialTypeName: _this.material.materialTypeName
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '物料保存成功!'});
                            window.bus.$emit('blogTableReload')
                            _this.$router.replace({path: '/baseDataList'});
                        }
                    }, resp=> {
                        _this.loading = false;
                        _this.$message({type: 'error', message: '物料保存失败!'});
                    });
                }
                else{
                    postRequest("/material/", {
                        name: _this.material.name,
                        number: _this.material.number,
                        baseUnitName: _this.material.baseUnitName,
                        baseUnitId: _this.material.baseUnitId,
                        materialTypeId: _this.material.materialTypeId,
                        materialModel: _this.material.materialModel,
                        materialTypeName: _this.material.materialTypeName
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '物料保存成功!'});
                            window.bus.$emit('blogTableReload')
                            _this.$router.replace({path: '/baseDataList'});
                        }
                    }, resp=> {
                        _this.loading = false;
                        _this.$message({type: 'error', message: '物料保存失败!'});
                    });
                }
            },
            setBaseUnit(baseUnit){
                this.material.baseUnitId = baseUnit.id;
                this.material.baseUnitName = baseUnit.name;
            },
            setMaterialType(materialType){
                this.material.materialTypeId = materialType.id;
                this.material.materialTypeName = materialType.name;
            },
            addBaseUnit(){
                if(this.from!="query"){
                    this.showBaseUnit = true;
                    this.$nextTick(() => {
                        this.$refs.updateBaseUnit.init()
                    })
                }
            },
            addMaterialType(){
                if(this.from!="query"){
                    this.showMaterialType = true;
                    this.$nextTick(() => {
                        this.$refs.updateMaterialType.init()
                    })
                }
            }
        },
        data() {
            return {
                material:{
                    name: "",
                    number: "",
                    baseUnitName: "",
                    baseUnitId: "",
                    materialTypeId: "",
                    materialModel: "",
                    materialTypeName: ""
                },
                from: '',
                loading: false,
                readonly: false,
                show: false,
                showBaseUnit: false,
                showMaterialType: false,
            }
        }
    }
</script>
<style>
  .post-article > .main > #editor {
    width: 100%;
    height: 450px;
    text-align: left;
  }

  .post-article > .header {
    background-color: #ececec;
    margin-top: 10px;
    padding-left: 5px;
    display: flex;
    justify-content: flex-start;
  }

  .post-article > .main {
    /*justify-content: flex-start;*/
    display: flex;
    flex-direction: column;
    padding-left: 5px;
    background-color: #ececec;
    padding-top: 0px;
  }

  .post-article > .header > .el-tag + .el-tag {
    margin-left: 10px;
  }

  .post-article > .header > .button-new-tag {
    margin-left: 10px;
    height: 32px;
    line-height: 30px;
    padding-top: 0;
    padding-bottom: 0;
  }

  .post-article > .header > .input-new-tag {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }

  .title {
    margin: 0px auto 40px auto;
    text-align: left;
    color: #505458;
  }

</style>
