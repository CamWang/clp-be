package com.starlink.clp.repository;

import com.starlink.clp.entity.School;
import com.starlink.clp.projection.school.SchoolInfo;
import com.starlink.clp.projection.school.SchoolSimple;
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

    Page<SchoolSimple> getAllBy(Pageable pageable);
    SchoolInfo getFirstById(Integer id);
    SchoolInfo getFirstByName(String name);

    Boolean existsSchoolById(Integer id);
    Boolean existsSchoolByName(String name);

    School findSchoolByNameAndId(String name, Integer id);


}
