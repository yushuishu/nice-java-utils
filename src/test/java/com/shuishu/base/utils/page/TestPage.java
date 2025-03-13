package com.shuishu.base.utils.page;


/**
 * @author wuZhenFeng
 * @date 2025/1/15 17:53
 * @version ：1.0.0
 * @since ：1.0.0
 */


import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.system.SystemUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：谁书-ss
 * @email  ：<p>Gmail：<a href="k1994583917@qq.com">Gmail Email</a></p>
 *           <p>QQ：<a href="1994583917@gmail.com">QQ Email</a></p>
 * @home   ：<p>Blog：<a href="http://longlonglong.top">Blog</a></p>
 *           <p>哔哩哔哩：<a href="https://space.bilibili.com/481342296">哔哩哔哩</a></p>
 *           <p>GitHub：<a href="https://github.com/yushuishu">GitHub</a></p>
 * @date   ：2025/1/15 17:53
 * @since  ：1.0.0
 * @ide    ：IntelliJ IDEA
 * @motto  ：ABC(Always Be Coding)
 * <p></p>
 * @description ：
 * <p></p>
 */
public class TestPage {

    @Test
    public void test_01() {
        PageDto pageDto = new PageDto(1, 10);
        PageVo<User> pageVo = pageDto.toPageVo(User.class);
        System.out.println(pageVo);
    }

    @Test
    public void test_02() {
        List<User> list = new ArrayList<>();
        list.add(new User(1L, "李白"));
        list.add(new User(2L, "韩信"));
        list.add(new User(3L, "兰陵王"));

        PageDto pageDto = new PageDto(1, 3);
        PageVo<User> pageVo = pageDto.toPageVo(User.class);
        pageVo.update(list, 10);
        System.out.println(pageVo);

        System.out.println(SystemUtil.getOsInfo().getName());
    }

    @Test
    public void test_03() {
        HttpResponse httpResponse = HttpUtil.createGet("https://pic1cache.yuschool.cn/omd/880e3cc22494e5ffaaf6388a6f161296").execute();
        System.out.println(httpResponse.header("Content-Type"));

    }

}
