package cn.com.doone.tx.cloud.service.user.bean;

import javax.persistence.Column;
import java.io.Serializable;

public class RegionBean implements Serializable{ 
	
	/**
	 * 上级区域编码（1国家2省3市4区）
	 */
	@Column(name = "REGION_CODE",length = 32,nullable = true)
	private String regionCode;
    /**
	 * 区域名称
     */
    @Column(name = "REGION_NAME",length = 255,nullable = true)
    private String regionName;
    /**
	 * 上级区域编码（1国家2省3市4区）
     */
    @Column(name = "PARENT_CODE",length = 32,nullable = true)
    private String parentCode;
    /**
	 * 区域层级
     */
    @Column(name = "REGION_LEVEL",length = 2,nullable = true)
    private String regionLevel;
    /**
	 * 备注
     */
    @Column(name = "REMARK",length = 255,nullable = true)
    private String remark;
    /**
	 * 删除标识位
     */
    @Column(name = "DEL_FLAG",length = 2,nullable = true)
    private String delFlag;
    /**
   	 * 国家简称
    */
    @Column(name = "ABB_CODE_NO2",length = 2,nullable = true)
    private String abbCodeNo2;
    
    /**
   	 * 国家简称
    */
    @Column(name = "CURRENCY_TYPE",length = 4,nullable = true)
    private String currencyType;
    
    /**
   	 * 国家简称
    */
    @Column(name = "CURRENCY_UNITE",length = 4,nullable = true)
    private String currencyUnit;
    
    public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getCurrencyUnit() {
		return currencyUnit;
	}

	public void setCurrencyUnit(String currencyUnit) {
		this.currencyUnit = currencyUnit;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionName(){
        return regionName;
    }
    
    public void setRegionName(String regionName){
        this.regionName = regionName;
    }
    
    public String getParentCode(){
        return parentCode;
    }
    
    public void setParentCode(String parentCode){
        this.parentCode = parentCode;
    }
    
    public String getRegionLevel(){
        return regionLevel;
    }
    
    public void setRegionLevel(String regionLevel){
        this.regionLevel = regionLevel;
    }
    
    public String getAbbCodeNo2() {
		return abbCodeNo2;
	}

	public void setAbbCodeNo2(String abbCodeNo2) {
		this.abbCodeNo2 = abbCodeNo2;
	}

	public String getRemark(){
        return remark;
    }
    
    public void setRemark(String remark){
        this.remark = remark;
    }
    
    public String getDelFlag(){
        return delFlag;
    }
    
    public void setDelFlag(String delFlag){
        this.delFlag = delFlag;
    }
    
}
