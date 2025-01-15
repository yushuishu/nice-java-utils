package com.shuishu.base.utils.entity;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
 * <br>
 * @Description ：对象实体操作
 * <br>
 * 参考：
 */
public class NiceEntity {

    private static final Logger logger = LoggerFactory.getLogger(NiceEntity.class);

    /**
     * 获取到对象中值为 null的字段名
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
            if (srcValue == null || !StringUtils.hasText(srcValue.toString())) {
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
            throw new RuntimeException(e.getMessage());
        }
        return map;
    }

    /**
     * Map convert Entity
     *
     * @param map 实体对象每个字段的值，key：字段名，value：字段值
     * @param entityClass 实体类
     * @param <T> -
     * @return -
     */
    public static <T> T convertMapToEntity(Map<String, Object> map, Class<T> entityClass) {
        try {
            T entity = entityClass.getDeclaredConstructor().newInstance();
            Set<Map.Entry<String, Object>> entries = map.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                String key = entry.getKey();
                Object value = entry.getValue();
                Field field = entityClass.getDeclaredField(key);
                field.setAccessible(true);
                field.set(entity, value);
            }
            return entity;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException e) {
            throw new RuntimeException(e.getMessage());
        }
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
