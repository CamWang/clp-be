package com.starlink.clp.exception;

import com.starlink.clp.constant.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author CamWang
 * @since 2020/7/28 19:54
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClpException extends RuntimeException{

    private ExceptionEnum exceptionEnum;

}
