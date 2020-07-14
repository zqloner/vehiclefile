package com.mgl.service.fixed;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mgl.api.CommonResult;
import com.mgl.bean.fixed.FixedRepaidProject;

/**
 * <p>
 * 固定维修项目费用参数 服务类
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
public interface FixedRepaidProjectService extends IService<FixedRepaidProject> {

    CommonResult saveOrUpdatte(FixedRepaidProject fixedRepaidProject);

    CommonResult delete(Long id);
}
