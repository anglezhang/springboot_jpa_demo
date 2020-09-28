package cn.com.doone.tx.cloud.service.wechat.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.doone.tx.cloud.service.wechat.dao.mapper.WxAgentMapper;
import cn.com.doone.tx.cloud.service.wechat.evt.agent.QueryWxAgentEvt;

@Service
@Transactional
public class WxAgentService {
	
	@Autowired
	private WxAgentMapper wxAgentMapper;
	
	public List<Map<String, Object>> queryWxAgentInfo(QueryWxAgentEvt evt){
		return wxAgentMapper.queryWxAgentInfo(evt);
	}

}
