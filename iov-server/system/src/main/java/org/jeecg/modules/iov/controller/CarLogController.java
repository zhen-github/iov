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

import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.iov.entity.CarLog;
import org.jeecg.modules.iov.service.ICarLogService;

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
 * @Description: 行车记录
 * @Author: jeecg-boot
 * @Date:   2020-02-27
 * @Version: V1.0
 */
@RestController
@RequestMapping("/iov/carLog")
@Slf4j
public class CarLogController extends JeecgController<CarLog, ICarLogService> {
	@Autowired
	private ICarLogService carLogService;
	
	/**
	 * 分页列表查询
	 *
	 * @param carLog
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CarLog carLog,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CarLog> queryWrapper = QueryGenerator.initQueryWrapper(carLog, req.getParameterMap(),"a");
		Page<CarLog> page = new Page<CarLog>(pageNo, pageSize);
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		queryWrapper.eq("b.create_by",sysUser.getUsername());
		IPage<CarLog> pageList = carLogService.list(page, queryWrapper);
		return Result.ok(pageList);
	}

	 @GetMapping(value = "/queryPageList")
	 public Result<?> queryPage(CarLog carLog,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,@RequestParam(name="motorcadeCode")String orgCode,
									HttpServletRequest req) {
		 QueryWrapper<CarLog> queryWrapper = QueryGenerator.initQueryWrapper(carLog, req.getParameterMap(),"a");
		 Page<CarLog> page = new Page<CarLog>(pageNo, pageSize);
		 queryWrapper.eq("b.sys_org_code",orgCode);
		 IPage<CarLog> pageList = carLogService.list(page, queryWrapper);
		 return Result.ok(pageList);
	 }
	
	/**
	 *   添加
	 *
	 * @param carLog
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CarLog carLog) {
		carLogService.save(carLog);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param carLog
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CarLog carLog) {
		carLogService.updateById(carLog);
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
		carLogService.removeById(id);
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
		this.carLogService.removeByIds(Arrays.asList(ids.split(",")));
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
		CarLog carLog = carLogService.getById(id);
		if(carLog==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(carLog);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param carLog
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CarLog carLog) {
        return super.exportXls(request, carLog, CarLog.class, "行车记录");
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
        return super.importExcel(request, response, CarLog.class);
    }

}
