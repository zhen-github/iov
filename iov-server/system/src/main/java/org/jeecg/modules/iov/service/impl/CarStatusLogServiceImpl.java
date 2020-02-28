package org.jeecg.modules.iov.service.impl;

import org.jeecg.modules.iov.entity.CarStatusLog;
import org.jeecg.modules.iov.mapper.CarStatusLogMapper;
import org.jeecg.modules.iov.service.ICarStatusLogService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 行车状态记录
 * @Author: jeecg-boot
 * @Date:   2020-02-27
 * @Version: V1.0
 */
@Service
public class CarStatusLogServiceImpl extends ServiceImpl<CarStatusLogMapper, CarStatusLog> implements ICarStatusLogService {

}
