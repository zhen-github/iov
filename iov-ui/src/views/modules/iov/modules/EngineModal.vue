<template>
  <a-modal
    :title="title"
    :width="width"
    :visible="visible"
    :footer="null"
    @cancel="engineCancel"
    :destroyOnClose="true">

    <a-table
      ref="table"
      size="middle"
      bordered
      rowKey="id"
      :columns="columns"
      :dataSource="record">

    </a-table>

  </a-modal>
</template>

<script>
  export default {
    name: 'EngineModal',
    data () {
      return {
        title: "发动机详情",
        width: 1200,
        visible: false,
        record: [],
        columns: [
          {
            title:'车牌号',
            align:"center",
            dataIndex: 'car.carNumber'
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
          }
        ],
      }
    },
    methods: {
      engineShow(record) {
        this.visible=true;
        this.record.push(record);
      },
      engineCancel() {
        this.record=[];
        this.visible=false;
      }
    }
  }
</script>

<style scoped>

</style>