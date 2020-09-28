package cn.com.doone.tx.cloud.service.user.bean;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "tl_operate_log")
@SequenceGenerator(name="common_generator",sequenceName="s_tl_operate_log", // oracle 用到的序列名 s_tf_custom_group
        allocationSize=1,initialValue=1) // 主键生成策略,sequenceName为s_表名,其他不用动
public class OperateLogBean extends PlatBaseEntity implements Serializable {

    private static final long serialVersionUID = 6329709914932448114L;
    /**操作URL*/
    @Column(name = "OPERATE_URL",length = 255,nullable = false)
    private String operateUrl;
    /**操作说明*/
    @Column(name = "OPERATE_DESC",length = 255)
    private String operateDesc;
    /**操作入参*/
    @Column(name = "IN_PARAM",length = 4000)
    private String inParam;
    /**操作出参*/
    @Column(name = "OUT_PARAM",length = 4000)
    private String outParam;
    /** 操作人员名称 */
    @Column(name = "OPERATE_NAME",length = 255,nullable = false)
    private String operateName;
    /**操作结果 ‘0’成功 其他失败*/
    @Column(name = "OPERATE_RESULT",length = 10,nullable = false)
    private String operateResult;
    /**操作IP*/
    @Column(name = "OPERATE_IP",length = 20,nullable = false)
    private String operateIp;
    /**系统编码*/
    @Column(name = "SYSTEM_CODE",length = 20)
    private String systemCode;
    /**失败原因*/
    @Column(name = "ERR_MSG",length = 1000)
    private String errMsg;
    @Column(name = "MENU_CODE",length = 32)
    private String menuCode;
    
	public String getOperateUrl() {
		return operateUrl;
	}
	public void setOperateUrl(String operateUrl) {
		this.operateUrl = operateUrl;
	}
	public String getInParam() {
		return inParam;
	}
	public void setInParam(String inParam) {
		this.inParam = inParam;
	}
	public String getOutParam() {
		return outParam;
	}
	public void setOutParam(String outParam) {
		this.outParam = outParam;
	}
	public String getOperateName() {
		return operateName;
	}
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
	public String getOperateResult() {
		return operateResult;
	}
	public void setOperateResult(String operateResult) {
		this.operateResult = operateResult;
	}
	public String getOperateIp() {
		return operateIp;
	}
	public void setOperateIp(String operateIp) {
		this.operateIp = operateIp;
	}
	
	public String getOperateDesc() {
		return operateDesc;
	}
	public void setOperateDesc(String operateDesc) {
		this.operateDesc = operateDesc;
	}
	
	public String getSystemCode() {
		return systemCode;
	}
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	@Override
	public String toString() {
		return "OperateLogBean [operateUrl=" + operateUrl + ", inParam=" + inParam + ", outParam=" + outParam
				+ ", operateName=" + operateName + ", operateResult=" + operateResult + ", operateIp=" + operateIp
				+ "]";
	}
    
    
}
