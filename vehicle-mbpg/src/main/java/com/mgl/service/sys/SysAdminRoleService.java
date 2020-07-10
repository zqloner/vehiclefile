package com.mgl.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgl.bean.sys.SysAdminRole;
import com.mgl.bean.sys.SysMenu;

import java.util.List;

/**
 * <p>
 * 系统管理员角色中间表 服务类
 * </p>
 *
 * @author zhaohy
 * @since 2019-09-03
 */
public interface SysAdminRoleService extends IService<SysAdminRole> {

    List<SysMenu> getUserMenus(Long id, Integer type);
}
