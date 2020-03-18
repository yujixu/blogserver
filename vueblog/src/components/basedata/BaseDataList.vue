<template>
  <el-container>
    <el-main class="main">
      <el-tabs v-model="activeName" @tab-click="handleClick" type="card">
        <el-tab-pane label="物料" name="material">
          <material_table state="-1" :showEdit="true" :showDelete="true" :showRestore="false" :activeName="activeName"></material_table>
        </el-tab-pane>
        <el-tab-pane label="物料类型" name="type">
          <material_type_table state="1" :showEdit="true" :showDelete="true" :showRestore="false" :activeName="activeName"></material_type_table>
        </el-tab-pane>
        <el-tab-pane label="计量单位" name="unit">
          <base_unit state="3" :showEdit="true" :showDelete="true" :showRestore="false" :activeName="activeName"></base_unit>
        </el-tab-pane>
        <el-tab-pane label="供应商" name="supplier">
          <supplier_table state="5" :showEdit="true" :showDelete="true" :showRestore="false" :activeName="activeName"></supplier_table>
        </el-tab-pane>
      </el-tabs>
    </el-main>
  </el-container>
</template>
<script>
    import {getRequest} from '@/utils/api'
    import MaterialTable from "./MaterialTable";
    import MaterialTypeTable from "./MaterialTypeTable"
    import BaseUnitTable from "./BaseUnitTable"
    import SupplierTable from "./SupplierTable";

    export default {
        mounted: function () {
            var _this = this;
            getRequest("/isAdmin").then(resp=> {
                if (resp.status == 200) {
                    _this.isAdmin = resp.data;
                }
            })
        },
        data() {
            return {
                activeName: 'material',
                isAdmin: false
            };
        },
        methods: {
            handleClick(tab, event) {
//        console.log(tab, event);
            }
        },
        components: {
            'material_table': MaterialTable,
            'base_unit': BaseUnitTable,
            'material_type_table': MaterialTypeTable,
            'supplier_table': SupplierTable
        }
    };
</script>
<style>
  .article_list > .header {
    background-color: #ececec;
    margin-top: 10px;
    padding-left: 5px;
    display: flex;
    justify-content: flex-start;
  }

  .article_list > .main {
    /*justify-content: flex-start;*/
    display: flex;
    flex-direction: column;
    padding-left: 0px;
    background-color: #fff;
    padding-top: 0px;
    margin-top: 8px;
  }
</style>
