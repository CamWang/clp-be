package com.starlink.clp.controller;

import com.starlink.clp.entity.Contest;
import com.starlink.clp.projection.contest.ContestDetail;
import com.starlink.clp.projection.contest.ContestSimple;
import com.starlink.clp.service.ContestService;
import com.starlink.clp.validate.ValidPage;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author CamWang
 * @since 2020/9/7 9:29
 */
@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
@Validated
public class ContestController {

    private ContestService contestService;

    public ContestController(ContestService contestService) {
        this.contestService = contestService;
    }

    @GetMapping("/contest")
    @ResponseStatus(HttpStatus.OK)
    public Page<ContestSimple> getContest(
            @ValidPage Pageable pageable
    ){
        return contestService.getContest(pageable);
    }

    @GetMapping("/contest/detail")
    @ResponseStatus(HttpStatus.OK)
    public ContestDetail getContestDetail(
            @Range(min = 1, max = 2000, message = "比赛ID范围超界") Integer id
    ) {
        return contestService.getContestDetail(id);
    }

    @GetMapping("/contest/time")
    @ResponseStatus(HttpStatus.OK)
    public Date getServerDate() {
        return new Date();
    }





}
