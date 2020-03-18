<template>
  <el-dialog
    width="50%"
    title="物料"
    :visible.sync="dialogVisible">

    <el-table
      ref="multipleTable"
      :data="material"
      tooltip-effect="dark"
      style="width: 100%;overflow-x: hidden; overflow-y: hidden;"
      max-height="390"
      @selection-change="handleSelectionChange">
      <el-table-column
        type="selection"
        width="35" align="left">
      </el-table-column>
      <el-table-column
        prop="number"
        label="物料编号"
        width="100" align="left">
      </el-table-column>
      <el-table-column
        prop="name"
        label="物料名称"
        width="100" align="left">
      </el-table-column>
      <el-table-column
        prop="materialTypeName"
        label="物料类型"
        width="100" align="left">
      </el-table-column>
      <el-table-column
        prop="materialModel"
        label="物料规格"
        width="100" align="left">
      </el-table-column>
      <el-table-column
        prop="baseUnitName"
        label="计量单位"
        width="100" align="left">
      </el-table-column>
    </el-table>
    <div style="display: flex;align-items: center;margin-top: 15px;justify-content: flex-end">
      <el-button type="primary" @click="cancel">取消</el-button>
      <el-button type="primary" @click="addMaterial">确定</el-button>
    </div>
  </el-dialog>


</template>

<script>
    import {getRequest} from "../../utils/api";

    export default{
        mounted: function () {
            var _this = this;
            getRequest("/material/all").then(resp=> {
                _this.loading = false;
                if (resp.status == 200) {
                    _this.material = resp.data;
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
                material: [],
                selItems: [],
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
            addMaterial(){
                this.dialogVisible=false;
                this.select();
            },
            init(){
                this.dialogVisible = true;
            },
            select(){
                this.$emit('reselected',this.selItems)
                this.$refs.multipleTable.clearSelection();
            }
        },
    }
</script>
