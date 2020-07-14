package com.mgl.service.repair.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgl.api.CommonResult;
import com.mgl.api.Constants;
import com.mgl.bean.repair.RepairHourName;
import com.mgl.dao.repair.RepairHourNameMapper;
import com.mgl.service.repair.RepairHourNameService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 维修工时参数管理 服务实现类
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Service
public class RepairHourNameServiceImpl extends ServiceImpl<RepairHourNameMapper, RepairHourName> implements RepairHourNameService {

    @Override
    public CommonResult saveOrUpdatte(RepairHourName repairHourName,Long userId) {
        if (repairHourName.getId() == null) {
            repairHourName.setDelFlag(Constants.DELFLAG_N0RMAL);
            repairHourName.setCreateTime(LocalDateTime.now());
            repairHourName.setUpdateTime(LocalDateTime.now());
            repairHourName.setCreateUserid(userId);
            save(repairHourName);
        } else {
            repairHourName.setUpdateTime(LocalDateTime.now());
            saveOrUpdate(repairHourName);
        }
        return CommonResult.success(null, "操作成功");
    }

    @Override
    public CommonResult delete(Long id) {
        saveOrUpdatte(getById(id).setDelFlag(Constants.DELFLAG_DELETE),null);
        return CommonResult.success("删除成功");
    }
}
