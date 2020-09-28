package cn.com.doone.tx.cloud.service.user.dao.provider;



import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import cn.com.doone.tx.cloud.service.user.evt.StaffRole.DeleteStaffRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.StaffRole.QueryStaffRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.groupRole.DeleteGroupRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.role.EditRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.role.QueryRoleEvt;

/**
 * yecz
 */
public class RoleProvider {

    private final String TS_ROLE_INFO = "ts_role_info";
    private final String TBL_STAFF_ROLE = "tr_staff_role";
    private final String TR_GROUP_ROLE = "tr_group_role";

    public String queryRoleCount(QueryRoleEvt evt) {
        SQL sql = new SQL().SELECT("count(*)").FROM(TS_ROLE_INFO);
        String roleName = evt.getRoleName();
        if (StringUtils.isNotBlank(roleName)) {
            evt.setRoleName("%"+roleName+"%");
            sql.WHERE("role_name like #{roleName}");
        }
        String sysCode = evt.getSysCode();
        if (StringUtils.isNotBlank(sysCode)) {
            sql.WHERE("sys_code = #{sysCode}");
        }
        Long creator = evt.getCreator();
        if (creator!=null) {
            sql.WHERE("creator=#{creator}");
        }
        //D为删除状态
        sql.WHERE("status!='D'");
        return sql.toString();
    }
    public String queryRoleByParam(QueryRoleEvt evt) {
        SQL sql = new SQL().SELECT_DISTINCT("t1.ID AS Id,t1.ROLE_NAME AS roleName," +
                "t1.ROLE_REMARK AS roleRemark,t1.SYS_CODE AS sysCode,t1.STATUS AS status," +
                "t1.CREATE_TIME AS createTime,t1.UPDATE_TIME AS updateTime,t1.OPERATOR AS operator," +
                "t1.CREATOR as creator,t2.ID as staffId,t2.staff_name AS creatorName")
                .FROM(TS_ROLE_INFO+" t1").LEFT_OUTER_JOIN("ts_staff_info t2 on t1.creator=t2.ID")
//                .LEFT_OUTER_JOIN("sys_connect_info t3 on t1.sys_code = t3.sys_code AND t3.STATUS = 'E'")
//                .LEFT_OUTER_JOIN("mul_language t4 on t3.sys_code = t4.code and t4.status = 'E' and t4.language_type=#{languageType}")
                ;
        Long Id = evt.getId();
//        System.out.println("Id"+Id);
        if(Id!= null){
            sql.WHERE("t1.id = #{id}");
        }
        String roleName = evt.getRoleName();
        if (StringUtils.isNotBlank(roleName)) {
            evt.setRoleName("%"+roleName+"%");
            sql.WHERE("t1.role_name like #{roleName}");
        }
        String sysCode = evt.getSysCode();
        if (StringUtils.isNotBlank(sysCode)) {
            sql.WHERE("t1.sys_code = #{sysCode}");
        }
        Long creator = evt.getCreator();
        if (creator!=null) {
            sql.WHERE("t1.creator=#{creator}");
        }
        //D为删除状态
        sql.WHERE("t1.status!='D'");
        sql.ORDER_BY("t1.CREATE_TIME desc");
        return sql.toString();
    }

    public String queryRoleIsExist(QueryRoleEvt evt){
        SQL sql = new SQL().SELECT("count(*)").FROM(TS_ROLE_INFO);
        Long Id = evt.getId();
        if(Id != null){
            sql.WHERE("id <> #{id}");//排除自己
        }
        String roleName = evt.getRoleName();
        if (StringUtils.isNotBlank(roleName)) {
            //evt.setRoleName("%"+roleName+"%");
            sql.WHERE("role_name = #{roleName}");
        }
        sql.WHERE("STATUS='E'");
        return sql.toString();
    }
    //根据角色id查对应系统的菜单id
//    public String querySysMenuIdByRoleId(QueryRoleEvt evt){
//        SQL sql = new SQL().SELECT("t1.*,t2.").FROM(TS_ROLE_INFO +" t1")
//        		.LEFT_OUTER_JOIN("sys_connect_info t2 on t1.sys_code = t2.sys_code AND t2.STATUS = 'E'");
//        Long Id = evt.getId();
//        if(Id != null){
//            sql.WHERE("id <> #{id}");//排除自己
//        }
//        String roleName = evt.getRoleName();
//        if (StringUtils.isNotBlank(roleName)) {
//            //evt.setRoleName("%"+roleName+"%");
//            sql.WHERE("role_name = #{roleName}");
//        }
//        sql.WHERE("STATUS='E'");
//        return sql.toString();
//    }

    public String doEdit(EditRoleEvt evt){
        SQL sql = new SQL().UPDATE(TS_ROLE_INFO);
        if (evt.getRoleName()!=null){
            sql.SET("ROLE_NAME=#{roleName}");
        }
        if (evt.getRoleRemark()!=null){
            sql.SET("ROLE_REMARK=#{roleRemark}");
        }
        if (evt.getSysCode()==null) {
        	evt.setSysCode("");
        	sql.SET("SYS_CODE=#{sysCode}");
		} else {
			sql.SET("SYS_CODE=#{sysCode}");
		}
        if (evt.getStatus()!=null){
            evt.setUpdateTime(new Date());
            sql.SET("STATUS=#{status}");
        }
        sql.WHERE("ID=#{id}");
        return sql.toString();
    }
    
    //删除账号角色绑定关系
    public String deleteStaffRole(DeleteStaffRoleEvt evt){
        SQL sql = new SQL().DELETE_FROM(TBL_STAFF_ROLE);
        if(evt.getRoleId()!=null){
            sql.WHERE("ROLE_ID = #{roleId}");
        }
        return sql.toString();
    }
    //删除用户组角色绑定关系
    public String deleteGroupRole(DeleteGroupRoleEvt evt){
        SQL sql = new SQL().DELETE_FROM(TR_GROUP_ROLE);
        if(evt.getRoleId()!=null){
            sql.WHERE("ROLE_ID = #{roleId}");
        }
        return sql.toString();
    }
    
    //查询角色和用户绑定关系的总条数
    public String queryStaffRoleCount(QueryStaffRoleEvt evt) {
    	 SQL sql = new SQL().SELECT("count(id)").FROM(TBL_STAFF_ROLE);
    	 if (evt.getRoleId() != null) {
			sql.WHERE("role_id = #{roleId}");
		 }
    	 sql.WHERE("status <> 'D'");
    	 return sql.toString();
	}

}
