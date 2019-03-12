package backend.util.zipUtil;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class setZip {
    public static String toZip(File directory, String fileName) throws FileNotFoundException,IOException,Exception{
        if (!directory.exists()) {
            throw new FileNotFoundException("File not found.");
        }
        String directoryPath = directory.getAbsolutePath();
        String zipPath = directoryPath + "/" + fileName + ".zip";// 压缩包生成路径，默认原文件夹
        BufferedInputStream bis = null;
        ZipOutputStream zos = null;
        File[] contents = directory.listFiles();// 先检查文件夹是否为空
        if (contents == null || contents.length < 1) {
            throw new FileNotFoundException("No file in it.");
        }
        zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipPath)));
        byte[] buffer = new byte[1024 * 10];// 缓冲流的缓冲区（大小暂定为10KB）
        long totalSize = 0;
        for (File file : contents) {
            totalSize += file.length();
            if (totalSize > 1024 * 1024 * 20) {
                throw new Exception("File too large.");
            }
            ZipEntry zipEntry = new ZipEntry(file.getName());// 创建压缩包
            zos.putNextEntry(zipEntry);
            bis = new BufferedInputStream(new FileInputStream(file), 1024 * 10);
            int hasRead = 0;// 读取内容
            while ((hasRead = bis.read(buffer, 0, 1024 * 10)) != -1) {
                zos.write(buffer, 0, hasRead);
            }
        }
        if (bis != null) {
            bis.close();
        }
        if (zos != null) {
            zos.close();
        }
        return zipPath;
    }
}
