package com.mgl.dao.service;

import com.mgl.bean.service.ServiceStationList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 服务站列表 Mapper 接口
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Mapper
public interface ServiceStationListMapper extends BaseMapper<ServiceStationList> {

    List<ServiceStationList> getByConditions(ServiceStationList serviceStationList);
}
