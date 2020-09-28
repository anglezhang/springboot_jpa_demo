package cn.com.doone.tx.cloud.service.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.doone.tx.cloud.service.config.utils.RegexUtil;
import cn.com.doone.tx.cloud.service.config.utils.TreeUtils;
import cn.com.doone.tx.cloud.service.user.evt.loginlock.QueryLoginLockEvt;
import cn.com.doone.tx.cloud.service.user.evt.loginlog.AddLoginLogEvt;
import cn.com.doone.tx.cloud.service.user.evt.loginrule.QueryLoginRuleEvt;
import cn.com.doone.tx.cloud.service.user.evt.menuInfo.QueryPermissionMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.rolemenu.QueryRoleMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.LoginEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.QueryStaffEvt;
import cn.com.doone.tx.cloud.service.user.evt.staffRegion.QueryStaffRegionEvt;
import cn.com.doone.tx.cloud.service.user.info.LoginRuleInfo;
import cn.com.doone.tx.cloud.service.user.info.RoleInfo;
import cn.com.doone.tx.cloud.service.user.info.StaffInfo;
import cn.com.doone.tx.cloud.service.user.info.StaffRegionInfo;
import cn.com.doone.tx.cloud.service.user.service.LoginLogService;
import cn.com.doone.tx.cloud.service.user.service.LoginRuleService;
import cn.com.doone.tx.cloud.service.user.service.MenuService;
import cn.com.doone.tx.cloud.service.user.service.RoleMenuService;
import cn.com.doone.tx.cloud.service.user.service.StaffRegionService;
import cn.com.doone.tx.cloud.service.user.service.StaffService;
import cn.com.doone.tx.cloud.tool.common.enums.CommonResultCode;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.common.util.DictLangUtil;
import cn.com.doone.tx.cloud.tool.common.util.Md5Util;
import cn.com.doone.tx.cloud.tool.common.util.TicketFlag;
import cn.com.doone.tx.cloud.tool.log.annotation.ServiceAround;
import cn.com.doone.tx.cloud.tool.login.bean.MenuInfo;
import cn.com.doone.tx.cloud.tool.service.common.base.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * Created by yecz on 2017/1/22 0022.
 */
@RestController
@RequestMapping("/login")
@Api(description = "登录服务")
public class LoginController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private RoleMenuService roleMenuService;
    
    @Autowired
    private MenuService menuService;
    
    
    @Autowired
    private LoginRuleService loginRuleService;
    
    @Autowired
    private LoginLogService loginLogService;
    
    @Autowired
    private RedisTemplate redisTemplate;
    
    @Autowired
    private DictLangUtil dictLangUtil;
    
    @Autowired
    private StaffRegionService staffRegionService;
    
    @ServiceAround
    @ApiOperation(value="用户登录#受理", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "dologin" , method = RequestMethod.POST)
    public ServerResp<Object> dologin(@RequestBody Message<LoginEvt> message){
        LoginEvt evt = message.getBody();
        ServerResp<Object> resp=new ServerResp<Object>();
        try {		
            String staffCode = evt.getStaffCode();
            String passWd = evt.getPassWd();
            if (StringUtils.isBlank(staffCode)||StringUtils.isBlank(passWd)){
                return resp.error(1001,"AUTH_VALIDATE_CODE_EMPTY");
            }
            
            /**根据账号查出用户信息*/
            QueryStaffEvt queryStaffEvt = new QueryStaffEvt();
            queryStaffEvt.setStaffCode(staffCode);
            queryStaffEvt.setPassWd(passWd);
            List<StaffInfo> staffInfos = staffService.queryStaffInfo(queryStaffEvt);
            if (staffInfos==null){
                return resp.error(1002,"AUTH_STAFF_NOTEXIST");
//                return resp.error(CommonResultCode.error.getRespCode(),"对不起，用户不存在");
            }
            if (staffInfos.size()==0){
            	return resp.error(1002,"AUTH_STAFF_NOTEXIST");
//                return resp.error(CommonResultCode.error.getRespCode(),"对不起，用户不存在");
            }
            
            /**校验密码**/
            StaffInfo t = staffInfos.get(0);
            /**MD5解密后比对**/
            if(!Md5Util.MD5(passWd).equals(t.getPassWd())){
            	return resp.error(1003,"AUTH_ACCOUNT_ERROR");
//                return resp.error(CommonResultCode.error.getRespCode(),"账号或密码错误");
            }
            /**是否禁用**/
            if(!"E".equals(t.getStatus())){
            	return resp.error(1004,"AUTH_ACCOUNT_FORBIDDEN");
//                return resp.error(CommonResultCode.error.getRespCode(),"登录失败，该账号已禁用");
            }
            /**成功**/
            List<RoleInfo> roleInfoList = t.getRoleInfoList();
            List<MenuInfo> menuInfoList = new ArrayList<>();
            if (roleInfoList!=null){
                for (RoleInfo roleInfo:roleInfoList){
                    /**查出角色绑定的菜单、按钮**/
                    Long roleId = roleInfo.getId();
                    QueryRoleMenuEvt queryRoleMenuEvt = new QueryRoleMenuEvt();
                    queryRoleMenuEvt.setRoleId(roleId);
                    List<MenuInfo> list = roleMenuService.queryRoleMenuInfos(queryRoleMenuEvt);
                    for (MenuInfo menuInfo:list){
                        if (menuInfo!=null){
                            menuInfoList.add(menuInfo);
                        }
                    }

                }
            }
            t.setMenuInfoList(menuInfoList);
            t.setPassWd("");//密码置空，不缓存
            //商户id
//            t.setMerchantId(staffService.getMerchantIdByGroupId(t.getGroupId()));
            /**将loginTicket作为key，用户信息staffInfo作为Value保存至Redis*/
            String loginTicket = TicketFlag.getTicket();//生成一个登录ticket,作为换取用户信息的凭证
            redisTemplate.opsForValue().set(loginTicket,t);
            /**设置失效时间 60分钟*/
            redisTemplate.expire(loginTicket,60, TimeUnit.MINUTES);
            /**返回用户信息StaffInfo+loginTicket*/
            t.setLoginTicket(loginTicket);
            
            return resp.success(t);
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();//统一系统异常
        }
    }
    
    @ServiceAround
	@ApiOperation(value="后台用户登录", notes="respCode 0成功，-1失败")
	@RequestMapping(value = "doPlatLogin" , method = RequestMethod.POST)
	public ServerResp<Object> doPlatLogin(@RequestBody Message<LoginEvt> message){
		LoginEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			String loginTicket = TicketFlag.getTicket();//生成一个登录ticket,作为换取用户信息的凭证
			String staffCode = evt.getStaffCode();
			String passWd = evt.getPassWd();
			String accountType = evt.getAccountType();
			AddLoginLogEvt addLoginLogEvt = new AddLoginLogEvt();
            addLoginLogEvt.setAccount(staffCode);
            addLoginLogEvt.setAccountType(accountType);
            
            if (StringUtils.isNotBlank(evt.getLoginType())) {
            	addLoginLogEvt.setLoginType(evt.getLoginType());
            }
            if (StringUtils.isNotBlank(evt.getLoginIp())) {
            	addLoginLogEvt.setLoginIp(evt.getLoginIp());
            }
            if (StringUtils.isNotBlank(evt.getLoginClient())) {
            	addLoginLogEvt.setLoginClient(evt.getLoginClient());
            }
            if (StringUtils.isNotBlank(evt.getLoginBrowser())) {
            	addLoginLogEvt.setLoginBrowser(evt.getLoginBrowser());
            }
            if (StringUtils.isNotBlank(evt.getSysCode())) {
            	addLoginLogEvt.setLoginChannelCode(evt.getSysCode());
            	addLoginLogEvt.setChannelCode(evt.getSysCode());
            }
			if (StringUtils.isBlank(staffCode)||StringUtils.isBlank(passWd)){
				addLoginLogEvt.setLoginStatus("F");
				addLoginLogEvt.setLoginResult("账号或密码为空");
				//存入登录日志
				loginLogService.addLoginLog(addLoginLogEvt);
				return resp.error(1001,"账号或密码为空");
			}
			/**根据账号查出用户信息*/
			QueryStaffEvt queryStaffEvt = new QueryStaffEvt();
			queryStaffEvt.setStaffCode(staffCode);
			queryStaffEvt.setPassWd(passWd);
			List<StaffInfo> staffInfos = staffService.queryStaffInfo(queryStaffEvt);
			
			QueryLoginLockEvt queryLoginLockEvt = new QueryLoginLockEvt();
			queryLoginLockEvt.setAccount(evt.getStaffCode());
			queryLoginLockEvt.setAccountType(accountType);//账号类型
			
			if (staffInfos==null){
				/**是否锁定  账号密码错误次数是否达到最大错误次数*/
				queryLoginLockEvt.setIsOkAccountPassword("N");
	            //boolean isLock = staffService.checkLoginLock(queryLoginLockEvt);
				Map<String, Object> tempMap = staffService.checkLoginLock(queryLoginLockEvt);
				boolean isLock = (boolean) tempMap.get("result");
	            if(isLock){
	            	addLoginLogEvt.setLoginStatus("F");
					addLoginLogEvt.setLoginResult("登录失败，账号已被锁定");
					loginLogService.addLoginLog(addLoginLogEvt);
					return resp.error(1005,"登录失败，账号已被锁定",tempMap.get("lockTime"));
//	        		return resp.error("登录失败，账号已被锁定");
	        	}
				
//                return resp.error(1002,"对不起，用户不存在");AUTH_STAFF_NOTEXIST
				addLoginLogEvt.setLoginStatus("F");
				addLoginLogEvt.setLoginResult("账号或密码错误");
				loginLogService.addLoginLog(addLoginLogEvt);
				return resp.error(1002,"账号或密码错误");
			}
			if (staffInfos.size()==0){
				
				/**是否锁定  账号密码错误次数是否达到最大错误次数*/
				queryLoginLockEvt.setIsOkAccountPassword("N");
	            //boolean isLock = staffService.checkLoginLock(queryLoginLockEvt);
				Map<String, Object> tempMap = staffService.checkLoginLock(queryLoginLockEvt);
				boolean isLock = (boolean) tempMap.get("result");
	            if(isLock){
	            	addLoginLogEvt.setLoginStatus("F");
					addLoginLogEvt.setLoginResult("登录失败，账号已被锁定");
					loginLogService.addLoginLog(addLoginLogEvt);
					return resp.error(1005,"登录失败，账号已被锁定",tempMap.get("lockTime"));
//	        		return resp.error("登录失败，账号已被锁定");
	        	}
				
				addLoginLogEvt.setLoginStatus("F");
				addLoginLogEvt.setLoginResult("账号或密码错误");
				loginLogService.addLoginLog(addLoginLogEvt);
				return resp.error(1002,"账号或密码错误");
			}
			
			/**校验密码**/
			StaffInfo staffInfo = staffInfos.get(0);
			
			addLoginLogEvt.setAccountId(String.valueOf(staffInfo.getId()));
			addLoginLogEvt.setAccountName(staffInfo.getStaffName());
			
			/**MD5解密后比对**/
			if(!Md5Util.MD5(passWd).equals(staffInfo.getPassWd())){
				
				/**是否锁定 账号密码错误次数是否达到最大错误次数*/
				queryLoginLockEvt.setIsOkAccountPassword("N");
	            //boolean isLock = staffService.checkLoginLock(queryLoginLockEvt);
				Map<String, Object> tempMap = staffService.checkLoginLock(queryLoginLockEvt);
				boolean isLock = (boolean) tempMap.get("result");
	            if(isLock){
	            	addLoginLogEvt.setLoginStatus("F");
					addLoginLogEvt.setLoginResult("登录失败，账号已被锁定");
					loginLogService.addLoginLog(addLoginLogEvt);
					return resp.error(1005,"登录失败，账号已被锁定",tempMap.get("lockTime"));
//	        		return resp.error("登录失败，账号已被锁定");
	        	}
				
				addLoginLogEvt.setLoginStatus("F");
				addLoginLogEvt.setLoginResult("账号或密码错误");
				loginLogService.addLoginLog(addLoginLogEvt);
				return resp.error(1003,"账号或密码错误");
//				return resp.error(CommonResultCode.error.getRespCode(),"账号或密码错误");
			}
			
			/**是否锁定*/
			queryLoginLockEvt.setIsOkAccountPassword("Y");
            //boolean isLock = staffService.checkLoginLock(queryLoginLockEvt);
			Map<String, Object> tempMap = staffService.checkLoginLock(queryLoginLockEvt);
			boolean isLock = (boolean) tempMap.get("result");
            if(isLock){
            	addLoginLogEvt.setLoginStatus("F");
				addLoginLogEvt.setLoginResult("登录失败，账号已被锁定");
				loginLogService.addLoginLog(addLoginLogEvt);
				return resp.error(1005,"登录失败，账号已被锁定",tempMap.get("lockTime"));
//        		return resp.error("登录失败，账号已被锁定");
        	}
			
			/**获取密码规则*/
			QueryLoginRuleEvt paramEvt = new QueryLoginRuleEvt();
			paramEvt.setApplyType("B");
			LoginRuleInfo loginRuleInfo = loginRuleService.queryDetail(paramEvt);
			
			/**是否初始密码校验*/
			String iniPwd = loginRuleInfo.getIniPwd();
			if (StringUtils.isNotBlank(iniPwd)) {
				if (passWd.equals(iniPwd)) {
					/**成功**/
					addLoginLogEvt.setLoginStatus("S");
					addLoginLogEvt.setLoginResult(CommonResultCode.success.getRespMsg());
					loginLogService.addLoginLog(addLoginLogEvt);
					List<MenuInfo> menuInfoList = new ArrayList<>();
					QueryPermissionMenuEvt permissionEvt = new QueryPermissionMenuEvt();
//					permissionEvt.setLanguageType(evt.getLanguageType());
					if (!"1".equals(staffInfo.getIsSuperManager())) {
						permissionEvt.setStaffId(staffInfo.getId());
					}
					menuInfoList = roleMenuService.queryPermissionMenu(permissionEvt);
					staffInfo.setMenuInfoList(TreeUtils.factorTree(menuInfoList, "id",
							"parentId", "children", 0l));
					staffInfo.setPassWd("");//密码置空，不缓存
					/**将loginTicket作为key，用户信息staffInfo作为Value保存至Redis*/
					redisTemplate.opsForValue().set(loginTicket,staffInfo);
					/**设置失效时间 60分钟*/
					redisTemplate.expire(loginTicket,12, TimeUnit.HOURS);
					//此redis保存在用户无法同时在线时使用
					redisTemplate.opsForHash().put("plat.login.ticket",staffCode,loginTicket);
					Map<String,Object> returnMap = new HashMap<String, Object>();
//					returnMap.put("token",token);
					returnMap.put("loginTicket",loginTicket);
					returnMap.put("staffInfo",staffInfo);
					
					resp.success(returnMap,"登录成功");
//					return resp.error(-2,"AUTH_ORIGIN_PASSWD");
					resp.getHead().setRespCode(-2);
					return resp;
				}
			}
			
			/**弱密码校验判断**/
			String minPwd = loginRuleInfo.getMinPwd();
			String maxPwd = loginRuleInfo.getMaxPwd();
			if (StringUtils.isNotBlank(minPwd) && StringUtils.isNotBlank(maxPwd)) {
				Integer minLength = Integer.valueOf(loginRuleInfo.getMinPwd());
				Integer maxLength = Integer.valueOf(loginRuleInfo.getMaxPwd());
				if (passWd.length() < minLength || passWd.length() > maxLength) {
					return resp.error(1006, minLength + "," + maxLength);
//					return resp.error("密码长度需为" + minLength + "-" + maxLength + "位");
				}
			} else if (StringUtils.isNotBlank(minPwd) && StringUtils.isBlank(maxPwd)) {
				Integer minLength = Integer.valueOf(loginRuleInfo.getMinPwd());
				if (passWd.length() < minLength) {
					return resp.error(1006, minLength + "," + "-1");
//					return resp.error("密码长度需大于" + minLength + "位");
				}
			} else if (StringUtils.isNotBlank(maxPwd) && StringUtils.isBlank(minPwd)) {
				Integer maxLength = Integer.valueOf(loginRuleInfo.getMaxPwd());
				if (passWd.length() > maxLength) {
					return resp.error(1006, "-1" + "," + maxLength);
//					return resp.error("密码长度需小于" + maxLength + "位");
				}
			}
			/**设置用户类型*/
			String ruleType = loginRuleInfo.getRuleType();
			if (StringUtils.isBlank(ruleType)) {
				loginRuleInfo.setRuleType("1");
			}
			/**密码正则校验*/
			if ("1".equals(loginRuleInfo.getRuleType())) {
				if (!RegexUtil.checkAgent(passWd)) {
					return resp.error("密码要包含数字，大写字母，小写字母，特殊字符其中3种");
				}
			}
			
//			if (regexUtil.differentPwdNum(passWd)) {
//				return resp.error("密码中包含不同字符总数小于4种");
//			}
			/**是否禁用**/
			if(!"E".equals(staffInfo.getStatus())){
				return resp.error(1004, "登录失败，该账号已禁用");
			}
			//该用户组是否被禁用
//			QueryStaffGroupEvt groupEvt = new QueryStaffGroupEvt();
//			groupEvt.setId(staffInfo.getGroupId());
//			groupEvt.setStatus("E");
//			int groupcnt  = staffGroupService.queryCount(groupEvt);
//			if(groupcnt<1){
//				return resp.error(CommonResultCode.error.getRespCode(),"登录失败，该账号所属的用户组已禁用");
//			}
			/**成功**/
			addLoginLogEvt.setLoginStatus("S");
			addLoginLogEvt.setLoginResult(CommonResultCode.success.getRespMsg());
			loginLogService.addLoginLog(addLoginLogEvt);
			List<MenuInfo> menuInfoList = new ArrayList<>();
			QueryPermissionMenuEvt permissionEvt = new QueryPermissionMenuEvt();
//			permissionEvt.setLanguageType(evt.getLanguageType());
			if (!"1".equals(staffInfo.getIsSuperManager())) {
				permissionEvt.setStaffId(staffInfo.getId());
			}
			menuInfoList = roleMenuService.queryPermissionMenu(permissionEvt);
			staffInfo.setMenuInfoList(TreeUtils.factorTree(menuInfoList, "id",
					"parentId", "children", 0l));
			staffInfo.setPassWd("");//密码置空，不缓存
			
			//国家编码取出放入info
			QueryStaffRegionEvt qsrEvt = new QueryStaffRegionEvt();
			qsrEvt.setStaffId(staffInfo.getId());
			List<StaffRegionInfo> staffRegions = staffRegionService.queryByParam(qsrEvt);
			if(null != staffRegions && !staffRegions.isEmpty()){
				List<String> countryCodes = new ArrayList<String>();
				for (StaffRegionInfo staffRegion : staffRegions) {
					countryCodes.add(staffRegion.getCountryCode());
				}
				staffInfo.setCountryCodes(countryCodes);
			}
			
//			//商户id
//			staffInfo.setMerchantId(staffService.getMerchantIdByGroupId(staffInfo.getGroupId()));
			/**将loginTicket作为key，用户信息staffInfo作为Value保存至Redis*/
			redisTemplate.opsForValue().set(loginTicket,staffInfo);
			/**设置失效时间 60分钟*/
			redisTemplate.expire(loginTicket,12, TimeUnit.HOURS);

			//此redis保存在用户无法同时在线时使用
			redisTemplate.opsForHash().put("plat.login.ticket",staffCode,loginTicket);
			//token与ticket入表
//			if("true".equals(useToken)) {
//				AddTokenTicketEvt addTokenTicketEvt = new AddTokenTicketEvt();
//				addTokenTicketEvt.setToken(token);
//				addTokenTicketEvt.setLoginTicket(loginTicket);
//				loginWebService.saveTokenAndTicket(addTokenTicketEvt);
//			}
			Map<String,Object> returnMap = new HashMap<String, Object>();
//			returnMap.put("token",token);
			returnMap.put("loginTicket",loginTicket);
			returnMap.put("staffInfo",staffInfo);
			
			return resp.success(returnMap,"登录成功");
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();//统一系统异常
		}
	}

    @ServiceAround
    @ApiOperation(value="从缓存中获取用户登录信息#查询", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "getStaffInfoFromRedis" , method = RequestMethod.POST)
    public ServerResp<Object> getStaffInfoFromRedis(@RequestBody Message<QueryStaffEvt> message){
        ServerResp<Object> resp = new ServerResp<>();
        QueryStaffEvt evt = message.getBody();
        String loginTicket = evt.getLoginTicket();
        System.out.println("获取用户信息时传入的ticket:"+loginTicket);
        if (StringUtils.isBlank(loginTicket)){
            return resp.error(CommonResultCode.error.getRespCode(),"获取用户信息Ticket为空");
        }
        try {
            if ( redisTemplate.opsForValue().get(loginTicket) != null){
                StaffInfo staffInfo = (StaffInfo) redisTemplate.opsForValue().get(loginTicket);
                /**自动延长 30分钟**/
                redisTemplate.expire(loginTicket,12,TimeUnit.HOURS);
                return resp.success(staffInfo);
            }else {
                return resp.error(CommonResultCode.error.getRespCode(),"用户信息为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
//            return resp.error("获取用户信息失败");
            return resp.systemError();//统一系统异常
        }
    }

    @ServiceAround
    @ApiOperation(value="删除缓存中的用户登录信息#受理", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "removeStaffInfoFromRedis" , method = RequestMethod.POST)
    public ServerResp<Object> removeStaffInfoFromRedis(@RequestBody Message<QueryStaffEvt> message){
        ServerResp<Object> resp = new ServerResp<>();
        QueryStaffEvt evt = message.getBody();
        String loginTicket = evt.getLoginTicket();
//        System.out.println("注销ticket:"+loginTicket);
        if (StringUtils.isBlank(loginTicket)){
            return resp.error(CommonResultCode.error.getRespCode(),"获取用户信息Ticket为空");
        }
        try {
            redisTemplate.delete(loginTicket);
//            resp.getHead().setRespCode(0);
            return resp.success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
//            return resp.error("操作失败");
            return resp.systemError();//统一系统异常
        }
    }
    
    @ServiceAround
	@ApiOperation(value="后台用户信息存入redis", notes="")
	@RequestMapping(value = "saveStaffToRedis" , method = RequestMethod.POST)
	public ServerResp<Object> saveStaffToRedis(@RequestBody Message<StaffInfo> message){
		ServerResp<Object> resp = new ServerResp<>();
		StaffInfo staffInfo = message.getBody();
		try {
			/**将loginTicket作为key，用户信息staffInfo作为Value保存至Redis*/
			redisTemplate.opsForValue().set(staffInfo.getLoginTicket(),staffInfo);
			/**设置失效时间 60分钟*/
			redisTemplate.expire(staffInfo.getLoginTicket(),12, TimeUnit.HOURS);
			resp.success(staffInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return resp.error();
		}
		return resp;
	}
    
    @ServiceAround
    @ApiOperation(value="用户账号取loginticket（限制同时在线使用）", notes="")
	@RequestMapping(value = "getLoginticketFromRedis" , method = RequestMethod.POST)
	public ServerResp<Object> getLoginticketFromRedis(@RequestBody Message<StaffInfo> message){
		ServerResp<Object> resp = new ServerResp<>();
		StaffInfo staffInfo = message.getBody();
		try {
			String loginTicket = (String) redisTemplate.opsForHash().get("plat.login.ticket",staffInfo.getStaffCode());
			resp.success(loginTicket);
		} catch (Exception e) {
			e.printStackTrace();
			return resp.error();
		}
		return resp;
	}
    
    @ServiceAround
    @ApiOperation(value="密码校验", notes="")
	@RequestMapping(value = "checkPassWd" , method = RequestMethod.POST)
	public ServerResp<Object> checkPassWd(@RequestBody Message<StaffInfo> message){
		ServerResp<Object> resp = new ServerResp<>();
		StaffInfo staffInfo = message.getBody();
		String passWd = staffInfo.getPassWd();
		try {
			/**获取密码规则*/
			QueryLoginRuleEvt paramEvt = new QueryLoginRuleEvt();
			paramEvt.setApplyType("B");
			LoginRuleInfo loginRuleInfo = loginRuleService.queryDetail(paramEvt);
			/**是否初始密码校验*/
			String iniPwd = loginRuleInfo.getIniPwd();
			if (StringUtils.isNotBlank(iniPwd)) {
				if (passWd.equals(iniPwd)) {
					return resp.error(-2,"AUTH_ORIGIN_PASSWD");
				}
			}
			/**弱密码校验判断**/
			String minPwd = loginRuleInfo.getMinPwd();
			String maxPwd = loginRuleInfo.getMaxPwd();
			if (StringUtils.isNotBlank(minPwd) && StringUtils.isNotBlank(maxPwd)) {
				Integer minLength = Integer.valueOf(loginRuleInfo.getMinPwd());
				Integer maxLength = Integer.valueOf(loginRuleInfo.getMaxPwd());
				if (passWd.length() < minLength || passWd.length() > maxLength) {
					return resp.error(1006, minLength + "," + maxLength);
				}
			} else if (StringUtils.isNotBlank(minPwd) && StringUtils.isBlank(maxPwd)) {
				Integer minLength = Integer.valueOf(loginRuleInfo.getMinPwd());
				if (passWd.length() < minLength) {
					return resp.error(1006, minLength + "," + "-1");
				}
			} else if (StringUtils.isNotBlank(maxPwd) && StringUtils.isBlank(minPwd)) {
				Integer maxLength = Integer.valueOf(loginRuleInfo.getMaxPwd());
				if (passWd.length() > maxLength) {
					return resp.error(1006, "-1" + "," + maxLength);
				}
			}
			resp.success();
		} catch (Exception e) {
			e.printStackTrace();
			return resp.error();
		}
		return resp;
	}
    
}
