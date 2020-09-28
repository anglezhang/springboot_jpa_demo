package cn.com.doone.tx.cloud.service.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;

import cn.com.doone.tx.cloud.service.user.bean.RoleInfoBean;
import cn.com.doone.tx.cloud.service.user.evt.StaffRole.QueryStaffRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.groupRole.DeleteGroupRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.role.AddRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.role.DeleteRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.role.EditRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.role.QueryRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.roleGroup.DeleteRoleGroupEvt;
import cn.com.doone.tx.cloud.service.user.evt.rolemenu.DeleteRoleMenuEvt;
import cn.com.doone.tx.cloud.service.user.info.RoleInfo;
import cn.com.doone.tx.cloud.service.user.service.RoleGroupService;
import cn.com.doone.tx.cloud.service.user.service.RoleMenuService;
import cn.com.doone.tx.cloud.service.user.service.RoleService;
import cn.com.doone.tx.cloud.tool.common.enums.CommonResultCode;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.log.annotation.ServiceAround;
import cn.com.doone.tx.cloud.tool.service.common.base.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by yecz on 2017/1/22 0022.
 */
@RestController
@RequestMapping("/role")
@Api(description = "角色服务")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private RoleGroupService roleGroupService;

    @ServiceAround
    @ApiOperation(value="查询系统角色数量#查询", notes="respCode 1-成功 0-失败")
    @RequestMapping(value = "roleCount" , method = RequestMethod.POST)
    public ServerResp<Object> roleCount(@RequestBody Message<QueryRoleEvt> message){
        QueryRoleEvt evt = message.getBody();
        ServerResp<Object> resp=new ServerResp<Object>();
        int cnt;
        try {
            cnt = roleService.queryRoleCount(evt);
            return resp.success(cnt);
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }

    @ServiceAround
    @ApiOperation(value="查询系统角色#查询", notes="respCode 1-成功 0-失败")
    @RequestMapping(value = "queryRoleByParam" , method = RequestMethod.POST)
    public ServerResp<Object> queryRoleByParam(@RequestBody Message<QueryRoleEvt> message){
        QueryRoleEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        List<RoleInfo> roles = null;
        try {
            PageHelper.startPage(evt.getQueryPage(),evt.getQuerySize());
            roles = roleService.queryRoleByParam(evt);
            return resp.success(roles);
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }

    @ServiceAround
    @ApiOperation(value="添加系统角色#受理", notes="respCode 1-成功 0-失败")
    @RequestMapping(value = "doAdd" , method = RequestMethod.POST)
    public ServerResp<Object> doAdd(@RequestBody  Message<AddRoleEvt> message){
        AddRoleEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            RoleInfoBean roleInfoBean = roleService.add(evt);
            Long id = roleInfoBean.getId();
            if (id!=null){
                return resp.success();
            }else {
                return resp.error(CommonResultCode.addFail.getRespCode(),
                        CommonResultCode.addFail.getRespMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }
    @ServiceAround
    @ApiOperation(value="编辑系统角色#受理", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "doEdit" , method = RequestMethod.POST)
    public ServerResp<Object> doEdit(@RequestBody Message<EditRoleEvt> message){
        EditRoleEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            int n = roleService.doEdit(evt);
            return resp.success(n);
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }
    @ServiceAround
    @ApiOperation(value="删除系统角色#受理", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "doDelete" , method = RequestMethod.POST)
    public ServerResp<Object> doDelete(@RequestBody Message<DeleteRoleEvt> message){
        DeleteRoleEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
       
        //删除tr_staff_role中的数据(账号绑定角色关系)
       /* DeleteStaffRoleEvt deleteStaffRoleEvt = new DeleteStaffRoleEvt();
        deleteStaffRoleEvt.setRoleId(evt.getId());
        int deleteStaffRole = roleService.doDeleteStaffRole(deleteStaffRoleEvt);*/
        
        //修改成：若已有账号与角色绑定，则不可删除该角色，并要给出相应提示（而当前系统没有提示，可以直接删除）。
        QueryStaffRoleEvt queryStaffRoleEvt = new QueryStaffRoleEvt();
        queryStaffRoleEvt.setRoleId(evt.getId());
        int staffRoleCount = roleService.queryStaffRoleCount(queryStaffRoleEvt);
        if(staffRoleCount > 0)
        	return resp.error("已有账号与该角色绑定，不可删除该角色!");
        //删除tr_group_role中的数据(用户组绑定角色关系)
        DeleteGroupRoleEvt deleteGroupRoleEvt = new DeleteGroupRoleEvt();
        deleteGroupRoleEvt.setRoleId(evt.getId());
        int deleteGroupRole = roleService.doDeleteGroupRole(deleteGroupRoleEvt);
        //删除tr_role_menu中的数据(角色绑定菜单关系)
        DeleteRoleMenuEvt deleteRoleMenuEvt = new DeleteRoleMenuEvt();
        deleteRoleMenuEvt.setRoleId(evt.getId());
        int delRoleMenu = roleMenuService.doDelete(deleteRoleMenuEvt);
        //删除tr_data_role中的数据(角色绑定用户组关系)
        DeleteRoleGroupEvt deleteRoleGroupEvt = new DeleteRoleGroupEvt();
        deleteRoleGroupEvt.setRoleId(evt.getId());
        int delRoleGroup = roleGroupService.doDelete(deleteRoleGroupEvt);
        //删除角色-修改状态为D
        int n = roleService.doDelete(evt);
        if (n>=1){
            return resp.success(n, "操作成功");
        }else {
            return resp.error("删除失败");
        }
    }
    @ServiceAround
    @ApiOperation(value="查询系统角色是否存在#受理", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "queryRoleIsExist" , method = RequestMethod.POST)
    public ServerResp<Object> queryRoleIsExist(@RequestBody Message<QueryRoleEvt> message){
        QueryRoleEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            int n = roleService.queryRoleIsExist(evt);
            return resp.success(n);
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }

    @ServiceAround
    @ApiOperation(value="查询所有角色信息#查询", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "queryAllByParam" , method = RequestMethod.POST)
    public ServerResp<Object> queryAllByParam(@RequestBody Message<QueryRoleEvt> message){
        QueryRoleEvt evt = message.getBody();
        ServerResp<Object> resp = new ServerResp<>();
        try {
            if("order".equals(evt.getType())){
                Map<String,Object> returnMap = new HashedMap();
                List<Map<String,Object>> nodes = new ArrayList<>();
                List<RoleInfo> roleInfoList = roleService.queryRoleByParam(evt);
                for (RoleInfo roleInfo:roleInfoList){
                    Long id = roleInfo.getId();
                    String name = roleInfo.getRoleName();
                    Map<String,Object> map = new HashedMap();
                    map.put("node",id);
                    map.put("name",name);
                    map.put("parent",0);
                    map.put("is_selected",0);
                    nodes.add(map);
                }
                returnMap.put("tree_type","checkbox");
                returnMap.put("nodes",nodes);
                return resp.success(returnMap);
            }else {
                return resp.success(roleService.queryRoleByParam(evt));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }
}
