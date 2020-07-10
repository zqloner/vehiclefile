package com.mgl.controller.login;


import com.mgl.api.CommonResult;
import com.mgl.shiro.AdminUserToken;
import com.mgl.shiro.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  admin用户登录controller
 * Created by zhaohy on 2019/9/2.
 */
@Controller
@Api(value = "管理员登录",tags = "管理员登录")
public class AdminLoginController{

    @PostMapping("/adminlogin")
    @ResponseBody
    @ApiOperation(value = "管理员登录" ,notes = "管理员登录")
    @ApiImplicitParams({@ApiImplicitParam(name = "username",value = "登录名称",required = true,dataType = "String",paramType = "query"),
                        @ApiImplicitParam(name = "password",value = "密码",required = true,dataType = "String",paramType = "query")})
    public CommonResult ajaxLogin(String username, String password) {
        AdminUserToken token = new AdminUserToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            System.out.println(ShiroUtils.getSysUser().toString());
            return CommonResult.success(null);
        } catch (Exception e) {
            String msg = "用户名或密码错误";
            return CommonResult.failed(msg);
        }
    }
}
