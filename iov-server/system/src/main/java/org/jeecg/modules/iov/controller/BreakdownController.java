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
import org.jeecg.modules.iov.entity.Breakdown;
import org.jeecg.modules.iov.service.IBreakdownService;

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
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;

 /**
 * @Description: 故障码
 * @Author: jeecg-boot
 * @Date:   2020-02-21
 * @Version: V1.0
 */
@RestController
@RequestMapping("/iov/breakdown")
@Slf4j
public class BreakdownController extends JeecgController<Breakdown, IBreakdownService> {
	@Autowired
	private IBreakdownService breakdownService;
	
	/**
	 * 分页列表查询
	 *
	 * @param breakdown
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Breakdown breakdown,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Breakdown> queryWrapper = QueryGenerator.initQueryWrapper(breakdown, req.getParameterMap());
		Page<Breakdown> page = new Page<Breakdown>(pageNo, pageSize);
		IPage<Breakdown> pageList = breakdownService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	@RequestMapping("/listByCode")
	public  Result<?> listByCode(String breakdownCodes){
		return Result.ok(breakdownService.listByCode(breakdownCodes));
	}
	/**
	 *   添加
	 *
	 * @param breakdown
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Breakdown breakdown) {
		breakdownService.save(breakdown);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param breakdown
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Breakdown breakdown) {
		breakdownService.updateById(breakdown);
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
		breakdownService.removeById(id);
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
		this.breakdownService.removeByIds(Arrays.asList(ids.split(",")));
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
		Breakdown breakdown = breakdownService.getById(id);
		if(breakdown==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(breakdown);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param breakdown
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Breakdown breakdown) {
        return super.exportXls(request, breakdown, Breakdown.class, "故障码");
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
        return super.importExcel(request, response, Breakdown.class);
    }

}
