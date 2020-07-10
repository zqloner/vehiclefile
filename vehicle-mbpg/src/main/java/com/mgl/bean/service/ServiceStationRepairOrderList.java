package com.mgl.bean.service;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 服务站维修报单
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ServiceStationRepairOrderList implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 服务站信息id
     */
    private Long serviceStationId;

    /**
     * 本部用户id
     */
    private Integer cadreUid;

    /**
     * 车辆信息id
     */
    private Long vehicleInfoId;

    /**
     * 故障地点
     */
    private String faultAddress;

    /**
     * 已经运行里程
     */
    private Double runMileage;

    /**
     * 服务站外出技师
     */
    private String serviceStationOutTechnician;

    /**
     * 是否索赔(0为索赔,1为不索赔)
     */
    private Integer isCompensate;

    /**
     * 故障责任厂家
     */
    private Long serviceStationDutyId;

    /**
     * 是否为批量故障(0为是,1为不是)
     */
    private Integer isMoreBreakdown;

    /**
     * 处理时间
     */
    private LocalDateTime repairTime;

    /**
     * 故障现象
     */
    private String faultPhenomenon;

    /**
     * 故障原因
     */
    private Long faultReasonId;

    /**
     * 处理过程
     */
    private String handleProcesses;

    /**
     * 处理结果
     */
    private String handleResult;

    /**
     * 维修照片
     */
    private String repairImg;

    /**
     * 上传附件
     */
    private String accessoryUrl;

    /**
     * 外出距离
     */
    private Double outDistance;

    /**
     * 里程单价
     */
    private Double outMileagePrice;

    /**
     * 服务车费
     */
    private Double serviceCarPrice;

    /**
     * 叉车费用
     */
    private Double forkliftPrice;

    /**
     * 其他费用
     */
    private Double otherPrice;

    /**
     * 总费用
     */
    private Double totalPrice;

    /**
     * 其他费用描述
     */
    private String otherPriceDescription;

    /**
     * 1为一级审核,2为二级审核,3为三级审核，4为四级审核
     */
    private Integer currentLevel;

    /**
     * 当前审核人id
     */
    private Long currentCheckId;

    /**
     * 置顶排序标识符
     */
    private Long sortNo;

    /**
     * 0为服务站维修单，1为本部维修单
     */
    private Integer orderType;

    /**
     * 创建人id
     */
    private Long createUid;

    /**
     * 创建人名字
     */
    private String createUsername;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 0为保存,1为一级审核,2为二级审核中，3为三级审核，4,为四级审核，5为驳回，6为归档
     */
    private Integer status;

    /**
     * 0为正常,1为删除
     */
    private Integer delFlag;


}
