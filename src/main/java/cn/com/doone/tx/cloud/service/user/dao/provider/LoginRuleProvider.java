package cn.com.doone.tx.cloud.service.user.dao.provider;


import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import cn.com.doone.tx.cloud.service.user.evt.loginrule.EditLoginRuleEvt;
import cn.com.doone.tx.cloud.service.user.evt.loginrule.QueryLoginRuleEvt;

public class LoginRuleProvider {
    
    private final String MAIN_TABLE_NAME = "LOGIN_RULE";
    
    public String queryCount(QueryLoginRuleEvt evt) {
       SQL sql = new SQL().SELECT("count(*)").FROM(MAIN_TABLE_NAME);
       Long id = evt.getId();
       if(id!= null){
          sql.WHERE("ID = #{id}");
       }
       String applyType = evt.getApplyType();
       if(applyType != null && !"".equals(applyType)){
          sql.WHERE("APPLY_TYPE = #{applyType}");
       }
       String iniPwd = evt.getIniPwd();
       if(iniPwd != null && !"".equals(iniPwd)){
          sql.WHERE("INI_PWD = #{iniPwd}");
       }
       String minPwd = evt.getMinPwd();
       if(minPwd != null && !"".equals(minPwd)){
          sql.WHERE("MIN_PWD = #{minPwd}");
       }
       String maxPwd = evt.getMaxPwd();
       if(maxPwd != null && !"".equals(maxPwd)){
          sql.WHERE("MAX_PWD = #{maxPwd}");
       }
       String ruleType = evt.getRuleType();
       if(ruleType != null && !"".equals(ruleType)){
          sql.WHERE("RULE_TYPE = #{ruleType}");
       }
       String lockRuleTime = evt.getLockRuleTime();
       if(lockRuleTime != null && !"".equals(lockRuleTime)){
          sql.WHERE("LOCK_RULE_TIME = #{lockRuleTime}");
       }
       String lockRuleTimeUnit = evt.getLockRuleTimeUnit();
       if(lockRuleTimeUnit != null && !"".equals(lockRuleTimeUnit)){
          sql.WHERE("LOCK_RULE_TIME_UNIT = #{lockRuleTimeUnit}");
       }
       String maxError = evt.getMaxError();
       if(maxError != null && !"".equals(maxError)){
          sql.WHERE("MAX_ERROR = #{maxError}");
       }
       String lockTime = evt.getLockTime();
       if(lockTime != null && !"".equals(lockTime)){
          sql.WHERE("LOCK_TIME = #{lockTime}");
       }
       String lockTimeRule = evt.getLockTimeRule();
       if(lockTimeRule != null && !"".equals(lockTimeRule)){
          sql.WHERE("LOCK_TIME_RULE = #{lockTimeRule}");
       }
       String pwdType = evt.getPwdType();
       if(pwdType != null && !"".equals(pwdType)){
          sql.WHERE("PWD_TYPE = #{pwdType}");
       }
       Integer numLength = evt.getNumLength();
       if(numLength != null){
          sql.WHERE("NUM_LENGTH = #{numLength}");
       }
       String status = evt.getStatus();
       if(status != null && !"".equals(status)){
          sql.WHERE("STATUS = #{status}");
       }
       //D为不在用状态
       sql.WHERE("status='E'");
       return sql.toString();
    }
    
    public String queryList(QueryLoginRuleEvt evt) {
        StringBuilder sqlStr = new StringBuilder();
        sqlStr.append("t1.ID,");
        sqlStr.append("t1.APPLY_TYPE,");
        sqlStr.append("t1.INI_PWD,");
        sqlStr.append("t1.MIN_PWD,");
        sqlStr.append("t1.MAX_PWD,");
        sqlStr.append("t1.RULE_TYPE,");
        sqlStr.append("t1.LOCK_RULE_TIME,");
        sqlStr.append("t1.LOCK_RULE_TIME_UNIT,");
        sqlStr.append("t1.MAX_ERROR,");
        sqlStr.append("t1.LOCK_TIME,");
        sqlStr.append("t1.LOCK_TIME_RULE,");
        sqlStr.append("t1.PWD_TYPE,");
        sqlStr.append("t1.NUM_LENGTH,");
        sqlStr.append("t1.STATUS,");
        sqlStr.append("t1.CREATOR,");
        sqlStr.append("t1.CREATE_TIME,");
        sqlStr.append("t1.OPERATOR,");
        sqlStr.append("t1.UPDATE_TIME");
        SQL sql = new SQL().SELECT(sqlStr.toString()).FROM(MAIN_TABLE_NAME+" t1");
        
        Long id = evt.getId();
        if(id!= null){
            sql.WHERE("t1.ID = #{id}");
        }
        String applyType = evt.getApplyType();
        if(applyType != null && !"".equals(applyType)){
          evt.setApplyType(applyType);
          sql.WHERE("t1.APPLY_TYPE = #{applyType}");
        }
        String iniPwd = evt.getIniPwd();
        if(iniPwd != null && !"".equals(iniPwd)){
          evt.setIniPwd(iniPwd);
          sql.WHERE("t1.INI_PWD = #{iniPwd}");
        }
        String minPwd = evt.getMinPwd();
        if(minPwd != null && !"".equals(minPwd)){
          evt.setMinPwd(minPwd);
          sql.WHERE("t1.MIN_PWD = #{minPwd}");
        }
        String maxPwd = evt.getMaxPwd();
        if(maxPwd != null && !"".equals(maxPwd)){
          evt.setMaxPwd(maxPwd);
          sql.WHERE("t1.MAX_PWD = #{maxPwd}");
        }
        String ruleType = evt.getRuleType();
        if(ruleType != null && !"".equals(ruleType)){
          evt.setRuleType(ruleType);
          sql.WHERE("t1.RULE_TYPE = #{ruleType}");
        }
        String lockRuleTime = evt.getLockRuleTime();
        if(lockRuleTime != null && !"".equals(lockRuleTime)){
          evt.setLockRuleTime(lockRuleTime);
          sql.WHERE("t1.LOCK_RULE_TIME = #{lockRuleTime}");
        }
        String lockRuleTimeUnit = evt.getLockRuleTimeUnit();
        if(lockRuleTimeUnit != null && !"".equals(lockRuleTimeUnit)){
          evt.setLockRuleTimeUnit(lockRuleTimeUnit);
          sql.WHERE("t1.LOCK_RULE_TIME_UNIT = #{lockRuleTimeUnit}");
        }
        String maxError = evt.getMaxError();
        if(maxError != null && !"".equals(maxError)){
          evt.setMaxError(maxError);
          sql.WHERE("t1.MAX_ERROR = #{maxError}");
        }
        String lockTime = evt.getLockTime();
        if(lockTime != null && !"".equals(lockTime)){
          evt.setLockTime(lockTime);
          sql.WHERE("t1.LOCK_TIME = #{lockTime}");
        }
        String lockTimeRule = evt.getLockTimeRule();
        if(lockTimeRule != null && !"".equals(lockTimeRule)){
          evt.setLockTimeRule(lockTimeRule);
          sql.WHERE("t1.LOCK_TIME_RULE = #{lockTimeRule}");
        }
        String pwdType = evt.getPwdType();
        if(pwdType != null && !"".equals(pwdType)){
           evt.setPwdType(pwdType);
           sql.WHERE("t1.PWD_TYPE = #{pwdType}");
        }
        Integer numLength = evt.getNumLength();
        if(numLength != null){
        	evt.setNumLength(numLength);
           sql.WHERE("t1.NUM_LENGTH = #{numLength}");
        }
        String status = evt.getStatus();
        if(status != null && !"".equals(status)){
          evt.setStatus(status);
          sql.WHERE("t1.STATUS = #{status}");
        }
        Long creator = evt.getCreator();
        if(creator!= null){
            sql.WHERE("t1.CREATOR = #{creator}");
        }
        Long operator = evt.getOperator();
        if(operator!= null){
            sql.WHERE("t1.OPERATOR = #{operator}");
        }
        //D为删除状态
        sql.WHERE("t1.status='E'");
        sql.ORDER_BY("t1.CREATE_TIME desc");
        return sql.toString();
    }
    
    public String doEdit(EditLoginRuleEvt evt){
        SQL sql = new SQL().UPDATE(MAIN_TABLE_NAME);
        sql.WHERE("ID=#{id}");
        if(evt.getApplyType() != null && !"".equals(evt.getApplyType())){
           sql.SET("APPLY_TYPE = #{applyType}");
        }
        if(evt.getIniPwd() != null && !"".equals(evt.getIniPwd())){
           sql.SET("INI_PWD = #{iniPwd}");
        }
        if(evt.getMinPwd() != null && !"".equals(evt.getMinPwd())){
           sql.SET("MIN_PWD = #{minPwd}");
        }
        if(evt.getMaxPwd() != null && !"".equals(evt.getMaxPwd())){
           sql.SET("MAX_PWD = #{maxPwd}");
        }
        if(evt.getRuleType() != null && !"".equals(evt.getRuleType())){
           sql.SET("RULE_TYPE = #{ruleType}");
        }
        if(evt.getLockRuleTime() != null && !"".equals(evt.getLockRuleTime())){
           sql.SET("LOCK_RULE_TIME = #{lockRuleTime}");
        }
        if(evt.getLockRuleTimeUnit() != null && !"".equals(evt.getLockRuleTimeUnit())){
           sql.SET("LOCK_RULE_TIME_UNIT = #{lockRuleTimeUnit}");
        }
        if(evt.getMaxError() != null && !"".equals(evt.getMaxError())){
           sql.SET("MAX_ERROR = #{maxError}");
        }
        if(evt.getLockTime() != null && !"".equals(evt.getLockTime())){
           sql.SET("LOCK_TIME = #{lockTime}");
        }
        if(evt.getLockTimeRule() != null && !"".equals(evt.getLockTimeRule())){
           sql.SET("LOCK_TIME_RULE = #{lockTimeRule}");
        }
        if(evt.getPwdType() != null && !"".equals(evt.getPwdType())){
            sql.SET("PWD_TYPE = #{pwdType}");
        }
        if(evt.getNumLength() != null){
            sql.SET("NUM_LENGTH = #{numLength}");
        }
        if(evt.getStatus() != null && !"".equals(evt.getStatus())){
           sql.SET("STATUS = #{status}");
        }
        if(evt.getOperator() != null){
            sql.SET("OPERATOR = #{operator}");
        }
        evt.setUpdateTime(new Date());
        sql.SET("UPDATE_TIME=#{updateTime}");
        return sql.toString();
    }
    
    public String queryLoginRuleIsExist(QueryLoginRuleEvt evt){
        SQL sql = new SQL().SELECT("count(*)").FROM(MAIN_TABLE_NAME);
        Long Id = evt.getId();
        if(Id != null){
            sql.WHERE("id <> #{id}");//排除自己
        }
        //这里补充需要去重检查的字段
        return sql.toString();
    }
    
    public String queryDetail(QueryLoginRuleEvt evt){
    	StringBuilder sqlStr = new StringBuilder();
    	sqlStr.append("t1.ID,");
        sqlStr.append("t1.APPLY_TYPE,");
        sqlStr.append("t1.INI_PWD,");
        sqlStr.append("t1.MIN_PWD,");
        sqlStr.append("t1.MAX_PWD,");
        sqlStr.append("t1.RULE_TYPE,");
        sqlStr.append("t1.LOCK_RULE_TIME,");
        sqlStr.append("t1.LOCK_RULE_TIME_UNIT,");
        sqlStr.append("t1.MAX_ERROR,");
        sqlStr.append("t1.LOCK_TIME,");
        sqlStr.append("t1.LOCK_TIME_RULE,");
        sqlStr.append("t1.PWD_TYPE,");
        sqlStr.append("t1.NUM_LENGTH,");
        sqlStr.append("t1.STATUS,");
        sqlStr.append("t1.CREATOR,");
        sqlStr.append("t1.CREATE_TIME,");
        sqlStr.append("t1.OPERATOR,");
        sqlStr.append("t1.UPDATE_TIME");
        SQL sql = new SQL().SELECT(sqlStr.toString()).FROM(MAIN_TABLE_NAME+" t1");
        Long id = evt.getId();
        if(id!= null){
           sql.WHERE("ID = #{id}");
        }
        if(StringUtils.isNotBlank(evt.getApplyType())){
        	sql.WHERE("APPLY_TYPE = #{applyType}");
        }
        return sql.toString();
    }
    
    public String queryInitPwd(QueryLoginRuleEvt evt){
    	SQL sql = new SQL().SELECT("INI_PWD,NUM_LENGTH,PWD_TYPE").FROM(MAIN_TABLE_NAME);
        String applyType = evt.getApplyType();
        if(applyType!= null){
           sql.WHERE("APPLY_TYPE = #{applyType}");
        }
        return sql.toString();
    }
    
}

