package com.mgl.service.repair.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgl.api.CommonResult;
import com.mgl.api.Constants;
import com.mgl.bean.repair.RepairProjectNames;
import com.mgl.dao.repair.RepairProjectNamesMapper;
import com.mgl.service.repair.RepairProjectNamesService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 工时维修项目 服务实现类
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Service
public class RepairProjectNamesServiceImpl extends ServiceImpl<RepairProjectNamesMapper, RepairProjectNames> implements RepairProjectNamesService {

    @Override
    public CommonResult saveOrUpdatte(RepairProjectNames repairProjectNames, Long userId) {
        if (repairProjectNames.getId() == null) {
            repairProjectNames.setDelFlag(Constants.DELFLAG_N0RMAL);
            repairProjectNames.setCreateTime(LocalDateTime.now());
            repairProjectNames.setUpdateTime(LocalDateTime.now());
            repairProjectNames.setCreateUid(userId);
            save(repairProjectNames);
        } else {
            repairProjectNames.setUpdateTime(LocalDateTime.now());
            saveOrUpdate(repairProjectNames);
        }
        return CommonResult.success(null, "操作成功");
    }

    @Override
    public CommonResult delete(Long id) {
        saveOrUpdatte(getById(id).setDelFlag(Constants.DELFLAG_DELETE),null);
        return CommonResult.success("删除成功");
    }
}
