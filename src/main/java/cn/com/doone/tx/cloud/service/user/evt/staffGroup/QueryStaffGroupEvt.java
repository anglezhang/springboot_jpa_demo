package cn.com.doone.tx.cloud.service.user.evt.staffGroup;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;

import java.io.Serializable;

/**
 * Created by liujx on 2017/2/18 0018.
 */
public class QueryStaffGroupEvt extends QueryEvt implements Serializable {

	private static final long serialVersionUID = 5155720874860945242L;
	private Long id;

	private Long parentId;

	private String groupName;

	private Integer groupType;

	private String status;

	private Long staffId;
	
	/** 是否超级管理员 */
	private String isSuperManager;

	/** 创建人 */
	private Long creator;

	/** 为空则默认模糊搜索，非空比如传Y则为 = 搜索 */
	private String isAccurate;

	/** 查询类型 */
	private String type;
	
	private Long excludeParentId;
	
	public Long getExcludeParentId() {
		return excludeParentId;
	}

	public void setExcludeParentId(Long excludeParentId) {
		this.excludeParentId = excludeParentId;
	}

	public String getIsAccurate() {
		return isAccurate;
	}

	public void setIsAccurate(String isAccurate) {
		this.isAccurate = isAccurate;
	}

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

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getGroupType() {
		return groupType;
	}

	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
