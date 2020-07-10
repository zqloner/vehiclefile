package com.mgl.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgl.bean.dto.SysMenuDto;
import com.mgl.bean.sys.SysMenu;

import java.util.List;

/**
 * <p>
 * 系统菜单表 服务类
 * </p>
 *
 * @author zhaohy
 * @since 2019-09-04
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> getlist(Long id);

    List<SysMenuDto> getMenuList();
}
