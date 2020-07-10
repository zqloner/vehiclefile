package com.mgl.dao.service;

import com.mgl.bean.service.ServiceStationReason;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 服务站维修单参数(故障原因) Mapper 接口
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Mapper
public interface ServiceStationReasonMapper extends BaseMapper<ServiceStationReason> {

}
