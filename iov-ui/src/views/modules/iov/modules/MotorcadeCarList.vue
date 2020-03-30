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
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('车辆管理')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
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

        <template  slot="sysOrgCode" slot-scope="text, record">
          <a-input-search
            placeholder="点击右侧按钮加入车队"
            v-model="record.sysOrgCode_dictText"
            disabled
            @search="onSearch(record.sysOrgCode,record.id)">
            <a-button slot="enterButton" icon="search">选择</a-button>
          </a-input-search>
        </template >

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
    <motorcade-window ref="motorcade" @updataMotorcade="onUpdataMotorcade"></motorcade-window>
    <car-modal ref="modalForm" @ok="modalFormOk"></car-modal>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import CarModal from './modules/CarModal'
  import MotorcadeWindow from './modules/MotorcadeWindow'
  import { getAction } from '@/api/manage'

  export default {
    name: "CarList",
    mixins:[JeecgListMixin],
    components: {
      MotorcadeWindow,
      CarModal
    },
    data () {
      return {
        description: '车辆管理管理页面',
        // 表头
        motorcadeId: '',
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
            title:'mac(暂定 车辆唯一标识)',
            align:"center",
            dataIndex: 'mac'
          },
          {
            title:'车辆品牌',
            align:"center",
            dataIndex: 'brand'
          },
          {
            title:'车牌号',
            align:"center",
            dataIndex: 'carNumber'
          },
          {
            title:'车队',
            align:"center",
            dataIndex:'sysOrgCode',
            scopedSlots: { customRender: 'sysOrgCode' }
          },
          {
            title:'备注',
            align:"center",
            dataIndex: 'remark'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/iov/car/list",
          delete: "/iov/car/delete",
          deleteBatch: "/iov/car/deleteBatch",
          exportXlsUrl: "/iov/car/exportXls",
          importExcelUrl: "iov/car/importExcel",
          motorcadeUrl:"/sys/sysDepart/getDepart"
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
      },
      onSearch(motorcadeKey,carId){
        if(motorcadeKey!=null)
        getAction(this.url.motorcadeUrl,{orgCode:motorcadeKey}).then((res)=>{
          if(res.success){
            this.$refs.motorcade.add(res.result.id,carId);
          }else{
            console.log(res.message);
          }
        })
        else
          this.$refs.motorcade.add(null,carId);
      },
      onUpdataMotorcade(){
        this.loadData();
      }
       
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>