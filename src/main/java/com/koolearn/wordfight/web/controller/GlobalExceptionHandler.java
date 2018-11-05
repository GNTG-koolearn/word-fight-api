package com.koolearn.wordfight.web.controller;

import com.koolearn.wordfight.exception.BizException;
import com.koolearn.wordfight.util.system.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
 
    /**
      * @Description: 系统异常捕获处理
      */
    @ExceptionHandler(value = Exception.class)
    public Result javaExceptionHandler(Exception ex) {
        log.error("捕获到Exception异常",ex);
        return new Result(new BizException(),null);
    }
 
    /**
      * @Description: 自定义异常捕获处理
      */
    @ExceptionHandler(value = BizException.class)
    public Result messageCenterExceptionHandler(BizException ex) {
        log.error("捕获到BizException异常",ex.getErrorInfo());

        return new Result(ex, null);
    }
 
}
