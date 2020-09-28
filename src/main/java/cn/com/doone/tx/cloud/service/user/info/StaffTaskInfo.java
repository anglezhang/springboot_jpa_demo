package cn.com.doone.tx.cloud.service.user.info;

import java.io.Serializable;

/**
 * 人员信息响应类
 */
public class StaffTaskInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7088565036814519364L;

	private Long id;

	private String status;

	// 国家编码
	private String countryCode;

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

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Override
	public String toString() {
		return "StaffTaskInfo [id=" + id + ", status=" + status + ", countryCode=" + countryCode + ", toString()="
				+ super.toString() + "]";
	}

}
