package com.mgl.dao.cars;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgl.bean.cars.MglVehicleCarInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangqi
 * @since 2020-06-21
 */
@Mapper
public interface MglVehicleCarInfoMapper extends BaseMapper<MglVehicleCarInfo> {

    List<MglVehicleCarInfo> getList(MglVehicleCarInfo mglVehicleCarInfo);
}
