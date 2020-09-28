package cn.com.doone.tx.cloud.service.user.info;

import java.io.Serializable;

public class RegionInfo implements Serializable {

	private static final long serialVersionUID = -8809737268641788069L;

	/** ID */
	private Long id;

	/** 销售品ID */
	private Long offerId;

	/** 编码 */
	private String CodeList;

	/** 名称 */
	private String nameList;
	
	/** 国家编码 */
	private String countryCode;

	/** 国家名称 */
	private String countryName;
	/** 省州编码 */
	private String proviceCode;

	/** 省州 */
	private String proviceName;
	/** 市县 */
	private String cityCode;

	/** 市县*/
	private String cityName;
	/** 乡镇编码 */
	private String regionCode;

	/** 乡镇 */
	private String regionName;

	
	
	public String getCodeList() {
		return CodeList;
	}

	public void setCodeList(String codeList) {
		CodeList = codeList;
	}

	public String getNameList() {
		return nameList;
	}

	public void setNameList(String nameList) {
		this.nameList = nameList;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getProviceCode() {
		return proviceCode;
	}

	public void setProviceCode(String proviceCode) {
		this.proviceCode = proviceCode;
	}

	public String getProviceName() {
		return proviceName;
	}

	public void setProviceName(String proviceName) {
		this.proviceName = proviceName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

}
