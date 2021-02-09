package com.test.mapper;

import com.test.entity.FastdfsFileUploadInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * fastdfs文件上传信息表(FastdfsFileUploadInfo)表数据库访问层
 *
 * @author LGCSpadger
 * @since 2021-02-08 15:40:59
 */
public interface FastdfsFileUploadInfoMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FastdfsFileUploadInfo queryById(Integer id);

    List<FastdfsFileUploadInfo> selectAll();

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<FastdfsFileUploadInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param fastdfsFileUploadInfo 实例对象
     * @return 对象列表
     */
    List<FastdfsFileUploadInfo> queryAll(FastdfsFileUploadInfo fastdfsFileUploadInfo);

    /**
     * 新增数据
     *
     * @param fastdfsFileUploadInfo 实例对象
     * @return 影响行数
     */
    int insert(FastdfsFileUploadInfo fastdfsFileUploadInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<FastdfsFileUploadInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<FastdfsFileUploadInfo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<FastdfsFileUploadInfo> 实例对象列表
     * @return 影响行数
     */
    int insertOrUpdateBatch(@Param("entities") List<FastdfsFileUploadInfo> entities);

    /**
     * 修改数据
     *
     * @param fastdfsFileUploadInfo 实例对象
     * @return 影响行数
     */
    int update(FastdfsFileUploadInfo fastdfsFileUploadInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}