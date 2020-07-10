package com.mgl.service.store.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgl.api.CommonResult;
import com.mgl.api.Constants;
import com.mgl.bean.store.StoreMaterialList;
import com.mgl.bean.store.StoreOrganizationStructure;
import com.mgl.bean.sys.SysAdmin;
import com.mgl.dao.store.StoreOrganizationStructureMapper;
import com.mgl.service.store.StoreMaterialListService;
import com.mgl.service.store.StoreOrganizationStructureService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 仓库组织架构 服务实现类
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Service
public class StoreOrganizationStructureServiceImpl extends ServiceImpl<StoreOrganizationStructureMapper, StoreOrganizationStructure> implements StoreOrganizationStructureService {

    @Resource
    private StoreMaterialListService materialListService;

    @Override
    public CommonResult saveOrUpdatte(StoreOrganizationStructure structure,SysAdmin user) {
        if (structure.getId() == null) {
            structure.setCreateTime(LocalDateTime.now());
            structure.setUpdateTime(LocalDateTime.now());
            structure.setCeateUid(user.getId());
            structure.setDelFlag(Constants.DELFLAG_N0RMAL);
            if (structure.getPid()==null) {
                structure.setPid(Constants.TREE_SECOND_ROOT);
            }
            save(structure);
        } else {
            structure.setUpdateTime(LocalDateTime.now());
            saveOrUpdate(structure);
        }
        return CommonResult.success(null, "操作成功");
    }

    @Override
    public CommonResult delete(Long id) {
        if (id == 1) {
            return CommonResult.failed("基础组织架构,不能删除");
        }
        List<StoreOrganizationStructure> list = list(new QueryWrapper<>(new StoreOrganizationStructure().setDelFlag(Constants.DELFLAG_N0RMAL).setPid(id)));
        List<StoreMaterialList> materialLists = materialListService.list(new QueryWrapper<>(new StoreMaterialList().setDelFlag(Constants.DELFLAG_N0RMAL).setCurrentStoreId(id)));
        if (CollectionUtils.isNotEmpty(list)) {
            return CommonResult.failed("该组织架构下有其他组织架构,不能删除");
        } else if (CollectionUtils.isNotEmpty(materialLists)) {
            return CommonResult.failed("该组织架构下有物料,不能删除");
        }
        saveOrUpdate(getById(id).setDelFlag(Constants.DELFLAG_DELETE));
        return CommonResult.success(null, "删除成功");
    }
}
