package com.mgl.controller.fixed;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.mgl.api.CommonPage;
import com.mgl.api.CommonResult;
import com.mgl.api.Constants;
import com.mgl.bean.fixed.FixedRepaidProject;
import com.mgl.service.fixed.FixedRepaidProjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 固定维修项目费用参数 前端控制器
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Controller
@RequestMapping("/fixedRepaidProject")
public class FixedRepaidProjectController {


    @Resource
    private FixedRepaidProjectService fixedRepaidProjectService;

    @GetMapping("/list")
    @ResponseBody
    @ApiOperation(value = "固定维修项目list",notes = "固定维修项目list")
    public CommonResult getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(fixedRepaidProjectService.list(new QueryWrapper<>(new FixedRepaidProject().setDelFlag(Constants.DELFLAG_N0RMAL)))));
    }


    @GetMapping("/getListNoPage")
    @ResponseBody
    @ApiOperation(value = "固定维修项目list不分页",notes = "固定维修项目list不分页")
    public CommonResult getListNoPage(){
        return CommonResult.success(fixedRepaidProjectService.list(new QueryWrapper<>(new FixedRepaidProject().setDelFlag(Constants.DELFLAG_N0RMAL))));
    }

    @PostMapping("/addOrUpdate")
    @ResponseBody
    @ApiOperation(value = "新增修改固定维修项目",notes = "新增修改固定维修项目")
    public CommonResult saveOrUpdatte(FixedRepaidProject fixedRepaidProject){
        return fixedRepaidProjectService.saveOrUpdatte(fixedRepaidProject);
    }

    @PostMapping("/toUpdate")
    @ResponseBody
    @ApiOperation(value = "到达修改固定维修项目",notes = "到达修改固定维修项目")
    public CommonResult toUpdate(Long id){
        return CommonResult.success(fixedRepaidProjectService.getById(id));
    }

    @GetMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "删除固定维修项目",notes = "删除固定维修项目")
    public CommonResult delete(Long id){
        return fixedRepaidProjectService.delete(id);
    }
}
