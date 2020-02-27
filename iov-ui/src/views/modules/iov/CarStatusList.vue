<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <!-- 查询区域-END -->
    <!-- 操作按钮区域 -->
    <!-- table区域-begin -->
    <div>


      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        @change="handleTableChange">

        <template  slot="status" slot-scope="text, record">
          <span v-if="record.status==1" style="color:#00e41c">在线</span>
          <span v-else style="color:#f22d0e" >离线</span>
        </template >
        <template  slot="breakdown" slot-scope="text, record">
          <span v-if="record.breakdown==null" style="color:#00e41c">正常</span>
          <a-button v-else @click="breakdownShow(record)"  type="primary">查看</a-button>
        </template >

        <template  slot="engine" slot-scope="text, record">
          <a-button @click="engineShow(record)"  type="primary">查看</a-button>
        </template >

        <template  slot="location" slot-scope="text, record">
          <a-button @click="mapShow(record)"  type="primary">查看</a-button>
        </template >



      </a-table>
    </div>
    <car-breakdown-modal ref="breakdown"></car-breakdown-modal>
    <engine-modal ref="engine"></engine-modal>
    <map-modal ref="map"></map-modal>
    <carStatus-modal ref="modalForm" @ok="modalFormOk"></carStatus-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import CarStatusModal from './modules/CarStatusModal'
  import MapModal from './modules/MapModal'
  import EngineModal from './modules/EngineModal'
  import CarBreakdownModal from './modules/CarBreakdownModal'

  export default {
    name: "CarStatusList",
    mixins:[JeecgListMixin],
    components: {
      CarBreakdownModal,
      EngineModal,
      MapModal,
      CarStatusModal
    },
    data () {
      return {
        description: '车况管理页面',
        // 表头
        columns: [
          {
            title:'车牌号',
            align:"center",
            dataIndex: 'car.carNumber'
          },
          {
            title:'状态',
            align:"center",
            dataIndex: 'status',
            scopedSlots: { customRender: 'status' }
          },
          {
            title:'车速',
            align:"center",
            dataIndex: 'speed'
          },
          {
            title:'转速',
            align:"center",
            dataIndex: 'rotation'
          },
          {
            title:'加速度',
            align:"center",
            dataIndex: 'acceleration'
          },
          {
            title:'倾斜角度',
            align:"center",
            dataIndex: 'inclination'
          },
          {
            title:'怠速时间',
            align:"center",
            dataIndex: 'idling'
          },
          {
            title:'冷却液温度',
            align:"center",
            dataIndex: 'coolantTemperature'
          },
          {
            title:'进气温度',
            align:"center",
            dataIndex: 'onflowTemperature'
          },
          {
            title:'机油温度',
            align:"center",
            dataIndex: 'engineOilTemperature'
          },
          {
            title:'故障',
            align:"center",
            dataIndex: 'breakdown',
            scopedSlots: { customRender: 'breakdown' }
          },
          {
            title:'发动机详情',
            align:"center",
            dataIndex: 'engine',
            scopedSlots: { customRender: 'engine' }
          },
          {
            title:'定位',
            align:"center",
            dataIndex: 'location',
            scopedSlots: { customRender: 'location' }
          }
        ],
        url: {
          list: "/iov/carStatus/list",
          delete: "/iov/carStatus/delete",
          deleteBatch: "/iov/carStatus/deleteBatch",
          exportXlsUrl: "/iov/carStatus/exportXls",
          importExcelUrl: "iov/carStatus/importExcel",
        },
        dictOptions:{
        },
      }
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      }
    },
    methods: {
      mapShow(record){
          this.$refs.map.mapShow(record)
      },
      engineShow(record){
        this.$refs.engine.engineShow(record)
      },
      breakdownShow(record){
        this.$refs.breakdown.breakdownShow(record.breakdown);
      },
      initDictConfig(){
      }
       
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>