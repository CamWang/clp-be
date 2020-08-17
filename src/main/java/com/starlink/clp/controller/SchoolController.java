package com.starlink.clp.controller;

import com.starlink.clp.constant.ExceptionEnum;
import com.starlink.clp.entity.School;
import com.starlink.clp.exception.ClpException;
import com.starlink.clp.projection.school.SchoolInfo;
import com.starlink.clp.projection.school.SchoolSimple;
import com.starlink.clp.util.FileUtil;
import com.starlink.clp.validate.ValidPage;
import com.starlink.clp.service.SchoolService;
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
     * 获取所有学校，使用分页，其中使用pageValidate()方法验证pageable合法性
     *
     * @param pageable 传入size每页元素数、page从0开始的页码、sort排序方式
     *                 三个参数对应默认为20、0、null
     */
    @GetMapping("/school")
    @ResponseStatus(HttpStatus.OK)
    public Page<SchoolSimple> getAllSchoolSimple(
            @ValidPage Pageable pageable
    ) {
        return SchoolService.getAllUserSimple(pageable);
    }

    /**
     * 向前端提供学校的信息
     *
     * // @param authentication 获取Security Context内对应用户的身份
     */
    @GetMapping("/school/id") //返回符合请求学校id的学校对象
    @ResponseStatus(HttpStatus.OK)
    public SchoolInfo getSchoolInfo(Integer id) {
        return SchoolService.getSchoolInfoById(id);
    }

    @GetMapping("/school/name") //返回符合请求学校名字的学校
    @ResponseStatus(HttpStatus.OK)
    public SchoolInfo getSchoolInfo(String name) {
        return SchoolService.getSchoolInfoByName(name);
    }

    @GetMapping("/school/present") //判断学校是否被注册
    @ResponseStatus(HttpStatus.OK)
    public Boolean testIfSchoolPresent(String name) {
        return SchoolService.testIfSchoolPresentByName(name);
    }

    @GetMapping("/school/nametoid") //通过学校id查询学校名字
    @ResponseStatus(HttpStatus.OK)
    public String SchoolIdToName(Integer id){
        return SchoolService.idToName(id);
    }

    /**
     * 学校注册
     *
     * @param school 学校实体
     * @return 返回学校注册成功
     */
    @PostMapping("/school") //创建学校
    @ResponseStatus(HttpStatus.CREATED)
    public String schoolRegister(
            @RequestBody @Validated School school
    ) {
        if (school.getName() == null || school.getName().isBlank()) {
            throw new ClpException(ExceptionEnum.IDENTIFIER_PARAM_MISSING);
        }
        if (SchoolService.testIfSchoolPresentByName(school.getName())) {
            throw new ClpException(ExceptionEnum.SCHOOL_ALREADY_EXIST_ERROR);
        }
        SchoolService.registerSchool(school);
        return "学校注册成功";
    }

    @DeleteMapping(path = "/school/delete")
    public String deleteSchool(@RequestParam Integer id) {

        if (SchoolService.testIfSchoolPresentById(id)){
            throw new ClpException(ExceptionEnum.SCHOOL_NOT_EXIST);
        }

        SchoolService.deleteSchool(id);
        return "删除成功";
    }

    /**
     * 修改学校信息
     * 需要验证身份是否是学校管理者或系统管理员
     */
    @PutMapping("/school")
    @ResponseStatus(HttpStatus.OK)
    public String changeUserInfoForSchool(
            @RequestBody @Validated School school
    ) {
        if (school.getId() == null || school.getName() == null || school.getName().isBlank()) {
            throw new ClpException(ExceptionEnum.IDENTIFIER_PARAM_MISSING);
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
