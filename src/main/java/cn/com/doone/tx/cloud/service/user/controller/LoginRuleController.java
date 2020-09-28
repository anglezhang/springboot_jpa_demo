package cn.com.doone.tx.cloud.service.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.doone.tx.cloud.service.user.bean.LoginRuleBean;
import cn.com.doone.tx.cloud.service.user.evt.loginrule.AddLoginRuleEvt;
import cn.com.doone.tx.cloud.service.user.evt.loginrule.EditLoginRuleEvt;
import cn.com.doone.tx.cloud.service.user.evt.loginrule.QueryLoginRuleEvt;
import cn.com.doone.tx.cloud.service.user.info.LoginRuleInfo;
import cn.com.doone.tx.cloud.service.user.service.LoginRuleService;
import cn.com.doone.tx.cloud.tool.common.enums.CommonResultCode;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.log.annotation.ServiceAround;
import cn.com.doone.tx.cloud.tool.service.common.base.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/loginRule")
@Api(description = "登录密码规则服务")
public class LoginRuleController {
   
	private static final Logger LOGGER = Logger.getLogger(LoginRuleController.class);
	
	@Autowired
    private LoginRuleService loginRuleService;
	
	@ServiceAround
    @ApiOperation(value="查询总记录数#查询", notes="respCode 1-成功 0-失败")
    @RequestMapping(value = "queryCount" , method = RequestMethod.POST)
    public ServerResp<Object> queryCount(@RequestBody Message<QueryLoginRuleEvt> message){
    	QueryLoginRuleEvt paramEvt = message.getBody();
        ServerResp<Object> resp=new ServerResp<Object>();
        int resInt;
        try {
        	resInt = loginRuleService.queryCount(paramEvt);
            resp.success(resInt);
        } catch (Exception e) { 
        	LOGGER.error("查询总记录数查询异常：" + e, e);
        	resp.systemError(CommonResultCode.countError.getRespMsg());
        }
        return resp;
    }
	
	@ServiceAround
    @ApiOperation(value="查询记录列表#查询", notes="respCode 1-成功 0-失败")
    @RequestMapping(value = "queryList" , method = RequestMethod.POST)
    public ServerResp<Object> queryList(@RequestBody Message<QueryLoginRuleEvt> message){
    	QueryLoginRuleEvt paramEvt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        List<Map<String,Object>> dataList = null;
        try {
            paramEvt.startPage();
            dataList = loginRuleService.queryList(paramEvt);
            resp.success(dataList);
        } catch (Exception e) { 
        	LOGGER.error("查询记录列表异常：" + e, e);
            resp.systemError(CommonResultCode.listError.getRespMsg());
        }
        return resp;
    }
	
	@ServiceAround
    @ApiOperation(value="新增功能#受理", notes="respCode 1-成功 0-失败")
    @RequestMapping(value = "doAdd" , method = RequestMethod.POST)
    public ServerResp<Object> doAdd(@RequestBody  Message<AddLoginRuleEvt> message){
		AddLoginRuleEvt paramEvt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
        	LoginRuleBean addBean = loginRuleService.add(paramEvt);
        	if(addBean == null){
        		resp.error("对不起，该已存在");
        	}else {
        		Long id = addBean.getId();
                if (id!=null){
                    resp.success("操作成功");
                }else {
                    resp.error(CommonResultCode.addFail.getRespCode(),  CommonResultCode.addFail.getRespMsg());
                }
        	}
        } catch (Exception e) {
        	LOGGER.error("新增功能异常：" + e, e);
            resp.systemError(CommonResultCode.addFail.getRespMsg());
        }
        return resp;
    }
	
	@ServiceAround
    @ApiOperation(value="编辑功能#受理", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "doEdit" , method = RequestMethod.POST)
    public ServerResp<Object> doEdit(@RequestBody Message<EditLoginRuleEvt> message){
        EditLoginRuleEvt paramEvt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            int resInt = loginRuleService.doEdit(paramEvt);
            if(resInt == 0){
            	resp.error(-1, CommonResultCode.editFail.getRespMsg());
            }else {
            	resp.success(resInt);	
            }
        } catch (Exception e) {
        	LOGGER.error("编辑功能异常：" + e, e);
            resp.systemError(CommonResultCode.editFail.getRespMsg());
        }
        return resp;
    }
	
	@ServiceAround
    @ApiOperation(value="密码规则详情功能#受理", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "queryDetail" , method = RequestMethod.POST)
	public ServerResp<Object> queryDetail(@RequestBody Message<QueryLoginRuleEvt> message){
		QueryLoginRuleEvt paramEvt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
        	LoginRuleInfo loginRuleInfo = loginRuleService.queryDetail(paramEvt);
        	resp.success(loginRuleInfo);
		} catch (Exception e) {
			LOGGER.error("密码规则详情功能异常：" + e, e);
            resp.systemError(CommonResultCode.detailError.getRespMsg());
		}
        return resp; 
	}
    
    @ServiceAround
    @ApiOperation(value="获取移动手厅App初始密码#受理", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "queryInitPwdForApp" , method = RequestMethod.POST)
	public ServerResp<Object> queryInitPwdForApp(@RequestBody Message<QueryLoginRuleEvt> message){
		QueryLoginRuleEvt paramEvt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
        	paramEvt.setApplyType("A");
        	LoginRuleInfo loginRuleInfo = loginRuleService.queryInitPwd(paramEvt);
        	Map<String, String> map = new HashMap<String, String>();
        	map.put("iniPwd", loginRuleInfo.getIniPwd());
        	map.put("numLength", loginRuleInfo.getNumLength());
        	map.put("pwdType", loginRuleInfo.getPwdType());
        	resp.success(map);
		} catch (Exception e) {
			LOGGER.error("详情功能异常：" + e, e);
            resp.systemError(CommonResultCode.otherError.getRespMsg());
		}
        return resp;
	}
   
}

