package com.shuishu.base.utils.file;


import com.shuishu.base.utils.file.entity.FileDto;
import com.shuishu.base.utils.file.entity.FileVo;
import com.shuishu.base.utils.file.enums.FileTypeEnums;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author ：谁书-ss
 * @Date ：2024/6/18 9:49
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <br>
 * @Description ：通用的文件操作
 * <br>
 * 参考：
 */
public class NiceFile {


    /**
     * 上传文件
     *
     * @param tempFolderPath 临时文件路径
     * @param saveFolderPath 保存的路径
     * @param multipartFile  文件
     * @return -
     */
    public static FileVo upload(String tempFolderPath, String saveFolderPath, MultipartFile multipartFile) {
        if (multipartFile == null) {
            return null;
        }
        if (tempFolderPath == null || !new File(tempFolderPath).isDirectory() || !(new File(tempFolderPath).mkdirs())) {
            return new FileVo(multipartFile.getOriginalFilename(), getFileSuffix(multipartFile), multipartFile.getSize(), "临时文件路径不是文件夹");
        }
        if (saveFolderPath == null || !new File(saveFolderPath).isDirectory() || !(new File(saveFolderPath).mkdirs())) {
            return new FileVo(multipartFile.getOriginalFilename(), getFileSuffix(multipartFile), multipartFile.getSize(), "保存文件路径不是文件夹");
        }

        return new FileVo();
    }

    /**
     * 上传文件
     *
     * @param tempFolderPath 临时文件路径
     * @param saveFolderPath 保存的路径
     * @param file           文件
     * @return -
     */
    public static FileVo upload(String tempFolderPath, String saveFolderPath, File file) {
        if (file == null) {
            return null;
        }
        if (tempFolderPath == null || !new File(tempFolderPath).isDirectory() || !(new File(tempFolderPath).mkdirs())) {
            return new FileVo(file.getName(), getFileSuffix(file), file.length(), "临时文件路径不是文件夹");
        }
        if (saveFolderPath == null || !new File(saveFolderPath).isDirectory() || !(new File(saveFolderPath).mkdirs())) {
            return new FileVo(file.getName(), getFileSuffix(file), file.length(), "保存文件路径不是文件夹");
        }

        return null;
    }

    /**
     * 上传文件
     *
     * @param fileDto -
     * @return -
     */
    public static FileVo upload(FileDto fileDto) {
        if (fileDto.getFile() != null) {
            return upload(fileDto.getTempFolderPath(), fileDto.getSaveFolderPath(), fileDto.getFile());
        }
        if (fileDto.getMultipartFile() != null) {
            return upload(fileDto.getTempFolderPath(), fileDto.getSaveFolderPath(), fileDto.getMultipartFile());
        }
        return null;
    }

    /**
     * 批量上传文件
     *
     * @param fileDtoList -
     * @return -
     */
    public static List<FileVo> upload(List<FileDto> fileDtoList) {
        List<FileVo> resultList = new ArrayList<>();
        if (fileDtoList != null && !fileDtoList.isEmpty()) {
            for (FileDto fileDto : fileDtoList) {
                if (fileDto.getFile() != null) {
                    resultList.add(upload(fileDto.getTempFolderPath(), fileDto.getSaveFolderPath(), fileDto.getFile()));
                }
                if (fileDto.getMultipartFile() != null) {
                    resultList.add(upload(fileDto.getTempFolderPath(), fileDto.getSaveFolderPath(), fileDto.getMultipartFile()));
                }
            }
        }
        return resultList;
    }


    /**
     * 获取文件 File 的后缀名
     *
     * @param file -
     * @return -
     */
    public static String getFileSuffix(File file) {
        if (file != null && !file.getName().isEmpty()) {
            return file.getName().substring(file.getName().lastIndexOf("."));
        }
        return null;
    }

    /**
     * 获取文件 MultipartFile 的后缀名
     *
     * @param multipartFile -
     * @return -
     */
    public static String getFileSuffix(MultipartFile multipartFile) {
        if (multipartFile != null && multipartFile.getOriginalFilename() != null) {
            return multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        }
        return null;
    }

    /**
     * 文件对象类型转换：MultipartFile -》File
     *
     * @param multipartFile  要转换的文件MultipartFile
     * @param tempFolderPath 临时文件路径
     * @return -
     * @throws IOException -
     */
    public File convertMultipartFileToFile(MultipartFile multipartFile, String tempFolderPath) throws IOException {
        if (multipartFile == null || multipartFile.getSize() == 0 || !StringUtils.hasText(multipartFile.getOriginalFilename())) {
            throw new NullPointerException("multipartFile文件不能为空");
        }
        if (tempFolderPath == null || !new File(tempFolderPath).isDirectory() || !(new File(tempFolderPath).mkdirs())) {
            throw new IOException("临时文件路径不是文件夹");
        }
        if (tempFolderPath.endsWith("/") || tempFolderPath.endsWith("\\")) {
            tempFolderPath = tempFolderPath.substring(0, tempFolderPath.length() - 1);
        }
        File file = new File(tempFolderPath + "/" + multipartFile.getOriginalFilename());
        boolean newFile = file.createNewFile();
        if (newFile) {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(multipartFile.getBytes());
            fileOutputStream.close();
        } else {
            throw new IOException("MultipartFile转换File失败");
        }
        return file;
    }

    /**
     * 文件对象类型转换：File -》MultipartFile
     *
     * @param file 要转换的文件File
     * @return -
     * @throws IOException -
     */
    public MultipartFile convertFileToMultipartFile(File file) throws IOException {
        if (file == null || file.length() == 0) {
            throw new NullPointerException("multipartFile文件不能为空");
        }
        String originalFilename = file.getName();
        String name = file.getName();
        String suffix = null;
        if (file.isFile() && file.getName().contains(".")) {
            name = file.getName().substring(0, file.getName().lastIndexOf("."));
            suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        }
        String finalName = name;
        String contentType = FileTypeEnums.getTypeBySuffix(suffix);

        byte[] fileByte = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(fileByte);
        }

        return new MultipartFile() {
            @Override
            public String getName() {
                return finalName;
            }

            @Override
            public String getOriginalFilename() {
                return originalFilename;
            }

            @Override
            public String getContentType() {
                return contentType;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return fileByte.length;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return fileByte;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new ByteArrayInputStream(fileByte);
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                try (FileOutputStream fos = new FileOutputStream(dest)) {
                    fos.write(fileByte);
                }
            }
        };
    }

}
