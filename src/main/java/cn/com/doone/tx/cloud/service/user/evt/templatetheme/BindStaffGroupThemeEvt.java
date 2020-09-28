package cn.com.doone.tx.cloud.service.user.evt.templatetheme;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-8-18.
 */
public class BindStaffGroupThemeEvt extends BaseEvt implements Serializable {

	private static final long serialVersionUID = 1L;
	@Field(length = 20, nullable = false, value = "用户组id")
	private Long staffGroupId;

	@Field(length = 10, nullable = false, value = "主题模板id")
	private Long selTemplateThemeId;

	public Long getStaffGroupId() {
		return staffGroupId;
	}

	public void setStaffGroupId(Long staffGroupId) {
		this.staffGroupId = staffGroupId;
	}

	public Long getSelTemplateThemeId() {
		return selTemplateThemeId;
	}

	public void setSelTemplateThemeId(Long selTemplateThemeId) {
		this.selTemplateThemeId = selTemplateThemeId;
	}

}
