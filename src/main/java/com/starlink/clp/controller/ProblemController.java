package com.starlink.clp.controller;

import com.starlink.clp.entity.Problem;
import com.starlink.clp.projection.problem.ProblemDetail;
import com.starlink.clp.projection.problem.ProblemJudgeInfo;
import com.starlink.clp.projection.problem.ProblemSimple;
import com.starlink.clp.service.ProblemService;
import com.starlink.clp.validate.ValidPage;
import com.starlink.clp.view.ProblemCreateView;
import com.starlink.clp.view.ProblemModifiedView;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author CamWang
 * @since 2020/9/7 9:28
 */
@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
@Validated
public class ProblemController {

    private final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    /**
     * 获取所有题目列表
     *
     * @param pageable 传入size每页元素数、page从0开始的页码、sort排序方式
     *                 三个参数对应默认为20、0、null
     */
    @GetMapping("/problem")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProblemSimple> getAllProblemSimple(
            @ValidPage(message = "页面请求错误")Pageable pageable
    ) {
        System.out.println(pageable.toString());
        return problemService.getProblemSimpleByPage(pageable);
    }

    /**
     * 通过关键字搜索
     */
    @PostMapping("/problem/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<ProblemSimple> searchProblem(
            @Length(max = 20, message = "搜索最长20字")
            @RequestParam(name = "keyword", defaultValue = "", required = false)
                    String keyword,
            @Range(min = 5, max = 20, message = "页面超范围")
            @RequestParam(name = "size", defaultValue = "5", required = false)
            Integer size,
            @Range(min = 0, max = 500, message = "页面超范围")
            @RequestParam(name = "page", defaultValue = "0", required = false)
                    Integer page
    ) {
        return problemService.getProblemSimpleByKeyword(keyword, PageRequest.of(page, size));
    }

    /**
     * 获取题目详情
     *
     * @param id 传入待查询的题目的ID
     */
    @GetMapping("/problem/detail")
    @ResponseStatus(HttpStatus.OK)
    public ProblemDetail getProblemSimple(
            @Range(min = 0, max = 50000, message = "请求ID超限") Integer id
    ) {
        return problemService.getProblemDetailById(id);
    }

    /**
     * 新建一个题目
     */
    @PostMapping("/problem")
    @ResponseStatus(HttpStatus.CREATED)
    public Problem createProblem(
            @RequestBody @Validated({ProblemCreateView.class})Problem problem
    ) {
        return problemService.createProblem(problem);
    }

    /**
     * 获取判题题目信息
     *
     * in: Judge_Client
     * method: _get_problem_info_http()
     */
    @PostMapping("/judge/problem")
    public void getJudgeProblemInfo(
            @RequestParam(name = "pid") Integer id
    ) {
        ProblemJudgeInfo problemJudgeInfo = problemService.getProblemJudgeInfo(id);
        System.out.println(problemJudgeInfo.getTimeLimit());
        System.out.println(problemJudgeInfo.getMemoryLimit());
        System.out.println(problemJudgeInfo.getSpecialJudge()? 1 : 0);
    }


    /**
     * 修改一个题目的题面
     */

    /**
     * 上传题目的输入输出
     */


}
