package cn.com.doone.tx.cloud.service.user.info;

import java.io.Serializable;

public class SysAccessInfo implements Serializable {

	private static final long serialVersionUID = -6389349415679794877L;

	/** 系统ID */
	private Long id;

	/** 系统名称 */
	private String sysName;

	/** 系统编码 */
	private String sysCode;

	/** 密钥 */
	private String sysKey;

	/** 登录页面地址 */
	private String loginUrl;

	/** 认证成功页面地址 */
	private String successUrl;

	/** 密码修改页面地址 */
	private String pwdUrl;

	/** 超出时间 */
	private String overSeconds;

	/** 延长登录会话有效时间 */
	private String extendMin;

	/** 菜单ID */
	private Long menuId;

	/** 状态 */
	private String status;

	/** 创建人 */
	private Long creator;

	/** 创建时间 */
	private String createTime;

	/** 操作人 */
	private Long operator;

	/** 修改时间 */
	private String updateTime;

	/** 是否管理平台，是Y,否N */
	private String isPlat;

	public String getIsPlat() {
		return isPlat;
	}

	public void setIsPlat(String isPlat) {
		this.isPlat = isPlat;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getSysKey() {
		return sysKey;
	}

	public void setSysKey(String sysKey) {
		this.sysKey = sysKey;
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

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getOperator() {
		return operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
