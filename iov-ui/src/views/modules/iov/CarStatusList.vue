<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">

        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->
    
    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{fixed:true,selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无此图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="图片不存在" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无此文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="uploadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
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
    </div>

    <carStatus-modal ref="modalForm" @ok="modalFormOk"></carStatus-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import CarStatusModal from './modules/CarStatusModal'

  export default {
    name: "CarStatusList",
    mixins:[JeecgListMixin],
    components: {
      CarStatusModal
    },
    data () {
      return {
        description: '车况管理页面',
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
            dataIndex: 'status'
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
            title:'纬度',
            align:"center",
            dataIndex: 'latitude'
          },
          {
            title:'经度',
            align:"center",
            dataIndex: 'longitude'
          },
          {
            title:'故障',
            align:"center",
            dataIndex: 'breakdown'
          },
          {
            title:'催化剂温度(缸组1,传感器1) ',
            align:"center",
            dataIndex: 'catalyzerTemOneOne'
          },
          {
            title:'催化剂温度(缸组2,传感器1) ',
            align:"center",
            dataIndex: 'catalyzerTemTwoOne'
          },
          {
            title:'催化剂温度(缸组1,传感器2) ',
            align:"center",
            dataIndex: 'catalyzerTemOneTwo'
          },
          {
            title:'催化剂温度(缸组2,传感器2) ',
            align:"center",
            dataIndex: 'catalyzerTemTwoTwo'
          },
          {
            title:'氧传感器电压(缸组1,传感器1)',
            align:"center",
            dataIndex: 'osvOneOne'
          },
          {
            title:'氧传感器电压(缸组1,传感器2)',
            align:"center",
            dataIndex: 'osvOneTwo'
          },
          {
            title:'氧传感器电压(缸组1,传感器3)',
            align:"center",
            dataIndex: 'osvOneThere'
          },
          {
            title:'氧传感器电压(缸组1,传感器4)',
            align:"center",
            dataIndex: 'osvOneFour'
          },
          {
            title:'氧传感器电压(缸组2,传感器1)',
            align:"center",
            dataIndex: 'osvTwoOne'
          },
          {
            title:'氧传感器电压(缸组2,传感器2)',
            align:"center",
            dataIndex: 'osvTwoTwo'
          },
          {
            title:'氧传感器电压(缸组2,传感器3)',
            align:"center",
            dataIndex: 'osvTwoThere'
          },
          {
            title:'氧传感器电压(缸组2,传感器4)',
            align:"center",
            dataIndex: 'osvTwoFour'
          },
          {
            title:'短期燃油修正(缸组1) ',
            align:"center",
            dataIndex: 'stfcOne'
          },
          {
            title:'长期燃油修正(缸组1)',
            align:"center",
            dataIndex: 'ltfcOne'
          },
          {
            title:'短期燃油修正(缸组2)',
            align:"center",
            dataIndex: 'stfcTwo'
          },
          {
            title:'长期燃油修正(缸组2)',
            align:"center",
            dataIndex: 'ltfcTwo'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' }
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
      initDictConfig(){
      }
       
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>