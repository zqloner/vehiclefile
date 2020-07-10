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
 * 备件仓库清单表
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StoreMaterialList implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 当前所在库id
     */
    private Long currentStoreId;

    /**
     * 所在库的级别(华北库 - 北京库 – 海格恒远服务站库)
     */
    private String storeName;

    /**
     * 物料名称
     */
    private String materialName;

    /**
     * 物料编码
     */
    private String materialCode;

    /**
     * 物料型号
     */
    private String materialModel;

    /**
     * 物料类型
     */
    private String materialType;

    /**
     * 规格
     */
    private String specification;

    /**
     * 说明
     */
    private String description;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 最小备货量
     */
    private Integer minCount;

    /**
     * 单位
     */
    private String unit;

    /**
     * 单价
     */
    private Double unitPrice;

    /**
     * 创建人
     */
    private Long createUid;

    /**
     * 创建人名称
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


}
