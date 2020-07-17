package com.mgl.controller.store;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.mgl.api.CommonPage;
import com.mgl.api.CommonResult;
import com.mgl.api.Constants;
import com.mgl.bean.store.StoreMaterialList;
import com.mgl.bean.store.StoreOrganizationStructure;
import com.mgl.service.store.StoreMaterialListService;
import com.mgl.service.store.StoreOrganizationStructureService;
import com.mgl.shiro.ShiroUtils;
import com.mgl.util.ExcelUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 备件仓库清单表 前端控制器
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Controller
@RequestMapping("/storeMaterialList")
public class StoreMaterialListController {
    @Resource
    private StoreMaterialListService storeMaterialListService;
    @Resource
    private StoreOrganizationStructureService structureService;

    @GetMapping("/list")
    @ResponseBody
    @ApiOperation(value = "仓库物料list",notes = "仓库物料list")
    public CommonResult getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                @RequestParam(value = "storeId") Long storeId){
        PageHelper.startPage(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(storeMaterialListService.list(new QueryWrapper<>(new StoreMaterialList().setDelFlag(Constants.DELFLAG_N0RMAL).setCurrentStoreId(storeId)))));
    }


    @GetMapping("/getListNoPage")
    @ResponseBody
    @ApiOperation(value = "仓库物料list不分页",notes = "仓库物料list不分页")
    public CommonResult getListNoPage(@RequestParam(value = "storeId") Long storeId){
        return CommonResult.success(storeMaterialListService.list(new QueryWrapper<>(new StoreMaterialList().setDelFlag(Constants.DELFLAG_N0RMAL).setCurrentStoreId(storeId))));
    }

    @PostMapping("/addOrUpdate")
    @ResponseBody
    @ApiOperation(value = "新增修改仓库物料",notes = "新增修改仓库物料")
    public CommonResult saveOrUpdatte(StoreMaterialList storeMaterialList){
        return storeMaterialListService.saveOrUpdatte(storeMaterialList, ShiroUtils.getSysUser());
    }

    @GetMapping("/toUpdate")
    @ResponseBody
    @ApiOperation(value = "到达修改仓库物料",notes = "到达修改仓库物料")
    public CommonResult toUpdate(Long id){
        return CommonResult.success(storeMaterialListService.getById(id));
    }

    @GetMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "删除仓库物料",notes = "删除仓库物料")
    public CommonResult delete(Long id){
        return storeMaterialListService.delete(id);
    }

    /**
     * 批量导入
     *
     * @param file
     * @return
     */
    @PostMapping("/import")
    @ApiOperation("导入仓库物料")
    @ResponseBody
    public CommonResult upload(@RequestParam("file") MultipartFile file, @RequestParam("storeId")Long storeId) {
        StoreOrganizationStructure structure = structureService.getById(storeId);
        List<StoreMaterialList> list = ExcelUtils.readExcel("", StoreMaterialList.class, file,0);
        list.stream().forEach(x -> {
            x.setCurrentStoreId(storeId);
            x.setStoreName(structure.getName());
            x.setUpdateTime(LocalDateTime.now());
            x.setCreateTime(LocalDateTime.now());
            x.setCreateUid(ShiroUtils.getSysUser().getId());
            x.setCreateUsername(ShiroUtils.getSysUser().getName());
            x.setDelFlag(Constants.DELFLAG_N0RMAL);
        });
        return storeMaterialListService.saveBatch(list) ? CommonResult.success("添加成功！共导入" + list.size() + "条数据") : CommonResult.failed("导入失败");
    }
}
