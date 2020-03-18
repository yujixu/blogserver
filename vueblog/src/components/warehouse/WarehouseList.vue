<template>
  <el-container>
    <el-main class="main">
      <el-tabs v-model="activeName" @tab-click="handleClick" type="card">
        <el-tab-pane label="采购入库" name="receipt">
          <receipt_table state="-1" :showEdit="true" :showDelete="true" :showRestore="false" :activeName="activeName"></receipt_table>
        </el-tab-pane>
        <el-tab-pane label="库存统计" name="stock">
          <stock_table state="5" :showEdit="true" :showDelete="true" :showRestore="false" :activeName="activeName"></stock_table>
        </el-tab-pane>
        <el-tab-pane label="出库管理" name="out">
          <out_table state="1" :showEdit="true" :showDelete="true" :showRestore="false" :activeName="activeName"></out_table>
        </el-tab-pane>
        <el-tab-pane label="领用申请" name="use">
          <use_table state="-3" :showEdit="true" :showDelete="true" :showRestore="false" :activeName="activeName"></use_table>
        </el-tab-pane>
        <el-tab-pane label="仓库" name="warehouse">
          <warehouse state="3" :showEdit="true" :showDelete="true" :showRestore="false" :activeName="activeName"></warehouse>
        </el-tab-pane>
      </el-tabs>
    </el-main>
  </el-container>
</template>
<script>
    import {getRequest} from '@/utils/api'
    import WarehouseTable from "./WarehouseTable";
    import WarehouseReceiptTable from "./WarehouseReceiptTable";
    import WarehouseStockTable from "./WarehouseStockTable";
    import UseApplyTable from "./UseApplyTable";
    import WarehouseOutTable from "./WarehouseOutTable";
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
                activeName: 'receipt',
                isAdmin: false
            };
        },
        methods: {
            handleClick(tab, event) {
//        console.log(tab, event);
            }
        },
        components: {
            'warehouse': WarehouseTable,
            'receipt_table': WarehouseReceiptTable,
            'stock_table': WarehouseStockTable,
            'use_table': UseApplyTable,
            'out_table': WarehouseOutTable
            // 'out_table':
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
