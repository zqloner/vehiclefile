package com.mgl.controller.repair;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.mgl.api.CommonPage;
import com.mgl.api.CommonResult;
import com.mgl.api.Constants;
import com.mgl.bean.repair.RepairProjectNames;
import com.mgl.service.repair.RepairProjectNamesService;
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
 * 工时维修项目 前端控制器
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Controller
@RequestMapping("/repairProjectNames")
public class RepairProjectNamesController {

    @Resource
    private RepairProjectNamesService repairProjectNamesService;

    @GetMapping("/list")
    @ResponseBody
    @ApiOperation(value = "维修工时项目list",notes = "维修工时项目list")
    public CommonResult getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                @RequestParam(value = "hourId") Long hourId){
        PageHelper.startPage(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(repairProjectNamesService.list(new QueryWrapper<>(new RepairProjectNames().setDelFlag(Constants.DELFLAG_N0RMAL).setHourId(hourId)))));
    }


    @GetMapping("/getListNoPage")
    @ResponseBody
    @ApiOperation(value = "维修工时项目list不分页",notes = "维修工时项目list不分页")
    public CommonResult getListNoPage(@RequestParam(value = "hourId") Long hourId){
        return CommonResult.success(repairProjectNamesService.list(new QueryWrapper<>(new RepairProjectNames().setDelFlag(Constants.DELFLAG_N0RMAL).setHourId(hourId))));
    }

    @PostMapping("/addOrUpdate")
    @ResponseBody
    @ApiOperation(value = "新增修改维修工时项目",notes = "新增修改维修工时项目")
    public CommonResult saveOrUpdatte(RepairProjectNames repairProjectNames){
        return repairProjectNamesService.saveOrUpdatte(repairProjectNames, ShiroUtils.getSysUser().getId());
    }

    @PostMapping("/toUpdate")
    @ResponseBody
    @ApiOperation(value = "到达修改维修工时项目",notes = "到达修改维修工时项目")
    public CommonResult toUpdate(Long id){
        return CommonResult.success(repairProjectNamesService.getById(id));
    }

    @GetMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "删除维修工时项目",notes = "删除维修工时项目")
    public CommonResult delete(Long id){
        return repairProjectNamesService.delete(id);
    }

    /**
     * 批量导入
     *
     * @param file
     * @return
     */
    @PostMapping("/import")
    @ApiOperation("导入工时项目")
    @ResponseBody
    public CommonResult upload(@RequestParam("file")MultipartFile file,@RequestParam("hourId")Long hourId) {
//        public static <T> List<T> readExcel(String path, Class<T> cls, MultipartFile file)
        String path = "";
        List<RepairProjectNames> list = ExcelUtils.readExcel(path, RepairProjectNames.class, file,0);
        list.stream().forEach(x -> {
            x.setHourId(hourId);
            x.setUpdateTime(LocalDateTime.now());
            x.setCreateTime(LocalDateTime.now());
            x.setCreateUid(ShiroUtils.getSysUser().getId());
            x.setDelFlag(Constants.DELFLAG_N0RMAL);
        });
        return repairProjectNamesService.saveBatch(list) ? CommonResult.success("添加成功！共导入" + list.size() + "条数据") : CommonResult.failed("导入失败");
    }

}
