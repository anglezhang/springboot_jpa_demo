package cn.com.doone.tx.cloud.service.user.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.doone.tx.cloud.service.user.bean.RoleInfoBean;
import cn.com.doone.tx.cloud.service.user.dao.mapper.RoleMapper;
import cn.com.doone.tx.cloud.service.user.evt.StaffRole.DeleteStaffRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.StaffRole.QueryStaffRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.groupRole.DeleteGroupRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.role.AddRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.role.DeleteRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.role.EditRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.role.QueryRoleEvt;
import cn.com.doone.tx.cloud.service.user.info.RoleInfo;
import cn.com.doone.tx.cloud.tool.common.enums.NormalStatus;
import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatRepository;

/**
 * Created by Administrator on 2017/2/8 0008.
 */
@Service("RoleService")
@Transactional
public class RoleService {


    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PlatRepository platRepository;

    //@Cacheable(value = "RoleMapper.queryRoleCount",keyGenerator = "defaultKeyGenerator")
    public int queryRoleCount(QueryRoleEvt evt){
        return roleMapper.queryRoleCount(evt);
    }

    //查询角色
    //@Cacheable(value = "RoleMapper.queryRoleByParam",keyGenerator = "defaultKeyGenerator")
    public List<RoleInfo> queryRoleByParam(QueryRoleEvt evt){
        return roleMapper.queryRoleByParam(evt);
    }

    //增加角色
    public RoleInfoBean add(AddRoleEvt evt){
        evt.setCreateTime(new Date());
        RoleInfoBean bean = new RoleInfoBean();
        BeanUtils.copyProperties(evt,bean);
        return platRepository.save(bean);
    }

    public int doEdit(EditRoleEvt evt){
        return roleMapper.doEdit(evt);
    }

    public int doDelete(DeleteRoleEvt evt){
        //删除菜单(修改状态为D)
        evt.setStatus(NormalStatus.D.name());
        return roleMapper.doDelete(evt);
    }

    public int queryRoleIsExist(QueryRoleEvt evt){
//        System.out.println(evt.getId()+evt.getRoleName());
        return roleMapper.queryRoleIsExist(evt);
    }

    //删除账号角色绑定关系
    public int doDeleteStaffRole(DeleteStaffRoleEvt evt){
        return roleMapper.deleteStaffRole(evt);
    }

    //删除用户组角色绑定关系
    public int doDeleteGroupRole(DeleteGroupRoleEvt evt){
        return roleMapper.deleteGroupRole(evt);
    }

    //查询角色和用户绑定关系的总条数
	public int queryStaffRoleCount(QueryStaffRoleEvt queryStaffRoleEvt) {
		return roleMapper.queryStaffRoleCount(queryStaffRoleEvt);
	}


}
