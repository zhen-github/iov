<template>
  <a-modal
    :width="modalWidth"
    :visible="visible"
    title="车队搜索"
    :confirmLoading="confirmLoading"
    @ok="handleSubmit"
    @cancel="handleCancel"
    cancelText="关闭"
    >
    <!--部门树-->
    <a-radio-group  v-model="motorcadeKey">

      <a-radio  :style="radioStyle" v-for="(item,index) in dataSource" :value="item.id"  :key="index" v-if="item.orgCode!=motorcadeCode">{{item.departName}}</a-radio>
    </a-radio-group>
  </a-modal>
</template>

<script>
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import { getAction } from '@/api/manage'

  export default {
    name: "MotorcadeCarWindow",
    components: {

    },
    mixins:[JeecgListMixin],
    data () {
      return {
        modalWidth:300,
        motorcadeKey:"",
        motorcadeCode:"",
        carId:"",
        title:"操作",
        visible: false,
        confirmLoading: false,
        columns:[],
        url: {
          list: "/sys/sysDepart/motorcadeList",
          updateUrl:"/iov/car/updateMotorcadeById",
        },
        radioStyle: {
          display: 'block',
          height: '30px',
          lineHeight: '30px',
        },
      }
    },
    methods: {
      show (carId,motorcadeCode) {
        this.carId = carId;
        this.motorcadeCode=motorcadeCode;
        this.visible = true;
      },
      close () {
        this.visible = false;
        this.motorcadeKey="";
        this.motorcadeCode="";
        this.carId="";
      },
      handleSubmit () {
        if (this.motorcadeKey!=""){
          getAction(this.url.updateUrl,{carId:this.carId,motorcadeId:this.motorcadeKey}).then((res)=>{
            if(res.success){
              this.$emit("updataMotorcade");
            }else{
              console.log(res.message);
            }
          }).finally(() => {
            this.confirmLoading = false;
          });
        }
        this.close();
      },
      handleCancel () {
        this.close()
      }

    }

  }
</script>

<style scoped>
  .ant-table-tbody .ant-table-row td{
    padding-top:10px;
    padding-bottom:10px;
  }
</style>