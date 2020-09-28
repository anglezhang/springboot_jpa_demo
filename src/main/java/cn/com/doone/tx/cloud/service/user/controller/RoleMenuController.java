package cn.com.doone.tx.cloud.service.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.doone.tx.cloud.service.user.bean.RoleMenuBean;
import cn.com.doone.tx.cloud.service.user.evt.rolemenu.AddRoleMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.rolemenu.DeleteRoleMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.rolemenu.QueryRoleMenuEvt;
import cn.com.doone.tx.cloud.service.user.info.RoleMenuInfo;
import cn.com.doone.tx.cloud.service.user.service.RoleMenuService;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.log.annotation.ServiceAround;
import cn.com.doone.tx.cloud.tool.service.common.base.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * yecz.
 */
@RestController
@RequestMapping("/roleMenu")
@Api(description = "角色关联菜单服务")
public class RoleMenuController {

    @Autowired
    private RoleMenuService roleMenuService;

    @ServiceAround
    @ApiOperation(value="查询角色绑定菜单信息#查询", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "queryRoleMenuByParam" , method = RequestMethod.POST)
    public ServerResp<Object> queryRoleMenuByParam(@RequestBody Message<QueryRoleMenuEvt> message){
        QueryRoleMenuEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            List<RoleMenuInfo> roleMenus = roleMenuService.queryRoleMenuByParam(evt);
            return resp.success(roleMenus);
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }

    /**设置菜单角色*/
    @ServiceAround
    @ApiOperation(value="设置角色绑定菜单信息#受理", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "setRoleMenu" , method = RequestMethod.POST)
    public ServerResp<Object> doAdd(@RequestBody Message<AddRoleMenuEvt> message){
        AddRoleMenuEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            int cnt = 0;
            //设置前先删除角色菜单关系
            DeleteRoleMenuEvt delEvt = new DeleteRoleMenuEvt();
            delEvt.setRoleId(evt.getRoleId());
            roleMenuService.doDelete(delEvt);
            //插入新的角色菜单关系
            for(Long menuId : evt.getIds()){
                evt.setMenuId(menuId);
                RoleMenuBean roleMenuBean = roleMenuService.add(evt);
                if (roleMenuBean!=null){
                    if (roleMenuBean.getId()!=null){
                        cnt++;
                    }
                }
            }
            return resp.success(cnt);
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }
}
