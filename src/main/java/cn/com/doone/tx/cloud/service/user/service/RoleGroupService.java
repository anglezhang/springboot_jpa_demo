package cn.com.doone.tx.cloud.service.user.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.doone.tx.cloud.service.user.bean.RoleGroupBean;
import cn.com.doone.tx.cloud.service.user.dao.mapper.RoleGroupMapper;
import cn.com.doone.tx.cloud.service.user.evt.roleGroup.AddRoleGroupEvt;
import cn.com.doone.tx.cloud.service.user.evt.roleGroup.DeleteRoleGroupEvt;
import cn.com.doone.tx.cloud.service.user.evt.roleGroup.QueryRoleGroupEvt;
import cn.com.doone.tx.cloud.service.user.info.RoleGroupInfo;
import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatRepository;

/**
 * Created by yecz on 2017/2/8 0008.
 */
@Service("RoleGroupService")
@Transactional
public class RoleGroupService {

    @Autowired
    private RoleGroupMapper roleGroupMapper;

    @Autowired
    private PlatRepository platRepository;

    //@Cacheable(value = "roleGroupMapper.queryRoleGroupByParam",keyGenerator = "defaultKeyGenerator")
    public List<RoleGroupInfo> queryRoleGroupByParam(QueryRoleGroupEvt evt){
        return roleGroupMapper.queryRoleGroupByParam(evt);
    }
    //添加角色用户组关系
    public RoleGroupBean doAdd(AddRoleGroupEvt evt){
        evt.setCreateTime(new Date());//创建时间
        RoleGroupBean bean = new RoleGroupBean();
        BeanUtils.copyProperties(evt,bean);
        return platRepository.save(bean);
    }

    public int doDelete(DeleteRoleGroupEvt evt){
        return roleGroupMapper.doDelete(evt);
    }

}
