package org.jeecg.modules.iov.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.iov.entity.CarLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 行车记录
 * @Author: jeecg-boot
 * @Date:   2020-02-27
 * @Version: V1.0
 */
public interface CarLogMapper extends BaseMapper<CarLog> {

    IPage<CarLog> list(IPage<CarLog> page,@Param("ew") Wrapper<CarLog> queryWrapper);
    void add(CarLog carLog);
    void updateStatus(CarLog carLog);

}
