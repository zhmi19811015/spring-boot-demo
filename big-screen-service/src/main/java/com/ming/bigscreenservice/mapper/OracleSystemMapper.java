package com.ming.bigscreenservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/3/18 8:43 上午
 */
public interface OracleSystemMapper extends BaseMapper {
    @Select("SELECT SUM(num_rows) num_rows from dba_tables  where owner = #{owner}  order by num_rows desc  nulls last")
    long getTotalCount(@Param("owner") String owner);

    @Select("select  sum(bytes) as bt　from dba_data_files WHERE tablespace_name=#{tablespaceName}  group by tablespace_name")
    long getTableSpaceSize(@Param("tablespaceName") String tablespaceName);
}
