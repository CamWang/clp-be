package com.starlink.clp.exception;

import com.starlink.clp.constant.ExceptionEnum;
import org.springframework.beans.FatalBeanException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.List;

/**
 * @author CamWang
 * @since 2020/7/28 20:15
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClpException.class)
    public ResponseEntity<ExceptionResult> handleException(ClpException clpException) {
        return ResponseEntity.status(clpException.getExceptionEnum().getCode())
                .body(new ExceptionResult(clpException.getExceptionEnum()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ExceptionResult> parameterMissingExceptionHandler(MissingServletRequestParameterException e) {
        return ResponseEntity.status(ExceptionEnum.PARAMETER_ERROR.getCode())
                .body(new ExceptionResult(
                        ExceptionEnum.PARAMETER_ERROR.getCode(),
                        "请求参数 " + e.getParameterName() + "不能为空"));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResult> parameterBodyMissingExceptionHandler(HttpMessageNotReadableException e) {
        return ResponseEntity.status(ExceptionEnum.PARAMETER_ERROR.getCode())
                .body(new ExceptionResult(ExceptionEnum.PARAMETER_ERROR.getCode(), "参数体格式错误"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResult> parameterExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult exceptions = e.getBindingResult();
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                FieldError fieldError = (FieldError) errors.get(0);
                return ResponseEntity.status(ExceptionEnum.PARAMETER_ERROR.getCode())
                        .body(new ExceptionResult(
                                ExceptionEnum.PARAMETER_ERROR.getCode(),
                                fieldError.getDefaultMessage()
                        ));
            }
        }
        return ResponseEntity.status(ExceptionEnum.PARAMETER_ERROR.getCode())
                .body(new ExceptionResult(ExceptionEnum.PARAMETER_ERROR.getCode(),
                        ExceptionEnum.PARAMETER_ERROR.getMessage()));
    }

    /**
     * Bean验证错误
     * 针对org.hibernate.validator.constraints
     */
    @ExceptionHandler(FatalBeanException.class)
    public ResponseEntity<ExceptionResult> fatalBeanExceptionHandler(FatalBeanException e) {
        return ResponseEntity.status(ExceptionEnum.PARAMETER_ERROR.getCode())
                .body(new ExceptionResult(ExceptionEnum.PARAMETER_ERROR.getCode(), e.getMessage()));
    }

    /**
     * Controller参数上的验证错误处理器
     * 针对javax.validation.constraints的错误
     * 这里由于返回message带有方法和变量的详细信息所以去掉再返回
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionResult> validationExceptionHandler(ValidationException e) {
        String st = e.getMessage();
        int x = st.indexOf(':');
        x++;
        String s = st.substring(x).trim();
        return ResponseEntity.status(ExceptionEnum.PARAMETER_ERROR.getCode())
                .body(new ExceptionResult(ExceptionEnum.PARAMETER_ERROR.getCode(), s));
    }

    /**
     * 参数请求类型错误
     * 比如@RequestBody应该用JSON格式请求但是使用x-www-form-urlencoded格式请求了
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ExceptionResult> httpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException e) {
        return ResponseEntity.status(ExceptionEnum.PARAMETER_TYPE_NOT_SUPPORTED.getCode())
                .body(new ExceptionResult(ExceptionEnum.PARAMETER_TYPE_NOT_SUPPORTED.getCode(), ExceptionEnum.PARAMETER_TYPE_NOT_SUPPORTED.getMessage()));
    }

}
