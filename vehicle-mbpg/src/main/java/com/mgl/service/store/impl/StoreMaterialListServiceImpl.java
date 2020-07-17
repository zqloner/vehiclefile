package com.mgl.service.store.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgl.api.CommonResult;
import com.mgl.api.Constants;
import com.mgl.bean.store.StoreMaterialList;
import com.mgl.bean.store.StoreOrganizationStructure;
import com.mgl.bean.sys.SysAdmin;
import com.mgl.dao.store.StoreMaterialListMapper;
import com.mgl.service.store.StoreMaterialListService;
import com.mgl.service.store.StoreOrganizationStructureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 备件仓库清单表 服务实现类
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Service
public class StoreMaterialListServiceImpl extends ServiceImpl<StoreMaterialListMapper, StoreMaterialList> implements StoreMaterialListService {

    @Resource
    private StoreOrganizationStructureService structureService;

    @Override
    public CommonResult saveOrUpdatte(StoreMaterialList storeMaterialList, SysAdmin sysAdmin) {
        StoreOrganizationStructure structure = structureService.getById(storeMaterialList.getCurrentStoreId());
        if (storeMaterialList.getId() == null) {
            storeMaterialList.setDelFlag(Constants.DELFLAG_N0RMAL);
            storeMaterialList.setCreateTime(LocalDateTime.now());
            storeMaterialList.setUpdateTime(LocalDateTime.now());
            storeMaterialList.setCreateUid(sysAdmin.getId());
            storeMaterialList.setCreateUsername(sysAdmin.getName());
            storeMaterialList.setStoreName(structure.getName());
            save(storeMaterialList);
        } else {
            storeMaterialList.setUpdateTime(LocalDateTime.now());
            saveOrUpdate(storeMaterialList);
        }
        return CommonResult.success(null, "操作成功");
    }

    @Override
    public CommonResult delete(Long id) {
        saveOrUpdatte(getById(id).setDelFlag(Constants.DELFLAG_DELETE),null);
        return CommonResult.success("删除成功");
    }
}
