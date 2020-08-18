package com.starlink.clp.repository;

import com.starlink.clp.entity.School;
import com.starlink.clp.projection.school.SchoolInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Qilin
 * @since 2020/8/8 17:23
 */

@Repository
public interface SchoolRepository extends CrudRepository<School, Integer>, PagingAndSortingRepository<School, Integer>, JpaSpecificationExecutor<School> {

    //对外提供学校公开列表
    Page<SchoolInfo> getAllBy(Pageable pageable);
    //对本校用户提供学校基本信息
    SchoolInfo getFirstById(Integer id);
    SchoolInfo getFirstByName(String name);
    //查询学校是否已经注册
    Boolean existsSchoolById(Integer id);
    Boolean existsSchoolByName(String name);
    //修改学校信息时获取学校的完整信息
    School findSchoolByNameAndId(String name, Integer id);

}
