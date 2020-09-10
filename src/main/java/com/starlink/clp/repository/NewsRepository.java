package com.starlink.clp.repository;

import com.starlink.clp.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author CamWang
 * @since 2020/9/10 16:04
 */
@Repository
public interface NewsRepository extends CrudRepository<News, Integer>,
        PagingAndSortingRepository<News, Integer>,
        JpaSpecificationExecutor<News> {
    Page<News> findBy(Pageable pageable);
}
