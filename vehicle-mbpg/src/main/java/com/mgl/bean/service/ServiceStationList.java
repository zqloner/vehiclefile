package com.mgl.bean.service;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 服务站列表
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ServiceStationList implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 区域
     */
    private String area;

    /**
     * 省份
     */
    private String province;

    /**
     * 市级
     */
    private String city;

    /**
     * 县级
     */
    private String county;

    /**
     * 服务站名称
     */
    private String stationName;

    /**
     * 服务站类型
     */
    private String stationType;

    /**
     * 服务站一级管理人id
     */
    private Long firstManagerId;

    /**
     * 服务站一级管理人名字
     */
    private String firstManagerName;

    /**
     * 服务站二级管理人id
     */
    private Long secondManagerId;

    /**
     * 服务站二级管理人名字
     */
    private String secondManagerName;

    /**
     * 服务站三级管理人id
     */
    private Long thirdManagerId;

    /**
     * 服务站三级管理人名字
     */
    private String thirdManagerName;

    /**
     * 服务站四级管理人id
     */
    private Long fourthManagerId;

    /**
     * 服务站四级管理人名字
     */
    private String fourthManagerName;

    /**
     * 服务站备件库id
     */
    private Long stationStoreId;

    /**
     * 服务站备件库名字
     */
    private String stationStoreName;

    /**
     * 是否为核心站？0为核心库,1为核心站
     */
    private Integer isCoreStation;

    /**
     * 建站时间
     */
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate stationCreateDate;

    /**
     * 所属街道地址
     */
    private String belongTreeAddress;

    /**
     * 维修工时单价
     */
    private Double repaireHourPrice;

    /**
     * 外出里程单价
     */
    private Double outMileagePrice;

    /**
     * 外出补助
     */
    private Double outSubsidy;

    /**
     * 辅助设备使用费
     */
    private Double auxiliaryEquipmentCost;

    /**
     * 外出服务车1车牌号
     */
    private String outCarFirst;

    /**
     * 外出服务车2车牌号
     */
    private String outCarSecond;

    /**
     * 创建人id
     */
    @TableField("create_userId")
    private Long createUserid;

    /**
     * 创建人名字
     */
    @TableField("create_userName")
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
     * 0为正常,1为删除
     */
    private Integer delFlag;


}
