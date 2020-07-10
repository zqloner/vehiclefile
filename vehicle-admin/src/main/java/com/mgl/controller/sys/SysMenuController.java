package com.mgl.controller.sys;


import com.mgl.api.CommonResult;
import com.mgl.service.sys.SysMenuService;
import com.mgl.shiro.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 系统菜单表 前端控制器
 * </p>
 *
 * @author zhaohy
 * @since 2019-09-03
 */
@Controller
@RequestMapping("/sysMenu")
@Api(value = "菜单列表",tags = "菜单列表")
public class SysMenuController {
    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("/list")
    @ResponseBody
    @ApiOperation("菜单列表")
    public CommonResult getList() {
        Long id = ShiroUtils.getSysUser().getId();

        return  CommonResult.success(sysMenuService.getlist(id));
    }


    @GetMapping("/menuList")
    @ResponseBody
    @ApiOperation("菜单列表全查-->角色新增pid")
    public CommonResult getmenuList() {
        return  CommonResult.success(sysMenuService.getMenuList());
    }

}
