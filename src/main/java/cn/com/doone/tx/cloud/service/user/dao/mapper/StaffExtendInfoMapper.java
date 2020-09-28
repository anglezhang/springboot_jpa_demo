package cn.com.doone.tx.cloud.service.user.dao.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import cn.com.doone.tx.cloud.service.user.bean.StaffExtendInfoBean;
import cn.com.doone.tx.cloud.service.user.dao.provider.StaffExtendInfoProvider;

/**
 * yecz
 */
@Mapper
public interface StaffExtendInfoMapper {

	 @SelectProvider(type = StaffExtendInfoProvider.class , method = "queryStaffExtendInfoByParam")
	 List<StaffExtendInfoBean> queryStaffExtendInfoByParam(String sqlWhere, StaffExtendInfoBean queryStaffEvt);


	/**删除扩展信息 状态置D*/
//	@Update("update ts_staff_extend_info set status='D' where STAFF_ID=#{staffId}")
////	@Delete("delete from ts_staff_extend_info  where STAFF_ID=#{staffId}")
//	int doDeleteFromExtend(DeleteStaffExtendEvt deleteStaffExtendEvt);
}
