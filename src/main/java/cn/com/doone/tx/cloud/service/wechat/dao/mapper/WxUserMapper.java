package cn.com.doone.tx.cloud.service.wechat.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.com.doone.tx.cloud.service.wechat.dao.provider.WxUserProvider;
import cn.com.doone.tx.cloud.service.wechat.evt.user.EditWxUserEvt;
import cn.com.doone.tx.cloud.service.wechat.evt.user.QueryWxUserEvt;

public interface WxUserMapper {
	
	@SelectProvider(type = WxUserProvider.class,method = "queryByParam")
	List<Map<String, Object>> queryByParam(QueryWxUserEvt evt);
	
	@SelectProvider(type = WxUserProvider.class , method = "queryUserCount")
    int queryUserCount(QueryWxUserEvt evt);
	
	@SelectProvider(type = WxUserProvider.class , method = "queryUserIsExist")
    int queryUserIsExist(QueryWxUserEvt evt);
	
	@UpdateProvider(type = WxUserProvider.class , method = "doEdit")
    int doEdit(EditWxUserEvt evt);


}
