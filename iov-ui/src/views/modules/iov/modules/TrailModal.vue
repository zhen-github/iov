<template>
  <a-modal
    :title="title"
    :width="width"
    :visible="visible"
    :footer="null"
    @cancel="cancel"
    :destroyOnClose="true"
  >
    <div v-if="flag">
      <baidu-map  class="map" :center="map.center" :zoom="map.zoom" ref="mapp" @ready="handler">
        <!--缩放-->
        <bm-navigation anchor="BMAP_ANCHOR_TOP_LEFT"></bm-navigation>

      </baidu-map>
    </div>
  </a-modal>
</template>

<script>
  import { getAction } from '@/api/manage'
  import { filterObj } from '@/utils/util';

  export default {
    name: 'TrailModal',
    data() {
      return {
        title: '定位',
        width: 800,
        visible: false,
        flag: false,
        data: [],
        map: {
          center: { lng: 103.388611, lat: 35.563611 },
          zoom: 15,
          pois: []
        },
        url: '/iov/carStatusLog/list',
        parameter: {
          carLogId: ''
        },
        data: []
      }
    },
    methods: {
      trailShow(logId) {
        this.visible = true
        this.parameter.carLogId = logId
        getAction(this.url, filterObj(this.parameter)).then((res) => {
          if (res.success) {
            this.data = res.result.records
            this.flag=true
            if (this.data.length>0){
              this.map.center.lng=this.data[0].longitude;
              this.map.center.lat=this.data[0].latitude;
            }
          }
          if (res.code === 510) {
            this.$message.warning(res.message)
          }
        })
        console.log(4)

      },
      cancel() {
        this.map.center={ lng: 103.388611, lat: 35.563611 },
        this.data = []
        this.map.pois = []
        this.visible = false
        this.flag=false
      },
      handler({ BMap, map }) {
        // 鼠标缩放
        map.enableScrollWheelZoom(true)
        for (let i = 0; i < this.data.length; i++) {
          this.map.pois.push(new BMap.Point(this.data[i].longitude, this.data[i].latitude))
          if (i==0||i==(this.data.length-1)){
            var marker= new BMap.Marker(new BMap.Point(this.data[i].longitude, this.data[i].latitude))
            map.addOverlay(marker)
          }
        }
        var sy = new BMap.Symbol(BMap_Symbol_SHAPE_BACKWARD_OPEN_ARROW, {
          scale: 0.6,//图标缩放大小
          strokeColor:'#fff',//设置矢量图标的线填充颜色
          strokeWeight: 2,//设置线宽
        })
        var icons = new BMap.IconSequence(sy, '100%', '10%',false)
        var polyline = new BMap.Polyline(this.map.pois, {
          strokeColor: '#18a45b',
          strokeWeight: 8,
          strokeOpacity: 0.8,
          icons:[icons]
        })
        map.addOverlay(polyline)
      }
    }
  }
</script>

<style scoped>
  .map {
    width: 100%;
    height: 400px;
  }
</style>