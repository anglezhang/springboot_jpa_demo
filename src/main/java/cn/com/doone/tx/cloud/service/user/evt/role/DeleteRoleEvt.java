package cn.com.doone.tx.cloud.service.user.evt.role;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

/**
 * Created by yecz on 2017/2/16 0016.
 */
public class DeleteRoleEvt extends BaseEvt {

	/** 主键 */
	@Field(value = "角色ID", length = 10, nullable = false)
	private Long id;

	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
