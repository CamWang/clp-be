package com.starlink.clp.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * 返回对象的null属性
 * 给BeanUtils.copyProperties用来复制元素
 *
 * @author CamWang
 * @since 2020/8/14 11:34
 */

public class IgnoreNullPropertiesUtil {
    public static String[] getNullPropertyNames(Object source) {
        BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null)
                emptyNames.add(pd.getName());
        }
        return emptyNames.toArray(new String[emptyNames.size()]);
    }
}

