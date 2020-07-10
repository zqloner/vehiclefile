package com.mgl.dao.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgl.bean.dto.SysMenuDto;
import com.mgl.bean.sys.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 系统菜单表 Mapper 接口
 * </p>
 *
 * @author zhaohy
 * @since 2019-09-04
 */
@Mapper
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenuDto> getMyMenus();
}
