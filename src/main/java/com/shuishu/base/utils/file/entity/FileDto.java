package com.shuishu.base.utils.file.entity;


import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Author ：谁书-ss
 * @Date ：2024/7/2 13:47
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <br>
 * @Description ：
 * <br>
 */
public class FileDto {

    /**
     * 临时文件夹路径
     */
    @NotNull(message = "临时文件夹路径不能为空")
    private String tempFolderPath;
    /**
     * 保存文件夹路径
     */
    @NotNull(message = "保存文件夹路径不能为空")
    private String saveFolderPath;
    /**
     * 保存文件使用的class类型：Java自带的类型File
     */
    private File file;
    /**
     * 保存文件使用的class类型：spring框架的MultipartFile
     */
    private MultipartFile multipartFile;


    public FileDto() {
    }

    public FileDto(String tempFolderPath, String saveFolderPath, File file, MultipartFile multipartFile) {
        this.tempFolderPath = tempFolderPath;
        this.saveFolderPath = saveFolderPath;
        this.file = file;
        this.multipartFile = multipartFile;
    }

    public FileDto(String tempFolderPath, String saveFolderPath, File file) {
        this.tempFolderPath = tempFolderPath;
        this.saveFolderPath = saveFolderPath;
        this.file = file;
    }

    public FileDto(String tempFolderPath, String saveFolderPath, MultipartFile multipartFile) {
        this.tempFolderPath = tempFolderPath;
        this.saveFolderPath = saveFolderPath;
        this.multipartFile = multipartFile;
    }

    public String getTempFolderPath() {
        return tempFolderPath;
    }

    public void setTempFolderPath(String tempFolderPath) {
        this.tempFolderPath = tempFolderPath;
    }

    public String getSaveFolderPath() {
        return saveFolderPath;
    }

    public void setSaveFolderPath(String saveFolderPath) {
        this.saveFolderPath = saveFolderPath;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }


    @Override
    public String toString() {
        return "FileDto{" +
                "saveFolderPath='" + saveFolderPath + '\'' +
                ", file=" + file +
                ", multipartFile=" + multipartFile +
                '}';
    }


}
