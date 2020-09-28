package cn.com.doone.tx.cloud.service.user.bean;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by liujx on 2017/2/17 0017.
 * @Description: (后台用户组-人员关联表)
 */
@Entity
@Table(name = "tr_group_staff")
@SequenceGenerator(name="common_generator",sequenceName="s_tr_group_staff", // oracle 用到的序列名 s_tr_group_staff
        allocationSize=1,initialValue=1) // 主键生成策略,sequenceName为s_表名,其他不用动
public class GroupStaffBean extends PlatBaseEntity implements Serializable{

    private static final long serialVersionUID = 941892892156851454L;
    /**用户组id*/
    @Column(name="GROUP_ID",length = 10,nullable = false)
    private Long groupId;

    /**人员id*/
    @Column(name="STAFF_ID",length = 10,nullable = false)
    private Long staffId;

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }
}
