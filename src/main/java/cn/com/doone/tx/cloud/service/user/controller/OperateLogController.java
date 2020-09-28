package cn.com.doone.tx.cloud.service.user.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.doone.tx.cloud.service.user.bean.OperateLogBean;
import cn.com.doone.tx.cloud.service.user.evt.operateLog.AddOperateLogEvt;
import cn.com.doone.tx.cloud.service.user.evt.operateLog.QueryOperateLogEvt;
import cn.com.doone.tx.cloud.service.user.service.OperateLogService;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.log.annotation.ServiceAround;
import cn.com.doone.tx.cloud.tool.service.common.base.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/operateLog")
@Api(description = "操作日志")
public class OperateLogController {

    @Autowired
    private OperateLogService operateLogService;

    @ServiceAround
    @ApiOperation(value="操作日志入库#受理", notes="")
    @RequestMapping(value = "addOperateLog" , method = RequestMethod.POST)
    public ServerResp<Object> addOperateLog(@RequestBody Message<AddOperateLogEvt> message){
    	ServerResp<Object> resp = new ServerResp<Object>();
    	try{
    		OperateLogBean operateLogBean = new OperateLogBean();
        	BeanUtils.copyProperties(message.getBody(), operateLogBean);
        	if(null==operateLogBean.getCreateTime()){
        		operateLogBean.setCreateTime(new Date());
        	}
        	if(null==operateLogBean.getStatus()){
        		operateLogBean.setStatus("E");
        	}
        	if(null==operateLogBean.getOperateResult()){
        		operateLogBean.setOperateResult("0");
        	}
        	operateLogBean.setCreator(operateLogBean.getOperator());
        	OperateLogBean operateLogBean2 =  operateLogService.addOperateLog(operateLogBean);
        	if(null!=operateLogBean2.getId()&&operateLogBean2.getId()>0){
        	    resp.success(0, "操作日志入库成功");
	    	}else{
	    		resp.error(-1, "操作日志入库失败"); 
	    	}
        	return resp;
    	}catch(Exception e){
    		e.printStackTrace();
//    		resp.error(-1,"操作日志入库失败");
    		return resp.systemError();
    	}
    }
    
    @ServiceAround
    @ApiOperation(value="查询日志条数#查询", notes="")
    @RequestMapping(value = "queryCount" , method = RequestMethod.POST)
    public ServerResp<Object> queryCount(@RequestBody Message<QueryOperateLogEvt> message){
    	ServerResp<Object> resp = new ServerResp<Object>();
    	try{
    		int count = operateLogService.queryCount(message.getBody());
    		resp.success(count);
    		return resp;
    	}catch(Exception e){
    		e.printStackTrace();
//    		resp.error("查询操作日志条数失败");
    		return resp.systemError();
    	}
    }
    
    @ServiceAround
    @ApiOperation(value="查询日志#查询", notes="")
    @RequestMapping(value = "queryByParam" , method = RequestMethod.POST)
    public ServerResp<Object> queryByParam(@RequestBody Message<QueryOperateLogEvt> message){
    	ServerResp<Object> resp = new ServerResp<Object>();
    	try{
    		List<Map<String, Object>> operateLogs = operateLogService.queryByParam(message.getBody());
    		resp.success(operateLogs);
    		return resp;
    	}catch(Exception e){
    		e.printStackTrace();
//    		resp.error("查询操作日志失败");
    		return resp.systemError();
    	}
    }

}
