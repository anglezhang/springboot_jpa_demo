package cn.com.doone.tx.cloud.service.user.dao.provider;


import org.apache.ibatis.jdbc.SQL;

import cn.com.doone.tx.cloud.service.user.bean.StaffGroupExtendInfoBean;

/**
 *yecz
 */
public class StaffGroupExtendInfoProvider {
	
    private final String TS_STAFF_GROUP_EXTEND_INFO = "ts_staff_group_extend_info";
    
    public String queryStaffGroupExtendInfoByParam(String sqlWhere, StaffGroupExtendInfoBean evt) {
        SQL sql = new SQL().SELECT("t1.*").FROM(TS_STAFF_GROUP_EXTEND_INFO+" t1");
        if (sqlWhere==null||sqlWhere.isEmpty()) {
        	sqlWhere = "";
		}
        sql.WHERE("1 = 1 " + sqlWhere);
        Long id = evt.getId();
        Long groupId = evt.getGroupId();
        if(id != null){
            sql.WHERE("ID = " + id);
        }
        if(groupId != null){
            sql.WHERE("GROUP_ID = " + groupId);
        }
        sql.WHERE("t1.STATUS='E'");
        return sql.toString();
    }
}
