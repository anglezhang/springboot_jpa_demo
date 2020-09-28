package cn.com.doone.tx.cloud.service.user.controller;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.doone.tx.cloud.service.config.utils.TreeUtils;
import cn.com.doone.tx.cloud.service.user.dao.mapper.MenuMapper;
import cn.com.doone.tx.cloud.service.user.evt.menu.QueryMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.menuInfo.QueryPermissionMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.QueryStaffMenuEvt;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.log.annotation.ServiceAround;
import cn.com.doone.tx.cloud.tool.login.bean.MenuInfo;
import cn.com.doone.tx.cloud.tool.service.common.base.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;


/**
 * Created by yecz on 2017/1/22 0022.
 */
@RestController
@RequestMapping("/menuInfo")
@Api(description = "用户角色菜单服务")
public class MenuInfoController {
	
	private static final Logger LOGGER = Logger.getLogger(MenuInfoController.class);

    @Autowired
    private MenuMapper menuMapper;

    @Value("${web-paths:}")
    private String webPaths;
    

	@ServiceAround
	@ApiOperation(value = "查询有权限的菜单#查询", notes = "respCode 1-成功 0-失败")
	@RequestMapping(value = "queryPermissionMenu", method = RequestMethod.POST)
	public ServerResp<Object> queryPermissionMenu(
			@RequestBody Message<QueryPermissionMenuEvt> message) {
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			QueryPermissionMenuEvt evt = message.getBody();
			if ("1".equals(evt.getIsSuperManager())) {
				evt.setStaffId(null);
			} else {
				evt.setStaffId(evt.getOperator());
			}
//			resp.success(sysAccessService.queryPermissionMenu(evt));
		} catch (Exception e) {
			LOGGER.error("查询有权限的菜单异常：" + e, e);
			resp.systemError();
		}

		return resp;
	}
    

    @ServiceAround
    @ApiOperation(value="查询用户角色菜单#查询", notes="respCode 1-成功 0-失败")
    @RequestMapping(value="queryUserMenu" , method = RequestMethod.POST)
    public ServerResp<Object> queryUserMenu(@RequestBody Message<QueryStaffMenuEvt> message){
        QueryStaffMenuEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            List<MenuInfo> menus = new ArrayList<MenuInfo>();
            List<MenuInfo> allMenus;
            Long parentId = 0L;
            //如果是超级管理员则查询所有菜单
            if ("1".equals(evt.getIsSuperManager())){
//                menus =  menuMapper.queryAllMenu(evt);
            	//获取所有菜单
            	QueryMenuEvt queryMenuEvt = new QueryMenuEvt();
//            	queryMenuEvt.setLanguageType(evt.getLanguageType());
            	queryMenuEvt.setIsMenu(1);
            	allMenus = menuMapper.queryAll(queryMenuEvt);
            }else {
            	allMenus = menuMapper.queryUserMenu(evt);
            }

			menus = recurMenuListByParentId(parentId, allMenus);
            //获取系统对应菜单id
//        	QuerySysAccessEvt querySysAccessEvt = new QuerySysAccessEvt();
//        	if (StringUtils.isNotBlank(evt.getSysCode())) {
//        		querySysAccessEvt.setSysCode(evt.getSysCode());
//        		querySysAccessEvt.setLanguageType(evt.getLanguageType());
//        		List<SysAccessInfo> sysList = sysAccessService.queryDataList(querySysAccessEvt);
//        		if (sysList != null && sysList.size() > 0) {
//        			parentId = sysList.get(0).getMenuId();
//        			//递归获取当前系统菜单下的所有子级菜单
//        			menus = recurMenuListByParentId(parentId, allMenus);
//        		}
//			}
            //外系统连接中的IP 端口 读取配置文件
            for (MenuInfo menuInfo:menus){
                if (menuInfo.getIsLocal() != null && menuInfo.getIsLocal()==0){//外系统菜单
                    if (StringUtils.isNotBlank(webPaths)){
//                        System.out.println(">>>>>>>>>>webPaths<<<<<<<<<<<"+webPaths);
                        JSONObject json = JSONObject.fromObject(webPaths);
                        Iterator jsonKeys = json.keys();
                        while (jsonKeys.hasNext())
                        {
                            String key = String.valueOf(jsonKeys.next());
                            String value = (String) json.get(key);
                            String menuUrl = menuInfo.getMenuUrl();
//                            if (menuUrl!= null && menuUrl.indexOf(key)>-1){
                            if (StringUtils.isNotBlank(menuUrl)&&menuUrl.indexOf("/")>0){
                                String contextPath = menuUrl.substring(0,menuUrl.indexOf("/"));
                                if (key.equals(contextPath)){
                                    menuUrl = menuUrl.replace(contextPath,value);//替换
                                    menuInfo.setMenuUrl(menuUrl);
                                }
                            }
                        }
                    }

                }
            }
            //将菜单list转换为树形结构
            Long rootId = parentId;
            List<MenuInfo> treeList = null;
            if (menus!=null&&menus.size()>0){
                treeList = TreeUtils.factorTree(menus,"id","parentId",
                        "children",rootId);
            }
            return resp.success(treeList);
        }catch (Exception e){
            e.printStackTrace();
            return  resp.systemError();
        }
    }

    /** 根据父级id递归获得所有子级菜单 */
    public List<MenuInfo> recurMenuListByParentId(Long parentId, List<MenuInfo> menuList){
    	List<MenuInfo> retList = new ArrayList<MenuInfo>();
    	if (menuList != null) {
    		for (int i = 0; i < menuList.size(); i++) {
    			if (menuList.get(i).getParentId() != null) {
    				Long pId = menuList.get(i).getParentId();
    				if (parentId.equals(pId)) {
    					retList.add(menuList.get(i));
    					Long innerParentId = menuList.get(i).getId();
    					recurMenuList(innerParentId, menuList, retList);
    				}
    			}
    		}
		}
    	return retList;
    }
    
    public void recurMenuList(Long parentId, List<MenuInfo> menuList, List<MenuInfo> retList){
    	for (int i = 0; i < menuList.size(); i++) {
			if (menuList.get(i).getParentId() != null) {
				Long pId = menuList.get(i).getParentId();
				if (parentId.equals(pId)) {
					retList.add(menuList.get(i));
					Long innerParentId = menuList.get(i).getId();
					recurMenuList(innerParentId, menuList, retList);
				}
			}
		}
    }
}
