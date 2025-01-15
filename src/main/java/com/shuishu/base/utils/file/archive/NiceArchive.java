package com.shuishu.base.utils.file.archive;


import cn.hutool.core.util.ZipUtil;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @Author ：谁书-ss
 * @Date ：2024/6/18 9:46
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <br>
 * @Description ：压缩文件，解压缩
 * <br>
 * 参考：
 */
public class NiceArchive {

    public static File decompress(String zipFilePath, String outFolderPath, Boolean srcFileIsRetain) throws FileNotFoundException {
        if (zipFilePath == null || zipFilePath.isEmpty() || !(new File(zipFilePath).exists())) {
            throw new FileNotFoundException("源文件不存在");
        }
        if (outFolderPath == null || outFolderPath.isEmpty()) {
            throw new FileNotFoundException("保存解压文件路径不存在");
        }
        srcFileIsRetain = srcFileIsRetain == null || srcFileIsRetain;

        return ZipUtil.unzip(new File(zipFilePath), new File(outFolderPath));
    }

    public static File compress(String srcFilePath, String outFolderPath, Boolean srcFileIsRetain) throws FileNotFoundException {
        if (srcFilePath == null || srcFilePath.isEmpty() || !(new File(srcFilePath).exists())) {
            throw new FileNotFoundException("源文件不存在");
        }
        if (outFolderPath == null || outFolderPath.isEmpty()) {
            throw new FileNotFoundException("保存压缩文件路径不存在");
        }
        srcFileIsRetain = srcFileIsRetain == null || srcFileIsRetain;
        return ZipUtil.zip(srcFilePath, outFolderPath);
    }

}
