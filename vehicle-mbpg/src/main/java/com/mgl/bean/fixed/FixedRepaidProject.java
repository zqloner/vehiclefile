package com.mgl.bean.fixed;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 固定维修项目费用参数
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FixedRepaidProject implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 维修项目名称
     */
    private String projectName;

    /**
     * 单价
     */
    private Double costOfPrice;

    /**
     * 维修项目名称
     */
    private String unit;


    /**
     * 0为正常,1为删除
     */
    private Integer delFlag;


}
