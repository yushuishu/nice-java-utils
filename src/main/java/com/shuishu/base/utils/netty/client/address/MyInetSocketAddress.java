package com.shuishu.base.utils.netty.client.address;


import java.net.InetSocketAddress;
import java.util.Objects;

/**
 * @Author ：谁书-ss
 * @Date ：2024/10/30 11:11
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <br>
 * @Description ：连接的服务端地址信息
 * <br>
 * 参考：
 */
public class MyInetSocketAddress {
    private final InetSocketAddress inetSocketAddress;
    /**
     * 编码：UTF-8、GBK
     */
    private final String charsetName;
    /**
     * 是否长连接，true：长连接  false：短连接
     */
    private final Boolean longConnect;


    /**
     * 有参构造
     *
     * @param hostname    主机
     * @param port        端口
     * @param charsetName 编码
     * @param longConnect 是否长连接
     */
    public MyInetSocketAddress(String hostname, int port, String charsetName, Boolean longConnect) {
        this.inetSocketAddress = new InetSocketAddress(hostname, port);
        if (!"UTF-8".equals(charsetName) && !"GBK".equals(charsetName)) {
            throw new RuntimeException("不支持的编码：" + charsetName);
        }
        this.charsetName = charsetName;
        this.longConnect = longConnect != null && longConnect;
    }

    public InetSocketAddress getInetSocketAddress() {
        return inetSocketAddress;
    }

    public String getCharsetName() {
        return charsetName;
    }

    public Boolean getLongConnect() {
        return longConnect;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MyInetSocketAddress that = (MyInetSocketAddress) o;
        return inetSocketAddress.equals(that.inetSocketAddress) && charsetName.equals(that.charsetName) && longConnect.equals(that.longConnect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inetSocketAddress, charsetName, longConnect);
    }

}
