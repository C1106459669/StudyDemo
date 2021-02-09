package com.test;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestFastdfs {

    //上传文件
    @Test
    public void testUpload() {
        try {
            //加载 fastdfs‐client.properties 配置文件
            ClientGlobal.initByProperties("config/fastdfs-client.properties");
            System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
            System.out.println("charset=" + ClientGlobal.g_charset);
            //定义TrackerClient，用于请求TrackerServer
            TrackerClient trackerClient = new TrackerClient();
            //连接tracker Server
            TrackerServer trackerServer = trackerClient.getConnection();
            if (trackerServer == null) {
                System.out.println("getConnection return null");
                return;
            }
            //获取一个storage server
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            if (storageServer == null) {
                System.out.println("getStoreStorage return null");
            }
            //创建一个storage存储客户端
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
            //本地文件的路径
            String filePath = "E:\\pictures\\yy.jpg";
            String fileId = null;
            //文件上传成功后会返回一个文件id
            fileId = storageClient1.upload_file1(filePath, "jpg", null);
            System.out.println("Upload local file " + filePath + " ok, fileid = " + fileId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //根据文件的 fileId 查询文件
    @Test
    public void testQueryFile() throws IOException, MyException {
        //加载fastdfs‐client.properties配置文件
        ClientGlobal.initByProperties("config/fastdfs-client.properties");
        //定义TrackerClient，用于请求TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //连接tracker Server
        TrackerServer trackerServer = trackerClient.getConnection();
        if (trackerServer == null) {
            System.out.println("getConnection return null");
            return;
        }
        //获取一个storage server
        StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
        if (storageServer == null) {
            System.out.println("getStoreStorage return null");
        }
        //定义StorageClient，用于请求storage server
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        FileInfo fileInfo = storageClient.query_file_info("group1",
                "M00/00/00/wKgrFl_8X1-AIjyGAACcOQWhdOY827.jpg");
        System.out.println(fileInfo);
    }

    //根据文件的 fileId 下载文件
    @Test
    public void testDownloadFile() throws IOException, MyException {
        //加载fastdfs‐client.properties配置文件
        ClientGlobal.initByProperties("config/fastdfs-client.properties");
        //定义TrackerClient，用于请求TrackerServer
        TrackerClient trackerClient = new TrackerClient();
        //连接tracker Server
        TrackerServer trackerServer = trackerClient.getConnection();
        if (trackerServer == null) {
            System.out.println("getConnection return null");
            return;
        }
        //获取一个storage server
        StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
        if (storageServer == null) {
            System.out.println("getStoreStorage return null");
        }
        //定义StorageClient1，用于请求storage server
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);
        //根据文件id下载文件
        byte[] result = storageClient1.download_file1("group1/M00/00/00/wKgrFl_8X1-AIjyGAACcOQWhdOY827.jpg");
        File file = new File("E:\\pictures\\test\\yy33.jpg");
        //使用输出流保存文件
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(result);
        fileOutputStream.close();
    }

}
