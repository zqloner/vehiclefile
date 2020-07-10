package com.mgl.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgl.api.CommonResult;
import com.mgl.bean.sys.SysAdmin;
import com.mgl.bean.sys.SysOrganizationStructure;

/**
 * <p>
 * 用户组织架构字典表 服务类
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
public interface SysOrganizationStructureService extends IService<SysOrganizationStructure> {

    CommonResult saveOrUpdatte(SysOrganizationStructure structure, SysAdmin user);

    CommonResult delete(Long id);
}
