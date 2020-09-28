package cn.com.doone.tx.cloud.service.config.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.doone.tx.cloud.service.config.bean.ConfigChannelBean;
import cn.com.doone.tx.cloud.service.config.dao.mapper.ConfigChannelMapper;
import cn.com.doone.tx.cloud.service.config.evt.channel.AddConfigChannelEvt;
import cn.com.doone.tx.cloud.service.config.evt.channel.EditConfigChannelEvt;
import cn.com.doone.tx.cloud.service.config.evt.channel.EditConfigChannelStatusEvt;
import cn.com.doone.tx.cloud.service.config.evt.channel.QueryConfigChannelEvt;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatRepository;

@Service("configChannelService")
@Transactional
public class ConfigChannelService {

    @Autowired
    private ConfigChannelMapper configChannelMapper;

    @Autowired
    private PlatRepository platRepository;

    public ServerResp queryChannelCount(QueryConfigChannelEvt queryConfigChannelEvt){
        ServerResp resp=new ServerResp();
        int i = configChannelMapper.queryChannelCount(queryConfigChannelEvt);
        resp.getHead().setRespCode(0);
        resp.setBody(i);
        return resp;
    }

    public ServerResp queryConfigChannelByParam(QueryConfigChannelEvt queryConfigChannelEvt){
        ServerResp resp=new ServerResp();
        List<ConfigChannelBean> channels = configChannelMapper.queryConfigChannelByParam(queryConfigChannelEvt);
        resp.getHead().setRespCode(0);
        resp.setBody(channels);
        return resp;
    }

    public ConfigChannelBean doAdd(AddConfigChannelEvt addConfigChannelEvt) throws InvocationTargetException, IllegalAccessException {
        ConfigChannelBean configChannel=new ConfigChannelBean();
        BeanUtils.copyProperties(addConfigChannelEvt,configChannel);
        configChannel.setStatus("E");
        configChannel.setCreateTime(new Date());
        configChannel.setUpdateTime(new Date());
        return platRepository.save(configChannel);
    }

    public ServerResp doEdit(EditConfigChannelEvt editConfigChannelEvt) throws Exception{
        ServerResp resp=new ServerResp();
        int i = configChannelMapper.doEdit(editConfigChannelEvt);
        if(i>0){
            QueryConfigChannelEvt queryConfigChannelEvt = new QueryConfigChannelEvt();
            queryConfigChannelEvt.setParentId(editConfigChannelEvt.getId());
            List<ConfigChannelBean> channelBeanList = configChannelMapper.queryConfigChannelByParam(queryConfigChannelEvt);
            if(channelBeanList!=null && channelBeanList.size()>0){
                for(ConfigChannelBean bean : channelBeanList){
                    editConfigChannelEvt.setId(bean.getId());
                    editConfigChannelEvt.setParentId(bean.getParentId());
                    configChannelMapper.doEditExtend(editConfigChannelEvt);
                }
            }
        }
        resp.getHead().setRespCode(0);
        resp.setBody(i);
        return resp;
    }

    public ServerResp doEditStatus(EditConfigChannelStatusEvt editConfigChannelStatusEvt){
        ServerResp resp=new ServerResp();
        int i = configChannelMapper.doEditStatus(editConfigChannelStatusEvt);
        resp.getHead().setRespCode(0);
        resp.setBody(i);
        return resp;
    }

    public int isHasChannelCode(QueryConfigChannelEvt evt) throws Exception{
        return configChannelMapper.isHasChannelCode(evt);
    }

    public ServerResp queryAllConfigChannels(QueryConfigChannelEvt queryConfigChannelEvt){
        ServerResp resp=new ServerResp();
        List<ConfigChannelBean> channels = configChannelMapper.queryAllConfigChannels(queryConfigChannelEvt);
        return resp.success(channels);
    }

    public ServerResp queryChannels(QueryConfigChannelEvt queryConfigChannelEvt){
        ServerResp resp=new ServerResp();
        List<ConfigChannelBean> channels = configChannelMapper.queryChannels(queryConfigChannelEvt);
        return resp.success(channels);
    }

}

