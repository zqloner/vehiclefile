package com.mgl.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgl.bean.sys.SysAdminRole;
import com.mgl.bean.sys.SysMenu;
import com.mgl.dao.sys.SysAdminRoleMapper;
import com.mgl.service.sys.SysAdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统管理员角色中间表 服务实现类
 * </p>
 *
 * @author zhaohy
 * @since 2019-09-03
 */
@Service
public class SysAdminRoleServiceImpl extends ServiceImpl<SysAdminRoleMapper, SysAdminRole> implements SysAdminRoleService {
    @Autowired
    private SysAdminRoleMapper mapper;

    @Override
    public List<SysMenu> getUserMenus(Long id, Integer type) {
        return mapper.getUserMenus(id,type);
    }
}
