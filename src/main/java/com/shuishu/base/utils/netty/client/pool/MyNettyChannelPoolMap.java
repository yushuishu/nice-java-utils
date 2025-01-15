package com.shuishu.base.utils.netty.client.pool;


import com.shuishu.base.utils.netty.client.address.MyInetSocketAddress;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.pool.AbstractChannelPoolMap;
import io.netty.channel.pool.ChannelPoolHandler;
import io.netty.channel.pool.FixedChannelPool;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author ：谁书-ss
 * @Date ：2024/10/30 11:17
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <br>
 * @Description ：连接的服务端地址信息
 * <br>
 * 参考：
 */
@Slf4j
public class MyNettyChannelPoolMap extends AbstractChannelPoolMap<MyInetSocketAddress, FixedChannelPool> {
    /**
     * 静态内部类，单例模式
     */
    private static class MyNettyChannelPoolMapHolder {
        private static final MyNettyChannelPoolMap INSTANCE = new MyNettyChannelPoolMap();
    }
    private MyNettyChannelPoolMap() {}
    public static MyNettyChannelPoolMap getInstance() {
        return MyNettyChannelPoolMapHolder.INSTANCE;
    }

    /**
     * 每个通道心跳检测 次数
     */
    public static final ConcurrentMap<String, AtomicInteger> HEALTH_STATE_CHECK_COUNT_MAP = new ConcurrentHashMap<>();
    public void setHealthStateCheckCountMap(String key) {
        HEALTH_STATE_CHECK_COUNT_MAP.computeIfAbsent(key, k -> new AtomicInteger(0)).incrementAndGet();
    }
    public int getHealthStateCheckCountMap(String key) {
        return HEALTH_STATE_CHECK_COUNT_MAP.getOrDefault(key, new AtomicInteger(0)).get();
    }
    public void deleteHealthStateCheckCountMap(String key) {
        HEALTH_STATE_CHECK_COUNT_MAP.remove(key);
    }

    /**
     * 获取消息，等待次数
     */
    public static final ConcurrentMap<String, CountDownLatch> COUNT_DOWN_LATCH_MAP = new ConcurrentHashMap<>();
    public void setCountDownLatchMap(String key, CountDownLatch countDownLatch) {
        if (key != null && countDownLatch != null) {
            COUNT_DOWN_LATCH_MAP.put(key, countDownLatch);
        }
    }
    public void countDown(String key) {
        CountDownLatch countDownLatch = COUNT_DOWN_LATCH_MAP.getOrDefault(key, null);
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
    }
    public CountDownLatch getCountDownLatchMap(String key) {
        return COUNT_DOWN_LATCH_MAP.getOrDefault(key, new CountDownLatch(1));
    }
    public void deleteCountDownLatchMap(String key) {
        COUNT_DOWN_LATCH_MAP.remove(key);
    }

    /**
     * 记录每个请求响应的数据，通道id为key
     */
    public static final ConcurrentMap<String, String> RESPONSE_MSG_MAP = new ConcurrentHashMap<>();
    public void setResponseMsgMap(String key, String msg) {
        if (key != null && msg != null) {
            RESPONSE_MSG_MAP.put(key, msg);
        }
    }
    public String getResponseMsgMap(String key) {
        return RESPONSE_MSG_MAP.getOrDefault(key, null);
    }
    public void deleteResponseMsgMap(String key) {
        RESPONSE_MSG_MAP.remove(key);
    }

    /**
     * 连接池存放的 最大连接数
     */
    private static final int MAX_CONNECTIONS = 32;
    /**
     * 建立链接 超时毫秒数
     */
    private static final int CONNECT_TIMEOUT = 10000;
    /**
     * 等待接收数据 超时毫秒数
     */
    private static final int AWAIT_RECEIVE_TIMEOUT = 15000;
    /**
     * 读 超时毫秒数
     */
    private static final int READ_TIMEOUT = 30000;
    /**
     * 写 超时毫秒数
     */
    private static final int WRITE_TIMEOUT = 30000;
    /**
     * 读写 超时毫秒数
     */
    private static final int READ_WRITE_TIMEOUT = 120000;
    /**
     * 获取Channel的超时持续时间，以毫秒为单位。如果设置值小于等于0，表示无限等待，直到有Channel可以使用。
     */
    private static final int ACQUIRE_TIMEOUT = 10000;
    /**
     * 等待获取的最大数量，一旦超过这个值，尝试就会失败（max-connections 设置的连接数都被使用了，无空闲连接，也无法创建连接，这时候可以等待的请求数量）
     * 这里设置为，连接池中最大数量的一半
     */
    private static final int MAX_PENDING_ACQUIRES = 16;
    /**
     * 长连接 通道心跳健康检测总次数：(6小时 * 60分钟 * 60秒) / 30秒 = 21600 / 30 = 720
     * *2 是因为读、写，会同时检测到，同一时刻心跳次数会减少2次；每两分钟会有一次读写心跳
     * 720 * 2 大约心跳时间为 4.8小时
     */
    private static final int LONG_HEALTH_CHECK_NUMBER = 720 * 2;
    /**
     * 短链接，通道心跳健康检测总次数：(2分钟 * 60秒) / 20秒 = 120 / 20 = 6
     * *2 是因为读、写会同时检测到，同一时刻心跳次数会减少2次
     */
    private static final int SHORT_HEALTH_CHECK_NUMBER = 6 * 2;

    /**
     * 客户端引导类
     */
    private static final Bootstrap BOOTSTRAP = new Bootstrap();
    static {
        BOOTSTRAP.group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECT_TIMEOUT)
                .option(ChannelOption.AUTO_READ, true);
    }


    @Override
    protected FixedChannelPool newPool(MyInetSocketAddress myInetSocketAddress) {
        String charsetName = myInetSocketAddress.getCharsetName();
        /**
         * new FixedChannelPool() 参数
         *
         * @param bootstrap             客户端引导类
         * @param handler               通道过滤器
         * @param healthCheck           检查Channel是否可以被用于进一步的IO操作
         * @param action                {@link FixedChannelPool.AcquireTimeoutAction} 当无法在规定的时间（acquireTimeoutMillis参数设定）
         *                              内获取Channel时应当做出的动作。可能的值有New（尝试创建一个新的Channel）和 Fail（立即失败，触发Future的Listener）。
         * @param acquireTimeoutMillis  设置获取Channel的超时持续时间，以毫秒为单位。如果设置值小于等于0，表示无限等待，直到有Channel可以使用。
         * @param maxConnections        池中最大的Channel数量，应用尝试获取一个连接，但是当前池中的连接已经达到了设定的最大数量，那么应用就会阻塞，直到有连接可以被释放出来供它使用。
         * @param maxPendingAcquires    等待获取的最大数量。一旦超过这个值，尝试就会失败。
         * @param releaseHealthCheck    true: 返回之前检查通道运行状况
         */
        return new FixedChannelPool(BOOTSTRAP.remoteAddress(myInetSocketAddress.getInetSocketAddress()), new ChannelPoolHandler() {
            /**
             * 使用完channel，需要释放才能放入连接池
             *
             * @param ch -
             * @throws Exception -
             */
            @Override
            public void channelReleased(Channel ch) throws Exception {
                // 刷新管道里的数据
                ch.writeAndFlush(Unpooled.EMPTY_BUFFER);
            }
            /**
             * 获取连接池中的channel
             *
             * @param ch -
             * @throws Exception -
             */
            @Override
            public void channelAcquired(Channel ch) throws Exception {
                // 是否长连接
                AttributeKey<Boolean> longConnectAttributeKey = AttributeKey.valueOf("longConnect");
                Attribute<Boolean> longConnectAttribute = ch.attr(longConnectAttributeKey);
                // 是否已经认证
                AttributeKey<Boolean> authenticationAttributeKey = AttributeKey.valueOf("authentication");
                Attribute<Boolean> authenticationAttribute = ch.attr(authenticationAttributeKey);
                // 获取连接池通道channel
                log.info("------》 获取连接池中的通道channel：{}，是否长连接：{}，是否已经认证：{}", ch.id(), longConnectAttribute.get(), authenticationAttribute.get());
            }
            /**
             * 当链接创建的时候添加channelHandler，只有当channel不足时会创建，但不会超过限制的最大channel数
             *
             * @param ch -
             * @throws Exception -
             */
            @Override
            public void channelCreated(Channel ch) throws Exception {
                // 是否长连接
                AttributeKey<Boolean> longConnectAttributeKey = AttributeKey.valueOf("longConnect");
                Attribute<Boolean> longConnectAttribute = ch.attr(longConnectAttributeKey);
                Boolean longConnect = myInetSocketAddress.getLongConnect();
                longConnectAttribute.set(longConnect);
                // 是否已经认证
                AttributeKey<Boolean> authenticationAttributeKey = AttributeKey.valueOf("authentication");
                Attribute<Boolean> authenticationAttribute = ch.attr(authenticationAttributeKey);
                authenticationAttribute.set(false);

                log.info("------》 创建通道：{}，是否长连接：{}", ch.id(), longConnect);

                // 基于换行符号
                ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                // 解码转String，注意调整自己的编码格式GBK、UTF-8
                ch.pipeline().addLast(new StringDecoder(Charset.forName(charsetName)));
                // 解码转String，注意调整自己的编码格式GBK、UTF-8
                ch.pipeline().addLast(new StringEncoder(Charset.forName(charsetName)));
                // 读空闲超时、写空闲超时和读写空闲超时 心跳检测
                ch.pipeline().addLast(new IdleStateHandler(READ_TIMEOUT, WRITE_TIMEOUT, READ_WRITE_TIMEOUT, TimeUnit.MILLISECONDS));
                // 创建新的通道连接
                ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                    /**
                     * 响应数据
                     *
                     * @param ctx           the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler} belongs to
                     * @param msg           the message to handle
                     * @throws Exception -
                     */
                    @Override
                    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                        String channelId = ctx.channel().id().toString();
                        log.info("------》 socket通道：{}，接收消息：{}", channelId, msg);
                        try {
                            if (msg != null) {
                                setResponseMsgMap(channelId, msg);
                            }
                        } finally {
                            countDown(channelId);
                        }
                    }

                    /**
                     * 当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
                     *
                     * @param ctx -
                     * @param cause -
                     * @throws Exception -
                     */
                    @Override
                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                        ctx.close();
                        log.error("------》 socket通道：" + ctx.channel().id() + "异常", cause);
                    }
                    /**
                     * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
                     *
                     * @param ctx -
                     * @throws Exception -
                     */
                    @Override
                    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                        // 是否长连接
                        AttributeKey<Boolean> longConnectAttributeKey = AttributeKey.valueOf("longConnect");
                        Attribute<Boolean> longConnectAttribute = ch.attr(longConnectAttributeKey);
                        // 是否已经认证
                        AttributeKey<Boolean> authenticationAttributeKey = AttributeKey.valueOf("authentication");
                        Attribute<Boolean> authenticationAttribute = ch.attr(authenticationAttributeKey);
                        // 断开连接，不尝试重新连接
                        String channelId = ctx.channel().id().toString();
                        log.info("------》 socket通道：{}，，是否长连接：{}，是否已经认证：{}，断开连接：{}", channelId, longConnectAttribute.get(), authenticationAttribute.get(), ctx.channel().remoteAddress().toString());
                        // 存储数据和等待响应数据记录次数，执行一遍清楚操作，防止OOM
                        deleteHealthStateCheckCountMap(channelId);
                        deleteResponseMsgMap(channelId);
                        deleteCountDownLatchMap(channelId);
                    }

                    /**
                     * 通道连接 心跳过滤器
                     *
                     * @param ctx -
                     * @param evt -
                     * @throws Exception -
                     */
                    @Override
                    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                        // 获取通道是否 是长连接
                        AttributeKey<Boolean> longConnectAttr = AttributeKey.valueOf("longConnect");
                        Boolean longConnect = ctx.channel().attr(longConnectAttr).get();
                        // 获取通道心跳次数
                        String channelId = ctx.channel().id().toString();
                        int count = getHealthStateCheckCountMap(channelId) + 1;
                        // 判断是否关闭通道
                        int checkNumber;
                        boolean del;
                        if (Boolean.TRUE.equals(longConnect)) {
                            checkNumber = LONG_HEALTH_CHECK_NUMBER;
                            del = count > LONG_HEALTH_CHECK_NUMBER;
                        } else {
                            checkNumber = SHORT_HEALTH_CHECK_NUMBER;
                            del = count > SHORT_HEALTH_CHECK_NUMBER;
                        }

                        if (del) {
                            // 存储数据和等待响应数据记录次数，执行一遍清除操作，防止OOM
                            deleteHealthStateCheckCountMap(channelId);
                            deleteResponseMsgMap(channelId);
                            deleteCountDownLatchMap(channelId);
                            // 关闭通道
                            log.info("------》 socket通道：{}，是否长连接：{} 心跳次数：{}，超过最大心跳次数{}，通道即将关闭", channelId, longConnect, count, checkNumber);
                            ctx.close();
                        } else {
                            setHealthStateCheckCountMap(channelId);
                            if (evt instanceof IdleStateEvent) {
                                /*
                                心跳将发送的数据操作 send() 注释掉原因：1、在给有些服务端发送数据时异常，导致客户端断开连接，2、发送影响性能
                                 */
                                IdleStateEvent e = (IdleStateEvent) evt;
                                if (e.state() == IdleState.READER_IDLE) {
                                    log.info("------》 socket通道：{}, 是否长连接：{} 心跳次数：{}，读空闲时间{}毫秒，开始心跳检测...", channelId, longConnect, count, READ_TIMEOUT);
                                    //send(ctx.channel(), "No data was received for a while.\r\n");
                                }else if (e.state() == IdleState.WRITER_IDLE) {
                                    log.info("------》 socket通道：{}, 是否长连接：{} 心跳次数：{}，写空闲时间{}毫秒，开始心跳检测...", channelId, longConnect, count, WRITE_TIMEOUT);
                                    //send(ctx.channel(), "No data was sent for a while.\r\n");
                                }else if (e.state() == IdleState.ALL_IDLE) {
                                    log.info("------》 socket通道：{}, 是否长连接：{} 心跳次数：{}，读写空闲时间{}毫秒，开始心跳检测...", channelId, longConnect, count, READ_WRITE_TIMEOUT);
                                    //send(ctx.channel(), "No data was either received or sent for a while.\r\n");
                                }
                            }
                        }
                    }
                });
            }
        }, channel -> {
            // 直接从Channel自身创建ChannelFuture。
            // channel.newSucceededFuture(isActive);
            // 从EventLoop的角度来创建一个ChannelFuture
            return channel.eventLoop().newSucceededFuture(channel.isActive());
        }, FixedChannelPool.AcquireTimeoutAction.FAIL, ACQUIRE_TIMEOUT, MAX_CONNECTIONS, MAX_PENDING_ACQUIRES, true);

    }

    /**
     * 发送消息
     *
     * @param channel -
     * @param message -
     * @param <T> -
     */
    public <T> void send(Channel channel, T message) {
        if (message != null && channel != null) {
            log.info("------》 socket通道：{}，发送消息：{}", channel.id(), message);
            setCountDownLatchMap(channel.id().toString(), new CountDownLatch(1));
            ChannelFuture channelFuture = channel.writeAndFlush(message).syncUninterruptibly();
            if (!channelFuture.isSuccess()) {
                log.error("------》 socket通道：" + channel.id()+ "，发送消息失败...", channelFuture.cause());
            }
        }
    }

    /**
     * 接收消息
     *
     * @param <T> -
     * @return -
     */
    public <T> Object receive(String channelId) throws InterruptedException {
        CountDownLatch countDownLatch = getCountDownLatchMap(channelId);
        boolean b = countDownLatch.await(AWAIT_RECEIVE_TIMEOUT, TimeUnit.MILLISECONDS);
        if (!b) {
            log.error("------》 socket通道：{}, 消息接收超时...", channelId);
            return null;
        }
        return getResponseMsgMap(channelId);
    }

}
