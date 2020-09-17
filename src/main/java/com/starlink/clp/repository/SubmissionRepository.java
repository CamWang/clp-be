package com.starlink.clp.repository;

import com.starlink.clp.entity.Submission;
import com.starlink.clp.projection.submission.SubmissionJudgeInfo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SubmissionRepository extends CrudRepository<Submission, Integer>,
        PagingAndSortingRepository<Submission, Integer>,
        JpaSpecificationExecutor<Submission> {
    @Query(value = "SELECT id FROM submission WHERE result<2", nativeQuery = true)
    List<Integer> findAllId(Pageable pageable);

    SubmissionJudgeInfo findFirstById(Integer id);
}
