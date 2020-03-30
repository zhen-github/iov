<template>
  <a-modal
    :width="modalWidth"
    :visible="visible"
    title="车队搜索"
    :confirmLoading="confirmLoading"
    @ok="handleSubmit"
    @cancel="handleCancel"
    cancelText="关闭"
    wrapClassName="ant-modal-cust-warp"
    >
    <!--部门树-->
    <template>
      <a-form :form="form">
      <a-form-item :labelCol="labelCol" :wrapperCol="wrapperCol" label="上级部门">
      <a-tree
        multiple
        treeCheckable="tree"
        checkable
        :checkedKeys="checkedKeys"
        allowClear="true"
        :checkStrictly="true"
        @check="onCheck"
        :dropdownStyle="{maxHeight:'200px',overflow:'auto'}"
        :treeData="departTree"
        placeholder="请选择上级部门"
        >
      </a-tree>
      </a-form-item>
      </a-form>
    </template>
  </a-modal>
</template>

<script>
  import pick from 'lodash.pick'
  import { getAction } from '@/api/manage'
  import { queryIdTree } from '@/api/api'
  export default {
    name: "MotorcadeWindow",
    components: {

    },
    data () {
      return {
        checkedKeys:[],
        carId:"",
        motorcadeId:"",
        modalWidth:400,
        departTree:[],
        title:"操作",
        visible: false,
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        headers:{},
        form:this.$form.createForm(this),
        url: {
          updateUrl:"/iov/car/updateMotorcadeById",
        },
      }
    },
    methods: {
      add (motorcadeKey,carId) {
        this.checkedKeys.push(motorcadeKey);
        this.carId = carId;
        this.queryDepartTree();
        this.visible = true;
      },
      close () {
        this.$emit('close');
        this.visible = false;
        this.carId="";
        this.motorcadeId="";
        this.checkedKeys = [];
      },
      handleSubmit () {
        if (this.checkedKeys.length>1)
          console.log("错误")
        else {
          if(this.checkedKeys.length==1)
            this.motorcadeId = this.checkedKeys[0];
          else
            this.motorcadeId="";
          getAction(this.url.updateUrl,{carId:this.carId,motorcadeId:this.motorcadeId}).then((res)=>{
            if(res.success){

            }else{
              console.log(res.message);
            }
          }).finally(() => {
            this.confirmLoading = false;

          })
        }
        this.$emit("updataMotorcade");
        this.close();
      },
      handleCancel () {
        this.close()
      },

      // 选择部门时作用的API
      onCheck(checkedKeys, info){
        if (checkedKeys.checked.length==0)
          this.checkedKeys = [];
        for (let i = 0; i < checkedKeys.checked.length; i++) {
          if (!this.checkedKeys.includes(checkedKeys.checked[i])){
            this.checkedKeys = [];
            this.checkedKeys.push(checkedKeys.checked[i]);
            break;
          }
        }
      },
      queryDepartTree(){
        queryIdTree().then((res)=>{
          if(res.success){
            this.departTree = res.result;
          }
        })
      },
      modalFormOk(){

      }
      },
  }
</script>

<style scoped>
  .ant-table-tbody .ant-table-row td{
    padding-top:10px;
    padding-bottom:10px;
  }
</style>