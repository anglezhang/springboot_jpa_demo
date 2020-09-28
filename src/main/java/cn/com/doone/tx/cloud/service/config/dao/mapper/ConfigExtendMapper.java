package cn.com.doone.tx.cloud.service.config.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.com.doone.tx.cloud.service.config.dao.provider.ExtendProvider;
import cn.com.doone.tx.cloud.service.config.evt.extend.TdExtendField;
import cn.com.doone.tx.cloud.service.config.info.TdExtendFieldInfo;

/**
 * Created by loser on 2017/2/13.
 */
@Mapper
public interface ConfigExtendMapper {


    @SelectProvider(type = ExtendProvider.class , method = "getExtendCount")
    Integer getExtendCount(TdExtendField tdExtendField);

    @SelectProvider(type = ExtendProvider.class , method = "getExtendByParam")
    List<TdExtendField> getExtendByParam(TdExtendField tdExtendField);

    @SelectProvider(type = ExtendProvider.class , method = "queryExtendByTableName")
    List<TdExtendField> queryExtendByTableName(TdExtendField tdExtendField);

    @InsertProvider(type = ExtendProvider.class , method = "doAdd")
    void doAdd(TdExtendField tdExtendField);

    @Select("SELECT table_name tableName FROM td_dict GROUP BY table_name")
    List<String> queryDictTableName();

    @Select("SELECT t.column_name FROM td_dict t WHERE t.TABLE_NAME = #{dictColumnName} GROUP BY t.column_name ")
    List<String> getDictColumnName(String dictColumnName);

    @Delete("update td_extend_field set status='D' where table_name = #{tableName}")
    int del(TdExtendField tdExtendField);

    @Update("update td_extend_field set status='D' where table_name = #{tableName}")
    int updateStatus(String tableName);

    @UpdateProvider(type = ExtendProvider.class , method = "doEdit")
    int doEdit(TdExtendField tdExtendField);

    @SelectProvider(type = ExtendProvider.class , method = "queryExtendFieldByParam")
    List<TdExtendFieldInfo> queryExtendFieldByParam(TdExtendField tdExtendField);
    
    @UpdateProvider(type = ExtendProvider.class , method = "updateStatusByParam")
    int updateStatusByParam(TdExtendField tdExtendField);
}
