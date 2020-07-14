package com.mgl.service.fixed.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgl.api.CommonResult;
import com.mgl.api.Constants;
import com.mgl.bean.fixed.FixedRepaidProject;
import com.mgl.dao.fixed.FixedRepaidProjectMapper;
import com.mgl.service.fixed.FixedRepaidProjectService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 固定维修项目费用参数 服务实现类
 * </p>
 *
 * @author zhangq
 * @since 2020-07-08
 */
@Service
public class FixedRepaidProjectServiceImpl extends ServiceImpl<FixedRepaidProjectMapper, FixedRepaidProject> implements FixedRepaidProjectService {

    @Override
    public CommonResult saveOrUpdatte(FixedRepaidProject fixedRepaidProject) {
        if (fixedRepaidProject.getId() == null) {
            fixedRepaidProject.setDelFlag(Constants.DELFLAG_N0RMAL);
            save(fixedRepaidProject);
        } else {
            saveOrUpdate(fixedRepaidProject);
        }
        return CommonResult.success(null, "操作成功");
    }

    @Override
    public CommonResult delete(Long id) {
        saveOrUpdatte(getById(id).setDelFlag(Constants.DELFLAG_DELETE));
        return CommonResult.success("删除成功");
    }
}
