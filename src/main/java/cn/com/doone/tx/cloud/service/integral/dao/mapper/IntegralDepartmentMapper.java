package cn.com.doone.tx.cloud.service.integral.dao.mapper;

import cn.com.doone.tx.cloud.service.integral.bean.IntegralDepartment;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Param;
import cn.com.doone.tx.cloud.service.integral.dao.provider.IntegralDepartmentProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface IntegralDepartmentMapper {
    /**
     * 删除积分规则适配部门
     * @param ruleId
     * */
    @DeleteProvider(type = IntegralDepartmentProvider.class
            ,method = "deleteIntegralDeptByRuleId")
    int deleteDeptByRuleIds(@Param("ruleId") Long ruleId);

    /**
     * 根据积分规则查询积分部门
     * @param integralRuleId 积分规则ID
     * */
    @SelectProvider(type = IntegralDepartmentProvider.class,method = "findIntegralDepartMentByRuleId")
    List<IntegralDepartment> findIntegralDepartMentByRuleId(@Param("integralRuleId")Long integralRuleId);
}