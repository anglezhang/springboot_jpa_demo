package cn.com.doone.tx.cloud.service.wechat.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.doone.tx.cloud.service.wechat.bean.WxUserBean;
import cn.com.doone.tx.cloud.service.wechat.dao.mapper.WxUserMapper;
import cn.com.doone.tx.cloud.service.wechat.evt.user.AddWxUserEvt;
import cn.com.doone.tx.cloud.service.wechat.evt.user.EditWxUserEvt;
import cn.com.doone.tx.cloud.service.wechat.evt.user.QueryWxUserEvt;
import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatRepository;

@Service
@Transactional
public class WxUserService {
	
	@Autowired
	private WxUserMapper wxUserMapper;
	
	@Autowired
    private PlatRepository platRepository;
	
	public List<Map<String, Object>> queryByParam(QueryWxUserEvt evt){
		return wxUserMapper.queryByParam(evt);
	}
	
	public int queryUserCount(QueryWxUserEvt evt){
		return wxUserMapper.queryUserCount(evt);
	}
	
	public int queryUserIsExist(QueryWxUserEvt evt){
		return wxUserMapper.queryUserIsExist(evt);
	}

	public WxUserBean add(AddWxUserEvt evt){
        evt.setCreateTime(new Date());
        WxUserBean bean = new WxUserBean();
        BeanUtils.copyProperties(evt,bean);
        return platRepository.save(bean);
    }
	
	public int doEdit(EditWxUserEvt evt){
        return wxUserMapper.doEdit(evt);
    }
}
