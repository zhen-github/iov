package org.jeecg.modules.iov.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
    public void add(CarStatus carStatus) {
        super.baseMapper.add(carStatus);
    }

    @Override
    public void delByCar(CarStatus carStatus) {
        super.baseMapper.delByCar(carStatus);
    }

    @Override
    public IPage<CarStatus> list(IPage<CarStatus> page, Wrapper<CarStatus> queryWrapper){

        return super.baseMapper.list(page,queryWrapper);
    }
}
