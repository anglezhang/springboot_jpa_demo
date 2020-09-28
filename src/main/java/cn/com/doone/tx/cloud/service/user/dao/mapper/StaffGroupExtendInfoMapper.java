package cn.com.doone.tx.cloud.service.user.dao.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import cn.com.doone.tx.cloud.service.user.bean.StaffGroupExtendInfoBean;
import cn.com.doone.tx.cloud.service.user.dao.provider.StaffGroupExtendInfoProvider;
import cn.com.doone.tx.cloud.service.user.evt.staffGroup.DeleteStaffGroupExtendEvt;


/**
 * yecz
 */
@Mapper
public interface StaffGroupExtendInfoMapper {

	 @SelectProvider(type = StaffGroupExtendInfoProvider.class , method = "queryStaffGroupExtendInfoByParam")
	 List<StaffGroupExtendInfoBean> queryStaffGroupExtendInfoByParam(String sqlWhere, StaffGroupExtendInfoBean queryStaffGroupEvt);


	/**删除扩展信息 状态置D*/
	@Update("update ts_staff_group_extend_info set status='D' where GROUP_ID=#{groupId}")
//	@Delete("delete from ts_staff_group_extend_info  where STAFF_ID=#{staffId}")
	int doDeleteFromExtend(DeleteStaffGroupExtendEvt deleteStaffGroupExtendEvt);

}
