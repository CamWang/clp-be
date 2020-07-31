package com.starlink.clp.exception;

import com.starlink.clp.constant.Constant;
import com.starlink.clp.constant.Exception;
import lombok.Data;

/**
 * @author CamWang
 * @since 2020/7/28 20:16
 */

@Data
public class ExceptionResult {
    private Integer status;
    private String message;
    private Long timestamp;

    public ExceptionResult(Exception.ExceptionEnum exceptionEnums) {
        this.status = exceptionEnums.getCode();
        this.message = exceptionEnums.getMessage();
        this.timestamp = System.currentTimeMillis();
    }
}
