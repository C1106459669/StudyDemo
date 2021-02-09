package com.test.service.impl;

import com.test.entity.FastdfsFileUploadInfo;
import com.test.mapper.FastdfsFileUploadInfoMapper;
import com.test.service.FastdfsFileUploadInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * fastdfs文件上传信息表(FastdfsFileUploadInfo)表服务实现类
 *
 * @author LGCSpadger
 * @since 2021-02-08 15:40:58
 */
@Service("fastdfsFileUploadInfoService")
public class FastdfsFileUploadInfoServiceImpl implements FastdfsFileUploadInfoService {

    @Autowired
    private FastdfsFileUploadInfoMapper fastdfsFileUploadInfoMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public FastdfsFileUploadInfo queryById(Integer id) {
        return this.fastdfsFileUploadInfoMapper.queryById(id);
    }

    @Override
    public List<FastdfsFileUploadInfo> getAll() {
        return this.fastdfsFileUploadInfoMapper.selectAll();
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<FastdfsFileUploadInfo> queryAllByLimit(int offset, int limit) {
        return this.fastdfsFileUploadInfoMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param fastdfsFileUploadInfo 实例对象
     * @return 实例对象
     */
    @Override
    public FastdfsFileUploadInfo insert(FastdfsFileUploadInfo fastdfsFileUploadInfo) {
        this.fastdfsFileUploadInfoMapper.insert(fastdfsFileUploadInfo);
        return fastdfsFileUploadInfo;
    }

    /**
     * 修改数据
     *
     * @param fastdfsFileUploadInfo 实例对象
     * @return 实例对象
     */
    @Override
    public FastdfsFileUploadInfo update(FastdfsFileUploadInfo fastdfsFileUploadInfo) {
        this.fastdfsFileUploadInfoMapper.update(fastdfsFileUploadInfo);
        return this.queryById(fastdfsFileUploadInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.fastdfsFileUploadInfoMapper.deleteById(id) > 0;
    }
}