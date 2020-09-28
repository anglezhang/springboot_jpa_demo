package cn.com.doone.tx.cloud.service.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;

import cn.com.doone.tx.cloud.service.user.bean.MenuInfoBean;
import cn.com.doone.tx.cloud.service.user.evt.menu.AddMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.menu.DeleteMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.menu.EditMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.menu.QueryMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.QueryStaffMenuEvt;
import cn.com.doone.tx.cloud.service.user.service.MenuService;
import cn.com.doone.tx.cloud.tool.common.enums.CommonResultCode;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.common.util.DictLangUtil;
import cn.com.doone.tx.cloud.tool.log.annotation.ServiceAround;
import cn.com.doone.tx.cloud.tool.login.bean.MenuInfo;
import cn.com.doone.tx.cloud.tool.service.common.base.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by liujx on 2017/2/13 0013.
 */
@RestController
@RequestMapping("/menu")
@Api(description = "菜单服务")
public class MenuController {
    @Autowired
    private MenuService sysMenuService;
    
    @Autowired
    private DictLangUtil dictLangUtil;

    @ServiceAround
    @ApiOperation(value="查询系统菜单数量#查询", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "menuCount" , method = RequestMethod.POST)
    public ServerResp<Object> menuCount(@RequestBody Message<QueryMenuEvt> message){
        QueryMenuEvt evt = message.getBody();
        ServerResp<Object> resp=new ServerResp<Object>();
        try {
            int cnt = sysMenuService.queryCount(evt);
            return resp.success(cnt);
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }
    
    @ServiceAround
    @ApiOperation(value="查询系统菜单列表#查询", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "queryByParam" , method = RequestMethod.POST)
    public ServerResp<Object> queryMenuByParam(@RequestBody Message<QueryMenuEvt> message){
        QueryMenuEvt evt = message.getBody();
        PageHelper.startPage(evt.getQueryPage(),evt.getQuerySize());
        ServerResp<Object> resp=new ServerResp<Object>();
        try {
            return resp.success(sysMenuService.queryByParam(evt));
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }
    
    @ServiceAround
    @ApiOperation(value="查询系统菜单详情#查询", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "queryDetail" , method = RequestMethod.POST)
    public ServerResp<Object> queryDetail(@RequestBody Message<QueryMenuEvt> message){
        QueryMenuEvt evt = message.getBody();
        ServerResp<Object> resp=new ServerResp<Object>();
        try {
            return resp.success(sysMenuService.queryDetail(evt));
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }
    
    @ServiceAround
    @ApiOperation(value="添加菜单#受理", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "doAdd" , method = RequestMethod.POST)
    public ServerResp<Object> doAdd(@RequestBody Message<AddMenuEvt> message){
        AddMenuEvt evt = message.getBody();
//        if(StringUtils.isBlank(evt.getMenuImg())){
//            evt.setMenuImg(getMenuImg());
//        }
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            //查出菜单或功能按钮的数量用于设置菜单编码
            QueryMenuEvt queryMenuEvt = new QueryMenuEvt();
            queryMenuEvt.setMenuCode(evt.getMenuCode());
//            queryMenuEvt.setLanguageType(evt.getLanguageType());
            int isExist = sysMenuService.queryCodeIsExist(queryMenuEvt);
            if(isExist>0) {
//                return resp.error((evt.getIsMenu() == 1 ? "菜单" : "功能") + "编码已存在");
            	return resp.error("编码已存在");
            }
//            queryMenuEvt.setParentId(evt.getParentId());
//            queryMenuEvt.setMenuName(evt.getMenuName());
//            int isExistName = sysMenuService.queryNameIsExist(queryMenuEvt);
//            if(isExistName>0) {
//                return resp.error((evt.getIsMenu() == 1 ? "菜单" : "功能") + "名称已存在");
//            }
            QueryMenuEvt queryList = new QueryMenuEvt();
            queryList.setParentId(evt.getParentId());
//            queryList.setLanguageType(evt.getLanguageType());
            //同级名称是否重复
            List<MenuInfo> subList = sysMenuService.queryByParam(queryList);
            for (MenuInfo subMenu : subList) {
            	if (subMenu != null) {
            		if (evt.getMenuName().equals(subMenu.getMenuName())) {
            			return resp.error("菜单名称已存在");
            		}
				}
			}
            MenuInfoBean menuInfoBean = sysMenuService.doAdd(evt);
            Long id = menuInfoBean.getId();
            if (id!=null){
                return resp.success(menuInfoBean);
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
    @ApiOperation(value="修改菜单#受理", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "doEdit" , method = RequestMethod.POST)
    public ServerResp<Object> doEdit(@RequestBody Message<EditMenuEvt> message){
        EditMenuEvt evt = message.getBody();
        ServerResp<Object> resp=new ServerResp<>();
        try {
            QueryMenuEvt queryMenuEvt = new QueryMenuEvt();
            queryMenuEvt.setId(evt.getId());
//            queryMenuEvt.setLanguageType(evt.getLanguageType());
            MenuInfo menuInfo = sysMenuService.queryDetail(queryMenuEvt);
            if(menuInfo!=null){
                queryMenuEvt.setParentId(menuInfo.getParentId());
            }
            queryMenuEvt.setMenuCode(evt.getMenuCode());
            //编码是否重复
            int isExist = sysMenuService.queryCodeIsExist(queryMenuEvt);
            if (isExist > 0) {
                return resp.error("编码已存在");
            }
            QueryMenuEvt queryList = new QueryMenuEvt();
            queryList.setParentId(evt.getParentId());
//            queryList.setLanguageType(evt.getLanguageType());
            //同级名称是否重复
            List<MenuInfo> subList = sysMenuService.queryByParam(queryList);
            for (MenuInfo subMenu : subList) {
            	if (subMenu != null) {
            		if (!subMenu.getId().equals(evt.getId())) {
            			if (evt.getMenuName().equals(subMenu.getMenuName())) {
            				return resp.error("名称已存在");
            			}
					}
				}
			}
//            queryMenuEvt.setMenuName(evt.getMenuName());
//            int isExistName = sysMenuService.queryNameIsExist(queryMenuEvt);
//            if (isExistName > 0) {
//                return resp.error("名称已存在");
//            }
            int n = sysMenuService.doEdit(evt);
            if (n>=1){
                return resp.success("操作成功");
            }else {
                return resp.error(CommonResultCode.editFail.getRespCode(),
                        CommonResultCode.editFail.getRespMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }

    @ServiceAround
    @ApiOperation(value="修改菜单状态#受理", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "updateStatus" , method = RequestMethod.POST)
    public ServerResp<Object>  updateStatus(@RequestBody Message<EditMenuEvt> message){
        EditMenuEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            int n = sysMenuService.updateStatus(evt);
            return resp.success(n);
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }

    @ServiceAround
    @ApiOperation(value="删除菜单#受理", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "doDelete" , method = RequestMethod.POST)
    public ServerResp<Object> doDelete(@RequestBody Message<DeleteMenuEvt> message){
        DeleteMenuEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            /**查询改菜单是否下挂了子菜单*/
            QueryMenuEvt queryMenuEvt = new QueryMenuEvt();
            queryMenuEvt.setParentId(evt.getId());
            int cnt  = sysMenuService.queryCount(queryMenuEvt);
            if (cnt>=1){
                return resp.error("该菜单下挂有子菜单，无法删除");
            }
            //删除菜单-修改状态为D
            int n = sysMenuService.doDelete(evt);
            if (n > 0) {
            	return resp.success(n);
			} else {
				return resp.error("删除失败");
			}
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError("删除失败");
        }
    }
    /**
     * 1、菜单管理树(无功能) scope = menu
     *2、超级管理员的角色分配时的菜单树含功能 scoup = role
     * */
    @ServiceAround
    @ApiOperation(value="菜单树#查询", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "tree" , method = RequestMethod.POST)
    public ServerResp<Object> tree(@RequestBody Message<QueryMenuEvt> message){
        QueryMenuEvt evt = message.getBody();
        ServerResp<Object> resp=new ServerResp<Object>();
        try {
            return resp.success(sysMenuService.tree(evt));
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }

    /**
     * 角色分配时的菜单树
     */
    @ServiceAround
    @ApiOperation(value="用户菜单(按钮)树#查询", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "userMenuTree" , method = RequestMethod.POST)
    public  ServerResp<Object> userMenuTree(@RequestBody Message<QueryStaffMenuEvt> message){
        QueryStaffMenuEvt evt = message.getBody();
        ServerResp<Object> resp=new ServerResp<Object>();
        try {
            resp.success(sysMenuService.userMenuTree(evt));
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }
    
    @ServiceAround
    @ApiOperation(value="查询所有系统菜单列表#查询", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "queryAll" , method = RequestMethod.POST)
    public ServerResp<Object> queryAll(@RequestBody Message<QueryMenuEvt> message){
        QueryMenuEvt evt = message.getBody();
        ServerResp<Object> resp=new ServerResp<Object>();
        try {
            return resp.success(sysMenuService.queryAll(evt));
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }
    
    /**菜单图标随机分配*/
    public String getMenuImg(){
        String[] menuImgs = {"fa-asterisk","fa-flag","fa-home","fa-file-pdf-o","fa-cube","fa-folder-open"};
        int x=(int)(Math.random()*5);
        return menuImgs[x];
    }

}
