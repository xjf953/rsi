package com.shawn.votesystem.utils;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class UploadUtil {

    /**
     * @param file     //文件对象
     * @param filePath //上传路径
     * @param fileName //文件名
     * @return 文件名
     */
    public static String fileUp(MultipartFile file, String filePath, String fileName) {
        String extName = ""; // 扩展名格式：
        try {
            if (file.getOriginalFilename().lastIndexOf(".") >= 0) {
                extName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            }
            if (StringUtils.isBlank(extName)) {
                extName = ".png";
            }
            //copyFile(file.getInputStream(), filePath, fileName + extName).replaceAll("-", "");
            copyFile(file.getInputStream(), filePath, fileName + extName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return fileName + extName;
    }

    /**
     * 写文件到当前目录的upload目录中
     *
     * @param in
     * @param
     * @throws IOException
     */
    public static String copyFile(InputStream in, String dir, String realName)
            throws IOException {
        File file = new File(dir, realName);

        if (file.exists()) {
            FileUtil.del(dir + realName);
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();

        copyInputStreamToFile(in, file);
        return realName;
    }

    public static void copyInputStreamToFile(InputStream source, File destination) throws IOException {
        try {
            copyToFile(source, destination);
        } finally {
            IoUtil.close(source);
        }

    }

    public static void copyToFile(InputStream source, File destination) throws IOException {
        FileOutputStream output = openOutputStream(destination);

        try {
            IoUtil.copy(source, output);
            output.close();
        } finally {
            IoUtil.close(output);
        }

    }

    public static FileOutputStream openOutputStream(File file) throws IOException {
        return openOutputStream(file, false);
    }

    public static FileOutputStream openOutputStream(File file, boolean append) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }

            if (!file.canWrite()) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        } else {
            File parent = file.getParentFile();
            if (parent != null && !parent.mkdirs() && !parent.isDirectory()) {
                throw new IOException("Directory '" + parent + "' could not be created");
            }
        }

        return new FileOutputStream(file, append);
    }

    public static String getRootPath() {
        return System.getProperty("user.dir");
    }
}
