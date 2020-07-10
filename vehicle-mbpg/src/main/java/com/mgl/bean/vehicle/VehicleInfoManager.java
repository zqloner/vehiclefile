package com.mgl.bean.vehicle;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 车辆信息表
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class VehicleInfoManager implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 整车场
     */
    private String carFactory;

    /**
     * 订单号
     */
    private String orderNumber;

    /**
     * 车牌号(不可重复)
     */
    private String carNumber;

    /**
     * 车架号(不可重复)
     */
    private String vin;

    /**
     * 电池编号(不可重复)
     */
    private String batteryNumber;

    /**
     * 并数
     */
    private String andTheNumber;

    /**
     * 串数
     */
    private String stringOfNumber;

    /**
     * AH数
     */
    @TableField("AH_number")
    private String ahNumber;

    /**
     * 箱数
     */
    private String boxNumber;

    /**
     * 电池结构
     */
    private String batteryStructure;

    /**
     * 是否带滑轨(0带，1不带)
     */
    private Integer isSlideway;

    /**
     * 整车型号
     */
    private String allCarModel;

    /**
     * 动力模式
     */
    private String dynamicModel;

    /**
     * BCU型号
     */
    private String bcuModel;

    /**
     * MMU型号
     */
    private String mmuModel;

    /**
     * 程序版本
     */
    private String programVersion;

    /**
     * 动力类型
     */
    private String dynamicType;

    /**
     * 电机厂家
     */
    private String electricalMachineryVender;

    /**
     * 电机功率
     */
    private String electricalMachineryPower;

    /**
     * 保温系统
     */
    private String keepWarmSystem;

    /**
     * 加热系统
     */
    private String keepHartSystem;

    /**
     * 冷却系统
     */
    private String keepCoolSystem;

    /**
     * 运行区域
     */
    private String runArea;

    /**
     * 运行省份
     */
    private String runProvince;

    /**
     * 运行城市
     */
    private String runCity;

    /**
     * 运行县
     */
    private String runCounty;

    /**
     * 车辆所属人
     */
    private String carBelongUser;

    /**
     * 出厂时间
     */
    private LocalDate outVenderDate;

    /**
     * 运营时间
     */
    private String serviceTime;

    /**
     * 质保年限
     */
    private String qualityGuaranteeYear;

    /**
     * 质保里程
     */
    private String qualityGuaranteeMileage;

    /**
     * 车辆维修此时
     */
    private Integer carRepaireCount;

    /**
     * 电池维修次数
     */
    private Integer batteryRepaireCount;

    /**
     * 工时表
     */
    private String publicHourTable;

    /**
     * 创建人id
     */
    private Long ceateUid;

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
     * 0为正常，1为删除
     */
    private Integer delFlag;

    /**
     * (是否过保)0为正常，1为过保
     */
    private Integer isOverInsured;


}
