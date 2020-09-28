package cn.com.doone.tx.cloud.service.user.evt.loginrule;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;

public class AddLoginRuleEvt extends BaseEvt implements Serializable{ 
	
	private static final long serialVersionUID = 8533438081505360093L;
	/**
	 * 适用类型  B:业务系统  A:APP
     */
    @Field(value="适用类型  B:业务系统  A:APP", length = 2, nullable = true)
    private String applyType;
    /**
	 * 初始密码
     */
    @Field(value="初始密码", length = 20, nullable = true)
    private String iniPwd;
    /**
	 * 密码最小长度
     */
    @Field(value="密码最小长度", length = 10, nullable = true)
    private String minPwd;
    /**
	 * 密码最大长度
     */
    @Field(value="密码最大长度", length = 10, nullable = true)
    private String maxPwd;
    /**
	 * 密码组成规则  1.数字、字母、特殊符号组合（字母区分大小写）
     */
    @Field(value="密码组成规则  1.数字、字母、特殊符号组合（字母区分大小写）", length = 200, nullable = true)
    private String ruleType;
    /**
	 * 密码锁定规则时间
     */
    @Field(value="密码锁定规则时间", length = 10, nullable = true)
    private String lockRuleTime;
    /**
	 * 密码锁定规则时间单位  D:天  H:小时  M:分  S:秒
     */
    @Field(value="密码锁定规则时间单位  D:天  H:小时  M:分  S:秒", length = 2, nullable = true)
    private String lockRuleTimeUnit;
    /**
	 * 密码最大输入错误次数
     */
    @Field(value="密码最大输入错误次数", length = 2, nullable = true)
    private String maxError;
    /**
	 * 密码锁定时间
     */
    @Field(value="密码锁定时间", length = 10, nullable = true)
    private String lockTime;
    /**
	 * 密码锁定时间单位  D:天  H:小时  M:分  S:秒
     */
    @Field(value="密码锁定时间单位  D:天  H:小时  M:分  S:秒", length = 2, nullable = true)
    private String lockTimeRule;
    /**
	 * 初始密码类型  1:码后位数  2:固定密码
     */
    @Field(value="初始密码类型  1:码后位数  2:固定密码", length = 2, nullable = true)
    private String pwdType;
    /**
	 * 码后位数
     */
    @Field(value="码后位数", length = 2, nullable = true)
    private Integer numLength;
    /**
	 * 创建人
     */
    @Field(value="创建人", length = 10, nullable = true)
    private Long creator;
    
    
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
    
    public Long getCreator(){
        return creator;
    }
    
    public void setCreator(Long creator){
        this.creator = creator;
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
