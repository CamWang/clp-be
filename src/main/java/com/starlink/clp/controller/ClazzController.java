package com.starlink.clp.controller;

import com.starlink.clp.constant.ExceptionEnum;
import com.starlink.clp.entity.Clazz;
import com.starlink.clp.entity.School;
import com.starlink.clp.entity.User;
import com.starlink.clp.exception.ClpException;
import com.starlink.clp.projection.clazz.ClazzInfo;
import com.starlink.clp.projection.clazz.ClazzSimple;
import com.starlink.clp.repository.ClazzRepository;
import com.starlink.clp.service.ClazzService;
import com.starlink.clp.view.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import com.starlink.clp.validate.ValidPage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @author Wang Chengci
 * @since 2020/8/15 22:55
 */
@RestController
@Validated
public class ClazzController {

    private ClazzService clazzService;
    public ClazzController(ClazzService clazzService){
        this.clazzService = clazzService;
    }

    /**
     * 获取"所有"班级及其用户的列表，使用分页
     * 包括
     *
     * Clazz_Name,Clazz_Id,Clazz_Description,
     * School_id,School_name
     *
     * @param pageable
     * @return
     */

    @GetMapping("/clazz")
    //返回状态码!
    @ResponseStatus(HttpStatus.OK)
    public Page<ClazzSimple> getAllClazzSimple(
            @ValidPage(message = "页面请求错误")
                    Pageable pageable
    ) {
        return clazzService.getAllClazzSimple(pageable);
    }


    /**
     * 通过班级id
     * 展现班级的具体信息给前端界面
     * 前端调取clazz的id属性返回给方法
     *
     * @return
     */

    @GetMapping("/clazz/detail_id")
    @ResponseStatus(HttpStatus.OK)
    public ClazzInfo getClazzInfoByClazzId(Integer id){
        return clazzService.getClazzInfoById(id);
    }

    /**
     * 通过班级name
     * @param name
     * @return
     */
    @GetMapping("/clazz/detail_name")
    @ResponseStatus(HttpStatus.OK)
    public ClazzInfo getClazzInfoByClazzName(String name){
        return clazzService.getClazzInfoByName(name);
    }

    /**
     * 通过学校school_id查询班级
     * @param school_id
     * @return
     */

    @GetMapping("/clazz/detail/schoolId")
    @ResponseStatus(HttpStatus.OK)
    public Page<ClazzSimple> getAllClazzBySchool_Id(
            @ValidPage(message = "页面请求错误") Pageable pageable,Integer schoolId){
        return clazzService.getAllClazzBySchool_Id(pageable,schoolId);
    }


    /**
     * 通过学校school_name查询班级
     * @param schoolName
     * @return
     */

    @GetMapping("/clazz/detail/schoolName")
    @ResponseStatus(HttpStatus.OK)
    public Page<ClazzSimple> getAllClazzBySchool_Name(
            @ValidPage(message = "页面请求错误") Pageable pageable,String schoolName){
        return clazzService.getAllClazzBySchool_Name(pageable,schoolName);
    }


    /**
     * 判断此班级在一个学校的名称是否已经存在
     * 存在返回true
     * 不存在返回false
     * @param name
     * @return
     */

    @GetMapping("/clazz/repeat")
    public Boolean testClazzNameExists(
            Integer schoolId,
            @Length(min = 2, max = 32,message = "班级名称长度在2-32字符之间")
            String name
    ) {
        return clazzService.testIfClazzNamePresent(schoolId,name);
    }

    /**
     * 班级注册
     *
     * @param clazz
     * @return
     */

    /**
     * 班级注册的json格式
     *  {
     *     "name":"计科19-3",
     *     "description":"123456"
     * }
     */


    /**
     *  用同样的班级名字注册之后会产生school_id增加的情况！！！
     */
    @PostMapping("/clazz")
    @ResponseStatus(HttpStatus.CREATED)
    public String clazzRegister(
            @RequestBody @Validated({ClazzRegisterView.class, ClazzSecurityView.class}) Clazz clazz
    ) {

        //对同一学校班级不重复的判断放在修改班级信息模块

        clazzService.registerClazz(clazz);
        return "班级注册成功";
    }

    /**
     * 修改班级信息
     */
    @PutMapping("/clazz")
    @ResponseStatus(HttpStatus.OK)
    public String changeClazzInfoForClazz(
            @RequestBody @Validated({ClazzModifiedView.class, ClazzSecurityView.class}) Clazz clazz

    ) {
//        if (clazzService.testIfClazzNamePresent(school.getId(),clazz.getName())) {
//            throw new ClpException(ExceptionEnum.CLAZZ_ALREADY_EXIST_ERROR);
//        }
        clazzService.modifyClazz(clazz);
        return "班级信息修改成功";
    }

    /**
     * 注销班级
     * @param clazz
     * @return
     */
    @DeleteMapping("/clazz/delete")
    @ResponseStatus(HttpStatus.OK)
    public String deleteSchool(
            @RequestBody @Validated({ClazzModifiedView.class,ClazzSecurityView.class})
                    Clazz clazz
    ) {
        clazzService.deleteClazz(clazz.getId());
        return "班级注销完成";
    }



}
