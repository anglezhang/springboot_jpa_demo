package cn.com.doone.tx.cloud.service.wechat.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.doone.tx.cloud.service.wechat.dao.mapper.WxCorpMapper;
import cn.com.doone.tx.cloud.service.wechat.evt.corp.QueryWxCorpEvt;

@Service
@Transactional
public class WxCorpService {
	
	@Autowired
	private WxCorpMapper wxCorpMapper;
	
	public List<Map<String, Object>> queryWxCorpInfo(QueryWxCorpEvt evt){
		return wxCorpMapper.queryWxCorpInfo(evt);
	}

}
