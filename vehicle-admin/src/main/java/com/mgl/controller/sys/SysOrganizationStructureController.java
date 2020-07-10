package com.mgl.controller.sys;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.mgl.api.CommonPage;
import com.mgl.api.CommonResult;
import com.mgl.api.Constants;
import com.mgl.bean.sys.SysOrganizationStructure;
import com.mgl.service.sys.SysOrganizationStructureService;
import com.mgl.shiro.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 用户组织架构字典表 前端控制器
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Controller
@RequestMapping("/sysOrganizationStructure")
@Api(value = "用户组织架构",tags = "用户组织架构")
public class SysOrganizationStructureController {
    @Resource
    private SysOrganizationStructureService structureService;

    @GetMapping("/list")
    @ResponseBody
    @ApiOperation(value = "用户组织list",notes = "用户组织list")
    public CommonResult getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(structureService.list(new QueryWrapper<>(new SysOrganizationStructure().setDelFlag(Constants.DELFLAG_N0RMAL)))));
    }


    @GetMapping("/getListNoPage")
    @ResponseBody
    @ApiOperation(value = "用户组织list不分页",notes = "用户组织list不分页")
    public CommonResult getListNoPage(){
        return CommonResult.success(structureService.list(new QueryWrapper<>(new SysOrganizationStructure().setDelFlag(Constants.DELFLAG_N0RMAL))));
    }

    @PostMapping("/addOrUpdate")
    @ResponseBody
    @ApiOperation(value = "新增修改组织架构",notes = "新增修改组织架构")
    public CommonResult saveOrUpdatte(SysOrganizationStructure structure){
        return structureService.saveOrUpdatte(structure, ShiroUtils.getSysUser());
    }

    @PostMapping("/toUpdate")
    @ResponseBody
    @ApiOperation(value = "新增修改组织架构",notes = "新增修改组织架构")
    public CommonResult toUpdate(Long id){
        return CommonResult.success(structureService.getById(id));
    }

    @GetMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "删除组织架构",notes = "删除组织架构")
    public CommonResult delete(Long id){
        return structureService.delete(id);
    }

}
