package com.test.service.impl;

import com.test.entity.FastdfsFileUploadInfo;
import com.test.mapper.FastdfsFileUploadInfoMapper;
import com.test.service.FastdfsFileUploadInfoService;
import com.test.utils.FastdfsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class FastdfsFileUploadInfoServiceImpl implements FastdfsFileUploadInfoService {

    @Autowired
    private FastdfsFileUploadInfoMapper fastdfsFileUploadInfoMapper;

    /**
     * 根据本地文件路径上传本地文件至fastdfs
     * @param fileLocalPath
     * @return
     */
    @Override
    public String upload(String fileLocalPath) {
//        String fileLocalPath = "E:\\pictures\\test\\yun.jpg";
        String[] uploadInfo = FastdfsUtil.fileLocalUpload(fileLocalPath,null);
        System.out.println(uploadInfo);
        String fileName = fileLocalPath.substring(fileLocalPath.lastIndexOf("\\") + 1);
        FastdfsFileUploadInfo fastdfsFileUploadInfo = new FastdfsFileUploadInfo();
        fastdfsFileUploadInfo.setFileName(fileName);
        fastdfsFileUploadInfo.setGroupName(uploadInfo[0]);
        fastdfsFileUploadInfo.setRemoteFilename(uploadInfo[1]);
        Date date = new Date();
        Timestamp currentTime = new Timestamp(date.getTime());
        fastdfsFileUploadInfo.setUploadTime(currentTime);
        int insertResult = fastdfsFileUploadInfoMapper.insert(fastdfsFileUploadInfo);
        if (insertResult > -1) {
            return "file upload success";
        }
        return "file upload failed!";
    }

    /**
     * 根据远程文件名称从fastdfs服务器上下载文件至本地
     * @param remoteFilename
     * @return
     */
    @Override
    public boolean download(String remoteFilename) {
        boolean result = FastdfsUtil.fileServerDownload(remoteFilename);
        return result;
    }

}
