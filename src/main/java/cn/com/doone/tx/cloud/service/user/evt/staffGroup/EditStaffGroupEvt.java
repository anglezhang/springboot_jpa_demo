package cn.com.doone.tx.cloud.service.user.evt.staffGroup;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by liujx on 2017/2/18 0018.
 */

public class EditStaffGroupEvt extends BaseEvt implements Serializable {

	private static final long serialVersionUID = -458487738250215877L;
	private Long id;

	private Long parentId;

	private Long groupId;

	private String groupName;

	private String groupRemark;

	private Integer sort;

	private String cityCode;

	private String areaCode;

	private String provinceCode;

	private String streetCode;

	private Integer groupType;

	private Map<String, String> extendParam;

	public Map<String, String> getExtendParam() {
		return extendParam;
	}

	public void setExtendParam(Map<String, String> extendParam) {
		this.extendParam = extendParam;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "EditStaffGroupEvt{" +
				"id=" + id +
				", parentId=" + parentId +
				", groupId=" + groupId +
				", groupName='" + groupName + '\'' +
				", groupRemark='" + groupRemark + '\'' +
				", sort=" + sort +
				", cityCode='" + cityCode + '\'' +
				", areaCode='" + areaCode + '\'' +
				", provinceCode='" + provinceCode + '\'' +
				", streetCode='" + streetCode + '\'' +
				", groupType=" + groupType +
				", extendParam=" + extendParam +
				'}';
	}
}
