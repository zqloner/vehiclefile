package com.mgl.service.cars.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgl.api.CommonResult;
import com.mgl.api.Constants;
import com.mgl.bean.cars.MglVehicleCarInfo;
import com.mgl.dao.cars.MglVehicleCarInfoMapper;
import com.mgl.service.cars.MglVehicleCarInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangqi
 * @since 2020-06-21
 */
@Service
public class MglVehicleCarInfoServiceImpl extends ServiceImpl<MglVehicleCarInfoMapper, MglVehicleCarInfo> implements MglVehicleCarInfoService {

    @Resource
    private MglVehicleCarInfoMapper mglVehicleCarInfoMapper;

    @Override
    public List<MglVehicleCarInfo> getList(MglVehicleCarInfo mglVehicleCarInfo) {
        return mglVehicleCarInfoMapper.getList(mglVehicleCarInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult addOrUpdate(MglVehicleCarInfo mglVehicleCarInfo) {
        return saveOrUpdate(mglVehicleCarInfo)?CommonResult.success(null,"操作成功"):CommonResult.failed("操作失败");
    }

    @Override
    public Boolean deleteById(Long id) {
        return saveOrUpdate(getById(id).setDelFlag(Constants.DELFLAG_N0RMAL));
    }
}
