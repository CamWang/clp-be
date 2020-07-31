package com.starlink.clp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author CamWang
 * @since 2020/7/28 19:54
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Exception extends RuntimeException{

    private ExceptionEnum exceptionEnum;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public enum ExceptionEnum {

        // 通用异常
        PARAMETER_ERROR(412, "参数错误"),
        PARAMETER_MISSING_ERROR(430, "参数缺失"),
        PARAMETER_LENGTH_ERROR(431, "参数长度异常"),
        PARAMETER_FORMAT_ERROR(433, "参数格式异常"),
        NUMBER_FORMAT_ERROR(434, "数字格式错误"),
        TIME_FORMAT_ERROR(435, "日期格式错误"),
        REQUEST_ERROR(436, "请求异常"),
        PAGEABLE_ERROR(437, "分页异常"),
        FILE_SIZE_TOO_LARGE(438, "文件太大"),
        FILE_NOT_FOUND(439, "未找到该文件"),

        // 安全异常
        PARAMETER_SECURITY_CHECK_ERROR(452, "参数安全检查异常"),
        UNAUTHORIZED(401, "没有该操作权限"),
        FORBIDDEN(403, "得到授权但访问禁止"),


        // 注册异常
        USER_ALREADY_EXIST_ERROR(409, "用户已存在"),
        PASSWORD_TOO_WEAK(458, "密码强度太弱"),


        // 登录异常
        USER_NOT_EXIST(460, "用户名不存在");


        private Integer code;
        private String message;
    }

}
