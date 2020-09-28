package cn.com.doone.tx.cloud.service.user.evt.staffGroup;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;

import java.io.Serializable;

/**
 * Created by liujx on 2017/2/18 0018.
 */
public class DeleteStaffGroupEvt extends BaseEvt implements Serializable {

	private static final long serialVersionUID = -7334057878109601943L;
	private Long id;

	/** 用户组类型 */
	private Integer groupType;

	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getGroupType() {
		return groupType;
	}

	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}
}
