package com.mgl.bean.store;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 出入库单物料变化详情表
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StoreOrderMeterialDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 当前仓库id
     */
    private Long currentStoreId;

    /**
     * 远程仓库id
     */
    private Long farawayStoreId;

    /**
     * 物料id
     */
    private Long 
materialId;

    /**
     * 操作数量
     */
    private Integer count;

    /**
     * 0为正常，1为删除
     */
    private Integer delFlag;


}
