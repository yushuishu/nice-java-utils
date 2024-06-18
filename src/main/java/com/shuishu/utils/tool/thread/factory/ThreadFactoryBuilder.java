package com.shuishu.utils.tool.thread.factory;


import com.shuishu.utils.tool.validation.NicePreconditions;

import javax.annotation.CheckForNull;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author ：谁书-ss
 * @Date ：2024/6/18 10:17
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：自定义线程工厂
 * <p></p>
 */
public final class ThreadFactoryBuilder {
    @CheckForNull
    private String nameFormat = null;
    @CheckForNull
    private Boolean daemon = null;
    @CheckForNull
    private Integer priority = null;
    @CheckForNull
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler = null;
    @CheckForNull
    private ThreadFactory backingThreadFactory = null;

    public ThreadFactoryBuilder() {
    }

    public ThreadFactoryBuilder setNameFormat(String nameFormat) {
        format(nameFormat, 0);
        this.nameFormat = nameFormat;
        return this;
    }

    public ThreadFactoryBuilder setDaemon(boolean daemon) {
        this.daemon = daemon;
        return this;
    }

    public ThreadFactoryBuilder setPriority(int priority) {
        NicePreconditions.checkArgument(priority >= 1, "Thread priority (%s) must be >= %s", priority, 1);
        NicePreconditions.checkArgument(priority <= 10, "Thread priority (%s) must be <= %s", priority, 10);
        this.priority = priority;
        return this;
    }

    public ThreadFactoryBuilder setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.uncaughtExceptionHandler = (Thread.UncaughtExceptionHandler) NicePreconditions.checkNotNull(uncaughtExceptionHandler);
        return this;
    }

    public ThreadFactoryBuilder setThreadFactory(ThreadFactory backingThreadFactory) {
        this.backingThreadFactory = (ThreadFactory) NicePreconditions.checkNotNull(backingThreadFactory);
        return this;
    }

    public ThreadFactory build() {
        return doBuild(this);
    }

    private static ThreadFactory doBuild(ThreadFactoryBuilder builder) {
        final String nameFormat = builder.nameFormat;
        final Boolean daemon = builder.daemon;
        final Integer priority = builder.priority;
        final Thread.UncaughtExceptionHandler uncaughtExceptionHandler = builder.uncaughtExceptionHandler;
        final ThreadFactory backingThreadFactory = builder.backingThreadFactory != null ? builder.backingThreadFactory : Executors.defaultThreadFactory();
        final AtomicLong count = nameFormat != null ? new AtomicLong(0L) : null;
        return new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = backingThreadFactory.newThread(runnable);
                Objects.requireNonNull(thread);
                if (nameFormat != null) {
                    thread.setName(ThreadFactoryBuilder.format(nameFormat, ((AtomicLong)Objects.requireNonNull(count)).getAndIncrement()));
                }
                if (daemon != null) {
                    thread.setDaemon(daemon);
                }
                if (priority != null) {
                    thread.setPriority(priority);
                }
                if (uncaughtExceptionHandler != null) {
                    thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
                }
                return thread;
            }
        };

    }

    private static String format(String format, Object... args) {
        return String.format(Locale.ROOT, format, args);
    }

}
