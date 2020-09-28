package cn.com.doone.tx.cloud.service.wechat.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.doone.tx.cloud.service.wechat.evt.token.EditWxTokenEvt;
import cn.com.doone.tx.cloud.service.wechat.service.WxTokenService;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.log.annotation.ServiceAround;
import cn.com.doone.tx.cloud.tool.service.common.base.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/wxToken")
@Api(description = "企业微信token服务")
public class WxTokenController {

	private static final Logger LOGGER = Logger.getLogger(WxTokenController.class);
	
	@Autowired
	private WxTokenService wxTokenService;
	
	@ServiceAround
	@ApiOperation(value = "更新accesstoken#编辑", notes = "respCode 1-成功 0-失败")
	@RequestMapping(value = "updateAccessToken", method = RequestMethod.POST)
	public ServerResp<Object> updateAccessToken(@RequestBody Message<EditWxTokenEvt> message) {
		EditWxTokenEvt paramEvt = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			int res = wxTokenService.updateToken(paramEvt);
			if(res > 0) {
				resp.success(res);
			}else {
				resp.error("更新失败");
			}
			
		} catch (Exception e) {
			LOGGER.error("更新accesstoken异常：" + e, e);
			resp.systemError("更新accesstoken异常");
		}
		return resp;
	}

}
