package cn.com.doone.tx.cloud.service.user.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.doone.tx.cloud.service.user.bean.LoginRuleBean;
import cn.com.doone.tx.cloud.service.user.dao.mapper.LoginRuleMapper;
import cn.com.doone.tx.cloud.service.user.evt.loginrule.AddLoginRuleEvt;
import cn.com.doone.tx.cloud.service.user.evt.loginrule.EditLoginRuleEvt;
import cn.com.doone.tx.cloud.service.user.evt.loginrule.QueryLoginRuleEvt;
import cn.com.doone.tx.cloud.service.user.info.LoginRuleInfo;
import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatRepository;

@Service 
@Transactional
public class LoginRuleService {
    @Autowired
	private LoginRuleMapper loginruleMapper;
	
	@Autowired
    private PlatRepository platRepository;
    
    //查询列表
	public List<Map<String,Object>> queryList(QueryLoginRuleEvt evt){
		return loginruleMapper.queryList(evt);
	}
	
	//新增
    public LoginRuleBean add(AddLoginRuleEvt evt){
        evt.setCreateTime(new Date());
        LoginRuleBean bean = new LoginRuleBean();
        BeanUtils.copyProperties(evt,bean);
        return platRepository.save(bean);
    }
	
    public int queryLoginRuleIsExist(QueryLoginRuleEvt evt){
      return loginruleMapper.queryLoginRuleIsExist(evt);
  }

	public int queryCount(QueryLoginRuleEvt evt) {
		return loginruleMapper.queryCount(evt);
	}

	public int doEdit(EditLoginRuleEvt evt) {
		return loginruleMapper.doEdit(evt);
	}
    
    public LoginRuleInfo queryDetail(QueryLoginRuleEvt evt) {
		return loginruleMapper.queryDetail(evt);
	}
    
    public LoginRuleInfo queryInitPwd(QueryLoginRuleEvt evt) {
		return loginruleMapper.queryInitPwd(evt);
	}
}

