package com.mgl.bean.service;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 服务站维修单参数(故障件责任厂家)
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ServiceStationDutyVender implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 故障原因
     */
    private String venderName;

    /**
     * 服务站名称
     */
    private Long stationId;

    /**
     * 0为正常；1为删除
     */
    private Integer delFlag;


}
