package cn.com.doone.tx.cloud.service.user.dao.provider;


import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Service;

import cn.com.doone.tx.cloud.service.user.evt.staffRegion.EditStaffRegionEvt;
import cn.com.doone.tx.cloud.service.user.evt.staffRegion.QueryStaffRegionEvt;

@Service
public class StaffRegionProvider {

	private final String MAIN_TABLE_NAME = "STAFF_REGION";
	
	public String queryByParam(QueryStaffRegionEvt evt) {
		SQL sql = new SQL().SELECT("*").FROM(MAIN_TABLE_NAME);
		if (evt.getStaffId() != null) {
			sql.WHERE("STAFF_ID = #{staffId}");
		}
		sql.WHERE("STATUS = 'E'");	
		return sql.toString();
	
	}
	
	public String doOut(EditStaffRegionEvt evt) {
		SQL sql = new SQL().UPDATE(MAIN_TABLE_NAME);
		String status = evt.getStatus();
		if (StringUtils.isNotBlank(status) ) {
			sql.SET("STATUS=#{status}");
		} 
		Long operator = evt.getOperator();
		if (operator!=null) {
			sql.SET("OPERATOR = #{operator}");
		}
		sql.SET("UPDATE_TIME = #{updateTime}");
		Long staffId = evt.getStaffId();
		if (staffId != null) {
			sql.WHERE("STAFF_ID = #{staffId}");
		}
		Long countryCode = evt.getCountryCode();
		if (countryCode != null) {
			sql.WHERE("COUNTRY_CODE = #{countryCode}");
		}
		return sql.toString();
	}
}
