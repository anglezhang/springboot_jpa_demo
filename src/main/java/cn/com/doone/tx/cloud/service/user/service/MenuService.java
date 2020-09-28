package cn.com.doone.tx.cloud.service.user.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.doone.tx.cloud.service.user.bean.MenuInfoBean;
import cn.com.doone.tx.cloud.service.user.dao.mapper.MenuMapper;
import cn.com.doone.tx.cloud.service.user.dao.mapper.RoleMenuMapper;
import cn.com.doone.tx.cloud.service.user.evt.menu.AddMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.menu.DeleteMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.menu.EditMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.menu.QueryMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.rolemenu.DeleteRoleMenuEvt;
import cn.com.doone.tx.cloud.service.user.evt.staff.QueryStaffMenuEvt;
import cn.com.doone.tx.cloud.tool.common.enums.NormalStatus;
import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatRepository;
import cn.com.doone.tx.cloud.tool.login.bean.MenuInfo;

/**
 * Created by liujx on 2017/2/13 0013.
 */
@Service
@Transactional
public class MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private PlatRepository platRepository;

   // @Cacheable(value = "MenuService.queryCount",keyGenerator = "defaultKeyGenerator")
    public int queryCount(QueryMenuEvt evt){
        return menuMapper.queryMenuCount(evt);
    }

    //@Cacheable(value = "MenuService.queryByParam",keyGenerator = "defaultKeyGenerator")
    public List<MenuInfo> queryByParam(QueryMenuEvt evt){
    	evt.startPage();
        return menuMapper.queryMenuByParam(evt);
    }
    
    public MenuInfo queryDetail(QueryMenuEvt evt){
    	return menuMapper.queryDetail(evt);
    }

    public int queryCodeIsExist(QueryMenuEvt evt){
        return menuMapper.queryCodeIsExist(evt);
    }

    public int queryNameIsExist(QueryMenuEvt evt){
        return menuMapper.queryNameIsExist(evt);
    }

    public MenuInfoBean doAdd(AddMenuEvt evt){
        /** 查询当前目录底下的菜单数 */
        QueryMenuEvt evt1 = new QueryMenuEvt();
        evt1.setParentId(evt.getParentId());
        int cnt  = queryCount(evt1);
        evt.setSort(++cnt);
        evt.setCreateTime(new Date());
        evt.setUpdateTime(new Date());
        MenuInfoBean bean = new MenuInfoBean();
        BeanUtils.copyProperties(evt,bean);
        bean = platRepository.save(bean);
        return bean;
    }

    public int doEdit(EditMenuEvt evt){
        int n = menuMapper.doEdit(evt);
        return n;
    }

    public int  updateStatus (EditMenuEvt evt){
        int n = menuMapper.updateStatus(evt);
        return n;
    }

    public int doDelete(DeleteMenuEvt evt){
        //先删除角色菜单关系
        DeleteRoleMenuEvt deleteRoleMenuEvt = new DeleteRoleMenuEvt();
        deleteRoleMenuEvt.setMenuId(evt.getId());
        roleMenuMapper.doDeleteByMenuId(deleteRoleMenuEvt);
        //删除菜单(修改状态为D)
        evt.setStatus(NormalStatus.D.name());
        int cnt = menuMapper.doDelete(evt);
        return cnt;
    }

    //@Cacheable(value = "MenuService.tree",keyGenerator = "defaultKeyGenerator")
    public List<MenuInfo> tree(QueryMenuEvt evt){
        return menuMapper.tree(evt);
    }

    public List<MenuInfo> userMenuTree(QueryStaffMenuEvt evt){
        return menuMapper.userMenuTree(evt);
    }
    
    public List<MenuInfo> queryAll(QueryMenuEvt evt){
    	return menuMapper.queryAll(evt);
    }

}
