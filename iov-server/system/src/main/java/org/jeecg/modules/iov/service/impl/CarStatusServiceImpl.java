package org.jeecg.modules.iov.service.impl;

import org.jeecg.modules.iov.entity.CarStatus;
import org.jeecg.modules.iov.mapper.CarStatusMapper;
import org.jeecg.modules.iov.service.ICarStatusService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 车况
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
@Service
public class CarStatusServiceImpl extends ServiceImpl<CarStatusMapper, CarStatus> implements ICarStatusService {

    @Override
    public void delByCarId(CarStatus carStatus) {
        super.baseMapper.delByCarId(carStatus);
    }
}
