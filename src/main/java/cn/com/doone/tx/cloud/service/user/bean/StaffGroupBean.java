package cn.com.doone.tx.cloud.service.user.bean;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by liujx on 2017/2/17 0017.
 * @Description: (后台用户组主表)
 */
@Entity
@Table(name = "ts_staff_group")
@SequenceGenerator(name="common_generator",sequenceName="s_ts_staff_group", // oracle 用到的序列名 s_ts_staff_group
        allocationSize=1,initialValue=1) // 主键生成策略,sequenceName为s_表名,其他不用动
public class StaffGroupBean extends PlatBaseEntity implements Serializable{


    private static final long serialVersionUID = 2855829585784580257L;
    /**上级用户组*/
    @Column(name = "PARENT_ID",length = 10,nullable = true)
    private Long parentId;
    /**用户组名称*/
    @Column(name = "GROUP_NAME",length = 256,nullable = false)
    private String groupName;
    /**用户组简介*/
    @Column(name = "GROUP_REMARK",length = 512,nullable = true)
    private String groupRemark;
    /**排列顺序*/
    @Column(name = "SORT",length = 10,nullable = true)
    private Integer sort;
    /**城市编码*/
    @Column(name = "CITY_CODE",length = 16,nullable = true)
    private String cityCode;
    /**地区编码*/
    @Column(name = "AREA_CODE",length = 16,nullable = true)
    private String areaCode;
    /**省编码*/
    @Column(name = "PROVINCE_CODE",length = 16,nullable = true)
    private String provinceCode;
    /**街道编码*/
    @Column(name = "STREET_CODE",length = 16,nullable = true)
    private String streetCode;
    /**用户组类型 1组织2自定义用户组*/
    @Column(name = "GROUP_TYPE",length = 2,nullable = true)
    private Integer groupType;
    /**模板主题id*/
    @Column(name = "TEMPLATE_THEME_ID",length = 10,nullable = true)
    private Long templateThemeId;
    /**创建人员*/
    @Column(name = "CREATOR",length = 10,nullable = true)
    private Long creator;

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

    public Long getCreator() {
        return creator;
    }

    public Long getTemplateThemeId() {
		return templateThemeId;
	}

	public void setTemplateThemeId(Long templateThemeId) {
		this.templateThemeId = templateThemeId;
	}

	public void setCreator(Long creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "StaffGroupBean{" +
                "parentId=" + parentId +
                ", groupName='" + groupName + '\'' +
                ", groupRemark='" + groupRemark + '\'' +
                ", sort=" + sort +
                ", cityCode='" + cityCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", streetCode='" + streetCode + '\'' +
                ", groupType=" + groupType +
                ", creator=" + creator +
                '}';
    }
}
