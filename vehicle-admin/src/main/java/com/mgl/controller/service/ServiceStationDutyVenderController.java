package com.mgl.controller.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.mgl.api.CommonPage;
import com.mgl.api.CommonResult;
import com.mgl.api.Constants;
import com.mgl.bean.service.ServiceStationDutyVender;
import com.mgl.service.service.ServiceStationDutyVenderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 服务站维修单参数(故障件责任厂家) 前端控制器
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Controller
@RequestMapping("/serviceStationDutyVender")
public class ServiceStationDutyVenderController {

    @Resource
    private ServiceStationDutyVenderService stationDutyVenderService;

    @GetMapping("/list")
    @ResponseBody
    @ApiOperation(value = "责任厂家故障原因list",notes = "责任厂家故障原因list")
    public CommonResult getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                @RequestParam("type")Integer type){
        PageHelper.startPage(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(stationDutyVenderService.list(new QueryWrapper<>(new ServiceStationDutyVender().setType(type).setDelFlag(Constants.DELFLAG_N0RMAL)))));
    }


    @GetMapping("/getListNoPage")
    @ResponseBody
    @ApiOperation(value = "责任厂家故障原因list不分页",notes = "责任厂家故障原因list不分页")
    public CommonResult getListNoPage(@RequestParam("type")Integer type){
        return CommonResult.success(stationDutyVenderService.list(new QueryWrapper<>(new ServiceStationDutyVender().setType(type).setDelFlag(Constants.DELFLAG_N0RMAL))));
    }

    @PostMapping("/addOrUpdate")
    @ResponseBody
    @ApiOperation(value = "新增修改责任厂家故障原因",notes = "新增修改责任厂家故障原因")
    public CommonResult saveOrUpdatte(ServiceStationDutyVender stationDutyVender){
        return stationDutyVenderService.saveOrUpdatte(stationDutyVender);
    }

    @PostMapping("/toUpdate")
    @ResponseBody
    @ApiOperation(value = "到达修改责任厂家故障原因",notes = "到达修改责任厂家故障原因")
    public CommonResult toUpdate(Long id){
        return CommonResult.success(stationDutyVenderService.getById(id));
    }

    @GetMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "删除责任厂家故障原因",notes = "删除责任厂家故障原因")
    public CommonResult delete(Long id){
        return stationDutyVenderService.delete(id);
    }

}
