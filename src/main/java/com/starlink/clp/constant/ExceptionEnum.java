package com.starlink.clp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

    // 通用异常
    PARAMETER_ERROR(412, "参数错误"),
    PARAMETER_MISSING_ERROR(430, "参数缺失"),
    PARAMETER_LENGTH_ERROR(431, "参数长度异常"),
    PARAMETER_FORMAT_ERROR(433, "参数格式异常"),
    PARAMETER_TYPE_NOT_SUPPORTED(429, "参数类型不支持"),
    NUMBER_FORMAT_ERROR(434, "数字格式错误"),
    TIME_FORMAT_ERROR(435, "日期格式错误"),
    REQUEST_ERROR(436, "请求异常"),
    PAGEABLE_ERROR(437, "分页异常"),
    FILE_SIZE_TOO_LARGE(438, "文件太大"),
    FILE_NOT_FOUND(439, "未找到该文件"),

    // 页请求异常
    PAGE_OUT_BOUND(440, "请求页面元素超限"),
    IMAGE_NOT_PRESENT(441, "没有上传图片"),
    IMAGE_TOO_LARGE(442, "图片大小过大"),
    IMAGE_TOO_MANY(443, "一次性上传图片过多"),
    IMAGE_NAME_EMPTY(444, "图片名为空"),
    IMAGE_NO_SUFFIX(445, "图片没有文件格式"),
    IDENTIFIER_PARAM_MISSING(446, "用户ID或用户名缺失"),
    SERVER_DOOMED(447, "服务器爆炸了"),


    // 安全异常
    PARAMETER_SECURITY_CHECK_ERROR(452, "参数安全检查异常"),
    UNAUTHORIZED(401, "没有该操作权限"),
    FORBIDDEN(403, "得到授权但访问禁止"),


    // 注册异常
    USER_ALREADY_EXIST_ERROR(409, "用户已存在"),
    PASSWORD_TOO_WEAK(458, "密码强度太弱"),


    // 登录异常
    USER_NOT_EXIST(460, "用户不存在"),

    // 服务器内部异常
    IMAGE_WRITE_ERROR(470, "图片保存出错"),

    // 修改异常
    FATAL_BEAN_EXCEPTION(550, "修改信息时内部错误");



    private Integer code;
    private String message;
}
