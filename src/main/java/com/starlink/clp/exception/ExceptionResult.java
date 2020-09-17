package com.starlink.clp.exception;

import com.starlink.clp.constant.ExceptionEnum;
import lombok.Data;

/**
 * @author CamWang
 * @since 2020/7/28 20:16
 */

@Data
public class ExceptionResult {
    private Integer status;
    private String messageCn;
    private String messageEn;
    private Long timestamp;

    public ExceptionResult(ExceptionEnum exceptionEnums) {
        this.status = exceptionEnums.getCode();
        this.messageCn = exceptionEnums.getMessageCn();
        this.messageEn = exceptionEnums.getMessageEn();
        this.timestamp = System.currentTimeMillis();
    }

    public ExceptionResult(Integer status, String messageCn, String messageEn) {
        this.status = status;
        this.messageCn = messageCn;
        this.messageEn = messageEn;
        this.timestamp = System.currentTimeMillis();
    }
}
