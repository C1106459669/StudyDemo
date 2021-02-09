package com.test.service;

public interface FastdfsFileUploadInfoService {

    //根据本地文件路径上传本地文件至fastdfs
    public String upload(String fileLocalPath);

    //根据远程文件名称从fastdfs服务器上下载文件至本地
    public boolean download(String remoteFilename);

}
