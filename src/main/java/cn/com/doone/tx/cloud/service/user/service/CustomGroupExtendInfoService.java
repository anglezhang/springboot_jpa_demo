package cn.com.doone.tx.cloud.service.user.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.doone.tx.cloud.service.user.bean.CustomGroupExtendInfoBean;
import cn.com.doone.tx.cloud.service.user.dao.mapper.CustomGroupExtendInfoMapper;


/**
 * 扩展用户组的扩展信息服务
 * @author huangminqiong
 * @version V1.0
 * @Title: CustomGroupService
 * @Package cn.com.doone.tx.cloud.user.service.service.sys
 * @Description: 
 * @date 2017年4月7日 上午10:09:43
 */
@Service
@Transactional
public class CustomGroupExtendInfoService {
   
    @Autowired
    private CustomGroupExtendInfoMapper customGroupExtendInfoMapper;

    /**
     * 查询自定义用户组扩展字段
     */
    public List<CustomGroupExtendInfoBean> queryCustomGroupExtendInfoByParam(CustomGroupExtendInfoBean evt){
    	return customGroupExtendInfoMapper.queryCustomGroupExtendInfoByParam(null, evt);
    }

    /**
     * 查询自定义用户组扩展字段
     */
    public List<CustomGroupExtendInfoBean> queryCustomGroupExtendInfoByParam(String sqlWhere, CustomGroupExtendInfoBean evt){
    	return customGroupExtendInfoMapper.queryCustomGroupExtendInfoByParam(sqlWhere, evt);
    }
}
