<template>
  <el-container v-loading="loading">
    <el-main class="main">
      <h3 class="title" v-if="from!='update'">新增企业</h3>
      <h3 class="title" v-if="from=='update'">修改企业信息</h3>
      <el-tabs type="card" @tab-click="handleClick" stretch="stretch" v-model="activeName">
        <el-tab-pane label="企业基础信息" name="base"></el-tab-pane>
        <el-tab-pane label="企业其他信息" name="other"></el-tab-pane>
      </el-tabs>
      <el-form v-loading="loading" v-if="showBase">
        <h4 class="title">基本信息</h4>
        <el-form-item>
          企业名称：<el-input size="small" type="text" v-model="company.COName" auto-complete="off" placeholder="企业名称" style="width:500px"></el-input>
        </el-form-item>
        <el-form-item>
          法定代表人：<el-input size="small" type="text" v-model="company.representative" auto-complete="off" placeholder="法定代表人" style="width:500px; margin-right: 15px"></el-input>
        </el-form-item>
        <el-form-item>
          身份证号：<el-input size="small" type="text" v-model="company.idNumber" auto-complete="off" placeholder="身份证号" style="width:500px"></el-input>
        </el-form-item>
        <el-form-item>
          联系方式：<el-input size="small" type="text" v-model="company.phoneNumber" auto-complete="off" placeholder="联系方式" style="width:500px"></el-input>
        </el-form-item>
        <el-form-item>
          统一社会信用代码：<el-input size="small" type="text" v-model="company.uscc" auto-complete="off" placeholder="统一社会信用代码" style="width:500px; margin-right: 55px"></el-input>
        </el-form-item>
        <el-form-item>
          企业成立时间：<el-date-picker size="small" style="width:500px; margin-right: 25px" v-model="company.establishTime" type="date" value-format="yyyy-MM-dd" placeholder="企业成立时间"></el-date-picker>
        </el-form-item>
        <el-form-item>
          注册资金：<el-input size="small" type="text" v-model="company.registeredCapital" auto-complete="off" placeholder="注册资金" style="width:500px"></el-input>
        </el-form-item>
        <el-form-item>
          企业纳税人类型：<el-select v-model="company.taxpayerType" style="width:500px; margin-right: 40px" size="small">
            <el-option
              v-for="item in taxpayerType"
              :key="item.value"
              :label="item.label"
              :value="item.label"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>

      <el-form v-loading="loading" v-if="showOther">
        <h4 class="title">业务信息</h4>
        <el-form-item>
          企业登记注册类型：<el-select v-model="company.registrationType" style="width:500px; margin-right: 55px" size="small">
            <el-option
              v-for="item in registrationType"
              :key="item.value"
              :label="item.label"
              :value="item.label"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          所属技术领域：<el-input size="small" type="text" v-model="company.technology" auto-complete="off" placeholder="所属技术领域" style="width:500px; margin-right: 25px"></el-input>
        </el-form-item>
        <el-form-item>
          行业类别：
          <el-cascader
            v-model="value"
            size="small"
            :options="industry"
            :props="{value:'code', label:'name', children: 'list'}"
            filterable
            style="width: 500px"
            clearable
            @change="industryChange"
          ></el-cascader>
        </el-form-item>
        <el-form-item>
          企业网站：<el-input size="small" type="text" v-model="company.website" auto-complete="off" placeholder="联系方式" style="width:500px"></el-input>
        </el-form-item>

        <h4 class="title">联系信息</h4>
        <el-form-item>
          企业联系地址：<el-input size="small" type="text" v-model="company.address" auto-complete="off" placeholder="企业联系地址" style="width:500px; margin-right: 55px"></el-input>
        </el-form-item>
        <el-form-item>
          邮政编码：<el-input size="small" type="text" v-model="company.postalCode" auto-complete="off" placeholder="邮政编码" style="width:500px; margin-right: 30px"></el-input>
        </el-form-item>
        <el-form-item>
          联系人：<el-input size="small" type="text" v-model="company.contact" auto-complete="off" placeholder="联系人" style="width:500px; margin-right: 15px"></el-input>
        </el-form-item>
        <el-form-item>
          电话：<el-input size="small" type="text" v-model="company.contactPhone" auto-complete="off" placeholder="电话" style="width:500px"></el-input>
        </el-form-item>
        <el-form-item>
          传真：<el-input size="small" type="text" v-model="company.fax" auto-complete="off" placeholder="传真" style="width:500px"></el-input>
        </el-form-item>

        <h4 class="title">银行信息</h4>
        <el-form-item>
          银行卡号：<el-input size="small" type="text" v-model="company.bankAccount" auto-complete="off" placeholder="银行卡号" style="width:500px; margin-right: 30px"></el-input>
        </el-form-item>
        <el-form-item>
          企业付款银行：<el-select v-model="company.bankId" style="width:500px; margin-right: 55px" size="small">
            <el-option
              v-for="item in bank"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          开户支行：<el-input size="small" type="text" v-model="company.bankBranch" auto-complete="off" placeholder="开户支行" style="width:500px; margin-right: 28px"></el-input>
        </el-form-item>
        <el-form-item>
          财务负责人：<el-input size="small" type="text" v-model="company.treasurer" auto-complete="off" placeholder="财务负责人" style="width:500px; margin-right: 40px"></el-input>
        </el-form-item>
        <el-form-item>
          联系电话：<el-input size="small" type="text" v-model="company.treasurerPhone" auto-complete="off" placeholder="联系电话" style="width:500px; margin-right: 30px"></el-input>
        </el-form-item>
        <el-form-item>
          邮箱：<el-input size="small" type="text" v-model="company.email" auto-complete="off" placeholder="邮箱" style="width:500px"></el-input>
        </el-form-item>
      </el-form>

      <div style="margin-top: 15px;" align="center">
        <template>
          <el-button v-if="showBase" type="primary" @click="next">下一步</el-button>
          <el-button v-if="showOther" type="primary" @click="saveCompany">保存</el-button>
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
            if (from=="update") {
                var id = this.$route.query.id;
                this.id = id;
                this.loading = true;
                getRequest("/company/" + id).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.company = resp.data;
                        _this.value = _this.company.industryId;
                    } else {
                        _this.$message({type: 'error', message: '页面加载失败!'})
                    }
                }, resp=> {
                    _this.loading = false;
                    _this.$message({type: 'error', message: '页面加载失败!'})
                })
            }
            getRequest("/industry/all").then(resp=> {
                if (resp.status == 200) {
                    _this.industry = resp.data;
                } else {
                    _this.$message({type: 'error', message: '页面加载失败!'})
                }
            }, resp=> {
                _this.$message({type: 'error', message: '页面加载失败!'})
            });

            getRequest("/bank/all").then(resp=> {
                if (resp.status == 200) {
                    _this.bank = resp.data;
                } else {
                    _this.$message({type: 'error', message: '页面加载失败!'})
                }
            }, resp=> {
                _this.$message({type: 'error', message: '页面加载失败!'})
            });
        },
        components: {
            mavonEditor,
        },
        methods: {
            next(){
                var _this = this;
                _this.activeName = "other";
                _this.showBase=false;
                _this.showOther=true;
            },
            industryChange(){
                this.company.industryId = this.value[this.value.length-1];
            },
            saveCompany(){
                var _this = this;
                _this.loading = true;
                if(this.$route.query.id!=null){
                    putRequest("/company/update", {
                        id: _this.id,
                        COName: _this.company.COName,
                        representative: _this.company.representative,
                        idNumber: _this.company.idNumber,
                        phoneNumber: _this.company.phoneNumber,
                        uscc: _this.company.uscc,
                        establishTime: _this.company.establishTime,
                        registeredCapital: _this.company.registeredCapital,
                        taxpayerType: _this.company.taxpayerType,
                        registrationType: _this.company.registrationType,
                        technology: _this.company.technology,
                        industryId: _this.company.industryId,
                        website: _this.company.website,
                        address: _this.company.address,
                        postalCode: _this.company.postalCode,
                        contact: _this.company.contact,
                        contactPhone: _this.company.contactPhone,
                        fax: _this.company.fax,
                        bankAccount: _this.company.bankAccount,
                        bankBranch: _this.company.bankBranch,
                        bankId: _this.company.bankId,
                        treasurer: _this.company.treasurer,
                        treasurerPhone: _this.company.treasurerPhone,
                        email: _this.company.email,
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '企业保存成功!'});
                            window.bus.$emit('blogTableReload')
                            _this.$router.replace({path: '/company'});
                        }
                    }, resp=> {
                        _this.loading = false;
                        _this.$message({type: 'error', message: '企业保存失败!'});
                    });
                }
                else{
                    postRequest("/company/", {
                        COName: _this.company.COName,
                        representative: _this.company.representative,
                        idNumber: _this.company.idNumber,
                        phoneNumber: _this.company.phoneNumber,
                        uscc: _this.company.uscc,
                        establishTime: _this.company.establishTime,
                        registeredCapital: _this.company.registeredCapital,
                        taxpayerType: _this.company.taxpayerType,
                        registrationType: _this.company.registrationType,
                        technology: _this.company.technology,
                        industryId: _this.company.industryId,
                        website: _this.company.website,
                        address: _this.company.address,
                        postalCode: _this.company.postalCode,
                        contact: _this.company.contact,
                        contactPhone: _this.company.contactPhone,
                        fax: _this.company.fax,
                        bankAccount: _this.company.bankAccount,
                        bankBranch: _this.company.bankBranch,
                        bankId: _this.company.bankId,
                        treasurer: _this.company.treasurer,
                        treasurerPhone: _this.company.treasurerPhone,
                        email: _this.company.email,
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '企业保存成功!'});
                            window.bus.$emit('blogTableReload')
                            _this.$router.replace({path: '/company'});
                        }
                    }, resp=> {
                        _this.loading = false;
                        _this.$message({type: 'error', message: '企业保存失败!'});
                    });
                }
            },
            handleClick(tab, event) {
                var _this = this;
                if(tab.name=="base"){
                    _this.showBase = true;
                    _this.showOther = false;
                }
                if(tab.name=="other"){
                    _this.showBase = false;
                    _this.showOther = true;
                }
            }
        },
        data() {
            return {
                company:{},
                from: '',
                loading: false,
                readonly: false,
                showBase: true,
                showOther: false,
                activeName: "base",
                taxpayerType: [{
                    value: '1',
                    label: '自然人'
                }, {
                    value: '2',
                    label: '法人'
                }, {
                    value: '3',
                    label: '个体工商户'
                }],
                registrationType: [{
                    value: '1',
                    label: '外商投资企业'
                }, {
                    value: '2',
                    label: '港澳台商投资企业'
                }, {
                    value: '3',
                    label: '内资企业'
                }],
                value: 0,
                industry: [],
                bank: [],
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
    margin: 0px auto 20px auto;
    text-align: left;
    color: #505458;
  }

</style>
