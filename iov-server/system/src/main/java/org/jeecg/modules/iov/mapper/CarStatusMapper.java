package org.jeecg.modules.iov.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.iov.entity.CarStatus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 车况
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
public interface CarStatusMapper extends BaseMapper<CarStatus> {
    void delByCarId(CarStatus carStatus);
}
