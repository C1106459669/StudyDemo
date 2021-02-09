package com.test.controller;

import com.test.entity.FastdfsFileUploadInfo;
import com.test.service.FastdfsFileUploadInfoService;
import com.test.utils.DataExportUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * fastdfs文件上传信息表(FastdfsFileUploadInfo)表控制层
 *
 * @author LGCSpadger
 * @since 2021-02-08 15:40:58
 */
@RestController
@RequestMapping("fastdfsFileUploadInfo")
public class FastdfsFileUploadInfoController {

    /**
     * 服务对象
     */
    @Autowired
    private FastdfsFileUploadInfoService fastdfsFileUploadInfoService;

    @Autowired
    private DataExportUtils exportUtils;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public FastdfsFileUploadInfo selectOne(Integer id) {
        return this.fastdfsFileUploadInfoService.queryById(id);
    }

    @GetMapping("getAll")
    public List<FastdfsFileUploadInfo> getAll(HttpServletResponse response) {
        List<FastdfsFileUploadInfo> data = fastdfsFileUploadInfoService.getAll();
        List<String> tableHead = new ArrayList<>();
        tableHead.add("id");
        tableHead.add("文件名");
        tableHead.add("上传时间");
        tableHead.add("上传用户");
        tableHead.add("文件组名");
        tableHead.add("服务器文件地址");
        tableHead.add("服务器ip");
        exportUtils.exportToExcel(response,"测试","F:\\test","fastdfs文件信息.xls",tableHead,data);
        return data;
    }

}