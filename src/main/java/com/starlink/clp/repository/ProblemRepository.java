package com.starlink.clp.repository;

import com.starlink.clp.entity.Problem;
import com.starlink.clp.projection.problem.ProblemDetail;
import com.starlink.clp.projection.problem.ProblemSimple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends CrudRepository<Problem, Integer>,
        PagingAndSortingRepository<Problem, Integer>,
        JpaSpecificationExecutor<Problem> {
    Page<ProblemSimple> findAllBy(Pageable pageable);
    ProblemDetail findFirstById(Integer id);
}
