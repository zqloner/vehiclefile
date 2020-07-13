package com.mgl.service.store;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgl.api.CommonResult;
import com.mgl.bean.store.StoreTypeDict;

/**
 * <p>
 * 出库入库转库类别表 服务类
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
public interface StoreTypeDictService extends IService<StoreTypeDict> {

    CommonResult saveOrUpdatte(StoreTypeDict storeTypeDict);

    CommonResult delete(Long id);
}
