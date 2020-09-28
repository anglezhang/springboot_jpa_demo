package cn.com.doone.tx.cloud.service.wechat.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;

import cn.com.doone.tx.cloud.service.wechat.bean.WxDepartmentBean;
import cn.com.doone.tx.cloud.service.wechat.evt.department.AddWxDepartmentEvt;
import cn.com.doone.tx.cloud.service.wechat.evt.department.EditWxDepartmentEvt;
import cn.com.doone.tx.cloud.service.wechat.evt.department.QueryWxDepartmentEvt;
import cn.com.doone.tx.cloud.service.wechat.evt.user.QueryWxUserEvt;
import cn.com.doone.tx.cloud.service.wechat.service.WxDepartmentService;
import cn.com.doone.tx.cloud.service.wechat.service.WxUserService;
import cn.com.doone.tx.cloud.tool.common.enums.CommonResultCode;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.log.annotation.ServiceAround;
import cn.com.doone.tx.cloud.tool.service.common.base.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/wxDepartment")
@Api(description = "企业微信部门服务")
public class WxDepartmentController {
	
	private static final Logger logger = Logger.getLogger(WxDepartmentController.class);
	
	@Autowired
	private WxDepartmentService wxDepartmentService;
	
	@Autowired
	private WxUserService wxUserService;
	
	@ServiceAround
    @ApiOperation(value="查询部门#查询", notes="respCode 1-成功 0-失败")
    @RequestMapping(value = "queryDepartmentByParam" , method = RequestMethod.POST)
    public ServerResp<Object> queryDepartmentByParam(@RequestBody Message<QueryWxDepartmentEvt> message){
		QueryWxDepartmentEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        List<Map<String,Object>> dataList = null;
        try {
        	evt.startPage();
            dataList = wxDepartmentService.queryByParam(evt);
			resp.success(dataList);
			return resp;
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }
	
	
	@ServiceAround
    @ApiOperation(value="添加部门#受理", notes="respCode 1-成功 0-失败")
    @RequestMapping(value = "doAdd" , method = RequestMethod.POST)
    public ServerResp<Object> doAdd(@RequestBody  Message<AddWxDepartmentEvt> message){
		AddWxDepartmentEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            WxDepartmentBean wxDepartmentBean = wxDepartmentService.add(evt);
            Long id = wxDepartmentBean.getId();
            if (id!=null){
                return resp.success();
            }else {
                return resp.error(CommonResultCode.addFail.getRespCode(),
                        CommonResultCode.addFail.getRespMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }
	
	@ServiceAround
    @ApiOperation(value="编辑部门#受理", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "doEdit" , method = RequestMethod.POST)
    public ServerResp<Object> doEdit(@RequestBody Message<EditWxDepartmentEvt> message){
		EditWxDepartmentEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            int n = wxDepartmentService.doEdit(evt);
            return resp.success(n);
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }
	
	
	@ServiceAround
	@ApiOperation(value = "查询全部部门#查询", notes = "respCode 1-成功 0-失败")
	@RequestMapping(value = "queryAllDepartment", method = RequestMethod.POST)
	public ServerResp<Object> queryAllDepartment(@RequestBody Message<QueryWxDepartmentEvt> message) {
		QueryWxDepartmentEvt paramEvt = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		List<Map<String,Object>> dataList = null;
		try {
			dataList = wxDepartmentService.queryByParam(paramEvt);
			resp.success(dataList);
		} catch (Exception e) {
			logger.error("查询记录信息异常：" + e, e);
			resp.systemError("查询记录信息异常");
		}
		return resp;
	}

	@ServiceAround
    @ApiOperation(value="查询部门是否存在#查询", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "queryDepartmentIsExist" , method = RequestMethod.POST)
    public ServerResp<Object> queryDepartmentIsExist(@RequestBody Message<QueryWxDepartmentEvt> message){
		QueryWxDepartmentEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            int n = wxDepartmentService.queryDepartmentIsExist(evt);
            return resp.success(n);
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }
	
	@ServiceAround
    @ApiOperation(value="查询部门是否可以删除#查询", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "queryDepartmentCanDelete" , method = RequestMethod.POST)
    public ServerResp<Object> queryDepartmentCanDelete(@RequestBody Message<QueryWxDepartmentEvt> message){
		QueryWxDepartmentEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
        	//查询是否有子部门
        	QueryWxDepartmentEvt evt2 = new QueryWxDepartmentEvt();
        	evt2.setParentId(evt.getId());
        	List<Map<String,Object>> list = wxDepartmentService.queryByParam(evt2);
        	if(list!=null && list.size()>0) {
        		return resp.success(list.size());
        	}
        	//查询是否有员工
        	QueryWxUserEvt evt3 = new QueryWxUserEvt();
        	evt3.setDepartmentId(evt.getId());
        	List<Map<String,Object>> list1 = wxUserService.queryByParam(evt3);
        	if(list1!=null && list1.size()>0) {
        		return resp.success(list1.size());
        	}
        	return resp.success(0);
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }
}
