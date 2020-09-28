package cn.com.doone.tx.cloud.service.user.bean;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "STAFF_REGION")
@SequenceGenerator(name = "common_generator", sequenceName = "S_STAFF_REGION", allocationSize = 1, initialValue = 1)
public class StaffRegionBean extends PlatBaseEntity implements Serializable {

	private static final long serialVersionUID = -555845083961341462L;
	
	/**
	 * 人员ID
	 */
	@Column(name = "STAFF_ID", length = 10, nullable = true)
	private Long staffId;
	
	/**
	 * 国家编码
	 */
	@Column(name = "COUNTRY_CODE", length = 10, nullable = true)
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
		return "StaffRegionBean [staffId=" + staffId + ", countryCode=" + countryCode + "]";
	}

}
