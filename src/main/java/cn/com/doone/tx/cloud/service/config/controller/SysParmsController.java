package cn.com.doone.tx.cloud.service.config.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;

import cn.com.doone.tx.cloud.service.config.evt.sysParms.AddSysParmEvt;
import cn.com.doone.tx.cloud.service.config.evt.sysParms.DeleteSysParmEvt;
import cn.com.doone.tx.cloud.service.config.evt.sysParms.EditSysParmEvt;
import cn.com.doone.tx.cloud.service.config.evt.sysParms.QuerySysParmEvt;
import cn.com.doone.tx.cloud.service.config.info.SysParmsInfo;
import cn.com.doone.tx.cloud.service.config.service.ParmValuesService;
import cn.com.doone.tx.cloud.service.config.service.SysParmsService;
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
@RequestMapping("/sysParms")
@Api(description = "系统参数配置服务")
public class SysParmsController {

    @Autowired
    private SysParmsService sysParmsService;

    @Autowired
    private RedisTemplate redisTemplate;
    
    @Autowired
    private ParmValuesService parmValuesService;

    @ServiceAround
    @ApiOperation(value="查询系统参数数量#查询", notes="0成功 -1失败")
    @RequestMapping(value = "sysParmsCount" , method = RequestMethod.POST)
    public ServerResp<Object> sysParmsCount(@RequestBody Message<QuerySysParmEvt> message){
        QuerySysParmEvt evt = message.getBody();
        ServerResp<Object> resp=new ServerResp<Object>();
        try {
            int cnt  = sysParmsService.querySysParmsCount(evt);
            return resp.success(cnt);
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }

    @ServiceAround
    @ApiOperation(value="查询系统参数#查询", notes="0成功 -1失败")
    @RequestMapping(value = "querySysParmsByParam" , method = RequestMethod.POST)
    public ServerResp<Object> querySysParmsByParam(@RequestBody Message<QuerySysParmEvt> message){
        QuerySysParmEvt evt = message.getBody();
        PageHelper.startPage(evt.getQueryPage(),evt.getQuerySize());
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            List<SysParmsInfo> sysParmsInfos = sysParmsService.querySysParmsByParam(evt);
            return resp.success(sysParmsInfos);
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }

    @ServiceAround
    @ApiOperation(value="添加系统参数#受理", notes="respCode 0-成功 -1失败")
    @RequestMapping(value = "doAdd" , method = RequestMethod.POST)
    public ServerResp<Object> doAdd(@RequestBody Message<AddSysParmEvt> message){
        AddSysParmEvt evt = message.getBody();
        evt.setStatus("E");
        evt.setCreateTime(new Date());
        evt.setUpdateTime(new Date());
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            return resp.success(sysParmsService.add(evt));
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError("操作失败");
        }
    }
    @ServiceAround
    @ApiOperation(value="编辑系统参数#受理", notes="respCode 0-成功 -1失败")
    @RequestMapping(value = "doEdit" , method = RequestMethod.POST)
    public ServerResp<Object> doEdit(@RequestBody Message<EditSysParmEvt> message){
        EditSysParmEvt evt = message.getBody();
//        System.out.println("doeditId"+evt.getId());
        evt.setUpdateTime(new Date());
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            /**1、更新数据库*/
            int n = sysParmsService.doEdit(evt);
            if (n>=1){
                /**2、更新redis缓存中的信息*/
                String key = evt.getConfigKey();
                List<String> valuesList = parmValuesService.queryKeyValues(key);
                redisTemplate.opsForValue().set("sysParm_"+key,valuesList);
                return resp.success("更新成功");
            }else {
                return resp.error();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }
    @ServiceAround
    @ApiOperation(value="删除系统参数#受理", notes="respCode 0-成功 -1失败")
    @RequestMapping(value = "doDelete" , method = RequestMethod.POST)
    public ServerResp<Object> doDelete(@RequestBody Message<DeleteSysParmEvt> message){
        DeleteSysParmEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            int n = sysParmsService.doDelete(evt);
            if (n>=1){
                return resp.success("删除成功");
            }else {
                return resp.error("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }
    @ServiceAround
    @ApiOperation(value="查询系统参数key是否存在#查询", notes="0成功 -1失败")
    @RequestMapping(value = "querySysParmsIsExist" , method = RequestMethod.POST)
    public ServerResp<Object> querySysParmsIsExist(@RequestBody Message<QuerySysParmEvt> message){
        QuerySysParmEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            int n = sysParmsService.querySysParmsIsExist(evt);
            return resp.success(n);
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }
}