package com.shuishu.utils.tool.entity;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Author ：谁书-ss
 * @Date ：2024/6/18 14:47
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：对象实体操作
 * <p></p>
 * 参考：
 */
public class NiceEntity {

    /**
     * 获取到对象中值为 null的属性名称
     *
     * @param source              源对象
     * @param extraFieldNameArray 额外的字段名
     * @return 值为空的字段名
     */
    public static String[] getNullPropertyNames(Object source, String... extraFieldNameArray) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (StringUtils.isEmpty(srcValue)) {
                emptyNames.add(pd.getName());
            }
        }
        if (!ObjectUtils.isEmpty(extraFieldNameArray)) {
            emptyNames.addAll(Arrays.asList(extraFieldNameArray));
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 拷贝对象值 到 新对象
     *
     * @param src                  原对象
     * @param target               目标对象
     * @param ignoreFieldNameArray 忽略的字段
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target, String... ignoreFieldNameArray) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src, ignoreFieldNameArray));
    }

    /**
     * object convert map
     *
     * @param obj 转换对象
     * @return 转换后的对象
     */
    public static Map<String, Object> convertObjectToMap(Object obj) {
        Class<?> cla = obj.getClass();
        Field[] fields = cla.getDeclaredFields();
        Map<String, Object> map = new HashMap<String, Object>(fields.length);
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                String key = field.getName();
                Object value = field.get(obj);
                if (value != null) {
                    map.put(key, value);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 比较公共的实体去重操作，实体对象无需重写 equals 和 hashcode 方法 就能实现去重操作
     * 使用调用方式：
     *      List<User> userList = ... // 存放有“重复”用户的集合
     *      userList = distinctEntity(userList, user -> Arrays.asList(user.getName(), user.getAge()));
     *
     * @param entityList 去重实体类型
     * @param keyExtractor 判断去重的字段条件
     * @param <T> -
     * @return -
     */
    public static <T> List<T>  distinctEntity(List<T> entityList, Function<? super T, ?> keyExtractor) {
        return entityList.stream()
                .filter(distinctByKey(keyExtractor))
                .collect(Collectors.toList());
    }
    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }



}
