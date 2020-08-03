package com.starlink.clp.controller;

import com.starlink.clp.constant.ExceptionEnum;
import com.starlink.clp.exception.ClpException;
import com.starlink.clp.projection.SubmissionProjection;
import com.starlink.clp.service.SubmissionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CamWang
 * @since 2020/8/3 10:45
 */
@RestController
public class SubmissionController {

    private final SubmissionService submissionService;

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @RequestMapping("/submission")
    public Page<SubmissionProjection> getAll(Pageable pageable, String stanLee) {
        System.out.println(stanLee);
        return this.submissionService.getAll(pageable);
    }

    @RequestMapping("/wild")
    public void excepiton() {
        throw new ClpException(ExceptionEnum.PARAMETER_ERROR);
    }

}
