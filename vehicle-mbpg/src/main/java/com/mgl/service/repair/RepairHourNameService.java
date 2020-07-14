package com.mgl.service.repair;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgl.api.CommonResult;
import com.mgl.bean.repair.RepairHourName;

/**
 * <p>
 * 维修工时参数管理 服务类
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
public interface RepairHourNameService extends IService<RepairHourName> {


    CommonResult saveOrUpdatte(RepairHourName repairHourName,Long userId);

    CommonResult delete(Long id);
}
