package cn.com.doone.tx.cloud.service.config.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;

import cn.com.doone.tx.cloud.service.config.evt.sysParms.DeleteSysParmEvt;
import cn.com.doone.tx.cloud.service.config.evt.sysParms.QuerySysParmEvt;
import cn.com.doone.tx.cloud.service.config.info.SysParmsInfo;
import cn.com.doone.tx.cloud.service.config.service.ParmValuesService;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.log.annotation.ServiceAround;
import cn.com.doone.tx.cloud.tool.service.common.base.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by yecz on 2017/2/22.
 * 系统参数配置类
 */
@RestController
@RequestMapping("/parmValues")
@Api(description = "系统参数值配置服务")
public class ParmValuesController {

    @Autowired
    private ParmValuesService parmValuesService;

    @Autowired
    private RedisTemplate redisTemplate;

    @ServiceAround
    @ApiOperation(value="查询系统参数value数量#查询", notes="respCode 0-成功 -1失败")
    @RequestMapping(value = "parmValuesCount" , method = RequestMethod.POST)
    public ServerResp<Object> parmValuesCount(@RequestBody Message<QuerySysParmEvt> message){
        QuerySysParmEvt evt = message.getBody();
        ServerResp<Object> resp=new ServerResp<Object>();
        int cnt  = parmValuesService.queryParmValuesCount(evt);
        return resp.success(cnt);
    }

    @ServiceAround
    @ApiOperation(value="查询系统value参数#查询", notes="respCode 0-成功 -1失败")
    @RequestMapping(value = "queryParmValuesByParam" , method = RequestMethod.POST)
    public ServerResp<Object> queryParmValuesByParam(@RequestBody Message<QuerySysParmEvt> message){
        QuerySysParmEvt evt = message.getBody();
        PageHelper.startPage(evt.getQueryPage(),evt.getQuerySize());
        ServerResp<Object> resp= new ServerResp<Object>();
        List<SysParmsInfo> sysParmsInfos = parmValuesService.queryParmValuesByParam(evt);
        return resp.success(sysParmsInfos);
    }

    @ServiceAround
    @ApiOperation(value="删除系统参数值#受理", notes="respCode 0-成功 -1失败")
    @RequestMapping(value = "doDelete" , method = RequestMethod.POST)
    public ServerResp<Object> doDelete(@RequestBody Message<DeleteSysParmEvt> message){
        DeleteSysParmEvt evt = message.getBody();
//        System.out.println("delId:"+evt.getId());
        ServerResp<Object> resp= new ServerResp<Object>();
        int n = parmValuesService.doDelete(evt);
        resp.getHead().setRespCode(n);
        return resp;
    }

    //>>>>>>>>>>>>>>>>>用户中心启动时将调用此方法<<<<<<<<<<<
    @ServiceAround
    @ApiOperation(value="查询系统参数key-value结果集#查询", notes="0 成功 -1失败")
    @RequestMapping(value = "queryParmValues" , method = RequestMethod.POST)
    public ServerResp<Object>  queryParmValues(@RequestBody Message<QuerySysParmEvt> message){
        ServerResp<Object> resp= new ServerResp<Object>();
        Map<String,List<String>> map = new HashedMap();
        //查询所有的参数key
        resp.getHead().setRespCode(0);
        try {
            List<String> keys = parmValuesService.queryKeys();
            //根据参数key查询对应的value
            for (String key:keys){
                List<String> values = parmValuesService.queryKeyValues(key);
                map.put(key,values);
                if(values.size()>0){
                    //放入缓存保存-使用 "sysPram_"+key 作为redis的key ,list作为参数
                    redisTemplate.opsForValue().set("sysParm_"+key,values);
                }
            }
//            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getHead().setRespCode(-1);
        }
        return resp.success(map);
    }


    @ServiceAround
    @ApiOperation(value="根据系统参数key查询value结果集#查询", notes="0 成功 -1失败")
    @RequestMapping(value = "queryValues" , method = RequestMethod.POST)
    public ServerResp<Object>  queryValues(@RequestBody Message<QuerySysParmEvt> message ){
        QuerySysParmEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        //查询所有的参数key
        resp.getHead().setRespCode(0);
        try {
        	List<String> values = parmValuesService.queryKeyValues(evt.getConfigKey());
//            System.out.println("values:"+values);
            resp.success(values);
        } catch (Exception e) {
            e.printStackTrace();
            resp.systemError("操作失败");
        }
        return resp;
    }
    
    @ServiceAround
    @ApiOperation(value="根据系统参数key查询value结果集", notes="")
    @RequestMapping(value = "queryValue" , method = RequestMethod.POST)
    public ServerResp<Object>  queryValue(@RequestBody Message<QuerySysParmEvt> message ){
        QuerySysParmEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        //查询所有的参数key
        resp.getHead().setRespCode(0);
        try {
        	List<SysParmsInfo> values = parmValuesService.queryKeyValue(evt);
//            System.out.println("values:"+values);
            resp.success(values);
        } catch (Exception e) {
            e.printStackTrace();
            resp.error("操作失败");
        }
        return resp;
    }
    
    @ServiceAround
    @ApiOperation(value="根据系统参数key查询value结果集#查询", notes="0 成功 -1 失败")
    @RequestMapping(value = "queryValuesRedis" , method = RequestMethod.POST)
    public ServerResp<Object>  queryValuesRedis(@RequestBody Message<QuerySysParmEvt> message ){
        QuerySysParmEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        //查询所有的参数key
        String key = evt.getConfigKey();
        List<String> valuesList = null;
        try {
            if (StringUtils.isBlank(key)){
                return resp.error("参数key为空");
            }
            //首先从缓存中获取
            try {
                valuesList = (List<String>) redisTemplate.opsForValue().get("sysParm_"+key);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (valuesList!=null){
                if (valuesList.size()>0){
                    return  resp.success(valuesList);
                }
            }else {
                //查询不到取数据库
                valuesList = parmValuesService.queryKeyValues(evt.getConfigKey());
                if (valuesList.size()>0){//缓存
                    try {
                        redisTemplate.opsForValue().set("sysParm_"+key,valuesList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            return  resp.success(valuesList);
        } catch (Exception e) {
            e.printStackTrace();
            return  resp.systemError("操作失败");
        }
    }


}
