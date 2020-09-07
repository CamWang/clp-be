package com.starlink.clp.repository;

import com.starlink.clp.entity.Submission;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SubmissionRepository extends CrudRepository<Submission, Integer>,
        PagingAndSortingRepository<Submission, Integer>,
        JpaSpecificationExecutor<Submission> {
}
