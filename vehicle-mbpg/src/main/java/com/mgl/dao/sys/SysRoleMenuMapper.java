package com.mgl.dao.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgl.bean.sys.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 系统角色菜单中间表 Mapper 接口
 * </p>
 *
 * @author zhaohy
 * @since 2019-09-03
 */
@Mapper
@Repository
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

}
