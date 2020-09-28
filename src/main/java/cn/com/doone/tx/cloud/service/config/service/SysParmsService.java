package cn.com.doone.tx.cloud.service.config.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.doone.tx.cloud.service.config.bean.SysParmsBean;
import cn.com.doone.tx.cloud.service.config.dao.mapper.SysParmsMapper;
import cn.com.doone.tx.cloud.service.config.evt.sysParms.AddSysParmEvt;
import cn.com.doone.tx.cloud.service.config.evt.sysParms.DeleteSysParmEvt;
import cn.com.doone.tx.cloud.service.config.evt.sysParms.EditSysParmEvt;
import cn.com.doone.tx.cloud.service.config.evt.sysParms.QuerySysParmEvt;
import cn.com.doone.tx.cloud.service.config.info.SysParmsInfo;
import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatRepository;

@Service("sysParmsService")
@Transactional
public class SysParmsService {

    @Autowired
    private SysParmsMapper sysParmsMapper;

    @Autowired
    private PlatRepository platRespository;

    //查询系统参数数量
    public int querySysParmsCount(QuerySysParmEvt evt){
        return sysParmsMapper.querySysParmsCount(evt);
    }

    //查询系统参数
    public List<SysParmsInfo> querySysParmsByParam(QuerySysParmEvt evt){
        return sysParmsMapper.querySysParmsByParam(evt);
    }

    //增加系统参数
    public SysParmsBean add(AddSysParmEvt evt){
        SysParmsBean bean = new SysParmsBean();
        BeanUtils.copyProperties(evt,bean);
//        System.out.println("bean:"+bean.getCreateTime());
        return platRespository.save(bean);
    }

//    public SysParmsBean doEdit(EditSysParmEvt evt){
//        SysParmsBean bean = new SysParmsBean();
//        BeanUtils.copyProperties(evt,bean);
////        System.out.println("bean:"+bean.getRoleName());
//        return platRespository.save(bean);
//    }

    public int doEdit(EditSysParmEvt evt){
        return sysParmsMapper.updateSysParm(evt);
    }

    public int doDelete(DeleteSysParmEvt evt){
        return sysParmsMapper.doDelete(evt);
    }

    public int querySysParmsIsExist(QuerySysParmEvt evt){
//        System.out.println(evt.getConfigType());
        return sysParmsMapper.querySysParmsIsExist(evt);
    }



}