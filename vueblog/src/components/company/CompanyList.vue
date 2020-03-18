<template>
  <el-container>
    <el-main class="main">
      <el-tabs v-model="activeName" @tab-click="handleClick" type="card">
        <el-tab-pane label="员工管理" name="employee">
          <employee_table state="-1" :showEdit="true" :showDelete="true" :showRestore="false" :activeName="activeName"></employee_table>
        </el-tab-pane>
        <el-tab-pane label="部门管理" name="dept">
          <dept_table state="1" :showEdit="true" :showDelete="true" :showRestore="false" :activeName="activeName"></dept_table>
        </el-tab-pane>

      </el-tabs>
    </el-main>
  </el-container>
</template>
<script>
    import {getRequest} from '@/utils/api'
    import DepartmentTable from "./DepartmentTable";
    import EmployeeTable from "./EmployeeTable";
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
                activeName: 'employee',
                isAdmin: false
            };
        },
        methods: {
            handleClick(tab, event) {
//        console.log(tab, event);
            }
        },
        components: {
            'dept_table': DepartmentTable,
            'employee_table': EmployeeTable
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
