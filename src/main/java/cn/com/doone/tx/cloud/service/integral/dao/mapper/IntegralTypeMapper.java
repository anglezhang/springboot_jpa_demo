package cn.com.doone.tx.cloud.service.integral.dao.mapper;

import cn.com.doone.tx.cloud.service.integral.bean.IntegralRule;
import cn.com.doone.tx.cloud.service.integral.bean.IntegralType;
import cn.com.doone.tx.cloud.service.integral.dao.provider.IntegralTypeProvider;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

public interface IntegralTypeMapper {
    /**
     * 查询积分类型
     * */
    @SelectProvider(type = IntegralTypeProvider.class , method = "queryInteralRule")
    Page<IntegralType> findIntegralType();

    /**
     * 更新积分类型
     * @param integralType 积分类型
     * */
    @UpdateProvider(type = IntegralTypeProvider.class,method = "updateRuleType")
    int updateRuleType(IntegralType integralType);

    /**
     * 根据ID查询
     * */
    @SelectProvider(type = IntegralTypeProvider.class,method = "findIntegralTypeById")
    IntegralType findIntegralTypeById(@Param("id")Long id);

}