package cn.com.doone.tx.cloud.service.config.dao.provider;


import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import cn.com.doone.tx.cloud.service.config.evt.channel.QueryConfigChannelEvt;



/**
 * Created by Administrator on 2017/1/19 0019.
 */
public class ConfigChannelProvider {

    private final String TBL_TD_CHANNEL = "td_channel";

    public String queryChannelCount(QueryConfigChannelEvt queryConfigChannelEvt) {
        SQL sql = new SQL().SELECT("count(*)").FROM(TBL_TD_CHANNEL);
        Long id = queryConfigChannelEvt.getId();
        if(id != null){
            sql.WHERE("id = #{id}");
        }
        Long parentId = queryConfigChannelEvt.getParentId();
        if(parentId != null){
            sql.WHERE("parent_id = #{parentId}");
        }else{
            sql.WHERE("parent_id = 0");
        }
        String channelCode = queryConfigChannelEvt.getChannelCode();
        if (channelCode != null && !"".equals(channelCode)) {
            sql.WHERE("channel_code = #{channelCode}");
        }
        String channelName = queryConfigChannelEvt.getChannelName();
        if (channelName != null && !"".equals(channelName)) {
            queryConfigChannelEvt.setChannelName("%"+channelName+"%");
            sql.WHERE("channel_name like #{channelName}");
        }
        String channelType = queryConfigChannelEvt.getChannelType();
        if (channelType != null && !"".equals(channelType)) {
            sql.WHERE("channel_type = #{channelType}");
        }
        String status = queryConfigChannelEvt.getStatus();
        if (status != null && !"".equals(status)) {
            sql.WHERE("status = #{status}");
        }
//        sql.WHERE("parent_id != -1");
        return sql.toString();
    }
    public String queryConfigChannelByParam(QueryConfigChannelEvt queryConfigChannelEvt) {
        SQL sql = new SQL().SELECT("*").FROM(TBL_TD_CHANNEL);
        Long id = queryConfigChannelEvt.getId();
        if(id != null){
            sql.WHERE("id = #{id}");
        }
        Long parentId = queryConfigChannelEvt.getParentId();
        if(parentId != null){
            sql.WHERE("parent_id = #{parentId}");
        }else{
            sql.WHERE("parent_id = 0");
        }
        String channelCode = queryConfigChannelEvt.getChannelCode();
        if (channelCode != null && !"".equals(channelCode)) {
            sql.WHERE("channel_code = #{channelCode}");
        }
        String channelName = queryConfigChannelEvt.getChannelName();
        if (channelName != null && !"".equals(channelName)) {
            queryConfigChannelEvt.setChannelName("%"+channelName+"%");
            sql.WHERE("channel_name like #{channelName}");
        }
        String channelType = queryConfigChannelEvt.getChannelType();
        if (channelType != null && !"".equals(channelType)) {
            sql.WHERE("channel_type = #{channelType}");
        }
        String status = queryConfigChannelEvt.getStatus();
        if (status != null && !"".equals(status)) {
            sql.WHERE("status = #{status}");
        }
//        sql.WHERE("parent_id != -1");
        return sql.toString();
    }
    /**查询所有渠道配置信息*/
    public String queryAllConfigChannels(QueryConfigChannelEvt queryConfigChannelEvt) {
        SQL sql = new SQL().SELECT("*").FROM(TBL_TD_CHANNEL);
        Long id = queryConfigChannelEvt.getId();
        if(id != null){
            sql.WHERE("id = #{id}");
        }
        Long parentId = queryConfigChannelEvt.getParentId();
        if(parentId != null){
            sql.WHERE("parent_id = #{parentId}");
        }else {
            //排除公共渠道
            sql.WHERE("parent_id <> -1");
        }
        String channelCode = queryConfigChannelEvt.getChannelCode();
        if (channelCode != null && !"".equals(channelCode)) {
            sql.WHERE("channel_code = #{channelCode}");
        }
        String channelName = queryConfigChannelEvt.getChannelName();
        if (channelName != null && !"".equals(channelName)) {
            queryConfigChannelEvt.setChannelName("%"+channelName+"%");
            sql.WHERE("channel_name like #{channelName}");
        }
        String channelType = queryConfigChannelEvt.getChannelType();
        if (channelType != null && !"".equals(channelType)) {
            sql.WHERE("channel_type = #{channelType}");
        }
        sql.WHERE("status = 'E'");
        sql.ORDER_BY("create_time asc");
        return sql.toString();
    }
    public String isHasChannelCode(QueryConfigChannelEvt queryConfigChannelEvt) {
        SQL sql = new SQL().SELECT("count(*)").FROM(TBL_TD_CHANNEL);
        Long id = queryConfigChannelEvt.getId();
        if(id != null){
            sql.WHERE("id != #{id}");
        }
        String channelCode = queryConfigChannelEvt.getChannelCode();
        if (channelCode != null && !"".equals(channelCode)) {
            sql.WHERE("channel_code = #{channelCode}");
        }
        return sql.toString();
    }

    public String queryChannels(QueryConfigChannelEvt queryConfigChannelEvt) {
        SQL sql = new SQL().SELECT("*").FROM(TBL_TD_CHANNEL);
        Long id = queryConfigChannelEvt.getId();
        if(id != null){
            sql.WHERE("id != #{id}");
        }else {
            String channelCode = queryConfigChannelEvt.getChannelCode();
            if (StringUtils.isNotBlank(channelCode)) {
                sql.WHERE("channel_code = #{channelCode}");
            }
            String channelName = queryConfigChannelEvt.getChannelName();
            if (StringUtils.isNotBlank(channelName)){
                sql.WHERE("channel_name = #{channelName}");
            }
        }
        return sql.toString();
    }

}
