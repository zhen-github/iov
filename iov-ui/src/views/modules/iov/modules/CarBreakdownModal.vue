<template>
  <a-modal
    :title="title"
    :width="width"
    :visible="visible"
    :footer="null"
    @cancel="breakdownCancel"
    :destroyOnClose="true"
  >
    <a-list itemLayout="horizontal" :dataSource="breakdownData">
      <a-list-item slot="renderItem" slot-scope="item">
        <a-list-item-meta
          :description="item.mean"
        >
          <a slot="title">错误代码：{{item.breakdownCode}}</a>
        </a-list-item-meta>
      </a-list-item>
    </a-list>
  </a-modal>
</template>

<script>

  import { getAction } from '@/api/manage'
  import { filterObj } from '@/utils/util';

  export default {
    name: 'CarBreakdownModal',
    data() {
      return {
        title: "发动机详情",
        width: 800,
        visible: false,
        url: "/iov/breakdown/listByCode",
        parameter: {
          breakdownCodes: "",
        },
        breakdownData:[]
      }
    },
    methods: {
      breakdownShow(breakdownCodes) {
        this.visible=true;
        this.parameter.breakdownCodes=breakdownCodes;
        getAction(this.url,filterObj(this.parameter)).then((res) => {
          if (res.success) {
            this.breakdownData=res.result;
            console.log(res.result) ;
          }
          if(res.code===510){
            this.$message.warning(res.message)
          }
        })
      },
      breakdownCancel() {
        this.breakdownData=[];
        this.visible=false;
      }
    }
  }
</script>

<style scoped>

</style>