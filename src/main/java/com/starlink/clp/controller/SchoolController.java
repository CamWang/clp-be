package com.starlink.clp.controller;

import com.starlink.clp.constant.ExceptionEnum;
import com.starlink.clp.entity.School;
import com.starlink.clp.exception.ClpException;
import com.starlink.clp.projection.school.SchoolInfo;
import com.starlink.clp.util.FileUtil;
import com.starlink.clp.validate.ValidPage;
import com.starlink.clp.service.SchoolService;
import com.starlink.clp.view.SchoolModifiedView;
import com.starlink.clp.view.SchoolRegisterView;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Qilin
 * @since 2020/8/8 12:29
 */

@RestController
@Validated
public class SchoolController {

    private final SchoolService SchoolService;

    public SchoolController(SchoolService schoolService) {
        this.SchoolService = schoolService;
    }

    /**
     * 获取所有学校，使用分页
     * 验证是否为系统管理员其他需要用到学校列表的管理员
     *
     * @param pageable 传入size每页元素数、
     *                 page从0开始的页码、
     *                 sort排序方式
     *                 三个参数对应默认为20、0、null
     */
    @GetMapping("/school")
    @ResponseStatus(HttpStatus.OK)
    public Page<SchoolInfo> getAllSchoolSimple(
            @ValidPage Pageable pageable
    ) {
        /**
         * 验证权限，不符合则抛回 UNAUTHORIZED 错误
         */
        return SchoolService.SchoolInfo(pageable);
    }

    /**
     * 向前端提供学校的信息，对已经登陆的用户公开使用，
     * 如果为普通用户的权限，则不返回学校id
     * 对于用户的学校id可在用户关联的学校处获取
     */
    @GetMapping("/school/name") //返回符合请求学校名字的学校信息
    @ResponseStatus(HttpStatus.OK)
    public SchoolInfo getSchoolInfo(
            @Length(min = 3, max = 24, message = "学校名字在3-24字符之间")
                    String name
    ) {
        SchoolInfo scINfo = SchoolService.getSchoolInfoByName(name);
        /**
         * 进行权限验证，如果是普通用户则不给出学校的id
         * scInfo。setId(0);
         */
        return scINfo;
    }

    @GetMapping("/school/present") //判断学校是否被注册
    @ResponseStatus(HttpStatus.OK)
    public Boolean testIfSchoolPresent(String name) {
        return SchoolService.testIfSchoolPresentByName(name);
    }

    /**
     * 学校注册
     * 需验证是否为系统管理员
     * 注册前先通过学校名字检查学校是否已经注册
     *
     * @param school 以json格式传入学校以下信息
     *               {
     *               "name": "XXX大学",
     *               "description": "XXXXX理工院校"
     *               }
     * @return 返回学校注册成功
     */
    @PostMapping("/school") //创建学校
    @ResponseStatus(HttpStatus.CREATED)
    public String schoolRegister(
            @RequestBody @Validated(SchoolRegisterView.class) School school
    ) {
        //二次验证 是否有存在的必要性？
        if (school.getName() == null || school.getName().isBlank()) {
            throw new ClpException(ExceptionEnum.SCHOOL_PARAM_MISSING);
        }
        if (SchoolService.testIfSchoolPresentByName(school.getName())) {
            throw new ClpException(ExceptionEnum.SCHOOL_ALREADY_EXIST_ERROR);
        }
        //如果未上传头像就设置为默认头像
        if (school.getAvatar() == null) {
            school.setAvatar("default.png");
        }
        SchoolService.registerSchool(school);
        return "学校注册成功";
    }

    /**
     * 注销学校，需验证是否为系统管理员或者当前学校管理者
     *
     * @param school 传入要删除的学校的信息，
     *               包括id，neme，description，avatar。
     * @return 返回删除操作的结果或抛出错误
     */
    @DeleteMapping("/school/delete")
    public String deleteSchool(
            @RequestBody @Validated(SchoolModifiedView.class) School school
    ) {

        if (school.getName() == null || school.getName().isBlank()) {
            throw new ClpException(ExceptionEnum.SCHOOL_PARAM_MISSING);
        }
        if (!SchoolService.testIfSchoolPresentByName(school.getName())) {
            throw new ClpException(ExceptionEnum.SCHOOL_NOT_EXIST);
        }

        SchoolService.deleteSchool(school.getId());
        return "删除成功";
    }

    /**
     * 修改学校信息
     * 需要验证身份是否是学校管理者或系统管理员
     */
    @PutMapping("/school")
    @ResponseStatus(HttpStatus.OK)
    public String changeUserInfoForSchool(
            @RequestBody @Validated(SchoolModifiedView.class) School school
    ) {
        if (school.getId() == null || school.getName() == null || school.getName().isBlank()) {
            throw new ClpException(ExceptionEnum.SCHOOL_PARAM_MISSING);
        }
        SchoolService.modifySchool(school);
        return "用户信息修改成功";
    }

    /**
     * 上传学校头像
     * 需要验证是否是学校管理者或系统管理员
     */
    @PostMapping("/school/avatar")
    @ResponseStatus(HttpStatus.OK)
    public String uploadAvatar(
            @NotNull(message = "学校ID不能为空") @Range(min = 0, max = 2097152, message = "学校ID范围超限")
                    Integer id,
            @NotBlank(message = "学校名字不能为空") @Length(min = 3, max = 24, message = "学校名字长度在3-24字符")
                    String name,
            @NotNull(message = "头像文件未成功提交")
                    MultipartFile avatarFile
    ) {
        if (name == null || name.isBlank()) {
            throw new ClpException(ExceptionEnum.IDENTIFIER_PARAM_MISSING);
        }
        if (!SchoolService.testIfSchoolPresentByName(name)) {
            throw new ClpException(ExceptionEnum.SCHOOL_NOT_EXIST);
        }
        String avatar = FileUtil.avatarProcess(avatarFile);
        SchoolService.setAvatar(id, name, avatar);
        return "头像上传成功";
    }

}
