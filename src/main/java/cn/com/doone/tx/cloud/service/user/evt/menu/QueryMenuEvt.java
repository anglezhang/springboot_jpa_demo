package cn.com.doone.tx.cloud.service.user.evt.menu;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liujx on 2017/2/17 0017.
 */
public class QueryMenuEvt extends QueryEvt implements Serializable {

	private static final long serialVersionUID = 4379048429988212890L;

	private Long id;

	private Long parentId;

	// @Length( max = 256 , message = "菜单名称长度不能超过256")
	@Field(value = "菜单名称", length = 256, nullable = true)
	private String menuName;

	// @Length( max = 16 , message = "菜单模块编号长度不能超过16")
	@Field(value = "菜单编码", length = 16, nullable = true)
	private String menuCode;

	private Integer isMenu;

	private Long staffId;

	private String scope;

	private Long creator;
	
	private String languageType;
	
	private List<String> menuCodeList;

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public Integer getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(Integer isMenu) {
		this.isMenu = isMenu;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public List<String> getMenuCodeList() {
		return menuCodeList;
	}

	public void setMenuCodeList(List<String> menuCodeList) {
		this.menuCodeList = menuCodeList;
	}

	public String getLanguageType() {
		return languageType;
	}

	public void setLanguageType(String languageType) {
		this.languageType = languageType;
	}

}
