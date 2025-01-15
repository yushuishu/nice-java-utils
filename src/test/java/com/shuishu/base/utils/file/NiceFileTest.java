package com.shuishu.base.utils.file;


import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author ：谁书-ss
 * @Date ：2024/7/2 15:30
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <br>
 * @Description ：
 * <br>
 */
public class NiceFileTest {

    @Test
    public void upload_test1() throws IOException {
        // 文件内容
        String content = "This is the content of the file.";
        InputStream inputStream = new ByteArrayInputStream(content.getBytes());

        // 创建 MockMultipartFile 对象
        MultipartFile multipartFile = new MockMultipartFile(
                "file",                // 文件名
                "example.txt",         // 原始文件名
                "text/plain",          // 内容类型
                inputStream            // 文件内容输入流
        );

        // 打印文件信息
        System.out.println("File name: " + multipartFile.getName());
        System.out.println("Original file name: " + multipartFile.getOriginalFilename());
        System.out.println("Content type: " + multipartFile.getContentType());
        System.out.println("File size: " + multipartFile.getSize());

        File file = new File("D:/tuodi/cslinks3.1/images/temp/2/2/11");
        System.out.println(file.isDirectory());
        System.out.println(file.getName());
        System.out.println(file.getName().substring(file.getName().lastIndexOf(".")));
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getPath());
        System.out.println(file.getParent());
        System.out.println(file.length());

        NiceFile.upload("D:/tuodi/cslinks3.1/images/temp", "D:/tuodi/cslinks3.1/images", multipartFile);
    }

}
