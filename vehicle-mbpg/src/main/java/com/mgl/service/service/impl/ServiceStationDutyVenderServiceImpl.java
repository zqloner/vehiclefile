package com.mgl.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgl.api.CommonResult;
import com.mgl.api.Constants;
import com.mgl.bean.service.ServiceStationDutyVender;
import com.mgl.dao.service.ServiceStationDutyVenderMapper;
import com.mgl.service.service.ServiceStationDutyVenderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务站维修单参数(故障件责任厂家) 服务实现类
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Service
public class ServiceStationDutyVenderServiceImpl extends ServiceImpl<ServiceStationDutyVenderMapper, ServiceStationDutyVender> implements ServiceStationDutyVenderService {

    @Override
    public CommonResult saveOrUpdatte(ServiceStationDutyVender stationDutyVender) {
        if (stationDutyVender.getId() == null) {
            stationDutyVender.setDelFlag(Constants.DELFLAG_N0RMAL);
            save(stationDutyVender);
        } else {
            saveOrUpdate(stationDutyVender);
        }
        return CommonResult.success(null, "操作成功");
    }

    @Override
    public CommonResult delete(Long id) {
        saveOrUpdatte(getById(id).setDelFlag(Constants.DELFLAG_DELETE));
        return CommonResult.success("删除成功");
    }
}
