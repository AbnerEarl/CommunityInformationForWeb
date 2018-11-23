package com.zhang.shequ.utils.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

public class ReflectionUtil {

    public static List<Field> getDeclaredFields(Object object) {
        if (Objects.equal(object, null)) {
            return null;
        }
        List<Field> fieldList = Lists.newArrayList();
        Class<?> clazz = object.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Field[] fields = clazz.getDeclaredFields();
                if (fields != null && fields.length > 0) {
                    CollectionUtils.reverseArray(fields);
                    fieldList.addAll(Lists.newArrayList(fields));
                }
            } catch (Exception e) {
            }
        }
        Collections.reverse(fieldList);
        return fieldList;
    }

    public static Field getDeclaredField(Object object, String fieldName) {
        Field field = null;
        Class<?> clazz = object.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static void setFieldValue(Object object, String fieldName, Object value) {
        Field field = getDeclaredField(object, fieldName);
        field.setAccessible(true);
        try {
            field.set(object, value);
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
    }

    public static void setFieldValue(Object object, Field field, Object value) {
        field.setAccessible(true);
        try {
            field.set(object, value);
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
    }

    public static Object getFieldValue(Object object, String fieldName) {
        Field field = getDeclaredField(object, fieldName);
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredMethod
     * 
     * @param object
     *            : 子类对象
     * @param methodName
     *            : 父类中的方法名
     * @param parameterTypes
     *            : 父类中的方法参数类型
     * @return 父类中的方法对象
     */

    public static Method getDeclaredMethod(Object object, String methodName, Class<?>... parameterTypes) {
        Method method = null;
        for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                method = clazz.getDeclaredMethod(methodName, parameterTypes);
                return method;
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     * 直接调用对象方法, 而忽略修饰符(private, protected, default)
     * 
     * @param object
     *            : 子类对象
     * @param methodName
     *            : 父类中的方法名
     * @param parameterTypes
     *            : 父类中的方法参数类型
     * @param parameters
     *            : 父类中的方法参数
     * @return 父类中方法的执行结果
     */
    public static Object invokeMethod(Object object, String methodName, Class<?>[] parameterTypes, Object[] parameters) {
        // 根据 对象、方法名和对应的方法参数 通过反射 调用上面的方法获取 Method 对象
        Method method = getDeclaredMethod(object, methodName, parameterTypes);
        method.setAccessible(true);
        try {
            if (null != method) {
                return method.invoke(object, parameters);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

}