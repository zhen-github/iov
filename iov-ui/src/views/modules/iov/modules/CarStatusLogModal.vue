<template>
  <a-modal
    :title="title"
    :width="width"
    :visible="visible"
    :footer="null"
    @cancel="close"
    :destroyOnClose="true">

    <a-table
      ref="table"
      size="middle"
      bordered
      rowKey="id"
      :columns="columns"
      :dataSource="data"
    >

    </a-table>


  </a-modal>
</template>

<script>
  import { getAction } from '@/api/manage'
  import { filterObj } from '@/utils/util';


  export default {
    name: "CarStatusLogModal",
    data () {
      return {
        title:"记录",
        width:1200,
        visible: false,
        description: '行车状态记录管理页面',
        // 表头
        columns: [
          {
            title:'时间',
            align:"center",
            dataIndex: 'createTime',
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
            dataIndex: 'breakdown'
          },
          {
            title:'催化剂温度',
            children:[
              {
                title:'缸组1',
                children:[
                  {
                    title:'传感器1',
                    align:"center",
                    dataIndex: 'catalyzerTemOneOne'
                  },
                  {
                    title:'传感器2 ',
                    align:"center",
                    dataIndex: 'catalyzerTemOneTwo'
                  }
                ]
              },
              {
                title:'缸组2',
                children:[
                  {
                    title:'传感器1',
                    align:"center",
                    dataIndex: 'catalyzerTemTwoOne'
                  },
                  {
                    title:'传感器2',
                    align:"center",
                    dataIndex: 'catalyzerTemTwoTwo'
                  },
                ]
              }
            ]
          },
          {
            title:'氧传感器电压',
            children:[
              {
                title:'缸组1',
                children:[
                  {
                    title:'传感器1',
                    align:"center",
                    dataIndex: 'osvOneOne'
                  },
                  {
                    title:'传感器2',
                    align:"center",
                    dataIndex: 'osvOneTwo'
                  },
                  {
                    title:'传感器3',
                    align:"center",
                    dataIndex: 'osvOneThere'
                  },
                  {
                    title:'传感器4',
                    align:"center",
                    dataIndex: 'osvOneFour'
                  },
                ]
              },
              {
                title:'缸组2',
                children:[
                  {
                    title:'传感器1',
                    align:"center",
                    dataIndex: 'osvTwoOne'
                  },
                  {
                    title:'传感器2',
                    align:"center",
                    dataIndex: 'osvTwoTwo'
                  },
                  {
                    title:'传感器3',
                    align:"center",
                    dataIndex: 'osvTwoThere'
                  },
                  {
                    title:'传感器4',
                    align:"center",
                    dataIndex: 'osvTwoFour'
                  },
                ]
              },
            ]
          },
          {
            title:'燃油修正',
            children:[
              {
                title:'缸组1',
                children:[

                  {
                    title:'短期 ',
                    align:"center",
                    dataIndex: 'stfcOne'
                  },
                  {
                    title:'长期',
                    align:"center",
                    dataIndex: 'ltfcOne'
                  }
                ]
              },
              {
                title:'缸组2',
                children:[
                  {
                    title:'短期',
                    align:"center",
                    dataIndex: 'stfcTwo'
                  },
                  {
                    title:'长期',
                    align:"center",
                    dataIndex: 'ltfcTwo'
                  }
                ]
              }
            ]
          }
        ],
        url: "/iov/carStatusLog/list",
        parameter: {
          carLogId: "",
        },
        data:[],
      }
    },
    methods: {
      showLog(logId){
        this.visible=true;
        this.parameter.carLogId=logId;
        getAction(this.url,filterObj(this.parameter)).then((res) => {
          if (res.success) {
            this.data=res.result.records;
          }
          if(res.code===510){
            this.$message.warning(res.message)
          }
        })
      },
      close () {
        this.data=[];
        this.visible = false;
      },
    }
  }
</script>