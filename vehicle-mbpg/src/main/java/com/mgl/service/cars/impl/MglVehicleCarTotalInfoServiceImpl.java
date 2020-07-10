package com.mgl.service.cars.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgl.api.CommonResult;
import com.mgl.api.Constants;
import com.mgl.bean.cars.MglVehicleCarTotalInfo;
import com.mgl.dao.cars.MglVehicleCarTotalInfoMapper;
import com.mgl.service.cars.MglVehicleCarTotalInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 车辆档案总表 服务实现类
 * </p>
 *
 * @author zhangqi
 * @since 2020-06-21
 */
@Service
public class MglVehicleCarTotalInfoServiceImpl extends ServiceImpl<MglVehicleCarTotalInfoMapper, MglVehicleCarTotalInfo> implements MglVehicleCarTotalInfoService {

    @Resource
    private MglVehicleCarTotalInfoMapper mglVehicleCarTotalInfoMapper;

    @Override
    public List<MglVehicleCarTotalInfo> getList(MglVehicleCarTotalInfo mglVehicleCarTotalInfo) {
        return mglVehicleCarTotalInfoMapper.getList(mglVehicleCarTotalInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommonResult addOrUpdate(MglVehicleCarTotalInfo mglVehicleCarTotalInfo) {
        return saveOrUpdate(mglVehicleCarTotalInfo)?CommonResult.success(null,"操作成功"):CommonResult.failed("操作失败");
    }

    @Override
    public Boolean deleteById(Long id) {
        return saveOrUpdate(getById(id).setDelFlag(Constants.DELFLAG_DELETE));
    }
}
