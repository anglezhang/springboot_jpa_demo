package cn.com.doone.tx.cloud.service.user.evt.staffRegion;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;

import java.io.Serializable;

public class AddStaffRegionEvt extends BaseEvt implements Serializable{

	private static final long serialVersionUID = -3271982641517557037L;
	
	/**
	 * 人员ID
	 */
	private Long staffId;
	
	/**
	 * 国家编码
	 */
	private Long countryCode;

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public Long getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Long countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return "AddStaffRegionEvt [staffId=" + staffId + ", countryCode=" + countryCode + "]";
	}

}
