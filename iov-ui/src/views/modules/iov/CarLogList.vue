<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :md="12" :sm="16">
            <a-form-item label="开始时间">
              <j-date :show-time="true" date-format="YYYY-MM-DD " placeholder="请选择开始时间" class="query-group-cust" v-model="queryParam.createTime_begin"></j-date>
              <span class="query-group-split-cust"></span>
              <j-date :show-time="true" date-format="YYYY-MM-DD " placeholder="请选择结束时间" class="query-group-cust" v-model="queryParam.createTime_end"></j-date>
            </a-form-item>
          </a-col>
          <a-col :md="12" :sm="16">
            <a-form-item label="结束时间">
              <j-date :show-time="true" date-format="YYYY-MM-DD" placeholder="请选择开始时间" class="query-group-cust" v-model="queryParam.updateTime_begin"></j-date>
              <span class="query-group-split-cust"></span>
              <j-date :show-time="true" date-format="YYYY-MM-DD" placeholder="请选择结束时间" class="query-group-cust" v-model="queryParam.updateTime_end"></j-date>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>
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
          <span v-if="record.status==0" >已完成</span>
          <span v-else  >行驶中</span>
        </template >

        <template  slot="trail" slot-scope="text, record">
          <a-button @click="trailShow(record)"  type="primary">查看</a-button>
        </template >

        <template  slot="log" slot-scope="text, record">
          <a-button @click="logShow(record)"  type="primary">查看</a-button>
        </template >






      </a-table>
    </div>
    <trail-modal ref="trail"></trail-modal>
    <car-status-log-modal ref="log"></car-status-log-modal>
    <carLog-modal ref="modalForm" @ok="modalFormOk"></carLog-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import CarLogModal from './modules/CarLogModal'
  import JDate from '@/components/jeecg/JDate.vue'
  import CarStatusLogModal from './modules/CarStatusLogModal'
  import TrailModal from './modules/TrailModal'

  export default {
    name: "CarLogList",
    mixins:[JeecgListMixin],
    components: {
      TrailModal,
      CarStatusLogModal,
      JDate,
      CarLogModal
    },
    data () {
      return {
        description: '行车记录管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
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
            title:'开始时间',
            align:"center",
            dataIndex: 'createTime'
          },
          {
            title:'结束时间',
            align:"center",
            dataIndex: 'updateTime'
          },
          {
            title: '轨迹',
            dataIndex: 'trail',
            align:"center",
            scopedSlots: { customRender: 'trail' }
          },
          {
            title: '记录',
            dataIndex: 'log',
            align:"center",
            scopedSlots: { customRender: 'log' }
          }
        ],
        url: {
          list: "/iov/carLog/list",
          delete: "/iov/carLog/delete",
          deleteBatch: "/iov/carLog/deleteBatch",
          exportXlsUrl: "/iov/carLog/exportXls",
          importExcelUrl: "iov/carLog/importExcel",
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
      trailShow(record){
        this.$refs.trail.trailShow(record.id);
      },
      logShow(record){
        this.$refs.log.showLog(record.id);
      },
      initDictConfig(){
      }
       
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>