package cn.com.doone.tx.cloud.service.user.evt.staff;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by liujx on 2017/2/17 0017.
 */
public class AddStaffEvt extends BaseEvt implements Serializable {

	private static final long serialVersionUID = -5538053306677507989L;

	@Field(value = "登录账号", length = 32, nullable = false)
	private String staffCode;

	@Field(value = "账号名称", length = 32, nullable = false)
	private String staffName;

	@Field(value = "密码长度", length = 32, nullable = true)
	private String passWd;

	@Field(value = "联系电话", length = 32, nullable = true)
	private String contractTel;

	@Field(value = "用户组ID", length = 10, nullable = true)
	private Long groupId;
	/** 创建人 */
	private Long creator;
	/** 图像 */
	@Field(value = "图像ID", length = 10, nullable = true)
	private Long attchId;
	/**
	 * 类型
     */
    @Field(value="类型", length = 10, nullable = true)
    private String staffType;
	
	// 添加用户扩展表所用map
	private Map<String, String> extendParam;
	
	//权限区域信息
	private String[] regionCodes;

	public String[] getRegionCodes() {
		return regionCodes;
	}

	public void setRegionCodes(String[] regionCodes) {
		this.regionCodes = regionCodes;
	}

	public Map<String, String> getExtendParam() {
		return extendParam;
	}

	public void setExtendParam(Map<String, String> extendParam) {
		this.extendParam = extendParam;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
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
	
}
