<template>
  <el-container v-loading="loading">
    <el-main class="main">

      <el-form v-loading="loading">
        <h3 class="title" v-if="from!='employee' && from!='query'">新增员工</h3>
        <h3 class="title" v-if="from=='employee'">修改员工</h3>
        <h3 class="title" v-if="from=='query'">员工</h3>
        <h4 class="title">基本信息</h4>
        <el-form-item>
          <span style="margin-left: 0px">员工类型：</span><el-select v-model="employee.employeeType" class="input-border" v-bind:disabled="readonly" style="width:200px;margin-left: 20px">
            <el-option
              v-for="item in type"
              :key="item.value"
              :label="item.label"
              :value="item.label"
            ></el-option>
          </el-select>
          <span style="margin-left: 30px">员工姓名：</span><el-input v-bind:readonly="readonly" class="input-border" type="text" v-model="employee.name" auto-complete="off" placeholder="员工姓名" style="width:200px;margin-left: 20px"></el-input>
          <span style="margin-left: 55px">部门：</span><el-select v-model="employee.deptId" v-bind:disabled="readonly" class="input-border" style="width:200px;margin-left: 20px">
            <el-option
              v-for="item in dept"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <span style="margin-left: 30px">  岗位：</span><el-input v-bind:readonly="readonly" class="input-border" type="text" v-model="employee.position" auto-complete="off" placeholder="岗位" style="width:200px;margin-left: 20px"></el-input>
          <span style="margin-left: 30px">入职状态：</span><el-select v-bind:disabled="readonly" v-model="employee.entryStatus" class="input-border" style="width:200px;margin-left: 20px">
            <el-option
              v-for="item in entryStatus"
              :key="item.value"
              :label="item.label"
              :value="item.label"
            ></el-option>
          </el-select>
          <span style="margin-left: 30px">入职时间：</span><el-date-picker class="input-border" style="width:200px;margin-left: 20px" v-model="employee.entryDate" v-bind:readonly="readonly" type="date" value-format="yyyy-MM-dd" placeholder="入职时间"></el-date-picker>
        </el-form-item>
        <el-form-item>
          <span style="margin-left: 30px">合同到期：</span><el-date-picker class="input-border" style="width:200px;margin-left: 20px;margin-right: 680px;" v-model="employee.expiration" v-bind:readonly="readonly" type="date" placeholder="合同到期" value-format="yyyy-MM-dd"></el-date-picker>
        </el-form-item>
        <h4 class="title">资质信息</h4>
        <el-form-item>
          <span>政治面貌：</span><el-select v-model="employee.politicalOutlook" v-bind:disabled="readonly" class="input-border" style="width:200px;margin-left: 20px">
            <el-option
              v-for="item in pOutlook"
              :key="item.value"
              :label="item.label"
              :value="item.label"
            ></el-option>
          </el-select>
          <span style="margin-left: 30px">最高学历：</span><el-select v-bind:disabled="readonly" v-model="employee.education" class="input-border" style="width:200px;margin-left: 20px">
            <el-option
              v-for="item in education"
              :key="item.value"
              :label="item.label"
              :value="item.label"
            ></el-option>
          </el-select>
          <span style="margin-left: 30px">毕业院校：</span><el-input v-bind:readonly="readonly" class="input-border" type="text" v-model="employee.university" auto-complete="off" placeholder="毕业院校" style="width:200px;margin-left: 20px"></el-input>
        </el-form-item>
        <el-form-item>
          <span style="margin-left: 30px">专业：</span><el-input v-bind:readonly="readonly" class="input-border" type="text" v-model="employee.major" auto-complete="off" placeholder="专业" style="width:200px;margin-left: 20px"></el-input>
          <span style="margin-left: 30px">职业资格：</span><el-input v-bind:readonly="readonly" class="input-border" type="text" v-model="employee.certificate" auto-complete="off" placeholder="职业资格" style="width:200px;margin-left: 20px;margin-right: 330px"></el-input>
        </el-form-item>
        <h4 class="title">个人信息</h4>
        <el-form-item>
          <span>出生日期：</span><el-date-picker class="input-border" placeholder="出生日期" style="width:200px;margin-left: 20px" v-model="employee.birthday" v-bind:readonly="readonly" type="date" value-format="yyyy-MM-dd"></el-date-picker>
          <span style="margin-left: 30px">身份证号：</span><el-input v-bind:readonly="readonly" class="input-border" type="text" v-model="employee.idNumber" auto-complete="off" placeholder="身份证号" style="width:200px;margin-left: 20px"></el-input>
          <span style="margin-left: 30px">婚姻状态：</span><el-select v-bind:disabled="readonly" v-model="employee.maritalStatus" class="input-border" style="width:200px;margin-left: 20px">
            <el-option
              v-for="item in maritalStatus"
              :key="item.value"
              :label="item.label"
              :value="item.label"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <span style="margin-left: 30px">性别：</span><el-select v-model="employee.sex" v-bind:disabled="readonly" class="input-border" style="width:200px;margin-left: 20px">
            <el-option
              v-for="item in sex"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
          <span style="margin-left: 30px">手机号码：</span><el-input v-bind:readonly="readonly" class="input-border" type="text" v-model="employee.phoneNumber" auto-complete="off" placeholder="手机号码" style="width:200px;margin-left: 20px"></el-input>
          <span style="margin-left: 55px">户籍：</span><el-input v-bind:readonly="readonly" class="input-border" type="text" v-model="employee.registered" auto-complete="off" placeholder="户籍" style="width:200px;margin-left: 20px"></el-input>
        </el-form-item>
        <el-form-item>
          <span>邮箱：</span><el-input v-bind:readonly="readonly" class="input-border" type="text" v-model="employee.email" auto-complete="off" placeholder="邮箱" style="width:200px;margin-left: 20px;margin-right: 620px;"></el-input>
        </el-form-item>
      </el-form>

      <div style="display: flex;align-items: center;margin-top: 15px;justify-content: flex-end">
        <template v-if=this.show>
          <el-button type="primary" @click="saveEmployee">保存</el-button>
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
    import formatDate from "../../utils/utils";

    export default {
        mounted: function () {
            var from = this.$route.query.from;
            this.from = from;
            var _this = this;
            if(from == "query"){
                this.readonly = true;
            }
            if(from==undefined || from=='employee'){
                _this.show = true;
            }
            if (from != null && from != '' && from != undefined) {
                var id = this.$route.query.id;
                this.id = id;
                this.loading = true;
                getRequest("/employee/" + id).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.employee = resp.data;
                        _this.employee.birthday = formatDate(_this.employee.birthday);
                        _this.employee.entryDate = formatDate(_this.employee.entryDate);
                        _this.employee.expiration = formatDate(_this.employee.expiration);
                    } else {
                        _this.$message({type: 'error', message: '页面加载失败!'})
                    }
                }, resp=> {
                    _this.loading = false;
                    _this.$message({type: 'error', message: '页面加载失败!'})
                });
            }
            getRequest("/dept/all").then(resp=> {
                _this.loading = false;
                if (resp.status == 200) {
                    _this.dept = resp.data;
                } else {
                    _this.$message({type: 'error', message: '页面加载失败!'})
                }
            }, resp=> {
                _this.loading = false;
                _this.$message({type: 'error', message: '页面加载失败!'})
            })

        },
        components: {
            mavonEditor,
        },
        methods: {
            saveEmployee(){
                var _this = this;
                _this.loading = true;
                console.log(_this.employee.entryDate);
                if(this.$route.query.id!=null){
                    putRequest("/employee/update", {
                        id: _this.employee.id,
                        employeeType: _this.employee.employeeType,
                        name: _this.employee.name,
                        position: _this.employee.position,
                        deptId: _this.employee.deptId,
                        entryStatus: _this.employee.entryStatus,
                        politicalOutlook: _this.employee.politicalOutlook,
                        entryDate: _this.employee.entryDate,
                        expiration: _this.employee.expiration,
                        education: _this.employee.education,
                        major: _this.employee.major,
                        university: _this.employee.university,
                        certificate: _this.employee.certificate,
                        birthday: _this.employee.birthday,
                        sex: _this.employee.sex,
                        email: _this.employee.email,
                        idNumber: _this.employee.idNumber,
                        maritalStatus: _this.employee.maritalStatus,
                        phoneNumber: _this.employee.phoneNumber,
                        registered: _this.employee.registered,
                        number: _this.employee.number
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '员工信息保存成功!'});
                            window.bus.$emit('blogTableReload')
                            _this.$router.replace({path: '/companyList'});
                        }
                    }, resp=> {
                        _this.loading = false;
                        _this.$message({type: 'error', message: '员工信息保存失败!'});
                    });
                }
                else{
                    postRequest("/employee/", {
                        employeeType: _this.employee.employeeType,
                        name: _this.employee.name,
                        position: _this.employee.position,
                        deptId: _this.employee.deptId,
                        entryStatus: _this.employee.entryStatus,
                        politicalOutlook: _this.employee.politicalOutlook,
                        entryDate: _this.employee.entryDate,
                        expiration: _this.employee.expiration,
                        education: _this.employee.education,
                        major: _this.employee.major,
                        university: _this.employee.university,
                        certificate: _this.employee.certificate,
                        birthday: _this.employee.birthday,
                        sex: _this.employee.sex,
                        email: _this.employee.email,
                        idNumber: _this.employee.idNumber,
                        maritalStatus: _this.employee.maritalStatus,
                        phoneNumber: _this.employee.phoneNumber,
                        registered: _this.employee.registered,
                    }).then(resp=> {
                        _this.loading = false;
                        if (resp.status == 200 && resp.data.status == 'success') {
                            _this.$message({type: 'success', message: '员工信息保存成功!'});
                            window.bus.$emit('blogTableReload')
                            _this.$router.replace({path: '/companyList'});
                        }
                    }, resp=> {
                        _this.loading = false;
                        _this.$message({type: 'error', message: '员工信息保存失败!'});
                    });
                }
            },
        },
        data() {
            return {
                employee:{},
                type: [{
                    value: '1',
                    label: '正式员工'
                }, {
                    value: '2',
                    label: '劳务派遣'
                }],
                dept: [],
                entryStatus:[{
                    value: '1',
                    label: '未入职'
                }, {
                    value: '2',
                    label: '已入职'
                }, {
                    value: '3',
                    label: '已离职'
                }],
                pOutlook: [{
                    value: '1',
                    label: '群众'
                }, {
                    value: '2',
                    label: '中共党员'
                }, {
                    value: '3',
                    label: '中共预备党员'
                },{
                    value: '4',
                    label: '共青团员'
                }, {
                    value: '5',
                    label: '民革党员'
                }, {
                    value: '6',
                    label: '民盟盟员'
                },{
                    value: '7',
                    label: '民建会员'
                }, {
                    value: '8',
                    label: '民进会员'
                }, {
                    value: '9',
                    label: '农工党党员'
                },{
                    value: '10',
                    label: '致公党党员'
                }, {
                    value: '11',
                    label: '九三学社社员'
                }, {
                    value: '12',
                    label: '台盟盟员'
                }, {
                    value: '13',
                    label: '无党派人士'
                }],
                education: [{
                    value: '0',
                    label: '无'
                },{
                    value: '1',
                    label: '职高'
                }, {
                    value: '2',
                    label: '中专'
                }, {
                    value: '3',
                    label: '博士'
                },{
                    value: '4',
                    label: '硕士'
                }, {
                    value: '5',
                    label: '专科'
                }, {
                    value: '6',
                    label: '本科'
                },{
                    value: '7',
                    label: '高中'
                }, {
                    value: '8',
                    label: '初中'
                }, {
                    value: '9',
                    label: '小学'
                }],
                maritalStatus: [{
                    value: '1',
                    label: '未婚'
                }, {
                    value: '2',
                    label: '已婚'
                }],
                sex: [{
                    value: '男',
                    label: '男'
                }, {
                    value: '女',
                    label: '女'
                }],
                from: '',
                loading: false,
                readonly: false,
                selItems: [],
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

  .input-border{
    border:1px solid #bfbfbf;
    border-radius:0.4rem;
  }
</style>
