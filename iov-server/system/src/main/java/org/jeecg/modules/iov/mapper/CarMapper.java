package org.jeecg.modules.iov.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.iov.entity.Car;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 车辆管理
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
public interface CarMapper extends BaseMapper<Car> {
    boolean add(Car car);
    void updateMotorcadeById(@Param("id")String id,@Param("sysOrgCode") String motorcadeCode);

}
