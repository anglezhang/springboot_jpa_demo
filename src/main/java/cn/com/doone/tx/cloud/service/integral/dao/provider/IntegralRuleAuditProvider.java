package cn.com.doone.tx.cloud.service.integral.dao.provider;

import cn.com.doone.tx.cloud.service.integral.evt.IntegralRuleAuditEvt;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

/**
 * ClassName: IntegralRuleAuditProvider <br/>
 * Description: <br/>
 * date: 2020/5/17 17:11<br/>
 *
 * @author zhangzhenxing<br />
 */
public class IntegralRuleAuditProvider {

    /**
     * 查询积分审核分页记录
     */
    public String findRuleAuditList(IntegralRuleAuditEvt integralRuleAuditEvt) {
        SQL sql = new SQL();
        sql.SELECT("ira.ID,ira.AUDIT_REMARK,case ira.AUDIT_RESULT WHEN 'E' THEN '通过' WHEN 'F' THEN '不通过'" +
                " END as AUDIT_RESULT ,ira.INTEGRAL_RULE_ID,ir.RULE_NAME ");
        sql.FROM("integral_rule_audit ira");
        sql.INNER_JOIN("integral_rule ir ON ir.id=ira.INTEGRAL_RULE_ID");
        if (integralRuleAuditEvt.getIntegralRuleId() != null) {
            sql.WHERE("ira.INTEGRAL_RULE_ID=#{integralRuleId}");
        }
        sql.ORDER_BY("ira.CREATE_TIME DESC");
        return sql.toString();
    }

    /**
     * 更新积分审核记录
     * */
    public String updateRuleAutid(IntegralRuleAuditEvt integralRuleAuditEvt){
        SQL sql = new SQL();
        sql.UPDATE("integral_rule_audit");
        if (StringUtils.isNotEmpty(integralRuleAuditEvt.getAuditResult())){
            sql.SET("AUDIT_RESULT=#{auditResult}");
        }
        if (StringUtils.isNotEmpty(integralRuleAuditEvt.getAuditRemark())){
            sql.SET("AUDIT_REMARK=#{auditRemark}");
        }
        if (integralRuleAuditEvt.getId() != null){
            sql.WHERE("ID=#{id}");
        }else {
            sql.WHERE("1=-2");
        }
        return sql.toString();
    }

    /**
     * 查询待审核积分记录
     * */
    public String findReviewedList(IntegralRuleAuditEvt integralRuleAuditEvt){
        SQL sql = new SQL();
        sql.SELECT("ir.ID,ir.INTEGRAL_VALUE,ir.RULE_NAME,it.TYPE_NAME,ir.STATUS");
        sql.FROM("integral_rule ir");
        sql.INNER_JOIN("integral_type it ON ir.INTEGRAL_TYPE_ID=it.ID");
        if (integralRuleAuditEvt.getRuleTypeId() != null){
            sql.WHERE("it.ID=#{ruleTypeId}");
        }
        sql.WHERE("ir.STATUS='W'");
        sql.ORDER_BY("ir.CREATE_TIME DESC");
        return sql.toString();
    }

    /**
     * 查询审核记录sql
     * */
    public String findAuditByRuleId(Long integralRuleId){
        SQL sql = new SQL();
        sql.SELECT("case ira.AUDIT_RESULT WHEN 'E' THEN '通过' WHEN 'F' THEN '不通过' END as AUDIT_RESULT" +
                ",ira.audit_remark,ira.STATUS," +
                "tsi.staff_name,date_format(ira.create_time,'%Y-%m-%d %H:%i:%S') AS create_time");
        sql.FROM("integral_rule_audit ira");
        sql.INNER_JOIN("ts_staff_info tsi ON ira.CREATOR=tsi.ID");
        if (integralRuleId != null){
            sql.WHERE("ira.integral_rule_id=#{integralRuleId}");
        }
        return sql.toString();
    }
}
