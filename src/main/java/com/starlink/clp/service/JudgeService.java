package com.starlink.clp.service;

import com.starlink.clp.constant.ResultEnum;
import com.starlink.clp.entity.Submission;
import com.starlink.clp.projection.submission.SubmissionJudgeInfo;
import com.starlink.clp.repository.SubmissionRepository;
import com.starlink.clp.util.IgnoreNullPropertiesUtil;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author CamWang
 * @since 2020/9/14 15:43
 */

@Service
public class JudgeService {

    @Getter
    public static String TEST_DATA_FILE_PATH;

    @Value("${clp.file.path.test.data}")
    public void setTestInputFilePath(String testFilePath) {
        TEST_DATA_FILE_PATH = testFilePath;
    }

    private SubmissionRepository submissionRepository;

    public JudgeService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    public List<Integer> getJudgeSubmission(Integer limit) {
        return submissionRepository.findAllId(
                PageRequest.of(0, limit, Sort.by(Sort.Direction.ASC, "id"))
        );
    }

    public void checkSubmission(Integer id, Integer result) {
        Optional<Submission> submission = submissionRepository.findById(id);
        if (submission.isPresent()){
            Submission temp = submission.get();
            temp.setResult(ResultEnum.values()[result]);
            submissionRepository.save(temp);
        }
    }

    public void updateSubmission(Submission submission) {
        Optional<Submission> oldSubmission = submissionRepository.findById(submission.getId());
        if (oldSubmission.isPresent()) {
            BeanUtils.copyProperties(
                    submission,
                    oldSubmission.get(),
                    IgnoreNullPropertiesUtil.getNullPropertyNames(submission));
            submissionRepository.save(submission);
        }
    }

    public void addCompileInfo(Integer id, String info) {
        Optional<Submission> oldSubmission = submissionRepository.findById(id);
        if (oldSubmission.isPresent()) {
            Submission submission = oldSubmission.get();
            submission.setCompileInfo(info);
            submissionRepository.save(submission);
        }
    }

    public void addRunTimeInfo(Integer id, String info) {
        Optional<Submission> oldSubmission = submissionRepository.findById(id);
        if (oldSubmission.isPresent()) {
            Submission submission = oldSubmission.get();
            submission.setRunInfo(info);
            submissionRepository.save(submission);
        }
    }

    public String getSubmissionCode(Integer id) {
        Optional<Submission> submission = submissionRepository.findById(id);
        if (submission.isPresent()) {
            return submission.get().getCode();
        }
        return "";
    }

    public SubmissionJudgeInfo getSubmissionInfo(Integer id) {
        return submissionRepository.findFirstById(id);
    }

    public ArrayList<File> getFileList(Integer id) {
        File file = new File(TEST_DATA_FILE_PATH + id);
        ArrayList<File> fileList = new ArrayList<>();
        if (file.isDirectory()) {
            String files[] = file.list();
            for (String f : files) {
                File tempFile = new File(TEST_DATA_FILE_PATH + id + "/" + f);
                fileList.add(tempFile);
            }
            return fileList;
        }
        return null;
    }

    public String getTestFile(String fileName){
        String content = "";
        try {
            content = new String(Files.readAllBytes(Path.of(TEST_DATA_FILE_PATH + fileName)));
        } catch (IOException e) {
            System.err.println("Get Test Data Failed: " + fileName);
        }
        return content;
    }
}
