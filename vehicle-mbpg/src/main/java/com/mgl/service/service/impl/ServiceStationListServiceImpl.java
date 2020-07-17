package com.mgl.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgl.api.CommonResult;
import com.mgl.api.Constants;
import com.mgl.bean.service.ServiceStationList;
import com.mgl.bean.sys.SysAdmin;
import com.mgl.dao.service.ServiceStationListMapper;
import com.mgl.service.service.ServiceStationListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 服务站列表 服务实现类
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Service
public class ServiceStationListServiceImpl extends ServiceImpl<ServiceStationListMapper, ServiceStationList> implements ServiceStationListService {

    @Resource
    private ServiceStationListMapper serviceStationListMapper;

    @Override
    public CommonResult saveOrUpdatte(ServiceStationList serviceStationList, SysAdmin sysAdmin) {
        if (serviceStationList.getId() == null) {
            serviceStationList.setDelFlag(Constants.DELFLAG_N0RMAL);
            serviceStationList.setCreateTime(LocalDateTime.now());
            serviceStationList.setUpdateTime(LocalDateTime.now());
            serviceStationList.setCreateUserid(sysAdmin.getId());
            serviceStationList.setCreateUsername(sysAdmin.getName());
            save(serviceStationList);
        } else {
            serviceStationList.setUpdateTime(LocalDateTime.now());
            saveOrUpdate(serviceStationList);
        }
        return CommonResult.success(null, "操作成功");
    }

    @Override
    public CommonResult delete(Long id) {
        saveOrUpdatte(getById(id).setDelFlag(Constants.DELFLAG_DELETE),null);
        return CommonResult.success("删除成功");
    }

    @Override
    public List<ServiceStationList> getByConditions(ServiceStationList serviceStationList) {
        return serviceStationListMapper.getByConditions(serviceStationList);
    }
}
