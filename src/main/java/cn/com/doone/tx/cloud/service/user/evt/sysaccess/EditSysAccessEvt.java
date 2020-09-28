package cn.com.doone.tx.cloud.service.user.evt.sysaccess;

import java.io.Serializable;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

public class EditSysAccessEvt extends BaseEvt implements Serializable {

	private static final long serialVersionUID = -8704424539036195879L;

	/** 系统ID */
	@Field(nullable = false, value = "系统ID", length = 10)
	private Long id;

	/** 系统名称 */
	@Field(nullable = true, value = "系统名称", length = 200)
	private String sysName;

	/** 系统编码 */
	@Field(nullable = true, value = "系统编码", length = 20)
	private String sysCode;

	/** 密钥 */
	@Field(nullable = true, value = "密钥", length = 100)
	private String sysKey;

	/** 登录页面地址 */
	@Field(nullable = true, value = "登录页面地址", length = 100)
	private String loginUrl;

	/** 认证成功页面地址 */
	@Field(nullable = true, value = "认证成功页面地址", length = 100)
	private String successUrl;

	/** 密码修改页面地址 */
	@Field(nullable = true, value = "密码修改页面地址", length = 100)
	private String pwdUrl;

	/** 超出时间 */
	@Field(nullable = true, value = "超出时间", length = 4)
	private String overSeconds;

	/** 延长登录会话有效时间 */
	@Field(nullable = true, value = "延长登录会话有效时间", length = 4)
	private String extendMin;

	/** 菜单ID */
	@Field(nullable = true, value = "菜单ID", length = 10)
	private Long menuId;

	/** 创建人 */
	@Field(nullable = true, value = "创建人", length = 10)
	private Long creator;

	/** 是否管理平台，是Y,否N */
	private String isPlat;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

}
