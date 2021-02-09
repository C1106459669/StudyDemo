package com.test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * fastdfs文件上传信息表(FastdfsFileUploadInfo)实体类
 *
 * @author LGCSpadger
 * @since 2021-01-14 20:48:08
 */
@Data
public class FastdfsFileUploadInfo implements Serializable {

    private static final long serialVersionUID = 831470072056442321L;
    /**
     * id
     */
    private Integer id;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件上传时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;
    /**
     * 文件上传人
     */
    private String uploadUsername;
    /**
     * 文件组名称
     */
    private String groupName;
    /**
     * 远程文件名称
     */
    private String remoteFilename;
    /**
     * 文件所在服务器IP地址
     */
    private String serverIp;

}