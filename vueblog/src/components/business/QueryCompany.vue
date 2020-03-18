<template>
  <el-dialog
    width="50%"
    title="企业信息详情"
    :visible.sync="dialogVisible">

    <el-form style="text-align: left">
      <h4 class="title">基本信息</h4>
      <el-divider></el-divider>
      <el-form-item>
        <span style="margin-left: 55px">企业名称：</span><span>{{company.coname}}</span>
        <span style="margin-left: 100px">法定代表人：</span><span>{{company.representative}}</span>
      </el-form-item>
      <el-form-item>
        <span style="margin-left: 55px">身份证号：</span><span>{{company.idNumber}}</span>
        <span style="margin-left: 133px">联系方式：</span><span>{{company.phoneNumber}}</span>
      </el-form-item>
      <el-form-item>
        <span>统一社会信用代码：</span>{{company.uscc}}
        <span style="margin-left: 105px">企业成立时间：</span>{{company.establishTime}}
      </el-form-item>
      <el-form-item>
        <span style="margin-left: 55px">注册资金：</span>{{company.registeredCapital}}
        <span style="margin-left: 162px">企业纳税人类型：</span>{{company.taxpayerType}}
      </el-form-item>

      <h4 class="title">业务信息</h4>
      <el-divider></el-divider>
      <el-form-item>
        <span>企业登记注册类型：</span>{{company.registrationType}}
        <span style="margin-left: 150px">所属技术领域：</span>{{company.technology}}
      </el-form-item>
      <el-form-item>
        <span style="margin-left: 55px">行业类别：</span>{{company.industryName}}
        <span style="margin-left: 180px">企业网站：</span>{{company.website}}
      </el-form-item>

      <h4 class="title">联系信息</h4>
      <el-divider></el-divider>
      <el-form-item>
        <span style="margin-left: 20px">企业联系地址：</span>{{company.address}}
        <span style="margin-left: 80px">邮政编码：</span>{{company.postalCode}}
      </el-form-item>
      <el-form-item>
        <span style="margin-left: 62px">联系人：</span>{{company.contact}}
        <span style="margin-left: 290px">电话：</span>{{company.contactPhone}}
      </el-form-item>
      <el-form-item>
        <span style="margin-left: 76px">传真：</span>{{company.fax}}
      </el-form-item>

      <h4 class="title">银行信息</h4>
      <el-divider></el-divider>
      <el-form-item>
        <span style="margin-left: 55px">银行卡号：</span>{{company.bankAccount}}
        <span style="margin-left: 100px">企业付款银行：</span>{{company.bankName}}
      </el-form-item>
      <el-form-item>
        <span style="margin-left: 55px">开户支行：</span>{{company.bankBranch}}
        <span style="margin-left: 85px">财务负责人：</span>{{company.treasurer}}
      </el-form-item>
      <el-form-item>
        <span style="margin-left: 55px">联系电话：</span>{{company.treasurerPhone}}
        <span style="margin-left: 198px">邮箱：</span>{{company.email}}
      </el-form-item>
    </el-form>
    <div style="display: flex;align-items: center;margin-top: 15px;justify-content: flex-end">
      <el-button type="primary" @click="close">确定</el-button>
    </div>
  </el-dialog>


</template>

<script>
    import {getRequest} from "../../utils/api";
    import moment from "moment";

    export default{
        data() {
            return {
                dialogVisible: false,
                company: [],
            }
        },
        methods: {
            init(){
                this.dialogVisible = true;
                var _this = this;
                getRequest("/company/"+ this.id).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.company = resp.data;
                    } else {
                        _this.$message({type: 'error', message: '页面加载失败!'})
                    }
                }, resp=> {
                    _this.loading = false;
                    _this.$message({type: 'error', message: '页面加载失败!'})
                })
            },
            close(){
                this.dialogVisible=false;
            },
            dateFormat(row, column, cellValue, index){
                const daterc = row[column.property];
                if(daterc!=null){
                    var date = row[column.property];
                    if(date == undefined){return ''}
                    return moment(date).format("YYYY-MM-DD")
                }
            },
        },
        props: ['id']
    }
</script>
