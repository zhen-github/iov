<template>
    <a-modal
      :title="title"
      :width="width"
      :visible="visible"
      :footer="null"
      @cancel="mapCancel"
      :destroyOnClose="true"
  >
      <div>
        <baidu-map class="map" :center="map.center" :zoom="map.zoom" ref="mapp"  @ready="handler">
          <!--缩放-->
          <bm-navigation anchor="BMAP_ANCHOR_TOP_LEFT"></bm-navigation>
          <!--点-->
          <bm-marker :position="map.center"  animation="BMAP_ANIMATION_DROP">
          </bm-marker>
        </baidu-map>
      </div>
    </a-modal>
</template>

<script>
  export default {
    name: 'MapModal',
    data () {
      return {
        title: "定位",
        width: 800,
        visible: false,
        map:{
          center: {lng: 0, lat: 0},
          zoom: 15
        },
      }
    },
    methods: {
      mapShow(record) {
        this.map.center.lng=record.longitude;
        this.map.center.lat=record.latitude;
        this.visible=true;
      },
      mapCancel() {
        this.visible=false;
      },
      handler ({BMap, map}) {
        // 鼠标缩放
        map.enableScrollWheelZoom(true);
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