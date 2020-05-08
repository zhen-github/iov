package org.jeecg.common.netty;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private String[] strs;
    private String regex = "[0-9]*(\\.?)[0-9]*";
    private Pattern p = Pattern.compile(regex);


    /***
     * 解析消息，更新状态
     * @param ctx
     * @param msg
     */
    public void dataEngine(String ctxId, ByteBuf msg) {
        // byte[] mac = new byte[6];
        strs = msg.toString(CharsetUtil.UTF_8).split(";");
        CarStatus carStatus = new CarStatus();
        CarStatusLog carStatusLog = new CarStatusLog();
        Car car = new Car();
        carStatus.setCar(car);
        // msg.getBytes(1, mac);
        //bytesToHexString(mac);
        car.setMac("D8CB8A828D3D");//设定唯一标识
        carStatus.setStatus(1);
        for (String item : strs) {
            String[] m = item.split(":");
            switch (m[0]) {
                case "经度":
                    Matcher longMatcher = p.matcher(m[1]);
                    if (longMatcher.find()) {
                        carStatus.setLongitude(Double.valueOf(longMatcher.group()));
                        carStatusLog.setLongitude(Double.valueOf(longMatcher.group()));
                    }
                    break;
                case "纬度":
                    Matcher latMatcher = p.matcher(m[1]);
                    if (latMatcher.find()) {
                        carStatus.setLatitude(Double.valueOf(latMatcher.group()));
                        carStatusLog.setLatitude(Double.valueOf(latMatcher.group()));
                    }
                    break;
                case "速度":
                    Matcher speedMatcher = p.matcher(m[1]);
                    if (speedMatcher.find()) {
                        carStatus.setSpeed(Double.valueOf(speedMatcher.group()));
                        carStatusLog.setSpeed(Double.valueOf(speedMatcher.group()));
                    }
                    break;
                case "DTC":
                    carStatus.setBreakdown(m[1].replace(" ", ""));
                    carStatusLog.setBreakdown(m[1].replace(" ", ""));
                    break;
                case "DS041":
                    carStatus.setCoolantTemperature(Double.valueOf(m[1].replace(" ", "")));
                    carStatusLog.setCoolantTemperature(Double.valueOf(m[1].replace(" ", "")));
                    break;
                case "DS042":
                    carStatus.setStfcOne(Double.valueOf(m[1].replace(" ", "")));
                    carStatusLog.setStfcOne(Double.valueOf(m[1].replace(" ", "")));
                    break;
                case "DS043":
                    carStatus.setLtfcOne(Double.valueOf(m[1].replace(" ", "")));
                    carStatusLog.setLtfcOne(Double.valueOf(m[1].replace(" ", "")));
                    break;
                case "DS044":
                    carStatus.setStfcTwo(Double.valueOf(m[1].replace(" ", "")));
                    carStatusLog.setStfcTwo(Double.valueOf(m[1].replace(" ", "")));
                    break;
                case "DS045":
                    carStatus.setLtfcTwo(Double.valueOf(m[1].replace(" ", "")));
                    carStatusLog.setLtfcTwo(Double.valueOf(m[1].replace(" ", "")));
                    break;
                case "DS046":

                    break;
                case "DS047":

                    break;
                case "DS048":
                    carStatus.setRotation(Integer.valueOf(m[1].replace(" ", "")));
                    carStatusLog.setRotation(Integer.valueOf(m[1].replace(" ", "")));
                    break;
                case "DS049":
                    carStatus.setSpeed(Double.valueOf(m[1].replace(" ", "")));
                    carStatusLog.setSpeed(Double.valueOf(m[1].replace(" ", "")));
                    break;
                case "DS051":
                    carStatus.setOnflowTemperature(Double.valueOf(m[1].replace(" ", "")));
                    carStatusLog.setOnflowTemperature(Double.valueOf(m[1].replace(" ", "")));
                    break;

                case "DS080":
                    carStatus.setOsvOneOne(Double.valueOf(m[1].replace(" ", "")));
                    carStatusLog.setOsvOneOne(Double.valueOf(m[1].replace(" ", "")));
                    break;
                case "DS082":
                    carStatus.setOsvOneTwo(Double.valueOf(m[1].replace(" ", "")));
                    carStatusLog.setOsvOneTwo(Double.valueOf(m[1].replace(" ", "")));
                    break;
                case "DS084":
                    carStatus.setOsvOneThere(Double.valueOf(m[1].replace(" ", "")));
                    carStatusLog.setOsvOneThere(Double.valueOf(m[1].replace(" ", "")));
                    break;
                case "DS086":
                    carStatus.setOsvOneFour(Double.valueOf(m[1].replace(" ", "")));
                    carStatusLog.setOsvOneFour(Double.valueOf(m[1].replace(" ", "")));
                    break;

                case "DS088":
                    carStatus.setOsvTwoOne(Double.valueOf(m[1].replace(" ", "")));
                    carStatusLog.setOsvTwoOne(Double.valueOf(m[1].replace(" ", "")));
                    break;
                case "DS090":
                    carStatus.setOsvTwoTwo(Double.valueOf(m[1].replace(" ", "")));
                    carStatusLog.setOsvTwoTwo(Double.valueOf(m[1].replace(" ", "")));
                    break;
                case "DS092":
                    carStatus.setOsvTwoThere(Double.valueOf(m[1].replace(" ", "")));
                    carStatusLog.setOsvTwoThere(Double.valueOf(m[1].replace(" ", "")));
                    break;
                case "DS094":
                    carStatus.setOsvTwoFour(Double.valueOf(m[1].replace(" ", "")));
                    carStatusLog.setOsvTwoFour(Double.valueOf(m[1].replace(" ", "")));
                    break;

                case "DS119":
                    carStatus.setCatalyzerTemOneOne(Double.valueOf(m[1].replace(" ", "")));
                    carStatusLog.setCatalyzerTemOneOne(Double.valueOf(m[1].replace(" ", "")));
                    break;
                case "DS120":
                    carStatus.setCatalyzerTemTwoOne(Double.valueOf(m[1].replace(" ", "")));
                    carStatusLog.setCatalyzerTemTwoOne(Double.valueOf(m[1].replace(" ", "")));
                    break;
                case "DS121":
                    carStatus.setCatalyzerTemOneTwo(Double.valueOf(m[1].replace(" ", "")));
                    carStatusLog.setCatalyzerTemOneTwo(Double.valueOf(m[1].replace(" ", "")));
                    break;
                case "DS122":
                    carStatus.setCatalyzerTemTwoTwo(Double.valueOf(m[1].replace(" ", "")));
                    carStatusLog.setCatalyzerTemTwoTwo(Double.valueOf(m[1].replace(" ", "")));
                    break;


                case "DS147":
                    break;
                case "Acc":
                    carStatus.setAcceleration(m[1]);
                    carStatusLog.setAcceleration(m[1]);
                    break;
                case "Gyro":
                    break;
                case "Angle":
                    carStatus.setInclination(m[1]);
                    carStatusLog.setInclination(m[1]);
                    break;
                case "Mag":
                    break;
            }
        }
        if (macMap.get(ctxId) == null | "".equals(macMap.get(ctxId))) {
            macMap.put(ctxId, "D8CB8A828D3D");
            QueryWrapper<Car> wrapper = new QueryWrapper<Car>();
            wrapper.eq("mac", "D8CB8A828D3D");
            CarLog carLog = new CarLog();
            carLog.setCar(carService.getOne(wrapper));
            carLog.setStatus(1);
            carLogService.add(carLog);
            logMap.put(ctxId, carLog.getId());
        }
        if (carStatus.getSpeed() == 0) {
            String[] strs = carStatus.getInclination().split(" ");
            if (Double.valueOf(strs[0]) > 90 || Double.valueOf(strs[0]) < -90) {
                System.out.println("发生侧翻");
            }
        }

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
        if (log != null & !"".equals(log)) {
            CarLog carLog = new CarLog();
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
