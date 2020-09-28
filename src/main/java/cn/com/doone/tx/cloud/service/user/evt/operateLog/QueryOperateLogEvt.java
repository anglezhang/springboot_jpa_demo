package cn.com.doone.tx.cloud.service.user.evt.operateLog;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;

public class QueryOperateLogEvt extends QueryEvt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3679114343007694154L;
	/** 操作时间 */
	private String startCreateTime;
	/** 操作时间 */
	private String endCreateTime;
	/** 操作人员名称 */
	private String operateName;
	/** 状态 */
	private String operateResult;
	
	/**
	 * 系统编码
	 */
	private String systemCode;
	/**
	 * 菜单编码
	 */
	private String menuCode;
	
	private String ids;
	
	private Long groupId;
	
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getStartCreateTime() {
		return startCreateTime;
	}

	public void setStartCreateTime(String startCreateTime) {
		this.startCreateTime = startCreateTime;
	}

	public String getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public String getOperateResult() {
		return operateResult;
	}

	public void setOperateResult(String operateResult) {
		this.operateResult = operateResult;
	}
	
	

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	
	

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	@Override
	public String toString() {
		return "QueryOperateLogEvt [startCreateTime=" + startCreateTime
				+ ", endCreateTime=" + endCreateTime + ", operateName="
				+ operateName + ", operateResult=" + operateResult + "]";
	}

}
