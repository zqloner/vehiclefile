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
 * 备件流转申请单
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StoreApplyOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 申请单号
     */
    private String applyNumber;

    /**
     * 申请人id
     */
    private Long applyUid;

    /**
     * 申请人名字
     */
    private String applyUsername;

    /**
     * 申请时间
     */
    private LocalDateTime applyTime;

    /**
     * 申请人所管理的仓库类型(0为出库,1为入库)
     */
    private Integer applyType;

    /**
     * 当前申请仓库id
     */
    private Long appyStoreId;

    /**
     * 说明
     */
    private String description;

    /**
     * 远程仓库id
     */
    private Long farStoreId;

    /**
     * 远程仓库类型(0为出库,1为入库)
     */
    private Integer farStoreType;

    /**
     * 状态(0保存,1对方确认中,2已归档，3驳回)
     */
    private Integer checkStatus;

    /**
     * 0为正常，1为删除
     */
    private Integer delFlag;


}
