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
 * 维修单审批详情表
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ServiceStationRepairOrderCheckDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 服务站维修报单id
     */
    private Long serviceStationRepairOrderId;

    /**
     * 审核人id
     */
    private Long checkUid;

    /**
     * 审核人名字
     */
    private String checkUsername;

    /**
     * 0为审核通过，1为驳回
     */
    private Integer checkStatus;

    /**
     * 审批意见
     */
    private String content;

    /**
     * 审批时间
     */
    private LocalDateTime createTime;


}
