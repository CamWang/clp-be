package com.starlink.clp.repository;

import com.starlink.clp.entity.Submission;
import com.starlink.clp.projection.SubmissionProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author CamWang
 * @since 2020/8/3 10:38
 */
@Repository
public interface SubmissionRepository extends JpaRepository<Submission, String>, JpaSpecificationExecutor<Submission> {
    Page<SubmissionProjection> findAllBy(Pageable pageable);
}
