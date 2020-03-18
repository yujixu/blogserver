<template>
  <el-dialog
    width="50%"
    title="计量单位"
    :visible.sync="dialogVisible">

    <el-table
      ref="multipleTable"
      :data="baseUnit"
      tooltip-effect="dark"
      style="width: 100%;overflow-x: hidden; overflow-y: hidden;"
      max-height="390">
      <el-table-column label="选择" width="50" align="left">
        <template slot-scope="scope">
          <el-radio  v-model="selectBaseUnit" :label="scope.row"><i></i></el-radio>
        </template>
      </el-table-column>
      <el-table-column
        prop="number"
        label="计量单位编号"
        width="200" align="left">
      </el-table-column>
      <el-table-column
        prop="name"
        label="计量单位名称"
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
            getRequest("/baseUnit/all").then(resp=> {
                _this.loading = false;
                if (resp.status == 200) {
                    _this.baseUnit = resp.data;
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
                baseUnit: [],
                selectBaseUnit: {},
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
                this.$emit('selected',this.selectBaseUnit)
            }
        },
    }
</script>
