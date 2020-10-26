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

        <template slot="inspect" slot-scope="text, record">
          <template v-for="item in inspect(record.mileage)">
            {{item}}<br/>
          </template>
        </template>
        <template slot="replace" slot-scope="text, record">
          <template v-for="item in replace(record.mileage)">
            {{item}}<br/>
          </template>
        </template>
        <template slot="phone" slot-scope="text,record">
          <span v-if="record.mileage >= 5000"> {{getPhone(record)}}  {{record.createBy}}</span>
        </template>
      </a-table>
    </div>
  </a-card>
</template>

<script>

  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import CarStatusModal from './modules/CarStatusModal'
  import MapModal from './modules/MapModal'
  import EngineModal from './modules/EngineModal'
  import CarBreakdownModal from './modules/CarBreakdownModal'
  import { getAction } from '@/api/manage'
  import user from '../../../store/modules/user'

  export default {
    name: 'CarStatusList',
    mixins: [JeecgListMixin],
    components: {
      CarBreakdownModal,
      EngineModal,
      MapModal,
      CarStatusModal
    },
    props: ['parentQueryParam'],
    data() {
      return {
        description: '精准服务',
        // 表头
        columns: [
          {
            title: '车牌号',
            align: 'center',
            dataIndex: 'car.carNumber'
          },
          {
            title: '里程',
            align: 'center',
            dataIndex: 'mileage'

          },
          {
            title: '检查',
            align: 'center',
            dataIndex: 'inspect',
            scopedSlots: { customRender: 'inspect' }
          },
          {
            title: '更换',
            align: 'center',
            dataIndex: 'replace',
            scopedSlots: { customRender: 'replace' }
          },
          {
            title: '联系方式',
            align: 'center',
            dataIndex: 'phone',
            scopedSlots: { customRender: 'phone' }
          }
        ],
        url: {
          list: '/iov/carStatus/list',
          delete: '/iov/carStatus/delete',
          deleteBatch: '/iov/carStatus/deleteBatch',
          exportXlsUrl: '/iov/carStatus/exportXls',
          importExcelUrl: 'iov/carStatus/importExcel'
        },
        dictOptions: {}
      }
    },
    computed: {
      importExcelUrl: function() {
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`
      }
    },
    methods: {
      initData() {
        if (this.parentQueryParam != null) {
          this.queryParam = this.parentQueryParam
        }
      },
      initDictConfig() {
      },
      inspect(mileage) {
        if (mileage >= 5000 && mileage < 10000) {
          return ['发动机机油及其滤清器', '冷却系统软管和接头']
        } else if (mileage >= 10000 && mileage < 20000) {
          return ['燃油管和接头', '氧传感器和三元催化器', '蒸发排放碳罐和蒸汽管', '排气系统部件', '驻车制动器', '制动油管和接头', '悬架和转向系统部件', '动力转向液和油管', '驱动轴护套', '安全保护装置系统部件', '润滑锁、铰链和发动机罩闩钩']
        } else if (mileage >= 20000 && mileage < 24000) {
          return ['燃油管和接头', '火花塞', '氧传感器和三元催化器', '蒸发排放碳罐和蒸汽管', '排气系统部件', '驻车制动器', '制动油管和接头', '悬架和转向系统部件', '动力转向液和油管', '驱动轴护套', '安全保护装置系统部件', '润滑锁、铰链和发动机罩闩钩']
        } else if (mileage >= 24000 && mileage < 30000) {
          return ['传送带', '发动机冷却液']
        } else if (mileage >= 30000 && mileage < 40000) {
          return ['燃油管和接头', '氧传感器和三元催化器', '蒸发排放碳罐和蒸汽管', '曲轴箱强制通风系统', '排气系统部件', '驻车制动器', '制动油管和接头', '悬架和转向系统部件', '动力转向液和油管', '驱动轴护套', '安全保护装置系统部件', '润滑锁、铰链和发动机罩闩钩']
        } else if (mileage >= 40000 && mileage < 50000) {
          return ['燃油滤清器', '燃油管和接头', '氧传感器和三元催化器', '蒸发排放碳罐和蒸汽管', '驻车制动器', '制动油管和接头', '排气系统部件', '悬架和转向系统部件', '动力转向液和油管', '驱动轴护套', '安全保护装置系统部件', '润滑锁、铰链和发动机罩闩钩']
        } else if (mileage >= 50000 && mileage < 60000) {
          return ['燃油管和接头', '氧传感器和三元催化器', '蒸发排放碳罐和蒸汽管', '排气系统部件', '驻车制动器', '制动油管和接头', '排气系统部件', '悬架和转向系统部件', '动力转向液和油管', '驱动轴护套', '安全保护装置系统部件', '润滑锁、铰链和发动机罩闩钩']
        } else if (mileage >= 60000 && mileage < 70000) {
          return ['燃油管和接头', '氧传感器和三元催化器', '火花塞', '氧传感器和三元催化器', '蒸发排放碳罐和蒸汽管', '曲轴箱强制通风系统', '排气系统部件', '驻车制动器', '制动油管和接头', '排气系统部件', '悬架和转向系统部件', '动力转向液和油管', '驱动轴护套', '安全保护装置系统部件', '润滑锁、铰链和发动机罩闩钩']
        } else if (mileage >= 70000 && mileage < 80000) {
          return ['燃油管和接头', '氧传感器和三元催化器', '蒸发排放碳罐和蒸汽管', '排气系统部件', '驻车制动器', '制动油管和接头', '排气系统部件', '悬架和转向系统部件', '动力转向液和油管', '驱动轴护套', '安全保护装置系统部件', '润滑锁、铰链和发动机罩闩钩']
        } else if (mileage >= 80000 && mileage < 90000) {
          return ['燃油滤清器', '燃油管和接头', '氧传感器和三元催化器', '氧传感器和三元催化器', '蒸发排放碳罐和蒸汽管', '驻车制动器', '制动油管和接头', '排气系统部件', '悬架和转向系统部件', '动力转向液和油管', '驱动轴护套', '安全保护装置系统部件', '润滑锁、铰链和发动机罩闩钩']
        } else if (mileage >= 90000 && mileage < 100000) {
          return ['燃油管和接头', '火花塞导线', '氧传感器和三元催化器', '蒸发排放碳罐和蒸汽管', '曲轴箱强制通风系统', '排气系统部件', '驻车制动器', '制动油管和接头', '排气系统部件', '悬架和转向系统部件', '动力转向液和油管', '驱动轴护套', '安全保护装置系统部件', '润滑锁、铰链和发动机罩闩钩']
        } else if (mileage >= 100000) {
          return ['燃油管和接头', '火花塞', '氧传感器和三元催化器', '蒸发排放碳罐和蒸汽管', '排气系统部件']
        }

      },
      replace(mileage) {
        if (mileage >= 20000 && mileage < 24000) {
          return ['空气滤清器滤芯', '内部空气滤清器']
        } else if (mileage >= 30000 && mileage < 40000) {
          return ['制动器/离合器油液']
        } else if (mileage >= 40000 && mileage < 50000) {
          return ['空气滤清器滤芯', '火花塞']
        } else if (mileage >= 60000 && mileage < 70000) {
          return ['空气滤清器滤芯', '内部空气滤清器', '制动器/离合器油液']
        } else if (mileage >= 80000 && mileage < 90000) {
          return ['空气滤清器滤芯', '火花塞', '内部空气滤清器', '自动变速器']
        } else if (mileage >= 90000 && mileage < 100000) {
          return ['制动器/离合器油液']
        } else if (mileage >= 100000) {
          return ['空气滤清器滤芯', '内部空气滤清器']
        }
      },
      getPhone(record) {
        console.log('cccccccc')
        getAction('/sys/user/list', { 'username': record.createBy }).then((res) => {
          if (res.success) {
            console.log(res.result)
            for (var i = 0; i < this.dataSource.length; i++) {
              if (record.id == this.dataSource[i].id && res.result.records.length != 0) {
                this.dataSource[i].createBy = res.result.records[0].phone
                this.$message.info("车辆"+record.car.carNumber+"需进行保养，请及时通知车主进行保养。")
              }
            }
          }
          if (res.code === 510) {
            this.$message.warning(res.message)
          }
        })
      }
    },
    watch: {
      parentQueryParam(val) {
        this.loadData()
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>