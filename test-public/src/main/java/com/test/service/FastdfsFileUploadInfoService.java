package com.test.service;

import com.test.entity.FastdfsFileUploadInfo;

import java.util.List;

/**
 * fastdfs文件上传信息表(FastdfsFileUploadInfo)表服务接口
 *
 * @author LGCSpadger
 * @since 2021-02-08 15:40:57
 */
public interface FastdfsFileUploadInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FastdfsFileUploadInfo queryById(Integer id);

    List<FastdfsFileUploadInfo> getAll();

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<FastdfsFileUploadInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param fastdfsFileUploadInfo 实例对象
     * @return 实例对象
     */
    FastdfsFileUploadInfo insert(FastdfsFileUploadInfo fastdfsFileUploadInfo);

    /**
     * 修改数据
     *
     * @param fastdfsFileUploadInfo 实例对象
     * @return 实例对象
     */
    FastdfsFileUploadInfo update(FastdfsFileUploadInfo fastdfsFileUploadInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}