package com.mgl.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgl.bean.dto.SysMenuDto;
import com.mgl.bean.sys.SysMenu;
import com.mgl.dao.sys.SysMenuMapper;
import com.mgl.service.sys.SysAdminRoleService;
import com.mgl.service.sys.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统菜单表 服务实现类
 * </p>
 *
 * @author zhaohy
 * @since 2019-09-04
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SysAdminRoleService adminRoleService;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> getlist(Long id) {
        //系统管理员返回所有权限
        if (id == 1) {
            return list(new QueryWrapper<>(new SysMenu().setType(1).setStatus(0)));
        }
        return adminRoleService.getUserMenus(id,1);
    }

    @Override
    public List<SysMenuDto> getMenuList() {
        return sysMenuMapper.getMyMenus();
    }
}
