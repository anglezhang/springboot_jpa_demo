package cn.com.doone.tx.cloud.service.user.evt.roleGroup;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;

import java.io.Serializable;

/**
 * Created by yecz on 2017/2/16 0016.
 */
public class QueryRoleGroupEvt extends QueryEvt implements Serializable {

	private static final long serialVersionUID = -1581368221694279041L;

	private Long roleId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}
