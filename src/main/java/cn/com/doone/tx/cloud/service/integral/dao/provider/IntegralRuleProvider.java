package cn.com.doone.tx.cloud.service.integral.dao.provider;

import cn.com.doone.tx.cloud.service.integral.bean.IntegralRule;
import cn.com.doone.tx.cloud.service.integral.evt.IntegralTypeQueryEvt;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * ClassName: IntegralRuleProvider <br/>
 * Description: <br/>
 * date: 2020/5/12 18:54<br/>
 *
 * @author zhangzhenxing<br />
 */
public class IntegralRuleProvider {

    /**
     * 按照条件查询积分规则
     */
    public String queryIntegralRule(IntegralTypeQueryEvt queryEvt) {
        SQL sql = new SQL();
        sql.SELECT("ir.ID, ir.RULE_NAME, ir.INTEGRAL_TYPE_ID,it.TYPE_NAME" +
                ", ir.INTEGRAL_VALUE, CASE ir.STATUS " +
                "WHEN 'E' THEN '启用' WHEN 'N' THEN '禁用' WHEN 'D' " +
                "THEN '删除' WHEN 'W' THEN '待审核' WHEN 'F' THEN '审核不通过' " +
                "END as STATUS_NAME,ir.STATUS, ir.CREATE_TIME, ir.UPDATE_TIME, " +
                "    ir.CREATOR, ir.OPERATOR");
        sql.FROM("integral_rule ir");
        sql.JOIN("integral_type it ON ir.INTEGRAL_TYPE_ID=it.ID ");
        //积分规则名称
        if (StringUtils.isNotEmpty(queryEvt.getRuleName())) {
            sql.WHERE("it.RULE_NAME like #{ruleName}");
        }
        //分值
        if (queryEvt.getMinIntegralValue() != null && queryEvt.getMaxIntegralValue() != null) {
            sql.WHERE("it.INTEGRAL_VALUE >= #{minIntegralValue}" +
                    "AND it.INTEGRAL_VALUE <= #{maxIntegralValue}");
        }
        if (queryEvt.getMinIntegralValue() != null && queryEvt.getMaxIntegralValue() == null) {
            sql.WHERE("it.INTEGRAL_VALUE >= #{minIntegralValue}");
        }
        if (queryEvt.getMinIntegralValue() == null && queryEvt.getMaxIntegralValue() != null) {
            sql.WHERE("it.INTEGRAL_VALUE <= #{maxIntegralValue}}");
        }
        if (queryEvt.getIntegralTypeId() != null){
            sql.WHERE("ir.INTEGRAL_TYPE_ID=#{integralTypeId}");
        }
        if (queryEvt.getDeptIds() != null && queryEvt.getDeptIds().size() > 0) {
            sql.WHERE("ir.ID IN (" + queryDeptRuleIds(queryEvt.getDeptIds()) + " )");
        }
        return sql.toString();
    }

    /**
     * 查询部门适用的积分规则主键
     *
     * @param deptIds 部门id集合
     */
    public String queryDeptRuleIds(List<Long> deptIds) {
        SQL sql = new SQL();
        sql.SELECT_DISTINCT("integral_rule_id");
        //需要建索引
        sql.FROM("integral_department");
        if (deptIds != null && deptIds.size() > 0) {
            StringBuffer sb = new StringBuffer("department_id in (");
            deptIds.forEach(deptId -> {
                sb.append(deptId + ",");
            });
            sb.append("-1)");
            sql.WHERE(sb.toString());
        }
        return sql.toString();
    }

    /**
     * 更新积分规则信息
     *
     * @param integralRule 积分规则
     */
    public String updateIntegralRule(IntegralRule integralRule) {
        SQL sql = new SQL();
        sql.UPDATE("integral_rule");
        if (integralRule.getRuleName() != null) {
            sql.SET("RULE_NAME=#{ruleName}");
        }
        if (integralRule.getIntegralTypeId() != null) {
            sql.SET("INTEGRAL_TYPE_ID=#{integralTypeId}");
        }
        if (integralRule.getStatus() != null) {
            sql.SET("STATUS=#{status}");
        }
        if (integralRule.getCreateTime() != null) {
            sql.SET("CREATE_TIME=#{createTime}");
        }
        if (integralRule.getCreator() != null) {
            sql.SET("CREATOR=#{creator}");
        }
        if (integralRule.getOperator() != null) {
            sql.SET("OPERATOR=#{operator}");
        }
        if (integralRule.getUpdateTime() != null) {
            sql.SET("UPDATE_TIME=#{updateTime}");
        }
        sql.WHERE("ID=#{id}");
        return sql.toString();
    }

    /**
     * 查询ID
     */
    public String findIntegralRuleById(Long id) {
        SQL sql = new SQL();
        sql.SELECT("ID, RULE_NAME, INTEGRAL_TYPE_ID, INTEGRAL_VALUE, STATUS, CREATE_TIME, UPDATE_TIME, \n" +
                "    CREATOR, OPERATOR").FROM("integral_rule");
        if (id == null) {
            sql.WHERE("1=2");
        }else {
            sql.WHERE("ID=#{id}");
        }
        return sql.toString();
    }
}
