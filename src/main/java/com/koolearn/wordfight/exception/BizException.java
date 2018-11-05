package com.koolearn.wordfight.exception;

import com.koolearn.wordfight.util.system.statusenum.ErrorEnum;
import lombok.Data;


@Data
public class BizException extends RuntimeException {
    private static final long serialVersionUID = 463358782142663414L;

    private int errorCode;
    private String errorInfo;
    private Throwable e;

    public BizException() {
        this.errorCode = ErrorEnum.SYSTEM_ERROR.getCode();
        this.errorInfo = ErrorEnum.SYSTEM_ERROR.getName();
    }

    public BizException(int errorCode) {
        this.errorCode = errorCode;
    }

    public BizException(ErrorEnum errorEnum) {
        this.errorCode = errorEnum.getCode();
        this.errorInfo = errorEnum.getName();
    }

    public BizException(int errorCode, Throwable e) {
        this.errorCode = errorCode;
        this.e = e;
    }

    public BizException(int errorCode, String errorInfo) {
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
    }

}
