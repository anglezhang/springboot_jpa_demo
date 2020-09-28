package cn.com.doone.tx.cloud.service.user.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.doone.tx.cloud.service.user.bean.SysAccessBean;
import cn.com.doone.tx.cloud.service.user.evt.sysaccess.AddSysAccessEvt;
import cn.com.doone.tx.cloud.service.user.evt.sysaccess.EditSysAccessEvt;
import cn.com.doone.tx.cloud.service.user.evt.sysaccess.QuerySysAccessEvt;
import cn.com.doone.tx.cloud.service.user.info.SysAccessInfo;
import cn.com.doone.tx.cloud.service.user.service.SysAccessService;
import cn.com.doone.tx.cloud.tool.common.enums.CommonResultCode;
import cn.com.doone.tx.cloud.tool.common.enums.NormalStatus;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.common.util.DictLangUtil;
import cn.com.doone.tx.cloud.tool.log.annotation.ServiceAround;
import cn.com.doone.tx.cloud.tool.service.common.base.BaseController;
import cn.com.doone.tx.cloud.tool.service.common.base.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * Created by zhenghb on 2018/4/21
 *
 */
@RestController
@RequestMapping("/sysAccess")
@Api(description = "系统接入管理")
public class SysAccessController extends BaseController {

	private static final Logger LOGGER = Logger.getLogger(SysAccessController.class);
	
	@Autowired
	private SysAccessService sysAccessService;
	
	@Autowired
    private DictLangUtil dictLangUtil;
	
	@ServiceAround
    @ApiOperation(value="查询接入管理总记录数#查询", notes="respCode 1-成功 0-失败")
    @RequestMapping(value = "queryDataCount" , method = RequestMethod.POST)
    public ServerResp<Object> queryDataCount(@RequestBody Message<QuerySysAccessEvt> message){
    	QuerySysAccessEvt paramEvt = message.getBody();
        ServerResp<Object> resp=new ServerResp<Object>();
        int resInt;
        try {
        	resInt = sysAccessService.queryDataCount(paramEvt);
            resp.success(resInt);
        } catch (Exception e) { 
        	LOGGER.error("查询接入管理总记录数查询异常：" + e, e);
        	resp.systemError(CommonResultCode.countError.getRespMsg());
        }
        return resp;
    }
	
	@ServiceAround
    @ApiOperation(value="查询接入管理记录列表#查询", notes="respCode 1-成功 0-失败")
    @RequestMapping(value = "queryDataList" , method = RequestMethod.POST)
    public ServerResp<Object> queryDataList(@RequestBody Message<QuerySysAccessEvt> message){
    	QuerySysAccessEvt paramEvt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        List<SysAccessInfo> dataList = null;
        try {
            paramEvt.startPage();
            dataList = sysAccessService.queryDataList(paramEvt);
            resp.success(dataList);
        } catch (Exception e) { 
        	LOGGER.error("查询接入管理记录列表异常：" + e, e);
            resp.systemError(CommonResultCode.listError.getRespMsg());
        }
        return resp;
    }
	
	@ServiceAround
    @ApiOperation(value="新增接入管理功能#受理", notes="respCode 1-成功 0-失败")
    @RequestMapping(value = "doAdd" , method = RequestMethod.POST)
    public ServerResp<Object> doAdd(@RequestBody  Message<AddSysAccessEvt> message){
		AddSysAccessEvt paramEvt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
        	SysAccessBean sysAccessBean = sysAccessService.add(paramEvt);
        	if(sysAccessBean == null){
        		resp.error("对不起，该业务系统已存在");
        	}else {
        		Long id = sysAccessBean.getId();
                if (id!=null){
                    resp.success(sysAccessBean);
                }else {
                    resp.error(CommonResultCode.addFail.getRespCode(),  CommonResultCode.addFail.getRespMsg());
                }
        	}
        } catch (Exception e) {
        	LOGGER.error("新增接入管理功能异常：" + e, e);
            resp.systemError(CommonResultCode.addFail.getRespMsg());
        }
        return resp;
    }
	
	@ServiceAround
    @ApiOperation(value="接入管理编辑功能#受理", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "doEdit" , method = RequestMethod.POST)
    public ServerResp<Object> doEdit(@RequestBody Message<EditSysAccessEvt> message){
		EditSysAccessEvt paramEvt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            int resInt = sysAccessService.doEdit(paramEvt);
            if(resInt == 0){
            	resp.error("对不起，该业务系统已存在");
            }else {
            	resp.success(resInt);	
            }
        } catch (Exception e) {
        	LOGGER.error("接入管理编辑功能异常：" + e, e);
            resp.systemError(CommonResultCode.editFail.getRespMsg());
        }
        return resp;
    }
	
	@ServiceAround
    @ApiOperation(value="接入管理删除功能#受理", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "doDelete" , method = RequestMethod.POST)
    public ServerResp<Object> doDelete(@RequestBody Message<EditSysAccessEvt> message){
		EditSysAccessEvt paramEvt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
        	paramEvt.setStatus(NormalStatus.D.name());
            int resInt = sysAccessService.doDelete(paramEvt);
            if(resInt == 0){
            	resp.error("删除失败");
            }else {
            	resp.success(resInt,"SUCCESS");	
            }
        } catch (Exception e) {
        	LOGGER.error("接入管理删除功能异常：" + e, e);
            resp.systemError("删除失败");
        }
        return resp;
    }
	
}
