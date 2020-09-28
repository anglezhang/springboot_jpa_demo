package cn.com.doone.tx.cloud.service.user.evt.operateLog;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

public class AddOperateLogEvt extends BaseEvt {

	/** 操作URL */
	@Field(nullable = false, length = 255, value = "操作URL")
	private String operateUrl;
	@Field(nullable = true, length = 255, value = "操作说明")
	private String operateDesc;
	/** 操作入参 */
	@Field(nullable = true, length = 4000, value = "操作入参")
	private String inParam;
	/** 操作出参 */
	@Field(nullable = true, length = 4000, value = "操作出参")
	private String outParam;
	/** 操作人员名称 */
	@Field(nullable = true, length = 255, value = "操作人员名称")
	private String operateName;
	/** 操作IP */
	@Field(nullable = true, length = 50, value = "操作IP")
	private String operateIp;
	/** 操作结果 */
	private String operateResult;
	/** 失败原因 */
	@Field(nullable = true, length = 1000, value = "失败原因")
	private String errMsg;
	
	/**
	 * 系统编号
	 */
	private String systemCode;
	/**
	 * 菜单编码
	 */
	private String menuCode;

	public String getOperateUrl() {
		return operateUrl;
	}

	public void setOperateUrl(String operateUrl) {
		this.operateUrl = operateUrl;
	}

	public String getInParam() {
		return inParam;
	}

	public void setInParam(String inParam) {
		this.inParam = inParam;
	}

	public String getOutParam() {
		return outParam;
	}

	public void setOutParam(String outParam) {
		this.outParam = outParam;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public String getOperateIp() {
		return operateIp;
	}

	public void setOperateIp(String operateIp) {
		this.operateIp = operateIp;
	}

	public String getOperateResult() {
		return operateResult;
	}

	public void setOperateResult(String operateResult) {
		this.operateResult = operateResult;
	}

	public String getOperateDesc() {
		return operateDesc;
	}

	public void setOperateDesc(String operateDesc) {
		this.operateDesc = operateDesc;
	}
	

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	
	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	
	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	@Override
	public String toString() {
		return "AddOperateLogEvt [operateUrl=" + operateUrl + ", inParam="
				+ inParam + ", outParam=" + outParam + ", operateName="
				+ operateName + ", operateIp=" + operateIp + ", operateResult="
				+ operateResult + "]";
	}

}
