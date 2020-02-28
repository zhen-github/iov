package org.jeecg.modules.iov.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.jeecg.modules.iov.entity.CarLog;
import org.jeecg.modules.iov.mapper.CarLogMapper;
import org.jeecg.modules.iov.service.ICarLogService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 行车记录
 * @Author: jeecg-boot
 * @Date:   2020-02-27
 * @Version: V1.0
 */
@Service
public class CarLogServiceImpl extends ServiceImpl<CarLogMapper, CarLog> implements ICarLogService {

    @Override
    public IPage<CarLog> list(IPage<CarLog> page, Wrapper<CarLog> queryWrapper) {
        return super.baseMapper.list(page,queryWrapper);
    }

    @Override
    public void add(CarLog carLog) {
        super.baseMapper.add(carLog);
    }

    @Override
    public void updateStatus(CarLog carLog) {
        super.baseMapper.updateStatus(carLog);
    }
}
