package cn.com.doone.tx.cloud.service.integral.controller;


import cn.com.doone.tx.cloud.service.integral.bean.IntegralType;
import cn.com.doone.tx.cloud.service.integral.evt.*;
import cn.com.doone.tx.cloud.service.integral.service.IntegralService;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.service.common.base.Message;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: IntegralController <br/>
 * Description: <br/>
 * date: 2020/5/7 15:51<br/>
 *
 * @author zhangzhenxing<br />
 */
@Controller
@RequestMapping(value = "/integralsService")
public class IntegralController {

    /**
     * 日志工具类
     * */
    private static final Logger lOGGER = Logger.getLogger(IntegralController.class);

    /**
     * 积分服务类
     * */
    @Autowired
    private IntegralService integralService;

    @PostMapping(value = "/findInteralTypes")
    @ResponseBody
    public ServerResp<Object> findInteralTypes(@RequestBody Message<IntegralQueryEvt> params){
        ServerResp<Object> resp = new ServerResp<Object>();
        if(params.getBody() == null){
            return resp.error("参数非法");
        }
        IntegralQueryEvt integralQueryEvt = params.getBody();
        Page<IntegralType> list = integralService.queryIntegralTypes(integralQueryEvt.getPageSize()
                ,integralQueryEvt.getPageNum());
        PageInfo<IntegralType> result = new PageInfo<>(list);
        Map<String,Object> map = new HashMap<>(12);
        map.put("list",result.getList());//列表
        map.put("size",result.getSize());//数量
        map.put("total",result.getTotal());//总记录数量
        map.put("pages",result.getPages());//总页数
        resp.success(map);
        return resp;
    }

    /**
     * 删除积分类型 逻辑删除积分类型 (将状态置为‘D’)
     * */
    @PostMapping(value = "/deleteInteral")
    @ResponseBody
    public ServerResp<Object> deleteInteral(@RequestBody Message<IntegraDelEvt> message){
        ServerResp<Object> resp = new ServerResp<Object>();
        try {
            IntegraDelEvt delEvt = message.getBody();
            int updatRows = integralService.deleteInteral(delEvt);
            if(updatRows > 0){
                resp.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.error("删除出错");
        }
        return resp;
    }

    /**
     * 添加积分规则
     * */
    @PostMapping(value = "/updateInteral")
    public ServerResp<Object> updateInteral(@RequestBody Message<IntegraAddEvt> message){
        //保存
        ServerResp<Object> resp = new ServerResp<Object>();
        String jsonStr = message.getBody().getEdtiIntegraDataArray();
        if (StringUtils.isEmpty(jsonStr)){
            resp.error("非法参数！");
        }
        JSONArray jsonArray = JSONArray.parseArray(jsonStr);
        Long operator = message.getBody().getOperator();
        int updatRows = integralService.updateInteral(jsonArray,operator);
        if (updatRows > 0){
            resp.success();
        }else {
            resp.error("保存失败");
        }
        return resp;
    }

    /**
     * 查询积分类型
     * */
    @PostMapping(value = "/findIntegralRulePage")
    @ResponseBody
    public ServerResp<Object> findIntegralRulePage(@RequestBody Message<IntegralTypeQueryEvt>
                                                               message){
        ServerResp<Object> resp = new ServerResp<Object>();
        if(message.getBody() == null){
            return resp.error("参数非法");
        }
        try {
            IntegralTypeQueryEvt queryEvt = message.getBody();
            Page<Map<String,Object>> list = integralService.queryIntegralRulePage(queryEvt);
            PageInfo<Map<String,Object>> result = new PageInfo<>(list);
            Map<String,Object> map = new HashMap<>(12);
            map.put("list",result.getList());//列表
            map.put("size",result.getSize());//数量
            map.put("total",result.getTotal());//总记录数量
            map.put("pages",result.getPages());//总页数
            resp.success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return resp.error("查询异常");
        }
        return resp;
    }

    /**
     * 保存积分规则
     * */
    @PostMapping(value = "/saveAndUpdateIntegraRule")
    public ServerResp<Object> saveAndUpdateIntegraRule(@RequestBody Message<IntegraRuleEvt> message){
        ServerResp<Object> resp = new ServerResp<Object>();
        IntegraRuleEvt integraRuleEvt = message.getBody();
        if (integraRuleEvt == null){
            resp.error("参数不能为空");
        }
        int updatRows = integralService.saveAndUpdateIntegraRule(integraRuleEvt);
        if (updatRows > 0){
            resp.success();
        }
        return resp;
    }

    /**
     * 查询积分规则
     * */
    @PostMapping(value = "/findIntegralRuleById")
    @ResponseBody
    public ServerResp<Object> findIntegralRuleById(@RequestBody Message<IntegraRuleEvt> message){
        ServerResp<Object> resp = new ServerResp<Object>();
        IntegraRuleEvt integraRuleEvt = message.getBody();
        if (integraRuleEvt == null){
            resp.error("参数非法");
        }else {
            integraRuleEvt = integralService.findIntegraRuleById(integraRuleEvt.getId());
            resp.success(integraRuleEvt);
        }
        return resp;
    }

    /**
     * 送审核
     * */
    /*@PostMapping(value = "/sendRuleAutid")
    @ResponseBody
    public ServerResp<Object> sendRuleAutid(@RequestBody Message<IntegralRuleAuditEvt> messgae){
        ServerResp<Object> resp = new ServerResp<Object>();
        IntegralRuleAuditEvt integralRuleAuditEvt = messgae.getBody();
        if (integralRuleAuditEvt == null){
            resp.error("参数非法");
        }else {
            Integer updateRows = integralService.sendRuleAutid(integralRuleAuditEvt);
            if (updateRows > 0){
                resp.success();
            }
        }
        return resp;
    }*/

    /**
     * 查询审核分页数据
     * */
    @RequestMapping(value = "/ruleAuditListPage")
    @ResponseBody
    public ServerResp<Object> ruleAuditListPage(@RequestBody Message<IntegralRuleAuditEvt> message){
        ServerResp<Object> resp = new ServerResp<Object>();
        IntegralRuleAuditEvt integralRuleAuditEvt = message.getBody();
        if (integralRuleAuditEvt == null){
            resp.error("参数非法");
        }else {
            List<Map<String,Object>> list = integralService.ruleAuditListPage(integralRuleAuditEvt);
            PageInfo<Map<String,Object>> result = new PageInfo<>(list);
            resp.success(result);
        }
        return resp;
    }

    /**
     * 更新积分状态
     * */
    @RequestMapping(value = "/saveAndUpdateRuleAudit")
    @ResponseBody
    public ServerResp<Object> saveAndUpdateRuleAudit(@RequestBody Message<IntegralRuleAuditEvt> message){
        ServerResp<Object> resp = new ServerResp<Object>();
        IntegralRuleAuditEvt integralRuleAuditEvt = message.getBody();
        if (integralRuleAuditEvt == null) {
            resp.error("参数非法");
        }else {
            int updateRows = integralService.saveAndUpdateRuleAudit(integralRuleAuditEvt);
            if (updateRows > 0){
                resp.success();
            }
        }
        return resp;
    }

    /**
     * 查询积分待审核状态数据
     * */
    @RequestMapping(value = "/findToViewRuleList")
    @ResponseBody
    public ServerResp<Object> findToViewRuleList(@RequestBody Message<IntegralRuleAuditEvt> message){
        ServerResp<Object> resp = new ServerResp<Object>();
        IntegralRuleAuditEvt integralRuleAuditEvt = message.getBody();
        if (integralRuleAuditEvt == null){
            resp.error("参数非法");
        }else {
            List<Map<String,Object>> list = integralService.findToViewRuleList(integralRuleAuditEvt);
            PageInfo<Map<String,Object>> result = new PageInfo<>(list);
            resp.success(result);
        }
        return resp;
    }
}
