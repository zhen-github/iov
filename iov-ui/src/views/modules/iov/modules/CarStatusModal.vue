<template>
  <a-modal
    :title="title"
    :width="width"
    :visible="visible"
    :confirmLoading="confirmLoading"
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="车牌号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'carId', validatorRules.carId]" placeholder="请输入车牌号"></a-input>
        </a-form-item>
        <a-form-item label="状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'status', validatorRules.status]" placeholder="请输入状态" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="车速" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'speed', validatorRules.speed]" placeholder="请输入车速" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="转速" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'rotation', validatorRules.rotation]" placeholder="请输入转速" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="加速度" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'acceleration', validatorRules.acceleration]" placeholder="请输入加速度" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="倾斜角度" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'inclination', validatorRules.inclination]" placeholder="请输入倾斜角度" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="怠速时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'idling', validatorRules.idling]" placeholder="请输入怠速时间" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="冷却液温度" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'coolantTemperature', validatorRules.coolantTemperature]" placeholder="请输入冷却液温度" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="进气温度" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'onflowTemperature', validatorRules.onflowTemperature]" placeholder="请输入进气温度" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="机油温度" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'engineOilTemperature', validatorRules.engineOilTemperature]" placeholder="请输入机油温度" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="纬度" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'latitude', validatorRules.latitude]" placeholder="请输入纬度" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="经度" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'longitude', validatorRules.longitude]" placeholder="请输入经度" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="故障" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="[ 'breakdown', validatorRules.breakdown]" placeholder="请输入故障"></a-input>
        </a-form-item>
        <a-form-item label="催化剂温度(缸组1,传感器1) " :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'catalyzerTemOneOne', validatorRules.catalyzerTemOneOne]" placeholder="请输入催化剂温度(缸组1,传感器1) " style="width: 100%"/>
        </a-form-item>
        <a-form-item label="催化剂温度(缸组2,传感器1) " :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'catalyzerTemTwoOne', validatorRules.catalyzerTemTwoOne]" placeholder="请输入催化剂温度(缸组2,传感器1) " style="width: 100%"/>
        </a-form-item>
        <a-form-item label="催化剂温度(缸组1,传感器2) " :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'catalyzerTemOneTwo', validatorRules.catalyzerTemOneTwo]" placeholder="请输入催化剂温度(缸组1,传感器2) " style="width: 100%"/>
        </a-form-item>
        <a-form-item label="催化剂温度(缸组2,传感器2) " :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'catalyzerTemTwoTwo', validatorRules.catalyzerTemTwoTwo]" placeholder="请输入催化剂温度(缸组2,传感器2) " style="width: 100%"/>
        </a-form-item>
        <a-form-item label="氧传感器电压(缸组1,传感器1)" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'osvOneOne', validatorRules.osvOneOne]" placeholder="请输入氧传感器电压(缸组1,传感器1)" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="氧传感器电压(缸组1,传感器2)" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'osvOneTwo', validatorRules.osvOneTwo]" placeholder="请输入氧传感器电压(缸组1,传感器2)" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="氧传感器电压(缸组1,传感器3)" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'osvOneThere', validatorRules.osvOneThere]" placeholder="请输入氧传感器电压(缸组1,传感器3)" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="氧传感器电压(缸组1,传感器4)" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'osvOneFour', validatorRules.osvOneFour]" placeholder="请输入氧传感器电压(缸组1,传感器4)" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="氧传感器电压(缸组2,传感器1)" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'osvTwoOne', validatorRules.osvTwoOne]" placeholder="请输入氧传感器电压(缸组2,传感器1)" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="氧传感器电压(缸组2,传感器2)" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'osvTwoTwo', validatorRules.osvTwoTwo]" placeholder="请输入氧传感器电压(缸组2,传感器2)" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="氧传感器电压(缸组2,传感器3)" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'osvTwoThere', validatorRules.osvTwoThere]" placeholder="请输入氧传感器电压(缸组2,传感器3)" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="氧传感器电压(缸组2,传感器4)" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'osvTwoFour', validatorRules.osvTwoFour]" placeholder="请输入氧传感器电压(缸组2,传感器4)" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="短期燃油修正(缸组1) " :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'stfcOne', validatorRules.stfcOne]" placeholder="请输入短期燃油修正(缸组1) " style="width: 100%"/>
        </a-form-item>
        <a-form-item label="长期燃油修正(缸组1)" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'ltfcOne', validatorRules.ltfcOne]" placeholder="请输入长期燃油修正(缸组1)" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="短期燃油修正(缸组2)" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'stfcTwo', validatorRules.stfcTwo]" placeholder="请输入短期燃油修正(缸组2)" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="长期燃油修正(缸组2)" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="[ 'ltfcTwo', validatorRules.ltfcTwo]" placeholder="请输入长期燃油修正(缸组2)" style="width: 100%"/>
        </a-form-item>

      </a-form>
    </a-spin>
  </a-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: "CarStatusModal",
    components: { 
    },
    data () {
      return {
        form: this.$form.createForm(this),
        title:"操作",
        width:800,
        visible: false,
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
        validatorRules: {
          carId: {rules: [
          ]},
          status: {rules: [
          ]},
          speed: {rules: [
          ]},
          rotation: {rules: [
          ]},
          acceleration: {rules: [
          ]},
          inclination: {rules: [
          ]},
          idling: {rules: [
          ]},
          coolantTemperature: {rules: [
          ]},
          onflowTemperature: {rules: [
          ]},
          engineOilTemperature: {rules: [
          ]},
          latitude: {rules: [
          ]},
          longitude: {rules: [
          ]},
          breakdown: {rules: [
          ]},
          catalyzerTemOneOne: {rules: [
          ]},
          catalyzerTemTwoOne: {rules: [
          ]},
          catalyzerTemOneTwo: {rules: [
          ]},
          catalyzerTemTwoTwo: {rules: [
          ]},
          osvOneOne: {rules: [
          ]},
          osvOneTwo: {rules: [
          ]},
          osvOneThere: {rules: [
          ]},
          osvOneFour: {rules: [
          ]},
          osvTwoOne: {rules: [
          ]},
          osvTwoTwo: {rules: [
          ]},
          osvTwoThere: {rules: [
          ]},
          osvTwoFour: {rules: [
          ]},
          stfcOne: {rules: [
          ]},
          ltfcOne: {rules: [
          ]},
          stfcTwo: {rules: [
          ]},
          ltfcTwo: {rules: [
          ]},
        },
        url: {
          add: "/iov/carStatus/add",
          edit: "/iov/carStatus/edit",
        }
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
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'carId','status','speed','rotation','acceleration','inclination','idling','coolantTemperature','onflowTemperature','engineOilTemperature','latitude','longitude','breakdown','catalyzerTemOneOne','catalyzerTemTwoOne','catalyzerTemOneTwo','catalyzerTemTwoTwo','osvOneOne','osvOneTwo','osvOneThere','osvOneFour','osvTwoOne','osvTwoTwo','osvTwoThere','osvTwoFour','stfcOne','ltfcOne','stfcTwo','ltfcTwo'))
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
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
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'carId','status','speed','rotation','acceleration','inclination','idling','coolantTemperature','onflowTemperature','engineOilTemperature','latitude','longitude','breakdown','catalyzerTemOneOne','catalyzerTemTwoOne','catalyzerTemOneTwo','catalyzerTemTwoTwo','osvOneOne','osvOneTwo','osvOneThere','osvOneFour','osvTwoOne','osvTwoTwo','osvTwoThere','osvTwoFour','stfcOne','ltfcOne','stfcTwo','ltfcTwo'))
      },

      
    }
  }
</script>