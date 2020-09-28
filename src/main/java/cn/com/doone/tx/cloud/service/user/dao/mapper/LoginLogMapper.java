package cn.com.doone.tx.cloud.service.user.dao.mapper;


import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.com.doone.tx.cloud.service.user.dao.provider.LoginLogProvider;
import cn.com.doone.tx.cloud.service.user.evt.loginlog.EditLoginMidLogEvt;
import cn.com.doone.tx.cloud.service.user.evt.loginlog.QueryLoginLogEvt;
import cn.com.doone.tx.cloud.service.user.info.LoginLogChartInfo;
import cn.com.doone.tx.cloud.service.user.info.LoginLogInfo;
import cn.com.doone.tx.cloud.service.user.info.LoginStatusChannelInfo;


/**
 * Created by Administrator on 2017-5-22.
 */
public interface LoginLogMapper {
    @SelectProvider(type = LoginLogProvider.class , method = "queryLoginLogList")
    /*查询登录日志列表*/
    public List<LoginLogInfo> queryLoginLogList(QueryLoginLogEvt evt);
    
    /*查询登录日志列表数量*/
    @SelectProvider(type = LoginLogProvider.class , method = "queryLoginLogListCount")
    public int queryLoginLogListCount(QueryLoginLogEvt evt);

    /*查询登录日志列表*/
    @SelectProvider(type = LoginLogProvider.class , method = "queryLoginLogListForLoginLock")
    public List<LoginLogInfo> queryLoginLogListForLoginLock(QueryLoginLogEvt evt);

    /*统计登录次数和登录人数*/
    @SelectProvider(type = LoginLogProvider.class , method = "queryLoginUserDataByChannel")
    public List<LoginLogChartInfo> queryLoginUserDataByChannel(QueryLoginLogEvt evt);

    /*统计平台分布*/
    @SelectProvider(type = LoginLogProvider.class , method = "queryLoginPlatDataByChannel")
    public List<LoginLogChartInfo> queryLoginPlatDataByChannel(QueryLoginLogEvt evt);

    /*统计浏览器分布*/
    @SelectProvider(type = LoginLogProvider.class , method = "queryLoginBrowerDataByChannel")
    public List<LoginLogChartInfo> queryLoginBrowerDataByChannel(QueryLoginLogEvt evt);

    /*统计登录类型*/
    @SelectProvider(type = LoginLogProvider.class , method = "queryLoginTypeDataByChannel")
    public List<LoginLogChartInfo> queryLoginTypeDataByChannel(QueryLoginLogEvt evt);

    /*统计登录城市和渠道*/
    @SelectProvider(type = LoginLogProvider.class , method = "queryLoginChannelDataByCity")
    public List<LoginLogChartInfo> queryLoginChannelDataByCity(QueryLoginLogEvt evt);
    
    /*统计渠道登陆状态*/
    @SelectProvider(type = LoginLogProvider.class , method = "queryLoginStatusStatisticsByChannel")
    public List<LoginStatusChannelInfo> queryLoginStatusStatisticsByChannel(QueryLoginLogEvt evt);

    /*删除tl_mid_loginlog_user前一天的日志*/
    @UpdateProvider(type = LoginLogProvider.class , method = "deleteYesterdayLogForUser")
    public Integer deleteYesterdayLogForUser(EditLoginMidLogEvt evt);

    /*删除tl_mid_loginlog_browser前一天的日志*/
    @UpdateProvider(type = LoginLogProvider.class , method = "deleteYesterdayLogForBrowser")
    public Integer deleteYesterdayLogForBrowser(EditLoginMidLogEvt evt);

    /*删除tl_mid_loginlog_type前一天的日志*/
    @UpdateProvider(type = LoginLogProvider.class , method = "deleteYesterdayLogForType")
    public Integer deleteYesterdayLogForType(EditLoginMidLogEvt evt);

    /*删除tl_mid_loginlog_city前一天的日志*/
    @UpdateProvider(type = LoginLogProvider.class , method = "deleteYesterdayLogForCity")
    public Integer deleteYesterdayLogForCity(EditLoginMidLogEvt evt);
    
    /*删除tl_mid_login_status_channel前一天的日志*/
    @UpdateProvider(type = LoginLogProvider.class , method = "deleteYesterdayLogForLoginStatusStatistics")
    public Integer deleteYesterdayLogForLoginStatusStatistics(EditLoginMidLogEvt evt);
    

    /*tl_mid_loginlog_user插入前一天的日志*/
    @UpdateProvider(type = LoginLogProvider.class , method = "addMidLogDataForUser")
    public Integer addMidLogDataForUser(EditLoginMidLogEvt evt);

    /*tl_mid_loginlog_browser插入前一天的日志*/
    @UpdateProvider(type = LoginLogProvider.class , method = "addMidLogDataForBrowser")
    public Integer addMidLogDataForBrowser(EditLoginMidLogEvt evt);

    /*tl_mid_loginlog_type插入前一天的日志*/
    @UpdateProvider(type = LoginLogProvider.class , method = "addMidLogDataForType")
    public Integer addMidLogDataForType(EditLoginMidLogEvt evt);

    /*tl_mid_loginlog_city插入前一天的日志*/
    @UpdateProvider(type = LoginLogProvider.class , method = "addMidLogDataForCity")
    public Integer addMidLogDataForCity(EditLoginMidLogEvt evt);
    
    /*TL_MID_LOGIN_STATUS_CHANNEL插入前一天的日志*/
    @UpdateProvider(type = LoginLogProvider.class , method = "addMidLoginStatusStatisticsDataForChannel")
    public Integer addMidLoginStatusStatisticsDataForChannel(EditLoginMidLogEvt evt);

    //添加分区
    @UpdateProvider(type = LoginLogProvider.class , method = "addPartition")
    public Integer addPartition(EditLoginMidLogEvt evt);

    //添加分区
    @SelectProvider(type = LoginLogProvider.class , method = "selectPartitionCount")
    public Integer selectPartitionCount(EditLoginMidLogEvt evt);
}
