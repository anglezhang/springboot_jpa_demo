package cn.com.doone.tx.cloud.service.integral.bean;

import java.io.Serializable;
import java.util.Date;

public class WxDepartment implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 微信部门ID
     */
    private Integer wxDepartmentId;

    /**
     * 微信父部门ID
     */
    private Integer wxParentId;

    /**
     * 排序
     */
    private Integer departmentOrder;

    /**
     * 状态 'E'启用 'D'删除
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private Integer creator;

    /**
     * 操作人
     */
    private Integer operator;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public Integer getWxDepartmentId() {
        return wxDepartmentId;
    }

    public void setWxDepartmentId(Integer wxDepartmentId) {
        this.wxDepartmentId = wxDepartmentId;
    }

    public Integer getWxParentId() {
        return wxParentId;
    }

    public void setWxParentId(Integer wxParentId) {
        this.wxParentId = wxParentId;
    }

    public Integer getDepartmentOrder() {
        return departmentOrder;
    }

    public void setDepartmentOrder(Integer departmentOrder) {
        this.departmentOrder = departmentOrder;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", departmentName=").append(departmentName);
        sb.append(", wxDepartmentId=").append(wxDepartmentId);
        sb.append(", wxParentId=").append(wxParentId);
        sb.append(", departmentOrder=").append(departmentOrder);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", creator=").append(creator);
        sb.append(", operator=").append(operator);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}