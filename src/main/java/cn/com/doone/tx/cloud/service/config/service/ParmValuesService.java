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

@Service("ParmValuesService")
@Transactional
public class ParmValuesService {

    @Autowired
    private SysParmsMapper sysParmsMapper;

    @Autowired
    private PlatRepository platRespository;

    //查询系统参数value数量
    public int queryParmValuesCount(QuerySysParmEvt evt){
        return sysParmsMapper.queryParmValuesCount(evt);
    }

    //查询系统参数value
    public List<SysParmsInfo> queryParmValuesByParam(QuerySysParmEvt evt){
        return sysParmsMapper.queryParmValuesByParam(evt);
    }
    //增加系统参数value
    public SysParmsBean add(AddSysParmEvt evt){
        SysParmsBean bean = new SysParmsBean();
        BeanUtils.copyProperties(evt,bean);
//        System.out.println("bean:"+bean.getCreateTime());
        return platRespository.save(bean);
    }

    public SysParmsBean doEdit(EditSysParmEvt evt){
        SysParmsBean bean = new SysParmsBean();
        BeanUtils.copyProperties(evt,bean);
//        System.out.println("bean:"+bean.getRoleName());
        return platRespository.save(bean);
    }

    public int doDelete(DeleteSysParmEvt evt){
        return sysParmsMapper.doDeleteValue(evt);
    }


    //查询系统参数key集合
    public List<String>  queryKeys(){
        return sysParmsMapper.queryKeys();
    }
    //根据key查询value集合
    public List<String>  queryKeyValues(String configKey){
        return sysParmsMapper.queryKeyValues(configKey);
    }
    
  //根据key查询value集合
    public List<SysParmsInfo>  queryKeyValue(QuerySysParmEvt evt){
        return sysParmsMapper.queryKeyValue( evt);
    }
}

