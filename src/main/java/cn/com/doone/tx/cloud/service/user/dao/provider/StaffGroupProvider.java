package cn.com.doone.tx.cloud.service.user.dao.provider;


import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import cn.com.doone.tx.cloud.service.user.evt.staffGroup.EditStaffGroupEvt;
import cn.com.doone.tx.cloud.service.user.evt.staffGroup.QueryStaffGroupEvt;

/**
 * Created by liujx on 2017/2/13 0013.
 */
public class StaffGroupProvider {

    private final String TBL_STAFF_GROUP = "ts_staff_group";
    private final String TBL_CUSTOM_GROUP = "tf_custom_group";
    private final String TBL_GROUP_ROLE = "tr_group_role";
    private final String TBL_ROLE_INFO = "ts_role_info";
    private final String TBL_STAFF_INFO = "ts_staff_info";

    public String queryCount(QueryStaffGroupEvt evt) {
        SQL sql = new SQL().SELECT("count(*)").FROM(TBL_STAFF_GROUP);
        Long id = evt.getId();
        if(id != null){
            sql.WHERE("ID = #{id}");
        }
        Long parentId = evt.getParentId();
        if(parentId != null){
            sql.WHERE("PARENT_ID = #{parentId}");
        }
        String groupName = evt.getGroupName();
        if(StringUtils.isNotBlank(groupName)){
            evt.setGroupName("%"+groupName+"%");
            sql.WHERE("GROUP_NAME like #{groupName}");
        }
        Integer groupType = evt.getGroupType();
        if(groupType != null){
            sql.WHERE("GROUP_TYPE = #{groupType}");
        }
        Long creator = evt.getCreator();
        if (creator!=null) {
            sql.WHERE("creator=#{creator}");
        }
        if(StringUtils.isNotBlank(evt.getStatus())){
        	sql.WHERE("status=#{status}");
        }
        //D为删除状态
        sql.WHERE("status!='D'");
        sql.ORDER_BY("SORT");
        return sql.toString();
    }

    /***
     * 查询出用户组信息，关联出创建者名称
     */
    public String queryByParam(QueryStaffGroupEvt evt) {
        SQL sql = new SQL().SELECT("t1.ID,PARENT_ID as parentId,t1.GROUP_NAME as groupName,t1.GROUP_REMARK as groupRemark,t1.TEMPLATE_THEME_ID as templateThemeId," +
                "t1.SORT as sort," +
                "t1.PROVINCE_CODE as provinceCode,t1.CITY_CODE as cityCode,t1.AREA_CODE as areaCode,t1.STREET_CODE as streetCode," +
                "t1.GROUP_TYPE as groupType," +
                "t1.STATUS as status,t1.OPERATOR as operator,t1.CREATE_TIME as createTime,t1.UPDATE_TIME as updateTime," +
                "t2.STAFF_NAME as creatorName")
                .FROM(TBL_STAFF_GROUP+" t1")
                .LEFT_OUTER_JOIN(TBL_STAFF_INFO+" t2 ON t1.creator=t2.ID");
        Long id = evt.getId();
        if(id != null){
            sql.WHERE("t1.ID = #{id}");
        }
        Long parentId = evt.getParentId();
        if(parentId != null){
            sql.WHERE("t1.PARENT_ID = #{parentId}");
        }
        String groupName = evt.getGroupName();
        if(StringUtils.isNotBlank(groupName)){
            if ("Y".equals(evt.getIsAccurate())){
                sql.WHERE("t1.GROUP_NAME=#{groupName}");
            }else {
                evt.setGroupName("%"+groupName+"%");
                sql.WHERE("t1.GROUP_NAME like #{groupName}");
            }
        }
        Integer groupType = evt.getGroupType();
        if(groupType != null){
            sql.WHERE("t1.GROUP_TYPE = #{groupType}");
        }
        Long creator = evt.getCreator();
        if (creator!=null) {
            sql.WHERE("t1.creator=#{creator}");
        }
        if(evt.getExcludeParentId() != null) {
        	sql.WHERE("t1.parent_id != #{excludeParentId}");
        }
        //D为删除状态
        sql.WHERE("t1.status!='D'");
        sql.ORDER_BY("t1.SORT");
        return sql.toString();
    }
    /**
     * 用户组树
     * 根据parentId递归查询用户组信息
     * */
    public String queryStaffGroupInfoByParent(QueryStaffGroupEvt evt){
        SQL sql = new SQL().SELECT("t1.ID,t1.PARENT_ID as parentId,t1.GROUP_NAME as groupName").FROM(TBL_STAFF_GROUP + " t1");
        Long creator = evt.getCreator();
        if (creator!=null) {
            sql.WHERE("t1.creator=#{creator}");
        }
        Long parentId = evt.getParentId();
        if (parentId!=null){
            sql.WHERE("t1.parent_id=#{parentId}");
        }
        sql.WHERE("t1.status!='D'");
        sql.ORDER_BY("t1.SORT");
        return sql.toString();
    }

    /**超级管理员 的用户组树*/
    public String queryAllGroup(QueryStaffGroupEvt evt){
        SQL sql = new SQL().SELECT("t1.ID,t1.PARENT_ID as parentId,t1.GROUP_NAME as groupName").FROM(TBL_STAFF_GROUP + " t1");
        sql.WHERE("t1.status!='D'");
        sql.ORDER_BY("t1.SORT");
        return sql.toString();
    }
    /**
     * 角色分配-数据权限的用户组树
     * 超级管理员展示所有
     */
    public String groupTreeForData(QueryStaffGroupEvt evt){
        SQL sql = new SQL().SELECT("t1.ID,t1.PARENT_ID as parentId,t1.GROUP_NAME as groupName").FROM(TBL_STAFF_GROUP + " t1");
        sql.WHERE("t1.status='E'");
        sql.WHERE("t1.ID>0");
        sql.ORDER_BY("t1.SORT");
        return sql.toString();
    }
    public String doEdit(EditStaffGroupEvt evt){
        SQL sql = new SQL().UPDATE(TBL_STAFF_GROUP);
        if (evt.getGroupName()!=null){
            sql.SET("GROUP_NAME=#{groupName}");
        }
        if (evt.getGroupRemark()!=null){
            sql.SET("GROUP_REMARK=#{groupRemark}");
        }
        if (evt.getStatus()!=null){
            sql.SET("STATUS=#{status}");
        }
        if(evt.getUpdateTime()!=null){
            sql.SET("UPDATE_TIME=#{updateTime}");
        }
        if (evt.getSort()!=null){
            sql.SET("SORT=#{sort}");
        }
        if(evt.getOperator()!=null){
            sql.SET("OPERATOR=#{operator}");
        }
        if(evt.getProvinceCode()!=null){
            sql.SET("PROVINCE_CODE=#{provinceCode}");
        }
        if(evt.getAreaCode()!=null){
            sql.SET("AREA_CODE=#{areaCode}");
        }
        if(evt.getCityCode()!=null){
            sql.SET("CITY_CODE=#{cityCode}");
        }
        if(evt.getStreetCode()!=null){
            sql.SET("STREET_CODE=#{streetCode}");
        }
        sql.WHERE("ID=#{id}");
        return sql.toString();
    }
    public String doEditCustomGroup(EditStaffGroupEvt evt){
        SQL sql = new SQL().UPDATE(TBL_CUSTOM_GROUP);
        if (StringUtils.isNotBlank(evt.getGroupName())){
            sql.SET("GROUP_NAME=#{groupName}");
        }
        if(evt.getOperator()!=null){
            sql.SET("OPERATOR=#{operator}");
        }
        if(evt.getUpdateTime()!=null){
            sql.SET("UPDATE_TIME=#{updateTime}");
        }
        sql.WHERE("GROUP_ID=#{groupId}");
        return sql.toString();
    }
    public String queryRoleByGroup(Long groupId){
        SQL sql = new SQL().SELECT("r.ID,r.ROLE_NAME roleName," +
                "r.ROLE_REMARK roleRemark,r.IS_DEFAULT isDefault,r.STATUS status," +
                "r.CREATE_TIME createTime,r.UPDATE_TIME updateTime,r.OPERATOR operator").FROM(TBL_ROLE_INFO +" r");
        sql.LEFT_OUTER_JOIN(TBL_GROUP_ROLE +" gr on r.ID = gr.ROLE_ID");
        sql.WHERE("gr.GROUP_ID = #{groupId}");
        sql.WHERE("r.STATUS='E'");
        sql.WHERE("gr.STATUS='E'");
        return sql.toString();
    }

//    public String queryCustomGroupByParam(QueryCustomGroupEvt evt) {
//        SQL sql = new SQL().SELECT("t1.*").FROM(TBL_CUSTOM_GROUP+" t1");
//        Long id = evt.getId();
//        Long groupId = evt.getGroupId();
//        String groupName = evt.getGroupName();
//        String groupType = evt.getGroupType();
//        if(id != null){
//            sql.WHERE("ID = #{id}");
//        }
//        if(groupId != null){
//            sql.WHERE("GROUP_ID = #{groupId}");
//        }
//        if(StringUtils.isNotBlank(groupName)){
//            evt.setGroupName("%"+groupName+"%");
//            sql.WHERE("GROUP_NAME like #{groupName}");
//        }
//        if(groupType != null){
//            sql.WHERE("GROUP_TYPE = #{groupType}");
//        }
//        sql.WHERE("t1.status='E'");
//        return sql.toString();
//    }
public String queryNameIsExist(QueryStaffGroupEvt evt){
    SQL sql = new SQL().SELECT("count(*)").FROM(TBL_STAFF_GROUP);
    String groupName = evt.getGroupName();
    if(StringUtils.isNotBlank(groupName)){
        sql.WHERE("GROUP_NAME = #{groupName}");
    }
    Long parentId =evt.getParentId();
    if(parentId!=null){
        sql.WHERE("PARENT_ID = #{parentId}");
    }
    Long id = evt.getId();
    if(id!=null){
        sql.WHERE("ID != #{id}");
    }
    sql.WHERE("STATUS!='D'");//启用和禁用
    return sql.toString();
}

    public String queryGroupIsForbid(QueryStaffGroupEvt evt){
        SQL sql = new SQL().SELECT("count(*)").FROM(TBL_STAFF_GROUP);
        Long id = evt.getId();
        if(id!=null){
            sql.WHERE("ID = #{id}");
        }
        sql.WHERE("STATUS='F'");//禁用
        return sql.toString();
    }


}
