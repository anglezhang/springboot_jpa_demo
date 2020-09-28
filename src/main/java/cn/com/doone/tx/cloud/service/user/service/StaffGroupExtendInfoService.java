package cn.com.doone.tx.cloud.service.user.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.doone.tx.cloud.service.user.bean.StaffGroupExtendInfoBean;
import cn.com.doone.tx.cloud.service.user.dao.mapper.StaffGroupExtendInfoMapper;


/**
 * 用户组扩展信息服务
 * ycz
 */
@Service
@Transactional
public class StaffGroupExtendInfoService {
   
    @Autowired
    private StaffGroupExtendInfoMapper staffGroupExtendInfoMapper;

    /**
     * 查询用户组扩展字段
     */
    public List<StaffGroupExtendInfoBean> queryStaffGroupExtendInfoByParam(StaffGroupExtendInfoBean evt){
    	return staffGroupExtendInfoMapper.queryStaffGroupExtendInfoByParam(null, evt);
    }

    /**
     * 查询自定义用户组扩展字段
     */
    public List<StaffGroupExtendInfoBean> queryStaffGroupExtendInfoByParam(String sqlWhere, StaffGroupExtendInfoBean evt){
        return staffGroupExtendInfoMapper.queryStaffGroupExtendInfoByParam(sqlWhere, evt);
    }
}
