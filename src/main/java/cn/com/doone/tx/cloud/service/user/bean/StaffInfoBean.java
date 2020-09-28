package cn.com.doone.tx.cloud.service.user.bean;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by liujx on 2017/2/17 0017.
 * @Description: (后台人员主表)
 */
@Entity
@Table(name = "ts_staff_info")
@SequenceGenerator(name="common_generator",sequenceName="s_ts_staff_info", // oracle 用到的序列名 s_ts_staff_info
        allocationSize=1,initialValue=1) // 主键生成策略,sequenceName为s_表名,其他不用动
public class StaffInfoBean extends PlatBaseEntity implements Serializable{

    private static final long serialVersionUID = -8518943778913870507L;
    /**登录账号*/
    @Column(name = "STAFF_CODE",length = 32,nullable = true)
    private String staffCode;
    /**人员名称*/
    @Column(name = "STAFF_NAME",length = 32,nullable = true)
    private String staffName;
    /**密码*/
    @Column(name = "PASSWD",length = 32,nullable = true)
    private String passWd;
    /**联系电话*/
    @Column(name = "CONTACT_TEL",length = 32,nullable = true)
    private String contractTel;
    /**性别 1 男 0 女*/
    @Column(name = "SEX",length = 1,nullable = true)
    private Integer sex;
    /**创建人员*/
    @Column(name = "CREATOR",length = 10,nullable = true)
    private Long creator;
    /**图像*/
    @Column(name = "ATTCHID",length = 10,nullable = true)
    private Long attchId;
    /** 类型*/
    @Column(name = "STAFF_TYPE",length = 10,nullable = true)
    private String staffType;

    /**是否为超级管理员*/
    @Column(name = "ISSUPERMANAGER",length = 10,nullable = true)
    private String isSuperManager;

    public String getIsSuperManager() {
        return isSuperManager;
    }

    public void setIsSuperManager(String isSuperManager) {
        this.isSuperManager = isSuperManager;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getPassWd() {
        return passWd;
    }

    public void setPassWd(String passWd) {
        this.passWd = passWd;
    }

    public String getContractTel() {
        return contractTel;
    }

    public void setContractTel(String contractTel) {
        this.contractTel = contractTel;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }


    public Long getAttchId() {
        return attchId;
    }

    public void setAttchId(Long attchId) {
        this.attchId = attchId;
    }
    
    public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	@Override
    public String toString() {
        return "StaffInfoBean{" +
                "staffCode='" + staffCode + '\'' +
                ", staffName='" + staffName + '\'' +
                ", passWd='" + passWd + '\'' +
                ", contractTel='" + contractTel + '\'' +
                ", sex=" + sex +
                ", creator=" + creator +
                ", attchId=" + attchId +
                ", isSuperManager='" + isSuperManager + '\'' +
                "staffType=" + staffType +
                '}';
    }
    
    
}
















