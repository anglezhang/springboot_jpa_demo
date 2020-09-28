package cn.com.doone.tx.cloud.service.integral.dao.provider;


import cn.com.doone.tx.cloud.service.integral.bean.IntegralType;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;


/**
 * ClassName: IntegralRuleProvider <br/>
 * Description: <br/>
 * date: 2020/5/12 14:19<br/>
 *
 * @author zhangzhenxing<br />
 */
public class IntegralTypeProvider {
    /**
     * 积分规则查询
     * */
    public String queryInteralRule(){
        SQL sql = new SQL();
        sql.SELECT("ID, TYPE_NAME, STATUS, CREATE_TIME, UPDATE_TIME" +
                ", CREATOR, OPERATOR")
                .FROM("integral_type")
                .WHERE("STATUS='E'")
                .ORDER_BY("UPDATE_TIME DESC");
        return sql.toString();
    }

    /**
     * 更新积分类型
     * */
    public String updateRuleType(IntegralType integralType){
        SQL sql = new SQL().UPDATE("integral_type");
        sql.WHERE("ID=#{id}");
        if (StringUtils.isNotEmpty(integralType.getTypeName())){
            sql.SET("TYPE_NAME=#{typeName}");
        }
        if (integralType.getUpdateTime() != null){
            sql.SET("UPDATE_TIME=#{updateTime}");
        }
        if (integralType.getOperator() != null){
            sql.SET("OPERATOR=#{operator}");
        }
        if (integralType.getCreateTime() != null){
            sql.SET("CREATE_TIME=#{updateTime}");
        }
        if (integralType.getOperator() != null){
            sql.SET("OPERATOR=#{operator}");
        }
        if (StringUtils.isNotEmpty(integralType.getStatus())){
            sql.SET("STATUS=#{status}");
        }
        return sql.toString();
    }

    /**
     * 根据ID查询规则类型
     * */
    public String findIntegralTypeById(Long id){
        SQL sql = new SQL();
        sql.SELECT("ID, TYPE_NAME, STATUS, CREATE_TIME, UPDATE_TIME" +
                ", CREATOR, OPERATOR")
                .FROM("integral_type");
        if (id == null){
            sql.WHERE("1=2");
        }else {
            sql.WHERE("ID=#{id}");
        }
        return sql.toString();
    }
}
