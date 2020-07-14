package com.mgl.service.repair;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgl.api.CommonResult;
import com.mgl.bean.repair.RepairProjectNames;

/**
 * <p>
 * 工时维修项目 服务类
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
public interface RepairProjectNamesService extends IService<RepairProjectNames> {

    CommonResult saveOrUpdatte(RepairProjectNames repairProjectNames, Long userId);

    CommonResult delete(Long id);
}
