package cn.com.doone.tx.cloud.service.user.evt.groupRole;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;

import java.io.Serializable;

/**
 * Created by liujx on 2017/2/18 0018.
 */
public class QueryGroupRoleEvt extends QueryEvt implements Serializable {

	private static final long serialVersionUID = 2039867946775473988L;
	private Long groupId;

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

}
