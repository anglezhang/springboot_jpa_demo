package cn.com.doone.tx.cloud.service.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;

import cn.com.doone.tx.cloud.service.config.evt.channel.AddConfigChannelEvt;
import cn.com.doone.tx.cloud.service.config.evt.channel.EditConfigChannelEvt;
import cn.com.doone.tx.cloud.service.config.evt.channel.EditConfigChannelStatusEvt;
import cn.com.doone.tx.cloud.service.config.evt.channel.QueryConfigChannelEvt;
import cn.com.doone.tx.cloud.service.config.service.ConfigChannelService;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.log.annotation.ServiceAround;
import cn.com.doone.tx.cloud.tool.service.common.base.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by APPLE on 2017/2/4.
 */
@RestController
@RequestMapping("/configChannel/channel")
@Api(description = "渠道配置服务")
public class ConfigChannelController {

	@Autowired
	private ConfigChannelService configChannelService;

	@ServiceAround
	@ApiOperation(value = "获取渠道配置数量#查询", notes = "")
	@RequestMapping(value = "queryChannelCount", method = RequestMethod.POST)
	public ServerResp queryChannelCount(@RequestBody Message<QueryConfigChannelEvt> message) {
		QueryConfigChannelEvt queryConfigChannelEvt = message.getBody();
		return configChannelService.queryChannelCount(queryConfigChannelEvt);
	}

	@ServiceAround
	@ApiOperation(value = "根据参数查询渠道配置#查询", notes = "")
	@RequestMapping(value = "queryConfigChannelByParam", method = RequestMethod.POST)
	public ServerResp queryConfigChannelByParam(@RequestBody Message<QueryConfigChannelEvt> message) {
		QueryConfigChannelEvt queryConfigChannelEvt = message.getBody();
		PageHelper.startPage(queryConfigChannelEvt.getQueryPage(), queryConfigChannelEvt.getQuerySize());
		ServerResp resp = configChannelService.queryConfigChannelByParam(queryConfigChannelEvt);
		return resp;
	}

	@ServiceAround
	@ApiOperation(value = "新增渠道配置#受理", notes = "")
	@RequestMapping(value = "doAdd", method = RequestMethod.POST)
	public ServerResp doAdd(@RequestBody Message<AddConfigChannelEvt> message) {
		ServerResp resp = new ServerResp();
		try {
			AddConfigChannelEvt addConfigChannelEvt = message.getBody();
			QueryConfigChannelEvt evt = new QueryConfigChannelEvt();
			evt.setChannelCode(addConfigChannelEvt.getChannelCode());
			int hasCount = configChannelService.isHasChannelCode(evt);
			if (hasCount > 0) {
				return resp.error("渠道编码已存在!");
			}
			return resp.success(configChannelService.doAdd(addConfigChannelEvt));
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}
	}

	@ServiceAround
	@ApiOperation(value = "编辑渠道配置#受理", notes = "")
	@RequestMapping(value = "doEdit", method = RequestMethod.POST)
	public ServerResp doEdit(@RequestBody Message<EditConfigChannelEvt> message) {
		ServerResp resp = new ServerResp();
		try {
			EditConfigChannelEvt editConfigChannelEvt = message.getBody();
			QueryConfigChannelEvt evt = new QueryConfigChannelEvt();
			evt.setId(editConfigChannelEvt.getId());
			evt.setChannelCode(editConfigChannelEvt.getChannelCode());
			int hasCount = configChannelService.isHasChannelCode(evt);
			if (hasCount > 0) {
				return resp.error("渠道编码已存在!");
			}
			return resp.success(configChannelService.doEdit(editConfigChannelEvt));
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}
	}

	@ServiceAround
	@ApiOperation(value = "编辑渠道配置#受理", notes = "")
	@RequestMapping(value = "doEditStatus", method = RequestMethod.POST)
	public ServerResp doEditStatus(@RequestBody Message<EditConfigChannelStatusEvt> message) {
		ServerResp resp = new ServerResp();
		try {
			return resp.success(configChannelService.doEditStatus(message.getBody()));
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}
	}

	@ServiceAround
	@ApiOperation(value = "查询所有渠道配置信息#查询", notes = "")
	@RequestMapping(value = "queryAllConfigChannels", method = RequestMethod.POST)
	public ServerResp queryAllConfigChannels(@RequestBody Message<QueryConfigChannelEvt> message) {
		QueryConfigChannelEvt queryConfigChannelEvt = message.getBody();
		PageHelper.startPage(1, 500);
		ServerResp resp = configChannelService.queryAllConfigChannels(queryConfigChannelEvt);
		return resp;
	}

	@ServiceAround
	@ApiOperation(value = "查询渠道配置信息(精确)#查询", notes = "")
	@RequestMapping(value = "queryChannels", method = RequestMethod.POST)
	public ServerResp queryChannels(@RequestBody Message<QueryConfigChannelEvt> message) {
		QueryConfigChannelEvt queryConfigChannelEvt = message.getBody();
		ServerResp resp = configChannelService.queryChannels(queryConfigChannelEvt);
		return resp;
	}

}
