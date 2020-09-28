package cn.com.doone.tx.cloud.service.user.dao.provider;


import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import cn.com.doone.tx.cloud.service.user.evt.StaffRole.DeleteStaffRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.StaffRole.QueryStaffRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.loginlock.QueryLoginLockEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.EditStaffEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.QueryStaffEvt;

public class StaffProvider {

	private final String TBL_STAFF_INFO = "ts_staff_info";
	private final String TBL_GROUP_STAFF = "tr_group_staff";
	private final String TBL_STAFF_GROUP = "ts_staff_group";
	private final String TBL_STAFF_ROLE = "tr_staff_role";
	private final String TBL_ROLE_INFO = "ts_role_info";
	private final String TBL_LOGIN_LOCK = "tf_login_lock";
	private final String TBL_STAFF_REGION = "STAFF_REGION";// 用户区域表

	private final String TS_STAFF_EXTEND_INFO = "ts_staff_extend_info";

	public String queryCount(QueryStaffEvt queryStaffEvt) {
		SQL sql = new SQL().SELECT("count(*)").FROM(TBL_STAFF_INFO + " t1")
				.LEFT_OUTER_JOIN(TBL_GROUP_STAFF + " t2 ON t1.ID=t2.STAFF_ID");
		Long id = queryStaffEvt.getId();
		if (id != null) {
			sql.WHERE("t1.ID = #{id}");
		}
		Long groupId = queryStaffEvt.getGroupId();
		if (groupId != null) {
			sql.WHERE("t2.GROUP_ID = #{groupId}");
		}
		/** 用户名称 */
		String staffName = queryStaffEvt.getStaffName();
		if (StringUtils.isNotBlank(staffName)) {
			queryStaffEvt.setStaffName("%" + staffName + "%");
			sql.WHERE("t1.STAFF_NAME like #{staffName}");
		}
		/** 登录账号 */
		String staffCode = queryStaffEvt.getStaffCode();
		if (StringUtils.isNotBlank(staffCode)) {
			queryStaffEvt.setStaffCode("%" + staffCode + "%");
			sql.WHERE("t1.STAFF_CODE like #{staffCode}");
		}
		/** 手机号码 */
		String contractTel = queryStaffEvt.getContractTel();
		if (StringUtils.isNotBlank(contractTel)) {
			queryStaffEvt.setContractTel("%" + contractTel + "%");
			sql.WHERE("t1.CONTACT_TEL like #{contractTel}");
		}
		String staffType = queryStaffEvt.getStaffType();
		if (staffType != null && !"".equals(staffType)) {
			sql.WHERE("t1.STAFF_TYPE = #{staffType}");
		}
		Long creator = queryStaffEvt.getCreator();
		if (creator != null) {
			sql.WHERE("t1.creator=#{creator}");
		}
		String pushType = queryStaffEvt.getPushType();
		if (null != pushType && !"".equals(pushType)) {
			sql.WHERE("(t1.email <> '' or t1.contact_tel <> '')");
		}
		String pushMethod = queryStaffEvt.getPushMethod();
		if (null != pushMethod && !"".equals(pushMethod)) {
			sql.WHERE(" t1." + pushMethod + " <> '' ");
		}
		// D为删除状态
//		sql.WHERE("t1.status!='D'");
		String status = queryStaffEvt.getStatus();
		if (StringUtils.isNotBlank(status)) {
			sql.WHERE("t1.status=#{status}");
		} else {
			sql.WHERE("t1.status <> 'D'");
		}
		System.out.println(sql.toString());
		return sql.toString();
	}

	/**
	 * 查询出用户信息 并关联出用户组信息
	 */
	public String queryByParam(QueryStaffEvt queryStaffEvt) {
		// t1.EMAIL email,
		SQL sql = new SQL().SELECT(
				"t1.ID,t1.STAFF_CODE staffCode,t1.STAFF_NAME staffName,t1.STAFF_TYPE staffType,t1.CONTACT_TEL contractTel,t1.ATTCHID attchId,"
						+ "t1.ISSUPERMANAGER isSuperManager,t1.SEX sex,t1.STATUS status,t1.CREATE_TIME createTime,t1.UPDATE_TIME updateTime,t1.OPERATOR operator,"
						+ "t2.GROUP_ID groupId,t3.GROUP_NAME groupName,t3.PROVINCE_CODE provinceCode,t3.CITY_CODE cityCode,"
						+ "t3.AREA_CODE areaCode,t3.STREET_CODE streetCode,t4.STAFF_NAME as creatorName")
				.FROM(TBL_STAFF_INFO + " t1").LEFT_OUTER_JOIN(TBL_GROUP_STAFF + " t2 ON t1.ID=t2.STAFF_ID")
				.LEFT_OUTER_JOIN(TBL_STAFF_GROUP + " t3 ON t2.GROUP_ID=t3.ID")
				.LEFT_OUTER_JOIN(TBL_STAFF_INFO + " t4 ON t1.creator=t4.ID");

		Long roleId = queryStaffEvt.getRoleId();
		String roleIds = queryStaffEvt.getRoleIds();
		if (roleId != null || StringUtils.isNotBlank(roleIds)) {
			sql.LEFT_OUTER_JOIN(TBL_STAFF_ROLE + " t5 ON t1.ID=t5.STAFF_ID");
			// sql.LEFT_OUTER_JOIN(TBL_ROLE_INFO+" t6 ON t5.ROLE_ID=t6.ID");
		}

		Long id = queryStaffEvt.getId();
		if (id != null) {
			sql.WHERE("t1.ID = #{id}");
		}
		Long groupId = queryStaffEvt.getGroupId();
		if (groupId != null) {
			sql.WHERE("t2.GROUP_ID = #{groupId}");
		}
		if (roleId != null) {
			sql.WHERE("t5.ROLE_ID = #{roleId}");
			sql.WHERE("t5.status='E'");
		}
		if (StringUtils.isNotBlank(roleIds)) {
			sql.WHERE("t5.ROLE_ID in (" + roleIds + ")");
			sql.WHERE("t5.status='E'");
		}
		/** 用户名称 */
		String staffName = queryStaffEvt.getStaffName();
		String isAccurate = queryStaffEvt.getIsAccurate();
		if (StringUtils.isNotBlank(staffName)) {
			if (StringUtils.isBlank(isAccurate)) {
				queryStaffEvt.setStaffName("%" + staffName + "%");
				sql.WHERE("t1.STAFF_NAME like #{staffName}");
			} else {
				sql.WHERE("t1.STAFF_NAME=#{staffName}");
			}
		}
		/** 登录账号 */
		String staffCode = queryStaffEvt.getStaffCode();
		if (StringUtils.isNotBlank(staffCode)) {
			if (StringUtils.isBlank(isAccurate)) {
				queryStaffEvt.setStaffCode("%" + staffCode + "%");
				sql.WHERE("t1.STAFF_CODE like #{staffCode}");
			} else {
				sql.WHERE("t1.STAFF_CODE=#{staffCode}");
			}
		}
		/** 手机号码 */
		String contractTel = queryStaffEvt.getContractTel();
		if (StringUtils.isNotBlank(contractTel)) {
			queryStaffEvt.setContractTel("%" + contractTel + "%");
			sql.WHERE("t1.CONTACT_TEL like #{contractTel}");
		}
		String staffType = queryStaffEvt.getStaffType();
		if (staffType != null && !"".equals(staffType)) {
			sql.WHERE("t1.STAFF_TYPE = #{staffType}");
		}

		Long creator = queryStaffEvt.getCreator();
		if (creator != null) {
			sql.WHERE("t1.creator=#{creator}");
		}
		String pushType = queryStaffEvt.getPushType();
		if (null != pushType && !"".equals(pushType)) {
			sql.WHERE("(t1.email <> '' or t1.contact_tel <> '')");
		}
		String pushMethod = queryStaffEvt.getPushMethod();
		if (null != pushMethod && !"".equals(pushMethod)) {
			sql.WHERE(" t1." + pushMethod + " <> '' ");
		}
		String status = queryStaffEvt.getStatus();
		if (StringUtils.isNotBlank(status)) {
			sql.WHERE("t1.status=#{status}");
		} else {
			sql.WHERE("t1.status <> 'D'");
		}
		// D为删除状态
		// sql.WHERE("t1.status!='D'");
		sql.WHERE("t2.status='E'");
		// 超级管理员可查看禁用的用户
		String isSuperManager = queryStaffEvt.getIsSuperManager();
		if (StringUtils.isNotBlank(isSuperManager) && "1".equals(isSuperManager)) {
			sql.WHERE("t3.status='E' or t3.status='F'");
		} else {
			sql.WHERE("t3.status='E'");
		}
		sql.ORDER_BY("t1.ID desc");
		return sql.toString();
	}

	/**
	 * 查询出用户信息 并关联出用户组信息 与用户区域表做关联，根据传参国家编码删选
	 */
	public String queryByRegion(QueryStaffEvt queryStaffEvt) {
		// t1.EMAIL email,
		SQL sql = new SQL().SELECT(
				"t1.ID,t1.STAFF_CODE staffCode,t1.STAFF_NAME staffName,t1.STAFF_TYPE staffType,t1.CONTACT_TEL contractTel,t1.ATTCHID attchId,"
						+ "t1.ISSUPERMANAGER isSuperManager,t1.SEX sex,t1.STATUS status,t1.CREATE_TIME createTime,t1.UPDATE_TIME updateTime,t1.OPERATOR operator,"
						+ "t2.GROUP_ID groupId,t3.GROUP_NAME groupName,t3.PROVINCE_CODE provinceCode,t3.CITY_CODE cityCode,"
						+ "t3.AREA_CODE areaCode,t3.STREET_CODE streetCode,t4.STAFF_NAME as creatorName")
				.FROM(TBL_STAFF_INFO + " t1").LEFT_OUTER_JOIN(TBL_GROUP_STAFF + " t2 ON t1.ID=t2.STAFF_ID")
				.LEFT_OUTER_JOIN(TBL_STAFF_GROUP + " t3 ON t2.GROUP_ID=t3.ID")
				.LEFT_OUTER_JOIN(TBL_STAFF_INFO + " t4 ON t1.creator=t4.ID");
		sql.LEFT_OUTER_JOIN(TBL_STAFF_REGION + " tsr ON tsr.STAFF_ID=t1.id");
		Long roleId = queryStaffEvt.getRoleId();
		String roleIds = queryStaffEvt.getRoleIds();
		if (roleId != null || StringUtils.isNotBlank(roleIds)) {
			sql.LEFT_OUTER_JOIN(TBL_STAFF_ROLE + " t5 ON t1.ID=t5.STAFF_ID");
			// sql.LEFT_OUTER_JOIN(TBL_ROLE_INFO+" t6 ON t5.ROLE_ID=t6.ID");
		}

		Long id = queryStaffEvt.getId();
		if (id != null) {
			sql.WHERE("t1.ID = #{id}");
		}
		Long groupId = queryStaffEvt.getGroupId();
		if (groupId != null) {
			sql.WHERE("t2.GROUP_ID = #{groupId}");
		}
		if (roleId != null) {
			sql.WHERE("t5.ROLE_ID = #{roleId}");
			sql.WHERE("t5.status='E'");
		}
		if (StringUtils.isNotBlank(roleIds)) {
			sql.WHERE("t5.ROLE_ID in (" + roleIds + ")");
			sql.WHERE("t5.status='E'");
		}
		/** 用户名称 */
		String staffName = queryStaffEvt.getStaffName();
		String isAccurate = queryStaffEvt.getIsAccurate();
		if (StringUtils.isNotBlank(staffName)) {
			if (StringUtils.isBlank(isAccurate)) {
				queryStaffEvt.setStaffName("%" + staffName + "%");
				sql.WHERE("t1.STAFF_NAME like #{staffName}");
			} else {
				sql.WHERE("t1.STAFF_NAME=#{staffName}");
			}
		}
		/** 登录账号 */
		String staffCode = queryStaffEvt.getStaffCode();
		if (StringUtils.isNotBlank(staffCode)) {
			if (StringUtils.isBlank(isAccurate)) {
				queryStaffEvt.setStaffCode("%" + staffCode + "%");
				sql.WHERE("t1.STAFF_CODE like #{staffCode}");
			} else {
				sql.WHERE("t1.STAFF_CODE=#{staffCode}");
			}
		}
		/** 手机号码 */
		String contractTel = queryStaffEvt.getContractTel();
		if (StringUtils.isNotBlank(contractTel)) {
			queryStaffEvt.setContractTel("%" + contractTel + "%");
			sql.WHERE("t1.CONTACT_TEL like #{contractTel}");
		}
		String staffType = queryStaffEvt.getStaffType();
		if (staffType != null && !"".equals(staffType)) {
			sql.WHERE("t1.STAFF_TYPE = #{staffType}");
		}

		Long creator = queryStaffEvt.getCreator();
		if (creator != null) {
			sql.WHERE("t1.creator=#{creator}");
		}
		String pushType = queryStaffEvt.getPushType();
		if (null != pushType && !"".equals(pushType)) {
			sql.WHERE("(t1.email <> '' or t1.contact_tel <> '')");
		}
		String pushMethod = queryStaffEvt.getPushMethod();
		if (null != pushMethod && !"".equals(pushMethod)) {
			sql.WHERE(" t1." + pushMethod + " <> '' ");
		}
		String status = queryStaffEvt.getStatus();
		if (StringUtils.isNotBlank(status)) {
			sql.WHERE("t1.status=#{status}");
		}
		// D为删除状态
		// sql.WHERE("t1.status!='D'");
		sql.WHERE("t2.status='E'");
		// 超级管理员可查看禁用的用户
		String isSuperManager = queryStaffEvt.getIsSuperManager();
		if (StringUtils.isNotBlank(isSuperManager) && "1".equals(isSuperManager)) {
			sql.WHERE("(t3.status='E' or t3.status='F')");
		} else {
			sql.WHERE("t3.status='E'");
		}
		// 是否超级管理员
		if (StringUtils.isNotBlank(isSuperManager)) {
			if (!"1".equals(isSuperManager)) {
				// 国家编码过滤编码查询
				String countryCode = queryStaffEvt.getCountryCode();
				if (StringUtils.isNotBlank(countryCode)) {
					sql.WHERE("tsr.COUNTRY_CODE  = #{countryCode}");
					sql.WHERE("tsr.STATUS  ='E'");
				}
			}
		}

		sql.ORDER_BY("t1.ID desc");
		return sql.toString();
	}

	/**
	 * 查询出用户信息 并关联出用户组信息 查出拥有环节权限的用户
	 */
	public String queryByTaskpms(QueryStaffEvt queryStaffEvt) {
		// t1.EMAIL email,
		SQL sql = new SQL().SELECT(
				"t1.ID,tsr.COUNTRY_CODE")
				.FROM(TBL_STAFF_INFO + " t1").LEFT_OUTER_JOIN(TBL_GROUP_STAFF + " t2 ON t1.ID=t2.STAFF_ID")
				.LEFT_OUTER_JOIN(TBL_STAFF_GROUP + " t3 ON t2.GROUP_ID=t3.ID")
				.LEFT_OUTER_JOIN(TBL_STAFF_INFO + " t4 ON t1.creator=t4.ID");
		sql.LEFT_OUTER_JOIN(TBL_STAFF_REGION + " tsr ON tsr.STAFF_ID=t1.id");
		Long roleId = queryStaffEvt.getRoleId();
		String roleIds = queryStaffEvt.getRoleIds();
		if (roleId != null || StringUtils.isNotBlank(roleIds)) {
			sql.LEFT_OUTER_JOIN(TBL_STAFF_ROLE + " t5 ON t1.ID=t5.STAFF_ID");
			// sql.LEFT_OUTER_JOIN(TBL_ROLE_INFO+" t6 ON t5.ROLE_ID=t6.ID");
		}

		Long id = queryStaffEvt.getId();
		if (id != null) {
			sql.WHERE("t1.ID = #{id}");
		}
		Long groupId = queryStaffEvt.getGroupId();
		if (groupId != null) {
			sql.WHERE("t2.GROUP_ID = #{groupId}");
		}
		if (roleId != null) {
			sql.WHERE("t5.ROLE_ID = #{roleId}");
			sql.WHERE("t5.status='E'");
		}
		if (StringUtils.isNotBlank(roleIds)) {
			sql.WHERE("t5.ROLE_ID in (" + roleIds + ")");
			sql.WHERE("t5.status='E'");
		}

		String staffType = queryStaffEvt.getStaffType();
		if (staffType != null && !"".equals(staffType)) {
			sql.WHERE("t1.STAFF_TYPE = #{staffType}");
		}

		String status = queryStaffEvt.getStatus();
		if (StringUtils.isNotBlank(status)) {
			sql.WHERE("t1.status=#{status}");
		}
		// D为删除状态
		// sql.WHERE("t1.status!='D'");
		sql.WHERE("t2.status='E'");

		sql.ORDER_BY("tsr.COUNTRY_CODE desc");
		return sql.toString();
	}

	/** 根据扩展信息查用户 */
	public String queryByExtendParam(String sqlWhere) {
		SQL sql = new SQL()
				.SELECT("t1.ID,t1.STAFF_CODE staffCode,t1.STAFF_NAME staffName,"
						+ "t1.CONTACT_TEL contractTel,t1.ATTCHID attchId,"
						+ "t1.SEX,t1.STATUS,t1.CREATE_TIME createTime,t1.UPDATE_TIME updateTime,t1.OPERATOR,"
						+ "t4.ID groupId,t4.GROUP_NAME groupName")
				.FROM(TBL_STAFF_INFO + " t1").LEFT_OUTER_JOIN(TS_STAFF_EXTEND_INFO + " t2 on t2.STAFF_ID = t1.ID")
				.LEFT_OUTER_JOIN(TBL_GROUP_STAFF + " t3 ON t3.STAFF_ID=t1.ID")
				.LEFT_OUTER_JOIN(TBL_STAFF_GROUP + " t4 ON t4.ID=t3.GROUP_ID");
		sql.WHERE("t2.STATUS='E'");
		sql.WHERE("t3.STATUS='E'");
		sql.WHERE("t4.STATUS='E'");
		sql.WHERE("1 = 1 " + sqlWhere);
		return sql.toString();
	}

	/** 登录时查询用户信息 */
	public String queryStaffInfo(QueryStaffEvt queryStaffEvt) {
		SQL sql = new SQL()
				.SELECT("t1.ID,t1.STAFF_CODE staffCode,t1.STAFF_NAME staffName,t1.PASSWD,t1.CONTACT_TEL contractTel,"
						+ "t1.ISSUPERMANAGER isSuperManager,t1.SEX,t1.STATUS,t1.CREATE_TIME createTime,t1.UPDATE_TIME updateTime,t1.OPERATOR,"
						+ "t2.GROUP_ID groupId,t3.GROUP_NAME groupName,t3.PROVINCE_CODE provinceCode,t3.CITY_CODE cityCode,"
						+ "t3.AREA_CODE areaCode,t3.STREET_CODE streetCode,t4.STAFF_NAME as creatorName,t1.STAFF_TYPE as staffType,t1.ATTCHID as attchId")
				.FROM(TBL_STAFF_INFO + " t1").LEFT_OUTER_JOIN(TBL_GROUP_STAFF + " t2 ON t1.ID=t2.STAFF_ID")
				.LEFT_OUTER_JOIN(TBL_STAFF_GROUP + " t3 ON t2.GROUP_ID=t3.ID")
				.LEFT_OUTER_JOIN(TBL_STAFF_INFO + " t4 ON t1.creator=t4.ID");
		/** 登录账号 */
		String staffCode = queryStaffEvt.getStaffCode();
		if (StringUtils.isNotBlank(staffCode)) {
			sql.WHERE("t1.STAFF_CODE=#{staffCode}");
		}
		// D为删除状态
		sql.WHERE("t1.status!='D'");
		return sql.toString();
	}

	public String queryUserIsExist(QueryStaffEvt evt) {
		SQL sql = new SQL().SELECT("count(*)").FROM(TBL_STAFF_INFO);
		Long id = evt.getId();
		if (id != null) {
			sql.WHERE("ID != #{id}");
		}
		String staffCode = evt.getStaffCode();
		if (staffCode != null && !"".equals(staffCode)) {
			sql.WHERE("STAFF_CODE = #{staffCode}");
		}
		String staffName = evt.getStaffName();
		if (StringUtils.isNotBlank(staffName)) {
			sql.WHERE("STAFF_NAME = #{staffName}");
		}
		sql.WHERE("(STATUS='E' OR STATUS='F')");// 启用和禁用
		return sql.toString();
	}

	public String doEdit(EditStaffEvt evt) {
		SQL sql = new SQL().UPDATE(TBL_STAFF_INFO);
		if (evt.getStaffName() != null) {
			sql.SET("STAFF_NAME=#{staffName}");
		}
		if (evt.getStaffType() != null && !"".equals(evt.getStaffType())) {
			sql.SET("STAFF_TYPE=#{staffType}");
		}
		if (evt.getContractTel() != null) {
			sql.SET("CONTACT_TEL=#{contractTel}");
		}
		if (evt.getAttchId() != null) {
			sql.SET("ATTCHID=#{attchId}");
		} else if (evt.getIsEdit() != null) {
			sql.SET("ATTCHID=null");
		}
		if (evt.getPassWd() != null) {
			sql.SET("PASSWD=#{passWd}");
		}
		if (evt.getStatus() != null) {
			sql.SET("STATUS=#{status}");
		}
		if (evt.getUpdateTime() != null) {
			sql.SET("UPDATE_TIME=#{updateTime}");
		}
		if (evt.getOperator() != null) {
			sql.SET("OPERATOR=#{operator}");
		}
		sql.WHERE("ID=#{id}");
		return sql.toString();
	}

	/** 查出用户被授权的角色 */
	public String queryRoleByGrand(QueryStaffRoleEvt evt) {
		SQL sql = new SQL()
				.SELECT("r.ID,r.ROLE_NAME roleName,"
						+ "r.ROLE_REMARK roleRemark,r.IS_DEFAULT isDefault,r.STATUS status,"
						+ "r.CREATE_TIME createTime,r.UPDATE_TIME updateTime,r.OPERATOR operator,sr.IS_GRANT isGrant")
				.FROM(TBL_ROLE_INFO + " r");
		sql.LEFT_OUTER_JOIN(TBL_STAFF_ROLE + " sr on r.ID = sr.ROLE_ID");
		sql.WHERE("sr.STAFF_ID = #{staffId}");
		sql.WHERE("r.STATUS='E'");
		sql.WHERE("sr.STATUS='E'");
		if (null != evt.getIsGrant() && evt.getIsGrant() == 1) {
			sql.WHERE("sr.IS_GRANT=1");
		}
		return sql.toString();
	}

	/** 查出用户所创建的角色 */
	public String queryRoleSelfCreate(QueryStaffRoleEvt evt) {
		SQL sql = new SQL()
				.SELECT("r.ID,r.ROLE_NAME roleName,"
						+ "r.ROLE_REMARK roleRemark,r.IS_DEFAULT isDefault,r.STATUS status,"
						+ "r.CREATE_TIME createTime,r.UPDATE_TIME updateTime,r.OPERATOR operator")
				.FROM(TBL_ROLE_INFO + " r");
		sql.WHERE("r.creator = #{staffId}");
		sql.WHERE("r.STATUS='E'");
		return sql.toString();
	}

	public String deleteStaffRole(DeleteStaffRoleEvt evt) {
		SQL sql = new SQL().DELETE_FROM(TBL_STAFF_ROLE);
		if (evt.getStaffId() != null) {
			sql.WHERE("STAFF_ID = #{staffId}");
		}
		if (evt.getRoleId() != null) {
			sql.WHERE("ROLE_ID = #{roleId}");
		}
		return sql.toString();
	}

	/** 查询用户信息(用户组、角色固定) */
	public String queryStaffByGroupAndRole(QueryStaffEvt queryStaffEvt) {
		SQL sql = new SQL().SELECT(
				"t1.ID,t1.STAFF_CODE staffCode,t1.STAFF_NAME staffName,t1.CONTACT_TEL contractTel,t1.ATTCHID attchId,"
						+ "t1.STATUS status,t1.CREATE_TIME createTime,t1.UPDATE_TIME updateTime,t1.OPERATOR,t2.GROUP_ID groupId,"
						+ "t4.GROUP_NAME groupName")
				.FROM(TBL_STAFF_INFO + " t1").LEFT_OUTER_JOIN(TBL_GROUP_STAFF + " t2 ON t2.STAFF_ID=t1.ID")
				.LEFT_OUTER_JOIN(TBL_STAFF_GROUP + " t4 ON t4.ID=t2.GROUP_ID")
				.LEFT_OUTER_JOIN(TBL_STAFF_ROLE + " t3 ON t3.STAFF_ID=t1.ID");
		Long groupId = queryStaffEvt.getGroupId();
		if (groupId != null) {
			sql.WHERE("t2.GROUP_ID = #{groupId}");
		}
		Long roleId = queryStaffEvt.getRoleId();
		if (roleId != null) {
			sql.WHERE("t3.ROLE_ID = #{roleId}");
		}
		sql.WHERE("t1.status!='D'");
		sql.WHERE("t2.status='E'");
		sql.WHERE("t3.status='E'");
		sql.ORDER_BY("t1.CREATE_TIME desc");
		return sql.toString();
	}

	/**
	 * 传入多个groupId 或多个 roleId 查询出用户信息
	 */
	public String queryStaffInfoByGroupOrRole(QueryStaffEvt queryStaffEvt) {
		SQL sql = new SQL().SELECT("*").FROM(TBL_STAFF_INFO + " t1");
		String groupIds = queryStaffEvt.getGroupIds();
		String roleIds = queryStaffEvt.getRoleIds();
		if (StringUtils.isNotBlank(groupIds)) {
			sql.INNER_JOIN(TBL_GROUP_STAFF + " t2 ON t1.ID=t2.STAFF_ID");
			sql.WHERE("t1.ID=#{id}");
			sql.WHERE("t2.GROUP_ID in (" + groupIds + ")");
		} else if (StringUtils.isNotBlank(roleIds)) {
			sql.INNER_JOIN(TBL_STAFF_ROLE + " t3 ON t1.ID=t3.STAFF_ID");
			sql.WHERE("t1.ID=#{id}");
			sql.WHERE("t3.ROLE_ID in (" + roleIds + ")");
		}
		sql.WHERE("t1.status='E'");
		System.out.println(sql.toString());
		return sql.toString();
	}

	public String queryLoginLockInfo(QueryLoginLockEvt evt) {
		SQL sql = new SQL().SELECT("*").FROM(TBL_LOGIN_LOCK);
		if (StringUtils.isNotBlank(evt.getAccount())) {
			sql.WHERE("ACCOUNT =#{account}");
		}
		if (StringUtils.isNotBlank(evt.getAccountType())) {
			sql.WHERE("ACCOUNT_TYPE =#{accountType}");
		}
		if (evt.getCreateTime() != null) {
			sql.WHERE("CREATE_TIME >= #{createTime}");
		}
		sql.WHERE("STATUS='E'");
		return sql.toString();
	}

	/**
	 * 查询出代理商
	 */
	public String queryForOption(QueryStaffEvt queryStaffEvt) {
		SQL sql = new SQL().SELECT("t1.ID,t1.STAFF_NAME").FROM(TBL_STAFF_INFO + " t1");
		if (queryStaffEvt.getStaffType() != null) {
			sql.WHERE("STAFF_TYPE = #{staffType}");
		}
		// D为删除状态
		sql.WHERE("t1.status='E'");
		sql.ORDER_BY("t1.CREATE_TIME desc");
		return sql.toString();
	}

	/** 查出用户角所绑定的角色并获取系统名称 */
	public String queryRoleByStaff(QueryStaffRoleEvt evt) {
		SQL sql = new SQL().SELECT_DISTINCT(
				"t1.ID,t1.ROLE_NAME roleName,t1.ROLE_REMARK roleRemark,t1.IS_DEFAULT isDefault," +
//						"t2.NAME as sysName,"+
						 "t1.STATUS status,t1.CREATE_TIME createTime,t1.UPDATE_TIME updateTime,t1.OPERATOR operator ")
				.FROM(TBL_ROLE_INFO + " t1").LEFT_OUTER_JOIN(TBL_STAFF_ROLE + " t3 on t3.role_id=t1.ID ");
		if (StringUtils.isNotBlank(evt.getLanguageType())) {
			sql
//					.LEFT_OUTER_JOIN("mul_language t2 on t1.sys_code=t2.code and t2.language_type=#{languageType} ")
					.WHERE("t1.id in (select t1.role_id from (select role_id from tr_staff_role t4 where t4.staff_id=#{staffId} and t4.STATUS ='E') t1 )")
					.WHERE("t3.staff_id=#{staffId} and t3.STATUS ='E'");
		} else {
			sql
//					.LEFT_OUTER_JOIN("mul_language t2 on t1.sys_code=t2.code ")
					.WHERE("t1.id in (select t1.role_id from (select role_id from tr_staff_role t4 where t4.staff_id=#{staffId} and t4.STATUS ='E') t1 )")
					.WHERE("t3.staff_id=#{staffId} and t3.STATUS ='E'");
		}
		return sql.toString();
	}

}
