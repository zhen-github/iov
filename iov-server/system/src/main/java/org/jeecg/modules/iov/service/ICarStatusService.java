package org.jeecg.modules.iov.service;

import org.jeecg.modules.iov.entity.CarStatus;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 车况
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
public interface ICarStatusService extends IService<CarStatus> {

    void delByCarId(CarStatus carStatus);
}
