package org.jeecg.modules.iov.service;

import org.jeecg.modules.iov.entity.Breakdown;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 故障码
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
public interface IBreakdownService extends IService<Breakdown> {

    public List<Breakdown> listByCode(String breakdownCodes);

}
