package cn.com.doone.tx.cloud.service.user.dao.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.SelectProvider;

import cn.com.doone.tx.cloud.service.user.dao.provider.OperateLogProvider;
import cn.com.doone.tx.cloud.service.user.evt.operateLog.QueryOperateLogEvt;

public interface OperateLogMapper {

    @SelectProvider(type = OperateLogProvider.class , method = "queryCount")
    int queryCount(QueryOperateLogEvt evt);

    @SelectProvider(type = OperateLogProvider.class , method = "queryByParam")
    List<Map<String, Object>> queryByParam(QueryOperateLogEvt evt);






}
