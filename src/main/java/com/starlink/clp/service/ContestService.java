package com.starlink.clp.service;

import com.starlink.clp.constant.ExceptionEnum;
import com.starlink.clp.entity.Contest;
import com.starlink.clp.exception.ClpException;
import com.starlink.clp.projection.contest.ContestDetail;
import com.starlink.clp.projection.contest.ContestSimple;
import com.starlink.clp.repository.ContestRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author CamWang
 * @since 2020/9/7 9:28
 */
@Service
public class ContestService {

    private ContestRepository contestRepository;

    public ContestService(ContestRepository contestRepository) {
        this.contestRepository = contestRepository;
    }

    public Page<ContestSimple> getContest(Pageable pageable) {
        if (contestRepository.count() == 0) {
            throw new ClpException(ExceptionEnum.NO_CONTEST_AVALIBLE);
        }
        return contestRepository.findAllByEnabledIsTrue(pageable);
    }

    public ContestDetail getContestDetail(Integer id) {
        if (!contestRepository.existsById(id)) {
            throw new ClpException(ExceptionEnum.CONTEST_NOT_EXIST);
        }
        return contestRepository.findFirstByIdAndEnabledIsTrue(id);
    }
}
