package cn.com.doone.tx.cloud.service.user.evt.groupStaff;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/17 0017.
 */
public class AddGroupStaffEvt extends BaseEvt implements Serializable {

	private static final long serialVersionUID = 3110175308717455975L;
	@Field(value = "用户组ID", length = 10, nullable = false)
	private Long groupId;

	@Field(value = "用户ID", length = 10, nullable = false)
	private Long staffId;

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

}
