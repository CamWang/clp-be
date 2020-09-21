package com.starlink.clp.service;

import com.starlink.clp.entity.Problem;
import com.starlink.clp.projection.problem.ProblemDetail;
import com.starlink.clp.projection.problem.ProblemJudgeInfo;
import com.starlink.clp.projection.problem.ProblemSimple;
import com.starlink.clp.repository.ProblemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author CamWang
 * @since 2020/9/7 9:28
 */
@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public Page<ProblemSimple> getProblemSimpleByPage(Pageable pageable) {
        return problemRepository.getAllBy(pageable);
    }

    public ProblemDetail getProblemDetailById(Integer id) {
        return problemRepository.findFirstById(id);
    }

    public Problem createProblem(Problem problem) {
        problemRepository.save(problem);
        return problem;
    }

    public ProblemJudgeInfo getProblemJudgeInfo(Integer id) {
        return problemRepository.getFirstById(id);
    }

    public Page<ProblemSimple> getProblemSimpleByKeyword(String keyword, Pageable pageable) {
        return problemRepository.findAllByTitleLike("%"+keyword+"%", pageable);
    }


}
