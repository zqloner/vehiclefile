package com.mgl.service.store;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgl.api.CommonResult;
import com.mgl.bean.store.StoreMaterialList;
import com.mgl.bean.sys.SysAdmin;

/**
 * <p>
 * 备件仓库清单表 服务类
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
public interface StoreMaterialListService extends IService<StoreMaterialList> {

    CommonResult saveOrUpdatte(StoreMaterialList storeMaterialList, SysAdmin sysAdmin);

    CommonResult delete(Long id);
}
