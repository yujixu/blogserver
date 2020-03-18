<template>
  <el-container>
    <el-main class="main">
      <el-tabs v-model="activeName" @tab-click="handleClick" type="card">
        <el-tab-pane label="植被信息" name="plant">
          <plant_table state="1" :showEdit="true" :showDelete="true" :showRestore="false" :activeName="activeName"></plant_table>
        </el-tab-pane>
        <el-tab-pane label="植物种类" name="type">
          <type_table state="-1" :showEdit="true" :showDelete="true" :showRestore="false" :activeName="activeName"></type_table>
        </el-tab-pane>
        <el-tab-pane label="植物单位" name="unit">
          <unit_table state="-3" :showEdit="true" :showDelete="true" :showRestore="false" :activeName="activeName"></unit_table>
        </el-tab-pane>
      </el-tabs>
    </el-main>
  </el-container>
</template>
<script>
    import {getRequest} from '@/utils/api'
    import PlantTable from "./PlantTable";
    import PlantTypeTable from "./PlantTypeTable";
    import PlantUnitTable from "./PlantUnitTable";

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
                activeName: 'plant',
                isAdmin: false
            };
        },
        methods: {
            handleClick(tab, event) {
//        console.log(tab, event);
            }
        },
        components: {
            'plant_table': PlantTable,
            'type_table': PlantTypeTable,
            'unit_table': PlantUnitTable
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
