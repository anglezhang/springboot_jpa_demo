package cn.com.doone.tx.cloud.service.config.dao.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import cn.com.doone.tx.cloud.service.config.bean.ConfigChannelBean;
import cn.com.doone.tx.cloud.service.config.dao.provider.ConfigChannelProvider;
import cn.com.doone.tx.cloud.service.config.evt.channel.EditConfigChannelEvt;
import cn.com.doone.tx.cloud.service.config.evt.channel.EditConfigChannelStatusEvt;
import cn.com.doone.tx.cloud.service.config.evt.channel.QueryConfigChannelEvt;

/**
 * Created by APPLE on 2017/1/19.
 */
@Mapper
public interface ConfigChannelMapper {

    //查询渠道配置数量
    @SelectProvider(type = ConfigChannelProvider.class , method = "queryChannelCount")
    int queryChannelCount(QueryConfigChannelEvt queryConfigChannelEvt);

    //根据参数查询渠道配置
    @SelectProvider(type = ConfigChannelProvider.class , method = "queryConfigChannelByParam")
    List<ConfigChannelBean> queryConfigChannelByParam(QueryConfigChannelEvt queryConfigChannelEvt);

    /**查询所有渠道配置信息*/
    @SelectProvider(type = ConfigChannelProvider.class , method = "queryAllConfigChannels")
    List<ConfigChannelBean> queryAllConfigChannels(QueryConfigChannelEvt queryConfigChannelEvt);

    //编辑渠道配置
    @Update("UPDATE td_channel set channel_code=#{channelCode},CHANNEL_ASCRIPTION=#{channelAscription},SCREEN_TYPE=#{screenType}," +
            "EXTEND_TYPE=#{extendType},channel_name=#{channelName},channel_name_eng=#{channelNameEng}," +
            "channel_type=#{channelType},is_cms=#{isCms},operator=#{operator},update_time=#{updateTime} where id=#{id}")
    int doEdit(EditConfigChannelEvt editConfigChannelEvt);

    //编辑二级渠道配置
    @Update("UPDATE td_channel set channel_code=(SELECT t.cCode FROM (SELECT CONCAT((SELECT SUBSTR(c1.channel_code,1,14)" +
            " FROM td_channel c1 WHERE c1.ID=#{parentId}),(SELECT SUBSTR(c2.channel_code,15,10) " +
            "FROM td_channel c2 WHERE c2.ID=#{id})) cCode from dual) t)," +
            "CHANNEL_ASCRIPTION=#{channelAscription},SCREEN_TYPE=#{screenType}," +
            "channel_type=#{channelType},is_cms=#{isCms},operator=#{operator},update_time=#{updateTime} where ID=#{id}")
    int doEditExtend(EditConfigChannelEvt editConfigChannelEvt);

    //启用禁用渠道配置
    @Update("UPDATE td_channel set status=#{status},operator=#{operator},update_time=#{updateTime} where id=#{id}")
    int doEditStatus(EditConfigChannelStatusEvt editConfigChannelStatusEvt);


    //查询渠道配置数量
    @SelectProvider(type = ConfigChannelProvider.class , method = "isHasChannelCode")
    int isHasChannelCode(QueryConfigChannelEvt queryConfigChannelEvt);

    //查询渠道
    @SelectProvider(type = ConfigChannelProvider.class , method = "queryChannels")
    List<ConfigChannelBean>  queryChannels(QueryConfigChannelEvt queryConfigChannelEvt);

}
