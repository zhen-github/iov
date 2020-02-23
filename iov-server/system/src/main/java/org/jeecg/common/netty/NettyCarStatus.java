package org.jeecg.common.netty;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class NettyCarStatus {
    private Map<String,String> map=new HashMap<>();

    public boolean isExist(String key){
        String value=map.get(key);
        return !"".equals(value);
    }


    public void put(String key,String value){
        map.put(key,value);
    }
}
