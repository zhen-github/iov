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


        <template  slot="sysOrgCode" slot-scope="text, record">
            <a-button @click="updataMotorcade(record.id,record.sysOrgCode)">变更车队</a-button>
        </template >
        <template  slot="action" slot-scope="text, record">
            <a-button @click="deleteMotorcade(record.id)">移除车辆</a-button>
        </template >


      </a-table>
    </div>
    <motorcade-car-window ref="motorcade"  @updataMotorcade="onUpdataMotorcade"></motorcade-car-window>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import MotorcadeWindow from './MotorcadeWindow'
  import { getAction } from '@/api/manage'
  import MotorcadeCarWindow from './MotorcadeCarWindow'

  export default {
    name: "MotorcadeCarList",
    mixins:[JeecgListMixin],
    components: {
      MotorcadeCarWindow,
      MotorcadeWindow,
    },
    props:["parentQueryParam"],
    data () {
      return {
        description: '车辆管理管理页面',
        // 表头
        motorcadeId: '',
        columns: [
          {
            title:'车牌号',
            align:"center",
            dataIndex: 'carNumber'
          },
          {
            title:'车辆品牌',
            align:"center",
            dataIndex: 'brand'
          },
          {
            title:'车队',
            align:"center",
            dataIndex:'sysOrgCode',
            scopedSlots: { customRender: 'sysOrgCode' }
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
          motorcadeUrl:"/sys/sysDepart/getDepart",
          deleteMotorcadeUrl:"/iov/car/updateMotorcadeById"
        },
        dictOptions:{
        },
      }
    },
    computed: {
    },
    methods: {
      initData(){
        if (this.parentQueryParam!=null){
          this.queryParam=this.parentQueryParam;
        }
      },
      initDictConfig(){
      },
      deleteMotorcade(carId){
        getAction(this.url.deleteMotorcadeUrl,{carId:carId,motorcadeId:""}).then((res)=>{
          if(res.success){
            this.loadData();
          }else{
            console.log(res.message);
          }
        })
      },
      updataMotorcade(carId,motorcadeCode){
        console.log("ccc")
        this.$refs.motorcade.show(carId,motorcadeCode);
      },
      onUpdataMotorcade(){
        this.loadData();
      }
       
    },
    watch:{
      parentQueryParam(val){
        this.loadData();
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>