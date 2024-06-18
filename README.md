# java工具类

<p>
  <a href="https://www.oracle.com/java/technologies/javase/17u-relnotes.html"><img src="https://img.shields.io/badge/jdk-%3E=17.0.0-blue.svg" alt="jdk compatility"></a>
  <a href="https://spring.io/projects/spring-boot"><img src="https://img.shields.io/badge/springboot-%3E=3.0.0-green.svg" alt="springboot compatility"></a>
</p>


## 介绍

项目基于 [springboot@3.3.0](https://spring.io/projects/spring-boot) 系列开发，开发环境使用[jdk@17.x](https://www.oracle.com/java/technologies/downloads/#java17)，[PostgreSQL-Server@14.5](https://www.postgresql.org/)。

站在巨人的肩膀上，基于少量的主流第三方包，封装工作中常见的工具类。

- 封装的工具，全部基于工作中真实遇到的功能需求，进行的整理、总结、编写。不会添加额外的操作方法。否则会导致很多方法，臃肿、甚至几乎用不到。
- 使用方法不通过导包的方式，而是通过 copy 代码的方式，将 class 类添加到工程项目的包路径下面，做少量的修改，进行使用。
- 提供使用方法示例
- 提供每个方法的描述、关键字，以便能够快速的找到想要的工具类、方法（idea全局搜索）

## 预览

## 项目结构

```text
project
└─src                     
    ├─main                
    │  ├─java             
    │  │  └─com           
    │  │      └─shuishu   
    │  │          └─utils 
    │  │              └─tool
    │  │                  ├─cache
    │  │                  │  └─redis
    │  │                  ├─entity
    │  │                  ├─file
    │  │                  │  ├─archive
    │  │                  │  ├─excel
    │  │                  │  ├─image
    │  │                  │  ├─ocr
    │  │                  │  └─video
    │  │                  ├─log
    │  │                  ├─math
    │  │                  ├─netty
    │  │                  │  ├─client
    │  │                  │  └─server
    │  │                  ├─reflect
    │  │                  ├─serialize
    │  │                  ├─statistics
    │  │                  ├─string
    │  │                  ├─thread
    │  │                  │  ├─factory
    │  │                  │  └─lock
    │  │                  │      └─aspect
    │  │                  ├─validation
    │  │                  └─xml
    │  └─resources
    └─test
        └─java
            └─com
                └─shuishu
                    └─utils
```

## 核心功能

| 功能 |            描述             |
|:--:|:-------------------------:|
| 日志 | 自定义日志输出工具，定时刷新持久化，减少cpu压力 |


## 使用

直接复制class文件，添加到工程中

## 引用

https://github.com/alibaba/fastjson2

https://www.hutool.cn/

https://www.cnblogs.com/liangxianning/p/17058248.html

https://github.com/google/guava

https://wizardforcel.gitbooks.io/guava-tutorial/content/index.html



