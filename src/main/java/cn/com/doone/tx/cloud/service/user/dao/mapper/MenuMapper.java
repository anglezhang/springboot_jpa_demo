package cn.com.doone.tx.cloud.service.user.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.com.doone.tx.cloud.service.user.dao.provider.MenuProvider;
import cn.com.doone.tx.cloud.service.user.evt.menu.DeleteMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.menu.EditMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.menu.QueryMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.rolemenu.DeleteRoleMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.QueryStaffMenuEvt;
import cn.com.doone.tx.cloud.tool.login.bean.MenuInfo;

/**
 * Created by liujx on 2017/2/13 0013.
 */
public interface MenuMapper {

    @SelectProvider(type = MenuProvider.class , method = "queryMenuCount")
    int queryMenuCount(QueryMenuEvt evt);

    @SelectProvider(type = MenuProvider.class , method = "queryCodeIsExist")
    int queryCodeIsExist(QueryMenuEvt evt);

    @SelectProvider(type = MenuProvider.class , method = "queryNameIsExist")
    int queryNameIsExist(QueryMenuEvt evt);

    @SelectProvider(type = MenuProvider.class , method = "queryMenuByParam")
    List<MenuInfo> queryMenuByParam(QueryMenuEvt evt);
    
    @SelectProvider(type = MenuProvider.class , method = "queryDetail")
    MenuInfo queryDetail(QueryMenuEvt evt);

    //菜单管理中的菜单树
    @SelectProvider(type = MenuProvider.class , method = "tree")
    List<MenuInfo> tree(QueryMenuEvt evt);

    //角色分配时的菜单树
    @SelectProvider(type = MenuProvider.class , method = "userMenuTree")
    List<MenuInfo> userMenuTree(QueryStaffMenuEvt evt);

    //查询所有菜单信息--超级管理员首页用户菜单展示
    @SelectProvider(type = MenuProvider.class , method = "queryAllMenu")
    List<MenuInfo> queryAllMenu(QueryStaffMenuEvt evt);

    //查询用户菜单信息--用于首页用户菜单展示
    //@SelectProvider(type = MenuProvider.class , method = "queryUserMenu")
    @Select("SELECT distinct t1.ID,t1.PARENT_ID as parentId," +
            "t1.MENU_NAME as menuName," +
            "t1.SORT sort,t1.MENU_IMG as menuImg,t1.MENU_URL as menuUrl,t1.MENU_CODE as menuCode," +
            "t1.IS_MENU isMenu,t1.IS_LOCAL as isLocal,t1.OPEN_TYPE as openType,t1.STATUS as status," +
            "t1.OPERATOR as operator,t1.CREATE_TIME as createTime,t1.UPDATE_TIME as updateTime FROM ts_menu_info t1 " +
            "left join tr_role_menu t2 ON  t1.ID = t2.MENU_ID AND t2.STATUS ='E' "+
//            "left join mul_language t6 ON t1.MENU_CODE = t6.CODE and t6.STATUS='E' AND t6.LANGUAGE_TYPE=#{languageType}"+
            "where t2.role_id in ("+
            "select T1.role_id from (select role_id from tr_staff_role t3 where t3.staff_id=#{staffId} and t3.STATUS ='E') T1 "+
            "union select T2.role_id from (select role_id from tr_group_role t4 left join tr_group_staff t5 " +
            "on t4.GROUP_ID = t5.GROUP_ID where t5.staff_id=#{staffId} AND t4.STATUS ='E' AND t5.STATUS ='E') T2) "+
            "and t1.ID<>-4 and t1.IS_MENU=1 and t1.STATUS='E' order by t1.SORT")
    List<MenuInfo> queryUserMenu(QueryStaffMenuEvt evt);

//    @Delete("delete from ts_menu_info where ID = #{id} OR PARENT_ID = #{id}")
    /**删除菜单 修改状态为D*/
    @Update("update ts_menu_info set status=#{status} where id=#{id} ")
    int doDelete(DeleteMenuEvt deleteMenuEvt);

    /**修改菜单 */
    @UpdateProvider(type = MenuProvider.class , method = "doEdit")
    int doEdit(EditMenuEvt evt);

    /**删除角色菜单关系 */
    @Delete("delete from tr_role_menu where MENU_ID = #{menuId}")
    int doDeleteRoleMenu(DeleteRoleMenuEvt deleteRoleMenuEvt);

    /**修改菜单柱状态*/
    @Update("update ts_menu_info set status=#{status} where id=#{id} ")
    int updateStatus(EditMenuEvt evt);
    
    /**查出所有菜单*/
//    @Select("select distinct t1.ID,t1.PARENT_ID as parentId,t2.NAME as menuName," +
//            "t1.SORT sort,t1.MENU_IMG as menuImg,t1.MENU_URL as menuUrl,t1.MENU_CODE as menuCode," +
//            "t1.IS_MENU isMenu,t1.IS_LOCAL as isLocal,t1.OPEN_TYPE as openType,t1.STATUS as status," +
//            "t1.OPERATOR as operator,t1.CREATE_TIME as createTime,t1.UPDATE_TIME as updateTime from ts_menu_info t1 "
//    		+ "left join mul_language t2 on t1.MENU_CODE=t2.CODE and t2.STATUS='E' "
//    		+ "where t1.status='E' "
//    		+ "and t2.SOURCES=1 "
//    		+ "and t2.language_type=#{languageType}"
//    		+ "order by t1.sort")
    @SelectProvider(type = MenuProvider.class , method = "queryAll")
    List<MenuInfo> queryAll(QueryMenuEvt evt);
}
