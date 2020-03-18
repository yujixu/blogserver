<template>
  <el-container>
    <el-main class="main">
      <el-tabs v-model="activeName" @tab-click="handleClick" type="card">
        <el-tab-pane label="室外区域" name="outside">
          <out_table state="1" :showEdit="true" :showDelete="true" :showRestore="false" :activeName="activeName"></out_table>
        </el-tab-pane>
        <el-tab-pane label="室内区域" name="inside">
          <in_table state="-1" :showEdit="true" :showDelete="true" :showRestore="false" :activeName="activeName"></in_table>
        </el-tab-pane>
      </el-tabs>
    </el-main>
  </el-container>
</template>
<script>
    import {getRequest} from '@/utils/api'
    import OutsideTable from "./OutsideTable";
    import InsideTable from "./InsideTable";
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
                activeName: 'outside',
                isAdmin: false
            };
        },
        methods: {
            handleClick(tab, event) {
//        console.log(tab, event);
            }
        },
        components: {
            'out_table': OutsideTable,
            'in_table': InsideTable
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
