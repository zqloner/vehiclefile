package com.mgl.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgl.api.CommonResult;
import com.mgl.bean.dto.RoleAndMenusDdo;
import com.mgl.bean.sys.SysRole;

import java.util.List;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 *
 * @author zhaohy
 * @since 2019-09-03
 */
public interface SysRoleService extends IService<SysRole> {

    //查询角色列表
    public  List<RoleAndMenusDdo> getList();

    //添加角色
    CommonResult addRole(SysRole role, List<Long> ids);

    //编辑角色
    CommonResult toUpdateRole(Long id);

    //编辑角色
    CommonResult updateRole(SysRole role, List<Long> ids);

    //删除角色
    CommonResult deleteRole(Long id);

}
