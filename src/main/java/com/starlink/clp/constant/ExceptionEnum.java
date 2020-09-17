package com.starlink.clp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnum {

    // 通用异常
    PARAMETER_ERROR(412, "参数错误", "Parameter Error"),
    PARAMETER_MISSING_ERROR(430, "参数缺失", "Parameter Missing"),
    PARAMETER_LENGTH_ERROR(431, "参数长度异常", "Parameter Length Error"),
    PARAMETER_FORMAT_ERROR(433, "参数格式异常", "Parameter Format Error"),
    PARAMETER_TYPE_NOT_SUPPORTED(429, "参数类型不支持", "Parameter Type Not Supported"),
    NUMBER_FORMAT_ERROR(434, "数字格式错误", "Number Format Error"),
    TIME_FORMAT_ERROR(435, "日期格式错误", "Time Format Error"),
    REQUEST_ERROR(436, "请求异常", "Request Error"),
    PAGEABLE_ERROR(437, "分页异常", "Pageable Error"),
    FILE_SIZE_TOO_LARGE(438, "文件太大", "File Size Too Large"),
    FILE_NOT_FOUND(439, "未找到该文件", "File Not Found"),

    // 页请求异常
    PAGE_OUT_BOUND(440, "请求页面元素超限", "Page Out Bound"),
    IMAGE_NOT_PRESENT(441, "没有上传图片", "Image Not Present"),
    IMAGE_TOO_LARGE(442, "图片大小超过2M限制", "Image Too Large"),
    IMAGE_TOO_MANY(443, "一次性上传图片过多", "Image Too Many"),
    IMAGE_NAME_EMPTY(444, "图片名为空", "Image Name Empty"),
    IMAGE_NO_SUFFIX(445, "图片没有文件格式", "Image No Suffix"),
    IDENTIFIER_PARAM_MISSING(446, "用户ID或用户名缺失", "Identifier Missing"),
    FILE_NOT_PRESENT(441, "没有上传文件", "File Not Present"),
    FILE_TOO_LARGE(442, "文件大小超过100M", "File Too Large"),
    FILE_TOO_MANY(443, "文件数量过多", "File Too Many"),
    FILE_NAME_EMPTY(444, "文件名称为空", "File Name Empty"),
    FILE_NAME_DUPLICATED(447, "有重复命名文件", "File Name Duplicated"),


    // 安全异常
    PARAMETER_SECURITY_CHECK_ERROR(452, "参数安全检查异常", "Security Check Error"),
    UNAUTHORIZED(401, "没有该操作权限", "Unauthorized"),
    FORBIDDEN(403, "得到授权但访问禁止", "Forbidden"),


    // 注册异常
    USER_ALREADY_EXIST_ERROR(409, "用户已存在", "User Already Exist"),
    PASSWORD_TOO_WEAK(458, "密码强度太弱", "Password Too Weak"),


    // 登录异常
    USER_NOT_EXIST(460, "用户不存在", "User Not Exist"),
    WRONG_PASSWORD(461, "用户名或密码错误", "Wrong Password"),
    ACCOUNT_LOCKED(462, "账户已被锁定", "Account Locked"),
    CREDENTIALS_EXPIRED(463, "凭证过期", "Credentials Expired"),
    ACCOUNT_EXPIRED(464, "账户过期", "Account Expired"),
    ACCOUNT_DISABLED(465, "账户已被禁用", "Account Disabled"),
    LOGIN_ERROR(466, "很抱歉登录失败", "Login Error"),
    LOGIN_REQUIRED(490, "登录之后才能进行该操作", "Login Required"),
    LOGIN_SERVER_ERROR(467, "服务器用户凭证丢失，请重新登录", "Login Server Error"),
    NEED_LOGOUT(468, "需要登出后操作", "Need Logout"),
    INVALID_LOGIN(469, "认证失败", "Invalid Login"),


    // 学校异常
    SCHOOL_NOT_EXIST(460, "学校未注册，请与系统管理员联系", "School Not Exist"),
    SCHOOL_ALREADY_EXIST_ERROR(409, "学校已被注册，请与系统管理员联系", "School Already Exist"),
    SCHOOL_PARAM_ERROR(468, "学校参数信息错误", "Parameter Error"),
    SCHOOL_INFO_MODIFY_EXCEPTION(469, "未发生有效的更改", "Not Modified"),

    // 班级异常
    CLAZZ_NOT_EXIST(460,"班级不存在", "Class Not Exist"),
    CLAZZ_ALREADY_EXIST_ERROR(409,"班级已经存在", "Class Already Exist"),

    // 比赛异常
    CONTEST_NOT_EXIST(460, "比赛不存在", "Contest Not Exist"),
    NO_CONTEST_AVAILABLE(461, "没有可显示的比赛", "No Contest Available"),

    // 队伍异常
    TEAM_NOT_EXIST(460, "队伍不存在", "Team Not Exist"),

    // 服务器内部异常
    IMAGE_WRITE_ERROR(470, "图片保存出错", "Image Write Error"),
    FILE_WRITE_ERROR(470, "文件保存出错", "File Write Error"),

    // 修改异常
    FATAL_BEAN_EXCEPTION(550, "修改信息时内部错误", "Fatal Bean Exception");


    private Integer code;
    private String messageCn;
    private String messageEn;

    @Override
    public String toString() {
        return "{" +
                "\"code\"=" + code +
                ", \"messageCN\"=\"" + messageCn + '\"' +
                ", \"messageEN\"=\"" + messageEn + '\"' +
                '}';
    }
}
