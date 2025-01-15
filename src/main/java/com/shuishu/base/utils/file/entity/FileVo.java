package com.shuishu.base.utils.file.entity;



/**
 * @Author ：谁书-ss
 * @Date ：2024/7/2 14:07
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <br>
 * @Description ：
 * <br>
 */
public class FileVo {

    /**
     * 上传时候的文件名（不含文件类型后缀）
     * 示例：aaa
     */
    private String fileNameOfOrigin;
    /**
     * 保存的文件名（不含文件类型后缀）
     * 示例：oweruiagnajg
     */
    private String fileNameOfCurrent;
    /**
     * 所在文件夹路径
     * 示例：D:/test/file
     */
    private String folderPath;
    /**
     * 文件全路径
     * 示例：D:/test/file/aaa.png
     */
    private String filePath;
    /**
     * 文件后缀
     * 示例：png
     */
    private String fileSuffix;
    /**
     * 文件大小（字节）
     */
    private Long fileSize;
    /**
     * 处理失败描述
     */
    private String failDesc;

    public FileVo() {
    }

    public FileVo(String fileNameOfOrigin, String fileNameOfCurrent, String folderPath, String filePath, String fileSuffix, String failDesc) {
        this.fileNameOfOrigin = fileNameOfOrigin;
        this.fileNameOfCurrent = fileNameOfCurrent;
        this.folderPath = folderPath;
        this.filePath = filePath;
        this.fileSuffix = fileSuffix;
        this.failDesc = failDesc;
    }

    public FileVo(String fileNameOfOrigin, String fileSuffix, Long fileSize, String failDesc) {
        this.fileNameOfOrigin = fileNameOfOrigin;
        this.fileSuffix = fileSuffix;
        this.fileSize = fileSize;
        this.failDesc = failDesc;
    }

    public String getFileNameOfOrigin() {
        return fileNameOfOrigin;
    }

    public void setFileNameOfOrigin(String fileNameOfOrigin) {
        this.fileNameOfOrigin = fileNameOfOrigin;
    }

    public String getFileNameOfCurrent() {
        return fileNameOfCurrent;
    }

    public void setFileNameOfCurrent(String fileNameOfCurrent) {
        this.fileNameOfCurrent = fileNameOfCurrent;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFailDesc() {
        return failDesc;
    }

    public void setFailDesc(String failDesc) {
        this.failDesc = failDesc;
    }

    @Override
    public String toString() {
        return "FileVo{" +
                "fileNameOfOrigin='" + fileNameOfOrigin + '\'' +
                ", fileNameOfCurrent='" + fileNameOfCurrent + '\'' +
                ", folderPath='" + folderPath + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileType='" + fileSuffix + '\'' +
                ", fileSize=" + fileSize +
                ", failDesc='" + failDesc + '\'' +
                '}';
    }

}
