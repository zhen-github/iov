package org.jeecg.modules.iov.service.impl;

import org.jeecg.modules.iov.entity.Breakdown;
import org.jeecg.modules.iov.mapper.BreakdownMapper;
import org.jeecg.modules.iov.service.IBreakdownService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 故障码
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
@Service
public class BreakdownServiceImpl extends ServiceImpl<BreakdownMapper, Breakdown> implements IBreakdownService {

    @Override
    public List<Breakdown> listByCode(String breakdownCodes) {
        return super.baseMapper.listByCode(breakdownCodes.split(","));
    }
}
