package com.shuishu.base.utils.date;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author ：谁书-ss
 * @email  ：<p>Gmail：<a href="k1994583917@qq.com">Gmail Email</a></p>
 *           <p>QQ：<a href="1994583917@gmail.com">QQ Email</a></p>
 * @home   ：<p>Blog：<a href="http://longlonglong.top">Blog</a></p>
 *           <p>哔哩哔哩：<a href="https://space.bilibili.com/481342296">哔哩哔哩</a></p>
 *           <p>GitHub：<a href="https://github.com/yushuishu">GitHub</a></p>
 * @date   ：2024/9/26 16:49
 * @since  ：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
public class NiceDate {

    /**
     * 几个半小时，可选参数
     *
     * @param date    - yyyy-MM-dd HH:mm:ss
     * @param strTime - HH:mm
     * @return -
     */
    public static Integer getHalfHourNumber(Date date, String strTime) {
        if (date != null) {
            // 将任意时间转换为Calendar类型
            Calendar cal = DateUtil.calendar(date);
            // 获取该时间的小时和分钟
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);
            // 计算该时间所在的半小时刻度
            return hour * 2 + (minute >= 30 ? 1 : 0) + 1;
        }
        if (StringUtils.hasText(strTime) && StrUtil.count(strTime, ":") == 1 && strTime.length() == 5) {
            DateTime tempDate = DateUtil.parse(DateUtil.format(new Date(), DatePattern.NORM_DATE_PATTERN) + " " + strTime + ":00", DatePattern.NORM_DATETIME_PATTERN);
            // 将任意时间转换为Calendar类型
            Calendar cal = DateUtil.calendar(tempDate);
            // 获取该时间的小时和分钟
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);
            // 计算该时间所在的半小时刻度
            return hour * 2 + (minute >= 30 ? 1 : 0) + 1;
        }
        return null;
    }



}
