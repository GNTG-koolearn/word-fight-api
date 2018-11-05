package com.koolearn.wordfight.util.system;

import com.koolearn.wordfight.exception.BizException;
import com.koolearn.wordfight.util.system.statusenum.ErrorEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
@ApiModel(value = "响应对象", description = "Result")
public class Result<T> implements Serializable {

    @ApiModelProperty(value = "业务码")
    private int code;
    @ApiModelProperty(value = "响应消息")
    private String message;
    @ApiModelProperty(value = "响应数据")
    private T data;

    public Result(T data) {
        this.code = 0;
        this.message = Constants.REQUEST_SUCCESS;
        this.data = data;
    }

    public Result() {
        this.code = 0;
        this.message = Constants.REQUEST_SUCCESS;
    }
    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(ErrorEnum errorEnum, T data) {
        this.code = errorEnum.getCode();
        this.message = errorEnum.getName();
        this.data = data;
    }

    public Result(BizException bizException,T data) {
        this.code = bizException.getErrorCode();
        this.message = bizException.getErrorInfo();
        this.data = data;
    }
}