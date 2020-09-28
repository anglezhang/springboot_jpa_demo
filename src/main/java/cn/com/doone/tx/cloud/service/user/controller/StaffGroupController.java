package cn.com.doone.tx.cloud.service.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;

import cn.com.doone.tx.cloud.service.user.bean.CustomGroupExtendInfoBean;
import cn.com.doone.tx.cloud.service.user.bean.StaffGroupBean;
import cn.com.doone.tx.cloud.service.user.bean.StaffGroupExtendInfoBean;
import cn.com.doone.tx.cloud.service.user.evt.common.QueryAreaEvt;
import cn.com.doone.tx.cloud.service.user.evt.common.QueryCityEvt;
import cn.com.doone.tx.cloud.service.user.evt.groupRole.AddGroupRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.groupRole.QueryGroupRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.QueryStaffEvt;
import cn.com.doone.tx.cloud.service.user.evt.staffGroup.AddStaffGroupEvt;
import cn.com.doone.tx.cloud.service.user.evt.staffGroup.DeleteStaffGroupEvt;
import cn.com.doone.tx.cloud.service.user.evt.staffGroup.EditStaffGroupEvt;
import cn.com.doone.tx.cloud.service.user.evt.staffGroup.QueryStaffGroupEvt;
import cn.com.doone.tx.cloud.service.user.evt.staffGroup.QueryStaffGroupRelTreeEvt;
import cn.com.doone.tx.cloud.service.user.evt.templatetheme.BindStaffGroupThemeEvt;
import cn.com.doone.tx.cloud.service.user.info.AreaBean;
import cn.com.doone.tx.cloud.service.user.info.CityBean;
import cn.com.doone.tx.cloud.service.user.info.ProvinceBean;
import cn.com.doone.tx.cloud.service.user.info.RoleInfo;
import cn.com.doone.tx.cloud.service.user.info.StaffGroupInfo;
import cn.com.doone.tx.cloud.service.user.info.StaffInfo;
import cn.com.doone.tx.cloud.service.user.service.CustomGroupExtendInfoService;
import cn.com.doone.tx.cloud.service.user.service.StaffGroupExtendInfoService;
import cn.com.doone.tx.cloud.service.user.service.StaffGroupService;
import cn.com.doone.tx.cloud.service.user.service.StaffService;
import cn.com.doone.tx.cloud.tool.common.enums.CommonResultCode;
import cn.com.doone.tx.cloud.tool.common.enums.YesOrNo;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.log.annotation.ServiceAround;
import cn.com.doone.tx.cloud.tool.service.common.base.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by liujx on 2017/2/13 0013.
 */
@RestController
@RequestMapping("/group")
@Api(description = "用户组服务")
public class StaffGroupController {

    @Autowired
    private StaffGroupService staffGroupService;
    
    @Autowired
	private StaffService staffService;

    @Autowired
    private StaffGroupExtendInfoService staffGroupExtendInfoService;

    @Autowired
    private CustomGroupExtendInfoService customGroupExtendInfoService;

    @ServiceAround
    @ApiOperation(value="查询用户组数量#查询", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "queryCount" , method = RequestMethod.POST)
    public ServerResp<Object> queryCount(@RequestBody Message<QueryStaffGroupEvt> message){
        QueryStaffGroupEvt evt = message.getBody();
        ServerResp<Object> resp=new ServerResp<Object>();
        try {
            int cnt  = staffGroupService.queryCount(evt);
            return resp.success(cnt);
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }

    @ServiceAround
    @ApiOperation(value="查询用户组列表#查询", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "queryByParam" , method = RequestMethod.POST)
    public ServerResp<Object> queryByParam(@RequestBody Message<QueryStaffGroupEvt> message){
        QueryStaffGroupEvt evt = message.getBody();
        PageHelper.startPage(evt.getQueryPage(),evt.getQuerySize());
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            return  resp.success(staffGroupService.queryByParam(evt));
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }

    @ServiceAround
    @ApiOperation(value="添加用户组#受理", notes="respCode 1-成功 0-失败")
    @RequestMapping(value = "doAdd" , method = RequestMethod.POST)
    public ServerResp<Object> doAdd(@RequestBody Message<AddStaffGroupEvt> message){
        AddStaffGroupEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            QueryStaffGroupEvt queryStaffGroupEvt = new QueryStaffGroupEvt();
            queryStaffGroupEvt.setParentId(evt.getParentId());
            queryStaffGroupEvt.setGroupName(evt.getGroupName());
            int isExist = staffGroupService.queryNameIsExist(queryStaffGroupEvt);
            if(isExist>0){
                return resp.error("组织架构名已存在");
            }
            queryStaffGroupEvt.setId(evt.getParentId());
            int isForbid = staffGroupService.queryGroupIsForbid(queryStaffGroupEvt);
            if(isForbid>0){
                return resp.error("上级组织架构已被禁用");
            }
            StaffGroupBean staffGroupBean = staffGroupService.doAdd(evt);
            Long id = staffGroupBean.getId();
            if (id!=null){
            	if(evt.isReturnInfo()){
            		return resp.success(staffGroupBean);
            	}
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
    @ApiOperation(value="修改用户组#受理", notes="respCode 1-成功 0-失败")
    @RequestMapping(value = "doEdit" , method = RequestMethod.POST)
    public ServerResp<Object> doEdit(@RequestBody  Message<EditStaffGroupEvt> message){
        EditStaffGroupEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            if(StringUtils.isNotBlank(evt.getGroupName())){
                QueryStaffGroupEvt queryStaffGroupEvt = new QueryStaffGroupEvt();
                queryStaffGroupEvt.setParentId(evt.getParentId());
                queryStaffGroupEvt.setGroupName(evt.getGroupName());
                queryStaffGroupEvt.setId(evt.getId());
                int isExist = staffGroupService.queryNameIsExist(queryStaffGroupEvt);
                if(isExist>0){
                    return resp.error("组织架构名已存在");
                }
            }
            int n = staffGroupService.doEdit(evt);
            if (n>=1){
                return resp.success(n);
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
    @ApiOperation(value="删除用户组#受理", notes="respCode 1-成功 0-失败")
    @RequestMapping(value = "doDelete" , method = RequestMethod.POST)
    public ServerResp<Object> doDelete(@RequestBody Message<DeleteStaffGroupEvt> message){
        DeleteStaffGroupEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            /**查询当前用户组是否下挂了子用户组*/
            QueryStaffGroupEvt queryStaffGroupEvt = new QueryStaffGroupEvt();
            queryStaffGroupEvt.setParentId(evt.getId());
            int cnt = staffGroupService.queryCount(queryStaffGroupEvt);
            if (cnt>=1){
                return resp.error("该组织架构下存在子架构");
            }else {
            	//该用户组下是否有用户
            	QueryStaffEvt staffEvt = new QueryStaffEvt();
            	staffEvt.setGroupId(evt.getId());
            	int userCount = staffService.queryCount(staffEvt);
            	if(userCount>0){
            		return resp.error("该组织架构下挂有账号");
            	}else{
	                /**删除用户组修改状态为D*/
	                int n = staffGroupService.doDelete(evt);
	                if (n>=1){
	                    return resp.success(n);
	                }else{
	                    return resp.error(CommonResultCode.deleteFail.getRespCode(),
	                            CommonResultCode.deleteFail.getRespMsg());
	                }
            	}
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }

    @ServiceAround
    @ApiOperation(value="启用禁用组织架构#受理", notes="respCode 1-成功 0-失败")
    @RequestMapping(value = "updateStatus" , method = RequestMethod.POST)
    public ServerResp<Object> updateStatus(@RequestBody Message<DeleteStaffGroupEvt> message){
        DeleteStaffGroupEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
        	//若是禁用则判断条件，若启用则直接启用
        	if ("F".equals(evt.getStatus())) {
        		/**查询当前用户组是否下挂了子用户组*/
        		QueryStaffGroupEvt queryStaffGroupEvt = new QueryStaffGroupEvt();
        		queryStaffGroupEvt.setParentId(evt.getId());
        		int cnt = staffGroupService.queryCount(queryStaffGroupEvt);
        		if (cnt>=1){
        			return resp.error("该组织架构下存在子架构");
        		}else {
        			//该用户组下是否有用户
        			QueryStaffEvt staffEvt = new QueryStaffEvt();
        			staffEvt.setGroupId(evt.getId());
        			int userCount = staffService.queryCount(staffEvt);
        			if(userCount>0){
        				return resp.error("该组织架构下挂有账号");
        			}else{
        				int n = staffGroupService.updateStatus(evt);
        				if (n>=1){
        					return resp.success(n);
        				}else{
        					return resp.error(CommonResultCode.deleteFail.getRespCode(),
        							"删除失败");
        				}
        			}
        		}
			} else {
				int n = staffGroupService.updateStatus(evt);
				if (n>=1){
					return resp.success(n);
				}else{
					return resp.error(CommonResultCode.deleteFail.getRespCode(),
							"删除失败");
				}
			}
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }

    /**
     * 用户管理、用户组管理中展示的用户组树
     * 1、超级管理员展示所有
     * 2、普通用户展示其所在用户组及下级用户组
     */
    @ServiceAround
    @ApiOperation(value="用户组树#查询", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "userGroupTree" , method = RequestMethod.POST)
    public ServerResp<Object> userGroupTree(@RequestBody Message<QueryStaffGroupEvt> message){
        QueryStaffGroupEvt evt= message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        System.out.println("super:"+evt.getIsSuperManager());
        try {
            if(YesOrNo.Y.name().equals(evt.getIsSuperManager())){
                /**超级管理员*/
                return resp.success(staffGroupService.queryAllGroup(evt));
            }else {
                /**普通用户*/
                return resp.success(staffGroupService.userGroupTree(evt));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }

    }

    /**
     * 角色分配-数据权限的用户组树
     * 1、超级管理员查询所有用户组
     * 2、普通用户查询其所有角色拥有的数据权限(用户组集合)
     */
    @ServiceAround
    @ApiOperation(value="角色分配-数据权限的用户组树#查询", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "groupTreeForData" , method = RequestMethod.POST)
    public ServerResp<Object> groupTreeForData(@RequestBody Message<QueryStaffGroupEvt> message){
        QueryStaffGroupEvt evt= message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            return resp.success(staffGroupService.groupTreeForData(evt));
        } catch (Exception e) {
            e.printStackTrace();
            return resp.error("操作失败");
        }

    }

    @ServiceAround
    @ApiOperation(value="查询所有省份信息#查询", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "queryAllProvince" , method = RequestMethod.POST)
    public ServerResp<Object> queryAllProvince(@RequestBody Message<Map<String,Object>> message){
        System.out.println("查询所有省份信息");
        ServerResp<Object> resp = new ServerResp<Object>();
        try {
            List<ProvinceBean> provinceBeans = staffGroupService.queryAllProvince();
            return resp.success(provinceBeans);
        }catch (Exception e){
            e.printStackTrace();
            return resp.error("查询失败");
        }
    }

    @ServiceAround
    @ApiOperation(value="根据省份CODE查询地市信息#查询", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "queryCityByPCode" , method = RequestMethod.POST)
    public ServerResp<Object> queryCityByPCode(@RequestBody Message<QueryCityEvt> message){
        QueryCityEvt evt = message.getBody();
        ServerResp<Object> resp = new ServerResp<Object>();
        try {
            List<CityBean> cityBeans = staffGroupService.queryCityByPCode(evt);
            return resp.success(cityBeans);
        }catch (Exception e){
            e.printStackTrace();
            return resp.error("查询失败");
        }
    }

    @ServiceAround
    @ApiOperation(value="根据地市ID查询区域信息#查询", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "queryAreaByCCode" , method = RequestMethod.POST)
    public ServerResp<Object> queryAreaByCCode(@RequestBody Message<QueryAreaEvt> message){
        QueryAreaEvt evt = message.getBody();
        ServerResp<Object> resp = new ServerResp<Object>();
        try {
            List<AreaBean> areaBeans = staffGroupService.queryAreaByCCode(evt);
            return resp.success(areaBeans);
        }catch (Exception e){
            e.printStackTrace();
            return resp.error("查询失败");
        }
    }

    @ServiceAround
    @ApiOperation(value="根据用户组查询角色列表#查询", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "queryRoleByGroup" , method = RequestMethod.POST)
    public ServerResp<Object> queryRoleByGroup(@RequestBody Message<QueryGroupRoleEvt> message){
        QueryGroupRoleEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        List<RoleInfo> roleInfos = staffGroupService.queryRoleByGroup(evt.getGroupId());
        return resp.success(roleInfos);
    }

    @ServiceAround
    @ApiOperation(value="用户组绑定角色#受理", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "setGroupRole" , method = RequestMethod.POST)
    public ServerResp<Object> setGroupRole(@RequestBody Message<AddGroupRoleEvt> message){
        AddGroupRoleEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            int cnt = 0;
            //删除关系
            DeleteStaffGroupEvt deleteStaffGroupEvt = new DeleteStaffGroupEvt();
            deleteStaffGroupEvt.setId(evt.getGroupId());
            int n1 = staffGroupService.deleteGroupRole(deleteStaffGroupEvt);
//		    插入新的关系
            for(Long roleId : evt.getIds()){
                evt.setRoleId(roleId);
                if(staffGroupService.setGroupRole(evt).getId()!=null){
                    cnt++;
                }
            }
            return resp.success(cnt);
        } catch (Exception e) {
            e.printStackTrace();
            return resp.error("绑定失败");
        }
    }

    /**用户组扩展信息*/
    @ServiceAround
    @RequestMapping(value = "queryStaffGroupExtendInfo" , method = RequestMethod.POST)
    @ApiOperation(value="通过id查用户组扩展信息#查询",notes = "respCode 0成功，-1失败")
    public ServerResp<Object> queryStaffGroupExtendInfo(@RequestBody Message<Map<String, String>> message ){
        Map<String, String> params = message.getBody();
        ServerResp<Object> resp=new ServerResp<Object>();
        StaffGroupExtendInfoBean staffGroupExtendInfoBean = new StaffGroupExtendInfoBean();
        staffGroupExtendInfoBean.setGroupId(Long.parseLong(params.get("baseId")));
        List<StaffGroupExtendInfoBean> list = staffGroupExtendInfoService.queryStaffGroupExtendInfoByParam(staffGroupExtendInfoBean);
        if (list.size()>0&&list.size()==1) {
            return resp.success(list.get(0));
        }
        return resp.error("扩展字段不存在");
    }
    /**扩展用户组信息*/
    @ServiceAround
    @RequestMapping(value = "queryExtendInfo" , method = RequestMethod.POST)
    @ApiOperation(value="通过id查扩展用户组扩展信息#查询",notes = "respCode 0成功，-1失败")
    public ServerResp<Object> queryCustomGroupExtendInfo(@RequestBody Message<Map<String, String>> message ){
        Map<String, String> params = message.getBody();
        ServerResp<Object> resp=new ServerResp<Object>();
        CustomGroupExtendInfoBean customGroupExtendInfoBean = new CustomGroupExtendInfoBean();
    	customGroupExtendInfoBean.setCustomGroupId(Long.parseLong(params.get("baseId")));
    	List<CustomGroupExtendInfoBean> list = customGroupExtendInfoService.queryCustomGroupExtendInfoByParam(customGroupExtendInfoBean);
    	if (list.size()>0&&list.size()==1) {
			return resp.success(list.get(0));
		}
    	return resp.error("扩展字段不存在");
    }
    
    @ServiceAround
	@ApiOperation(value = "主题授权#受理", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "doBindStaffGroupTheme", method = RequestMethod.POST)
	public ServerResp<Object> doBindStaffGroupTheme(@RequestBody Message<BindStaffGroupThemeEvt> message) {
		BindStaffGroupThemeEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<>();		
		try {			
			int n = staffGroupService.doBindStaffGroupTheme(evt);
			if (n >= 1) {
				return resp.success("授权成功");
			} else {
				return resp.error("授权失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}
	}
    
    @ServiceAround
	@ApiOperation(value = "用户组和用户关系树查询#查询", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "queryStaffGroupRelTree", method = RequestMethod.POST)
	public ServerResp<Object> queryStaffGroupRelTree(@RequestBody Message<QueryStaffGroupRelTreeEvt> message) {
    	QueryStaffGroupRelTreeEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<>();		
		try {
			List<Map<String,Object>> treeList = new ArrayList<Map<String,Object>>();
			Long rootGroupId = evt.getGroupId();
			if(rootGroupId == null){
				rootGroupId = -4L;
			}
			treeList.add(generateTreeBean(rootGroupId.intValue(),-1,"全部",true));//根目录			
			List<Map<String, Object>> subTreeList = getSubGroupStaffRelTreeList(rootGroupId);
			treeList.addAll(subTreeList);
			return resp.success(treeList);
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}
	}

    //递归查询部门和用户的关系树
	protected List<Map<String, Object>> getSubGroupStaffRelTreeList(Long groupId) {
		//查询用户组id下的所有用户组
		QueryStaffGroupEvt staffGroupEvt = new QueryStaffGroupEvt();
		staffGroupEvt.setParentId(groupId);
		List<Map<String,Object>> subTreeList = new ArrayList<Map<String,Object>>();
		List<StaffGroupInfo> staffGroupList = staffGroupService.queryStaffGroupInfoByParent(staffGroupEvt);
		for(StaffGroupInfo groupInfo:staffGroupList){
			//子部门
			subTreeList.add(generateTreeBean(groupInfo.getId().intValue(),groupId.intValue(),groupInfo.getGroupName(),true));
			//递归查询子部门下的树结构
			subTreeList.addAll(getSubGroupStaffRelTreeList(groupInfo.getId()));
		}
		//查询用户组id下的所有用户组
		QueryStaffEvt staffEvt = new QueryStaffEvt();
		staffEvt.setGroupId(groupId);
		List<StaffInfo> staffList = staffService.queryByParam(staffEvt);
		for(StaffInfo staffInfo:staffList){
			//子用户
			subTreeList.add(generateTreeBean(staffInfo.getId().intValue(),groupId.intValue(),staffInfo.getStaffName(),false));
		}
		return subTreeList;
	}
    
    protected Map<String,Object> generateTreeBean(int id, int parentId, String name,boolean isGroup){
    	Map<String,Object> treeBean = new HashMap<String, Object>();
    	String parentIdStr = "group_"+parentId;
    	String idStr = "user_"+id;
    	if(isGroup){
    		idStr = "group_"+id;
    	}
    	treeBean.put("id", idStr);
    	treeBean.put("pId", parentIdStr);
    	treeBean.put("name", name);
    	treeBean.put("nocheck", isGroup);
    	treeBean.put("type", isGroup == true ? "0":"1");//0代表用户组，1代表用户
    	return treeBean;
    }
    @ServiceAround
    @ApiOperation(value="查询所有用户组信息#查询", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "queryAllByParam" , method = RequestMethod.POST)
    public ServerResp<Object> queryAllByParam(@RequestBody Message<QueryStaffGroupEvt> message){
        QueryStaffGroupEvt evt = message.getBody();
        ServerResp<Object> resp = new ServerResp<>();
        try {
            if("order".equals(evt.getType())){
                Map<String,Object> returnMap = new HashedMap();
                List<Map<String,Object>> nodes = new ArrayList<>();
                List<StaffGroupInfo> staffGroupInfoList = staffGroupService.queryByParam(evt);
                for (StaffGroupInfo staffGroupInfo:staffGroupInfoList){
                    Long id = staffGroupInfo.getId();
                    String name = staffGroupInfo.getGroupName();
                    Long parentId = staffGroupInfo.getParentId();
                    Map<String,Object> map = new HashedMap();
                    map.put("node",id);
                    map.put("name",name);
                    map.put("parent",parentId);
                    map.put("is_selected",0);
                    nodes.add(map);
                }
                returnMap.put("tree_type","checkbox");
                returnMap.put("nodes",nodes);
                return resp.success(returnMap);
            }else {
                return resp.success(staffGroupService.queryByParam(evt));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }
}
