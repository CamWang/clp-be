package com.starlink.clp.repository;

import com.starlink.clp.entity.Rejudge;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RejudgeRepository extends CrudRepository<Rejudge, Integer>,
        PagingAndSortingRepository<Rejudge, Integer>,
        JpaSpecificationExecutor<Rejudge> {
}
