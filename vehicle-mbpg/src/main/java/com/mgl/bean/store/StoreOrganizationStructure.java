package com.mgl.bean.store;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 仓库组织架构
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StoreOrganizationStructure implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 库房名称
     */
    private String name;

    /**
     * 库房管理人id(关联用户表)
     */
    private Long managerId;

    /**
     * 管理人名称
     */
    private String managerName;

    /**
     * 上级仓库id
     */
    private Long pid;

    /**
     * 创建人id
     */
    private Long ceateUid;

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

    /**
     * 管理人名称
     */
    private String parentName;
}
