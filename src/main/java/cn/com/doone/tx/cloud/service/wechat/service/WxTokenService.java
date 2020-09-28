package cn.com.doone.tx.cloud.service.wechat.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.doone.tx.cloud.service.wechat.bean.WxTokenBean;
import cn.com.doone.tx.cloud.service.wechat.dao.mapper.WxTokenMapper;
import cn.com.doone.tx.cloud.service.wechat.evt.token.EditWxTokenEvt;
import cn.com.doone.tx.cloud.service.wechat.evt.token.QueryWxTokenEvt;
import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatRepository;

@Transactional
@Service
public class WxTokenService {
	
	private static final Logger LOGGER = Logger.getLogger(WxTokenService.class);

	@Autowired
	private WxTokenMapper wxTokenMapper;
	
	@Autowired
    private PlatRepository platRepository;
	
	public int updateToken(EditWxTokenEvt evt) {
		int res = 0;
		try {
			QueryWxTokenEvt queryEvt = new QueryWxTokenEvt();
			queryEvt.setWxCorpId(evt.getWxCorpId());
			queryEvt.setWxAgentId(evt.getWxAgentId());
			queryEvt.setTokenType(evt.getTokenType());
			queryEvt.setStatus("E");
			List<Map<String, Object>> list = wxTokenMapper.queryWxTokenInfo(queryEvt);
			if(list != null && list.size() > 0) {
				evt.setId(Long.parseLong(String.valueOf(list.get(0).get("id"))));
				res = wxTokenMapper.doEdit(evt);
			}else {
				WxTokenBean bean = new WxTokenBean();
				BeanUtils.copyProperties(evt, bean);
				bean = platRepository.save(bean);
				res = 1;
			}
			
		}catch(Exception e) {
			LOGGER.error("更新token异常" + e.getMessage() , e);
		}
		return res;
	}

}
