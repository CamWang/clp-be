package com.starlink.clp.service;

import com.starlink.clp.constant.ExceptionEnum;
import com.starlink.clp.entity.Clazz;
import com.starlink.clp.entity.School;
import com.starlink.clp.entity.User;
import com.starlink.clp.exception.ClpException;
import com.starlink.clp.projection.clazz.ClazzInfo;
import com.starlink.clp.projection.clazz.ClazzSimple;
import com.starlink.clp.repository.ClazzRepository;
import com.starlink.clp.util.IgnoreNullPropertiesUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * @author Wang Chengci
 * @since 2020/8/15 22:56
 */

@Service
public class ClazzService {
    private final ClazzRepository clazzRepository;

    public ClazzService(ClazzRepository clazzRepository) {
        this.clazzRepository = clazzRepository;
    }

    //获得普通信息（普通用户）
    public Page<ClazzSimple> getAllClazzSimple(Pageable pageable){
        return clazzRepository.getAllBy(pageable);
    }

    public Page<ClazzSimple> getAllClazzBySchool_Id(Pageable pageable ,Integer schoolId){
        return  clazzRepository.getClazzBySchool_Id(pageable,schoolId);
    }

    public Page<ClazzSimple> getAllClazzBySchool_Name(Pageable pageable ,String schoolName){
        return  clazzRepository.getClazzBySchool_Name(pageable,schoolName);
    }

    public Optional<Clazz> getClazzById(Integer id){
        return clazzRepository.findById(id);
    }

    //获得详细信息（管理员）
    public ClazzInfo getClazzInfoById(Integer id){
        return clazzRepository.getFirstById(id);
    }
    public ClazzInfo getClazzInfoByName(String name){
        return clazzRepository.getFirstByName(name);
    }

    /**
     * 查看同一个学校的某个班级是否已经被注册
     * @param schoolId
     * @param name
     * @return
     */
    public Boolean testIfClazzNamePresent(Integer schoolId,String name) {
        return clazzRepository.existsClazzBySchool_IdAndName(schoolId,name);
    }


    public void registerClazz(Clazz clazz){
        //需查看是否为管理员或者班级管理者
        clazzRepository.save(clazz);
    }

    @Transactional
    public void modifyClazz(Clazz clazz){
        Clazz oldClazz = clazzRepository.findClazzById(clazz.getId());//id是不变的
        if(oldClazz == null){
            //班级不存在，要先创建
            throw new ClpException(ExceptionEnum.CLAZZ_NOT_EXIST);
        }
//        if(testIfClazzNamePresent(clazz.getSchool().getId(),clazz.getName())){
//            throw new ClpException(ExceptionEnum.CLAZZ_ALREADY_EXIST_ERROR);
//        }
        BeanUtils.copyProperties(clazz,oldClazz,IgnoreNullPropertiesUtil.getNullPropertyNames(clazz));
        clazzRepository.save(oldClazz);
    }

    /**
     * 注销班级
     */
    public void deleteClazz(Integer id){
        /**
         * 验证管理员
         */
        clazzRepository.deleteById(id);
    }



}
