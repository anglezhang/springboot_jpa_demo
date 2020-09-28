package cn.com.doone.tx.cloud.service.config.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.doone.tx.cloud.service.config.dao.mapper.XssMapper;
import cn.com.doone.tx.cloud.service.config.evt.xss.QueryXssHeadSetEvt;
import cn.com.doone.tx.cloud.service.config.evt.xss.QueryXssRegexEvt;
import cn.com.doone.tx.cloud.service.config.evt.xss.QueryXssRuleEvt;
import cn.com.doone.tx.cloud.service.config.info.XssConfigInfo;
import cn.com.doone.tx.cloud.service.config.info.XssHeadSetInfo;
import cn.com.doone.tx.cloud.service.config.info.XssRegexInfo;
import cn.com.doone.tx.cloud.service.config.info.XssRuleInfo;

@Service
@Transactional
public class XssConfigService {
	
	@Autowired
	private XssMapper xssMapper;

	public List<XssConfigInfo> queryXssConfigList(){
		return xssMapper.queryXssConfigList();
	}
	
	public List<XssHeadSetInfo> queryXssHeadSetList(QueryXssHeadSetEvt evt){
		return xssMapper.queryXssHeadSetList(evt);
	}
	
	public List<XssRuleInfo> queryXssRuleList(QueryXssRuleEvt evt){
		return xssMapper.queryXssRuleList(evt);
	}
	
	
	public List<XssRegexInfo> queryXssRegexList(QueryXssRegexEvt evt){
		return xssMapper.queryXssRegexList(evt);
	}
}
