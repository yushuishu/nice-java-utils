package com.shuishu.base.utils.log.record;


import com.shuishu.base.utils.log.record.domain.LogRecordContext;
import com.shuishu.base.utils.log.record.entity.po.LogRecord;
import com.shuishu.base.utils.log.record.function.ActionWithContext;
import com.shuishu.base.utils.log.record.function.ExceptionHandlerWithContext;
import com.shuishu.base.utils.log.record.function.SupplierWithContext;

import java.util.Date;
import java.util.function.Consumer;

/**
 * @author ：谁书-ss
 * @email ：<p>Gmail：<a href="k1994583917@qq.com">Gmail Email</a></p>
 * <p>QQ：<a href="1994583917@gmail.com">QQ Email</a></p>
 * @home ：<p>Blog：<a href="http://longlonglong.top">Blog</a></p>
 * <p>哔哩哔哩：<a href="https://space.bilibili.com/481342296">哔哩哔哩</a></p>
 * <p>GitHub：<a href="https://github.com/yushuishu">GitHub</a></p>
 * @date ：2024/9/13 9:05
 * @ide ：IntelliJ IDEA
 * @motto ：ABC(Always Be Coding)
 * <br>
 * @description ：
 * <br>
 * @since ：1.0.0
 */
public class LogRecordTemplate {

    /**
     * 返回值的模板方法
     *
     * @param action           -
     * @param exceptionHandler -
     * @param finalizer        -
     * @param <T>              -
     * @return -
     * <br>
     * @author ：谁书-ss
     * @date ：2024/9/13 9:25
     * @since ：1.0.0
     */
    public static <T> T execute(SupplierWithContext<T> action, ExceptionHandlerWithContext exceptionHandler, Consumer<LogRecord> finalizer) {
        LogRecord logRecord = new LogRecord();
        logRecord.setApiProcessStatus("SUCCESS");

        LogRecordContext<T> context = new LogRecordContext<>();
        try {
            context = action.get();
        } catch (Exception e) {
            logRecord.setApiProcessStatus("FAIL");
            exceptionHandler.accept(e, context);
        } finally {
            logRecord.setApiResponseTime(new Date());
            finalizer.accept(logRecord);
        }
        return context.getResult();
    }


    /**
     * 没有返回值的模板方法
     *
     * @param action           -
     * @param exceptionHandler -
     * @param finalizer        -
     *                         <br>
     * @author ：谁书-ss
     * @date ：2024/9/13 9:28
     * @since ：1.0.0
     */
    public static void execute(ActionWithContext action, ExceptionHandlerWithContext exceptionHandler, Consumer<LogRecord> finalizer) {
        LogRecord logRecord = new LogRecord();
        logRecord.setApiProcessStatus("SUCCESS");

        LogRecordContext<Void> context = new LogRecordContext<>();
        try {
            action.execute(context);
        } catch (Exception e) {
            logRecord.setApiProcessStatus("FAIL");
            exceptionHandler.accept(e, context);
        } finally {
            logRecord.setApiResponseTime(new Date());
            finalizer.accept(logRecord);
        }
    }

}
