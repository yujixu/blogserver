<template>
  <el-dialog
    width="50%"
    title="采购申请单"
    :visible.sync="dialogVisible">

    <el-table
      ref="multipleTable"
      :data="purReq"
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
        label="单据编号"
        width="300" align="left">
      </el-table-column>
      <el-table-column
        prop="name"
        label="单据名称"
        width="300" align="left">
      </el-table-column>

    </el-table>
    <div style="display: flex;align-items: center;margin-top: 15px;justify-content: flex-end">
      <el-button type="primary" @click="cancel">取消</el-button>
      <el-button type="primary" @click="addPurReq">确定</el-button>
    </div>
  </el-dialog>


</template>

<script>
    import {getRequest} from "../../utils/api";

    export default{
        mounted: function () {
            var _this = this;
            getRequest("/purReq/getExcute").then(resp=> {
                _this.loading = false;
                if (resp.status == 200) {
                    _this.purReq = resp.data;
                    var tags = resp.data.tags;
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
                purReq: [],
                selected: {},
                check: null,
            }
        },
        methods: {
            handleSelectionChange(row) {
                this.selected=row;
            },
            cancel(){
                this.dialogVisible=false;
                this.select();
            },
            addPurReq(){
                this.dialogVisible=false;
                this.select();
            },
            init(){
                this.dialogVisible = true;
            },
            select(){
                this.$emit('reselected',this.selected)
            }
        },
    }
</script>
