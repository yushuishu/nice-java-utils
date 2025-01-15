package com.shuishu.base.utils.date;


import org.junit.jupiter.api.Test;

/**
 * @author ：谁书-ss
 * @email  ：<p>Gmail：<a href="k1994583917@qq.com">Gmail Email</a></p>
 *           <p>QQ：<a href="1994583917@gmail.com">QQ Email</a></p>
 * @home   ：<p>Blog：<a href="http://longlonglong.top">Blog</a></p>
 *           <p>哔哩哔哩：<a href="https://space.bilibili.com/481342296">哔哩哔哩</a></p>
 *           <p>GitHub：<a href="https://github.com/yushuishu">GitHub</a></p>
 * @date   ：2024/9/26 16:50
 * @since  ：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
public class NiceDateTest {

    @Test
    public void getHalfHourNumber() {

        Integer halfHourNumber1= NiceDate.getHalfHourNumber(null, "21:30");
        Integer halfHourNumber2= NiceDate.getHalfHourNumber(null, "21:50");
        Integer halfHourNumber3= NiceDate.getHalfHourNumber(null, "22:00");
        Integer halfHourNumber4= NiceDate.getHalfHourNumber(null, "00:20");
        System.out.println(String.format("halfHourNumber1: %s  halfHourNumber2: %s  halfHourNumber3: %s  halfHourNumber4: %s", halfHourNumber1, halfHourNumber2, halfHourNumber3, halfHourNumber4));

    }

}
