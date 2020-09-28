package cn.com.doone.tx.cloud.service.user.evt.loginrule;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;

import java.io.Serializable;
import java.util.Date;

public class QueryLoginRuleEvt extends QueryEvt implements Serializable{ 
	
	private static final long serialVersionUID = 4956226673902548664L;
	/**
	 * 主键ID
     */
    private Long id;
    /**
	 * 适用类型  B:业务系统  A:APP
     */
    private String applyType;
    /**
	 * 初始密码
     */
    private String iniPwd;
    /**
	 * 密码最小长度
     */
    private String minPwd;
    /**
	 * 密码最大长度
     */
    private String maxPwd;
    /**
	 * 密码组成规则  1.数字、字母、特殊符号组合（字母区分大小写）
     */
    private String ruleType;
    /**
	 * 密码锁定规则时间
     */
    private String lockRuleTime;
    /**
	 * 密码锁定规则时间单位  D:天  H:小时  M:分  S:秒
     */
    private String lockRuleTimeUnit;
    /**
	 * 密码最大输入错误次数
     */
    private String maxError;
    /**
	 * 密码锁定时间
     */
    private String lockTime;
    /**
	 * 密码锁定时间单位  D:天  H:小时  M:分  S:秒
     */
    private String lockTimeRule;
    /**
	 * 初始密码类型  1:码后位数  2:固定密码
     */
    private String pwdType;
    /**
	 * 码后位数
     */
    private Integer numLength;
    /**
	 * E 可用    D 删除  
     */
    private String status;
    /**
	 * 创建人
     */
    private Long creator;
    /**
	 * 创建时间
     */
    private Date createTime;
    /**
	 * 修改人
     */
    private Long operator;
    /**
	 * 修改时间
     */
    private Date updateTime;
    
    public Long getId(){
        return id;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public String getApplyType(){
        return applyType;
    }
    
    public void setApplyType(String applyType){
        this.applyType = applyType;
    }
    
    public String getIniPwd(){
        return iniPwd;
    }
    
    public void setIniPwd(String iniPwd){
        this.iniPwd = iniPwd;
    }
    
    public String getMinPwd(){
        return minPwd;
    }
    
    public void setMinPwd(String minPwd){
        this.minPwd = minPwd;
    }
    
    public String getMaxPwd(){
        return maxPwd;
    }
    
    public void setMaxPwd(String maxPwd){
        this.maxPwd = maxPwd;
    }
    
    public String getRuleType(){
        return ruleType;
    }
    
    public void setRuleType(String ruleType){
        this.ruleType = ruleType;
    }
    
    public String getLockRuleTime(){
        return lockRuleTime;
    }
    
    public void setLockRuleTime(String lockRuleTime){
        this.lockRuleTime = lockRuleTime;
    }
    
    public String getLockRuleTimeUnit(){
        return lockRuleTimeUnit;
    }
    
    public void setLockRuleTimeUnit(String lockRuleTimeUnit){
        this.lockRuleTimeUnit = lockRuleTimeUnit;
    }
    
    public String getMaxError(){
        return maxError;
    }
    
    public void setMaxError(String maxError){
        this.maxError = maxError;
    }
    
    public String getLockTime(){
        return lockTime;
    }
    
    public void setLockTime(String lockTime){
        this.lockTime = lockTime;
    }
    
    public String getLockTimeRule(){
        return lockTimeRule;
    }
    
    public void setLockTimeRule(String lockTimeRule){
        this.lockTimeRule = lockTimeRule;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public Long getCreator(){
        return creator;
    }
    
    public void setCreator(Long creator){
        this.creator = creator;
    }
    
    public Date getCreateTime(){
        return createTime;
    }
    
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    
    public Long getOperator(){
        return operator;
    }
    
    public void setOperator(Long operator){
        this.operator = operator;
    }
    
    public Date getUpdateTime(){
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

	public String getPwdType() {
		return pwdType;
	}

	public void setPwdType(String pwdType) {
		this.pwdType = pwdType;
	}

	public Integer getNumLength() {
		return numLength;
	}

	public void setNumLength(Integer numLength) {
		this.numLength = numLength;
	}
    
}
