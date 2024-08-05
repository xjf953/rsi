package com.shawn.votesystem.utils;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.shawn.votesystem.exception.BaseException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * 实体工具
 *
 * @author fly.xiao
 * @date 2022/6/21 11:47
 * @since 1.0.0
 */
public class ModelUtil {

    /**
     * 实体属性拷贝
     * @author fly xiao
     * @date 2022-06-21 15:17
     * @param source
     * @param target
     * @return T
     */
    public static <T> T copyProperties(Object source, T target) {
        try {
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            // 错误编码未定
            throw new BaseException("bean copy error");
        }
    }

    /**
     * 自动忽略id复制
     * @author fly xiao
     * @date 2022-06-21 15:17
     * @param source
     * @param target
     * @return T
     */
    public static <T> T copyPropertiesIgnoreId(Object source, T target) {
        try {
            BeanUtils.copyProperties(source, target, new String[] { "id" });
            return target;
        } catch (Exception e) {
            // 错误编码未定
            throw new BaseException("bean copy ignoreId error");
        }
    }

    /**
     * 提供忽略属性复制
     * @author fly xiao
     * @date 2022-06-21 15:17
     * @param source
     * @param target
     * @param ignoreProperties
     * @return T
     */
    public static <T> T copyPropertiesWithIgnore(Object source, T target, String... ignoreProperties) {
        try {
            BeanUtils.copyProperties(source, target, ignoreProperties);
            return target;
        } catch (Exception e) {
            // 错误编码未定
            throw new BaseException("bean ignoreProperties error");
        }
    }

    /**
     * 拷贝列表，返回新的对象T ，例如model list转 VO list
     * @author fly xiao
     * @date 2022-06-21 15:17
     * @param list
     * @param classs
     * @return java.util.List<T>
     */
    @SuppressWarnings("rawtypes")
    public static <T> List<T> copyListProperties(Collection list, Class<T> classs) {
        try {
            List<T> targetList = new ArrayList<T>();
            for (Object source : list) {
                targetList.add(copyProperties(source, classs.getDeclaredConstructor().newInstance()));
            }
            return targetList;
        } catch (Exception e) {
            // 错误编码未定
            throw new BaseException("bean error");
        }
    }

    /**
     * 拷贝列表，返回新的对象T ，例如model list转 VO list
     * @author fly xiao
     * @date 2022-06-21 15:17
     * @param list
     * @param classs
     * @param ignoreProperties
     * @return java.util.List<T>
     */
    @SuppressWarnings("rawtypes")
    public static <T> List<T> copyListPropertiesWithIgnore(Collection list, Class<T> classs,
                                                           String... ignoreProperties) {
        try {
            List<T> targetList = new ArrayList<T>();
            for (Object source : list) {
                targetList.add(copyPropertiesWithIgnore(source, classs.getDeclaredConstructor().newInstance(), ignoreProperties));
            }
            return targetList;
        } catch (Exception e) {
            // 错误编码未定
            throw new BaseException("bean ignoreProperties error");
        }
    }

    /**
     * 获取为空属性
     * @author fly xiao
     * @date 2022-06-21 15:18
     * @param source
     * @param ignoreProperties
     * @return java.lang.String[]
     */
    public static String[] getNullPropertyNames(Object source,String... ignoreProperties) {
        BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        if (ignoreProperties!=null) {
            for (String ig : ignoreProperties) {
                emptyNames.add(ig);
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 拷贝对象属性，忽略null值
     * @author fly xiao
     * @date 2022-06-21 15:18
     * @param source
     * @param target
     * @return T
     */
    public static <T> T copyPropertiesIgnoreNull(Object source, T target) {
        return copyPropertiesWithIgnore(source, target, getNullPropertyNames(source));
    }

    /**
     * 拷贝对象属性，忽略null值,同时可以指定特殊属性忽略
     * @author fly xiao
     * @date 2022-06-21 15:18
     * @param source
     * @param target
     * @param ignoreProperties
     * @return T
     */
    public static <T> T copyPropertiesIgnoreNullWithProperties(Object source, T target,String... ignoreProperties) {
        return copyPropertiesWithIgnore(source, target, getNullPropertyNames(source,ignoreProperties));
    }
}
