package com.mgl.component;


import com.mgl.api.CommonResult;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by zhaohy on 2019/9/4.
 */
@RestControllerAdvice
public class MyExceptionHandler {

//    @ExceptionHandler(value = Exception.class)
    public CommonResult handlerException() {
        return CommonResult.failed("系统异常");
    }
}
