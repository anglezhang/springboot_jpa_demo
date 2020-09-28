package cn.com.doone.tx.cloud.service.user.info;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by liujx on 2017/2/18 0018.
 * 用户组信息响应类
 */
public class StaffGroupInfo implements Serializable{

    private static final long serialVersionUID = 479962284743570337L;
    /**用户组 id*/
    private Long id;
    /**父级 id */
    private Long parentId;

    private String groupName;

    private String groupRemark;

    private Integer sort;

    private String cityCode;

    private String areaCode;

    private String provinceCode;

    private String streetCode;

    /**用户组类型 1组织2自定义用户组*/
    private Integer groupType;
    /**状态:E在用  D不可用*/
    private String status;

    private String createTime;

    private String updateTime;

    private Long operator;

    private String provinceName;

    private String cityName;

    private String areaName;

    private String streetName;

    /**创建者*/
    private String creatorName;

    private Long templateThemeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupRemark() {
        return groupRemark;
    }

    public void setGroupRemark(String groupRemark) {
        this.groupRemark = groupRemark;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Long getOperator() {
        return operator;
    }

    public void setOperator(Long operator) {
        this.operator = operator;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Long getTemplateThemeId() {
		return templateThemeId;
	}

	public void setTemplateThemeId(Long templateThemeId) {
		this.templateThemeId = templateThemeId;
	}

	@Override
    public String toString() {
        return "StaffGroupInfo{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", groupName='" + groupName + '\'' +
                ", groupRemark='" + groupRemark + '\'' +
                ", sort=" + sort +
                ", cityCode='" + cityCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", streetCode='" + streetCode + '\'' +
                ", groupType=" + groupType +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", operator=" + operator +
                ", provinceName='" + provinceName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", areaName='" + areaName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", creatorName='" + creatorName + '\'' +
                '}';
    }
}
