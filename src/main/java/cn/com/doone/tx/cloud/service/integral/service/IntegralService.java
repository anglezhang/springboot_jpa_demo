package cn.com.doone.tx.cloud.service.integral.service;

import cn.com.doone.tx.cloud.service.integral.bean.IntegralDepartment;
import cn.com.doone.tx.cloud.service.integral.bean.IntegralRule;
import cn.com.doone.tx.cloud.service.integral.bean.IntegralRuleAudit;
import cn.com.doone.tx.cloud.service.integral.bean.IntegralType;
import cn.com.doone.tx.cloud.service.integral.dao.mapper.IntegralDepartmentMapper;
import cn.com.doone.tx.cloud.service.integral.dao.mapper.IntegralRuleAuditMapper;
import cn.com.doone.tx.cloud.service.integral.dao.mapper.IntegralRuleMapper;
import cn.com.doone.tx.cloud.service.integral.dao.mapper.IntegralTypeMapper;
import cn.com.doone.tx.cloud.service.integral.evt.IntegraDelEvt;
import cn.com.doone.tx.cloud.service.integral.evt.IntegraRuleEvt;
import cn.com.doone.tx.cloud.service.integral.evt.IntegralRuleAuditEvt;
import cn.com.doone.tx.cloud.service.integral.evt.IntegralTypeQueryEvt;
import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;
import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatRepository;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ClassName: IntegralService <br/>
 * Description:积分服务接口 <br/>
 * date: 2020/5/8 11:49<br/>
 *
 * @author zhangzhenxing<br />
 */
@Service("IntegralService")
public class IntegralService {


    /**
     * 积分业务mapper
     */
    @Autowired
    private IntegralTypeMapper integralTypeMapper;

    /**
     * 基础封装工具
     */
    @Autowired
    private PlatRepository platRepository;

    /**
     * 积分规则查询
     */
    @Autowired
    private IntegralRuleMapper integralRuleMapper;

    /**
     * 积分部门mapper
     */
    @Autowired
    private IntegralDepartmentMapper integralDepartmentMapper;

    /**
     * 积分审核mapper
     */
    @Autowired
    private IntegralRuleAuditMapper integralRuleAuditMapper;


    /**
     * 查询积分类型
     *
     * @param pageSize 页面大小
     * @param pageNum  页码
     */
    public Page<IntegralType> queryIntegralTypes(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        Page<IntegralType> result = integralTypeMapper.findIntegralType();
        return result;
    }

    /**
     * 更新积分类型数据
     *
     * @param jsonArray 积分类型数据串
     * @param operator  操作人
     */
    public Integer updateInteral(JSONArray jsonArray, Long operator) {
        AtomicInteger updateRows = new AtomicInteger();
        AtomicReference<String> as = new AtomicReference<>();

        if (jsonArray != null && jsonArray.size() > 0) {
            Date now = new Date();
            jsonArray.forEach(jsobj -> {
                IntegralType bean = JSON.parseObject(jsobj.toString(), IntegralType.class);
                if (bean.getId() == null) {
                    bean.setCreateTime(now);
                    bean.setUpdateTime(now);
                    bean.setStatus("E");
                    bean.setOperator(operator);
                    bean.setCreator(operator);
                    platRepository.save(bean);
                    updateRows.addAndGet(updateRows.intValue() + 1);
                } else {
                    bean.setUpdateTime(now);
                    bean.setOperator(operator);
                    updateRows.addAndGet(integralTypeMapper.updateRuleType(bean));
                }
            });
        }
        return updateRows.intValue();
    }

    /**
     * 逻辑删除积分类型 将状态置为 D
     *
     * @param delEvt 删除事件
     */
    public Integer deleteInteral(IntegraDelEvt delEvt) {
        Integer updateRows = 0;
        if (delEvt != null) {
            IntegralType entity = new IntegralType();
            entity.setStatus("D");
            entity.setOperator(delEvt.getOperator());
            entity.setId(delEvt.getIntegraId());
            updateRows = integralTypeMapper.updateRuleType(entity);
        }
        return updateRows;
    }

    /**
     * 查询积分规则
     */
    public Page<Map<String, Object>> queryIntegralRulePage(IntegralTypeQueryEvt queryEvt) {
        PageHelper.startPage(queryEvt.getPageNum(), queryEvt.getPageSize());
        return integralRuleMapper.queryIntegralRules(queryEvt);
    }

    /**
     * 保存或更新积分规则
     */
    @Transactional
    public Integer saveAndUpdateIntegraRule(IntegraRuleEvt integraRuleEvt) {
        Integer updateRows = 0;
        IntegralRule integralRule = new IntegralRule();
        BeanUtils.copyProperties(integraRuleEvt, integralRule);
        Date now = new Date();
        integralRule.setUpdateTime(now);
        //判断是新增还是修改
        if (integraRuleEvt.getId() == null) {
            integralRule.setCreateTime(now);
            integralRule.setStatus("W");
            platRepository.save(integralRule);
            updateRows++;
        } else {
            updateRows += integralRuleMapper.updatetegralRules(integralRule);
        }
        //根据积分规则删除适配部门
        updateRows += integralDepartmentMapper.deleteDeptByRuleIds(integraRuleEvt.getId());
        if (StringUtils.isNotEmpty(integraRuleEvt.getDeptIds())) {
            long[] deptIds = Arrays.stream(integraRuleEvt.getDeptIds()
                    .split(",")).mapToLong(deptId -> {
                return Long.parseLong(deptId);
            }).toArray();
            for (int i = 0; i < deptIds.length; i++) {
                IntegralDepartment integralDepartment = new IntegralDepartment();
                integralDepartment.setCreateTime(now);
                integralDepartment.setUpdateTime(now);
                integralDepartment.setOperator(integralRule.getOperator());
                integralDepartment.setCreator(integralRule.getCreator());
                integralDepartment.setStatus("E");
                integralDepartment.setIntegralRuleId(integralRule.getId());
                integralDepartment.setId(null);
                integralDepartment.setDepartmentId(deptIds[i]);
                platRepository.save(integralDepartment);
                updateRows++;
            }
        }

        return updateRows;
    }

    /**
     * 根据ID查询积分规则
     */
    public IntegraRuleEvt findIntegraRuleById(Long id) {
        IntegralRule integralRule = integralRuleMapper.findIntegralRuleById(id);
        List<IntegralDepartment> deptList = integralDepartmentMapper.findIntegralDepartMentByRuleId(id);
        IntegraRuleEvt integraRuleEvt = new IntegraRuleEvt();
        BeanUtils.copyProperties(integralRule, integraRuleEvt);
        List<Long> deptIds = new ArrayList<>(12);
        List<String> depetNames = new ArrayList<>(12);
        deptList.forEach(dept -> {
            deptIds.add(dept.getDepartmentId());
            depetNames.add(dept.getDepartmentName());
        });
        IntegralType integralType = integralTypeMapper.findIntegralTypeById(integralRule.getIntegralTypeId());
        if (integralType != null) {
            integraRuleEvt.setIntegralName(integralType.getTypeName());
        }
        String deptIdStr = deptIds.stream().map(Objects::toString)
                .collect(Collectors.joining(","));
        integraRuleEvt.setDeptIds(deptIdStr);
        String deptNameStr = depetNames.stream().map(Objects::toString)
                .collect(Collectors.joining(","));
        integraRuleEvt.setDeptNames(deptNameStr);
        List<Map<String,Object>> auditList = integralRuleAuditMapper.findAuditByRuleId(integralRule.getId());
        if (auditList != null && auditList.size() > 0){
            Map<String,Object> audit = auditList.get(0);
            integraRuleEvt.setToViewOperator(audit.get("staffName").toString());
            integraRuleEvt.setAuditResult(audit.get("auditResult").toString());
            integraRuleEvt.setAuditRemark(audit.get("auditRemark").toString());
            integraRuleEvt.setAuditTime(audit.get("createTime").toString());
        }
        return integraRuleEvt;
    }

    /**
     * 送审核
     */
    @Transactional
    public Integer sendRuleAutid(IntegralRuleAuditEvt integralRuleAuditEvt) {
        int insertRows = 0;
        try {
            IntegralRuleAudit integralRuleAudit = new IntegralRuleAudit();
            Date date = new Date();
            BeanUtils.copyProperties(integralRuleAuditEvt, integralRuleAudit);
            integralRuleAudit.setCreator(integralRuleAuditEvt.getOperator());
            integralRuleAudit.setUpdateTime(date);
            integralRuleAudit.setCreateTime(date);
            insertRows = 1;
            platRepository.save(integralRuleAudit);
            IntegralRule integralRule = integralRuleMapper
                    .findIntegralRuleById(integralRuleAudit.getIntegralRuleId());
            if (integralRule != null) {
                //更新积分规则状态
                integralRule.setStatus("W");//待审核
                integralRule.setOperator(integralRuleAuditEvt.getOperator());
                integralRule.setUpdateTime(date);
                insertRows += integralRuleMapper.updatetegralRules(integralRule);
            }
        } catch (BeansException e) {
            e.printStackTrace();
            insertRows = 0;
        }
        return insertRows;
    }

    /**
     * 查询分页数据
     */
    public List<Map<String, Object>> ruleAuditListPage(IntegralRuleAuditEvt integralRuleAuditEvt) {
        PageHelper.startPage(integralRuleAuditEvt.getQueryPage(), integralRuleAuditEvt.getQuerySize());
        List<Map<String, Object>> list = integralRuleAuditMapper.findRuleAuditList(integralRuleAuditEvt);
        return list;
    }

    /**
     * 更新审核信息
     */
    @Transactional
    public int saveAndUpdateRuleAudit(IntegralRuleAuditEvt integralRuleAuditEvt) {
        Date date = new Date();
        int updateRows = 0;
        IntegralRuleAudit integralRuleAudit = new IntegralRuleAudit();
        BeanUtils.copyProperties(integralRuleAuditEvt, integralRuleAudit);
        integralRuleAudit.setCreator(integralRuleAuditEvt.getOperator());
        integralRuleAudit.setUpdateTime(date);
        integralRuleAudit.setCreateTime(date);
        platRepository.save(integralRuleAudit);
        updateRows = 1;
        String ruleStatus = "E".equals(integralRuleAuditEvt.getAuditResult()) ? "E" : "F";
        IntegralRule integralRule = integralRuleMapper.findIntegralRuleById(integralRuleAuditEvt.getIntegralRuleId());
        if (integralRule != null) {
            integralRule.setStatus(ruleStatus);
            integralRule.setUpdateTime(date);
            integralRule.setOperator(integralRuleAuditEvt.getOperator());
            updateRows += integralRuleMapper.updatetegralRules(integralRule);
        }
        //updateRows += integralRuleAuditMapper.updateRuleAutid(integralRuleAuditEvt);
        return updateRows;
    }

    /**
     * 查询待审核积分
     * */
    public List<Map<String, Object>> findToViewRuleList(IntegralRuleAuditEvt integralRuleAuditEvt) {
        PageHelper.startPage(integralRuleAuditEvt.getQueryPage(), integralRuleAuditEvt.getQuerySize());
        List<Map<String, Object>> list = integralRuleAuditMapper.findReviewedList(integralRuleAuditEvt);
        return list;
    }
}
