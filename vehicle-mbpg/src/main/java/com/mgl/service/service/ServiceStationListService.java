package com.mgl.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgl.api.CommonResult;
import com.mgl.bean.service.ServiceStationList;
import com.mgl.bean.sys.SysAdmin;

import java.util.List;

/**
 * <p>
 * 服务站列表 服务类
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
public interface ServiceStationListService extends IService<ServiceStationList> {

    CommonResult saveOrUpdatte(ServiceStationList serviceStationList, SysAdmin sysAdmin);

    CommonResult delete(Long id);

    List<ServiceStationList> getByConditions(ServiceStationList serviceStationList);
}
