package cn.com.doone.tx.cloud.service.user.bean;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by administrator on 2017/8/29 
 * @Description: (用户账号锁定表)
 */
@Entity
@Table(name = "tf_login_lock")
@SequenceGenerator(name="common_generator",sequenceName="s_tf_login_lock", // oracle 用到的序列名 s_ts_staff_info
        allocationSize=1,initialValue=1) // 主键生成策略,sequenceName为s_表名,其他不用动
public class LoginLockBean extends PlatBaseEntity implements Serializable{

    private static final long serialVersionUID = -8518943778913870222L;
    /**账号*/
    @Column(name = "ACCOUNT",length = 64,nullable = false)
    private String account;
    /**账号类型 1：后台   2：前台*/
    @Column(name = "ACCOUNT_TYPE",length = 2,nullable = false)
    private String accountType;
    /**锁定时间*/
    @Column(name = "CREATE_TIME",nullable = false)
    private Date createTime;
    /**解锁时间*/
    @Column(name = "UNLOCK_TIME")
    private Date unlockTime;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUnlockTime() {
		return unlockTime;
	}
	public void setUnlockTime(Date unlockTime) {
		this.unlockTime = unlockTime;
	}
	
}