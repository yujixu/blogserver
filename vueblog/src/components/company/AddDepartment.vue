<template>
  <el-container v-loading="loading">
    <el-main class="main">
      <el-form v-loading="loading">
        <h3 class="title" v-if="from!='dept' && from!='query'">新增部门</h3>
        <h3 class="title" v-if="from=='dept'">修改部门</h3>
        <h3 class="title" v-if="from=='query'">部门</h3>
        <h4 class="title">基本信息</h4>
        <el-form-item>
          部门编号：<el-input v-bind:readonly="readonly" type="text" v-model="department.number" auto-complete="off" placeholder="部门编号" style="width:200px;margin-right: 100px"></el-input>
          部门名称：<el-input v-bind:readonly="readonly" type="text" v-model="department.name" auto-complete="off" placeholder="部门名称" style="width:200px"></el-input>
        </el-form-item>
      </el-form>

      <div style="display: flex;align-items: center;margin-top: 15px;justify-content: flex-end">
        <template v-if=this.show>
          <el-button type="primary" @click="saveDepartment">保存</el-button>
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

    export default {
        mounted: function () {
            var from = this.$route.query.from;
            this.from = from;
            var _this = this;
            if(from == "query"){
                this.readonly = true;
            }
            if(from==undefined || from=='unit'){
                _this.show = true;
            }
            if (from != null && from != '' && from != undefined) {
                var id = this.$route.query.id;
                this.id = id;
                this.loading = true;
                getRequest("/dept/" + id).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.department = resp.data;
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
            mavonEditor,
        },
        methods: {
            saveDepartment(){
                var _this = this;
                _this.loading = true;
                if(this.$route.query.id!=null){
                    putRequest("/dept/update", {
                        id: _this.id,
                        name: _this.department.name,
                        number: _this.department.number,
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '部门保存成功!'});
                            window.bus.$emit('blogTableReload')
                            _this.$router.replace({path: '/baseDataList'});
                        }
                    }, resp=> {
                        _this.loading = false;
                        _this.$message({type: 'error', message: '部门保存失败!'});
                    });
                }
                else{
                    postRequest("/dept/", {
                        name: _this.department.name,
                        number: _this.department.number,
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '部门保存成功!'});
                            window.bus.$emit('blogTableReload')
                            _this.$router.replace({path: '/companyList'});
                        }
                    }, resp=> {
                        _this.loading = false;
                        _this.$message({type: 'error', message: '部门保存失败!'});
                    });
                }
            }
        },
        data() {
            return {
                department:{
                    name: "",
                    number: "",
                },
                from: '',
                loading: false,
                readonly: false,
                show: false,
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
