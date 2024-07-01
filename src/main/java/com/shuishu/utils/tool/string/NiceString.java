package com.shuishu.utils.tool.string;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author ：谁书-ss
 * @Date ：2024/6/18 10:36
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：字符串操作
 * <p></p>
 * 参考：
 */
public class NiceString {

    /**
     * 指定分割符，解析字符串，返回字符串集合
     *
     * @param srcStr 要分割的字符串
     * @param s_delimiter 分隔符：cn.hutool.core.text.StrUtil
     * @return
     */
    public static List<String> parseStringByDelimiter(String srcStr, String s_delimiter) {
        if (s_delimiter != null && !s_delimiter.isEmpty()) {
            return Arrays.stream(srcStr.split(s_delimiter)).distinct().collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * 指定分割符，解析字符串，返回字符串集合
     *
     * @param srcStr 要分割的字符串
     * @param c_delimiter 分隔符：cn.hutool.core.text.StrUtil
     * @return -
     */
    public static List<String> parseStringByDelimiter(String srcStr, char c_delimiter) {
        return Arrays.stream(srcStr.split(String.valueOf(c_delimiter))).distinct().collect(Collectors.toList());
    }

}
