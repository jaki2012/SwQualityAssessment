package com.tongji409.website.service;

import com.tongji409.website.service.support.ServiceSupport;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Project: SwQualityAssesment
 * Package: com.tongji409.website.services
 * Author:  Novemser
 * 2016/12/15
 */
@Service
@ComponentScan
public class FileSystemService extends ServiceSupport {

    private String serverFileRootPath = "/Users/lijiechu/Documents/scode/";

    /***
     * 把文件从指定URL下载下来
     * 下载的文件首先会暂时以.tmp文件存储，如果下载成功则会重命名为正常文件名
     * 否则失败，删除tmp文件
     * @param fileName 文件路径
     * @param archiveUrl 下载的Url
     * @return 是否成功
     * @throws IOException
     */
    public boolean saveArchiveToFile(String fileName, String archiveUrl) {
        URL website = null;
        try {
            website = new URL(archiveUrl);
        } catch (MalformedURLException e) {
            packageError("打开archiveUrl失败，请重试");
            return false;
        }

        // First generate a temp file
        File fileFolder = new File(serverFileRootPath + fileName + "_dir");

        fileFolder.mkdirs();

        File saveFile = new File(serverFileRootPath + fileName + "_dir/" + fileName + ".tmp");

        if (saveFile.exists())
            saveFile.delete();

        URLConnection connection = null;
        try {
            connection = website.openConnection(Proxy.NO_PROXY);
        } catch (IOException e) {
            packageError("打开指向项目的连接失败，请重试");
            return false;
        }
        connection.setConnectTimeout(1000 * 10);
        // If download time exceeds 2 hours
        // Retry/Abort
        connection.setReadTimeout(1000 * 60 * 60 * 2);
//        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
//        FileOutputStream fos = new FileOutputStream(saveFile);

        try {
            FileUtils.copyToFile(connection.getInputStream(), saveFile);
        } catch (IOException e) {
            saveFile.delete();
            packageError("项目下载失败，请重试");
            return false;
        }
        // Java NIO
//        FileUtils.copyURLToFile(website, saveFile);
//        long offset = 0;
//        long count;
//        while ((count = fos.getChannel().transferFrom(rbc, offset, Long.MAX_VALUE)) > 0)
//        {
//            offset += count;
//        }
        // If succeed, change to the desired name
        File dest = new File(serverFileRootPath + fileName + "_dir/" + fileName + ".zip");
        if (dest.exists())
            dest.delete();

        if (!saveFile.renameTo(dest)) {
            saveFile.delete();
            packageError("保持文件至服务器失败，请重试");
            return false;
        }

        return true;
    }

    /***
     * 验证下载的文件是否完好
     * @param file 文件对象
     * @return true:完整, false otherwise
     */
    public boolean isZipFileValid(final File file) {
        ZipFile zipfile = null;
        ZipInputStream zis = null;
        try {
            zipfile = new ZipFile(file);
            zis = new ZipInputStream(new FileInputStream(file));
            ZipEntry ze = zis.getNextEntry();
            if (ze == null) {
                return false;
            }
            while (ze != null) {
                // if it throws an exception fetching any of the following then we know the file is corrupted.
                zipfile.getInputStream(ze);
                ze.getCrc();
                ze.getCompressedSize();
                ze.getName();
                ze = zis.getNextEntry();
            }
            return true;
        } catch (ZipException e) {
            return false;
        } catch (IOException e) {
            return false;
        } finally {
            try {
                if (zipfile != null) {
                    zipfile.close();
                }
                if (zis != null) {
                    zis.close();
                }
            } catch (IOException e) {
                return false;
            }

        }
    }

    public String unzipProject(String name) {
        File[] files = listServerFiles(name);

        if (files.length != 1)
            return null;

        return unzipArchive(files[0]);
    }

    public void unzipAll(String path) {
        unzipArchives(listServerFiles(path));
    }

    public File[] listServerFiles(String path) {
        if (null == path || path.equals(""))
            return null;

        File[] files = new File(serverFileRootPath + path + "_dir").listFiles();
        return files;
    }



    public String unzipArchive(File file) {
        if (file.isDirectory())
            return null;

        String relative = file.getName() + System.currentTimeMillis();
        String pathName = serverFileRootPath + relative  + "_dir";

        try {
            if (FilenameUtils.getExtension(file.getCanonicalPath()).equals("zip")) {
                net.lingala.zip4j.core.ZipFile zipFile = new net.lingala.zip4j.core.ZipFile(file);
                zipFile.extractAll(pathName);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return relative;
    }

    public void unzipArchives(File[] files) {

        for (File file : files) {
            if (file.isDirectory()) {
//                System.out.println("Directory: " + file.getName());
                unzipArchives(file.listFiles()); // Calls same method again to extract dir.
            } else {
                try {
                    if (FilenameUtils.getExtension(file.getCanonicalPath()).equals("zip"))
                        try {
                            net.lingala.zip4j.core.ZipFile zipFile = new net.lingala.zip4j.core.ZipFile(file);
                            try {
                                zipFile.extractAll(serverFileRootPath + file.getName() + System.currentTimeMillis());
//                                System.out.println("Extract " + file.getName() + " successful.");
                            } catch (net.lingala.zip4j.exception.ZipException e) {
                                e.printStackTrace();
                            }
                        } catch (net.lingala.zip4j.exception.ZipException e) {
                            e.printStackTrace();
                        }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
