<template>
  <el-container v-loading="loading">
    <el-main class="main">
      <el-steps :active="active" finish-status="success">
        <el-step title="步骤 1"></el-step>
        <el-step title="步骤 2"></el-step>
        <el-step title="步骤 3"></el-step>
      </el-steps>
      <el-form v-loading="loading">
        <h3 class="title" v-if="from!='update'">新增租赁合同</h3>
        <h3 class="title" v-if="from=='update'">修改租赁合同</h3>
        <el-form-item>
          合同类型：<el-input v-bind:readonly="readonly" type="text" v-model="agreement.number" auto-complete="off" placeholder="计量单位编号" style="width:200px;margin-right: 100px"></el-input>
        </el-form-item>
      </el-form>

      <div style="display: flex;align-items: center;margin-top: 15px;justify-content: flex-end">
        <template v-if=this.show>
          <el-button style="margin-top: 12px;" @click="next">下一步</el-button>
          <el-button type="primary" @click="saveAgreement">保存</el-button>
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
            if(from=='add'){
                _this.show = true;
            }
            if (from == "query") {
                var id = this.$route.query.id;
                this.id = id;
                this.loading = true;
                getRequest("/agreement/" + id).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.agreement = resp.data;
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
            saveAgreement(){
                var _this = this;
                _this.loading = true;
                if(this.$route.query.id!=null){
                    putRequest("/agreement/update", {
                        id: _this.id,
                        name: _this.agreement.name,
                        number: _this.agreement.number,
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '计量单位保存成功!'});
                            window.bus.$emit('blogTableReload')
                            _this.$router.replace({path: '/baseDataList'});
                        }
                    }, resp=> {
                        _this.loading = false;
                        _this.$message({type: 'error', message: '计量单位保存失败!'});
                    });
                }
                else{
                    postRequest("/agreement/", {
                        name: _this.agreement.name,
                        number: _this.agreement.number,
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '计量单位保存成功!'});
                            window.bus.$emit('blogTableReload')
                            _this.$router.replace({path: '/baseDataList'});
                        }
                    }, resp=> {
                        _this.loading = false;
                        _this.$message({type: 'error', message: '计量单位保存失败!'});
                    });
                }
            },
            next() {
                if (this.active++ > 2) this.active = 0;
            }
        },
        data() {
            return {
                agreement:{},
                from: '',
                loading: false,
                readonly: false,
                show: false,
                active: 0,

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
