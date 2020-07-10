package com.mgl.bean.service;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 本部人员维修报单自定义关联费用表
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ServiceStationcadreRepairOrderCostPrice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 维修订单id
     */
    private Long repaireOrderId;

    /**
     * 费用名称
     */
    private String costName;

    /**
     * 费用
     */
    private Double costPrice;

    /**
     * 0为正常，1为删除
     */
    private Integer delFlag;


}
