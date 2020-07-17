package com.mgl.controller.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.mgl.api.CommonPage;
import com.mgl.api.CommonResult;
import com.mgl.api.Constants;
import com.mgl.bean.service.ServiceStationList;
import com.mgl.service.service.ServiceStationListService;
import com.mgl.shiro.ShiroUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 服务站列表 前端控制器
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Controller
@RequestMapping("/serviceStationList")
public class ServiceStationListController {
    @Resource
    private ServiceStationListService serviceStationListService;

    @GetMapping("/list")
    @ResponseBody
    @ApiOperation(value = "服务站list",notes = "服务站list")
    public CommonResult getList(ServiceStationList serviceStationList,
                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(serviceStationListService.getByConditions(serviceStationList)));
    }


    @GetMapping("/getListNoPage")
    @ResponseBody
    @ApiOperation(value = "服务站list不分页",notes = "服务站list不分页")
    public CommonResult getListNoPage(){
        return CommonResult.success(serviceStationListService.list(new QueryWrapper<>(new ServiceStationList().setDelFlag(Constants.DELFLAG_N0RMAL))));
    }

    @PostMapping("/addOrUpdate")
    @ResponseBody
    @ApiOperation(value = "新增修改服务站",notes = "新增修改服务站")
    public CommonResult saveOrUpdatte(ServiceStationList serviceStationList){
        return serviceStationListService.saveOrUpdatte(serviceStationList, ShiroUtils.getSysUser());
    }

    @GetMapping("/toUpdate")
    @ResponseBody
    @ApiOperation(value = "到达修改服务站",notes = "到达修改服务站")
    public CommonResult toUpdate(Long id){
        return CommonResult.success(serviceStationListService.getById(id));
    }

    @GetMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "删除服务站",notes = "删除服务站")
    public CommonResult delete(Long id){
        return serviceStationListService.delete(id);
    }
}
