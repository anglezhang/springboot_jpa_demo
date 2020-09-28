package cn.com.doone.tx.cloud.service.user.evt.groupRole;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by liujx on 2017/2/18 0018.
 */
public class AddGroupRoleEvt extends BaseEvt implements Serializable{

    private static final long serialVersionUID = -7690241984655465032L;
    /**角色id数组*/
//    @NotNull(message = "角色id数组不能为空")
    @Field(value="用户组ID", nullable = false)
    private Long[] ids;

    /**用户组id*/
//    @NotNull(message = "用户组id不能为空")
    @Field(value="用户组ID", length = 10, nullable = false)
    private Long groupId;

    private Long roleId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "AddGroupRoleEvt{" +
                "ids=" + Arrays.toString(ids) +
                ", groupId=" + groupId +
                '}';
    }
}
