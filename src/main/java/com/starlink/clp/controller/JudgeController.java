package com.starlink.clp.controller;

import com.starlink.clp.constant.ResultEnum;
import com.starlink.clp.entity.Submission;
import com.starlink.clp.projection.submission.SubmissionJudgeInfo;
import com.starlink.clp.service.JudgeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author CamWang
 * @since 2020/9/14 9:55
 */
@RestController
@RequestMapping("/judge")
public class JudgeController {

    private JudgeService judgeService;

    public JudgeController(JudgeService judgeService) {
        this.judgeService = judgeService;
    }

    /**
     * 检查登录状态
     *
     * Judged & Judge Client
     * method: check_login()
     */
    @PostMapping("/check")
    public String checkLogin() {
        return "1";
    }

    /**
     * 获取未判题解
     *
     * Judged
     * method: _get_jobs_http()
     */
    @PostMapping("/queue")
    public String getJudgeSolution(
            @RequestParam(name = "max_running", required = false, defaultValue = "1") Integer limit
    ) {
        List<Integer> list =  judgeService.getJudgeSubmission(limit);
        if (list.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(Integer id : list) {
            stringBuilder.append(id);
        }
        return stringBuilder.toString();
    }

    /**
     * 获取题解源码
     *
     *  Judge_Client
     *  method: _get_solution_http()
     */
    @PostMapping("/submission/code")
    public String getSubmissionCode(
            @RequestParam(name = "sid") Integer id
    ) {
        String code = judgeService.getSubmissionCode(id);
        if (code == null || code.isBlank()) {
            return "";
        }
        return code;
    }

    /**
     * 获取题解信息
     *
     * Judge_Client
     * method: _get_solution_info_http()
     */
    @PostMapping("/submission/info")
    public String getSubmissionInfo(
            @RequestParam(name = "sid") Integer id
    ) {
        SubmissionJudgeInfo submissionJudgeInfo = judgeService.getSubmissionInfo(id);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(submissionJudgeInfo.getProblem().getId()).append("\n");
        stringBuilder.append(submissionJudgeInfo.getUser().getId()).append("\n");
        stringBuilder.append(submissionJudgeInfo.getLanguage()).append("\n");
        stringBuilder.append(submissionJudgeInfo.getContest().getId()).append("\n");
        return stringBuilder.toString();
    }

    /**
     * 获取测试数据文件列表
     *
     * Judge_Client
     * method: get_test_file()
     */
    @PostMapping("/data/list")
    public String getTestFileList(
            @RequestParam("pid") Integer id
    ) {
        ArrayList<File> fileList = judgeService.getFileList(id);
        StringBuilder stringBuilder = new StringBuilder();
        for (File file : fileList) {
            stringBuilder.append(file.lastModified()).append("\n");
            stringBuilder.append(file.getName()).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * 获取判题数据
     *
     * Judge_Client
     */
    @PostMapping("/data/file")
    public String getTestFile(
            @RequestParam("filename") String file
    ) {
        return judgeService.getTestFile(file);
    }


    /**
     * 写入题解结果
     *
     * Judge_Client
     * method: _check_out_http()
     */
    @PostMapping("/checkout")
    public String checkoutSolution(
            @RequestParam(name = "sid") Integer id,
            @RequestParam(name = "result") Integer result
    ){
        judgeService.checkSubmission(id, result);
        return "1";
    }

    /**
     * 更新题解信息
     *
     * Judge Client
     * method: _update_solution_http()
     */
    @PostMapping("/update")
    public void updateSolution(
            @RequestParam(name = "sid") Integer id,
            @RequestParam(name = "result") Integer result,
            @RequestParam(name = "time", required = false, defaultValue = "0") Integer time,
            @RequestParam(name = "memory", required = false, defaultValue = "0") Integer memory,
            @RequestParam(name = "sim", required = false, defaultValue = "0") Integer sim,
            @RequestParam(name = "simid", required = false, defaultValue = "0") Integer simId,
            @RequestParam(name = "pass_rate", required = false, defaultValue = "0") Float passRate
    ) {
        Submission submission = new Submission();
        submission.setId(id);
        submission.setEnd(new Date());
        submission.setResult(ResultEnum.values()[result]);
        submission.setTime(time);
        submission.setMemory(memory);
        submission.setSim(sim);
        submission.setSimId(simId);
        submission.setPassRate(passRate);
        judgeService.updateSubmission(submission);
    }

    /**
     * 添加编译信息
     *
     * Judge_Client
     * method: _addceininfo_http
     */
    @PostMapping("/info/compile")
    public void addCompileInfo(
            @RequestParam(name = "sid") Integer id,
            @RequestParam(name = "info") String info
    ) {
        judgeService.addCompileInfo(id, info);
    }

    /**
     * 添加运行时信息
     *
     * Judge_Client
     * method: _addreinfo_http
     */
    @PostMapping("/info/run")
    public void addRunInfo(
            @RequestParam(name = "sid") Integer id,
            @RequestParam(name = "info") String info
    ) {
        judgeService.addRunTimeInfo(id, info);
    }
}
