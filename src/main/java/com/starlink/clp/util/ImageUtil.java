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
import java.util.UUID;

/**
 * 保存文件的工具类
 * 目前只有保存图片的能力
 *
 * @author CamWang
 * @since 2020/8/13 16:30
 */

@Component
public class ImageUtil {

    // 图片保存路径
    @Getter
    public static String USER_AVATAR_PATH;
    @Getter
    public static String SCHOOL_AVATAR_PATH;
    @Getter
    public static String REGION_IMAGE_PATH;

    @Value("${clp.image.path.user.avatar}")
    public void setUserAvatarPath(String userAvatarPath) {
        USER_AVATAR_PATH = userAvatarPath;
    }
    @Value("${clp.image.path.school.avatar}")
    public void setSchoolAvatarPath(String schoolAvatarPath) {
        SCHOOL_AVATAR_PATH = schoolAvatarPath;
    }
    @Value("${clp.image.path.school.avatar}")
    public void setRegionImagePath(String regionImagePath) {
        REGION_IMAGE_PATH = regionImagePath;
    }

    // 图片最大大小
    @Getter
    public static Long IMAGE_MAX_SIZE;

    @Value("${clp.image.max.size}")
    public void setImageMaxSize(Long imageMaxSize) {
        IMAGE_MAX_SIZE = imageMaxSize;
    }

    /**
     * 保存多个图片
     * @param images 传入多个图片文件，文件名会使用UUID生成随机名称，
     *               生成的文件名会作为返回值的一部分
     * @param path 保存的文件路径
     * @param randomName 标记是否需要使用UUID随机文件名
     * @param limit 限制文件数量最大为几个，超过数量会抛出IMAGE_TOO_MANY错误
     * @return 返回所有已保存文件的用户名的ArrayList
     *
     */
    public static ArrayList<String> saveMultipleImages(MultipartFile[] images, String path, Boolean randomName, Integer limit) {
        ArrayList<String> savedImages = new ArrayList<>();
        if (images == null || images.length < 1) {
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
            String fileName = "";
            if (randomName) {
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
                fileName = name + suffix;
            } else {
                fileName = originalFileName;
            }
            String filePath = path + fileName;
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
     * 封装好仅能保存一个图片的方法
     *
     * @return 返回保存好的图片名称
     */
    public static String oneImageProcess(MultipartFile avatarFile, FileTypeEnum fileType) {
        String path = null;
        boolean randomName = true;
        if (fileType == FileTypeEnum.USER_AVATAR) {
            path = USER_AVATAR_PATH;
        }
        if (fileType == FileTypeEnum.SCHOOL_AVATAR) {
            path = SCHOOL_AVATAR_PATH;
        }
        if (fileType == FileTypeEnum.REGION_AVATAR) {
            path = REGION_IMAGE_PATH;
            randomName = false;
        }
        String avatar = null;
        if (!(avatarFile == null || avatarFile.isEmpty())) {
            try {
                avatar = ImageUtil.saveMultipleImages(new MultipartFile[]{avatarFile}, path, randomName, 1).get(0);
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
