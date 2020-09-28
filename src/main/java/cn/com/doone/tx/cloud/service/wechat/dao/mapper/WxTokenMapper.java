package cn.com.doone.tx.cloud.service.wechat.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.com.doone.tx.cloud.service.wechat.dao.provider.WxTokenProvider;
import cn.com.doone.tx.cloud.service.wechat.evt.token.EditWxTokenEvt;
import cn.com.doone.tx.cloud.service.wechat.evt.token.QueryWxTokenEvt;

public interface WxTokenMapper {
	
	@SelectProvider(type = WxTokenProvider.class,method = "queryWxTokenInfo")
	List<Map<String, Object>> queryWxTokenInfo(QueryWxTokenEvt evt);

	@UpdateProvider(type = WxTokenProvider.class,method = "doEdit")
	int doEdit(EditWxTokenEvt evt);
}
