package com.mgl.bean.repair;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.mgl.annotation.ExcelColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 工时维修项目
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RepairProjectNames implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 工时id
     */
    private Long hourId;

    /**
     * 维修项目名称
     */
    @ExcelColumn("项目名称")
    private String projectName;

    /**
     * 单位
     */
    @ExcelColumn("单位")
    private String unit;

    /**
     * 初次维修工时
     */
    @ExcelColumn("初次维修工时")
    private Double firstRepairHours;

    /**
     * 重复叠加工时
     */
    @ExcelColumn("重复叠加工时")
    private Double repeatOverlayHours;

    /**
     * 创建人
     */
    private Long createUid;

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
