package cn.com.doone.tx.cloud.service.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.doone.tx.cloud.service.config.evt.xss.QueryXssHeadSetEvt;
import cn.com.doone.tx.cloud.service.config.evt.xss.QueryXssRegexEvt;
import cn.com.doone.tx.cloud.service.config.evt.xss.QueryXssRuleEvt;
import cn.com.doone.tx.cloud.service.config.service.XssConfigService;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.log.annotation.ServiceAround;
import cn.com.doone.tx.cloud.tool.service.common.base.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/xssConfig")
@Api(description = "Xss配置服务")
public class XssConfigController {
	
	@Autowired
	private XssConfigService xssConfigService;

	@ServiceAround
	@ApiOperation(value = "查询Xss配置列表#查询", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "queryXssConfigList", method = RequestMethod.POST)
	public ServerResp<Object> queryXssConfigList(@RequestBody Message message) {
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			return resp.success(xssConfigService.queryXssConfigList());
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}
	}
	
	@ServiceAround
	@ApiOperation(value = "查询Xss Head配置列表#查询", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "queryXssHeadSetList", method = RequestMethod.POST)
	public ServerResp<Object> queryXssHeadSetList(@RequestBody Message<QueryXssHeadSetEvt> message) {
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			return resp.success(xssConfigService.queryXssHeadSetList(message.getBody()));
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}
	}
	
	@ServiceAround
	@ApiOperation(value = "查询Xss规则配置列表#查询", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "queryXssRuleList", method = RequestMethod.POST)
	public ServerResp<Object> queryXssRuleList(@RequestBody Message<QueryXssRuleEvt> message) {
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			return resp.success(xssConfigService.queryXssRuleList(message.getBody()));
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}
	}
	
	@ServiceAround
	@ApiOperation(value = "查询Xss正则配置列表#查询", notes = "respCode 0成功，-1失败")
	@RequestMapping(value = "queryXssRegexList", method = RequestMethod.POST)
	public ServerResp<Object> queryXssRegexList(@RequestBody Message<QueryXssRegexEvt> message) {
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			return resp.success(xssConfigService.queryXssRegexList(message.getBody()));
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}
	}
}
