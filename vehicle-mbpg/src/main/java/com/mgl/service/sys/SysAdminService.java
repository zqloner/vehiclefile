package com.mgl.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgl.api.CommonResult;
import com.mgl.bean.dto.SysAdminDto;
import com.mgl.bean.sys.SysAdmin;

import java.util.List;


/**
 * <p>
 * 系统管理员 服务类
 * </p>
 *
 * @author zhaohy
 * @since 2019-09-02
 */
public interface SysAdminService extends IService<SysAdmin> {

    SysAdmin fingUserByUserLoginName(String loginName);

    //查询管理员列表
    List<SysAdminDto> getAountList();
    //通过id查找SysAdmin
    SysAdminDto getAcountById(Long id);
    //添加管理员
    CommonResult addAdmin(SysAdmin admin, Long id);

    //编辑管理员
    CommonResult updateAdmin(SysAdmin admin, Long id);

    //删除管理员
    CommonResult deleteAdmin(Long id);

    //重置密码
    CommonResult changePassword(Long id);

    CommonResult updatePwd(Long id, String newPwd, String oldPwd);
}
