<template>
  <el-dialog
    width="50%"
    title="采购合同"
    :visible.sync="dialogVisible">

    <el-table
      ref="multipleTable"
      :data="contract"
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
        label="采购合同编号"
        width="120" align="left">
      </el-table-column>
      <el-table-column
        prop="name"
        label="采购合同名称"
        width="120" align="left">
      </el-table-column>
      <el-table-column
        prop="purType"
        label="采购类型"
        width="100" align="left">
      </el-table-column>
      <el-table-column
        prop="amount"
        label="总金额(元)"
        width="100" align="left">
      </el-table-column>
    </el-table>
    <div style="display: flex;align-items: center;margin-top: 15px;justify-content: flex-end">
      <el-button type="primary" @click="cancel">取消</el-button>
      <el-button type="primary" @click="addPurContract">确定</el-button>
    </div>
  </el-dialog>


</template>

<script>
    import {getRequest} from "../../utils/api";

    export default{
        mounted: function () {
            var _this = this;
            getRequest("/contract/getClose").then(resp=> {
                _this.loading = false;
                if (resp.status == 200) {
                    _this.contract = resp.data;
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
                contract: [],
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
            addPurContract(){
                this.dialogVisible=false;
                this.select();
            },
            init(){
                this.dialogVisible = true;
            },
            select(){
                this.$emit('reselected',this.selItems)
            }
        },
    }
</script>
