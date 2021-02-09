package com.test.utils;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class FastdfsUtil {

    static Logger logger = LoggerFactory.getLogger(FastdfsUtil.class);

    static {
        try {
            ClientGlobal.initByProperties("config/fastdfs-client.properties");
        } catch (Exception e) {
            logger.error("FastFds初始化失败", e);
        }
    }

    //文件上传
    public String upload(MultipartFile file) throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();

        StorageServer storageServer = null;
        StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);

        NameValuePair pairs[] = null;
        String oldName = file.getOriginalFilename();
        //扩展名
        String extName = oldName.substring(oldName.lastIndexOf(".") + 1);
        String file1 = storageClient1.upload_file1(file.getBytes(), extName, pairs);
        return file1;
    }

    /**
     * 上传到服务器
     *
     * @param localFilePath 本地文件全路径
     * @return
     */
    public static String[] fileLocalUpload(String localFilePath) {
        return fileLocalUpload(localFilePath, null);
    }

    /**
     * 上传到服务器
     *
     * @param localFilePath 本地文件全路径
     * @param nvp           NameValuePair
     * @return
     */
    public static String[] fileLocalUpload(String localFilePath, NameValuePair[] nvp) {
        try {
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            //文件拓展名
            String fileExtName = localFilePath.substring(localFilePath.lastIndexOf(".") + 1);
            String fileIds[] = storageClient.upload_file(localFilePath, fileExtName, nvp);

            logger.info("fileIds.length={}", fileIds.length);
            logger.info("组名={}", fileIds[0]);
            logger.info("路径={} ", fileIds[1]);
            return fileIds;
        } catch (FileNotFoundException e) {
            logger.error("FastFds上传失败", e);
        } catch (IOException e) {
            logger.error("FastFds上传失败", e);
        } catch (MyException e) {
            logger.error("FastFds上传失败", e);
        }
        return null;
    }

    /**
     * 从文件服务器下载
     *
     * @param remoteFilename 文件名称例如: M00/00/00/wKgqHVty2ZCAHaBvAAE0vHMtwgw608.png
     * @return
     */
    public static boolean fileServerDownload(String remoteFilename) {
        String localPath = System.getProperty("java.io.tmpdir") + UUID.randomUUID().toString() + ".tmp";
        System.out.println("localPath: " + localPath);
        //System.getProperty("java.io.tmpdir") + UUID.randomUUID().toString() + ".tmp"
        return fileServerDownload("group1", remoteFilename, "E:\\pictures\\test01");
    }

    /**
     * 从文件服务器下载
     *
     * @param remoteFilename    服务器文件名称例如: M00/00/00/wKgqHVty2ZCAHaBvAAE0vHMtwgw608.png
     * @param localDownloadPath 下载到本地路径
     * @return
     */
    public boolean fileServerDownload(String remoteFilename, String localDownloadPath) {
        return fileServerDownload("group1", remoteFilename, localDownloadPath);
    }

    /**
     * 从文件服务器下载
     *
     * @param groupName         组名
     * @param remoteFilename    服务器文件名称例如: M00/00/00/wKgqHVty2ZCAHaBvAAE0vHMtwgw608.png
     * @param localDownloadPath 下载到本地路径
     * @return
     */
    public static boolean fileServerDownload(String groupName, String remoteFilename, String localDownloadPath) {
        try {
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            byte[] b = storageClient.download_file(groupName, remoteFilename);
            FileOutputStream fos = new FileOutputStream(new File(localDownloadPath));
            fos.write(b);
            fos.close();

            return true;
        } catch (Exception e) {
            logger.error("FastFds下载失败", e);
            return false;
        }
    }

    /**
     * 获取文件信息
     *
     * @param remoteFilename 服务器文件名称例如: M00/00/00/wKgqHVty2ZCAHaBvAAE0vHMtwgw608.png
     * @return
     */
    public FileInfo getFileInfo(String remoteFilename) {
        return getFileInfo("group1", remoteFilename);
    }

    /**
     * 获取文件信息
     *
     * @param groupName      组名
     * @param remoteFilename 服务器文件名称例如: M00/00/00/wKgqHVty2ZCAHaBvAAE0vHMtwgw608.png
     * @return
     */
    public FileInfo getFileInfo(String groupName, String remoteFilename) {
        try {
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            FileInfo fi = storageClient.get_file_info(groupName, remoteFilename);
            System.out.println(fi.getSourceIpAddr());
            System.out.println(fi.getFileSize());
            System.out.println(fi.getCreateTimestamp());
            System.out.println(fi.getCrc32());
            return fi;
        } catch (Exception e) {
            logger.error("FastFds获取文件信息失败", e);
            return null;
        }
    }

    public NameValuePair[] getFileMate(String remoteFilename) {
        return getFileMate("group1", remoteFilename);
    }

    public NameValuePair[] getFileMate(String groupName, String remoteFilename) {
        try {
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            NameValuePair nvps[] = storageClient.get_metadata(groupName, remoteFilename);
            for (NameValuePair nvp : nvps) {
                System.out.println(nvp.getName() + ":" + nvp.getValue());
            }
            return nvps;
        } catch (Exception e) {
            logger.error("FastFds获取文件描述信息失败", e);
            return null;
        }
    }

    /**
     * 删除服务器文件
     *
     * @param remoteFilename 服务器文件名称例如: M00/00/00/wKgqHVty2ZCAHaBvAAE0vHMtwgw608.png
     * @return
     */
    public boolean delete(String remoteFilename) {
        return delete("group1", remoteFilename);
    }

    /**
     * 删除服务器文件
     *
     * @param groupName      组名
     * @param remoteFilename 服务器文件名称例如: M00/00/00/wKgqHVty2ZCAHaBvAAE0vHMtwgw608.png
     * @return
     */
    public boolean delete(String groupName, String remoteFilename) {
        try {
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            int i = storageClient.delete_file("group1", remoteFilename);
            logger.info(i == 0 ? "删除成功" : "删除失败:" + i);
            return i == 0 ? true : false;
        } catch (Exception e) {
            logger.error("FastFds删除失败", e);
            return false;
        }
    }

    public static void main(String[] args) {
        FastdfsUtil fastFdsTest = new FastdfsUtil();
        String[] strings = FastdfsUtil.fileLocalUpload("D:\\header.jpg");

        //获取文件信息
        FileInfo ll = fastFdsTest.getFileInfo(strings[1]);

        //下载图片到本地
        boolean fl = FastdfsUtil.fileServerDownload("group1", strings[1], "D:\\" + UUID.randomUUID() + "test.png");

        //删除文件
        boolean fla = fastFdsTest.delete(strings[1]);
    }

}
