package com.test.controller;

import com.test.service.FastdfsFileUploadInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/fdfs")
public class FastDFSController {

    @Autowired
    private FastdfsFileUploadInfoService fastDFSService;

    /**
     * 根据本地文件路径上传文件
     * @param
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    public String upload(String fileLocalPath) {
        String result = fastDFSService.upload(fileLocalPath);
        return result;
    }

    /**
     * 文件下载
     * @param response
     * @throws Exception
     */
    @GetMapping("/download")
    public String  download(String remoteFilename, HttpServletResponse response) throws Exception{
        boolean result = fastDFSService.download(remoteFilename);
        if (result) {
            return "file download success";
        }
        return "file download failed";
    }

}
