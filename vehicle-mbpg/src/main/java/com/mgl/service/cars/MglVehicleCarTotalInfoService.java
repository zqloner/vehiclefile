package com.mgl.service.cars;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgl.api.CommonResult;
import com.mgl.bean.cars.MglVehicleCarTotalInfo;

import java.util.List;

/**
 * <p>
 * 车辆档案总表 服务类
 * </p>
 *
 * @author zhangqi
 * @since 2020-06-21
 */
public interface MglVehicleCarTotalInfoService extends IService<MglVehicleCarTotalInfo> {

    List<MglVehicleCarTotalInfo> getList(MglVehicleCarTotalInfo mglVehicleCarTotalInfo);

    CommonResult addOrUpdate(MglVehicleCarTotalInfo mglVehicleCarTotalInfo);

    Boolean deleteById(Long id);
}
