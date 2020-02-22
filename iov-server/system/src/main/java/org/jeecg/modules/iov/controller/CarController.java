package org.jeecg.modules.iov.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.iov.entity.Car;
import org.jeecg.modules.iov.entity.CarStatus;
import org.jeecg.modules.iov.service.ICarService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.iov.service.ICarStatusService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;

 /**
 * @Description: 车辆管理
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
@RestController
@RequestMapping("/iov/car")
@Slf4j
public class CarController extends JeecgController<Car, ICarService> {
	@Autowired
	private ICarService carService;
	 @Autowired
	 private ICarStatusService carStatusService;
	
	/**
	 * 分页列表查询
	 *
	 * @param car
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	@PermissionData(pageComponent="modules/iov/CarList")
	public Result<?> queryPageList(Car car,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Car> queryWrapper = QueryGenerator.initQueryWrapper(car, req.getParameterMap());
		Page<Car> page = new Page<Car>(pageNo, pageSize);
		IPage<Car> pageList = carService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param car
	 * @return
	 */
	@PostMapping(value = "/add")
	@Transactional
	public Result<?> add(@RequestBody Car car) {
		carService.save(car);
		CarStatus carStatus=new CarStatus();
		carStatus.setCarId(car.getId());
		carStatusService.save(carStatus);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param car
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Car car) {
		carService.updateById(car);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@Transactional
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		carService.removeById(id);
		CarStatus carStatus=new CarStatus();
		carStatus.setCarId(id);
		carStatusService.delByCarId(carStatus);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.carService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Car car = carService.getById(id);
		if(car==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(car);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param car
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Car car) {
        return super.exportXls(request, car, Car.class, "车辆管理");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Car.class);
    }

}
