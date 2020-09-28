package cn.com.doone.tx.cloud.service.config.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.com.doone.tx.cloud.service.config.dao.provider.SysParmsProvider;
import cn.com.doone.tx.cloud.service.config.evt.sysParms.DeleteSysParmEvt;
import cn.com.doone.tx.cloud.service.config.evt.sysParms.EditSysParmEvt;
import cn.com.doone.tx.cloud.service.config.evt.sysParms.QuerySysParmEvt;
import cn.com.doone.tx.cloud.service.config.info.SysParmsInfo;

/**
 * yecz
 */
@Mapper
public interface SysParmsMapper {

    @SelectProvider(type = SysParmsProvider.class , method = "querySysParmsCount")
    int querySysParmsCount(QuerySysParmEvt evt);

    @SelectProvider(type = SysParmsProvider.class , method = "querySysParmsByParam")
    List<SysParmsInfo> querySysParmsByParam(QuerySysParmEvt evt);

    @Update("update td_config set STATUS='D'where config_key in (select c.config_key from" +
            "(select config_key from td_config where id=#{id}) c)")
    int doDelete(DeleteSysParmEvt evt);

    @SelectProvider(type = SysParmsProvider.class , method = "querySysParmsIsExist")
    int  querySysParmsIsExist(QuerySysParmEvt evt);

    @UpdateProvider(type = SysParmsProvider.class , method = "updateSysParm")
    int updateSysParm(EditSysParmEvt evt);

    //---value-----
    @SelectProvider(type = SysParmsProvider.class , method = "queryParmValuesCount")
    int queryParmValuesCount(QuerySysParmEvt evt);

    @SelectProvider(type = SysParmsProvider.class , method = "queryParmValuesByParam")
    List<SysParmsInfo> queryParmValuesByParam(QuerySysParmEvt evt);

//    @Delete("delete from td_config where id=#{id}")
    @Update("update td_config set STATUS='D'where id=#{id}")
    int doDeleteValue(DeleteSysParmEvt evt);

    //查询系统参数key集合
    @Select("Select distinct config_key from td_config where type=0 and status='E'")
    List<String> queryKeys();

    //根据key查询value集合
    @Select("Select distinct config_value from td_config where config_key=#{configKey} and type=1 and STATUS='E'")
    List<String> queryKeyValues(String configKey);
    
  //根据key查询value集合
    @Select("Select distinct config_value from td_config where config_key=#{configKey} and type=1 and STATUS='E'")
    List<SysParmsInfo> queryKeyValue(QuerySysParmEvt evt);

}
