package com.mgl.service.store;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgl.api.CommonResult;
import com.mgl.bean.store.StoreOrganizationStructure;
import com.mgl.bean.sys.SysAdmin;

/**
 * <p>
 * 仓库组织架构 服务类
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
public interface StoreOrganizationStructureService extends IService<StoreOrganizationStructure> {

    CommonResult saveOrUpdatte(StoreOrganizationStructure structure, SysAdmin user);

    CommonResult delete(Long id);
}
