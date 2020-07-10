package com.mgl.dao.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgl.bean.dto.SysAdminDto;
import com.mgl.bean.sys.SysAdmin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 系统管理员 Mapper 接口
 * </p>
 *
 * @author zhaohy
 * @since 2019-09-02
 */
@Mapper
@Repository
public interface SysAdminMapper extends BaseMapper<SysAdmin> {

    List<SysAdminDto> getAountList();
    SysAdminDto getAcountById(Long id);
}
