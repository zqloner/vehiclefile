package com.mgl.service.cars;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgl.api.CommonResult;
import com.mgl.bean.cars.MglVehicleCarInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangqi
 * @since 2020-06-21
 */
public interface MglVehicleCarInfoService extends IService<MglVehicleCarInfo> {

    List<MglVehicleCarInfo> getList(MglVehicleCarInfo mglVehicleCarInfo);

    CommonResult addOrUpdate(MglVehicleCarInfo mglVehicleCarInfo);

    Boolean deleteById(Long id);
}
