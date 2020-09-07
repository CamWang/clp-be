package com.starlink.clp.repository;

import com.starlink.clp.entity.Problem;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProblemRepository extends CrudRepository<Problem, Integer>,
        PagingAndSortingRepository<Problem, Integer>,
        JpaSpecificationExecutor<Problem> {
}
