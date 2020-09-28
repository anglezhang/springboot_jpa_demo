package cn.com.doone.tx.cloud.service.wechat.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;

import cn.com.doone.tx.cloud.service.wechat.dao.provider.WxAgentProvider;
import cn.com.doone.tx.cloud.service.wechat.evt.agent.QueryWxAgentEvt;

public interface WxAgentMapper {
	
	@SelectProvider(type = WxAgentProvider.class,method = "queryWxAgentInfo")
	List<Map<String, Object>> queryWxAgentInfo(QueryWxAgentEvt evt);


}
