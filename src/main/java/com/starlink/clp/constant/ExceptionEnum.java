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
    IMAGE_TOO_LARGE(442, "图片大小超过2M限制"),
    IMAGE_TOO_MANY(443, "一次性上传图片过多"),
    IMAGE_NAME_EMPTY(444, "图片名为空"),
    IMAGE_NO_SUFFIX(445, "图片没有文件格式"),
    IDENTIFIER_PARAM_MISSING(446, "用户ID或用户名缺失"),
    FILE_NOT_PRESENT(441, "没有上传文件"),
    FILE_TOO_LARGE(442, "文件大小超过100M"),
    FILE_TOO_MANY(443, "文件数量过多"),
    FILE_NAME_EMPTY(444, "文件名称为空"),
    FILE_NAME_DUPLICATED(447, "有重复命名文件"),


    // 安全异常
    PARAMETER_SECURITY_CHECK_ERROR(452, "参数安全检查异常"),
    UNAUTHORIZED(401, "没有该操作权限"),
    FORBIDDEN(403, "得到授权但访问禁止"),


    // 注册异常
    USER_ALREADY_EXIST_ERROR(409, "用户已存在"),
    PASSWORD_TOO_WEAK(458, "密码强度太弱"),


    // 登录异常
    USER_NOT_EXIST(460, "用户不存在"),
    WRONG_PASSWORD(461, "用户名或密码错误"),
    ACCOUNT_LOCKED(462, "账户已被锁定"),
    CREDENTIALS_EXPIRED(463, "凭证过期"),
    ACCOUNT_EXPIRED(464, "账户过期"),
    ACCOUNT_DISABLED(465, "账户已被禁用"),
    LOGIN_ERROR(466, "很抱歉登录失败"),
    LOGIN_REQUIRED(490, "登录之后才能进行该操作"),
    LOGIN_SERVER_ERROR(467, "服务器用户凭证丢失，请重新登录"),
    NEED_LOGOUT(468, "需要登出后操作"),
    INVALID_LOGIN(469, "认证失败"),


    // 学校异常
    SCHOOL_NOT_EXIST(460, "学校未注册，请与系统管理员联系"),
    SCHOOL_ALREADY_EXIST_ERROR(409, "学校已被注册，请与系统管理员联系"),
    SCHOOL_PARAM_ERROR(468, "学校参数信息错误"),
    SCHOOL_INFO_MODIFY_EXCEPTION(469, "未发生有效的更改"),

    // 班级异常
    CLAZZ_NOT_EXIST(460,"班级不存在"),
    CLAZZ_ALREADY_EXIST_ERROR(409,"班级已经存在"),

    // 队伍异常
    TEAM_NOT_EXIST(460, "队伍不存在"),

    // 服务器内部异常
    IMAGE_WRITE_ERROR(470, "图片保存出错"),
    FILE_WRITE_ERROR(470, "文件保存出错"),

    // 修改异常
    FATAL_BEAN_EXCEPTION(550, "修改信息时内部错误");


    private Integer code;
    private String message;

    @Override
    public String toString() {
        return "{" +
                "\"code\"=" + code +
                ", \"message\"=\"" + message + '\"' +
                '}';
    }
}
