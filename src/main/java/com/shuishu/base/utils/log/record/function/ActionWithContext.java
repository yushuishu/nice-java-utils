package com.shuishu.base.utils.log.record.function;


import com.shuishu.base.utils.log.record.domain.LogRecordContext;

/**
 * @author ：谁书-ss
 * @email  ：<p>Gmail：<a href="k1994583917@qq.com">Gmail Email</a></p>
 *           <p>QQ：<a href="1994583917@gmail.com">QQ Email</a></p>
 * @home   ：<p>Blog：<a href="http://longlonglong.top">Blog</a></p>
 *           <p>哔哩哔哩：<a href="https://space.bilibili.com/481342296">哔哩哔哩</a></p>
 *           <p>GitHub：<a href="https://github.com/yushuishu">GitHub</a></p>
 * @date   ：2024/9/13 9:04
 * @since  ：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <br>
 * @description ：
 * <br>
 */
@FunctionalInterface
public interface ActionWithContext {
    void execute(LogRecordContext<?> context) throws Exception;
}
