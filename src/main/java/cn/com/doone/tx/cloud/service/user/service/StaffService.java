package cn.com.doone.tx.cloud.service.user.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.doone.tx.cloud.service.user.bean.GroupStaffBean;
import cn.com.doone.tx.cloud.service.user.bean.LoginLockBean;
import cn.com.doone.tx.cloud.service.user.bean.StaffExtendInfoBean;
import cn.com.doone.tx.cloud.service.user.bean.StaffInfoBean;
import cn.com.doone.tx.cloud.service.user.bean.StaffRegionBean;
import cn.com.doone.tx.cloud.service.user.bean.StaffRoleBean;
import cn.com.doone.tx.cloud.service.user.dao.mapper.StaffExtendInfoMapper;
import cn.com.doone.tx.cloud.service.user.dao.mapper.StaffGroupMapper;
import cn.com.doone.tx.cloud.service.user.dao.mapper.StaffMapper;
import cn.com.doone.tx.cloud.service.user.dao.mapper.StaffRegionMapper;
import cn.com.doone.tx.cloud.service.user.evt.StaffRole.AddStaffRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.StaffRole.DeleteStaffRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.StaffRole.QueryStaffRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.groupStaff.AddGroupStaffEvt;
import cn.com.doone.tx.cloud.service.user.evt.loginlock.AddLoginLockEvt;
import cn.com.doone.tx.cloud.service.user.evt.loginlock.QueryLoginLockEvt;
import cn.com.doone.tx.cloud.service.user.evt.loginlog.QueryLoginLogEvt;
import cn.com.doone.tx.cloud.service.user.evt.loginrule.QueryLoginRuleEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.AddStaffEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.DeleteStaffEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.EditStaffEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.EditStaffPwdEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.QueryStaffEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.QueryStaffTaskEvt;
import cn.com.doone.tx.cloud.service.user.evt.staffGroup.QueryStaffGroupEvt;
import cn.com.doone.tx.cloud.service.user.evt.staffRegion.EditStaffRegionEvt;
import cn.com.doone.tx.cloud.service.user.info.DataAuthsInfo;
import cn.com.doone.tx.cloud.service.user.info.LoginLockInfo;
import cn.com.doone.tx.cloud.service.user.info.LoginLogInfo;
import cn.com.doone.tx.cloud.service.user.info.LoginRuleInfo;
import cn.com.doone.tx.cloud.service.user.info.RoleInfo;
import cn.com.doone.tx.cloud.service.user.info.StaffInfo;
import cn.com.doone.tx.cloud.service.user.info.StaffTaskInfo;
import cn.com.doone.tx.cloud.tool.common.data.SysParmsDataService;
import cn.com.doone.tx.cloud.tool.common.enums.CommonResultCode;
import cn.com.doone.tx.cloud.tool.common.enums.NormalStatus;
import cn.com.doone.tx.cloud.tool.common.invoke.BaseService;
import cn.com.doone.tx.cloud.tool.common.invoke.RestUtil;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.common.util.CapsLookUtil;
import cn.com.doone.tx.cloud.tool.common.util.DictLangUtil;
import cn.com.doone.tx.cloud.tool.common.util.Md5Util;
import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatRepository;

@Service
// @Transactional
public class StaffService extends BaseService {

	@Autowired
	private StaffMapper staffMapper;

	// @Autowired
	// private ExtendInfoMapper extendInfoMapper;

	@Autowired
	private StaffGroupMapper staffGroupMapper;

	@Autowired
	private PlatRepository platRepository;

	@Autowired
	private StaffExtendInfoMapper staffExtendInfoMapper;

	@Autowired
	private RestUtil serviceRestUtil;

	// --注入系统参数获取服务--
	@Autowired
	private SysParmsDataService sysParmsDataService;

	@Autowired
	private LoginLogService loginLogService;

	@Autowired
	private LoginRuleService loginRuleService;

	// @Autowired
	// private LoginUtil loginUtil;

	@Autowired
	private DictLangUtil dictLangUtil;

	@Autowired
	private StaffRegionMapper staffRegionMapper;

	// @Cacheable(value = "StaffService.queryCount",keyGenerator =
	// "defaultKeyGenerator")
	public int queryCount(QueryStaffEvt queryStaffEvt) {
		return staffMapper.queryCount(queryStaffEvt);
	}

	// @Cacheable(value = "StaffService.queryByParam",keyGenerator =
	// "defaultKeyGenerator")
	public List<StaffInfo> queryByParam(QueryStaffEvt queryStaffEvt) {
		List<StaffInfo> staffInfos;
		Map<String, Object> extendMap = queryStaffEvt.getExtendMap();
		String languageType = queryStaffEvt.getLanguageType();
		if (extendMap != null && !extendMap.isEmpty()) { // 扩展查询条件
			String sqlWhere = "";
			for (Map.Entry<String, Object> entry : extendMap.entrySet()) {
				// 扩展字段
				Map<String, Object> params = new HashMap<>();
				params.put("tableName", "ts_staff_extend_info");
				params.put("fieldCode", entry.getKey());
				ServerResp resp = serviceRestUtil.post(COMMON_SERVICE, "config/extend/queryExtendFieldByParam", params);
				if (resp.isSuccess()) {
					// 取扩展字段
					List<Map<String, String>> extendFileds = (List<Map<String, String>>) resp.getBody();
					if (extendFileds.size() > 0 && extendFileds.size() == 1) {
						Map<String, String> extendFiled = extendFileds.get(0);
						if (entry.getValue() != null) {
							sqlWhere += " and t2." + extendFiled.get("fieldName") + "='" + entry.getValue() + "' ";
						}
					}
				}
			}
			System.out.println("sqlWhere:" + sqlWhere);
			if (StringUtils.isBlank(sqlWhere)) {
				return null;
			} else {
				staffInfos = staffMapper.queryByExtendParam(sqlWhere);
				/** 获取用户所绑定的角色列表 */
				for (StaffInfo temp : staffInfos) {
					QueryStaffRoleEvt queryStaffRoleEvt = new QueryStaffRoleEvt();
					queryStaffRoleEvt.setStaffId(temp.getId());
					queryStaffRoleEvt.setLanguageType(languageType);
					List<RoleInfo> roleInfos = queryRoleByStaff(queryStaffRoleEvt);
					temp.setRoleInfoList(roleInfos);
				}
			}
		} else {
			staffInfos = staffMapper.queryByParam(queryStaffEvt);
			/** 获取用户所绑定的角色列表 */
			for (StaffInfo temp : staffInfos) {
				QueryStaffRoleEvt queryStaffRoleEvt = new QueryStaffRoleEvt();
				queryStaffRoleEvt.setStaffId(temp.getId());
				queryStaffRoleEvt.setLanguageType(languageType);
				List<RoleInfo> roleInfos = queryRoleByStaff(queryStaffRoleEvt);
				temp.setRoleInfoList(roleInfos);
			}
		}
		return staffInfos;
	}

	public List<StaffInfo> queryAllByParam(QueryStaffEvt queryStaffEvt) {
		return staffMapper.queryByParam(queryStaffEvt);
	}

	// 根据国家编码，删选用户
	public List<StaffInfo> queryAllByRegion(QueryStaffEvt queryStaffEvt) {
		return staffMapper.queryByRegion(queryStaffEvt);
	}

	// 根据环节编码，删选用户
	public Map<String, Object> queryByTaskpms(QueryStaffTaskEvt queryStaffEvt) {
		Map<String, Object> taskMap = new HashMap<String, Object>();
		Map<String, Object> staffParamMap = new HashMap<String, Object>();
		Map<String, Object> taskInfoStaffMap = new HashMap<String, Object>();
		;
		Map<String, Object> staffMap = null;
		new HashMap<String, Object>();
		List<StaffTaskInfo> staffList = null;
		List<Map<String, Object>> taskPms = queryStaffEvt.getTaskPms();
		QueryStaffEvt evtQuery = null;
		String taskCode = "";
		String pmsType = "";
		if (taskPms != null && taskPms.size() > 0) {
			Iterator<Map<String, Object>> iterator = taskPms.iterator();
			while (iterator.hasNext()) {

				Map<String, Object> info = iterator.next();
				Object ob = info.get("pmsId");
				Integer ida = (Integer) Integer.valueOf(ob.toString());
				Long id = Long.valueOf(ida);
				taskCode = (String) info.get("taskCode");
				pmsType = (String) info.get("pmsType");
				evtQuery = new QueryStaffEvt();

				if ("1".equals(pmsType)) {
					// 用戶Id
					evtQuery.setId(id);
				} else if ("2".equals(pmsType)) {
					// 用戶组Id
					evtQuery.setGroupId(id);
				} else if ("3".equals(pmsType)) {
					// 角色Id
					evtQuery.setRoleId(id);
				}
				// 把类型和id组合为key,put 数据
				if (!taskInfoStaffMap.isEmpty()) {
					if (taskInfoStaffMap.containsKey(pmsType + "-" + id)) {
						// 对于存在的环节，不需要重新查询

					} else {
						// 对于bu存在存在的环节，把数据插入进去
						// 重新查询

						// 处理用户，
						staffList = staffMapper.queryByTaskpms(evtQuery);

						taskInfoStaffMap.put(pmsType + "-" + id, staffList);
					}

				} else {
					// 处理用户，
					staffList = staffMapper.queryByTaskpms(evtQuery);

					taskInfoStaffMap.put(pmsType + "-" + id, staffList);
				}

			}

		}
		if (taskPms != null && taskPms.size() > 0) {

			for (Map<String, Object> info : taskPms) {
				Object ob = info.get("pmsId");
				Integer ida = (Integer) Integer.valueOf(ob.toString());
				Long id = Long.valueOf(ida);
				taskCode = (String) info.get("taskCode");
				pmsType = (String) info.get("pmsType");
				List<StaffTaskInfo> staffInfo = null;
				if (!taskInfoStaffMap.isEmpty()) {
					staffMap = new HashMap<String, Object>();
					if (taskInfoStaffMap.containsKey(pmsType + "-" + id)) {
						staffList = (List<StaffTaskInfo>) taskInfoStaffMap.get(pmsType + "-" + id);
						String countryCode = "";
						if (staffList != null && staffList.size() > 0) {

							for (StaffTaskInfo staInfo : staffList) {
								staffInfo = new ArrayList<StaffTaskInfo>();
								countryCode = staInfo.getCountryCode();
								// key允许有null,但是序列化不允许，设置为NO
								if (countryCode != "" && countryCode != null) {
									if (!staffMap.isEmpty()) {
										// 对于存在的，把数据更新进去
										if (staffMap.containsKey(countryCode)) {
											staffInfo = (List<StaffTaskInfo>) staffMap.get(countryCode);
											staffInfo.add(staInfo);
											staffMap.put(countryCode, staffInfo);
										} else {
											// 对于bu存在的国家编码，把数据插入进去
											staffInfo.add(staInfo);
											staffMap.put(countryCode, staffInfo);
										}

									} else {
										// 对于bu存在的，把数据插入进去
										staffInfo.add(staInfo);
										staffMap.put(countryCode, staffInfo);
									}

								} else {
									if (!staffMap.isEmpty()) {
										// 对于存在的，把数据更新进去
										if (staffMap.containsKey("NO")) {
											staffInfo = (List<StaffTaskInfo>) staffMap.get("NO");
											staffInfo.add(staInfo);
											staffMap.put("NO", staffInfo);
										} else {
											// 对于bu存在的国家编码，把数据插入进去
											staffInfo.add(staInfo);
											staffMap.put("NO", staffInfo);
										}

									} else {
										// 对于bu存在的，把数据插入进去
										staffInfo.add(staInfo);
										staffMap.put("NO", staffInfo);
									}

								}
							}
						}
						staffParamMap.put(taskCode, staffMap);
					}

				}
			}

		}

		// 根据taskCoded的pmsId和用户Id

		return staffParamMap;
	}

	/** 登录时查询用户信息 */
	public List<StaffInfo> queryStaffInfo(QueryStaffEvt queryStaffEvt) {
		queryStaffEvt.startPage();
		List<StaffInfo> staffInfos = staffMapper.queryStaffInfo(queryStaffEvt);
		// 获取用户所绑定的角色列表
		for (StaffInfo temp : staffInfos) {
			QueryStaffRoleEvt queryStaffRoleEvt = new QueryStaffRoleEvt();
			queryStaffRoleEvt.setStaffId(temp.getId());
			List<RoleInfo> roleInfos = queryRoleByStaff(queryStaffRoleEvt);
			temp.setRoleInfoList(roleInfos);
		}
		return staffInfos;
	}

	/** 用于修改密码时原密码校验 */
	public int checkUser(QueryStaffEvt queryStaffEvt) {
		List<StaffInfo> staffInfos = staffMapper.checkUser(queryStaffEvt);
		int i = staffInfos.size();
		return i;
	}

	// public int bus(){
	// DeleteStaffExtendEvt deleteStaffExtendEvt = new DeleteStaffExtendEvt();
	// deleteStaffExtendEvt.setStaffId(122L);
	// int cnt = staffExtendInfoMapper.doDeleteFromExtend(deleteStaffExtendEvt);
	// StaffExtendInfoBean staffExtendInfoBean = new StaffExtendInfoBean();
	// staffExtendInfoBean.setStaffId(4L);//用户id
	// staffExtendInfoBean.setUpdateTime(new Date());
	// staffExtendInfoBean.setCreateTime(new Date());
	// staffExtendInfoBean.setOperator(4L);
	// staffExtendInfoBean.setField1("ewfwef");
	// staffExtendInfoBean.setStatus(NormalStatus.E.name());
	// StaffExtendInfoBean staffExtendInfoBean1 =
	// platRepository.save(staffExtendInfoBean);
	// return 4;
	// }

	/***
	 * 新增用户信息入库
	 */
	@Transactional
	public StaffInfoBean doAdd(AddStaffEvt evt) {
		String password = evt.getPassWd();
		if (StringUtils.isNotBlank(password)) {
			evt.setPassWd(Md5Util.MD5(password));
		}
		// else{
		// //初始密码1a2b3c
		// evt.setPassWd(Md5Util.MD5("1a2b3c"));
		// }
		evt.setCreateTime(new Date());
		evt.setUpdateTime(new Date());
		StaffInfoBean bean = new StaffInfoBean();
		BeanUtils.copyProperties(evt, bean);
		bean.setCreator(evt.getOperator());
		/** 用户主表 */
		bean = platRepository.save(bean);
		/** 用户id */
		Long staffId = bean.getId();
		/** 插入用户、用户组关联表 */
		Long groupId = evt.getGroupId();
		Long operator = evt.getOperator();
		AddGroupStaffEvt addGroupStaffEvt = new AddGroupStaffEvt();
		addGroupStaffEvt.setStatus("E");
		addGroupStaffEvt.setCreateTime(new Date());
		addGroupStaffEvt.setGroupId(groupId);
		addGroupStaffEvt.setOperator(operator);
		addGroupStaffEvt.setStaffId(staffId);
		GroupStaffBean groupStaffBean = new GroupStaffBean();
		BeanUtils.copyProperties(addGroupStaffEvt, groupStaffBean);
		groupStaffBean.setCreator(evt.getOperator());
		platRepository.save(groupStaffBean);
		// 插入用户扩展表
		// if(evt.getExtendParam()!=null) {
		// evt.getExtendParam().put("baseId", bean.getId().toString());
		// evt.getExtendParam().put("tableName", "ts_staff_extend_info");
		// evt.getExtendParam().put("baseName", "staff");
		// evt.getExtendParam().put("operator", evt.getOperator().toString());
		// extendInfoMapper.add(evt.getExtendParam());
		// }
		/** 入用户扩展信息 */
		Map<String, String> extendMap = evt.getExtendParam();
		if (extendMap != null && !extendMap.isEmpty()) {
			saveStaffExtends(extendMap, staffId, operator);
		}

		// 插入用户区域权限表
		String[] regionCodes = evt.getRegionCodes();
		if (regionCodes != null && regionCodes.length > 0) {
			for (int j = 0; j < regionCodes.length; j++) {
				if (StringUtils.isNotBlank(regionCodes[j])) {
					StaffRegionBean staffRegionBean = new StaffRegionBean();
					staffRegionBean.setStaffId(bean.getId());
					staffRegionBean.setOperator(operator);
					staffRegionBean.setCreator(operator);
					staffRegionBean.setCountryCode(Long.valueOf(regionCodes[j]));
					platRepository.save(staffRegionBean);
				}
			}
		}
		return bean;
	}

	public int doEdit(EditStaffEvt evt) {
		String password = evt.getPassWd();
		if (StringUtils.isNotBlank(password)) {
			evt.setPassWd(Md5Util.MD5(password));
		}
		evt.setUpdateTime(new Date());
		int n = staffMapper.doEdit(evt);
		// 修改用户扩展表
		// if(evt.getExtendParam()!=null) {
		// evt.getExtendParam().put("baseId", evt.getId().toString());
		// evt.getExtendParam().put("tableName", "ts_staff_extend_info");
		// evt.getExtendParam().put("baseName", "staff");
		// evt.getExtendParam().put("operator", evt.getOperator().toString());
		// extendInfoMapper.edit(evt.getExtendParam());
		// }
		/** 2、扩展信息 */
		Map<String, String> extendMap = evt.getExtendParam();
		if (extendMap != null && !extendMap.isEmpty()) {
			/** 先删除 扩展表状态置D */
			// DeleteStaffExtendEvt deleteStaffExtendEvt = new DeleteStaffExtendEvt();
			// deleteStaffExtendEvt.setStaffId(evt.getId());
			// int cnt = staffExtendInfoMapper.doDeleteFromExtend(deleteStaffExtendEvt);
			// /**保存*/
			// saveStaffExtends(extendMap,evt.getId(),evt.getOperator());
		}

		// 修改区域权限
		String[] regionCodes = evt.getRegionCodes();
		String[] oldRegionCodes = evt.getOldRegionCodes();
		if (regionCodes != null && regionCodes.length > 0) {
			if (oldRegionCodes != null && oldRegionCodes.length > 0) {
				for (int i = 0; i < regionCodes.length; i++) {
					if (StringUtils.isNotBlank(regionCodes[i])) {
						if (Arrays.asList(oldRegionCodes).contains(regionCodes[i]) == false) {
							StaffRegionBean staffRegionBean = new StaffRegionBean();
							staffRegionBean.setStaffId(evt.getId());
							staffRegionBean.setOperator(evt.getOperator());
							staffRegionBean.setCreator(evt.getOperator());
							staffRegionBean.setCountryCode(Long.valueOf(regionCodes[i]));
							platRepository.save(staffRegionBean);
						}
					}
				}
				for (int i = 0; i < oldRegionCodes.length; i++) {
					if (StringUtils.isNotBlank(oldRegionCodes[i])) {
						if (Arrays.asList(regionCodes).contains(oldRegionCodes[i]) == false) {
							EditStaffRegionEvt editStaffRegionEvt = new EditStaffRegionEvt();
							editStaffRegionEvt.setStaffId(evt.getId());
							editStaffRegionEvt.setCountryCode(Long.valueOf(oldRegionCodes[i]));
							editStaffRegionEvt.setStatus("D");
							staffRegionMapper.doOut(editStaffRegionEvt);
						}
					}
				}
			} else {
				for (int i = 0; i < regionCodes.length; i++) {
					if (StringUtils.isNotBlank(regionCodes[i])) {
						StaffRegionBean staffRegionBean = new StaffRegionBean();
						staffRegionBean.setStaffId(evt.getId());
						staffRegionBean.setOperator(evt.getOperator());
						staffRegionBean.setCreator(evt.getOperator());
						staffRegionBean.setCountryCode(Long.valueOf(regionCodes[i]));
						platRepository.save(staffRegionBean);
					}
				}
			}
		} else {
			if (oldRegionCodes != null && oldRegionCodes.length > 0) {
				for (int i = 0; i < oldRegionCodes.length; i++) {
					if (StringUtils.isNotBlank(regionCodes[i])) {
						EditStaffRegionEvt editStaffRegionEvt = new EditStaffRegionEvt();
						editStaffRegionEvt.setStaffId(evt.getId());
						editStaffRegionEvt.setCountryCode(Long.valueOf(oldRegionCodes[i]));
						editStaffRegionEvt.setStatus("D");
						staffRegionMapper.doOut(editStaffRegionEvt);
					}
				}
			}
		}
		return n;
	}

	/*** 保存用户扩展信息 */
	private Long saveStaffExtends(Map<String, String> extendMap, Long staffId, Long operator) {
		StaffExtendInfoBean staffExtendInfoBean = new StaffExtendInfoBean();
		staffExtendInfoBean.setStaffId(staffId);// 用户id
		staffExtendInfoBean.setUpdateTime(new Date());
		staffExtendInfoBean.setCreateTime(new Date());
		staffExtendInfoBean.setOperator(operator);
		staffExtendInfoBean.setStatus(NormalStatus.E.name());
		Class<?> beanClass = StaffExtendInfoBean.class;
		for (String fieldName : extendMap.keySet()) {
			String value = extendMap.get(fieldName);
			fieldName = StringUtils.replace(fieldName, "_", "");
			fieldName = CapsLookUtil.toUpperCaseFirstOne(fieldName.toLowerCase());
			try {
				Method method = beanClass.getMethod("set" + fieldName, String.class);
				method.invoke(staffExtendInfoBean, value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		StaffExtendInfoBean staffExtendInfoBean1 = platRepository.save(staffExtendInfoBean);
		return staffExtendInfoBean1.getId();
	}

	public int doDelete(DeleteStaffEvt evt) {
		GroupStaffBean groupStaffBean = new GroupStaffBean();
		groupStaffBean.setStaffId(evt.getId());
		// 删除用户、用户组关联表
		int cnt1 = staffMapper.doDeleteStaffGroup(evt.getId());
		StaffRoleBean staffRoleBean = new StaffRoleBean();
		staffRoleBean.setStaffId(evt.getId());
		// 删除用户、角色关联表
		DeleteStaffRoleEvt deleteStaffRoleEvt = new DeleteStaffRoleEvt();
		deleteStaffRoleEvt.setStaffId(evt.getId());
		int cnt2 = staffMapper.deleteStaffRole(deleteStaffRoleEvt);
		// 删除用户扩展表
		Map<String, String> deExtendMap = new HashMap<>();
		deExtendMap.put("baseId", evt.getId().toString());
		deExtendMap.put("tableName", "ts_staff_extend_info");
		deExtendMap.put("baseName", "staff");
		// int cnt3 = extendInfoMapper.delete(deExtendMap);
		// 删除用户(修改状态为D)
		evt.setStatus(NormalStatus.D.name());
		int cnt4 = staffMapper.doDelete(evt);
		return cnt4;
	}

	public int queryUserIsExist(QueryStaffEvt evt) {
		return staffMapper.queryUserIsExist(evt);
	}

	/** 根据用户查出所有角色 */
	public List<RoleInfo> queryRolesByStaff(QueryStaffEvt queryStaffEvt) {
		return staffMapper.queryRolesByStaff(queryStaffEvt);
	}

	/** 用户管理中的角色分配 */
	public List<RoleInfo> queryRoleByStaff(QueryStaffRoleEvt evt) {
		// 1、用户可分配的角色（所创建的角色及其可授权的角色总和）
		if (null != evt.getIsGrant() && evt.getIsGrant() == 1) {
			List<RoleInfo> isGrandRoles = staffMapper.queryRoleByGrand(evt);
			List<RoleInfo> selfCreateRoles = staffMapper.queryRoleSelfCreate(evt);
			List<RoleInfo> LastRoleInfoList = new ArrayList<>();
			// 去重 SET 对象要重写equals 和hashcode,set 本事无序
			// selfCreateRoles.addAll(isGrandRoles);
			// Set<RoleInfo> ts = new HashSet<RoleInfo>();
			// for (RoleInfo roleInfo:selfCreateRoles) {
			// if(ts.add(roleInfo)){
			// LastRoleInfoList.add(roleInfo);
			// }
			// }
			LastRoleInfoList = isGrandRoles;
			for (RoleInfo r1 : selfCreateRoles) {
				RoleInfo roleInfo = r1;
				Boolean flag = false;
				for (RoleInfo r2 : isGrandRoles) {
					if (r1.getId() == r2.getId()) {
						flag = true;
						break;
					}
				}
				if (!flag) {
					LastRoleInfoList.add(roleInfo);
				}
			}
			return LastRoleInfoList;
		} else {
			// 2、查询该用户已绑定的角色
			return staffMapper.queryRoleByStaff(evt);
		}

	}

	public StaffRoleBean setStaffRole(AddStaffRoleEvt evt) {
		StaffRoleBean bean = new StaffRoleBean();
		BeanUtils.copyProperties(evt, bean);
		bean.setCreator(evt.getOperator());
		bean = platRepository.save(bean);
		return bean;
	}

	public int deleteStaffRole(DeleteStaffRoleEvt evt) {
		return staffMapper.deleteStaffRole(evt);
	}
	/** 查询用户扩展信息 */
	// public Map<String,Object> queryExtendInfo(Map<String,String> params){
	// Map<String,Object> extendInfo= extendInfoMapper.queryExtendInfo(params);
	// return extendInfo;
	// }

	/**
	 * 非用户中心 传入peratorId信息 返回operatorName信息
	 */
	public String queryUserInfo(QueryStaffEvt evt) {
		return staffMapper.queryUserInfo(evt);
	}

	/**
	 * 查询用户所有数据权限 1、先查出该用户的所有角色(含个人角色、其所在用户组角色，角色不重复记录) 2、根据角色集 查出所有数据权限集(用户组集)
	 * 3、将此数据权限集所有用户组作为根，查出所有下属用户组 用户组信息数据不重复记录
	 */

	public List<DataAuthsInfo> getUserDataAuths(QueryStaffEvt evt) {
		// 角色集->角色绑定用户组集
		QueryStaffRoleEvt queryStaffRoleEvt = new QueryStaffRoleEvt();
		queryStaffRoleEvt.setStaffId(evt.getId());
		List<DataAuthsInfo> dataAuthsInfoList = staffMapper.queryDataAuthsInfoByStaff(queryStaffRoleEvt);
		// 取所有groupId做parentId 递归查询
		List<DataAuthsInfo> TotaldataAuthsInfoList = new ArrayList<>();
		for (DataAuthsInfo dataAuthsInfo : dataAuthsInfoList) {
			Long groupId = dataAuthsInfo.getId();
			QueryStaffGroupEvt evt1 = new QueryStaffGroupEvt();
			evt1.setParentId(groupId);
			List<DataAuthsInfo> list = new ArrayList<>();
			list = getStaffGroupsByParentId(list, evt1);
			TotaldataAuthsInfoList.addAll(list);
		}
		// System.out.println(TotaldataAuthsInfoList);
		// System.out.println(TotaldataAuthsInfoList.size());
		// set集合保存的是引用不同地址的对象
		Set<DataAuthsInfo> ts = new HashSet<DataAuthsInfo>();
		ts.addAll(TotaldataAuthsInfoList);
		// System.out.println(ts.size());
		// for (DataAuthsInfo dataAuthsInfo : ts) {
		// System.out.println(dataAuthsInfo.getId()+"-"+dataAuthsInfo.getGroupName());
		// }
		List<DataAuthsInfo> LastdataAuthsInfoList = new ArrayList<DataAuthsInfo>(ts);
		// System.out.println(LastdataAuthsInfoList.size());
		// for (DataAuthsInfo dataAuthsInfo:LastdataAuthsInfoList){
		// System.out.println(dataAuthsInfo.getId()+"-"+dataAuthsInfo.getGroupName());
		// }
		return LastdataAuthsInfoList;
	}

	// 根据parentId做递归查询
	public List<DataAuthsInfo> getStaffGroupsByParentId(List<DataAuthsInfo> list, QueryStaffGroupEvt evt) {
		// 每次都根据parentId查询出对应的list
		// System.out.println("parentId"+evt.getParentId());
		List<DataAuthsInfo> staffGroupInfoList = staffGroupMapper.queryDataAuthsInfoByParent(evt);
		// System.out.println(staffGroupInfoList);
		if (staffGroupInfoList != null) {
			if (staffGroupInfoList.size() > 0) {
				list.addAll(staffGroupInfoList);
				// System.out.println("list:"+list);
				// 遍历所查出的元素，将其id作为parentId继续查询
				for (DataAuthsInfo dataAuthsInfo : staffGroupInfoList) {
					QueryStaffGroupEvt evt1 = new QueryStaffGroupEvt();
					evt1.setParentId(dataAuthsInfo.getId());
					getStaffGroupsByParentId(list, evt1);
				}
			}
		}
		return list;
	}

	/** 通过账号查询绑定号码 */
	public List<StaffInfo> queryStaffInfoByCode(QueryStaffEvt queryStaffEvt) {
		return staffMapper.queryStaffInfoByCode(queryStaffEvt);
	}

	/** 查询用户信息(用户组、角色固定) */
	public List<StaffInfo> queryStaffByGroupAndRole(QueryStaffEvt queryStaffEvt) {
		List<StaffInfo> staffInfos = staffMapper.queryStaffByGroupAndRole(queryStaffEvt);
		/** 获取用户所绑定的角色列表 */
		for (StaffInfo temp : staffInfos) {
			QueryStaffRoleEvt queryStaffRoleEvt = new QueryStaffRoleEvt();
			queryStaffRoleEvt.setStaffId(temp.getId());
			List<RoleInfo> roleInfos = queryRoleByStaff(queryStaffRoleEvt);
			temp.setRoleInfoList(roleInfos);
		}
		return staffInfos;
	}

	/** 查询用户信息(是否属于特定用户组或特定角色 ) */
	public List<StaffInfo> queryStaffInfoByGroupOrRole(QueryStaffEvt queryStaffEvt) {
		List<StaffInfo> staffInfos = staffMapper.queryStaffInfoByGroupOrRole(queryStaffEvt);
		return staffInfos;
	}

	/** 添加锁定日志 */
	public LoginLockBean doAddLoginLockInfo(AddLoginLockEvt evt) {
		QueryLoginRuleEvt queryLoginRuleEvt = new QueryLoginRuleEvt();
		// 适用类型 B:业务系统 A:APP
		if (StringUtils.isNotBlank(evt.getApplyType())) // 默认未B
			queryLoginRuleEvt.setApplyType(evt.getApplyType());
		else
			queryLoginRuleEvt.setApplyType("B");
		LoginRuleInfo loginRuleInfo = loginRuleService.queryDetail(queryLoginRuleEvt);
		String lockTime = "";
		String lockTimeRule = "";
		if (loginRuleInfo != null) {
			if (StringUtils.isNotBlank(loginRuleInfo.getLockTime())) {
				lockTime = loginRuleInfo.getLockTime();
			}
			if (StringUtils.isNotBlank(loginRuleInfo.getLockTimeRule())) {
				lockTimeRule = loginRuleInfo.getLockTimeRule();
			}
		}
		long lockMillis = 0;
		if (StringUtils.isNotBlank(lockTime) && StringUtils.isNotBlank(lockTimeRule)) {
			long lLockTime = Long.valueOf(lockTime);
			switch (lockTimeRule) {
			case "D":
				lockMillis += lLockTime * 24 * 60 * 60 * 1000;
				break;
			case "H":
				lockMillis += lLockTime * 60 * 60 * 1000;
				break;
			case "M":
				lockMillis += lLockTime * 60 * 1000;
				break;
			case "S":
				lockMillis += lLockTime * 1000;
				break;
			}
		}
		Date unlockTime = new Date(evt.getCreateTime().getTime() + lockMillis);
		LoginLockBean bean = new LoginLockBean();
		bean.setAccount(evt.getAccount());
		bean.setAccountType(evt.getAccountType());
		bean.setCreateTime(evt.getCreateTime());
		bean.setUnlockTime(unlockTime);
		bean.setStatus("E");
		// bean.setOperator(-4L);
		bean = platRepository.save(bean);
		return bean;
	}

	/** 查找锁定日志 */
	public List<LoginLockInfo> queryLoginLockInfo(QueryLoginLockEvt evt) {
		// 设置创建日期
		Integer lockTime = evt.getLockMinutes();
		Date createTime = null;
		if ("D".equals(evt.getLockTimeRule())) {
			createTime = DateUtils.addDays(new Date(), -lockTime);
		} else if ("H".equals(evt.getLockTimeRule())) {
			createTime = DateUtils.addHours(new Date(), -lockTime);
		} else if ("M".equals(evt.getLockTimeRule())) {
			createTime = DateUtils.addMinutes(new Date(), -lockTime);
		} else if ("S".equals(evt.getLockTimeRule())) {
			createTime = DateUtils.addSeconds(new Date(), -lockTime);
		}
		// createTime = DateUtils.addMinutes(new Date(), -lockTime);
		evt.setCreateTime(createTime);
		return staffMapper.queryLoginLockInfo(evt);
	}

	public String getSystemParam(String systemKey) {
		try {
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("configKey", systemKey);
			List<String> valueList = sysParmsDataService.getParamValues(param);

			System.out.println("系统参数获取" + systemKey + "返回:" + valueList);
			if (valueList != null && valueList.size() > 0) {
				return valueList.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 账户是否被锁定
	public Map<String, Object> checkLoginLock(QueryLoginLockEvt evt) {
		Map<String, Object> result = new HashMap<>();
		// 是否开启登录次数验证
		// String bOpenloginLock = getSystemParam("loginLock.switch");
		// 登入验证次数
		String loginLockNum = getSystemParam("loginLock.num");
		// 锁定分钟数
		// String loginLockMinutes = getSystemParam("loginLock.minutes");
		String account = evt.getAccount();
		String accountType = evt.getAccountType();
		String applyType = evt.getApplyType();
		// 适用类型 B:业务系统 A:APP
		if (StringUtils.isBlank(applyType)) {
			applyType = "B";
		}
		QueryLoginRuleEvt queryLoginRuleEvt = new QueryLoginRuleEvt();
		queryLoginRuleEvt.setApplyType(applyType);
		// 查规则表获取锁定时长和最大密码输错次数
		LoginRuleInfo loginRuleInfo = loginRuleService.queryDetail(queryLoginRuleEvt);
		String lockTime = "0";
		String lockTimeRule = "";
		if (loginRuleInfo != null) {
			// 获取最大可输错次数
			if (StringUtils.isNotBlank(loginRuleInfo.getMaxError())) {
				loginLockNum = loginRuleInfo.getMaxError();
			}
			// 锁定时间
			if (StringUtils.isNotBlank(loginRuleInfo.getLockTime())) {
				lockTime = loginRuleInfo.getLockTime();
			}
			// 锁定单位
			if (StringUtils.isNotBlank(loginRuleInfo.getLockTimeRule())) {
				lockTimeRule = loginRuleInfo.getLockTimeRule();
			}
		}
		int lockNum = 0;
		try {
			lockNum = Integer.valueOf(loginLockNum);
			List<LoginLockInfo> lockInfoList = null;
			if (lockNum > 0) {// 参数正确
				QueryLoginLockEvt loginLockEvt = new QueryLoginLockEvt();
				loginLockEvt.setAccount(account);
				loginLockEvt.setAccountType(accountType);
				// if (StringUtils.isNotBlank(lockTime)) {
				// loginLockEvt.setLockMinutes(Integer.valueOf(lockTime));
				// }
				// if (StringUtils.isNotBlank(lockTimeRule)) {
				// loginLockEvt.setLockTimeRule(lockTimeRule);
				// }
				// 查此账号是否锁定
				// List<LoginLockInfo> lockInfoList = queryLoginLockInfo(loginLockEvt);
				lockInfoList = staffMapper.queryLoginLockInfo(loginLockEvt);
				// 判断当前时间小于解锁时间则已锁，否则未锁
				if (!lockInfoList.isEmpty()) {
					for (LoginLockInfo loginLockInfo : lockInfoList) {
						Date now = new Date();
						if (now.getTime() < loginLockInfo.getUnlockTime().getTime()) {
							long itime = (loginLockInfo.getUnlockTime().getTime() - now.getTime()) / 1000;
							long dtime = itime / 60;
							if (itime % 60 > 0) {
								dtime++;
							}
							result.put("result", true);
							result.put("lockTime", dtime);// 距离解锁时间 单位分钟
							return result;
							// return true;
						}
					}
				}
			}
			QueryLoginLogEvt queryLoginLogEvt = new QueryLoginLogEvt();
			queryLoginLogEvt.setAccount(account);
			queryLoginLogEvt.setAccountType(accountType);
			int intLockTime = Integer.valueOf(lockTime);

			long lockTiem = Long.valueOf(lockTime);
			Date createTime = new Date();
			// 根据不同单位获取时间段
			if ("D".equals(lockTimeRule)) {
				createTime = DateUtils.addDays(new Date(), -intLockTime);
				lockTiem = lockTiem * 24 * 60;
			} else if ("H".equals(lockTimeRule)) {
				createTime = DateUtils.addHours(new Date(), -intLockTime);
				lockTiem = lockTiem * 60;
			} else if ("M".equals(lockTimeRule)) {
				createTime = DateUtils.addMinutes(new Date(), -intLockTime);
			} else if ("S".equals(lockTimeRule)) {
				createTime = DateUtils.addSeconds(new Date(), -intLockTime);
				lockTiem = lockTiem / 60;
				if (lockTiem % 6 > 0) {
					lockTiem++;
				}
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String startDate = formatter.format(createTime);
			String endDate = formatter.format(new Date());
			queryLoginLogEvt.setStartDate(startDate);
			queryLoginLogEvt.setEndDate(endDate);
//			queryLoginLogEvt.setLanguageType(evt.getLanguageType());
			// 查询登录日志
			List<LoginLogInfo> loginLogList = loginLogService.queryLoginLogListForLoginLock(queryLoginLogEvt);
			// 遍历登录失败次数
			int countFalseTimes = 0;
			for (LoginLogInfo loginLogInfo : loginLogList) {
				if (StringUtils.isNotBlank(loginLogInfo.getLoginStatus())) {
					if ("S".equals(loginLogInfo.getLoginStatus())) {
						break;
					} else {
						// 遍历锁定表，若锁定时间小于当前时间，表示此账号解锁，则计数+1
						boolean flag = true;
						if (lockInfoList != null) {
							for (LoginLockInfo lockInfo : lockInfoList) {
								if (lockInfo.getUnlockTime() != null) {
									if (lockInfo.getUnlockTime().after(new Date())) {
										flag = false;
									}
								}
							}
						}
						if (flag) {
							++countFalseTimes;
						}
					}
				}
			}
			String isOkAccountPassword = evt.getIsOkAccountPassword();
			if (StringUtils.isBlank(isOkAccountPassword)) {
				isOkAccountPassword = "N";
			}
			if ("N".equals(isOkAccountPassword)) {//如果账号或密码错误的话 判断次数减一 进行判断登录错误次数
				// 若登录失败次数大于最大密码错误次数，则锁定该用户
				if (countFalseTimes >= (lockNum-1)) {
					AddLoginLockEvt addLockEvt = new AddLoginLockEvt();
					addLockEvt.setApplyType(applyType);
					addLockEvt.setAccount(account);
					addLockEvt.setAccountType(accountType);
					addLockEvt.setCreateTime(new Date());
					LoginLockBean loginLockBean = doAddLoginLockInfo(addLockEvt);
					if (loginLockBean != null) {
						if (loginLockBean != null) {
							result.put("result", true);
							result.put("lockTime", lockTiem);// 距离解锁时间 单位分钟
							return result;
						}
						// return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// return false;
		result.put("result", false);
		return result;
	}

	public void doAddLoginLockInfo(QueryLoginLockEvt evt) {
		String account = evt.getAccount();
		String accountType = evt.getAccountType();
		String applyType = evt.getApplyType();
		// 适用类型 B:业务系统 A:APP
		if (StringUtils.isBlank(applyType)) {
			applyType = "B";
		}
		// 是否开启登录次数验证
		String bOpenloginLock = getSystemParam("loginLock.switch");
		// 登入验证次数
		String loginLockNum = getSystemParam("loginLock.num");
		QueryLoginRuleEvt queryLoginRuleEvt = new QueryLoginRuleEvt();
		queryLoginRuleEvt.setApplyType(applyType);
		LoginRuleInfo loginRuleInfo = loginRuleService.queryDetail(queryLoginRuleEvt);
		String lockTime = "";
		String lockTimeRule = "";
		if (loginRuleInfo != null) {
			bOpenloginLock = "1";
			if (StringUtils.isNotBlank(loginRuleInfo.getMaxError())) {
				loginLockNum = loginRuleInfo.getMaxError();
			}
			if (StringUtils.isNotBlank(loginRuleInfo.getLockTime())) {
				lockTime = loginRuleInfo.getLockTime();
			}
			if (StringUtils.isNotBlank(loginRuleInfo.getLockTimeRule())) {
				lockTimeRule = loginRuleInfo.getLockTimeRule();
			}
		}
		if ("1".equals(bOpenloginLock)) {
			int lockNum = 0;
			try {
				lockNum = Integer.valueOf(loginLockNum);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (lockNum > 0) {// 参数正确
				// 是否已锁定
				// boolean isLock = checkLoginLock(evt);
				Map<String, Object> teMap = checkLoginLock(evt);
				boolean isLock = (boolean) teMap.get("result");
				if (isLock) {
					return;
				}
				// 查询锁定日志
				QueryLoginLogEvt loginLogEvt = new QueryLoginLogEvt();
				loginLogEvt.setAccountAll(account);
				loginLogEvt.setAccountType(accountType);
				int intLockTime = Integer.valueOf(lockTime);
				Date createTime = null;
				// 根据不同单位获取时间段
				if ("D".equals(lockTimeRule)) {
					createTime = DateUtils.addDays(new Date(), -intLockTime);
				} else if ("H".equals(lockTimeRule)) {
					createTime = DateUtils.addHours(new Date(), -intLockTime);
				} else if ("M".equals(lockTimeRule)) {
					createTime = DateUtils.addMinutes(new Date(), -intLockTime);
				} else if ("S".equals(lockTimeRule)) {
					createTime = DateUtils.addSeconds(new Date(), -intLockTime);
				}
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String startDate = formatter.format(createTime);
				String endDate = formatter.format(new Date());
				loginLogEvt.setStartDate(startDate);
				loginLogEvt.setEndDate(endDate);
				List<LoginLogInfo> loginLogList = loginLogService.queryLoginLogList(loginLogEvt);
				int failNum = 0;
				for (LoginLogInfo loginLogInfo : loginLogList) {
					if (StringUtils.isNotBlank(loginLogInfo.getLoginStatus())) {
						if ("S".equals(loginLogInfo.getLoginStatus())) {
							break;
						} else {
							failNum++;
						}
					}
				}
				if (failNum >= lockNum) {
					// 连续登录次数全部失败，锁定登录状态
					AddLoginLockEvt addLockEvt = new AddLoginLockEvt();
					addLockEvt.setAccount(account);
					addLockEvt.setAccountType(accountType);
					addLockEvt.setCreateTime(new Date());
					doAddLoginLockInfo(addLockEvt);
				}
			}
		}
	}

	// 根据groupId查询商户id
	// public Long getMerchantIdByGroupId(Long groupId) {
	// try {
	// if (groupId != null && groupId > 0) {
	// Map<String, Object> params = new HashMap<>();
	// params.put("groupId", groupId);
	// ServerResp resp = serviceRestUtil.post(MERCHANT_SERVICE,
	// "/userGroup/queryByParam", params);
	// if (resp.success() && resp.getBody() != null) {
	// List<Map<String, Object>> merchantList = (List<Map<String, Object>>) resp
	// .getBody();
	// if (merchantList != null && merchantList.size() > 0) {
	// return Long.valueOf(String.valueOf(merchantList.get(0)
	// .get("id")));
	// }
	// }
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return null;
	// }

	public ServerResp<Object> resetPwd(EditStaffPwdEvt evt) {
		ServerResp<Object> resp = new ServerResp<Object>();
		// 校验账号与旧密码是否匹配
		String staffCode = evt.getStaffCode();
		String passWd = evt.getPassWd();
		QueryStaffEvt queryStaffEvt = new QueryStaffEvt();
		queryStaffEvt.setStaffCode(staffCode);
		queryStaffEvt.setPassWd(passWd);
		List<StaffInfo> staffInfos = queryStaffInfo(queryStaffEvt);
		if (staffInfos == null) {
			return resp.error(CommonResultCode.error.getRespCode(), "对不起，用户不存在");
		}
		if (staffInfos.size() == 0) {
			return resp.error(CommonResultCode.error.getRespCode(), "对不起，用户不存在");
		}
		/** 校验密码 **/
		StaffInfo staffInfo = staffInfos.get(0);
		/** MD5解密后比对 **/
		if (!Md5Util.MD5(passWd).equals(staffInfo.getPassWd())) {
			return resp.error(CommonResultCode.error.getRespCode(), "旧密码错误");
		}
		QueryLoginRuleEvt queryLoginRuleEvt = new QueryLoginRuleEvt();
		queryLoginRuleEvt.setApplyType("B");
		// 获取密码规则
		LoginRuleInfo loginRuleInfo = loginRuleService.queryDetail(queryLoginRuleEvt);
		String minPwd = loginRuleInfo.getMinPwd();
		String maxPwd = loginRuleInfo.getMaxPwd();
		String newPassword = evt.getNewPassWd();
		/** 弱密码校验判断 **/
		if (StringUtils.isNotBlank(minPwd) && StringUtils.isNotBlank(maxPwd)) {
			int min = Integer.valueOf(minPwd);
			int max = Integer.valueOf(maxPwd);
			if (newPassword.length() <= min || newPassword.length() >= max) {
				return resp.error(-3, (min+1) + "," + (max-1));
			}
		} else if (StringUtils.isNotBlank(minPwd) && StringUtils.isBlank(maxPwd)) {
			int min = Integer.valueOf(minPwd);
			if (newPassword.length() < min) {
				return resp.error(-3, (min+1) + "," + "-1");
			}
		} else if (StringUtils.isBlank(minPwd) && StringUtils.isNotBlank(maxPwd)) {
			int max = Integer.valueOf(maxPwd);
			if (newPassword.length() > max) {
				return resp.error(-3, "-1" + "," + (max-1));
			}
		}

		if (!checkAgent(newPassword)) {
			return resp.error(-2, "密码要包含数字，大写字母，小写字母，特殊字符其中3种");
		}
		
		//判断是否是默认密码
		if(StringUtils.isNotBlank(loginRuleInfo.getIniPwd())) {
			if (loginRuleInfo.getIniPwd().equals(newPassword)) {
				return resp.error(-5, "新密码不能是默认初始密码");
			}
		}
		// if (differentPwdNum(newPassword)) {
		// return resp.error(-2,"密码中包含不同字符总数小于4种");
		// }
		// 取账号id将其登录密码改为新密码
		EditStaffEvt editStaffEvt = new EditStaffEvt();
		editStaffEvt.setId(staffInfo.getId());
		if (StringUtils.isNotBlank(newPassword)) {
			editStaffEvt.setPassWd(Md5Util.MD5(newPassword));
		}
		editStaffEvt.setUpdateTime(new Date());
		int n = staffMapper.doEdit(editStaffEvt);
		if (n > 0) {
			resp.getHead().setRespCode(0);
			resp.getHead().setRespMsg("操作成功");
		}
		return resp;
	}

	/**
	 * 密码必须是数字、大写字母、小写字母、特殊字符其中3种的组合 不是组合返回false
	 */
	public boolean checkAgent(String pwd) {
		Pattern p = Pattern.compile(
				"^(?![0-9a-z]+$)(?![0-9A-Z]+$)(?![0-9\\W]+$)(?![a-z\\W]+$)(?![a-zA-Z]+$)(?![A-Z\\W]+$)[a-zA-Z0-9\\W_]+$");
		Matcher m = p.matcher(pwd);
		if (m.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 不同字符总数小于4，（如1122AA算3个字符，111111算一个字符） 小于4 返回true
	 */
	public boolean differentPwdNum(String str) {
		boolean flag = false;
		int num = 1;// 字符数
		char[] cs = str.toCharArray();
		List list = new ArrayList();
		for (int i = 0; i < cs.length; i++) {
			list.add(i, cs[i]);
		}
		int c = cs[0];
		for (int i = 1; i < list.size(); i++) {
			char lc = (Character) list.get(i);
			if (c != (int) lc) {
				for (int j = i; j < list.size(); j++) {
					char jc = (Character) list.get(j);
					if (c == (int) jc) {
						list.remove(j);
						j--;
					}
				}
				c = (int) lc;
				num++;
			}
		}
		if (num < 4) {
			flag = true;
		}
		return flag;
	}

	public List<Map<String, Object>> queryForOption(QueryStaffEvt evt) {
		return staffMapper.queryForOption(evt);
	}

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T clone(T obj) {

		T clonedObj = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			oos.close();

			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			clonedObj = (T) ois.readObject();
			ois.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return clonedObj;
	}
}
