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
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.iov.entity.CarStatusLog;
import org.jeecg.modules.iov.service.ICarStatusLogService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;

 /**
 * @Description: 行车状态记录
 * @Author: jeecg-boot
 * @Date:   2020-02-27
 * @Version: V1.0
 */
@RestController
@RequestMapping("/iov/carStatusLog")
@Slf4j
public class CarStatusLogController extends JeecgController<CarStatusLog, ICarStatusLogService> {
	@Autowired
	private ICarStatusLogService carStatusLogService;
	
	/**
	 * 分页列表查询
	 *
	 * @param carStatusLog
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CarStatusLog carStatusLog,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CarStatusLog> queryWrapper = QueryGenerator.initQueryWrapper(carStatusLog, req.getParameterMap());
		Page<CarStatusLog> page = new Page<CarStatusLog>(pageNo, pageSize);
		IPage<CarStatusLog> pageList = carStatusLogService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param carStatusLog
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CarStatusLog carStatusLog) {
		carStatusLogService.save(carStatusLog);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param carStatusLog
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CarStatusLog carStatusLog) {
		carStatusLogService.updateById(carStatusLog);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		carStatusLogService.removeById(id);
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
		this.carStatusLogService.removeByIds(Arrays.asList(ids.split(",")));
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
		CarStatusLog carStatusLog = carStatusLogService.getById(id);
		if(carStatusLog==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(carStatusLog);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param carStatusLog
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CarStatusLog carStatusLog) {
        return super.exportXls(request, carStatusLog, CarStatusLog.class, "行车状态记录");
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
        return super.importExcel(request, response, CarStatusLog.class);
    }

}
