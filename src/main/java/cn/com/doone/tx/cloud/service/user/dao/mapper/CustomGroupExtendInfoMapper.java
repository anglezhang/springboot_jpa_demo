package cn.com.doone.tx.cloud.service.user.dao.mapper;


import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import cn.com.doone.tx.cloud.service.user.bean.CustomGroupExtendInfoBean;
import cn.com.doone.tx.cloud.service.user.dao.provider.CustomGroupExtendInfoProvider;
import cn.com.doone.tx.cloud.service.user.evt.staffGroup.DeleteStaffGroupExtendEvt;


/**
 * @author huangminqiong
 * @version V1.0
 * @Title: CustomGroupExtendInfoMapper
 * @Package cn.com.doone.tx.cloud.user.service.dao.mapper.sys
 * @Description: 自定义用户组扩展Dao
 * @date 2017年4月18日 下午3:46:05
 */
public interface CustomGroupExtendInfoMapper {

	 @SelectProvider(type = CustomGroupExtendInfoProvider.class , method = "queryCustomGroupExtendInfoByParam")
	 List<CustomGroupExtendInfoBean> queryCustomGroupExtendInfoByParam(String sqlWhere, CustomGroupExtendInfoBean queryStaffGroupEvt);

	/**删除扩展信息 状态置D*/
	@Update("update ts_custom_group_extend_info set status='D' where CUSTOM_GROUP_ID=#{groupId}")
//	@Delete("delete from ts_staff_group_extend_info  where STAFF_ID=#{staffId}")
	int doDeleteFromExtend(DeleteStaffGroupExtendEvt deleteStaffGroupExtendEvt);
}
