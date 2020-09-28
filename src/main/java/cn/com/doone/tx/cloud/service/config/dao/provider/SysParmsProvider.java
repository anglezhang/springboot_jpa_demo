package cn.com.doone.tx.cloud.service.config.dao.provider;


import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import cn.com.doone.tx.cloud.service.config.evt.sysParms.EditSysParmEvt;
import cn.com.doone.tx.cloud.service.config.evt.sysParms.QuerySysParmEvt;

/**
 * yecz
 */
public class SysParmsProvider {

    private final String TD_CONFIG = "td_config";

    public String querySysParmsCount(QuerySysParmEvt evt) {
        SQL sql = new SQL().SELECT("count(*)").FROM(TD_CONFIG);
        String configType = evt.getConfigType();//参数类型
        if (StringUtils.isNotBlank(configType)) {
            sql.WHERE("config_type=#{configType}");
        }
        String configKey = evt.getConfigKey();//参数key
        if (StringUtils.isNotBlank(configKey)) {
            evt.setConfigKey("%"+configKey+"%");
            sql.WHERE("config_key like #{configKey}");
        }
        Long type = evt.getType();//0 key信息 ;1 key-value信息
        if (type!=null) {
            sql.WHERE("type=#{type}");
        }
        sql.WHERE("status='E'");
        return sql.toString();
    }
    public String querySysParmsByParam(QuerySysParmEvt evt) {
        /*SQL sql = new SQL().SELECT("ID as id,CONFIG_TYPE as configType,CONFIG_KEY as configKey," +
                "CONFIG_VALUE as configValue,CONFIG_DESC as configDesc,VALUE_DESC as valueDesc,SORT as sort," +
                "STATUS as status,OPERATOR as operator,CREATE_TIME as createTime,UPDATE_TIME as updateTime")
                .FROM(TD_CONFIG);*/
        SQL sql = new SQL().SELECT("*").FROM(TD_CONFIG);
        Long id = evt.getId();//参数id
        if (id!=null) {
            sql.WHERE("id=#{id}");
        }
        String configType = evt.getConfigType();//参数类型
        if (StringUtils.isNotBlank(configType)) {
            sql.WHERE("config_type=#{configType}");
        }
        String configKey = evt.getConfigKey();//参数key
        if (StringUtils.isNotBlank(configKey)) {
            evt.setConfigKey("%"+configKey+"%");
            sql.WHERE("config_key like #{configKey}");
        }
        Long type = evt.getType();//0 key信息 ;1 key-value信息
        if (type!=null) {
            sql.WHERE("type=#{type}");
        }
        sql.WHERE("status='E'");
        return sql.toString();
    }

    public String querySysParmsIsExist(QuerySysParmEvt evt){
        SQL sql = new SQL().SELECT("count(*)").FROM(TD_CONFIG );
        String configKey = evt.getConfigKey();//参数key
        if (StringUtils.isNotBlank(configKey)) {
            sql.WHERE("config_key=#{configKey}");
        }
        String configType = evt.getConfigType();//参数类型
        if (StringUtils.isNotBlank(configType)) {
            sql.WHERE("config_type=#{configType}");
        }
        String configValue = evt.getConfigValue();//参数值
        if (StringUtils.isNotBlank(configValue)) {
            sql.WHERE("config_value=#{configValue}");
        }
        Long id = evt.getId();//参数id
        if (id!=null&&id!=0){
            sql.WHERE("id<>#{id}");//排除本身
        }
        Long type = evt.getType();//0 key信息 ;1 key-value信息
        if (type!=null) {
            sql.WHERE("type=#{type}");
        }
        sql.WHERE("status='E'");
        return sql.toString();
    }

    public String updateSysParm(EditSysParmEvt evt){
        SQL sql = new SQL().UPDATE(TD_CONFIG);
        String configKey = evt.getConfigKey();//参数key
        if (configKey!=null) {
            sql.SET("config_key=#{configKey}");
        }
        String configType = evt.getConfigType();//参数类型
        if (configType!=null) {
            sql.SET("config_type=#{configType}");
        }
        String configDesc = evt.getConfigDesc();//参数描述
        if (configDesc!=null) {
            sql.SET("config_desc=#{configDesc}");
        }
        String configValue = evt.getConfigValue();//参数值
        if (configValue!=null) {
            sql.SET("config_value=#{configValue}");
        }
        String valueDesc = evt.getValueDesc();//参数值描述
        if (valueDesc!=null) {
            sql.SET("value_desc=#{valueDesc}");
        }
        Date updateTime = evt.getUpdateTime();
        if (updateTime!=null) {
            sql.SET("update_time=#{updateTime}");
        }
        Long id = evt.getId();//参数id
        if (id!=null&&id!=0){
            sql.WHERE("id=#{id}");//排除本身
        }
        return sql.toString();
    }


    //参数value
    public String queryParmValuesCount(QuerySysParmEvt evt) {
        SQL sql = new SQL().SELECT("count(*)").FROM(TD_CONFIG);
        String configType = evt.getConfigType();//参数类型
        if (StringUtils.isNotBlank(configType)) {
            sql.WHERE("config_type=#{configType}");
        }
        String configKey = evt.getConfigKey();//参数key
        if (StringUtils.isNotBlank(configKey)) {
            sql.WHERE("config_key=#{configKey}");
        }
        Long type = evt.getType();//0 key信息 ;1 key-value信息
        if (type!=null) {
            sql.WHERE("type=#{type}");
        }
        sql.WHERE("status='E'");
        return sql.toString();
    }
    public String queryParmValuesByParam(QuerySysParmEvt evt) {
        /*SQL sql = new SQL().SELECT("ID as id,CONFIG_TYPE as configType,CONFIG_KEY as configKey," +
                "CONFIG_VALUE as configValue,CONFIG_DESC as configDesc,VALUE_DESC as valueDesc,SORT as sort," +
                "STATUS as status,OPERATOR as operator,CREATE_TIME as createTime,UPDATE_TIME as updateTime")
                .FROM(TD_CONFIG);*/
        SQL sql = new SQL().SELECT("*").FROM(TD_CONFIG);
        Long id = evt.getId();//参数id
        if (id!=null) {
            sql.WHERE("id=#{id}");
        }
        String configType = evt.getConfigType();//参数类型
        if (StringUtils.isNotBlank(configType)) {
            sql.WHERE("config_type=#{configType}");
        }
        String configKey = evt.getConfigKey();//参数key
        if (StringUtils.isNotBlank(configKey)) {
            sql.WHERE("config_key=#{configKey}");
        }
        Long type = evt.getType();//0 key信息 ;1 key-value信息
        if (type!=null) {
            sql.WHERE("type=#{type}");
        }
        sql.WHERE("status='E'");
        return sql.toString();
    }


}
