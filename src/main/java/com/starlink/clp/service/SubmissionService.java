package com.starlink.clp.service;

import com.starlink.clp.projection.SubmissionProjection;
import com.starlink.clp.repository.SubmissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author CamWang
 * @since 2020/8/3 10:38
 */
@Service
public class SubmissionService {

    private final SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    public Page<SubmissionProjection> getAll(Pageable pageable) {
        return submissionRepository.findAllBy(pageable);
    }

}
