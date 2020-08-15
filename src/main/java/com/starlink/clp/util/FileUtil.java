package com.starlink.clp.util;

import com.starlink.clp.constant.ExceptionEnum;
import com.starlink.clp.exception.ClpException;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * 保存文件的工具类
 * 目前只有保存图片的能力
 *
 * @author CamWang
 * @since 2020/8/13 16:30
 */

@Component
public class FileUtil {

    // 图片保存路径
    @Getter
    public static String IMAGE_PATH;

    // 图片最大大小
    @Getter
    public static Long IMAGE_MAX_SIZE;

    @Value("${clp.avatar.file.path}")
    public void setImagePath(String filePath) {
        IMAGE_PATH = filePath;
    }

    @Value("${clp.avatar.max.size}")
    public void setImageMaxSize(Long imageMaxSize) {
        IMAGE_MAX_SIZE = imageMaxSize;
    }

    /**
     * 保存多个图片
     * @param images 传入多个图片文件，文件名会使用UUID生成随机名称，
     *               生成的文件名会作为返回值的一部分
     * @param limit 限制文件数量最大为几个，超过数量会抛出IMAGE_TOO_MANY错误
     * @return 返回所有已保存文件的用户名的ArrayList
     *
     */
    public static ArrayList<String> saveMultipleImages(MultipartFile[] images, Integer limit) {
        ArrayList<String> savedImages = new ArrayList<>();
        if (images == null) {
            throw new ClpException(ExceptionEnum.IMAGE_NOT_PRESENT);
        }
        if (images.length > limit) {
            throw new ClpException(ExceptionEnum.IMAGE_TOO_MANY);
        }
        for (MultipartFile image : images) {
            if (image == null) {
                throw new ClpException(ExceptionEnum.IMAGE_NOT_PRESENT);
            }
            if (image.getSize() > IMAGE_MAX_SIZE) {
                throw new ClpException(ExceptionEnum.IMAGE_TOO_LARGE);
            }
            String originalFileName = image.getOriginalFilename();
            String suffix = "";
            if (originalFileName != null) {
                suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
            } else {
                throw new ClpException(ExceptionEnum.IMAGE_NAME_EMPTY);
            }
            if (suffix.isEmpty()) {
                throw new ClpException(ExceptionEnum.IMAGE_NO_SUFFIX);
            }
            String name = UUID.randomUUID().toString();
            String fileName = name + suffix;
            String filePath = IMAGE_PATH + fileName;
            File file = new File(filePath);
            try {
                image.transferTo(file);
                savedImages.add(fileName);
            } catch (IOException e) {
                throw new ClpException(ExceptionEnum.IMAGE_WRITE_ERROR);
            }
        }
        return savedImages;
    }


    /**
     * 封装保存图片的方法用来做一些判空和校验
     * 仅能保存一个图片
     *
     * @return 返回保存好的图片名称
     */
    public static String avatarProcess(MultipartFile avatarFile) {
        String avatar = null;
        if (!(avatarFile == null || avatarFile.isEmpty())) {
            try {
                avatar = FileUtil.saveMultipleImages(new MultipartFile[]{avatarFile}, 1).get(0);
            } catch (IndexOutOfBoundsException e) {
                throw new ClpException(ExceptionEnum.IMAGE_WRITE_ERROR);
            }
            if (avatar == null || avatar.isEmpty()) {
                throw new ClpException(ExceptionEnum.IMAGE_WRITE_ERROR);
            }
        }
        return avatar;
    }

}
