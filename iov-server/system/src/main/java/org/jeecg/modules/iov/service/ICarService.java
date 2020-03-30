package org.jeecg.modules.iov.service;

import org.jeecg.modules.iov.entity.Car;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 车辆管理
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
public interface ICarService extends IService<Car> {
    boolean add(Car car);
    void updateMotorcadeById(String id,String motorcadeCode);

}
