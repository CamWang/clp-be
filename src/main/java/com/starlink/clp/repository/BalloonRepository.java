package com.starlink.clp.repository;

import com.starlink.clp.entity.Balloon;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BalloonRepository extends CrudRepository<Balloon, Integer>,
        PagingAndSortingRepository<Balloon, Integer>,
        JpaSpecificationExecutor<Balloon> {
}
