package cn.com.doone.tx.cloud.service.user.evt.staff;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by liujx on 2017/2/17 0017.
 */
public class QueryStaffEvt extends QueryEvt implements Serializable {

	private static final long serialVersionUID = -2798459979683527838L;
	/** 主键 */
	@Field(value = "登录账号", length = 10, nullable = true)
	private Long id;
	/** 登录账号 */
	@Field(value = "登录账号", length = 32, nullable = true)
	private String staffCode;
	/** 账号名称 */
	@Field(value = "账号名称", length = 32, nullable = true)
	private String staffName;
	/** 联系电话 */
	@Field(value = "联系电话", length = 32, nullable = true)
	private String contractTel;
	/** 性别 */
	private Integer sex;
	/** 用户组groupId */
	private Long groupId;
	/** 用户组groupIds 多个用户组id组成的字符串如："33,96,77" */
	private String groupIds;
	/** 角色roleId */
	private Long roleId;
	/** 角色roleIds 多个角色id组成的字符串如："33,96,77" */
	private String roleIds;
	/** 密码 */
	private String passWd;
	/** 创建者 */
	private Long creator;
	/** staffId数组 */
	private Long[] ids;
	/** 登录ticket */
	private String loginTicket;
	/** 是否是超级管理员 */
	private String isSuperManager;

	private String pushType;

	private String pushMethod;// 推送方式（过滤用户）
	/** 类型 */
	private String staffType;
	/** 状态 */
	private String status;
	/** 语言类型 */
	private String languageType;
	/** 国家编码 */
	private List<String> countryCodes;
	/** 国家编码 */
	private String countryCode;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public List<String> getCountryCodes() {
		return countryCodes;
	}

	public void setCountryCodes(List<String> countryCodes) {
		this.countryCodes = countryCodes;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/** 扩展信息 **/
	private Map<String, Object> extendMap;

	/** 为空则默认模糊搜索，非空比如传Y则为 = 搜索 */
	private String isAccurate;

	/** 查询类型 */
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsSuperManager() {
		return isSuperManager;
	}

	public void setIsSuperManager(String isSuperManager) {
		this.isSuperManager = isSuperManager;
	}

	public String getPassWd() {
		return passWd;
	}

	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getContractTel() {
		return contractTel;
	}

	public void setContractTel(String contractTel) {
		this.contractTel = contractTel;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public String getLoginTicket() {
		return loginTicket;
	}

	public void setLoginTicket(String loginTicket) {
		this.loginTicket = loginTicket;
	}

	public Map<String, Object> getExtendMap() {
		return extendMap;
	}

	public void setExtendMap(Map<String, Object> extendMap) {
		this.extendMap = extendMap;
	}

	public String getIsAccurate() {
		return isAccurate;
	}

	public void setIsAccurate(String isAccurate) {
		this.isAccurate = isAccurate;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}

	public String getPushType() {
		return pushType;
	}

	public void setPushType(String pushType) {
		this.pushType = pushType;
	}

	public String getPushMethod() {
		return pushMethod;
	}

	public void setPushMethod(String pushMethod) {
		this.pushMethod = pushMethod;
	}

	public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}

}
