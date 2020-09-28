package cn.com.doone.tx.cloud.service.user.dao.provider;


import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import cn.com.doone.tx.cloud.service.user.evt.menu.EditMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.menu.QueryMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.QueryStaffMenuEvt;

/**
 * Created by liujx on 2017/2/13 0013.
 */
public class MenuProvider {

    private final String TS_MENU_INFO = "ts_menu_info";

    public String queryMenuCount(QueryMenuEvt evt) {
        SQL sql = new SQL().SELECT("count(DISTINCT t1.ID)").FROM(TS_MENU_INFO + " t1");
//        		.LEFT_OUTER_JOIN("mul_language t2 ON t1.MENU_CODE = t2.CODE AND t2.STATUS = 'E' ");
        Long id = evt.getId();
        if(id != null){
            sql.WHERE("t1.ID = #{id}");
        }
        Long parentId = evt.getParentId();
        if(parentId != null){
            sql.WHERE("t1.PARENT_ID = #{parentId}");
        }
//        String menuName = evt.getMenuName();
//        if(StringUtils.isNotBlank(menuName)){
//            evt.setMenuName("%"+menuName+"%");
//            sql.WHERE("t2.NAME like #{menuName}");
//        }
        Integer isMenu = evt.getIsMenu();
        if(isMenu != null){
            sql.WHERE("t1.IS_MENU = #{isMenu}");
        }
        String menuCode = evt.getMenuCode();
        if(StringUtils.isNotBlank(menuCode)){
            sql.WHERE("t1.MENU_CODE = #{menuCode}");
        }
        Long creator = evt.getCreator();
        if(creator!=null){
            sql.WHERE("t1.CREATOR = #{creator}");
        }
//        String languageType = evt.getLanguageType();
//        if (StringUtils.isNotBlank(languageType)) {
//        	sql.WHERE("t2.language_type = #{languageType}");
//		}
//        sql.WHERE("t2.sources=1");
        //D为删除状态
        sql.WHERE("t1.status!='D'");
        return sql.toString();
    }
    public String queryMenuByParam(QueryMenuEvt evt) {
        SQL sql = new SQL().SELECT_DISTINCT("t1.ID,t1.PARENT_ID as parentId,t1.SORT sort,t1.menu_Name as menuName," +
                "t1.MENU_IMG as menuImg,t1.MENU_URL as menuUrl,t1.MENU_CODE as menuCode,t1.IS_MENU isMenu,t1.IS_LOCAL as isLocal," +
                "t1.OPEN_TYPE as openType,t1.STATUS as status,t1.OPERATOR as operator,t1.CREATE_TIME as createTime," +
                "t1.UPDATE_TIME as updateTime").FROM(TS_MENU_INFO + " t1");
//        sql.LEFT_OUTER_JOIN("mul_language t2 ON t1.MENU_CODE = t2.CODE AND t2.STATUS = 'E'");
        Long id = evt.getId();
        if(id != null){
            sql.WHERE("t1.ID = #{id}");
        }
        Long parentId = evt.getParentId();
        if(parentId != null){
            sql.WHERE("t1.PARENT_ID = #{parentId}");
        }
//        String menuName = evt.getMenuName();
//        if(StringUtils.isNotBlank(menuName)){
//            evt.setMenuName("%"+menuName+"%");
//            sql.WHERE("t2.NAME like #{menuName}");
//        }
        Integer isMenu = evt.getIsMenu();
        if(isMenu != null){
            sql.WHERE("t1.IS_MENU = #{isMenu}");
        }
        String menuCode = evt.getMenuCode();
        if(StringUtils.isNotBlank(menuCode)) {
            sql.WHERE("t1.MENU_CODE = #{menuCode}");
        }
        Long creator = evt.getCreator();
        if(creator!=null){
            sql.WHERE("t1.CREATOR = #{creator}");
        }
//        String languageType = evt.getLanguageType();
//        if (StringUtils.isNotBlank(languageType)) {
//        	sql.WHERE("t2.language_type = #{languageType}");
//		}
        //D为删除状态
        sql.WHERE("t1.status!='D'");
//        sql.WHERE("t2.sources=1");
        sql.ORDER_BY("t1.SORT");
        return sql.toString();
    }
    
    public String queryDetail(QueryMenuEvt evt){
    	SQL sql = new SQL().SELECT_DISTINCT("t1.ID,t1.PARENT_ID as parentId,t1.SORT sort,t1.menu_Name as menuName," +
                "t1.MENU_IMG as menuImg,t1.MENU_URL as menuUrl,t1.MENU_CODE as menuCode,t1.IS_MENU isMenu,t1.IS_LOCAL as isLocal," +
                "t1.OPEN_TYPE as openType,t1.STATUS as status,t1.OPERATOR as operator,t1.CREATE_TIME as createTime," +
                "t1.UPDATE_TIME as updateTime").FROM(TS_MENU_INFO + " t1");
//    	sql.LEFT_OUTER_JOIN("mul_language t2 ON t1.MENU_CODE = t2.CODE AND t2.STATUS = 'E'");
    	Long id = evt.getId();
        if(id != null){
            sql.WHERE("t1.ID = #{id}");
        }
//        String menuName = evt.getMenuName();
//        if(StringUtils.isNotBlank(menuName)){
//            sql.WHERE("t2.NAME = #{menuName}");
//        }
        String menuCode = evt.getMenuCode();
        if(StringUtils.isNotBlank(menuCode)){
            sql.WHERE("t1.MENU_CODE = #{menuCode}");
        }
//        String languageType = evt.getLanguageType();
//        if (StringUtils.isNotBlank(languageType)) {
//        	sql.WHERE("t2.language_type = #{languageType}");
//		}
//        sql.WHERE("t2.sources=1");
        //D为删除状态
        sql.WHERE("t1.status!='D'");
        return sql.toString();
    }

    public String tree(QueryMenuEvt evt){
        SQL sql = new SQL().SELECT("t1.ID,t1.PARENT_ID as parentId,t1.IS_MENU as isMenu,t1.menu_Name as menuName").FROM(TS_MENU_INFO + " t1");
//        sql.LEFT_OUTER_JOIN("mul_language t2 ON t1.MENU_CODE = t2.CODE AND t2.STATUS = 'E'");
//        sql.LEFT_OUTER_JOIN("tr_role_menu t2 ON t1.MENU_ID = t2.MENU_ID AND t2.STATUS = E");
//        sql.LEFT_OUTER_JOIN("tr_staff_role t3 ON t2.ROLE_ID = t3.ROLE_ID AND t3.STATUS = E");
//        sql.WHERE("t3.STAFF_ID = #{staffId}");
//        System.out.println(staffInfo.getScope());
        if(!"role".equals(evt.getScope())){//角色分配菜单树含功能按钮
            sql.WHERE("t1.IS_MENU = 1");
        }
//        Long creator = evt.getCreator();
//        if(creator!=null){
//            sql.WHERE("t1.CREATOR = #{creator}");
//        }
//        String languageType = evt.getLanguageType();
//        if (StringUtils.isNotBlank(languageType)) {
//        	sql.WHERE("t2.language_type = #{languageType}");
//		}
//        sql.WHERE("t2.SOURCES = 1");
        sql.WHERE("t1.ID <> -4");
        //D为删除状态
        sql.WHERE("t1.status!='D'");
        sql.ORDER_BY("t1.SORT");
        return sql.toString();
    }

    //查询用户菜单、按钮树--用于角色管理->角色分配
    public String userMenuTree(QueryStaffMenuEvt evt){
        SQL sql = new SQL().SELECT("t1.ID,t1.PARENT_ID as parentId," +
                "t1.IS_MENU as isMenu, t1.menu_Name as menuName").FROM(TS_MENU_INFO + " t1");
        sql.LEFT_OUTER_JOIN("tr_role_menu t2 ON t1.ID = t2.MENU_ID AND t2.STATUS ='E'");
        sql.LEFT_OUTER_JOIN("tr_staff_role t3 ON t2.ROLE_ID = t3.ROLE_ID AND t3.STATUS ='E'");
//        sql.LEFT_OUTER_JOIN("mul_language t4 ON t1.MENU_CODE = t4.CODE AND t4.STATUS ='E'");
        sql.WHERE("t3.STAFF_ID = #{staffId}");
        sql.WHERE("t1.ID <> -4");
        sql.WHERE("t1.STATUS='E'");
//        sql.WHERE("t4.language_type = #{languageType}");
        sql.ORDER_BY("t1.SORT");
        return sql.toString();
    }
    //查询所有菜单信息--超级管理员首页用户菜单展示
    public String queryAllMenu(QueryStaffMenuEvt evt){
        SQL sql = new SQL().SELECT_DISTINCT("t1.ID,t1.PARENT_ID as parentId,t1.menu_name as menuName," +
                "t1.SORT sort,t1.MENU_IMG as menuImg,t1.MENU_URL as menuUrl,t1.MENU_CODE as menuCode," +
                "t1.IS_LOCAL as isLocal,t1.OPEN_TYPE as openType")
                .FROM(TS_MENU_INFO+" t1");
//        sql.LEFT_OUTER_JOIN("mul_language t2 ON t1.MENU_CODE = t2.CODE AND t2.STATUS = 'E'");
        sql.WHERE("t1.ID <> -4");
        sql.WHERE("t1.IS_MENU=1");
        sql.WHERE("t1.STATUS='E'");
//        sql.WHERE("t2.SOURCES=1");
        String menuCode = evt.getMenuCode();
        if(StringUtils.isNotBlank(menuCode)) {
            sql.WHERE("t1.MENU_CODE = #{menuCode}");
        }
//        String languageType = evt.getLanguageType();
//        if (StringUtils.isNotBlank(languageType)) {
//        	sql.WHERE("t2.language_type = #{languageType}");
//		}
        sql.ORDER_BY("t1.SORT");
        return sql.toString();
    }
    //查询用户菜单信息--用于首页用户菜单展示
    public String queryUserMenu(QueryStaffMenuEvt evt){
        SQL sql = new SQL().SELECT("t1.ID,t1.PARENT_ID as parentId,t1.menu_name as menuName," +
                "t1.SORT sort,t1.MENU_IMG as menuImg,t1.MENU_URL as menuUrl,t1.MENU_CODE as menuCode," +
                "t1.IS_LOCAL as isLocal,t1.OPEN_TYPE as openType")
                .FROM(TS_MENU_INFO+" t1");
        sql.LEFT_OUTER_JOIN("tr_role_menu t2 ON t1.ID = t2.MENU_ID AND t2.STATUS ='E'");
        sql.LEFT_OUTER_JOIN("tr_staff_role t3 ON t2.ROLE_ID = t3.ROLE_ID AND t3.STATUS ='E'");
//        sql.LEFT_OUTER_JOIN("mul_language t4 ON t1.MENU_CODE = t4.CODE AND t4.STATUS = 'E' ");
        sql.WHERE("t3.STAFF_ID = #{staffId}");
        sql.WHERE("t1.ID <> -4");
        sql.WHERE("t1.IS_MENU=1");
        sql.WHERE("t1.STATUS='E'");
//        sql.WHERE("t4.SOURCES=1");
        String menuCode = evt.getMenuCode();
        if(StringUtils.isNotBlank(menuCode)) {
            sql.WHERE("t1.MENU_CODE = #{menuCode}");
        }
//        String languageType = evt.getLanguageType();
//        if (StringUtils.isNotBlank(languageType)) {
//        	sql.WHERE("t4.language_type = #{languageType}");
//		}
        sql.ORDER_BY("t1.SORT");
        return sql.toString();
    }
    public String doEdit(EditMenuEvt evt){
        SQL sql = new SQL().UPDATE(TS_MENU_INFO);
        if (evt.getMenuName()!=null){
            sql.SET("MENU_NAME=#{menuName}");
        }
        if (evt.getMenuImg()!=null){
            sql.SET("MENU_IMG=#{menuImg}");
        }else {
            if (evt.getSort()==null){
                sql.SET("MENU_IMG=null");
            }
        }
        if (evt.getMenuUrl()!=null){
            sql.SET("MENU_URL=#{menuUrl}");
        }
        if (evt.getMenuCode()!=null){
            sql.SET("MENU_CODE=#{menuCode}");
        }
        if (evt.getStatus()!=null){
            evt.setUpdateTime(new Date());
            sql.SET("STATUS=#{status}");
        }
        if (evt.getSort()!=null){
            sql.SET("SORT=#{sort}");
        }
        if(evt.getOpenType()!=null){
            sql.SET("OPEN_TYPE=#{openType}");
        }
        if(evt.getIsLocal()!=null){
            sql.SET("IS_LOCAL=#{isLocal}");
        }
        sql.WHERE("ID=#{id}");
        return sql.toString();
    }
    public String queryCodeIsExist(QueryMenuEvt evt){
        SQL sql = new SQL().SELECT("count(*)").FROM(TS_MENU_INFO + " t1");
//        sql.LEFT_OUTER_JOIN("mul_language t2 ON t1.MENU_CODE = t2.CODE AND t2.STATUS = 'E'");
        String menuCode = evt.getMenuCode();
        if(StringUtils.isNotBlank(menuCode)){
            sql.WHERE("t1.MENU_CODE = #{menuCode}");
        }
//        String menuName = evt.getMenuName();
//        if(StringUtils.isNotBlank(menuName)){
//            sql.WHERE("t2.NAME = #{menuName}");
//        }
//        String languageType = evt.getLanguageType();
//        if (StringUtils.isNotBlank(languageType)) {
//        	sql.WHERE("t2.language_type = #{languageType}");
//		}
        Long id = evt.getId();
        if(id!=null){
            sql.WHERE("t1.ID != #{id}");
        }
        sql.WHERE("(t1.STATUS='E' OR t1.STATUS='F')");//启用和禁用
//        System.out.println(sql.toString());
        return sql.toString();
    }

    public String queryNameIsExist(QueryMenuEvt evt){
        SQL sql = new SQL().SELECT("count(*)").FROM(TS_MENU_INFO + " t1");
        String menuName = evt.getMenuName();
        if(StringUtils.isNotBlank(menuName)){
            sql.WHERE("t1.MENU_NAME = #{menuName}");
        }
        Long parentId =evt.getParentId();
        if(parentId!=null){
            sql.WHERE("t1.PARENT_ID = #{parentId}");
        }
        Long id = evt.getId();
        if(id!=null){
            sql.WHERE("t1.ID != #{id}");
        }
        sql.WHERE("t1.STATUS!='D'");//启用和禁用
        return sql.toString();
    }

    public String queryAll(QueryMenuEvt evt){
    	StringBuffer sqlSB = new StringBuffer();
    	
    	sqlSB.append("SELECT DISTINCT T1.ID, \n");
    	sqlSB.append("                T1.PARENT_ID   AS PARENTID, \n");
    	sqlSB.append("                T1.MENU_NAME        AS MENUNAME, \n");
    	sqlSB.append("                T1.SORT        SORT, \n");
    	sqlSB.append("                T1.MENU_IMG    AS MENUIMG, \n");
    	sqlSB.append("                T1.MENU_URL    AS MENUURL, \n");
    	sqlSB.append("                T1.MENU_CODE   AS MENUCODE, \n");
    	sqlSB.append("                T1.IS_MENU     ISMENU, \n");
    	sqlSB.append("                T1.IS_LOCAL    AS ISLOCAL, \n");
    	sqlSB.append("                T1.OPEN_TYPE   AS OPENTYPE, \n");
    	sqlSB.append("                T1.STATUS      AS STATUS, \n");
    	sqlSB.append("                T1.OPERATOR    AS OPERATOR, \n");
    	sqlSB.append("                T1.CREATE_TIME AS CREATETIME, \n");
    	sqlSB.append("                T1.UPDATE_TIME AS UPDATETIME \n");
    	sqlSB.append("  FROM TS_MENU_INFO T1 \n");
//    	sqlSB.append("  LEFT JOIN MUL_LANGUAGE T2 \n");
//    	sqlSB.append("    ON T1.MENU_CODE = T2.CODE \n");
//    	sqlSB.append("   AND T2.STATUS = 'E' \n");
    	sqlSB.append(" WHERE T1.STATUS = 'E' \n");
//    	sqlSB.append("   AND T2.SOURCES = 1 \n");
    	if(null != evt.getIsMenu()){
    		sqlSB.append("   AND T1.IS_MENU = #{isMenu} \n");
    	}
//    	sqlSB.append("   AND T2.LANGUAGE_TYPE = #{languageType} \n");
    	sqlSB.append(" ORDER BY T1.SORT \n");

    	return sqlSB.toString();
    }
}
