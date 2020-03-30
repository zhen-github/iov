package org.jeecg.modules.iov.service.impl;

import org.jeecg.modules.iov.entity.Car;
import org.jeecg.modules.iov.mapper.CarMapper;
import org.jeecg.modules.iov.service.ICarService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 车辆管理
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {
    @Override
    public boolean add(Car car) {
        return super.baseMapper.add(car);
    }

    @Override
    public void updateMotorcadeById(String id, String motorcadeCode) {
        super.baseMapper.updateMotorcadeById(id,motorcadeCode);
    }
}
