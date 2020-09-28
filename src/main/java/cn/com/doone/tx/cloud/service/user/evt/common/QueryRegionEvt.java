package cn.com.doone.tx.cloud.service.user.evt.common;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @OAuthor: YeCongZhi
 * @Description: 大区查询
 * @CreatedDate: 2017/9/6 14:04
 * @Package:cn.com.doone.tx.cloud.user.service.evt.common
 */
public class QueryRegionEvt extends QueryEvt implements Serializable {
    private static final long serialVersionUID = 3088439463179286050L;

    private String regionCode;

    /**是否加载下属省份信息*/
    private String isLoadProvince;
    /**
	 * 区域名称
     */
    private String regionName;
    /**
	 * 上级区域编码（1国家2省3市4区）
     */
    private String parentCode;
    /**
	 * 区域层级
     */
    private String regionLevel;
    /**
	 * 创建人员
     */
    private Long creator;
    /**
	 * 创建时间
     */
    private Date createTime;
    /**
	 * 修改人员
     */
    private Long operator;
    /**
	 * 修改时间
     */
    private Date updateTime;
    /**
	 * 备注
     */
    private String remark;
    /**
	 * 删除标识位
     */
    private String delFlag;
    /**
	 * 语言
     */
    private String languageType;
    /**
     * 国家简称
     */
    private String abbCodeNo2;
    

    /**
     * 使用类型
     */
    private String useType;
    
    private List<String> regionCodeList;

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getIsLoadProvince() {
        return isLoadProvince;
    }

    public void setIsLoadProvince(String isLoadProvince) {
        this.isLoadProvince = isLoadProvince;
    }

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getRegionLevel() {
		return regionLevel;
	}

	public void setRegionLevel(String regionLevel) {
		this.regionLevel = regionLevel;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getOperator() {
		return operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}

	public String getAbbCodeNo2() {
		return abbCodeNo2;
	}

	public void setAbbCodeNo2(String abbCodeNo2) {
		this.abbCodeNo2 = abbCodeNo2;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public List<String> getRegionCodeList() {
		return regionCodeList;
	}

	public void setRegionCodeList(List<String> regionCodeList) {
		this.regionCodeList = regionCodeList;
	}
    
}
