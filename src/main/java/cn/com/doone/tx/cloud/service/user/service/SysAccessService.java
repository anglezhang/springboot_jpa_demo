package cn.com.doone.tx.cloud.service.user.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.doone.tx.cloud.service.user.bean.SysAccessBean;
import cn.com.doone.tx.cloud.service.user.dao.mapper.SysAccessMapper;
import cn.com.doone.tx.cloud.service.user.evt.menuInfo.QueryPermissionMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.sysaccess.AddSysAccessEvt;
import cn.com.doone.tx.cloud.service.user.evt.sysaccess.EditSysAccessEvt;
import cn.com.doone.tx.cloud.service.user.evt.sysaccess.QuerySysAccessEvt;
import cn.com.doone.tx.cloud.service.user.info.SysAccessInfo;
import cn.com.doone.tx.cloud.tool.common.invoke.BaseService;
import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatRepository;

@Service
@Transactional
public class SysAccessService extends BaseService {

	@Autowired
	private SysAccessMapper sysAccessMapper;
	
	@Autowired
    private PlatRepository platRepository;
	
	public List<Map<String,Object>> queryPermissionMenu(QueryPermissionMenuEvt evt){
		return sysAccessMapper.queryPermissionMenu(evt);
	}
	
	//查询列表
	public List<SysAccessInfo> queryDataList(QuerySysAccessEvt evt){
		return sysAccessMapper.queryDataList(evt);
	}
	
	//新增
    public SysAccessBean add(AddSysAccessEvt evt){
        evt.setCreateTime(new Date());
        SysAccessBean bean = new SysAccessBean();
        BeanUtils.copyProperties(evt,bean);
        return platRepository.save(bean);
    }
	
    //查询是否存在
    public int querySysAccessIsExist(QuerySysAccessEvt evt){
      return sysAccessMapper.queryCustomerIsExist(evt);
    }

	public int queryDataCount(QuerySysAccessEvt evt) {
		return sysAccessMapper.queryDataCount(evt);
	}

	public int doEdit(EditSysAccessEvt evt) {
		return sysAccessMapper.doEdit(evt);
	}
	
	public int doDelete(EditSysAccessEvt paramEvt) {
		return sysAccessMapper.doDelete(paramEvt);
	}

	public int doNenable(EditSysAccessEvt paramEvt) {
		return sysAccessMapper.doNenable(paramEvt);
	}
	
}
