package cn.com.doone.tx.cloud.service.config.dao.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import cn.com.doone.tx.cloud.service.config.evt.xss.QueryXssHeadSetEvt;
import cn.com.doone.tx.cloud.service.config.evt.xss.QueryXssRegexEvt;
import cn.com.doone.tx.cloud.service.config.evt.xss.QueryXssRuleEvt;
import cn.com.doone.tx.cloud.service.config.info.XssConfigInfo;
import cn.com.doone.tx.cloud.service.config.info.XssHeadSetInfo;
import cn.com.doone.tx.cloud.service.config.info.XssRegexInfo;
import cn.com.doone.tx.cloud.service.config.info.XssRuleInfo;

@Mapper
public interface XssMapper {
	
	@Select("SELECT * FROM td_xss_config t WHERE t.status='E'")
	public List<XssConfigInfo> queryXssConfigList();

	@Select("SELECT t.* FROM td_xss_headset t WHERE t.STATUS='E' AND t.XSS_CONFIG_ID=#{xssConfigId}")
	public List<XssHeadSetInfo> queryXssHeadSetList(QueryXssHeadSetEvt evt);
	
	@Select("SELECT t.* FROM td_xss_rule t WHERE t.STATUS='E' AND t.XSS_CONFIG_ID=#{xssConfigId}")
	public List<XssRuleInfo> queryXssRuleList(QueryXssRuleEvt evt);
	
	@Select("SELECT t.* FROM td_xss_regex t WHERE t.STATUS='E' AND t.XSS_RULE_ID=#{xssRuleId}")
	public List<XssRegexInfo> queryXssRegexList(QueryXssRegexEvt evt);
}
