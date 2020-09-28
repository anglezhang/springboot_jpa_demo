package cn.com.doone.tx.cloud.service.user.controller;

import java.util.ArrayList;
import java.util.Date;
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

import cn.com.doone.tx.cloud.service.user.bean.StaffExtendInfoBean;
import cn.com.doone.tx.cloud.service.user.bean.StaffInfoBean;
import cn.com.doone.tx.cloud.service.user.evt.StaffRole.AddStaffRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.StaffRole.DeleteStaffRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.StaffRole.QueryStaffRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.loginlock.QueryLoginLockEvt;
import cn.com.doone.tx.cloud.service.user.evt.loginrule.QueryLoginRuleEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.AddStaffEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.DeleteStaffEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.EditStaffEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.EditStaffPwdEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.QueryStaffEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.QueryStaffTaskEvt;
import cn.com.doone.tx.cloud.service.user.evt.staffGroup.QueryStaffGroupEvt;
import cn.com.doone.tx.cloud.service.user.info.LoginRuleInfo;
import cn.com.doone.tx.cloud.service.user.info.RoleInfo;
import cn.com.doone.tx.cloud.service.user.info.StaffInfo;
import cn.com.doone.tx.cloud.service.user.service.LoginRuleService;
import cn.com.doone.tx.cloud.service.user.service.StaffExtendInfoService;
import cn.com.doone.tx.cloud.service.user.service.StaffGroupService;
import cn.com.doone.tx.cloud.service.user.service.StaffService;
import cn.com.doone.tx.cloud.tool.common.enums.CommonResultCode;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.common.util.Md5Util;
import cn.com.doone.tx.cloud.tool.log.annotation.ServiceAround;
import cn.com.doone.tx.cloud.tool.service.common.base.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@Api(description = "用户管理服务")
public class StaffController {

	@Autowired
	private StaffService staffService;

	@Autowired
	private StaffExtendInfoService staffExtendInfoService;

	@Autowired
	private LoginRuleService loginRuleService;

	@Autowired
	private StaffGroupService staffGroupService;

	@ServiceAround
	@ApiOperation(value = "查询系统账号数量#查询", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "queryCount", method = RequestMethod.POST)
	public ServerResp<Object> userCount(@RequestBody Message<QueryStaffEvt> message) {
		QueryStaffEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			int cnt = staffService.queryCount(evt);
			return resp.success(cnt);
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}
	}

	@ServiceAround
	@ApiOperation(value = "查询系统账号列表#查询", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "queryByParam", method = RequestMethod.POST)
	public ServerResp<Object> queryByParam(@RequestBody Message<QueryStaffEvt> message) {
		QueryStaffEvt evt = message.getBody();
		PageHelper.startPage(evt.getQueryPage(), evt.getQuerySize());
		ServerResp<Object> resp = new ServerResp<>();
		try {
			return resp.success(staffService.queryByParam(evt));
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}

	}

	@ServiceAround
	@ApiOperation(value = "密码校验#受理", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "checkUser", method = RequestMethod.POST)
	public ServerResp<Object> checkUser(@RequestBody Message<QueryStaffEvt> message) {
		QueryStaffEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<>();
		try {
			String pwd = Md5Util.MD5(evt.getPassWd());
			evt.setPassWd(pwd);
			resp.success(staffService.checkUser(evt));
			return resp;
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}

	}

	@ServiceAround
	@ApiOperation(value = "添加系统账号#受理", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "doAdd", method = RequestMethod.POST)
	public ServerResp<Object> doAdd(@RequestBody Message<AddStaffEvt> message) {
		AddStaffEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			// 查询下挂组织架构是否被禁用
			QueryStaffGroupEvt queryStaffGroupEvt = new QueryStaffGroupEvt();
			queryStaffGroupEvt.setId(evt.getGroupId());
			int isForbid = staffGroupService.queryGroupIsForbid(queryStaffGroupEvt);
			if (isForbid > 0) {
				return resp.error("上级组织架构已被禁用");
			}
			/** 登录账号查重 */
			QueryStaffEvt queryStaffEvt = new QueryStaffEvt();
			queryStaffEvt.setStaffCode(evt.getStaffCode());
			int cnt = staffService.queryUserIsExist(queryStaffEvt);
			if (cnt > 0) {
				return resp.error("登录账号已存在");
			}
			/** 登录账号查重 */
			QueryStaffEvt queryStaffEvt1 = new QueryStaffEvt();
			queryStaffEvt1.setStaffName(evt.getStaffName());
			int cnt1 = staffService.queryUserIsExist(queryStaffEvt1);
			if (cnt1 > 0) {
				return resp.error("账号名称已存在");
			}
			if (StringUtils.isBlank(evt.getPassWd())) {
				QueryLoginRuleEvt queryLoginRuleEvt = new QueryLoginRuleEvt();
				queryLoginRuleEvt.setApplyType("B");
				LoginRuleInfo loginRuleInfo = loginRuleService.queryDetail(queryLoginRuleEvt);
				if (StringUtils.isNotBlank(loginRuleInfo.getIniPwd())) {
					evt.setPassWd(loginRuleInfo.getIniPwd());
				}
			}
			StaffInfoBean staffInfoBean = staffService.doAdd(evt);
			Long id = staffInfoBean.getId();
			if (id != null) {
				return resp.success("操作成功");
			} else {
				return resp.error(CommonResultCode.addFail.getRespCode(), "添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}
	}

	@ServiceAround
	@ApiOperation(value = "修改系统账号#受理", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "doEdit", method = RequestMethod.POST)
	public ServerResp<Object> doEdit(@RequestBody Message<EditStaffEvt> message) {
		EditStaffEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			/** 登录账号查重 */
			if (StringUtils.isNotBlank(evt.getStaffCode())) {
				QueryStaffEvt queryStaffEvt = new QueryStaffEvt();
				queryStaffEvt.setId(evt.getId());
				queryStaffEvt.setStaffCode(evt.getStaffCode());
				if (staffService.queryUserIsExist(queryStaffEvt) > 0) {
					return resp.error("登录账号已存在");
				}
			}
			/** 用户名称查重 */
			if (StringUtils.isNotBlank(evt.getStaffName())) {
				QueryStaffEvt queryStaffEvt1 = new QueryStaffEvt();
				queryStaffEvt1.setId(evt.getId());
				queryStaffEvt1.setStaffName(evt.getStaffName());
				if (staffService.queryUserIsExist(queryStaffEvt1) > 0) {
					return resp.error("账号名称已存在");
				}
			}
			int n = staffService.doEdit(evt);
			if (n >= 1) {
				return resp.success("操作成功");
			} else {
				return resp.error(CommonResultCode.editFail.getRespCode(), "修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}
	}

	@ServiceAround
	@ApiOperation(value = "删除系统账号#受理", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "doDelete", method = RequestMethod.POST)
	public ServerResp<Object> doDelete(@RequestBody Message<DeleteStaffEvt> message) {
		DeleteStaffEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			// 删除账号修改状态为D
			return resp.success(staffService.doDelete(evt));
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}
	}

	@ServiceAround
	@ApiOperation(value = "根据账号查询角色列表#受理", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "queryRoleByStaff", method = RequestMethod.POST)
	public ServerResp<Object> queryRoleByStaff(@RequestBody Message<QueryStaffRoleEvt> message) {
		QueryStaffRoleEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			resp.success(staffService.queryRoleByStaff(evt));
			return resp;
		} catch (Exception e) {
			e.printStackTrace();
			return resp.error();
		}
	}

	@ServiceAround
	@ApiOperation(value = "添加账号角色关联关系#受理", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "setStaffRole", method = RequestMethod.POST)
	public ServerResp<Object> setStaffRole(@RequestBody Message<AddStaffRoleEvt> message) {
		AddStaffRoleEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			DeleteStaffRoleEvt deleteStaffRoleEvt = new DeleteStaffRoleEvt();
			deleteStaffRoleEvt.setStaffId(evt.getStaffId());
			staffService.deleteStaffRole(deleteStaffRoleEvt);
			int cnt = 0;
			Long[] roleIds = evt.getIds();
			// 多个角色Id
			for (int i = 0; i < roleIds.length; i++) {
				AddStaffRoleEvt temp = new AddStaffRoleEvt();
				temp.setRoleId(roleIds[i]);
				temp.setStaffId(evt.getStaffId());
				temp.setOperator(evt.getOperator());
				temp.setStatus(evt.getStatus());
				temp.setUpdateTime(new Date());
				temp.setCreateTime(new Date());
				temp.setIsGrant(0);
				// 如果该角色设置可以被授权
				Long[] grandIds = evt.getGrantIds();
				if (grandIds != null) {
					for (int j = 0; j < grandIds.length; j++) {
						System.out.println(roleIds[i] + "," + grandIds[j]);
						if (roleIds[i].equals(grandIds[j])) {
							temp.setIsGrant(1);
							break;
						}
					}
				}
				staffService.setStaffRole(temp);
				cnt++;
			}
			return resp.success(cnt);
		} catch (Exception e) {
			e.printStackTrace();
			return resp.error("操作失败");
		}
	}

	/**
	 * 非用户中心 传入peratorId信息 返回operatorName信息
	 */
	@ServiceAround
	@ApiOperation(value = "查询用户信息公共方法#受理", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "queryUserInfo", method = RequestMethod.POST)
	public ServerResp<Object> queryUserInfo(@RequestBody Message<QueryStaffEvt> message) {
		QueryStaffEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<>();
		Long[] ids = evt.getIds();
		String operatorNames[] = new String[ids.length];
		for (int i = 0; i < ids.length; i++) {
			evt.setId(ids[i]);
			String staffName = staffService.queryUserInfo(evt);
			operatorNames[i] = staffName;
		}
		return resp.success(operatorNames);
	}

	/**
	 * 查询用户所有数据权限 用户数据权限获取服务
	 */
	@ServiceAround
	@ApiOperation(value = "用户数据权限获取服务#受理", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "getUserDataAuths", method = RequestMethod.POST)
	public ServerResp<Object> getUserDataAuths(@RequestBody Message<QueryStaffEvt> message) {
		ServerResp<Object> resp = new ServerResp<>();
		QueryStaffEvt evt = message.getBody();
		try {
			if (evt.getId() == null) {
				return resp.error("请传入用户信息(Id)");
			}
			return resp.success(staffService.getUserDataAuths(evt));
		} catch (Exception e) {
			e.printStackTrace();
			return resp.error("用户数据权限获取服务");
		}

	}

	@ServiceAround
	@ApiOperation(value = "通过id查扩展信息#查询", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "queryExtendInfo", method = RequestMethod.POST)
	public ServerResp<Object> queryExtendInfo(@RequestBody Message<Map<String, String>> message) {
		Map<String, String> params = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		params.put("baseName", "staff");
		try {
			Map<String, Object> extendInfo = null;// staffService.queryExtendInfo(params);
			if (extendInfo != null && !extendInfo.isEmpty()) {
				resp.success(extendInfo);
			} else {
				resp.error("扩展信息不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.error("扩展信息查询异常！");
		}
		return resp;
	}

	@ServiceAround
	@ApiOperation(value = "通过id查用户扩展信息#查询", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "queryStaffExtendInfo", method = RequestMethod.POST)
	public ServerResp<Object> queryStaffExtendInfo(@RequestBody Message<Map<String, String>> message) {
		Map<String, String> params = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		StaffExtendInfoBean staffExtendInfoBean = new StaffExtendInfoBean();
		staffExtendInfoBean.setStaffId(Long.parseLong(params.get("baseId")));
		List<StaffExtendInfoBean> list = staffExtendInfoService.queryStaffExtendInfoByParam(staffExtendInfoBean);
		if (list.size() > 0) {
			return resp.success(list.get(0));
		}
		return resp.error("扩展信息不存在");
	}

	@ServiceAround
	@ApiOperation(value = "通过账号查询绑定号码#查询", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "queryStaffInfoByCode", method = RequestMethod.POST)
	public ServerResp<Object> queryStaffInfoByCode(@RequestBody Message<QueryStaffEvt> message) {
		QueryStaffEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<>();
		try {
			return resp.success(staffService.queryStaffInfoByCode(evt));
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}

	}

	@ServiceAround
	@ApiOperation(value = "查询用户组下角色固定的用户信息", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "queryStaffInfoByGroupAndRole", method = RequestMethod.POST)
	public ServerResp<Object> queryStaffInfoByGroupAndRole(@RequestBody Message<QueryStaffEvt> message) {
		QueryStaffEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<>();
		try {
			if (evt.getGroupId() != null && evt.getRoleId() != null) {
				return resp.success(staffService.queryStaffByGroupAndRole(evt));
			} else {
				return resp.error("参数缺失");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}

	}

	@ServiceAround
	@ApiOperation(value = "查询用户信息(是否属于特定用户组或特定角色)", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "queryStaffInfoByGroupOrRole", method = RequestMethod.POST)
	public ServerResp<Object> queryStaffInfoByGroupOrRole(@RequestBody Message<QueryStaffEvt> message) {
		QueryStaffEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<>();
		try {
			if (evt.getId() == null
					|| (StringUtils.isBlank(evt.getGroupIds()) && StringUtils.isBlank(evt.getRoleIds()))) {
				return resp.error(-2, "参数缺失");
			}
			List<StaffInfo> staffInfos = staffService.queryStaffInfoByGroupOrRole(evt);
			if (staffInfos != null && staffInfos.size() >= 1) {
				return resp.success(0, "该用户属于传入的用户组(角色)");
			} else {
				return resp.error(-3, "该用户不属于传入的用户组(角色)");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}

	}

	@ServiceAround
	@ApiOperation(value = "判断是否需要锁定用户", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "doCheckLoginLock", method = RequestMethod.POST)
	public ServerResp<Object> doCheckLoginLock(@RequestBody Message<QueryLoginLockEvt> message) {
		QueryLoginLockEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<>();
		try {
			staffService.doAddLoginLockInfo(evt);
			return resp.success("操作完成");
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}
	}

	@ServiceAround
	@ApiOperation(value = "查询锁定日志", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "queryLoginLockInfo", method = RequestMethod.POST)
	public ServerResp<Object> queryLoginLockInfo(@RequestBody Message<QueryLoginLockEvt> message) {
		QueryLoginLockEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<>();
		try {
			return resp.success(staffService.queryLoginLockInfo(evt));
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}
	}

	@ServiceAround
	@ApiOperation(value = "查询所有系统账号信息#查询", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "queryAllByParam", method = RequestMethod.POST)
	public ServerResp<Object> queryAllByParam(@RequestBody Message<QueryStaffEvt> message) {
		QueryStaffEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<>();
		try {
			if ("order".equals(evt.getType())) {
				Map<String, Object> returnMap = new HashedMap();
				List<Map<String, Object>> nodes = new ArrayList<>();
				List<StaffInfo> staffInfoList = staffService.queryAllByParam(evt);
				for (StaffInfo staffInfo : staffInfoList) {
					Long id = staffInfo.getId();
					String name = staffInfo.getStaffName();
					Map<String, Object> map = new HashedMap();
					map.put("node", id);
					map.put("name", name);
					map.put("parent", 0);
					map.put("is_selected", 0);
					nodes.add(map);
				}
				returnMap.put("tree_type", "checkbox");
				returnMap.put("nodes", nodes);
				return resp.success(returnMap);
			} else {
				return resp.success(staffService.queryAllByParam(evt));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}
	}

	@ServiceAround
	@ApiOperation(value = "密码重置#受理", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "resetPwd", method = RequestMethod.POST)
	public ServerResp<Object> resetPwd(@RequestBody Message<EditStaffPwdEvt> message) {
		EditStaffPwdEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			resp = staffService.resetPwd(evt);
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}
		return resp;
	}

	@ServiceAround
	@ApiOperation(value = "添加系统账号并返回#受理", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "doAddAndReturn", method = RequestMethod.POST)
	public ServerResp<Object> doAddAndReturn(@RequestBody Message<AddStaffEvt> message) {
		AddStaffEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			/** 登录账号查重 */
			QueryStaffEvt queryStaffEvt = new QueryStaffEvt();
			queryStaffEvt.setStaffCode(evt.getStaffCode());
			int cnt = staffService.queryUserIsExist(queryStaffEvt);
			if (cnt > 0) {
				return resp.error("登录账号已存在");
			}
			/** 登录账号查重 */
			QueryStaffEvt queryStaffEvt1 = new QueryStaffEvt();
			queryStaffEvt1.setStaffName(evt.getStaffName());
			int cnt1 = staffService.queryUserIsExist(queryStaffEvt1);
			if (cnt1 > 0) {
				return resp.error("账号名称已存在");
			}
			StaffInfoBean staffInfoBean = staffService.doAdd(evt);
			if (staffInfoBean != null) {
				return resp.success(staffInfoBean);
			} else {
				return resp.error(CommonResultCode.addFail.getRespCode(), CommonResultCode.addFail.getRespMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}
	}

	@ServiceAround
	@ApiOperation(value = "查询数据用于载入下拉框#查询", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "queryForOption", method = RequestMethod.POST)
	public ServerResp<Object> queryForOption(@RequestBody Message<QueryStaffEvt> message) {
		QueryStaffEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<>();
		try {
			return resp.success(staffService.queryForOption(evt));
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}
	}

	@ServiceAround
	@ApiOperation(value = "查询用户角色#查询", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "queryRolesByStaff", method = RequestMethod.POST)
	public ServerResp<Object> queryRolesByStaff(@RequestBody Message<QueryStaffEvt> message) {
		QueryStaffEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<>();
		try {
			List<RoleInfo> list = staffService.queryRolesByStaff(evt);
			return resp.success(list);
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}
	}

	@ServiceAround
	@ApiOperation(value = "查询所有系统账号信息根据国家编码#查询", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "queryAllByRegion", method = RequestMethod.POST)
	public ServerResp<Object> queryAllByRegion(@RequestBody Message<QueryStaffEvt> message) {
		QueryStaffEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<>();
		try {
			if ("order".equals(evt.getType())) {
				Map<String, Object> returnMap = new HashedMap();
				List<Map<String, Object>> nodes = new ArrayList<>();
				List<StaffInfo> staffInfoList = staffService.queryAllByRegion(evt);
				for (StaffInfo staffInfo : staffInfoList) {
					Long id = staffInfo.getId();
					String name = staffInfo.getStaffName();
					Map<String, Object> map = new HashedMap();
					map.put("node", id);
					map.put("name", name);
					map.put("parent", 0);
					map.put("is_selected", 0);
					nodes.add(map);
				}
				returnMap.put("tree_type", "checkbox");
				returnMap.put("nodes", nodes);
				return resp.success(returnMap);
			} else {
				return resp.success(staffService.queryAllByRegion(evt));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}
	}

	@ServiceAround
	@ApiOperation(value = "查询所有系统账号信息根据环节权限#查询", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "queryByTaskpms", method = RequestMethod.POST)
	public ServerResp<Object> queryByTaskpms(@RequestBody Message<QueryStaffTaskEvt> message) {
		QueryStaffTaskEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<>();
		try {
			Map<String, Object> taskInfoStaff = staffService.queryByTaskpms(evt);
			resp.success(taskInfoStaff);

		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}
		return resp;
	}

}
