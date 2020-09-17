package com.starlink.clp.util;

import com.starlink.clp.constant.ExceptionEnum;
import com.starlink.clp.constant.FileTypeEnum;
import com.starlink.clp.exception.ClpException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author CamWang
 * @since 2020/9/7 14:35
 */
@Component
public class FileUtil {

    @Getter
    public static String EXECUTABLE_FILE_PATH;
    @Getter
    public static String TEST_DATA_FILE_PATH;

    @Value("${clp.file.path.executable}")
    public void setExecutableFilePath(String executableFilePath) {
        EXECUTABLE_FILE_PATH = executableFilePath;
    }
    @Value("${clp.file.path.test.data}")
    public void setTestInputFilePath(String testFilePath) {
        TEST_DATA_FILE_PATH = testFilePath;
    }

    @Getter
    public static Long FILE_MAX_SIZE;

    @Value("${clp.file.max.size}")
    public void setFileMaxSize(Long fileMaxSize) {
        FILE_MAX_SIZE = fileMaxSize;
    }

    /**
     * 功能基本和ImageUtil一致，去掉了UUID生成
     */
    public static ArrayList<String> saveMultipleFiles(MultipartFile[] files, String path, Integer limit) {
        ArrayList<String> savedFiles = new ArrayList<>();
        if (files == null || files.length < 1) {
            throw new ClpException(ExceptionEnum.FILE_NOT_PRESENT);
        }
        if (files.length > limit) {
            throw new ClpException(ExceptionEnum.FILE_TOO_MANY);
        }
        for (MultipartFile f : files) {
            if (f == null) {
                throw new ClpException(ExceptionEnum.FILE_NOT_PRESENT);
            }
            if (f.getSize() > FILE_MAX_SIZE) {
                throw new ClpException(ExceptionEnum.FILE_TOO_LARGE);
            }
            String fileName = f.getOriginalFilename();
            String filePath = path + fileName;
            File file = new File(filePath);
            try {
                f.transferTo(file);
                savedFiles.add(fileName);
            } catch (IOException e) {
                throw new ClpException(ExceptionEnum.FILE_WRITE_ERROR);
            }
        }
        return savedFiles;
    }

    /**
     * 封装好仅能保存一个文件的方法
     *
     * @return 返回保存好的文件名称
     */
    public static String executableFileProcess(MultipartFile file, FileTypeEnum fileType) {
        String path = EXECUTABLE_FILE_PATH;
        String fileName = null;
        if (!(file == null || file.isEmpty())) {
            try {
                fileName = FileUtil.saveMultipleFiles(new MultipartFile[] {file}, path, 1).get(0);
            } catch (IndexOutOfBoundsException e) {
                throw new ClpException(ExceptionEnum.FILE_WRITE_ERROR);
            }
            if (fileName == null || file.isEmpty()) {
                throw new ClpException(ExceptionEnum.FILE_WRITE_ERROR);
            }
        }
        return fileName;
    }
}
