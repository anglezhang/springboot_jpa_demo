

package cn.com.doone.tx.cloud.service.user.dao.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.com.doone.tx.cloud.service.user.dao.provider.LoginRuleProvider;
import cn.com.doone.tx.cloud.service.user.evt.loginrule.EditLoginRuleEvt;
import cn.com.doone.tx.cloud.service.user.evt.loginrule.QueryLoginRuleEvt;
import cn.com.doone.tx.cloud.service.user.info.LoginRuleInfo;

public interface LoginRuleMapper {
    
    @SelectProvider(type = LoginRuleProvider.class , method = "queryCount")
    int queryCount(QueryLoginRuleEvt paramEvt);
	
	@SelectProvider(type = LoginRuleProvider.class , method = "queryList")
	List<Map<String,Object>> queryList(QueryLoginRuleEvt evt);
	
	@UpdateProvider(type = LoginRuleProvider.class , method = "doEdit")
	int doEdit(EditLoginRuleEvt evt);

	@SelectProvider(type = LoginRuleProvider.class , method = "queryLoginRuleIsExist")
	int queryLoginRuleIsExist(QueryLoginRuleEvt evt);
 
    @SelectProvider(type = LoginRuleProvider.class , method = "queryDetail")
	LoginRuleInfo queryDetail(QueryLoginRuleEvt evt);
    
    @SelectProvider(type = LoginRuleProvider.class , method = "queryInitPwd")
    LoginRuleInfo queryInitPwd(QueryLoginRuleEvt evt);
    
}

