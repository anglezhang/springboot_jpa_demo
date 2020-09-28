package cn.com.doone.tx.cloud.service.wechat.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;
@Entity
@Table(name = "wx_corp")
@SequenceGenerator(name = "common_generator", sequenceName = "s_wx_corp", // oracle 用到的序列名 s_config_code
		allocationSize = 1, initialValue = 1) // 主键生成策略,sequenceName为s_表名,其他不用动
public class WxCorpBean extends PlatBaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7547978086525228103L;
	
	@Column(name = "WX_CORP_ID", length = 30, nullable = false)
	private String wxCorpId;
    
	@Column(name = "CORP_NAME", length = 100, nullable = true)
    private String corpName;
    
	@Column(name = "MAIL_SECRET", length = 100, nullable = true)
    private String mailSecret;
    
	@Column(name = "CONTACT_SECRET", length = 100, nullable = true)
    private String contactSecret;

	public String getWxCorpId() {
		return wxCorpId;
	}

	public void setWxCorpId(String wxCorpId) {
		this.wxCorpId = wxCorpId;
	}

	public String getCorpName() {
		return corpName;
	}

	public void setCorpName(String corpName) {
		this.corpName = corpName;
	}

	public String getMailSecret() {
		return mailSecret;
	}

	public void setMailSecret(String mailSecret) {
		this.mailSecret = mailSecret;
	}

	public String getContactSecret() {
		return contactSecret;
	}

	public void setContactSecret(String contactSecret) {
		this.contactSecret = contactSecret;
	}
	
	

}
