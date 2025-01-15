package com.shuishu.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @Author ：谁书-ss
 * @Date   ： 2024/6/18 9:07
 * @IDE    ：IntelliJ IDEA
 * @Motto  ：ABC(Always Be Coding)
 * <br>
 * @Description ：站在巨人的肩膀上，基于少量的主流第三方包，封装工作中常见的工具类
 * 封装工具，引用的第三方工具库，都是主流的工具类库，不追求纯粹的基于jdk来做封装。
 *
 * 说明：1、基于 springboot 3.x，引用 spring-boot-starter-web 包，额外的第三方如下：
 * 			- hutool-all
 * 			- fastjson2
 * 			- easyexcel
 * 			- netty-all
 * 		2、部分封装工具涉及第三方包。如 json 操作，NiceFastJson 基于 fastjson2.x ；NiceJackson 基于 Jackson2.x
 * 		3、封装的工具，全部基于工作中真实遇到的功能需求，进行的整理、总结、编写。不会添加额外的操作方法。否则会导致很多方法，臃肿、甚至几乎用不到。
 * 		4、使用方法不通过导包的方式，而是通过 copy 代码的方式，将 class 类添加到工程项目的包路径下面，做少量的修改，进行使用。
 * 		5、提供使用方法示例
 * 		6、提供每个方法的描述、关键字，以便能够快速的找到想要的工具类、方法
 */
@SpringBootApplication
public class NiceJavaUtilsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NiceJavaUtilsApplication.class, args);
	}

}
