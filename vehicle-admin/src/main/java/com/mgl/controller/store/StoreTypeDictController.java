package com.mgl.controller.store;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.mgl.api.CommonPage;
import com.mgl.api.CommonResult;
import com.mgl.api.Constants;
import com.mgl.bean.store.StoreTypeDict;
import com.mgl.service.store.StoreTypeDictService;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 出库入库转库类别表 前端控制器
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Controller
@RequestMapping("/storeTypeDict")
public class StoreTypeDictController {

    @Resource
    private StoreTypeDictService storeTypeDictService;

    @GetMapping("/list")
    @ResponseBody
    @ApiOperation(value = "转库类型list",notes = "转库类型list")
    public CommonResult getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                @RequestParam("type")Integer type){
        PageHelper.startPage(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(storeTypeDictService.list(new QueryWrapper<>(new StoreTypeDict().setType(type).setDelFlag(Constants.DELFLAG_N0RMAL)))));
    }


    @GetMapping("/getListNoPage")
    @ResponseBody
    @ApiOperation(value = "转库类型list不分页",notes = "转库类型list不分页")
    public CommonResult getListNoPage(@RequestParam("type")Integer type){
        return CommonResult.success(storeTypeDictService.list(new QueryWrapper<>(new StoreTypeDict().setType(type).setDelFlag(Constants.DELFLAG_N0RMAL))));
    }

    @PostMapping("/addOrUpdate")
    @ResponseBody
    @ApiOperation(value = "新增修改转库类型",notes = "新增修改转库类型")
    public CommonResult saveOrUpdatte(StoreTypeDict storeTypeDict){
        return storeTypeDictService.saveOrUpdatte(storeTypeDict);
    }

    @PostMapping("/toUpdate")
    @ResponseBody
    @ApiOperation(value = "到达修改转库类型",notes = "到达修改转库类型")
    public CommonResult toUpdate(Long id){
        return CommonResult.success(storeTypeDictService.getById(id));
    }

    @GetMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "删除转库类型",notes = "删除转库类型")
    public CommonResult delete(Long id){
        return storeTypeDictService.delete(id);
    }
}
