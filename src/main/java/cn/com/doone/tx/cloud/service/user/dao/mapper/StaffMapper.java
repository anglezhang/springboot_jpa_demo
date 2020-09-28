package cn.com.doone.tx.cloud.service.user.dao.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.com.doone.tx.cloud.service.user.dao.provider.StaffProvider;
import cn.com.doone.tx.cloud.service.user.evt.StaffRole.DeleteStaffRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.StaffRole.QueryStaffRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.loginlock.QueryLoginLockEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.DeleteStaffEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.EditStaffEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.QueryStaffEvt;
import cn.com.doone.tx.cloud.service.user.info.DataAuthsInfo;
import cn.com.doone.tx.cloud.service.user.info.LoginLockInfo;
import cn.com.doone.tx.cloud.service.user.info.RoleInfo;
import cn.com.doone.tx.cloud.service.user.info.StaffInfo;
import cn.com.doone.tx.cloud.service.user.info.StaffTaskInfo;

@Mapper
public interface StaffMapper {

	@SelectProvider(type = StaffProvider.class, method = "queryCount")
	int queryCount(QueryStaffEvt queryStaffEvt);

	@SelectProvider(type = StaffProvider.class, method = "queryByParam")
	List<StaffInfo> queryByParam(QueryStaffEvt queryStaffEvt);

	@SelectProvider(type = StaffProvider.class, method = "queryByRegion")
	List<StaffInfo> queryByRegion(QueryStaffEvt queryStaffEvt);

	@SelectProvider(type = StaffProvider.class, method = "queryByExtendParam")
	List<StaffInfo> queryByExtendParam(String sqlWhere);

	@Select("select STAFF_CODE staffCode, CONTACT_TEL contractTel,PASSWD passWd from ts_staff_info where staff_code = #{staffCode}")
	List<StaffInfo> queryStaffInfoByCode(QueryStaffEvt queryStaffEvt);

	/** 登录时查询用户信息 */
	@SelectProvider(type = StaffProvider.class, method = "queryStaffInfo")
	List<StaffInfo> queryStaffInfo(QueryStaffEvt queryStaffEvt);

	// @Delete("DELETE FROM ts_staff_info WHERE ID = #{id}")
	/** 删除 修改状态为D */
	@Update("update ts_staff_info set status=#{status} where id=#{id} ")
	int doDelete(DeleteStaffEvt evt);

	@Delete("DELETE FROM tr_group_staff WHERE STAFF_ID = #{staffId}")
	int doDeleteStaffGroup(Long staffId);

	@SelectProvider(type = StaffProvider.class, method = "queryUserIsExist")
	int queryUserIsExist(QueryStaffEvt evt);

	@Select("select * from ts_staff_info where STAFF_CODE=#{staffCode} and PASSWD=#{passWd}")
	List<StaffInfo> checkUser(QueryStaffEvt evt);

	// @SelectProvider(type = StaffProvider.class , method = "queryRoleByStaff")
	// 查出用户角所绑定的角色 及用户所在用户组的角色
	/*
	 * @Select("SELECT distinct t1.ID,t1.ROLE_NAME roleName,t1.ROLE_REMARK roleRemark,t1.IS_DEFAULT isDefault,t6.NAME as sysName,"
	 * +
	 * "t1.STATUS status,t1.CREATE_TIME createTime,t1.UPDATE_TIME updateTime,t1.OPERATOR operator,t5.IS_GRANT isGrant "
	 * + "from ts_role_info t1 left join tr_staff_role t5 on t5.role_id=t1.ID " +
	 * "left join mul_language t6 on t1.sys_code=t6.code " + "where t1.id in ("+
	 * 
	 * "select T1.role_id from (select role_id from tr_staff_role t3 where t3.staff_id=#{staffId} and t3.STATUS ='E') T1 "
	 * +
	 * "union select T2.role_id from (select role_id from tr_group_role t4 left join tr_group_staff t5 "
	 * +
	 * "on t4.GROUP_ID = t5.GROUP_ID where t5.staff_id=#{staffId} AND t4.STATUS ='E' AND t5.STATUS ='E') T2) "
	 * +
	 * 
	 * "and t5.staff_id=#{staffId} and t5.STATUS ='E'")
	 * 
	 * @Select("SELECT distinct t1.ID,t1.ROLE_NAME roleName,t1.ROLE_REMARK roleRemark,t1.IS_DEFAULT isDefault,t2.NAME as sysName,"
	 * +
	 * "t1.STATUS status,t1.CREATE_TIME createTime,t1.UPDATE_TIME updateTime,t1.OPERATOR operator "
	 * + "from ts_role_info t1 left join tr_staff_role t3 on t3.role_id=t1.ID " +
	 * "left join mul_language t2 on t1.sys_code=t2.code and t2.language_type=#{languageType} "
	 * + "where t1.id in (" +
	 * "select t1.role_id from (select role_id from tr_staff_role t4 where t4.staff_id=#{staffId} and t4.STATUS ='E') t1 )"
	 * + "and t3.staff_id=#{staffId} and t3.STATUS ='E' ")
	 */
	@SelectProvider(type = StaffProvider.class, method = "queryRoleByStaff")
	List<RoleInfo> queryRoleByStaff(QueryStaffRoleEvt evt);

	/** 查出用户被授权的角色 */
	@SelectProvider(type = StaffProvider.class, method = "queryRoleByGrand")
	List<RoleInfo> queryRoleByGrand(QueryStaffRoleEvt evt);

	/** 查出用户所创建的角色 */
	@SelectProvider(type = StaffProvider.class, method = "queryRoleSelfCreate")
	List<RoleInfo> queryRoleSelfCreate(QueryStaffRoleEvt evt);

	@DeleteProvider(type = StaffProvider.class, method = "deleteStaffRole")
	int deleteStaffRole(DeleteStaffRoleEvt evt);

	@UpdateProvider(type = StaffProvider.class, method = "doEdit")
	int doEdit(EditStaffEvt evt);

	/** 根据operator获取用户信息的共有方法 */
	@Select("select staff_name from ts_staff_info where id=#{id}")
	public String queryUserInfo(QueryStaffEvt evt);

	/**
	 * 查出用户角所绑定的角色 及用户所在用户组的角色 上述结果作为条件查出角色绑定的用户组信息
	 */
	@Select("select distinct t5.id,t5.parent_id,t5.group_name from ts_staff_group t5 where t5.id in" + "("
			+ "select t6.group_id from tr_data_role t6 where t6.role_id in" + "(" + "SELECT distinct t1.ID"
			+ " from ts_role_info t1 where t1.id in ("
			+ " select T1.role_id from (select role_id from tr_staff_role t3 where t3.staff_id=#{staffId} and t3.STATUS ='E') T1 "
			+ " union select T2.role_id from (select role_id from tr_group_role t4 left join tr_group_staff t5 "
			+ " on t4.GROUP_ID = t5.GROUP_ID where t5.staff_id=#{staffId} AND t4.STATUS ='E' AND t5.STATUS ='E') T2)"
			+ ")" + ")")
	List<DataAuthsInfo> queryDataAuthsInfoByStaff(QueryStaffRoleEvt evt);

	/** 查询用户信息(用户组、角色固定) */
	@SelectProvider(type = StaffProvider.class, method = "queryStaffByGroupAndRole")
	List<StaffInfo> queryStaffByGroupAndRole(QueryStaffEvt queryStaffEvt);

	/** 查询用户信息(是否属于特定用户组或特定角色 ) */
	@SelectProvider(type = StaffProvider.class, method = "queryStaffInfoByGroupOrRole")
	List<StaffInfo> queryStaffInfoByGroupOrRole(QueryStaffEvt queryStaffEvt);

	/** 查找锁定日志 */
	@SelectProvider(type = StaffProvider.class, method = "queryLoginLockInfo")
	List<LoginLockInfo> queryLoginLockInfo(QueryLoginLockEvt evt);

	/** 查询有用于下拉框加载 */
	@SelectProvider(type = StaffProvider.class, method = "queryForOption")
	List<Map<String, Object>> queryForOption(QueryStaffEvt evt);

	@Select("select t1.ID AS Id,t1.ROLE_NAME AS roleName,t1.ROLE_REMARK AS roleRemark,t1.SYS_CODE AS sysCode,t1.STATUS AS status,t1.CREATE_TIME AS createTime,t1.UPDATE_TIME AS updateTime,t1.OPERATOR AS operator,t1.CREATOR as creator from ts_role_info t1 left join tr_staff_role t2 on t1.ID=t2.ROLE_ID "
			+ "where t2.staff_id=#{id}")
	List<RoleInfo> queryRolesByStaff(QueryStaffEvt queryStaffEvt);

	@SelectProvider(type = StaffProvider.class, method = "queryByTaskpms")
	List<StaffTaskInfo> queryByTaskpms(QueryStaffEvt evt);
}
