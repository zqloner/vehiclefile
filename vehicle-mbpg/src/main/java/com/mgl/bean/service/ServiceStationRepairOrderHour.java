package com.mgl.bean.service;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 服务站维修单工时和维修项目
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ServiceStationRepairOrderHour implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 服务站维修报单id
     */
    private Long serviceStationRepairOrderId;

    /**
     * 维修项目
     */
    private String repairProject;

    /**
     * 初次维修工时
     */
    private Double firstRepairHour;

    /**
     * 累加工时
     */
    private Double accumulationHour;

    /**
     * 维修适量
     */
    private Integer repairCount;

    /**
     * 总工时数量
     */
    private Double totalHourCount;

    /**
     * 工时费
     */
    private Double hourCost;

    /**
     * 0为正常，1为删除
     */
    private Integer delFlag;


}
