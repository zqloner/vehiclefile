package com.mgl.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgl.api.CommonResult;
import com.mgl.api.Constants;
import com.mgl.bean.sys.SysAdmin;
import com.mgl.bean.sys.SysOrganizationStructure;
import com.mgl.dao.sys.SysOrganizationStructureMapper;
import com.mgl.service.sys.SysAdminService;
import com.mgl.service.sys.SysOrganizationStructureService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户组织架构字典表 服务实现类
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Service
public class SysOrganizationStructureServiceImpl extends ServiceImpl<SysOrganizationStructureMapper, SysOrganizationStructure> implements SysOrganizationStructureService {

    @Resource
    private SysAdminService sysAdminService;

    @Override
    public CommonResult saveOrUpdatte(SysOrganizationStructure structure,SysAdmin user) {
        if (structure.getId() == null) {
            structure.setCreateTime(LocalDateTime.now());
            structure.setUpdateTime(LocalDateTime.now());
            structure.setCreateUid(user.getId());
            structure.setCreateUsername(user.getName());
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
        List<SysOrganizationStructure> list = list(new QueryWrapper<>(new SysOrganizationStructure().setDelFlag(Constants.DELFLAG_N0RMAL).setPid(id)));
        List<SysAdmin> sysAdmins = sysAdminService.list(new QueryWrapper<>(new SysAdmin().setIsDel(Constants.DELFLAG_N0RMAL).setStatus(Constants.STATUS_AVAILABLE).setOrganizationalStructureId(id)));
        if (CollectionUtils.isNotEmpty(list)) {
            return CommonResult.failed("该组织架构下有其他组织架构,不能删除");
        } else if (CollectionUtils.isNotEmpty(sysAdmins)) {
            return CommonResult.failed("该组织架构下有用户,不能删除");
        }
        saveOrUpdate(getById(id).setDelFlag(Constants.DELFLAG_DELETE));
        return CommonResult.success(null, "删除成功");
    }
}
