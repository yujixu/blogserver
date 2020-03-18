<template>
  <el-dialog
    width="50%"
    title="领用申请"
    :visible.sync="dialogVisible">

    <el-table
      ref="multipleTable"
      :data="useApply"
      tooltip-effect="dark"
      style="width: 100%;overflow-x: hidden; overflow-y: hidden;"
      max-height="390"
      @current-change="handleSelectionChange">
      <el-table-column
        label="选择"
        width="50" align="left">
        <template slot-scope="scope">
          <el-radio  v-model="check" :label="scope.row"><i></i></el-radio>
        </template>
      </el-table-column>
      <el-table-column
        prop="number"
        label="领用申请编号"
        width="130" align="left">
      </el-table-column>
      <el-table-column
        prop="name"
        label="领用申请名称"
        width="130" align="left">
      </el-table-column>
      <el-table-column
        prop="reason"
        label="领用原因"
        width="100" align="left">
      </el-table-column>
      <el-table-column
        prop="applyTime"
        label="申请时间"
        :formatter="dateFormat"
        width="160" align="left">
      </el-table-column>
    </el-table>
    <div style="display: flex;align-items: center;margin-top: 15px;justify-content: flex-end">
      <el-button type="primary" @click="cancel">取消</el-button>
      <el-button type="primary" @click="add">确定</el-button>
    </div>
  </el-dialog>


</template>

<script>
    import {getRequest} from "../../utils/api";
    import moment from "moment";

    export default{
        mounted: function () {
            var _this = this;
            getRequest("/use/getCheck").then(resp=> {
                _this.loading = false;
                if (resp.status == 200) {
                    _this.useApply = resp.data;
                } else {
                    _this.$message({type: 'error', message: '页面加载失败!'})
                }
            }, resp=> {
                _this.loading = false;
                _this.$message({type: 'error', message: '页面加载失败!'})
            })
        },
        data() {
            return {
                dialogVisible: false,
                useApply: [],
                selItems: {},
                check: null,
            }
        },
        methods: {
            handleSelectionChange(val) {
                this.selItems = val;
            },
            cancel(){
                this.dialogVisible=false;
                this.select();
            },
            add(){
                this.dialogVisible=false;
                this.select();
            },
            init(){
                this.dialogVisible = true;
            },
            select(){
                this.$emit('reselected',this.selItems)
            },
            dateFormat(row, column, cellValue, index){
                const daterc = row[column.property];
                if(daterc!=null){
                    var date = row[column.property];
                    if(date == undefined){return ''}
                    return moment(date).format("YYYY-MM-DD HH:mm")
                }
            },
        },
    }
</script>
