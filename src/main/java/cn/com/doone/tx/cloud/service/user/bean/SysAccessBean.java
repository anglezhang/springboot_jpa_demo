package cn.com.doone.tx.cloud.service.user.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;

@Entity
@Table(name = "sys_connect_info")
@SequenceGenerator(name = "common_generator", sequenceName = "s_sys_connect_info", allocationSize = 1, initialValue = 1)
public class SysAccessBean extends PlatBaseEntity implements Serializable {

	private static final long serialVersionUID = 2679208449094888912L;

	/** 系统名称 */
	@Column(name = "SYS_NAME", length = 200, nullable = false)
	private String sysName;

	/** 系统编码 */
	@Column(name = "SYS_CODE", length = 20, nullable = true)
	private String sysCode;

	/** 密钥 */
	@Column(name = "SYS_KEY", length = 100, nullable = true)
	private String sysKey;

	/** 登录页面地址 */
	@Column(name = "LOGIN_URL", length = 100, nullable = true)
	private String loginUrl;

	/** 认证成功页面地址 */
	@Column(name = "SUCCESS_URL", length = 100, nullable = true)
	private String successUrl;

	/** 密码修改页面地址 */
	@Column(name = "PWD_URL", length = 100, nullable = true)
	private String pwdUrl;

	/** 超出时间 */
	@Column(name = "OVER_SECONDS", length = 4, nullable = true)
	private String overSeconds;

	/** 延长登录会话有效时间 */
	@Column(name = "EXTEND_MIN", length = 4, nullable = true)
	private String extendMin;

	/** 菜单ID */
	@Column(name = "MENU_ID", length = 10, nullable = true)
	private Long menuId;

	/** 是否管理平台，是Y,否N */
	@Column(name = "IS_PLAT", length = 2, nullable = true)
	private String isPlat;

	@Override
	public String toString() {
		return "SysAccessBean [sysName=" + sysName + ", sysCode=" + sysCode + ", sysKey=" + sysKey + ", loginUrl="
				+ loginUrl + ", successUrl=" + successUrl + ", pwdUrl=" + pwdUrl + ", overSeconds=" + overSeconds
				+ ", extendMin=" + extendMin + ", menuId=" + menuId + ", isPlat=" + isPlat + ", toString()="
				+ super.toString() + "]";
	}

	public String getIsPlat() {
		return isPlat;
	}

	public void setIsPlat(String isPlat) {
		this.isPlat = isPlat;
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

}
