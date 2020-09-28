package cn.com.doone.tx.cloud.service.user.dao.mapper;



import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.com.doone.tx.cloud.service.user.dao.provider.RoleProvider;
import cn.com.doone.tx.cloud.service.user.evt.StaffRole.DeleteStaffRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.StaffRole.QueryStaffRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.groupRole.DeleteGroupRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.role.DeleteRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.role.EditRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.role.QueryRoleEvt;
import cn.com.doone.tx.cloud.service.user.info.RoleInfo;

/**
 * Created by Administrator on 2017/1/19 0019.
 */
public interface RoleMapper {

    @SelectProvider(type = RoleProvider.class , method = "queryRoleCount")
    int queryRoleCount(QueryRoleEvt evt);

    @SelectProvider(type = RoleProvider.class , method = "queryRoleByParam")
    List<RoleInfo> queryRoleByParam(QueryRoleEvt evt);

    @UpdateProvider(type = RoleProvider.class , method = "doEdit")
    int doEdit(EditRoleEvt evt);

//    @Delete("delete from ts_role_info where id = #{id}")
    /**删除 修改状态为D*/
    @Update("update ts_role_info set status=#{status} where id=#{id} ")
    int doDelete(DeleteRoleEvt evt);

    @SelectProvider(type = RoleProvider.class , method = "queryRoleIsExist")
    int queryRoleIsExist(QueryRoleEvt evt);

    //删除账户、角色关联表
    @DeleteProvider(type = RoleProvider.class , method = "deleteStaffRole")
    int deleteStaffRole(DeleteStaffRoleEvt evt);
    //删除用户组、角色关联表
    @DeleteProvider(type = RoleProvider.class , method = "deleteGroupRole")
    int deleteGroupRole(DeleteGroupRoleEvt evt);

    //查询角色和用户绑定关系的总条数
    @SelectProvider(type = RoleProvider.class , method = "queryStaffRoleCount")
	int queryStaffRoleCount(QueryStaffRoleEvt queryStaffRoleEvt);

}
