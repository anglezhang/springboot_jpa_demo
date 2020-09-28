package cn.com.doone.tx.cloud.service.user.evt.staffGroup;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;

/**
 * yecz
 */
public class DeleteStaffGroupExtendEvt extends BaseEvt implements Serializable {

	private static final long serialVersionUID = -2423975567336008830L;

	/** groupId **/
	@Field(nullable = true, length = 19, value = "groupId")
	private Long groupId;
	/** status **/
	@Field(nullable = true, length = 11, value = "status")
	private String status;

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}