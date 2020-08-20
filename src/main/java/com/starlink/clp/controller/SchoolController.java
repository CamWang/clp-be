package com.starlink.clp.controller;

import com.starlink.clp.constant.ExceptionEnum;
import com.starlink.clp.entity.School;
import com.starlink.clp.exception.ClpException;
import com.starlink.clp.projection.school.SchoolInfo;
import com.starlink.clp.validate.ValidPage;
import com.starlink.clp.service.SchoolService;
import com.starlink.clp.view.SchoolModifiedView;
import com.starlink.clp.view.SchoolRegisterView;
import com.starlink.clp.view.SchoolSecurityView;
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
     * 查看学校列表，使用url参数
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
     * 向前端提供学校的信息，对已经登陆的用户使用，
     *
     * @param name 使用url传入学校的名字
     * @return 返回学校的基本信息 如果学校不存在跑错 SCHOOL_NOT_EXIST
     */
    @GetMapping("/school/name") //返回符合请求学校名字的学校信息
    @ResponseStatus(HttpStatus.OK)
    public SchoolInfo getSchoolInfo(
            @Length(min = 3, max = 24, message = "学校名字在3-24字符之间")
                    String name
    ) {
        if (!SchoolService.testIfSchoolPresentByName(name)) {
            throw new ClpException(ExceptionEnum.SCHOOL_NOT_EXIST);
        }
        return SchoolService.getSchoolInfoByName(name);
    }

    /**
     * 用户注册时，用于测试用户选择的学校是否已注册
     *
     * @param name 用户要进入的学校
     * @return Boolean类型的值
     */
    @GetMapping("/school/present") //判断学校是否被注册
    @ResponseStatus(HttpStatus.OK)
    public Boolean testIfSchoolPresent(String name) {
        return SchoolService.testIfSchoolPresentByName(name);
    }

    /**
     * 学校注册
     * 需验证是否为系统管理员
     * 使用json类型传入学校实体，只允许有name和description两个属性
     * 注册前先通过学校名字检查学校是否已经注册，避免重复注册
     *
     * @param school 以json格式传入学校以下信息
     *               {
     *               "name": "XXX大学",
     *               "description": "XXXXX理工院校"
     *               }
     * @return 若学校已存在，抛出 SCHOOL_ALREADY_EXIST_ERROR 错误
     */
    @PostMapping("/school") //学校注册
    @ResponseStatus(HttpStatus.CREATED)
    public String schoolRegister(
            @RequestBody @Validated({SchoolRegisterView.class, SchoolSecurityView.class}) School school
    ) {
        if (SchoolService.testIfSchoolPresentByName(school.getName())) {
            throw new ClpException(ExceptionEnum.SCHOOL_ALREADY_EXIST_ERROR);
        }
        SchoolService.registerSchool(school);
        return "学校注册成功";
    }

    /**
     * 上传学校头像
     * 传入form-data格式参数
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
            throw new ClpException(ExceptionEnum.SCHOOL_PARAM_ERROR);
        }
        if (!SchoolService.testIfSchoolPresentByName(name)) {
            throw new ClpException(ExceptionEnum.SCHOOL_NOT_EXIST);
        } else {
            if (!id.equals(SchoolService.getSchoolInfoByName(name).getId())) {
                throw new ClpException(ExceptionEnum.SCHOOL_PARAM_ERROR);
            }
        }
        SchoolService.setAvatar(id, name, avatarFile);
        return "头像上传成功";
    }

    /**
     * 修改学校信息 传入json格式
     * 需要验证身份是否是本学校管理者或系统管理员
     * <p>
     * {
     * "id": 11,                            //修改学校信息必须具有的字段
     * "name": "滨州大学",                   //学校名字修改的时候会检验是否与其他学校重名
     * "description": "不知名的理工院校",      //学校描述需要符合字数限制
     * }
     */
    @PutMapping("/school")
    @ResponseStatus(HttpStatus.OK)
    public String changeUserInfoForSchool(
            @RequestBody @Validated(SchoolModifiedView.class) School school
    ) {
        /**
         * 获取学校的Id，判断操作者对本Id对应的学校是否有操作权限
         */
        SchoolService.modifySchool(school);
        return "学校信息修改成功";
    }

    /**
     * 注销学校，需验证是否为系统管理员或者当前学校管理者
     *
     * @param school 传入要删除的学校的信息，
     *               包括id，neme
     * @return 返回删除操作的结果或抛出错误
     */
    @DeleteMapping("/school/delete")
    public String deleteSchool(
            @RequestBody @Validated(SchoolModifiedView.class) School school
    ) {

        if (school.getName() == null || school.getName().isBlank()) {
            throw new ClpException(ExceptionEnum.SCHOOL_PARAM_ERROR);
        }
        if (SchoolService.getSchoolInfoByName(school.getName()) == null || SchoolService.getSchoolInfoById(school.getId()) == null) {
            throw new ClpException(ExceptionEnum.SCHOOL_NOT_EXIST);
        }
        if (SchoolService.testIfSchoolPresentByName(school.getName()) && !school.getId().equals(SchoolService.getSchoolInfoByName(school.getName()).getId())) {
            throw new ClpException(ExceptionEnum.SCHOOL_PARAM_ERROR);
        }
        SchoolService.deleteSchool(school.getId());
        return "学校注销完成";
    }

}
