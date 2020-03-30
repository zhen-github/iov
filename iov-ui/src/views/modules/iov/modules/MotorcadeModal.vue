<template>
  <a-modal
    :title="title"
    :width="800"
    :ok=false
    :visible="visible"
    :confirmLoading="confirmLoading"
    :okButtonProps="{ props: {disabled: disableSubmit} }"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">

    <a-spin :spinning="confirmLoading">
      <a-form :form="form">
        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="机构名称"
          :hidden="false"
          hasFeedback >
          <a-input id="departName" placeholder="请输入机构/部门名称" v-decorator="['departName', validatorRules.departName ]"/>
        </a-form-item>
        <a-form-item style="display:none"
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="机构类型">
            <a-input-number v-decorator="[ 'orgCategory',{'initialValue':2}]" />
        </a-form-item>

        <a-form-item style="display:none"
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="排序">
          <a-input-number v-decorator="[ 'departOrder',{'initialValue':0}]" />
        </a-form-item>

        <a-form-item
          :labelCol="labelCol"
          :wrapperCol="wrapperCol"
          label="备注">
          <a-textarea placeholder="请输入备注" v-decorator="['memo', {}]"  />
        </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>
  import { httpAction } from '@/api/manage'
  import { queryIdTree } from '@/api/api'
  import pick from 'lodash.pick'
  import ATextarea from 'ant-design-vue/es/input/TextArea'
  export default {
    name: "MotorcadeModal",
    components: { ATextarea },
    data () {
      return {
        motorcadeData:null,
        departName:"",
        title:"操作",
        visible: false,
        disableSubmit:false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },

        confirmLoading: false,
        form: this.$form.createForm(this),
        validatorRules:{
        departName:{rules: [{ required: true, message: '请输入机构/部门名称!' }]},
         mobile:{rules: [{validator:this.validateMobile}]}
        },
        url: {
          add: "/sys/sysDepart/addMotorcade",
          edit: "/sys/sysDepart/edit",
        },
        dictDisabled:true,
      }
    },
    created () {
    },
    methods: {

      add () {
        this.edit({});
      },
      edit (record) {
          this.form.resetFields();
          this.model = Object.assign({}, record);
          this.motorcadeData=record;
          this.visible = true;
          this.$nextTick(() => {
            this.form.setFieldsValue(pick(this.model,'orgCategory','departName','departOrder','memo'))
          });
      },
      close () {
        this.motorcadeData=null;
        this.disableSubmit = false;
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let formData = Object.assign(this.model, values);
            let dataUrl=this.url.add;
            let type="post";
            if (this.motorcadeData!=null){
              this.motorcadeData.orgCategory=formData.orgCategory;
              this.motorcadeData.departName=formData.departName;
              this.motorcadeData.departOrder=formData.departOrder;
              this.motorcadeData.memo=formData.memo;
              formData=this.motorcadeData;
              dataUrl=this.url.edit;
              type="put";
            }
            httpAction(dataUrl,formData,type).then((res)=>{
              if(res.success){

                this.$emit("updataMotorcade");
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })

          }
        })
      },
      handleCancel () {
        this.close()
      }
    }
  }
</script>

<style scoped>

</style>