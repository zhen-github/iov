<template>
  <a-modal
    :title="title"
    :width="width"
    :visible="visible"
    :footer="null"
    @cancel="breakdownCancel"
    :destroyOnClose="true"
  >
    <div>

    </div>
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
        }
      }
    },
    methods: {
      breakdownShow(breakdownCodes) {
        this.visible=true;
        this.parameter.breakdownCodes=breakdownCodes;
        getAction(this.url,filterObj(this.parameter)).then((res) => {
          if (res.success) {
            console.log(res.result.records) ;
          }
          if(res.code===510){
            this.$message.warning(res.message)
          }
        })
      },
      breakdownCancel() {
        this.visible=false;
      }
    }
  }
</script>

<style scoped>

</style>