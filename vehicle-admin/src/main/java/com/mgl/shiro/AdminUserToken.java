package com.mgl.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by zhaohy on 2019/9/2.
 */
public class AdminUserToken extends UsernamePasswordToken {

    public AdminUserToken(String username, String password) {
        super(username,password);
    }
}
