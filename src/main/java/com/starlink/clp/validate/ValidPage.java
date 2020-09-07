package com.starlink.clp.validate;

import com.starlink.clp.validate.impl.EmptyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.lang.annotation.*;
import java.text.CollationKey;

import com.starlink.clp.validate.ValidPage.List;
import com.starlink.clp.validate.impl.PageValidator;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 用于判断Pageable参数是否合法的自定义Bean Validation注解
 * 页面元素数量必须在0-100之间
 * 偏移量和页面页码必须在0-1万之间
 *
 * The annotated Pageable element must be safe to use.
 * Page size must between 0-100 to avoid performance loss.
 * Offset and PageNumber must between 0-10,000
 *
 * @author CamWang
 * @since 2020/8/15
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(List.class)
@Constraint(validatedBy = PageValidator.class)
public @interface ValidPage {
    String message() default "Page parameter error.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    @Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        ValidPage[] value();
    }
}
