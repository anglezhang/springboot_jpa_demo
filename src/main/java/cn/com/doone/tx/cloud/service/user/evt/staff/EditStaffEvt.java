package cn.com.doone.tx.cloud.service.user.evt.staff;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by liujx on 2017/2/17 0017.
 */
public class EditStaffEvt extends BaseEvt implements Serializable {
	private static final long serialVersionUID = -5538053306677507989L;

	@Field(value = "用户ID", length = 10, nullable = false)
	private Long id;

	@Field(value = "登录账号", length = 32, nullable = true)
	private String staffCode;

	@Field(value = "账号名称", length = 32, nullable = true)
	private String staffName;

	@Field(value = "密码", length = 32, nullable = true)
	private String passWd;

	@Field(value = "联系电话", length = 32, nullable = true)
	private String contractTel;

	@Field(value = "图像Id", length = 10, nullable = true)
	private Long attchId;

	@Field(value = "用户管理编辑标识", length = 10, nullable = true)
	private String isEdit;
	 
    @Field(value="类型", length = 10, nullable = true)
    private String staffType;

	// 扩展信息
	private Map<String, String> extendParam;
	
	//新的权限区域
	private String[] regionCodes;
	
	//旧的权限区域
	private String[] oldRegionCodes;
	
	
	public String[] getRegionCodes() {
		return regionCodes;
	}

	public void setRegionCodes(String[] regionCodes) {
		this.regionCodes = regionCodes;
	}

	public String[] getOldRegionCodes() {
		return oldRegionCodes;
	}

	public void setOldRegionCodes(String[] oldRegionCodes) {
		this.oldRegionCodes = oldRegionCodes;
	}

	public Map<String, String> getExtendParam() {
		return extendParam;
	}

	public void setExtendParam(Map<String, String> extendParam) {
		this.extendParam = extendParam;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAttchId() {
		return attchId;
	}

	public void setAttchId(Long attchId) {
		this.attchId = attchId;
	}

	public String getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}

	public String getStaffType() {
		return staffType;
	}

	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	@Override
	public String toString() {
		return "EditStaffEvt{" + "id=" + id + ", staffCode='" + staffCode
				+ '\'' + ", staffName='" + staffName + '\'' + ", passWd='"
				+ passWd + '\'' + ", contractTel='" + contractTel + '\''
				+ ", attchId=" + attchId + ", extendParam=" + extendParam
				+ ", isEdit='" + isEdit + '\'' + ",staffType=" + staffType + '}';
	}
}
