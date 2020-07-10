package com.mgl.dao.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgl.bean.dto.RoleAndMenusDdo;
import com.mgl.bean.dto.RoleAndMenusDto;
import com.mgl.bean.sys.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 系统角色表 Mapper 接口
 * </p>
 *
 * @author zhaohy
 * @since 2019-09-03
 */
@Mapper
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    RoleAndMenusDto getRoleMenus(@Param("roleId") Long roleId);
    List<RoleAndMenusDdo> getList();
}
