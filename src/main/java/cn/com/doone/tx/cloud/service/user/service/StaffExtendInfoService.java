package cn.com.doone.tx.cloud.service.user.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.doone.tx.cloud.service.user.bean.StaffExtendInfoBean;
import cn.com.doone.tx.cloud.service.user.dao.mapper.StaffExtendInfoMapper;

/**
 * 用户扩展信息服务
 * ycz
 */
@Service
@Transactional
public class StaffExtendInfoService {
   
    @Autowired
    private StaffExtendInfoMapper staffExtendInfoMapper;

    /**
     * 查询用户组扩展字段
     */
    public List<StaffExtendInfoBean> queryStaffExtendInfoByParam(StaffExtendInfoBean evt){
    	return staffExtendInfoMapper.queryStaffExtendInfoByParam(null, evt);
    }

    /**
     * 查询自定义用户组扩展字段
     */
    public List<StaffExtendInfoBean> queryStaffExtendInfoByParam(String sqlWhere, StaffExtendInfoBean evt){
        return staffExtendInfoMapper.queryStaffExtendInfoByParam(sqlWhere, evt);
    }
}
