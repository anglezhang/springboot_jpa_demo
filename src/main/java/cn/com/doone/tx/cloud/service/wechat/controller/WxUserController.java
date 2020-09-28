package cn.com.doone.tx.cloud.service.wechat.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.doone.tx.cloud.service.wechat.bean.WxUserBean;
import cn.com.doone.tx.cloud.service.wechat.evt.user.AddWxUserEvt;
import cn.com.doone.tx.cloud.service.wechat.evt.user.EditWxUserEvt;
import cn.com.doone.tx.cloud.service.wechat.evt.user.QueryWxUserEvt;
import cn.com.doone.tx.cloud.service.wechat.service.WxUserService;
import cn.com.doone.tx.cloud.tool.common.enums.CommonResultCode;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.log.annotation.ServiceAround;
import cn.com.doone.tx.cloud.tool.service.common.base.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/wxUser")
@Api(description = "企业微信员工服务")
public class WxUserController {
	
	private static final Logger logger = Logger.getLogger(WxUserController.class);
	
	@Autowired
	private WxUserService wxUserService;
	
	@ServiceAround
    @ApiOperation(value="查询员工#查询", notes="respCode 1-成功 0-失败")
    @RequestMapping(value = "queryUserByParam" , method = RequestMethod.POST)
    public ServerResp<Object> queryUserByParam(@RequestBody Message<QueryWxUserEvt> message){
		QueryWxUserEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        List<Map<String,Object>> dataList = null;
        try {
        	evt.startPage();
            dataList = wxUserService.queryByParam(evt);
			resp.success(dataList);
			return resp;
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }
	
	@ServiceAround
    @ApiOperation(value="查询员工数量#查询", notes="respCode 1-成功 0-失败")
    @RequestMapping(value = "userCount" , method = RequestMethod.POST)
    public ServerResp<Object> userCount(@RequestBody Message<QueryWxUserEvt> message){
		QueryWxUserEvt evt = message.getBody();
        ServerResp<Object> resp=new ServerResp<Object>();
        try {
            int cnt = wxUserService.queryUserCount(evt);
            return resp.success(cnt);
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }
	
	@ServiceAround
    @ApiOperation(value="查询员工是否存在#查询", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "queryUserIsExist" , method = RequestMethod.POST)
    public ServerResp<Object> queryUserIsExist(@RequestBody Message<QueryWxUserEvt> message){
		QueryWxUserEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            int n = wxUserService.queryUserIsExist(evt);
            return resp.success(n);
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }
	
	@ServiceAround
    @ApiOperation(value="添加员工#受理", notes="respCode 1-成功 0-失败")
    @RequestMapping(value = "doAdd" , method = RequestMethod.POST)
    public ServerResp<Object> doAdd(@RequestBody  Message<AddWxUserEvt> message){
		AddWxUserEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            WxUserBean wxUserBean = wxUserService.add(evt);
            Long id = wxUserBean.getId();
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
    @ApiOperation(value="编辑员工#受理", notes="respCode 0成功，-1失败")
    @RequestMapping(value = "doEdit" , method = RequestMethod.POST)
    public ServerResp<Object> doEdit(@RequestBody Message<EditWxUserEvt> message){
		EditWxUserEvt evt = message.getBody();
        ServerResp<Object> resp= new ServerResp<Object>();
        try {
            int n = wxUserService.doEdit(evt);
            return resp.success(n);
        } catch (Exception e) {
            e.printStackTrace();
            return resp.systemError();
        }
    }

}
