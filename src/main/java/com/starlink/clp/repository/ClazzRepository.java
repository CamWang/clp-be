package com.starlink.clp.repository;

import com.starlink.clp.entity.Clazz;
import com.starlink.clp.entity.School;
import com.starlink.clp.projection.clazz.ClazzInfo;
import com.starlink.clp.projection.clazz.ClazzSimple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author Wang Chengci
 * @since 2020/8/15 22:55
 */

@Repository
public interface ClazzRepository extends CrudRepository<Clazz,Integer>,
        PagingAndSortingRepository<Clazz,Integer>,
        JpaSpecificationExecutor<Clazz> {
    //展示所有班级列表
    Page<ClazzSimple> getAllBy(Pageable pageable);
    //根据学校id展示班级列表
    Page<ClazzSimple> getClazzBySchool_Id(Pageable pageable,Integer schoolId);
    //根据学校name展示班级列表
    Page<ClazzSimple> getClazzBySchool_Name(Pageable pageable,String school_name);
    //根据班级id展示班级具体信息
    ClazzInfo getFirstById(Integer id);
    //根据班级name展示班级具体信息
    ClazzInfo getFirstByName(String name);
    //判断同一个学校的班级是否已经被注册
    Boolean existsClazzBySchool_IdAndName(Integer schoolId,String name);
    Boolean existsClazzByName(String name);
    Clazz findClazzById(Integer id);
}
