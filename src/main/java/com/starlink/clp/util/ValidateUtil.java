package com.starlink.clp.util;

import com.starlink.clp.constant.ExceptionEnum;
import com.starlink.clp.exception.ClpException;
import org.springframework.data.domain.Pageable;

/**
 * 验证工具类
 *
 * @author CamWang
 * @since 2020/8/13 9:51
 */

public class ValidateUtil {

    /**
     * 验证pageable请求是否过大导致性能下降
     *
     * @param pageable 需要被验证的PageRequest对象
     */
    public static void pageValidate(Pageable pageable) {
        if (pageable.isUnpaged()) {
            return;
        }
        if (pageable.getPageSize() > 50 || pageable.getPageSize() < 0) {
            throw new ClpException(ExceptionEnum.PAGE_OUT_BOUND);
        }
    }
}
