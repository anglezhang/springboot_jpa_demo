package cn.com.doone.tx.cloud.service.user.dao.provider;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import cn.com.doone.tx.cloud.service.user.evt.loginlog.EditLoginMidLogEvt;
import cn.com.doone.tx.cloud.service.user.evt.loginlog.QueryLoginLogEvt;
import cn.com.doone.tx.cloud.tool.service.common.util.SqlGenerateUtil;

/**
 * Created by Administrator on 2017-5-11.
 */
public class LoginLogProvider {
    private String TBL_LOGIN_LOG = "tl_login_log";
    private String TBL_LOGIN_LOG_BROWSER = "tl_mid_loginlog_browser";
    private String TBL_LOGIN_LOG_USER = "tl_mid_loginlog_user";
    private String TBL_LOGIN_LOG_TYPE = "tl_mid_loginlog_type";
    private String TBL_LOGIN_LOG_CITY = "tl_mid_loginlog_city";
    private String TBL_LOGIN_STATUS_CHANNEL = "tl_mid_login_status_channel";

    public String queryLoginLogList(QueryLoginLogEvt evt) {
        String selectCols = " t1.ID as id,t1.ACCOUNT as account,t1.ACCOUNT_TYPE as accountType,t1.LOGIN_TYPE as loginType,"
        		+ "t1.ACCOUNT_ID as accountId,t1.ACCOUNT_NAME as accountName,t1.ACC_NBR_TYPE as accNbrType,"
        		+ "t1.CITY_CODE as cityCode,t1.LOGIN_IP as loginIp,t1.LOGIN_CHANNEL_CODE as loginChannelCode,"
        		+ "t1.CHANNEL_CODE as channelCode,t1.LOGIN_CLIENT as loginClient,t1.LOGIN_BROWSER as loginBrowser,"
        		+ "t1.DEVICE_NO as deviceNo,t1.LOGIN_STATUS as loginStatus,t1.LOGIN_RESULT as loginResult,t1.LOGIN_TIME as loginTime";
        SQL sql = new SQL().SELECT_DISTINCT(selectCols).FROM(TBL_LOGIN_LOG + " t1");
//        sql.LEFT_OUTER_JOIN("mul_language t2 ON t1.LOGIN_CHANNEL_CODE = t2.CODE AND t2.STATUS = 'E' AND t2.LANGUAGE_TYPE = #{languageType}");
//        AddQueryConfigListSqlRef(sql, evt);
        if(StringUtils.isNotBlank(evt.getStartDate())){
            sql.WHERE(SqlGenerateUtil.dateTimeToChar("t1.LOGIN_TIME")+">=#{startDate}");
        }
        if(StringUtils.isNotBlank(evt.getEndDate())){
            sql.WHERE(SqlGenerateUtil.dateTimeToChar("t1.LOGIN_TIME")+"<=#{endDate}");
        }
        String loginAccount = evt.getAccount();
        if(StringUtils.isNotBlank(loginAccount)) {
            evt.setAccount("%" + loginAccount + "%");
            sql.WHERE("t1.ACCOUNT like #{account}");
        }
        String accountType = evt.getAccountType();
        if (StringUtils.isNotBlank(accountType)) {
			sql.WHERE("t1.ACCOUNT_TYPE = #{accountType}");
		}
//        String languageType = evt.getLanguageType();
//        if (StringUtils.isNotBlank(languageType)) {
//			sql.WHERE("t2.LANGUAGE_TYPE = #{languageType}");
//		}
        sql.WHERE("t1.STATUS='E'");
        sql.ORDER_BY("t1.LOGIN_TIME DESC");
        return sql.toString();
    }
    
    public String queryLoginLogListForLoginLock(QueryLoginLogEvt evt) {
        String selectCols = " t1.ID as id,t1.ACCOUNT as account,t1.ACCOUNT_TYPE as accountType,t1.LOGIN_TYPE as loginType,"
        		+ "t1.ACCOUNT_ID as accountId,t1.ACCOUNT_NAME as accountName,t1.ACC_NBR_TYPE as accNbrType,"
        		+ "t1.CITY_CODE as cityCode,t1.LOGIN_IP as loginIp,t1.LOGIN_CHANNEL_CODE as loginChannelCode,"
        		+ "t1.CHANNEL_CODE as channelCode,t1.LOGIN_CLIENT as loginClient,t1.LOGIN_BROWSER as loginBrowser,"
        		+ "t1.DEVICE_NO as deviceNo,t1.LOGIN_STATUS as loginStatus,t1.LOGIN_RESULT as loginResult,t1.LOGIN_TIME as loginTime";
        SQL sql = new SQL().SELECT(selectCols).FROM(TBL_LOGIN_LOG + " t1");
//        sql.LEFT_OUTER_JOIN("mul_language t2 ON t1.LOGIN_CHANNEL_CODE = t2.CODE AND t2.STATUS = 'E'");
//        AddQueryConfigListSqlRef(sql, evt);
        if(StringUtils.isNotBlank(evt.getStartDate())){
            sql.WHERE(SqlGenerateUtil.dateTimeToChar("t1.LOGIN_TIME")+">=#{startDate}");
        }
        if(StringUtils.isNotBlank(evt.getEndDate())){
            sql.WHERE(SqlGenerateUtil.dateTimeToChar("t1.LOGIN_TIME")+"<=#{endDate}");
        }
        String loginAccount = evt.getAccount();
        if(StringUtils.isNotBlank(loginAccount)) {
            sql.WHERE("t1.ACCOUNT = #{account}");
        }
        String accountType = evt.getAccountType();
        if (StringUtils.isNotBlank(accountType)) {
			sql.WHERE("t1.ACCOUNT_TYPE = #{accountType}");
		}
//        String languageType = evt.getLanguageType();
        
        sql.WHERE("t1.STATUS='E'");
        sql.ORDER_BY("t1.LOGIN_TIME DESC");
        return sql.toString();
    }

    public String queryLoginLogListCount(QueryLoginLogEvt evt) {
        String selectCols = "count(DISTINCT t1.ID)";
//        SQL sql = new SQL().SELECT(selectCols).FROM(TBL_LOGIN_LOG);
//        AddQueryConfigListSqlRef(sql, evt);
        SQL sql = new SQL().SELECT_DISTINCT(selectCols).FROM(TBL_LOGIN_LOG + " t1");
//        sql.LEFT_OUTER_JOIN("mul_language t2 ON t1.LOGIN_CHANNEL_CODE = t2.CODE AND t2.STATUS = 'E' AND t2.LANGUAGE_TYPE = #{languageType}");
//        AddQueryConfigListSqlRef(sql, evt);
        if(StringUtils.isNotBlank(evt.getStartDate())){
            sql.WHERE(SqlGenerateUtil.dateTimeToChar("t1.LOGIN_TIME")+">=#{startDate}");
        }
        if(StringUtils.isNotBlank(evt.getEndDate())){
            sql.WHERE(SqlGenerateUtil.dateTimeToChar("t1.LOGIN_TIME")+"<=#{endDate}");
        }
        String loginAccount = evt.getAccount();
        if(StringUtils.isNotBlank(loginAccount)) {
            evt.setAccount("%" + loginAccount + "%");
            sql.WHERE("t1.ACCOUNT like #{account}");
        }
        String accountType = evt.getAccountType();
        if (StringUtils.isNotBlank(accountType)) {
			sql.WHERE("t1.ACCOUNT_TYPE = #{accountType}");
		}
//        String languageType = evt.getLanguageType();
//        if (StringUtils.isNotBlank(languageType)) {
//			sql.WHERE("t2.LANGUAGE_TYPE = #{languageType}");
//		}
        sql.WHERE("t1.STATUS='E'");
        sql.ORDER_BY("LOGIN_TIME DESC");
        return sql.toString();
    }

    public String queryLoginUserDataByChannel(QueryLoginLogEvt evt){
        String selectCols = "sum(login_num) loginTimes,sum(login_user_num) loginUserCount,channel_code channelCode";
        SQL sql = new SQL().SELECT(selectCols).FROM(TBL_LOGIN_LOG_USER);
        AddQueryConfigListSqlRef(sql, evt);
        sql.GROUP_BY("CHANNEL_CODE");
        sql.ORDER_BY("CHANNEL_CODE");
        return  sql.toString();
    }

    public String queryLoginPlatDataByChannel(QueryLoginLogEvt evt){
        String selectCols = "sum(mobile_num) mobileNum, sum(pc_num) pcNum ,channel_code channelCode";
        SQL sql = new SQL().SELECT(selectCols).FROM(TBL_LOGIN_LOG_USER);
        AddQueryConfigListSqlRef(sql, evt);
        sql.GROUP_BY("CHANNEL_CODE");
        sql.ORDER_BY("CHANNEL_CODE");
        return  sql.toString();
    }

    public String queryLoginBrowerDataByChannel(QueryLoginLogEvt evt){
        String selectCols = "LOGIN_BROWSER loginBrowser , sum(login_num) loginCount,channel_code channelCode";
        SQL sql = new SQL().SELECT(selectCols).FROM(TBL_LOGIN_LOG_BROWSER);
        AddQueryConfigListSqlRef(sql, evt);
        sql.GROUP_BY("channel_code,LOGIN_BROWSER");
        sql.ORDER_BY("CHANNEL_CODE");
        return  sql.toString();
    }

    public String queryLoginTypeDataByChannel(QueryLoginLogEvt evt){
        String selectCols = "LOGIN_TYPE loginType,sum(login_num) loginCount,channel_code channelCode";
        SQL sql = new SQL().SELECT(selectCols).FROM(TBL_LOGIN_LOG_TYPE);
        AddQueryConfigListSqlRef(sql, evt);
        sql.GROUP_BY("channel_code,LOGIN_TYPE");
        sql.ORDER_BY("CHANNEL_CODE");
        return  sql.toString();
    }

    public String queryLoginChannelDataByCity(QueryLoginLogEvt evt){
        String selectCols = "city_code cityCode , sum(login_num) loginCount,channel_code channelCode";
        SQL sql = new SQL().SELECT(selectCols).FROM(TBL_LOGIN_LOG_CITY);
        AddQueryConfigListSqlRef(sql, evt);
        sql.GROUP_BY("channel_code,city_code");
        sql.ORDER_BY("city_code");
        return  sql.toString();
    }

    private void AddQueryConfigListSqlRef(SQL sql, QueryLoginLogEvt evt) {
        if(StringUtils.isNotBlank(evt.getStartDate())){
            sql.WHERE(SqlGenerateUtil.dateToChar("LOGIN_TIME")+">=#{startDate}");
        }
        if(StringUtils.isNotBlank(evt.getEndDate())){
            sql.WHERE(SqlGenerateUtil.dateToChar("LOGIN_TIME")+"<=#{endDate}");
        }
        if(StringUtils.isNotBlank(evt.getChannelCode())){
            sql.WHERE("CHANNEL_CODE = #{channelCode}");
        }
        if(StringUtils.isNotBlank(evt.getAccNbrType())){
            sql.WHERE("ACC_NBR_TYPE = #{accNbrType}");
        }
        String loginAccount = evt.getAccount();
        if(StringUtils.isNotBlank(loginAccount)) {
            evt.setAccount("%" + loginAccount + "%");
            sql.WHERE("ACCOUNT like #{account}");
        }
        if(StringUtils.isNotBlank(evt.getAccountAll())){
            sql.WHERE("ACCOUNT = #{accountAll}");
        }
        if(StringUtils.isNotBlank(evt.getAccountType())){
            sql.WHERE("ACCOUNT_TYPE = #{accountType}");
        }
        if(StringUtils.isNotBlank(evt.getCityCode())){
            sql.WHERE("CITY_CODE = #{cityCode}");
        }
        if(StringUtils.isNotBlank(evt.getCityCode())){
            sql.WHERE("CITY_CODE = #{cityCode}");
        }
        if(StringUtils.isNotBlank(evt.getLoginStatus())){
            sql.WHERE("LOGIN_STATUS = #{loginStatus}");
        }
        if(StringUtils.isNotBlank(evt.getLoginType())){
            sql.WHERE("LOGIN_TYPE = #{loginType}");
        }
        //D为删除状态
        sql.WHERE("status!='D'");
    }

    //删除数据
    private String deleteMidLogDataByTime(EditLoginMidLogEvt evt, String tableName){
        SQL sql = new SQL().UPDATE(tableName);
        sql.SET("STATUS='D'");
        sql.WHERE("(STATUS='E')");
//        sql.WHERE(SqlStrUtil.getDateNoSeparator("LOGIN_TIME")+"=#{yesterDayDateStr}");
        return sql.toString();
    }
    //删除user中间表
    public String deleteYesterdayLogForUser(EditLoginMidLogEvt evt){
        return deleteMidLogDataByTime(evt,TBL_LOGIN_LOG_USER);
    }
    public String deleteYesterdayLogForBrowser(EditLoginMidLogEvt evt){
        return deleteMidLogDataByTime(evt,TBL_LOGIN_LOG_BROWSER);
    }
    public String deleteYesterdayLogForType(EditLoginMidLogEvt evt){
        return deleteMidLogDataByTime(evt,TBL_LOGIN_LOG_TYPE);
    }
    public String deleteYesterdayLogForCity(EditLoginMidLogEvt evt){
        return deleteMidLogDataByTime(evt,TBL_LOGIN_LOG_CITY);
    }
    public String deleteYesterdayLogForLoginStatusStatistics(EditLoginMidLogEvt evt){
        return deleteMidLogDataByTime(evt,TBL_LOGIN_STATUS_CHANNEL);
    }
    //中间表插入数据
    public String addMidLogDataForUser(EditLoginMidLogEvt evt){
        String idKey = " ";
        String idValue = " ";
        String as = " AS T ";
        String login_time = evt.getYesterDayDateStr();
        if(SqlGenerateUtil.isOracle()) {//oracle
            as=" ";
            idKey = " id, ";
            idValue = "s_tl_mid_loginlog_user.nextval,";
            login_time =  "TO_DATE(\'column\',\'yyyyMMdd\')".replace("column",evt.getYesterDayDateStr());
        }
        String sql =  "insert into TL_MID_LOGINLOG_USER"
                +"("+idKey+"status,channel_code,login_num, login_time, login_user_num,mobile_num,pc_num,CREATE_TIME)"
                +" select "+idValue+" 'E',channel_code, login_num, "+login_time+","
                +" login_user_num,mobile_num,pc_num,"+SqlGenerateUtil.getNow()
                +" from (SELECT channel_code,COUNT(1) login_num,COUNT(DISTINCT(account_Id)) login_user_num,"
                +" count(CASE WHEN log.LOGIN_CLIENT = 'pc' THEN 1 END) mobile_num,"
                +" count(CASE WHEN log.LOGIN_CLIENT = 'mobile' THEN 1 END) pc_num"
                +" FROM cloud_user.tl_login_log log"
                +" where CHANNEL_CODE is not null"
                +" and "+SqlGenerateUtil.toCharFmt("LOGIN_TIME", "yyyyMMdd")+" = #{yesterDayDateStr}"
                +" AND LOGIN_STATUS = 'S'"
                +" AND STATUS = 'E'"
                +" GROUP BY CHANNEL_CODE)"+as;
        return sql;
    }

    public String addMidLogDataForBrowser(EditLoginMidLogEvt evt){
        String idKey = " ";
        String idValue = " ";
        String as = " AS T ";
        String login_time = evt.getYesterDayDateStr();
        String ifNull = " ifnull";
        if(SqlGenerateUtil.isOracle()) {//oracle
            idKey = " id, ";
            idValue = "s_tl_mid_loginlog_browser.nextval,";
            ifNull = " nvl";
            login_time =  "TO_DATE(column,\'yyyyMMdd\')".replace("column",evt.getYesterDayDateStr());
            as=" ";
        }
        String sql =  "insert into tl_mid_loginlog_browser"
                +"("+idKey+"status,channel_code,login_num, login_time,login_browser,CREATE_TIME)"
                +" select "+idValue+" 'E',channel_code, login_num, "+login_time+","
                +" login_browser,"+SqlGenerateUtil.getNow()
                +" from (SELECT channel_code,COUNT(1) login_num,"+ifNull+"(LOGIN_BROWSER,'其他') login_browser"
                +" FROM cloud_user.tl_login_log log"
                +" where CHANNEL_CODE is not null"
                +" and "+SqlGenerateUtil.toCharFmt("LOGIN_TIME", "yyyyMMdd")+" = #{yesterDayDateStr}"
                +" AND LOGIN_STATUS = 'S'"
                +" AND STATUS = 'E'"
                +" GROUP BY CHANNEL_CODE,LOGIN_BROWSER)"+as;
        return sql;
    }

    public String addMidLogDataForType(EditLoginMidLogEvt evt){
        String idKey = " ";
        String idValue = " ";
        String as = " AS T ";
        String login_time = evt.getYesterDayDateStr();
        String ifNull = " ifnull";
        if(SqlGenerateUtil.isOracle()) {//oracle
            idKey = " id, ";
            idValue = "s_tl_mid_loginlog_type.nextval,";
            ifNull = " nvl";
            login_time =  "TO_DATE(column,\'yyyyMMdd\')".replace("column",evt.getYesterDayDateStr());
            as=" ";
        }
        String sql =  "insert into tl_mid_loginlog_type"
                +"("+idKey+"status,channel_code,login_num, login_time,login_type,CREATE_TIME)"
                +" select "+idValue+" 'E',channel_code, login_num, "+login_time+","
                +" login_type,"+SqlGenerateUtil.getNow()
                +" from (SELECT channel_code,COUNT(1) login_num,"+ifNull+"(login_type,'') login_type"
                +" FROM cloud_user.tl_login_log log"
                +" where CHANNEL_CODE is not null"
                +" and "+SqlGenerateUtil.toCharFmt("LOGIN_TIME", "yyyyMMdd")+" = #{yesterDayDateStr}"
                +" AND LOGIN_STATUS = 'S'"
                +" AND STATUS = 'E'"
                +" GROUP BY CHANNEL_CODE,login_type)"+as;
        return sql;
    }

    public String addMidLogDataForCity(EditLoginMidLogEvt evt){
        String idKey = " ";
        String idValue = " ";
        String as = " AS T ";
        String login_time = evt.getYesterDayDateStr();
        String ifNull = " ifnull";
        if(SqlGenerateUtil.isOracle()) {//oracle
            idKey = " id, ";
            idValue = "s_tl_mid_loginlog_city.nextval,";
            ifNull = " nvl";
            login_time =  "TO_DATE(column,\'yyyyMMdd\')".replace("column",evt.getYesterDayDateStr());
            as=" ";
        }
        String sql =  "insert into tl_mid_loginlog_city"
                +"("+idKey+"status,channel_code,login_num, login_time,city_code,CREATE_TIME)"
                +" select "+idValue+" 'E',channel_code, login_num, "+login_time+","
                +" city_code,"+SqlGenerateUtil.getNow()
                +" from (SELECT channel_code,COUNT(1) login_num,"+ifNull+"(city_code,'') city_code"
                +" FROM cloud_user.tl_login_log log"
                +" where CHANNEL_CODE is not null"
                +" and "+SqlGenerateUtil.toCharFmt("LOGIN_TIME", "yyyyMMdd")+" = #{yesterDayDateStr}"
                +" AND LOGIN_STATUS = 'S'"
                +" AND STATUS = 'E'"
                +" GROUP BY CHANNEL_CODE,city_code)"+as;
        return sql;
    }
    
    //中间表插入渠道登录统计数据
    public String addMidLoginStatusStatisticsDataForChannel(EditLoginMidLogEvt evt){
        String idKey = " ";
        String idValue = " ";
        String as = " AS T ";
        String login_time = evt.getYesterDayDateStr();
        String selectSql = "CHANNEL_CODE,COUNT(LOGIN_STATUS='S' OR NULL) AS SUCCESS_NUM,COUNT(LOGIN_STATUS='F' OR NULL) AS FAIL_NUM,";
        if(SqlGenerateUtil.isOracle()) {//oracle
            as=" ";
            idKey = " id, ";
            idValue = "S_TL_MID_LOGIN_STATUS_CHANNEL.nextval,";
            login_time =  "TO_DATE(\'column\',\'yyyyMMdd\')".replace("column",evt.getYesterDayDateStr());
            selectSql = "CHANNEL_CODE,SUM(CASE WHEN LOGIN_STATUS = 'S' THEN 1 ELSE 0 END) SUCCESS_NUM,SUM(CASE WHEN LOGIN_STATUS = 'F' THEN 1 ELSE 0 END) FAIL_NUM,";
        }
        String sql =  "insert into TL_MID_LOGIN_STATUS_CHANNEL"
                +"("+idKey+"status,channel_code,success_num,fail_num, login_time,CREATE_TIME)"
                +" select "+idValue+" 'E',"+selectSql+login_time+","+SqlGenerateUtil.getNow()
                +" FROM cloud_user.tl_login_log log"
                +" where CHANNEL_CODE is not null"
                +" and "+SqlGenerateUtil.toCharFmt("LOGIN_TIME", "yyyyMMdd")+" = #{yesterDayDateStr}"
                +" AND STATUS = 'E'"
                +" AND LOGIN_TYPE = '2'"
                +" GROUP BY CHANNEL_CODE";
        return sql;
    }

    //添加分区
    public String addPartition(EditLoginMidLogEvt evt){
        String sql = "ALTER TABLE "+ evt.getTableName()
                +" ADD PARTITION(" +
                " PARTITION p"+evt.getYesterDayDateStr()+
                " VALUES " +
                " LESS THAN(" +
                " TO_DAYS('"+evt.getYesterDayDateStr()+"')+1" +
                " )" +
                " )";
        return sql;
    }

    //查询分区是否存在
    public String selectPartitionCount(EditLoginMidLogEvt evt){
        String sql = "SELECT count(1) " +
                " FROM " +
                " INFORMATION_SCHEMA.PARTITIONS " +
                " WHERE " +
                " TABLE_NAME = '"+ evt.getTableName() +"'"+
                " AND partition_name = 'p"+evt.getYesterDayDateStr()+"'";
        return sql;
    }
    
    //统计用户登录状态
    public String queryLoginStatusStatisticsByChannel(QueryLoginLogEvt evt){
    	SQL sql = new SQL().SELECT("sum(fail_num) fail_num,sum(success_num) success_num,channel_code").FROM("TL_MID_LOGIN_STATUS_CHANNEL");
    	 if(StringUtils.isNotBlank(evt.getStartDate())){
             sql.WHERE(SqlGenerateUtil.dateToChar("LOGIN_TIME")+">=#{startDate}");
         }
         if(StringUtils.isNotBlank(evt.getEndDate())){
             sql.WHERE(SqlGenerateUtil.dateToChar("LOGIN_TIME")+"<=#{endDate}");
         }
         sql.WHERE("STATUS='E'");
         sql.GROUP_BY("channel_code");
    	return sql.toString();
    }
}

