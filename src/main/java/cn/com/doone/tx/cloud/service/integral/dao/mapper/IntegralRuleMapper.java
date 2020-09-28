package cn.com.doone.tx.cloud.service.integral.dao.mapper;

import cn.com.doone.tx.cloud.service.integral.bean.IntegralRule;
import cn.com.doone.tx.cloud.service.integral.dao.provider.IntegralRuleProvider;
import cn.com.doone.tx.cloud.service.integral.evt.IntegralTypeQueryEvt;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.Map;

public interface IntegralRuleMapper {

    /**
     * 查询积分规则列表数据
     * @param queryEvt 查询条件
     * */
    @SelectProvider(type = IntegralRuleProvider.class,method="queryIntegralRule")
    Page<Map<String,Object>> queryIntegralRules(IntegralTypeQueryEvt queryEvt);

    /**
     * 更新积分规则
     * */
    @UpdateProvider(type = IntegralRuleProvider.class,method="updateIntegralRule")
    Integer updatetegralRules(IntegralRule integralRule);

    /**
     * 根据ID查对象
     * */
    @SelectProvider(type = IntegralRuleProvider.class,method = "findIntegralRuleById")
    IntegralRule findIntegralRuleById(@Param("id")Long id);
}