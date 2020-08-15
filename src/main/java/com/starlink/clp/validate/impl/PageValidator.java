package com.starlink.clp.validate.impl;

import com.starlink.clp.validate.ValidPage;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 验证Pageable参数的合法性
 *
 * @author CamWang
 * @since 2020/8/15 17:44
 */

@Service
public class PageValidator implements ConstraintValidator<ValidPage, Pageable> {

    private static final int MAX_PAGE_SIZE = 100;

    @Override
    public void initialize(ValidPage constraintAnnotation) {

    }

    @Override
    public boolean isValid(Pageable value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        if (value.getPageSize() < 0 || value.getPageSize() > MAX_PAGE_SIZE) {
            return false;
        }
        if (value.getOffset() < 0 || value.getPageNumber() < 0 || value.getOffset() > 10000 || value.getPageNumber() > 10000) {
            return false;
        }
        return true;
    }
}
