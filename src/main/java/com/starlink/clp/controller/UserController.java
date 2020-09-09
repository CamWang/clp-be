package com.starlink.clp.controller;

import com.starlink.clp.constant.ExceptionEnum;
import com.starlink.clp.constant.FileTypeEnum;
import com.starlink.clp.entity.User;
import com.starlink.clp.exception.ClpException;
import com.starlink.clp.projection.user.UserInfo;
import com.starlink.clp.projection.user.UserSimple;
import com.starlink.clp.service.UserService;
import com.starlink.clp.util.ImageUtil;
import com.starlink.clp.validate.ValidPage;
import com.starlink.clp.view.UserModifiedView;
import com.starlink.clp.view.UserRegisterView;
import com.starlink.clp.view.UserSecurityView;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 * 用户Controller类
 * Controller仅用于将参数打包成对应对象并调用Service层
 * 当文件传入时需要交给FileUtil处理
 *
 * @author CamWang
 * @since 2020/8/13 9:04
 */

@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * 获取所有用户列表，使用分页
     *
     * @param pageable 传入size每页元素数、page从0开始的页码、sort排序方式
     *                 三个参数对应默认为20、0、null
     */
    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserSimple> getAllUserSimple(
            @ValidPage(message = "页面请求错误") Pageable pageable
    ) {
        return userService.getAllUserSimple(pageable);
    }


    /**
     * 登录之后获取用户详细信息供前端使用
     *
     * // @param authentication 获取Security Context内对应用户的身份
     */
    @GetMapping("/user/detail")
    @ResponseStatus(HttpStatus.OK)
    public UserInfo getUserInfo(
            @AuthenticationPrincipal UserDetails userDetails
            ) {
        /**
         *  需要验证是否为当前用户，如果是ADMIN则跳过验证
         */
        String username = userDetails.getUsername();
        if (username == null) {
            throw new ClpException(ExceptionEnum.LOGIN_SERVER_ERROR);
        }
        return userService.getUserInfoByUsername(username);
    }


    /**
     * 查看用户名是否已存在
     *
     * @param username 需要被查询是否重复的用户名
     */
    @GetMapping("/user/repeat")
    public Boolean testUsernameExists(
            @Length(min = 4, max = 32, message = "用户名长度在4-32字符之间") String username
    ) {
        return userService.testIfUsernamePresent(username);
    }


    /**
     * 用户注册
     */
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public String userRegister(
            @RequestBody @Validated({UserRegisterView.class, UserSecurityView.class}) User user,
            HttpServletRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        if (userDetails != null) {
            throw new ClpException(ExceptionEnum.NEED_LOGOUT);
        }
        if (userService.testIfUsernamePresent(user.getUsername())) {
            throw new ClpException(ExceptionEnum.USER_ALREADY_EXIST_ERROR);
        }
        user.setIp(request.getRemoteAddr());
        userService.registerUser(user);
        return "注册成功";
    }


    /**
     * 修改用户信息
     * 需要验证身份
     */
    @PutMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public String changeUserInfoForUser(
            @RequestBody @Validated({UserModifiedView.class, UserSecurityView.class}) User user,
            @Length(min = 4, max = 32, message = "密码长度在4-32字符之间") String oldPassword,
            HttpServletRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        String username = userDetails.getUsername();
        if (username == null) {
            throw new ClpException(ExceptionEnum.LOGIN_SERVER_ERROR);
        } else if (username.equals(user.getUsername())) {
            throw new ClpException(ExceptionEnum.UNAUTHORIZED);
        }
        user.setIp(request.getRemoteAddr());
        userService.modifyUser(user, oldPassword);
        return "用户信息修改成功";
    }


    /**
     * 上传用户头像
     * 需要测试是否是本人
     */
    @PostMapping("/user/avatar")
    @ResponseStatus(HttpStatus.OK)
    public String uploadAvatar(
            @NotNull(message = "用户ID不能为空") @Length(min = 2, max = 32, message = "用户名范围超限")
                    String username,
//            @NotBlank(message = "密码不能为空") @Length(min = 4, max = 32, message = "密码长度在4-32字符")
//                    String password,
            @NotNull(message = "头像文件未成功提交")
                    MultipartFile avatarFile,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        String username1 = userDetails.getUsername();
        if (username1 == null) {
            throw new ClpException(ExceptionEnum.LOGIN_SERVER_ERROR);
        } else if (username.equals(username1)) {
            throw new ClpException(ExceptionEnum.UNAUTHORIZED);
        }
        if (!userService.testIfUsernamePresent(username)) {
            throw new ClpException(ExceptionEnum.USER_NOT_EXIST);
        }
        String avatar = ImageUtil.oneImageProcess(avatarFile, FileTypeEnum.USER_AVATAR);
        userService.setAvatar(username, avatar);
        return "头像上传成功";
    }
}
