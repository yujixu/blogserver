import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Home from '@/components/Home'
import ArticleList from '@/components/blog/ArticleList'
import CateMana from '@/components/CateMana'
import DataCharts from '@/components/DataCharts'
import PostArticle from '@/components/blog/PostArticle'
import UserMana from '@/components/UserMana'
import PurReqList from "@/components/purchase/PurReqList";
import AddPurReq from "../components/purchase/AddPurReq";
import BaseDataList from "../components/basedata/BaseDataList";
import AddMaterial from "../components/basedata/AddMaterial";
import AddMaterialType from "../components/basedata/AddMaterialType";
import AddBaseUnit from "../components/basedata/AddBaseUnit";
import CompanyList from "../components/company/CompanyList";
import AddDepartment from "../components/company/AddDepartment";
import AddEmployee from "../components/company/AddEmployee";
import WarehouseList from "../components/warehouse/WarehouseList";
import AddWarehouse from "../components/warehouse/AddWarehouse";
import AddPurContract from "../components/purchase/AddPurContract";
import AddSupplier from "../components/basedata/AddSupplier";
import AddWarehouseReceipt from "../components/warehouse/AddWarehouseReceipt";
import AddUseApply from "../components/warehouse/AddUseApply";
import AddWarehouseOut from "../components/warehouse/AddWarehouseOut";
import PlantList from "../components/plant/PlantList"
import AreaList from "../components/area/AreaList"
import BuildingTable from "../components/business/BuildingTable";

import "../assets/icon/iconfont.css"
import AddPlantType from "../components/plant/AddPlantType";
import AddPlantUnit from "../components/plant/AddPlantUnit";
import RoomResourceTable from "../components/business/RoomResourceTable";
import CompanyTable from "../components/business/CompanyTable";
import AddCompany from "../components/business/AddCompany";
import QueryCompany from "../components/business/QueryCompany";
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: '登录',
      hidden: true,
      component: Login
    }, {
      path: '/home',
      name: '',
      component: Home,
      hidden: true
    },{
      path: '/home',
      component: Home,
      name: '基础管理',
      iconCls: 'fa fa-file-text-o',
      children: [
        {
          path: '/articleList',
          name: '公告列表',
          component: ArticleList,
          meta: {
            keepAlive: true
          }
        }, {
          path: '/companyList',
          name: '公司管理',
          component: CompanyList,
          meta: {
            keepAlive: true
          }
        },{
          path: '/baseDataList',
          name: '基础数据',
          component: BaseDataList,
          meta: {
            keepAlive: true
          }
        }, {
          path: '/addMaterial',
          name: '物料',
          component: AddMaterial,
          hidden: true,
          meta: {
            keepAlive: false
          }
        }, {
          path: '/addSupplier',
          name: '供应商',
          component: AddSupplier,
          hidden: true,
          meta: {
            keepAlive: false
          }
        }, {
          path: '/addMaterialType',
          name: '物料规格',
          component: AddMaterialType,
          hidden: true,
          meta: {
            keepAlive: false
          }
        }, {
          path: '/addBaseUnit',
          name: '计量单位',
          component: AddBaseUnit,
          hidden: true,
          meta: {
            keepAlive: false
          }
        }, {
          path: '/addDepartment',
          name: '部门',
          component: AddDepartment,
          hidden: true,
          meta: {
            keepAlive: false
          }
        }, {
          path: '/addEmployee',
          name: '员工',
          component: AddEmployee,
          hidden: true,
          meta: {
            keepAlive: false
          }
        }, {
          path: '/editBlog',
          name: '编辑公告',
          component: PostArticle,
          hidden: true,
          meta: {
            keepAlive: false
          }
        }
      ]
    }, {
      path: '/home',
      component: Home,
      name: '综合管理',
      iconCls: 'icon-zhyqdingdan',
      children: [
        {
          path: '/purReqList',
          name: '采购管理',
          component: PurReqList,
          iconCls: 'icon-zhyqdingdanliebiao',
        }, {
          path: '/warehouseList',
          name: '仓库管理',
          component: WarehouseList,
          meta: {
            keepAlive: false
          }
        }, {
          path: '/addPurReq',
          name: '采购申请单',
          component: AddPurReq,
          hidden: true,
          meta: {
            keepAlive: false
          }
        }, {
          path: '/addPurContract',
          name: '采购合同',
          component: AddPurContract,
          hidden: true,
          meta: {
            keepAlive: false
          }
        }, {
          path: '/addUseApply',
          name: '领用申请',
          component: AddUseApply,
          hidden: true,
          meta: {
            keepAlive: false
          }
        }, {
          path: '/addWarehouse',
          name: '仓库',
          component: AddWarehouse,
          hidden: true,
          meta: {
            keepAlive: false
          }
        }, {
          path: '/addWarehouseReceipt',
          name: '采购入库',
          component: AddWarehouseReceipt,
          hidden: true,
          meta: {
            keepAlive: false
          }
        }
        , {
          path: '/addWarehouseOut',
          name: '出库单',
          component: AddWarehouseOut,
          hidden: true,
          meta: {
            keepAlive: false
          }
        }
      ]
    }, {
      path: '/home',
      component: Home,
      name: '园林管理',
      iconCls: 'icon-zhyqtree',
      children: [
        {
          path: '/area',
          name: '区域管理',
          component: AreaList
        }, {
          path: '/plantList',
          name: '植被管理',
          component: PlantList
        }, {
          path: '/addPlantType',
          name: '植被种类',
          hidden: true,
          component: AddPlantType
        }, {
          path: '/addPlantUnit',
          name: '植被单位',
          hidden: true,
          component: AddPlantUnit
        }
      ]
    }, {
      path: '/home',
      component: Home,
      name: '招商租赁',
      iconCls: 'icon-zhyqbus',
      children: [
        {
          path: '/building',
          name: '楼栋管理',
          component: BuildingTable
        },{
          path: '/business',
          name: '房源信息管理',
          component: RoomResourceTable
        }, {
          path: '/company',
          name: '企业信息管理',
          component: CompanyTable
        }, {
          path: '/addCompany',
          name: '企业信息',
          hidden: true,
          component: AddCompany
        }
      ]
    }, {
      path: '/home',
      component: Home,
      name: '用户管理',
      children: [
        {
          path: '/user',
          iconCls: 'icon-zhyqyonghuguanli',
          name: '用户管理',
          component: UserMana
        }
      ]
    }, {
      path: '/home',
      component: Home,
      name: '栏目管理',
      children: [
        {
          path: '/cateMana',
          iconCls: 'fa fa-reorder',
          name: '栏目管理',
          component: CateMana
        }
      ]
    }, {
      path: '/home',
      component: Home,
      name: '数据统计',
      iconCls: 'fa fa-bar-chart',
      children: [
        {
          path: '/charts',
          iconCls: 'fa fa-bar-chart',
          name: '数据统计',
          component: DataCharts
        }
      ]
    }
  ]
})
