package cn.com.doone.tx.cloud.service.user.dao.mapper;


import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.com.doone.tx.cloud.service.user.dao.provider.StaffRegionProvider;
import cn.com.doone.tx.cloud.service.user.evt.staffRegion.EditStaffRegionEvt;
import cn.com.doone.tx.cloud.service.user.evt.staffRegion.QueryStaffRegionEvt;
import cn.com.doone.tx.cloud.service.user.info.StaffRegionInfo;


public interface StaffRegionMapper {

	@SelectProvider(type = StaffRegionProvider.class, method = "queryByParam")
	List<StaffRegionInfo> queryByParam(QueryStaffRegionEvt evt);
	
	
	@UpdateProvider(type = StaffRegionProvider.class, method = "doOut")
	int doOut(EditStaffRegionEvt evt);
	
}
