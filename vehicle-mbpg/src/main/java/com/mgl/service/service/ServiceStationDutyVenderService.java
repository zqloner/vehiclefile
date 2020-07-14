package com.mgl.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgl.api.CommonResult;
import com.mgl.bean.service.ServiceStationDutyVender;

/**
 * <p>
 * 服务站维修单参数(故障件责任厂家) 服务类
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
public interface ServiceStationDutyVenderService extends IService<ServiceStationDutyVender> {

    CommonResult saveOrUpdatte(ServiceStationDutyVender stationDutyVender);

    CommonResult delete(Long id);
}
