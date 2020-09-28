package cn.com.doone.tx.cloud.service.user.dao.provider;

import org.apache.ibatis.jdbc.SQL;

import cn.com.doone.tx.cloud.service.user.bean.CustomGroupExtendInfoBean;

/**
 * @author huangminqiong
 * @version V1.0
 * @Title: CustomGroupExtendInfoProvider
 * @Package cn.com.doone.tx.cloud.user.service.dao.provider.sys
 * @Description: 
 * @date 2017年4月18日 下午3:46:42
 */
public class CustomGroupExtendInfoProvider {
	
    private final String TS_CUSTOM_GROUP_EXTEND_INFO = "ts_custom_group_extend_info";
    
    public String queryCustomGroupExtendInfoByParam(String sqlWhere, CustomGroupExtendInfoBean evt) {
        SQL sql = new SQL().SELECT("t1.*").FROM(TS_CUSTOM_GROUP_EXTEND_INFO+" t1");
        if (sqlWhere==null||sqlWhere.isEmpty()) {
        	sqlWhere = "";
		}
        sql.WHERE("1 = 1 " + sqlWhere);
        Long id = evt.getId();
        Long customGroupId = evt.getCustomGroupId();
        if(id != null){
            sql.WHERE("ID = " + id);
        }
        if(customGroupId != null){
            sql.WHERE("CUSTOM_GROUP_ID = " + customGroupId);
        }
        return sql.toString();
    }
}
