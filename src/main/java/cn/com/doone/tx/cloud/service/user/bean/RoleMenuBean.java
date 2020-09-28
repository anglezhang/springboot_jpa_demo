package cn.com.doone.tx.cloud.service.user.bean;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tr_role_menu")
@SequenceGenerator(name="common_generator",sequenceName="s_tr_role_menu",
        allocationSize=1,initialValue=1)
public class RoleMenuBean extends PlatBaseEntity implements Serializable {

    private static final long serialVersionUID = -2998993877351074534L;

//    /**主键*/
//    @Column(name = "ROLE_MENU_ID")
//    private Long roleMenuId;

    /**角色id*/
    @Column(name = "ROLE_ID",length = 10,nullable = true)
    private Long roleId;
    /**菜单id*/
    @Column(name = "MENU_ID",length = 10,nullable = true)
    private Long menuId;
//    /**状态*/
//    @Column(name = "STATUS",length = 16,nullable = true)
//    private String status;
//    /**创建时间*/
//    @Column(name = "CREATE_TIME",length = 100,nullable = true)
//    private Date createTime;
//    /**创建时间*/
//    @Column(name = "UPDATE_TIME",length = 100,nullable = true)
//    private Date updateTime;
//    /**操作人员*/
//    @Column(name = "OPERATOR",length = 10,nullable = true)
//    private Long operator;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "RoleMenuBean{" +
                "roleId=" + roleId +
                ", menuId=" + menuId +
                '}';
    }
}
