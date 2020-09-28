package cn.com.doone.tx.cloud.service.user.dao.provider;



import org.apache.ibatis.jdbc.SQL;

import cn.com.doone.tx.cloud.service.user.evt.roleGroup.QueryRoleGroupEvt;

public class RoleGroupProvider {

    private final String TR_DATA_ROLE = "tr_data_role";

    public String queryRoleGroupByParam(QueryRoleGroupEvt queryRoleGroupEvt) {
        SQL sql = new SQL().SELECT("ROLE_ID AS roleId," +
                "GROUP_ID AS groupId,STATUS AS status,CREATE_TIME AS createTime," +
                "UPDATE_TIME AS updateTime,OPERATOR AS operator").FROM(TR_DATA_ROLE);
        Long roleId = queryRoleGroupEvt.getRoleId();
        if(roleId != null){
            sql.WHERE("role_id = #{roleId}");
        }
        return sql.toString();
    }

}
