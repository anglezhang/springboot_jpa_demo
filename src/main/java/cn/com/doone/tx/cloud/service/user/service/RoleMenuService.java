package cn.com.doone.tx.cloud.service.user.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.doone.tx.cloud.service.user.bean.RoleMenuBean;
import cn.com.doone.tx.cloud.service.user.dao.mapper.RoleMenuMapper;
import cn.com.doone.tx.cloud.service.user.evt.menuInfo.QueryPermissionMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.rolemenu.AddRoleMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.rolemenu.DeleteRoleMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.rolemenu.QueryRoleMenuEvt;
import cn.com.doone.tx.cloud.service.user.info.RoleMenuInfo;
import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatRepository;
import cn.com.doone.tx.cloud.tool.login.bean.MenuInfo;

/**
 * Created by yecz on 2017/2/8 0008.
 */
@Service("sysRoleMenuService")
@Transactional
public class RoleMenuService {


    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private PlatRepository platRepository;

    //查询角色菜单关系
    //@Cacheable(value = "roleMenuMapper.queryRoleMenuByParam",keyGenerator = "defaultKeyGenerator")
    public List<RoleMenuInfo> queryRoleMenuByParam(QueryRoleMenuEvt evt){
        return roleMenuMapper.queryRoleMenuByParam(evt);
    }

    //增加角色菜单关系
    public RoleMenuBean add(AddRoleMenuEvt evt){
        RoleMenuBean bean = new RoleMenuBean();
        BeanUtils.copyProperties(evt,bean);
//        System.out.println("bean:"+bean.getRoleId());
        return platRepository.save(bean);
    }
    //删除角色菜单关系
    public int doDelete(DeleteRoleMenuEvt evt){
        return roleMenuMapper.doDelete(evt);
    }

    //查询角色菜单、按钮信息
    public List<MenuInfo> queryRoleMenuInfos(QueryRoleMenuEvt evt){
        return roleMenuMapper.queryRoleMenuInfos(evt);
    }
    
    public List<MenuInfo> queryPermissionMenu(QueryPermissionMenuEvt evt){
    	return roleMenuMapper.queryPermissionMenu(evt);
    }
}
