package cn.com.doone.tx.cloud.service.user.evt.staffGroup;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;

/**
 * Created by administrator on 2017/8/28
 */
public class QueryStaffGroupRelTreeEvt extends QueryEvt implements Serializable {

	private static final long serialVersionUID = 1L;
	@Field(length = 20, nullable = true, value = "用户组id")
	private Long groupId;

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
}
