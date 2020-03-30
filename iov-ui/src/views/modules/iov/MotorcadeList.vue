<template>
  <a-row :gutter="8">
    <a-col :md="8" :sm="24">

      <a-card title="车队管理" :bordered="false">
        <div class="table-operator">
          <a-button @click="handleAdd" type="primary" style="float:right" icon="plus">新增</a-button>
        </div>
        <a-input-search @search="onSearch" style="width:100%;margin-top: 10px" placeholder="请输入部门名称"/>
        <a-table
          ref="table"
          size="middle"
          bordered
          rowKey="id"
          :columns="columns"
          :dataSource="dataSource"
          :pagination="ipagination"
          :loading="loading">

          <template  slot="departName" slot-scope="text, record">
            <span v-if="record.orgType!=1">{{record.departName}}</span>
            <span v-else>未加入车队车辆</span>
          </template >

          <template  slot="details" slot-scope="text, record">
            <a-button @click="detailsShow(record.orgCode,record.departName)"  type="primary">查看</a-button>
          </template >


          <span slot="action" slot-scope="text, record" v-if="record.orgType!=1">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>
        </a-table>
    <motorcade-modal ref="motorcade"  @updataMotorcade="onUpdataMotorcade"></motorcade-modal>
      </a-card>
    </a-col>
    <a-col :md="16" :sm="24">
      <a-card :title="motorcadeName"  :tabList="tabList" :activeTabKey="titleKey"
               @tabChange="key => onTabChange(key)" :bordered="false">
        <div v-if="titleKey==='tab1'">
          <MotorcadeCarList v-if="motorcadeCode!==null" :parentQueryParam="{sysOrgCode:this.motorcadeCode}"></MotorcadeCarList>
        </div>
        <div v-if="titleKey==='tab2'">
          <CarStatusList  v-if="motorcadeCode!==null" :parentQueryParam="{sysOrgCode:this.motorcadeCode}"></CarStatusList>
        </div>
        <div v-if="titleKey==='tab3'">
          <CarLogList v-if="motorcadeCode!==null" :parentQueryParam="{motorcadeCode:this.motorcadeCode}" ></CarLogList>
        </div>
      </a-card>
    </a-col>
  </a-row>
</template>

<script>
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import CarStatusList from './CarStatusList'
  import CarLogList from './CarLogList'
  import MotorcadeCarList from './modules/MotorcadeCarList'
  import MotorcadeModal from './modules/MotorcadeModal'

  export default {
    name: 'MotorcadeList',
    components: { MotorcadeModal, MotorcadeCarList, CarLogList, CarStatusList },
    mixins:[JeecgListMixin],
    comments: [],
    data () {
      return {
        motorcadeName:'车辆详情',
        titleKey: 'tab1',
        motorcadeCode: null,
        tabList: [
          {
            key: 'tab1',
            tab: '车辆详情',
          },
          {
            key: 'tab2',
            tab: '车辆状态',
          },
          {
            key: 'tab3',
            tab: '车辆记录',
          },
        ],
        columns: [
          {
            title: '车队名',
            dataIndex: 'departName',
            align:"center",
            scopedSlots: { customRender: 'departName' }
          },
          {
            title:'详情',
            align:"center",
            dataIndex: 'details',
            scopedSlots: { customRender: 'details' }
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/sys/sysDepart/motorcadeList",
          delete: '/sys/sysDepart/delete',
          edit: '/sys/sysDepart/edit',
          deleteBatch: '/sys/sysDepart/deleteBatch',
          exportXlsUrl: "sys/sysDepart/exportXls",
          importExcelUrl: "sys/sysDepart/importExcel",
        }
      }
    },
    methods:{
      initDictConfig(){
      },
      onTabChange(key) {
        this.titleKey=key;

      },
      detailsShow(code,motorcadeName){
        this.motorcadeCode=code;
        this.motorcadeName=motorcadeName;
      },
      handleAdd() {
        this.$refs.motorcade.add();
      },
      handleEdit(record){
        this.$refs.motorcade.edit(record);
      },
      onSearch(value) {
        this.queryParam={departName:value};
        this.loadData();
      },
      onUpdataMotorcade(){
        this.loadData();
      }

    },


  }
</script>

<style scoped>

</style>