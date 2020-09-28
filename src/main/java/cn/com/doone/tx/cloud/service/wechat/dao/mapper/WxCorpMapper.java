package cn.com.doone.tx.cloud.service.wechat.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;

import cn.com.doone.tx.cloud.service.wechat.dao.provider.WxCorpProvider;
import cn.com.doone.tx.cloud.service.wechat.evt.corp.QueryWxCorpEvt;

public interface WxCorpMapper {
	
	@SelectProvider(type = WxCorpProvider.class,method = "queryWxCorpInfo")
	List<Map<String, Object>> queryWxCorpInfo(QueryWxCorpEvt evt);

}
