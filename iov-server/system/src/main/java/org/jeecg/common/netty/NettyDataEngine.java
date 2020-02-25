package org.jeecg.common.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.jeecg.modules.iov.entity.Car;
import org.jeecg.modules.iov.entity.CarStatus;
import org.jeecg.modules.iov.service.ICarStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class NettyDataEngine {
    private Map<String,String> map=new HashMap<>();
    @Autowired
    private ICarStatusService carStatusService;


    /***
     * 解析消息，更新状态
     * @param ctx
     * @param msg
     */
    public void dataEngine(String ctxId,ByteBuf msg){
        byte[] mac=new byte[6];
        CarStatus carStatus=new CarStatus();
        Car car=new Car();
        carStatus.setCar(car);
        msg.getBytes(1,mac);
        car.setMac(bytesToHexString(mac));
        carStatus.setStatus(1);
        if (map.get(ctxId)==null|"".equals(map.get(ctxId)))
        {
            map.put(ctxId,bytesToHexString(mac));
        }
        carStatus.setSpeed((double) msg.getByte(7));
        carStatusService.updateStatus(carStatus);
    }


    /**
     * 更新离线状态
     * @param ctx
     */
    public void inactive(ChannelHandlerContext ctx){
        String mac= map.remove(ctx.channel().id().asLongText());
        if (mac!=null&!"".equals(mac)){
            carStatusService.inactive(mac);
        }
    }

    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().toUpperCase();
    }
}
