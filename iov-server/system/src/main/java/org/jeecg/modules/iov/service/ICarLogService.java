package org.jeecg.modules.iov.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.jeecg.modules.iov.entity.CarLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 行车记录
 * @Author: jeecg-boot
 * @Date:   2020-02-27
 * @Version: V1.0
 */
public interface ICarLogService extends IService<CarLog> {


    IPage<CarLog> list(IPage<CarLog> page, Wrapper<CarLog> queryWrapper);

    void add(CarLog carLog);

    void updateStatus(CarLog carLog);
}
