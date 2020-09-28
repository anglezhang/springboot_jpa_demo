package cn.com.doone.tx.cloud.service.user.dao.provider;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import cn.com.doone.tx.cloud.service.user.evt.menuInfo.QueryPermissionMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.sysaccess.EditSysAccessEvt;
import cn.com.doone.tx.cloud.service.user.evt.sysaccess.QuerySysAccessEvt;




public class SysAccessProvider {

	private final String MAIN_TABLE_NAME = "sys_connect_info";

	public String queryDataCount(QuerySysAccessEvt evt) {
		SQL sql = new SQL().SELECT("count(*)").FROM(MAIN_TABLE_NAME + " t1");
		boolean key = true;
		String editUnique = evt.getEditUnique();
		if (StringUtils.isNotBlank(editUnique)) {
			if ("Y".equals(editUnique)) {
				key = false;
			}
		}

		Long id = evt.getId();
		if (id != null) {
			if (key)
				sql.WHERE("t1.id = #{id}");
			else
				sql.WHERE("t1.id <> #{id}");
		}

		String sysName = evt.getSysName();
		if (sysName != null && !"".equals(sysName)) {
			if (key) {
				evt.setSysName("%" + sysName + "%");
				sql.WHERE("t1.SYS_NAME like #{sysName}");
			} else {
				sql.WHERE("t1.SYS_NAME = #{sysName}");
			}
		}

		String sysCode = evt.getSysCode();
		if (sysCode != null && !"".equals(sysCode)) {
			sql.WHERE("t1.SYS_CODE = #{sysCode}");
		}
		String sysKey = evt.getSysKey();
		if (StringUtils.isNotBlank(sysKey)) {
			sql.WHERE("t1.SYS_KEY = #{sysKey}");
		}
		
		// D为不在用状态
		sql.WHERE("t1.status='E'");
		return sql.toString();
	}

	public String queryDataList(QuerySysAccessEvt evt) {
		SQL sql = new SQL().SELECT_DISTINCT("t1.*,t1.SYS_NAME as sysName").FROM(MAIN_TABLE_NAME + " t1")
				.LEFT_OUTER_JOIN("ts_menu_info t2 on t1.MENU_ID=t2.ID");
		Long Id = evt.getId();
		if (Id != null) {
			sql.WHERE("t1.id = #{id}");
		}
		String sysName = evt.getSysName();
		if (sysName != null && !"".equals(sysName)) {
			evt.setSysName("%" + sysName + "%");
			sql.WHERE("t1.SYS_NAME like #{sysName}");
		}
		String sysCode = evt.getSysCode();
		if (sysCode != null && !"".equals(sysCode)) {
			sql.WHERE("t1.SYS_CODE = #{sysCode}");
		}
		Long menuId = evt.getMenuId();
		if (menuId != null) {
			sql.WHERE("t1.MENU_ID = #{menuId}");
		}
		
		// D为不在用状态
		sql.WHERE("t1.status='E'");
		sql.ORDER_BY("t1.CREATE_TIME desc");
		return sql.toString();
	}

	public String doEdit(EditSysAccessEvt evt) {
		SQL sql = new SQL().UPDATE(MAIN_TABLE_NAME);
		sql.WHERE("ID=#{id}");
		// if(evt.getSysName()!= null){
		// sql.SET("SYS_NAME=#{sysName}");
		// }
		if (StringUtils.isNotBlank(evt.getSysCode())) {
			sql.SET("SYS_CODE=#{sysCode}");
		}
		if (StringUtils.isNotBlank(evt.getSysKey())) {
			sql.SET("SYS_KEY=#{sysKey}");
		}
		if (StringUtils.isNotBlank(evt.getLoginUrl())) {
			sql.SET("LOGIN_URL=#{loginUrl}");
		}
		if (StringUtils.isNotBlank(evt.getSuccessUrl())) {
			sql.SET("SUCCESS_URL=#{successUrl}");
		}
		if (StringUtils.isNotBlank(evt.getPwdUrl())) {
			sql.SET("PWD_URL=#{pwdUrl}");
		}
		if (StringUtils.isNotBlank(evt.getOverSeconds())) {
			sql.SET("OVER_SECONDS=#{overSeconds}");
		}
		if (StringUtils.isNotBlank(evt.getExtendMin())) {
			sql.SET("EXTEND_MIN=#{extendMin}");
		}
		if (evt.getMenuId() != null) {
			sql.SET("MENU_ID=#{menuId}");
		}
		if (StringUtils.isNotBlank(evt.getStatus())) {
			sql.SET("STATUS=#{status}");
		}
		if (evt.getOperator() != null) {
			sql.SET("OPERATOR=#{operator}");
		}

		if (StringUtils.isNotBlank(evt.getIsPlat())) {
			sql.SET("IS_PLAT=#{isPlat}");
		}
		evt.setUpdateTime(new Date());
		sql.SET("UPDATE_TIME=#{updateTime}");
		return sql.toString();
	}

	public String querySysAccessIsExist(QuerySysAccessEvt evt) {
		SQL sql = new SQL().SELECT("count(*)").FROM(MAIN_TABLE_NAME);
		Long Id = evt.getId();
		if (Id != null) {
			sql.WHERE("id <> #{id}");// 排除自己
		}
		// 这里补充需要去重检查的字段
		return sql.toString();
	}

	public String doDelete(EditSysAccessEvt evt) {
		SQL sql = new SQL().UPDATE(MAIN_TABLE_NAME);
		sql.WHERE("ID=#{id}");
		sql.SET("STATUS=#{status}");
		if (evt.getOperator() != null) {
			sql.SET("OPERATOR=#{operator}");
		}
		evt.setUpdateTime(new Date());
		sql.SET("UPDATE_TIME=#{updateTime}");
		return sql.toString();
	}

	public String doNenable(EditSysAccessEvt evt) {
		SQL sql = new SQL().UPDATE(MAIN_TABLE_NAME);
		sql.WHERE("ID=#{id}");
		sql.SET("STATUS=#{status}");
		if (evt.getOperator() != null) {
			sql.SET("OPERATOR=#{operator}");
		}
		evt.setUpdateTime(new Date());
		sql.SET("UPDATE_TIME=#{updateTime}");
		return sql.toString();
	}

	public String queryPermissionMenu(QueryPermissionMenuEvt evt) {
		StringBuffer sqlSB = new StringBuffer();
		String sources = "0";// 来源 1.菜单 2.渠道 3.接入点 具体查看字典表

		sqlSB.append("SELECT M.*, \n");
		sqlSB.append("       CI.SYS_NAME NAME, \n");
		sqlSB.append("       CI.LOGIN_URL, \n");
		sqlSB.append("       CI.SUCCESS_URL, \n");
		if (null != evt.getStaffId()) {
			sqlSB.append("       (SELECT 1 \n");
			sqlSB.append("          FROM TR_ROLE_MENU RM \n");
			sqlSB.append("         INNER JOIN TR_STAFF_ROLE SR \n");
			sqlSB.append("            ON RM.ROLE_ID = SR.ROLE_ID \n");
			sqlSB.append("         WHERE RM.STATUS = 'E' \n");
			sqlSB.append("           AND SR.STATUS = 'E' \n");
			sqlSB.append("           AND SR.STAFF_ID = #{staffId} \n");
			sqlSB.append("           AND RM.MENU_ID = M.ID \n");
			sqlSB.append("           AND ROWNUM = 1) AS IS_PERMIT \n");
		} else {
			sqlSB.append("       '1' AS IS_PERMIT \n");
		}
		sqlSB.append("  FROM TS_MENU_INFO M \n");
		sqlSB.append(" INNER JOIN SYS_CONNECT_INFO CI \n");
		sqlSB.append("    ON CI.MENU_ID = M.ID \n");
		sqlSB.append("   AND CI.STATUS = 'E' \n");
		sqlSB.append(" WHERE M.STATUS = 'E' \n");
		sqlSB.append("   AND M.PARENT_ID = 0 \n");
		sqlSB.append(" ORDER BY M.SORT \n");

		return sqlSB.toString();
	}

}
