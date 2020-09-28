package cn.com.doone.tx.cloud.service.user.info;


import javax.persistence.Column;
import java.io.Serializable;

/**
 * 数据权限信息(用户组信息)响应类
 */
public class DataAuthsInfo implements Serializable{

    private static final long serialVersionUID = -8924340293585698765L;
    /**ID*/
    @Column(name = "ID")
    private Long id;
    /**上级用户组*/
    @Column(name = "PARENT_ID")
    private Long parentId;
    /**用户组名称*/
    @Column(name = "GROUP_NAME")
    private String groupName;


//    /**用户组简介*/
//    @Column(name = "GROUP_REMARK")
//    private String groupRemark;
//    /**排列顺序*/
//    @Column(name = "SORT")
//    private Integer sort;
//    /**城市编码*/
//    @Column(name = "CITY_CODE")
//    private String cityCode;
//    /**地区编码*/
//    @Column(name = "AREA_CODE")
//    private String areaCode;
//    /**省编码*/
//    @Column(name = "PROVINCE_CODE")
//    private String provinceCode;
//    /**街道编码*/
//    @Column(name = "STREET_CODE")
//    private String streetCode;
//    /**用户组类型 1组织2自定义用户组*/
//    @Column(name = "GROUP_TYPE")
//    private Integer groupType;
//    /**创建人员*/
//    @Column(name = "CREATOR")
//    private Long creator;


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

    @Override
    public String toString() {
        return "DataAuthsInfo{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", groupName='" + groupName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataAuthsInfo that = (DataAuthsInfo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;
        return groupName != null ? groupName.equals(that.groupName) : that.groupName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        return result;
    }
}
