package cn.com.doone.tx.cloud.service.user.info;



import java.io.Serializable;
import java.util.Date;
import java.util.List;

import cn.com.doone.tx.cloud.tool.login.bean.MenuInfo;

/**
 * liujx 人员信息响应类
 */
public class StaffInfo implements Serializable {

	private static final long serialVersionUID = -6145447086877060589L;
	private Long id;

	private String staffCode;

	private String staffName;

	private String passWd;

	private String contractTel;

	private Long attchId;

	private String scope;

	private Integer sex;

	private String status;

	private Date createTime;

	private Date updateTime;

	private Long operator;

	private String isSuperManager;

	private String creatorName;

	/** 类型 */
	private String staffType;

	// 1、登录ticket
	private String loginTicket;
	// 2、用户组信息
	/** 用户组 id */
	private Long groupId;
	/** 用户组名称 */
	private String groupName;
	/** 省编码 */
	private String provinceCode;
	/** 市编码 */
	private String cityCode;
	/** 区域编码 */
	private String areaCode;
	/** 街道编码 */
	private String streetCode;
	/** 业务系统编码 */
	private String sysCode;
	/** 登录页面 */
	private String loginUrl;
	/** 认证成功页面 */
	private String successUrl;
	/** 密码修改页面 */
	private String pwdUrl;
	/** 单点超时 单位：秒 */
	private String overSeconds;
	/** 延长登录 单位：分钟 */
	private String extendMin;

	// 3、用户角色信息
	private List<RoleInfo> roleInfoList;

	// 4、用户菜单、按钮信息
	private List<MenuInfo> menuInfoList;

	// 国家编码
	private List<String> countryCodes;

	// 国家编码
	private String countryCode;

	// 商户id
	private Long merchantId;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getLoginTicket() {
		return loginTicket;
	}

	public void setLoginTicket(String loginTicket) {
		this.loginTicket = loginTicket;
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

	public String getPassWd() {
		return passWd;
	}

	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}

	public String getContractTel() {
		return contractTel;
	}

	public void setContractTel(String contractTel) {
		this.contractTel = contractTel;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getOperator() {
		return operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getStreetCode() {
		return streetCode;
	}

	public void setStreetCode(String streetCode) {
		this.streetCode = streetCode;
	}

	public List<RoleInfo> getRoleInfoList() {
		return roleInfoList;
	}

	public void setRoleInfoList(List<RoleInfo> roleInfoList) {
		this.roleInfoList = roleInfoList;
	}

	public List<MenuInfo> getMenuInfoList() {
		return menuInfoList;
	}

	public void setMenuInfoList(List<MenuInfo> menuInfoList) {
		this.menuInfoList = menuInfoList;
	}

	public String getIsSuperManager() {
		return isSuperManager;
	}

	public void setIsSuperManager(String isSuperManager) {
		this.isSuperManager = isSuperManager;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Long getAttchId() {
		return attchId;
	}

	public void setAttchId(Long attchId) {
		this.attchId = attchId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getPwdUrl() {
		return pwdUrl;
	}

	public void setPwdUrl(String pwdUrl) {
		this.pwdUrl = pwdUrl;
	}

	public String getOverSeconds() {
		return overSeconds;
	}

	public void setOverSeconds(String overSeconds) {
		this.overSeconds = overSeconds;
	}

	public String getExtendMin() {
		return extendMin;
	}

	public void setExtendMin(String extendMin) {
		this.extendMin = extendMin;
	}

	public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	public List<String> getCountryCodes() {
		return countryCodes;
	}

	public void setCountryCodes(List<String> countryCodes) {
		this.countryCodes = countryCodes;
	}

	@Override
	public String toString() {
		return "StaffInfo [id=" + id + ", staffCode=" + staffCode + ", staffName=" + staffName + ", passWd=" + passWd
				+ ", contractTel=" + contractTel + ", attchId=" + attchId + ", scope=" + scope + ", sex=" + sex
				+ ", status=" + status + ", createTime=" + createTime + ", updateTime=" + updateTime + ", operator="
				+ operator + ", isSuperManager=" + isSuperManager + ", creatorName=" + creatorName + ", staffType="
				+ staffType + ", loginTicket=" + loginTicket + ", groupId=" + groupId + ", groupName=" + groupName
				+ ", provinceCode=" + provinceCode + ", cityCode=" + cityCode + ", areaCode=" + areaCode
				+ ", streetCode=" + streetCode + ", sysCode=" + sysCode + ", loginUrl=" + loginUrl + ", successUrl="
				+ successUrl + ", pwdUrl=" + pwdUrl + ", overSeconds=" + overSeconds + ", extendMin=" + extendMin
				+ ", roleInfoList=" + roleInfoList + ", menuInfoList=" + menuInfoList + ", countryCodes=" + countryCodes
				+ ", merchantId=" + merchantId + "]";
	}

}
