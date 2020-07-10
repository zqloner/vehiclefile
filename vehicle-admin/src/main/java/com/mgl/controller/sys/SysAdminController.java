package com.mgl.controller.sys;


import com.github.pagehelper.PageHelper;
import com.mgl.api.CommonPage;
import com.mgl.api.CommonResult;
import com.mgl.bean.sys.SysAdmin;
import com.mgl.service.sys.SysAdminService;
import com.mgl.service.sys.SysRoleService;
import com.mgl.shiro.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统管理员 前端控制器
 * </p>
 *
 * @author zhaohy
 * @since 2019-09-02
 */
@Controller
@RequestMapping("/sysAdmin")
@Api(value = "管理员账号管理",tags = "管理员账号")
public class SysAdminController {

    @Autowired
    private SysAdminService sysAdminService;
    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping("/list")
    @ResponseBody
    @ApiOperation(value = "查询管理员list",notes = "查询管理员列表")
    public CommonResult getList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(sysAdminService.getAountList()));
    }


    @PostMapping("/add")
    @ResponseBody
    @ApiOperation(value = "添加管理员", notes = "添加管理员")
    public CommonResult add(SysAdmin sysAdmin, @RequestParam("roleId") Long roleId) {
        Md5Hash md5Hash = new Md5Hash(sysAdmin.getPassword());
        sysAdmin.setPassword(md5Hash.toHex());
        return  sysAdminService.addAdmin(sysAdmin,roleId);
    }


    @GetMapping("/toUpdate")
    @ResponseBody
    @ApiOperation(value = "通过id查找帐户", notes = "通过id查找帐户")
    public CommonResult toUpdate(@RequestParam(value = "id") Long id){
        return  CommonResult.success(sysAdminService.getAcountById(id),"查找成功");
    }


    @GetMapping("/findAllroles")
    @ResponseBody
    @ApiOperation(value = "查询所有角色", notes = "查询所有角色")
    public CommonResult findAllroles(){
        return  CommonResult.success(sysRoleService.list(null),"查找成功");
    }


    @PostMapping("/update")
    @ResponseBody
    @ApiOperation(value = "修改管理员账号信息", notes = "修改管理员账号")
    public CommonResult update(SysAdmin admin,@RequestParam(value = "roleId",required = false) Long roleId){
        return sysAdminService.updateAdmin(admin,roleId);
    }

    @GetMapping("/delete")
    @ResponseBody
    @ApiOperation(value = "删除管理员账号",notes = "删除管理员账号")
    public CommonResult delete(@RequestParam("id") Long id) {
        return sysAdminService.deleteAdmin(id);
    }

    @PostMapping("/changePassword")
    @ResponseBody
    @ApiOperation(value = "重置管理员密码",notes = "重置管理员密码")
    public CommonResult changePassword(@RequestParam("id") Long id) {
        return CommonResult.success(sysAdminService.changePassword(id));
    }

    @PostMapping("/updatePwd")
    @ResponseBody
    @ApiOperation(value = "修改自己密码",notes = "修改自己密码")
    public CommonResult updatePwd(String newPwd,String oldPwd) {
        Long id = ShiroUtils.getSysUser().getId();
        return sysAdminService.updatePwd(id,newPwd,oldPwd);
    }

}
