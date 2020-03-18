<template>
  <el-dialog
    width="50%"
    title="物料类型"
    :visible.sync="dialogVisible">

    <el-table
      ref="multipleTable"
      :data="materialType"
      tooltip-effect="dark"
      style="width: 100%;overflow-x: hidden; overflow-y: hidden;"
      max-height="390">
      <el-table-column label="选择" width="50" align="left">
        <template slot-scope="scope">
          <el-radio  v-model="selectMaterialType" :label="scope.row"><i></i></el-radio>
        </template>
      </el-table-column>
      <el-table-column
        prop="number"
        label="物料类型编号"
        width="200" align="left">
      </el-table-column>
      <el-table-column
        prop="name"
        label="物料类型名称"
        width="150" align="left">
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
            getRequest("/materialType/all").then(resp=> {
                _this.loading = false;
                if (resp.status == 200) {
                    _this.materialType = resp.data;
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
                materialType: [],
                selectMaterialType: {},
            }
        },
        methods: {
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
                this.$emit('selected',this.selectMaterialType)
            }
        },
    }
</script>
