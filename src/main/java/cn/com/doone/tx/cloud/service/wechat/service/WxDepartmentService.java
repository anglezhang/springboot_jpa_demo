package cn.com.doone.tx.cloud.service.wechat.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.doone.tx.cloud.service.wechat.bean.WxDepartmentBean;
import cn.com.doone.tx.cloud.service.wechat.dao.mapper.WxDepartmentMapper;
import cn.com.doone.tx.cloud.service.wechat.evt.department.AddWxDepartmentEvt;
import cn.com.doone.tx.cloud.service.wechat.evt.department.EditWxDepartmentEvt;
import cn.com.doone.tx.cloud.service.wechat.evt.department.QueryWxDepartmentEvt;
import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatRepository;

@Service
@Transactional
public class WxDepartmentService {
	
	@Autowired
	private WxDepartmentMapper wxDepartmentMapper;
	
	@Autowired
    private PlatRepository platRepository;
	
	public List<Map<String, Object>> queryByParam(QueryWxDepartmentEvt evt){
		return wxDepartmentMapper.queryByParam(evt);
	}
	
	public WxDepartmentBean add(AddWxDepartmentEvt evt){
        evt.setCreateTime(new Date());
        WxDepartmentBean bean = new WxDepartmentBean();
        BeanUtils.copyProperties(evt,bean);
        return platRepository.save(bean);
    }
	
	public int doEdit(EditWxDepartmentEvt evt){
        return wxDepartmentMapper.doEdit(evt);
    }
	public int queryDepartmentIsExist(QueryWxDepartmentEvt evt){
		return wxDepartmentMapper.queryDepartmentIsExist(evt);
	}


}
