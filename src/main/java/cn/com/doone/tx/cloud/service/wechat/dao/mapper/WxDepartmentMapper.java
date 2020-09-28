package cn.com.doone.tx.cloud.service.wechat.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.com.doone.tx.cloud.service.wechat.dao.provider.WxDepartmentProvider;
import cn.com.doone.tx.cloud.service.wechat.evt.department.EditWxDepartmentEvt;
import cn.com.doone.tx.cloud.service.wechat.evt.department.QueryWxDepartmentEvt;

public interface WxDepartmentMapper {
	
	@SelectProvider(type = WxDepartmentProvider.class,method = "queryByParam")
	List<Map<String, Object>> queryByParam(QueryWxDepartmentEvt evt);
	
	@SelectProvider(type = WxDepartmentProvider.class , method = "queryDepartmentIsExist")
    int queryDepartmentIsExist(QueryWxDepartmentEvt evt);
	
	@UpdateProvider(type = WxDepartmentProvider.class , method = "doEdit")
    int doEdit(EditWxDepartmentEvt evt);


}
