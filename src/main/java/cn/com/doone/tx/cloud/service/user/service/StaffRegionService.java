package cn.com.doone.tx.cloud.service.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.doone.tx.cloud.service.user.dao.mapper.StaffRegionMapper;
import cn.com.doone.tx.cloud.service.user.evt.staffRegion.QueryStaffRegionEvt;
import cn.com.doone.tx.cloud.service.user.info.StaffRegionInfo;
import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatRepository;

@Service("staffRegionService")
@Transactional
public class StaffRegionService {
	
	@Autowired
	private StaffRegionMapper staffRegionMapper;
	
	@Autowired
    private PlatRepository platRepository;
	
	public List<StaffRegionInfo> queryByParam(QueryStaffRegionEvt evt){
		return staffRegionMapper.queryByParam(evt);
	}

}
