package cn.com.doone.tx.cloud.service.integral.dao.mapper;

import cn.com.doone.tx.cloud.service.integral.evt.IntegralRuleAuditEvt;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import cn.com.doone.tx.cloud.service.integral.dao.provider.IntegralRuleAuditProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;
import java.util.Map;

/**
 * ClassName: IntegralRuleAuditMapper <br/>
 * Description: <br/>
 * date: 2020/5/17 17:10<br/>
 *
 * @author zhangzhenxing<br />
 */
public interface IntegralRuleAuditMapper {

    /**
     * 查询积分审核记录
     * */
    @SelectProvider(type = IntegralRuleAuditProvider.class,method = "findRuleAuditList")
    List<Map<String,Object>> findRuleAuditList(IntegralRuleAuditEvt integralRuleAuditEvt);

    /**
     * 更新积分审核状态
     * */
    @UpdateProvider(type = IntegralRuleAuditProvider.class,method = "updateRuleAutid")
    int updateRuleAutid(IntegralRuleAuditEvt integralRuleAuditEvt);

    /**
     * 查询待审核记录
     * @param integralRuleAuditEvt
     * */
    @SelectProvider(type = IntegralRuleAuditProvider.class,method = "findReviewedList")
    List<Map<String,Object>> findReviewedList(IntegralRuleAuditEvt integralRuleAuditEvt);

    /**
     * 根据积分ID查询审核记录
     * */
    @SelectProvider(type = IntegralRuleAuditProvider.class,method = "findAuditByRuleId")
    List<Map<String,Object>> findAuditByRuleId(@Param("integralRuleId")Long integralRuleId);
}
