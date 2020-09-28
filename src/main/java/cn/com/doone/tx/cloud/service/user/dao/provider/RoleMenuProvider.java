package cn.com.doone.tx.cloud.service.user.dao.provider;



import org.apache.ibatis.jdbc.SQL;

import cn.com.doone.tx.cloud.service.user.evt.menuInfo.QueryPermissionMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.rolemenu.QueryRoleMenuEvt;

public class RoleMenuProvider {

    private final String TR_ROLE_MENU = "tr_role_menu";
    private final String TS_MENU_INFO = "ts_menu_info";

    public String queryRoleMenuByParam(QueryRoleMenuEvt queryRoleMenuEvt) {
        SQL sql = new SQL().SELECT("ROLE_ID AS roleId," +
                "MENU_ID AS menuId,STATUS AS status,CREATE_TIME AS createTime," +
                "UPDATE_TIME AS updateTime,OPERATOR AS operator").FROM(TR_ROLE_MENU);
        Long roleId = queryRoleMenuEvt.getRoleId();
        if(roleId != null){
            sql.WHERE("role_id = #{roleId}");
        }
        return sql.toString();
    }

    public String queryRoleMenuInfos(QueryRoleMenuEvt queryRoleMenuEvt) {
        SQL sql = new SQL().SELECT_DISTINCT("t2.ID,t2.MENU_CODE as menuCode")
                .FROM(TR_ROLE_MENU+" t1");
        sql.INNER_JOIN(TS_MENU_INFO+" t2 ON t1.MENU_ID=t2.ID");
        Long roleId = queryRoleMenuEvt.getRoleId();
        if(roleId != null){
            sql.WHERE("t1.role_id = #{roleId}");
        }
        sql.WHERE("t1.STATUS='E'");
        sql.WHERE("t1.MENU_ID <> -4");
        sql.WHERE("t2.STATUS='E'");//启用状态
        sql.WHERE("t2.IS_MENU=2");//1 菜单 2功能按钮
        return sql.toString();
    }

    public String queryPermissionMenu(QueryPermissionMenuEvt evt){
    	StringBuffer sqlSB = new StringBuffer();
    	sqlSB.append("SELECT M.*, \n");
    	if(null != evt.getStaffId()){
    		sqlSB.append("       (SELECT 1 \n");
    		sqlSB.append("          FROM TR_ROLE_MENU RM \n");
    		sqlSB.append("         INNER JOIN TR_STAFF_ROLE SR \n");
    		sqlSB.append("            ON RM.ROLE_ID = SR.ROLE_ID \n");
    		sqlSB.append("         WHERE RM.STATUS = 'E' \n");
    		sqlSB.append("           AND SR.STATUS = 'E' \n");
    		sqlSB.append("           AND SR.STAFF_ID = #{staffId} \n");
    		sqlSB.append("           AND RM.MENU_ID = M.ID \n");
    		sqlSB.append("           LIMIT 1) AS IS_PERMIT \n");
    	}else{
    		sqlSB.append("       '1' AS IS_PERMIT \n");
    	}
    	sqlSB.append("  FROM TS_MENU_INFO M \n");
//    	sqlSB.append(" INNER JOIN MUL_LANGUAGE ML \n");
//    	sqlSB.append("    ON m.MENU_CODE = ML.CODE \n");
//    	sqlSB.append("   AND ML.SOURCES = 1 \n");
//    	sqlSB.append("   AND ML.LANGUAGE_TYPE = #{languageType} \n");
    	sqlSB.append(" WHERE M.STATUS = 'E' \n");
    	//sqlSB.append(" --  AND M.PARENT_ID = 0 \n");
    	sqlSB.append(" ORDER BY M.SORT \n");
    	return sqlSB.toString();
    }
}
