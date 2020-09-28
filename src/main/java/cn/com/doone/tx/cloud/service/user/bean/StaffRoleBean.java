package cn.com.doone.tx.cloud.service.user.bean;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by liujx on 2017/2/17 0017.
 * @Description: (后台人员-角色关联表)
 */
@Entity
@Table(name = "tr_staff_role")
@SequenceGenerator(name="common_generator",sequenceName="s_tr_staff_role", // oracle 用到的序列名 s_tr_staff_role
        allocationSize=1,initialValue=1) // 主键生成策略,sequenceName为s_表名,其他不用动
public class StaffRoleBean extends PlatBaseEntity implements Serializable{

    private static final long serialVersionUID = 2446151270061233360L;
    @Column(name = "STAFF_ID",length = 10,nullable = false)
    private Long staffId;

    @Column(name = "ROLE_ID",length = 10,nullable = false)
    private Long  roleId;

    @Column(name = "IS_GRANT",length = 1,nullable = true)
    private Integer isGrant;

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Integer getIsGrant() {
        return isGrant;
    }


    public void setIsGrant(Integer isGrant) {
        this.isGrant = isGrant;
    }


    @Override
    public String toString() {
        return "StaffRoleBean{" +
                "staffId=" + staffId +
                ", roleId=" + roleId +
                ", isGrant=" + isGrant +
                '}';
    }
}
