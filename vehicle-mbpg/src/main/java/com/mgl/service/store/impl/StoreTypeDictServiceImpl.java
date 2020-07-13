package com.mgl.service.store.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgl.api.CommonResult;
import com.mgl.api.Constants;
import com.mgl.bean.store.StoreTypeDict;
import com.mgl.dao.store.StoreTypeDictMapper;
import com.mgl.service.store.StoreMaterialListService;
import com.mgl.service.store.StoreTypeDictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 出库入库转库类别表 服务实现类
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Service
public class StoreTypeDictServiceImpl extends ServiceImpl<StoreTypeDictMapper, StoreTypeDict> implements StoreTypeDictService {

    @Resource
    private StoreMaterialListService materialListService;

    @Override
    public CommonResult saveOrUpdatte(StoreTypeDict storeTypeDictr) {
        if (storeTypeDictr.getId() == null) {
            storeTypeDictr.setDelFlag(Constants.DELFLAG_N0RMAL);
            save(storeTypeDictr);
        } else {
            saveOrUpdate(storeTypeDictr);
        }
        return CommonResult.success(null, "操作成功");
    }

    @Override
    public CommonResult delete(Long id) {
        saveOrUpdatte(getById(id).setDelFlag(Constants.DELFLAG_DELETE));
        return CommonResult.success("基础组织架构,不能删除");
    }
}

