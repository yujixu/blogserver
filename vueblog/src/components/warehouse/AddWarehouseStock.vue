<template>
  <el-dialog
    width="50%"
    title="采购入库单"
    :visible.sync="dialogVisible">

    <el-table
      ref="multipleTable"
      :data="receipt"
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
        label="采购入库单编号"
        width="150" align="left">
      </el-table-column>
      <el-table-column
        prop="name"
        label="采购入库单名称"
        width="150" align="left">
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
            getRequest("/receipt/getExcute").then(resp=> {
                _this.loading = false;
                if (resp.status == 200) {
                    _this.receipt = resp.data;
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
                receipt: [],
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
                var _this = this;
                if(this.selItems.id!=null && this.selItems.id!=""){
                    this.$confirm('是否确认该入库单中所有物料都已经入库?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        this.dialogVisible=false;
                        this.select();
                    }).catch(() => {
                        _this.$message({
                            type: 'info',
                            message: '已取消'
                        });
                    });
                }else{
                    this.dialogVisible=false;
                }
            },
            init(){
                this.dialogVisible = true;
            },
            select(){
                this.$emit('reselected',this.selItems.id)
            }
        },
    }
</script>
