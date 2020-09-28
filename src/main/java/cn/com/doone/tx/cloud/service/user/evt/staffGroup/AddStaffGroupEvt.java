package cn.com.doone.tx.cloud.service.user.evt.staffGroup;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by liujx on 2017/2/17 0017.
 */
public class AddStaffGroupEvt extends BaseEvt implements Serializable {

	private static final long serialVersionUID = -7135302781777232039L;
	private Long parentId;

	private String groupName;

	private String groupRemark;

	private Integer sort;

	private String cityCode;

	private String areaCode;

	private String provinceCode;

	private String streetCode;

	@Field(value = "用户组类型", nullable = false)
	private Integer groupType;

	/** 创建人 */
	private Long creator;

	// 是否返回信息
	private boolean isReturnInfo;

	// 添加用户扩展表所用map
	private Map<String, String> extendParam;

	public Map<String, String> getExtendParam() {
		return extendParam;
	}

	public void setExtendParam(Map<String, String> extendParam) {
		this.extendParam = extendParam;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupRemark() {
		return groupRemark;
	}

	public void setGroupRemark(String groupRemark) {
		this.groupRemark = groupRemark;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getStreetCode() {
		return streetCode;
	}

	public void setStreetCode(String streetCode) {
		this.streetCode = streetCode;
	}

	public Integer getGroupType() {
		return groupType;
	}

	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public boolean isReturnInfo() {
		return isReturnInfo;
	}

	public void setReturnInfo(boolean isReturnInfo) {
		this.isReturnInfo = isReturnInfo;
	}

}
