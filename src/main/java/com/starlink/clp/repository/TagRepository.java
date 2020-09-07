package com.starlink.clp.repository;

import com.starlink.clp.entity.Tag;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author CamWang
 * @since 2020/9/7 9:33
 */
public interface TagRepository extends CrudRepository<Tag, Integer>,
        PagingAndSortingRepository<Tag, Integer>,
        JpaSpecificationExecutor<Tag> {

}
