package cn.com.doone.tx.cloud.service.user.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.doone.tx.cloud.service.user.bean.LoginLogBean;
import cn.com.doone.tx.cloud.service.user.dao.mapper.LoginLogMapper;
import cn.com.doone.tx.cloud.service.user.evt.loginlog.AddLoginLogEvt;
import cn.com.doone.tx.cloud.service.user.evt.loginlog.EditLoginMidLogEvt;
import cn.com.doone.tx.cloud.service.user.evt.loginlog.QueryLoginLogEvt;
import cn.com.doone.tx.cloud.service.user.info.LoginLogChartInfo;
import cn.com.doone.tx.cloud.service.user.info.LoginLogInfo;
import cn.com.doone.tx.cloud.service.user.info.LoginStatusChannelInfo;
import cn.com.doone.tx.cloud.tool.common.invoke.BaseService;
import cn.com.doone.tx.cloud.tool.hibernate.repository.WebRepository;
import cn.com.doone.tx.cloud.tool.service.common.util.SqlGenerateUtil;

/**
 * Created by Administrator on 2017-5-22.
 */
@Service("LoginLogService")
public class LoginLogService extends BaseService {
    @Autowired
    private LoginLogMapper loginLogMapper;

    @Autowired
    private WebRepository webRepository;

    public List<LoginLogInfo> queryLoginLogList(QueryLoginLogEvt evt) {
    	evt.startPage();
        return loginLogMapper.queryLoginLogList(evt);
    }

    public int queryLoginLogListCount(QueryLoginLogEvt evt) {
        return loginLogMapper.queryLoginLogListCount(evt);
    }
    
    public List<LoginLogInfo> queryLoginLogListForLoginLock(QueryLoginLogEvt evt) {
//        return new ArrayList<LoginLogInfo>();
        return loginLogMapper.queryLoginLogListForLoginLock(evt);
    }

    /*统计登录次数和登录人数*/
    public List<LoginLogChartInfo> queryLoginUserDataByChannel(QueryLoginLogEvt evt) {
        return loginLogMapper.queryLoginUserDataByChannel(evt);
    }

    /*统计平台分布*/
    public List<LoginLogChartInfo> queryLoginPlatDataByChannel(QueryLoginLogEvt evt) {
        return loginLogMapper.queryLoginPlatDataByChannel(evt);
    }

    /*统计浏览器分布*/
    public List<LoginLogChartInfo> queryLoginBrowerDataByChannel(QueryLoginLogEvt evt) {
        return loginLogMapper.queryLoginBrowerDataByChannel(evt);
    }

    /*统计登录类型*/
    public List<LoginLogChartInfo> queryLoginTypeDataByChannel(QueryLoginLogEvt evt) {
        return loginLogMapper.queryLoginTypeDataByChannel(evt);
    }

    /*统计登录城市和渠道*/
    public List<LoginLogChartInfo> queryLoginChannelDataByCity(QueryLoginLogEvt evt) {
        return loginLogMapper.queryLoginChannelDataByCity(evt);
    }
    
    /*统计渠道登陆状态*/
    public List<LoginStatusChannelInfo> queryLoginStatusStatisticsByChannel(QueryLoginLogEvt evt) {
        return loginLogMapper.queryLoginStatusStatisticsByChannel(evt);
    }

    /*tl_mid_loginlog_user插入前一天的日志*/
    public Integer addMidLogDataForUser(EditLoginMidLogEvt evt) {
        loginLogMapper.deleteYesterdayLogForUser(evt);
        return loginLogMapper.addMidLogDataForUser(evt);
    }

    /*tl_mid_loginlog_browser插入前一天的日志*/
    public Integer addMidLogDataForBrowser(EditLoginMidLogEvt evt) {
        loginLogMapper.deleteYesterdayLogForBrowser(evt);
        return loginLogMapper.addMidLogDataForBrowser(evt);
    }

    /*tl_mid_loginlog_type插入前一天的日志*/
    public Integer addMidLogDataForType(EditLoginMidLogEvt evt) {
        loginLogMapper.deleteYesterdayLogForType(evt);
        return loginLogMapper.addMidLogDataForType(evt);
    }

    /*tl_mid_loginlog_city插入前一天的日志*/
    public Integer addMidLogDataForCity(EditLoginMidLogEvt evt) {
        loginLogMapper.deleteYesterdayLogForCity(evt);
        return loginLogMapper.addMidLogDataForCity(evt);
    }
    
    /*tl_mid_login_status_channel插入前一天的日志*/
    public Integer addMidLoginStatusStatisticsDataForChannel(EditLoginMidLogEvt evt) {
        loginLogMapper.deleteYesterdayLogForLoginStatusStatistics(evt);
        return loginLogMapper.addMidLoginStatusStatisticsDataForChannel(evt);
    }

    //修改定时器日志
//    public ServerResp<Object> doEditTimingLog(Map<String, Object> param) {
//        return serviceRestUtil.post(TIMING_SERVICE, "timingLog/doEdit", param);
//    }


    //添加分区
    public Integer addPartition(EditLoginMidLogEvt evt){
        if(!SqlGenerateUtil.isOracle()) {//orcale可以按天自动创建表分区
            if (selectPartitionCount(evt) < 1) {//无分区则创建
                return loginLogMapper.addPartition(evt);
            }
        }
        return 0;
    }

    //添加分区
    public Integer selectPartitionCount(EditLoginMidLogEvt evt){
        return loginLogMapper.selectPartitionCount(evt);
    }
    
    //保存登录日志
    public LoginLogBean addLoginLog(AddLoginLogEvt addLoginLogEvt){
        LoginLogBean loginLogBean = new LoginLogBean();
        BeanUtils.copyProperties(addLoginLogEvt,loginLogBean);
        loginLogBean.setLoginTime(new Date());
        loginLogBean.setStatus("E");
        return webRepository.save(loginLogBean);
    }
}
