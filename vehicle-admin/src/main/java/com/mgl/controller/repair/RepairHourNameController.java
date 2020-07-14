package com.mgl.controller.repair;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.mgl.api.CommonPage;
import com.mgl.api.CommonResult;
import com.mgl.api.Constants;
import com.mgl.bean.repair.RepairHourName;
import com.mgl.service.repair.RepairHourNameService;
import com.mgl.shiro.ShiroUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 维修工时参数管理 前端控制器
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Controller
@RequestMapping("/repairHourName")
public class RepairHourNameController {

    @Resource
    private RepairHourNameService repairHourNameService;

    @GetMapping("/list")
    @ResponseBody
    @ApiOperation(value = "维修工时list",notes = "维修工时list")
    public CommonResult getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(repairHourNameService.list(new QueryWrapper<>(new RepairHourName().setDelFlag(Constants.DELFLAG_N0RMAL)))));
    }


    @GetMapping("/getListNoPage")
    @ResponseBody
    @ApiOperation(value = "维修工时list不分页",notes = "维修工时list不分页")
    public CommonResult getListNoPage(){
        return CommonResult.success(repairHourNameService.list(new QueryWrapper<>(new RepairHourName().setDelFlag(Constants.DELFLAG_N0RMAL))));
    }

    @PostMapping("/addOrUpdate")
    @ResponseBody
    @ApiOperation(value = "新增修改维修工时",notes = "新增修改维修工时")
    public CommonResult saveOrUpdatte(RepairHourName repairHourName){
        return repairHourNameService.saveOrUpdatte(repairHourName, ShiroUtils.getSysUser().getId());
    }

    @PostMapping("/toUpdate")
    @ResponseBody
    @ApiOperation(value = "到达修改维修工时",notes = "到达修改维修工时")
    public CommonResult toUpdate(Long id){
        return CommonResult.success(repairHourNameService.getById(id));
    }

    @GetMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "删除维修工时",notes = "删除维修工时")
    public CommonResult delete(Long id){
        return repairHourNameService.delete(id);
    }
}
