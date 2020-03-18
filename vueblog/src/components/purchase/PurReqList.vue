<template>
  <el-container>
    <el-main class="main">
      <el-tabs v-model="activeName" @tab-click="handleClick" type="card">
        <el-tab-pane label="采购申请单" name="all">
          <purReq_table state="-1" :showEdit="true" :showDelete="true" :showRestore="false" :activeName="activeName"></purReq_table>
        </el-tab-pane>
        <el-tab-pane label="采购合同" name="contract">
          <contract_table state="1" :showEdit="true" :showDelete="true" :showRestore="false" :activeName="activeName"></contract_table>
        </el-tab-pane>
      </el-tabs>
    </el-main>
  </el-container>
</template>
<script>
    import {getRequest} from '@/utils/api'
    import PurReqTable from "./PurReqTable";
    import PurContractTable from "./PurContractTable";
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
                activeName: 'all',
                isAdmin: false
            };
        },
        methods: {
            handleClick(tab, event) {
//        console.log(tab, event);
            }
        },
        components: {
            'purReq_table': PurReqTable,
            'contract_table': PurContractTable
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
