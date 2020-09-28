package cn.com.doone.tx.cloud.service.wechat.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.doone.tx.cloud.service.wechat.evt.agent.QueryWxAgentEvt;
import cn.com.doone.tx.cloud.service.wechat.service.WxAgentService;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.log.annotation.ServiceAround;
import cn.com.doone.tx.cloud.tool.service.common.base.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/wxAgent")
@Api(description = "企业微信应用服务")
public class WxAgentController {
	
	private static final Logger LOGGER = Logger.getLogger(WxAgentController.class);
	
	@Autowired
	private WxAgentService wxAgentService;
	
	@ServiceAround
	@ApiOperation(value = "查询记录信息#查询", notes = "respCode 1-成功 0-失败")
	@RequestMapping(value = "queryWxAgentInfo", method = RequestMethod.POST)
	public ServerResp<Object> queryWxAgentInfo(@RequestBody Message<QueryWxAgentEvt> message) {
		QueryWxAgentEvt paramEvt = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		List<Map<String,Object>> dataList = null;
		try {
			dataList = wxAgentService.queryWxAgentInfo(paramEvt);
			resp.success(dataList);
		} catch (Exception e) {
			LOGGER.error("查询记录信息异常：" + e, e);
			resp.systemError("查询记录信息异常");
		}
		return resp;
	}


}
