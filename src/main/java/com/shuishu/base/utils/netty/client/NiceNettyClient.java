package com.shuishu.base.utils.netty.client;


import com.shuishu.base.utils.netty.client.address.MyInetSocketAddress;
import com.shuishu.base.utils.netty.client.pool.MyNettyChannelPoolMap;
import io.netty.channel.Channel;
import io.netty.channel.pool.FixedChannelPool;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Author ：谁书-ss
 * @Date ：2024/6/18 13:53
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <br>
 * @Description ：netty 客户端
 * <br>
 * 参考：
 */
@Slf4j
public class NiceNettyClient {

    private static final int ACQUIRE_TIMEOUT = 10; // 秒


    public static FixedChannelPool getConnect(String host, int port, String charsetName, Boolean longConnect) {
        MyNettyChannelPoolMap myNettyChannelPoolMap = MyNettyChannelPoolMap.getInstance();
        return myNettyChannelPoolMap.get(new MyInetSocketAddress(host, port, charsetName, longConnect));
    }

    public static <T> Object requestShort(String host, int port, String charsetName, Boolean longConnect, T message) throws ExecutionException, InterruptedException, TimeoutException {
        MyNettyChannelPoolMap myNettyChannelPoolMap = MyNettyChannelPoolMap.getInstance();
        FixedChannelPool fixedChannelPool = getConnect(host, port,charsetName, longConnect);
        Channel channel = fixedChannelPool.acquire().get(10, TimeUnit.SECONDS);
        myNettyChannelPoolMap.send(channel, message);
        return getResponseAndClose(channel);
    }

    public static <T> Object requestShort(Channel channel, Boolean release, T message) {
        if (channel == null) {
            return null;
        }
        MyNettyChannelPoolMap myNettyChannelPoolMap = MyNettyChannelPoolMap.getInstance();
        myNettyChannelPoolMap.send(channel, message);
        if (release) {
            return getResponseAndClose(channel);
        } else {
            return getResponseAndNotClose(channel);
        }
    }

    public static <T> Object requestLong(String host, int port, String charsetName, Boolean longConnect, T message) throws ExecutionException, InterruptedException, TimeoutException {
        MyNettyChannelPoolMap myNettyChannelPoolMap = MyNettyChannelPoolMap.getInstance();
        FixedChannelPool fixedChannelPool = getConnect(host, port, charsetName, longConnect);
        Channel channel = fixedChannelPool.acquire().get(10, TimeUnit.SECONDS);
        myNettyChannelPoolMap.send(channel, message);
        return getResponseAndRelease(fixedChannelPool, channel);
    }

    public static <T> Object requestLong(FixedChannelPool fixedChannelPool, Channel channel, Boolean release, T message) {
        if (fixedChannelPool == null || channel == null) {
            return null;
        }
        MyNettyChannelPoolMap myNettyChannelPoolMap = MyNettyChannelPoolMap.getInstance();
        myNettyChannelPoolMap.send(channel, message);
        if (release) {
            return getResponseAndRelease(fixedChannelPool, channel);
        } else {
            return getResponseAndNotRelease(channel);
        }
    }

    private static Object getResponseAndClose(Channel channel) {
        MyNettyChannelPoolMap myNettyChannelPoolMap = MyNettyChannelPoolMap.getInstance();
        String channelId = channel.id().toString();
        Object response = null;
        try {
            response = myNettyChannelPoolMap.receive(channelId);
        } catch (InterruptedException e) {
            log.error("------》 socket通道：" + channelId + "，获取响应数据异常", e);
        } finally {
            if (channel.isActive()) {
                // 短连接，通道使用完就关闭，清楚健康检测记录次数、数据临时存储、等待响应数据
                myNettyChannelPoolMap.deleteHealthStateCheckCountMap(channelId);
                myNettyChannelPoolMap.deleteResponseMsgMap(channelId);
                myNettyChannelPoolMap.deleteCountDownLatchMap(channelId);
                log.info("------》 关闭连接通道：{}", channelId);
                channel.close();
            }
        }
        return response;
    }

    private static Object getResponseAndNotClose(Channel channel) {
        MyNettyChannelPoolMap myNettyChannelPoolMap = MyNettyChannelPoolMap.getInstance();
        String channelId = channel.id().toString();
        Object response = null;
        try {
            response = myNettyChannelPoolMap.receive(channelId);
        } catch (InterruptedException e) {
            log.error("------》 socket通道：" + channelId + "，获取响应数据异常", e);
        }
        return response;
    }

    public static void close(Channel channel) {
        if (channel != null && channel.isActive()) {
            MyNettyChannelPoolMap myNettyChannelPoolMap = MyNettyChannelPoolMap.getInstance();
            String channelId = channel.id().toString();
            // 短连接，通道使用完就关闭，清楚健康检测记录次数、数据临时存储、等待响应数据
            myNettyChannelPoolMap.deleteHealthStateCheckCountMap(channelId);
            myNettyChannelPoolMap.deleteResponseMsgMap(channelId);
            myNettyChannelPoolMap.deleteCountDownLatchMap(channelId);
            log.info("------》 关闭连接通道：{}", channelId);
            channel.close();
        }
    }


    private static Object getResponseAndRelease(FixedChannelPool fixedChannelPool, Channel channel) {
        MyNettyChannelPoolMap myNettyChannelPoolMap = MyNettyChannelPoolMap.getInstance();
        String channelId = channel.id().toString();
        Object response = null;
        try {
            response = myNettyChannelPoolMap.receive(channelId);
        } catch (InterruptedException e) {
            log.error("------》 socket通道：" + channelId + "，获取响应数据异常", e);
        } finally {
            if (fixedChannelPool != null) {
                log.info("------》 归还连接通道：{}", channelId);
                fixedChannelPool.release(channel);
            }
        }
        return response;
    }

    private static Object getResponseAndNotRelease(Channel channel) {
        MyNettyChannelPoolMap myNettyChannelPoolMap = MyNettyChannelPoolMap.getInstance();
        String channelId = channel.id().toString();
        Object response = null;
        try {
            response = myNettyChannelPoolMap.receive(channelId);
        } catch (InterruptedException e) {
            log.error("------》 socket通道：" + channelId + "，获取响应数据异常", e);
        }
        return response;
    }

    public static void release(FixedChannelPool fixedChannelPool, Channel channel) {
        if (fixedChannelPool != null && channel != null) {
            log.info("------》 归还连接通道：{}", channel.id().toString());
            fixedChannelPool.release(channel);
        }
    }

}
