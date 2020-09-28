package cn.com.doone.tx.cloud.service.integral.dao.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * ClassName: IntegralDepartmentProvider <br/>
 * Description: <br/>
 * date: 2020/5/14 17:44<br/>
 *
 * @author zhangzhenxing<br />
 */
public class IntegralDepartmentProvider {

    /**
     * 工具部门删除
     * @param ruleId 部门ID
     * */
    public String deleteIntegralDeptByRuleId(Long ruleId){
        SQL sql = new SQL();
        sql.DELETE_FROM("integral_department");
        if (ruleId == null){
            sql.WHERE("1=2");//不传id不删除数据
        }else {
            sql.WHERE("integral_rule_id=#{ruleId}");
        }
        return sql.toString();
    }

    /**
     * 根据积分规则查询积分部门
     * @param integralRuleId 积分规则ID
     * */
    public String findIntegralDepartMentByRuleId(Long integralRuleId){
        SQL sql = new SQL();
        sql.SELECT("id.ID, wd.DEPARTMENT_NAME,id.INTEGRAL_RULE_ID, id.DEPARTMENT_ID, id.STATUS" +
                ", id.CREATE_TIME, id.UPDATE_TIME, id.CREATOR, id.OPERATOR")
                .FROM("integral_department id");
        sql.INNER_JOIN("wx_department wd ON wd.ID=id.DEPARTMENT_ID");
        if(integralRuleId == null){
            sql.WHERE("1=2");
        }else {
            sql.WHERE("id.INTEGRAL_RULE_ID=#{integralRuleId}");
        }
        return sql.toString();
    }
}
