package com.shuishu.base.utils.serialize;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.filter.SimplePropertyPreFilter;

import java.util.Arrays;

/**
 * @Author ：谁书-ss
 * @Date ：2024/6/18 9:21
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <br>
 * @Description ：FastJson 封装
 * <br>
 * 参考：
 */
public class NiceFastJson {
    /**
     * 忽略指定字段名，装换为json字符串
     *
     * @param obj               被序列化的对象
     * @param propertyNameArray 忽略序列化的字段名（比如 MultipartFile 文件对象，不能转换为json，就需要忽略）
     * @return -
     */
    public static String toJSONString(Object obj, String... propertyNameArray) {
        if (propertyNameArray.length > 0) {
            SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
            filter.getExcludes().addAll(Arrays.asList(propertyNameArray));
            return JSON.toJSONString(obj, filter);
        } else {
            return JSON.toJSONString(obj);
        }
    }

}
