package cn.com.doone.tx.cloud.service.user.dao.provider;


import org.apache.ibatis.jdbc.SQL;

import cn.com.doone.tx.cloud.service.user.bean.StaffExtendInfoBean;

/**
 *yecz
 */
public class StaffExtendInfoProvider {
	
    private final String TS_STAFF_EXTEND_INFO = "ts_staff_extend_info";
    
    public String queryStaffExtendInfoByParam(String sqlWhere, StaffExtendInfoBean evt) {
        SQL sql = new SQL().SELECT("t1.*").FROM(TS_STAFF_EXTEND_INFO+" t1");
        if (sqlWhere==null||sqlWhere.isEmpty()) {
        	sqlWhere = "";
		}
        sql.WHERE("1 = 1 " + sqlWhere);
        Long id = evt.getId();
        Long staffId = evt.getStaffId();
        if(id != null){
            sql.WHERE("ID = " + id);
        }
        if(staffId != null){
            sql.WHERE("STAFF_ID = " + staffId);
        }
        sql.WHERE("t1.STATUS='E'");
        return sql.toString();
    }
}
