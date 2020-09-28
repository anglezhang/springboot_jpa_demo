package cn.com.doone.tx.cloud.service.user.bean;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "LOGIN_RULE")
@SequenceGenerator(name="common_generator",sequenceName="S_LOGIN_RULE",
        allocationSize=1,initialValue=1)
public class LoginRuleBean extends PlatBaseEntity implements Serializable{ 
	
	private static final long serialVersionUID = -6436971730634921619L;
	/**
	 * 适用类型  B:业务系统  A:APP
     */
    @Column(name = "APPLY_TYPE",length = 2,nullable = true)
    private String applyType;
    /**
	 * 初始密码
     */
    @Column(name = "INI_PWD",length = 20,nullable = true)
    private String iniPwd;
    /**
	 * 密码最小长度
     */
    @Column(name = "MIN_PWD",length = 10,nullable = true)
    private String minPwd;
    /**
	 * 密码最大长度
     */
    @Column(name = "MAX_PWD",length = 10,nullable = true)
    private String maxPwd;
    /**
	 * 密码组成规则  1.数字、字母、特殊符号组合（字母区分大小写）
     */
    @Column(name = "RULE_TYPE",length = 200,nullable = true)
    private String ruleType;
    /**
	 * 密码锁定规则时间
     */
    @Column(name = "LOCK_RULE_TIME",length = 10,nullable = true)
    private String lockRuleTime;
    /**
	 * 密码锁定规则时间单位  D:天  H:小时  M:分  S:秒
     */
    @Column(name = "LOCK_RULE_TIME_UNIT",length = 2,nullable = true)
    private String lockRuleTimeUnit;
    /**
	 * 密码最大输入错误次数
     */
    @Column(name = "MAX_ERROR",length = 2,nullable = true)
    private String maxError;
    /**
	 * 密码锁定时间
     */
    @Column(name = "LOCK_TIME",length = 10,nullable = true)
    private String lockTime;
    /**
	 * 密码锁定时间单位  D:天  H:小时  M:分  S:秒
     */
    @Column(name = "LOCK_TIME_RULE",length = 2,nullable = true)
    private String lockTimeRule;
    /**
	 * 初始密码类型  1:码后位数  2:固定密码
     */
    @Column(name = "PWD_TYPE",length = 2,nullable = true)
    private String pwdType;
    /**
	 * 码后位数
     */
    @Column(name = "NUM_LENGTH",length = 2,nullable = true)
    private Integer numLength;
    
    @Override
	public String toString() {
		String str = "LoginRuleBean [";
              str += "applyType=" + applyType + ",";
              str += "iniPwd=" + iniPwd + ",";
              str += "minPwd=" + minPwd + ",";
              str += "maxPwd=" + maxPwd + ",";
              str += "ruleType=" + ruleType + ",";
              str += "lockRuleTime=" + lockRuleTime + ",";
              str += "lockRuleTimeUnit=" + lockRuleTimeUnit + ",";
              str += "maxError=" + maxError + ",";
              str += "lockTime=" + lockTime + ",";
              str += "lockTimeRule=" + lockTimeRule + ",";
              str += "pwdType=" + pwdType + ",";
              str += "numLength=" + numLength + ",";
		str += "toString=";
		str += super.toString() + "]";
		return str;
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
