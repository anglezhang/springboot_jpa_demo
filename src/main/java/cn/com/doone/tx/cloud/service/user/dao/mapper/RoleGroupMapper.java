package cn.com.doone.tx.cloud.service.user.dao.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import cn.com.doone.tx.cloud.service.user.dao.provider.RoleGroupProvider;
import cn.com.doone.tx.cloud.service.user.evt.roleGroup.DeleteRoleGroupEvt;
import cn.com.doone.tx.cloud.service.user.evt.roleGroup.QueryRoleGroupEvt;
import cn.com.doone.tx.cloud.service.user.info.RoleGroupInfo;


/**
 * yecz
 */
@Mapper
public interface RoleGroupMapper {

    //查询
    @SelectProvider(type = RoleGroupProvider.class , method = "queryRoleGroupByParam")
    List<RoleGroupInfo> queryRoleGroupByParam(QueryRoleGroupEvt evt);

    //插入
   /* @Insert("INSERT INTO tr_data_role\n" +
            " (DATA_GROUP_ID, ROLE_ID, GROUP_ID,STATUS," +
            "CREATE_TIME,UPDATE_TIME,OPERATOR)\n" +
            "VALUES (#{dataGroupId}, #{roleId}, #{groupId},#{status}," +
            "#{createTime},#{updateTime},#{operator})")
    int doAdd(RoleGroupInfo roleGroupInfo);*/

    //删除角色用户组关系 roleId为条件
    @Delete("delete from tr_data_role where role_id = #{roleId}")
    int doDelete(DeleteRoleGroupEvt evt);

    //删除角色用户组关系 groupId为条件
    @Delete("delete from tr_data_role where group_id = #{groupId}")
    int doDeleteDateRoleByGroupId(DeleteRoleGroupEvt evt);

}
