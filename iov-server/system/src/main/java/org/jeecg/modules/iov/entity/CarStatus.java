package org.jeecg.modules.iov.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @Description: 车况
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
@Data
@TableName("iov_car_status")
public class CarStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
    private java.lang.String id;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.util.Date updateTime;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
    private java.lang.String sysOrgCode;
	/**车牌号*/
	@Excel(name = "车牌号", width = 15)
    private java.lang.String carId;
	/**状态*/
	@Excel(name = "状态", width = 15)
    private java.lang.Integer status;
	/**车速*/
	@Excel(name = "车速", width = 15)
    private java.lang.Double speed;
	/**转速*/
	@Excel(name = "转速", width = 15)
    private java.lang.Integer rotation;
	/**加速度*/
	@Excel(name = "加速度", width = 15)
    private java.lang.Double acceleration;
	/**倾斜角度*/
	@Excel(name = "倾斜角度", width = 15)
    private java.lang.Double inclination;
	/**怠速时间*/
	@Excel(name = "怠速时间", width = 15)
    private java.lang.Double idling;
	/**冷却液温度*/
	@Excel(name = "冷却液温度", width = 15)
    private java.lang.Double coolantTemperature;
	/**进气温度*/
	@Excel(name = "进气温度", width = 15)
    private java.lang.Double onflowTemperature;
	/**机油温度*/
	@Excel(name = "机油温度", width = 15)
    private java.lang.Double engineOilTemperature;
	/**纬度*/
	@Excel(name = "纬度", width = 15)
    private java.lang.Double latitude;
	/**经度*/
	@Excel(name = "经度", width = 15)
    private java.lang.Double longitude;
	/**故障*/
	@Excel(name = "故障", width = 15)
    private java.lang.String breakdown;
	/**催化剂温度(缸组1,传感器1) */
	@Excel(name = "催化剂温度(缸组1,传感器1) ", width = 15)
    private java.lang.Double catalyzerTemOneOne;
	/**催化剂温度(缸组2,传感器1) */
	@Excel(name = "催化剂温度(缸组2,传感器1) ", width = 15)
    private java.lang.Double catalyzerTemTwoOne;
	/**催化剂温度(缸组1,传感器2) */
	@Excel(name = "催化剂温度(缸组1,传感器2) ", width = 15)
    private java.lang.Double catalyzerTemOneTwo;
	/**催化剂温度(缸组2,传感器2) */
	@Excel(name = "催化剂温度(缸组2,传感器2) ", width = 15)
    private java.lang.Double catalyzerTemTwoTwo;
	/**氧传感器电压(缸组1,传感器1)*/
	@Excel(name = "氧传感器电压(缸组1,传感器1)", width = 15)
    private java.lang.Double osvOneOne;
	/**氧传感器电压(缸组1,传感器2)*/
	@Excel(name = "氧传感器电压(缸组1,传感器2)", width = 15)
    private java.lang.Double osvOneTwo;
	/**氧传感器电压(缸组1,传感器3)*/
	@Excel(name = "氧传感器电压(缸组1,传感器3)", width = 15)
    private java.lang.Double osvOneThere;
	/**氧传感器电压(缸组1,传感器4)*/
	@Excel(name = "氧传感器电压(缸组1,传感器4)", width = 15)
    private java.lang.Double osvOneFour;
	/**氧传感器电压(缸组2,传感器1)*/
	@Excel(name = "氧传感器电压(缸组2,传感器1)", width = 15)
    private java.lang.Double osvTwoOne;
	/**氧传感器电压(缸组2,传感器2)*/
	@Excel(name = "氧传感器电压(缸组2,传感器2)", width = 15)
    private java.lang.Double osvTwoTwo;
	/**氧传感器电压(缸组2,传感器3)*/
	@Excel(name = "氧传感器电压(缸组2,传感器3)", width = 15)
    private java.lang.Double osvTwoThere;
	/**氧传感器电压(缸组2,传感器4)*/
	@Excel(name = "氧传感器电压(缸组2,传感器4)", width = 15)
    private java.lang.Double osvTwoFour;
	/**短期燃油修正(缸组1) */
	@Excel(name = "短期燃油修正(缸组1) ", width = 15)
    private java.lang.Double stfcOne;
	/**长期燃油修正(缸组1)*/
	@Excel(name = "长期燃油修正(缸组1)", width = 15)
    private java.lang.Double ltfcOne;
	/**短期燃油修正(缸组2)*/
	@Excel(name = "短期燃油修正(缸组2)", width = 15)
    private java.lang.Double stfcTwo;
	/**长期燃油修正(缸组2)*/
	@Excel(name = "长期燃油修正(缸组2)", width = 15)
    private java.lang.Double ltfcTwo;
}
