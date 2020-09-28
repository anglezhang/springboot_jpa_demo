package cn.com.doone.tx.cloud.service.user.dao.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import cn.com.doone.tx.cloud.service.user.dao.provider.RoleMenuProvider;
import cn.com.doone.tx.cloud.service.user.evt.menuInfo.QueryPermissionMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.rolemenu.DeleteRoleMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.rolemenu.QueryRoleMenuEvt;
import cn.com.doone.tx.cloud.service.user.info.RoleMenuInfo;
import cn.com.doone.tx.cloud.tool.login.bean.MenuInfo;


/**
 * yecz
 */
@Mapper
public interface RoleMenuMapper {

    //查询角色菜单
    @SelectProvider(type = RoleMenuProvider.class , method = "queryRoleMenuByParam")
    List<RoleMenuInfo> queryRoleMenuByParam(QueryRoleMenuEvt evt);

    //删除
    @Delete("delete from tr_role_menu where role_id = #{roleId}")
    int doDelete(DeleteRoleMenuEvt evt);

    @Delete("delete from tr_role_menu where menu_id = #{menuId}")
    int doDeleteByMenuId(DeleteRoleMenuEvt evt);

    //查询角色菜单、按钮信息
    @SelectProvider(type = RoleMenuProvider.class , method = "queryRoleMenuInfos")
    List<MenuInfo> queryRoleMenuInfos(QueryRoleMenuEvt evt);
    
    //查
    @SelectProvider(type = RoleMenuProvider.class , method = "queryPermissionMenu")
    List<MenuInfo> queryPermissionMenu(QueryPermissionMenuEvt evt);

}
