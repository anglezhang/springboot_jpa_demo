package cn.com.doone.tx.cloud.service.user.evt.menu;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;

/**
 * Created by liujx on 2017/2/17 0017.
 */
public class DeleteMenuEvt extends BaseEvt implements Serializable {

	private static final long serialVersionUID = 5178392567179223292L;
	// @NotNull(message = "菜单id不能为空")
	@Field(value = "菜单id", length = 10, nullable = false)
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
