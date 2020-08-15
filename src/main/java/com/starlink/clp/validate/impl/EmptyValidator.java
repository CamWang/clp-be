package com.starlink.clp.validate.impl;

import com.starlink.clp.validate.Empty;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

/**
 * 针对Jackson2HttpMessageConverter转换结果校验String或Array是否为空
 *
 * @author CamWang
 * @since 2020/8/15 10:45
 */

@Service
public class EmptyValidator implements ConstraintValidator<Empty, Object> {


    @Override
    public void initialize(Empty constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (value.getClass().getName().equals("java.lang.String")) {
            String tempString = (String)value;
            return tempString.length() == 0;
        }
        if (value instanceof List<?>) {
            List<Object> result = new ArrayList<>((List<?>) value);
            return result.isEmpty();
        }
        return false;
    }
}
