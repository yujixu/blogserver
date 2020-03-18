<template>
  <el-dialog
    width="70%"
    title="领取记录"
    :visible.sync="dialogVisible">

    <el-table
      ref="multipleTable"
      :data="outDetail"
      tooltip-effect="dark"
      style="width: 100%;overflow-x: hidden; overflow-y: hidden;"
      max-height="390">
      <el-table-column
        prop="materialNumber"
        label="物料编号"
        width="100" align="left">
      </el-table-column>
      <el-table-column
        prop="materialName"
        label="物料名称"
        width="100" align="left">
      </el-table-column>
      <el-table-column
        prop="materialType"
        label="物料类型"
        width="100" align="left">
      </el-table-column>
      <el-table-column
        prop="materialModel"
        label="物料规格"
        width="100" align="left">
      </el-table-column>
      <el-table-column
        prop="useNum"
        label="数量"
        width="100" align="left">
      </el-table-column>
      <el-table-column
        prop="applicantName"
        label="领取人"
        width="100" align="left">
      </el-table-column>
      <el-table-column
        prop="outTime"
        label="领取时间"
        :formatter="dateFormat"
        width="160" align="left">
      </el-table-column>
    </el-table>
  </el-dialog>


</template>

<script>
    import {getRequest} from "../../utils/api";
    import moment from "moment";

    export default{
        data() {
            return {
                dialogVisible: false,
                outDetail: [],
                check: null,
            }
        },
        methods: {
            init(){
                this.dialogVisible = true;
                var _this = this;
                getRequest("/outDetail/hst/"+ this.stockId).then(resp=> {
                    _this.loading = false;
                    if (resp.status == 200) {
                        _this.outDetail = resp.data;
                    } else {
                        _this.$message({type: 'error', message: '页面加载失败!'})
                    }
                }, resp=> {
                    _this.loading = false;
                    _this.$message({type: 'error', message: '页面加载失败!'})
                })
            },
            dateFormat(row, column, cellValue, index){
                const daterc = row[column.property];
                if(daterc!=null){
                    var date = row[column.property];
                    if(date == undefined){return ''}
                    return moment(date).format("YYYY-MM-DD HH:mm:ss")
                }
            },
        },
        props: ['stockId']
    }
</script>
