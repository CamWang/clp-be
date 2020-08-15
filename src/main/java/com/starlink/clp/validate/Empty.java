package com.starlink.clp.validate;

import com.starlink.clp.validate.impl.EmptyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.lang.annotation.*;

import com.starlink.clp.validate.Empty.List;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 用于判断String或Array类型元素是否为空的自定义Bean Validation注解
 * 由于Jackson2HttpMessageConverter在接受JSON转换为Bean的过程中会将空的数组属性设置为"[[]]"
 * 导致使用@Null无法正确判空，所以使用该注解，其他属性正常使用@Null就可以
 * 实现类是EmptyValidator.java
 *
 * The annotated String or Array element must be null or have empty body.
 * Accept only String or Array type.
 *
 * @author CamWang
 * @since 2020/8/15
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD})
@SupportedValidationTarget(ValidationTarget.ANNOTATED_ELEMENT)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(List.class)
@Constraint(validatedBy = EmptyValidator.class)
public @interface Empty {

    String message() default "Not empty";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    @Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Empty[] value();
    }
}
