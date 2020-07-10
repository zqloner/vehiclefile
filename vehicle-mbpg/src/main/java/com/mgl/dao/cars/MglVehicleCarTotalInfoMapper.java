package com.mgl.dao.cars;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mgl.bean.cars.MglVehicleCarTotalInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 车辆档案总表 Mapper 接口
 * </p>
 *
 * @author zhangqi
 * @since 2020-06-21
 */
@Mapper
public interface MglVehicleCarTotalInfoMapper extends BaseMapper<MglVehicleCarTotalInfo> {

    List<MglVehicleCarTotalInfo> getList(MglVehicleCarTotalInfo mglVehicleCarTotalInfo);
}
