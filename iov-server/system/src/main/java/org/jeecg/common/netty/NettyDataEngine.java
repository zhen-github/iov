package org.jeecg.common.netty;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.jeecg.modules.iov.entity.Car;
import org.jeecg.modules.iov.entity.CarLog;
import org.jeecg.modules.iov.entity.CarStatus;
import org.jeecg.modules.iov.entity.CarStatusLog;
import org.jeecg.modules.iov.service.ICarLogService;
import org.jeecg.modules.iov.service.ICarService;
import org.jeecg.modules.iov.service.ICarStatusLogService;
import org.jeecg.modules.iov.service.ICarStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class NettyDataEngine {
    private Map<String, String> macMap = new HashMap<>();
    private Map<String, String> logMap = new HashMap<>();
    @Autowired
    private ICarService carService;
    @Autowired
    private ICarStatusService carStatusService;
    @Autowired
    private ICarLogService carLogService;
    @Autowired
    private ICarStatusLogService carStatusLogService;


    /***
     * 解析消息，更新状态
     * @param ctx
     * @param msg
     */
    public void dataEngine(String ctxId, ByteBuf msg) {
        byte[] mac = new byte[6];
        CarStatus carStatus = new CarStatus();
        CarStatusLog carStatusLog=new CarStatusLog();
        Car car = new Car();
        carStatus.setCar(car);
        msg.getBytes(1, mac);
        car.setMac(bytesToHexString(mac));
        carStatus.setStatus(1);
        if (macMap.get(ctxId) == null | "".equals(macMap.get(ctxId))) {
            macMap.put(ctxId, bytesToHexString(mac));
            QueryWrapper<Car> wrapper=new QueryWrapper<Car>();
            wrapper.eq("mac",bytesToHexString(mac));
            CarLog carLog=new CarLog();
            carLog.setCar(carService.getOne(wrapper));
            carLog.setStatus(1);
            carLogService.add(carLog);
            logMap.put(ctxId,carLog.getId());
        }
        carStatus.setSpeed((double) msg.getByte(7));
        carStatusLog.setSpeed((double) msg.getByte(7));
        carStatusService.updateStatus(carStatus);
        carStatusLog.setCarLogId(logMap.get(ctxId));
        carStatusLogService.save(carStatusLog);
    }


    /**
     * 更新离线状态
     *
     * @param ctx
     */
    public void inactive(ChannelHandlerContext ctx) {
        String mac = macMap.remove(ctx.channel().id().asLongText());
        String log = logMap.remove(ctx.channel().id().asLongText());
        if (mac != null & !"".equals(mac)) {
            carStatusService.inactive(mac);
        }
        if (log!=null&!"".equals(log)){
            CarLog carLog=new CarLog();
            carLog.setId(log);
            carLog.setStatus(0);
            carLogService.updateStatus(carLog);
        }
    }

    public static String bytesToHexString(byte[] src) {
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
