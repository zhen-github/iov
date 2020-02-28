package org.jeecg.modules.iov.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.jeecg.modules.iov.entity.CarStatus;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 车况
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
public interface ICarStatusService extends IService<CarStatus> {
    void add(CarStatus carStatus);
    void delByCar(CarStatus carStatus);
    void updateStatus(CarStatus carStatus);
    IPage<CarStatus> list(IPage<CarStatus> page, String userId);
    void  inactive(String mac);
}
