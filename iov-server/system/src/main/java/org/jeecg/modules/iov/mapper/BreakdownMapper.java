package org.jeecg.modules.iov.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.iov.entity.Breakdown;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 故障码
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
public interface BreakdownMapper extends BaseMapper<Breakdown> {

    public List<Breakdown> listByCode(@Param("breakdownCodes")String[] breakdownCodes);
}
