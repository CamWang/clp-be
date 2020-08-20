package com.starlink.clp.service;

import com.starlink.clp.constant.ExceptionEnum;
import com.starlink.clp.entity.School;
import com.starlink.clp.exception.ClpException;
import com.starlink.clp.projection.school.SchoolInfo;
import com.starlink.clp.repository.SchoolRepository;
import com.starlink.clp.util.FileUtil;
import com.starlink.clp.util.IgnoreNullPropertiesUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * @author Qilin
 * @since 2020/8/13 21:18
 */
@Service
public class SchoolService {

    private final SchoolRepository schoolRepo;

    public SchoolService(SchoolRepository schoolRepo) {
        this.schoolRepo = schoolRepo;
    }

    public Page<SchoolInfo> SchoolInfo(Pageable pageable) {
        return schoolRepo.getAllBy(pageable);
    }

    public Optional<School> getSchoolById(Integer id) {
        return schoolRepo.findById(id);
    }

    public SchoolInfo getSchoolInfoById(Integer id) {
        return schoolRepo.getFirstById(id);
    }

    public SchoolInfo getSchoolInfoByName(String name) {
        return schoolRepo.getFirstByName(name);
    }


    /**
     * 判断某个名字的学校是否已经注册，返回Bollean类型的值
     *
     * @param schoolname 传入String类型的学校名字
     * @return 返回 Boolean 类型的值 true Or false 来表示学校是否存在
     */
    public Boolean testIfSchoolPresentByName(String schoolname) {
        return schoolRepo.existsSchoolByName(schoolname);
    }

    public void registerSchool(School school) {
        /**
         * 验证操作者的身份是否为系统管理员
         */
        schoolRepo.save(school);
    }

    @Transactional
    public void modifySchool(School school) {
        School oldSchool = schoolRepo.findSchoolById(school.getId());

        if (oldSchool == null) {
            throw new ClpException(ExceptionEnum.SCHOOL_NOT_EXIST);
        }
        if (testIfSchoolPresentByName(school.getName()) && !school.getId().equals(schoolRepo.getFirstByName(school.getName()).getId())) {
            throw new ClpException(ExceptionEnum.SCHOOL_ALREADY_EXIST_ERROR);
        }
        BeanUtils.copyProperties(school, oldSchool, IgnoreNullPropertiesUtil.getNullPropertyNames(school));
        schoolRepo.save(oldSchool);
    }

    @Transactional
    public void setAvatar(Integer id, String name, MultipartFile avatarFile) {
        School oldSchool = schoolRepo.findSchoolByNameAndId(name, id);
        if (oldSchool == null) {
            throw new ClpException(ExceptionEnum.SCHOOL_NOT_EXIST);
        }
        String avatar = FileUtil.avatarProcess(avatarFile);
        oldSchool.setAvatar(avatar);
        schoolRepo.save(oldSchool);
    }

    public void deleteSchool(Integer id) {
        schoolRepo.deleteById(id);
    }

}
