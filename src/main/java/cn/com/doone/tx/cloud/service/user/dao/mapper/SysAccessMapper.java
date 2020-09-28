package cn.com.doone.tx.cloud.service.user.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.com.doone.tx.cloud.service.user.dao.provider.SysAccessProvider;
import cn.com.doone.tx.cloud.service.user.evt.menuInfo.QueryPermissionMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.sysaccess.EditSysAccessEvt;
import cn.com.doone.tx.cloud.service.user.evt.sysaccess.QuerySysAccessEvt;
import cn.com.doone.tx.cloud.service.user.info.SysAccessInfo;




public interface SysAccessMapper {

	@SelectProvider(type = SysAccessProvider.class , method = "queryDataCount")
    int queryDataCount(QuerySysAccessEvt paramEvt);
	
	@SelectProvider(type = SysAccessProvider.class , method = "queryDataList")
	List<SysAccessInfo> queryDataList(QuerySysAccessEvt evt);
	
	@SelectProvider(type = SysAccessProvider.class , method = "querySysAccessIsExist")
	int queryCustomerIsExist(QuerySysAccessEvt evt);
	
	@UpdateProvider(type = SysAccessProvider.class , method = "doEdit")
	int doEdit(EditSysAccessEvt evt);
	
	@UpdateProvider(type = SysAccessProvider.class , method = "doDelete")
	int doDelete(EditSysAccessEvt paramEvt);
	
	@UpdateProvider(type = SysAccessProvider.class , method = "doNenable")
	int doNenable(EditSysAccessEvt paramEvt);
	
	@SelectProvider(type = SysAccessProvider.class , method = "queryPermissionMenu")
	List<Map<String,Object>> queryPermissionMenu(QueryPermissionMenuEvt evt);
	
}
