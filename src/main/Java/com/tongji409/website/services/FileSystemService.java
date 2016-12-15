package com.tongji409.website.services;

import com.tongji409.website.services.support.ServiceSupport;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

/**
 * Project: SwQualityAssesment
 * Package: com.tongji409.website.services
 * Author:  Novemser
 * 2016/12/15
 */
@Service
@ComponentScan
public class FileSystemService extends ServiceSupport {

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
        File saveFile = new File(fileName + ".tmp");
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
        File dest = new File(fileName);
        if (dest.exists())
            dest.delete();

        if (!saveFile.renameTo(dest)) {
            saveFile.delete();
            packageError("保持文件至服务器失败，请重试");
            return false;
        }

        return true;
    }
}
