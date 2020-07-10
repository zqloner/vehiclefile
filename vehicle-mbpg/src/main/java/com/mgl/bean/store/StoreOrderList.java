package com.mgl.bean.store;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 出库单
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StoreOrderList implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 当前操作仓库id
     */
    private Long currentStoreId;

    /**
     * 出入库单号
     */
    private String orderNumber;

    /**
     * 出入库类别
     */
    private Long outStoreType;

    /**
     * 目标仓库id
     */
    private Long farawayStoreId;

    /**
     * 当前操作人名字
     */
    private String currentOperatorName;

    /**
     * 出入库说明
     */
    private String description;

    /**
     * 发货人
     */
    private String consignerUername;

    /**
     * 发货地点
     */
    private String consignerAddress;

    /**
     * 发货时间
     */
    private LocalDateTime consignerTime;

    /**
     * 收货人
     */
    private String receiveUsername;

    /**
     * 收货地址
     */
    private String receiveAddress;

    /**
     * 物流公司
     */
    private String company;

    /**
     * 运单号
     */
    private String waybillNumber;

    /**
     * 运单价格
     */
    private Double waybillPrice;

    /**
     * 0为出库单，1为入库单，2为转库单
     */
    private Integer type;

    /**
     * 当前操作人id
     */
    private Long currentOperatorId;

    /**
     * 出库日期
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


}
