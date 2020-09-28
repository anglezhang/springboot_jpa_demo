package cn.com.doone.tx.cloud.service.user.evt.menu;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;

/**
 * Created by liujx on 2017/2/17 0017.
 */
public class AddMenuEvt extends BaseEvt implements Serializable {

	private static final long serialVersionUID = -4525609018167831289L;
	// @NotNull(message = "父级id不能为空")
	@Field(value = "父级id", length = 10, nullable = false)
	private Long parentId;

	// @NotNull(message = "菜单名称不能为空")
	// @Length( max = 256 , message = "菜单名称长度不能超过256")
	@Field(value = "菜单名称", length = 256, nullable = false)
	private String menuName;

	private Integer sort;

	// @Length( max = 256 , message = "菜单图标路径长度不能超过256")
	@Field(value = "菜单图标路径", length = 256, nullable = true)
	private String menuImg;

	// @Length( max = 256 , message = "提示信息长度不能超过256")
	@Field(value = "提示信息长度", length = 256, nullable = true)
	private String menuTips;

	// @Length( max = 256 , message = "对应链接长度不能超过256")
	@Field(value = "对应链接长度", length = 256, nullable = true)
	private String menuUrl;

	// @NotNull(message = "菜单模块编号不能为空")
	// @Length( max = 64 , message = "对应链接长度不能超过16")
	@Field(value = "菜单模块编号", length = 16, nullable = true)
	private String menuCode;

	@Field(value = "是否显示成分割分", length = 1, nullable = true)
	private Integer isSeperator;

	// @NotNull(message = "菜单类型不能为空")
	@Field(value = "菜单类型", length = 1, nullable = true)
	private Integer isMenu;

	@Field(value = "是否本系统", length = 1, nullable = true)
	private Integer isLocal;

	// @Length(max = 20 , message = "打开方式长度不能超过20")
	@Field(value = "打开方式", length = 20, nullable = true)
	private String openType;

	@Field(value = "创建者", length = 10, nullable = true)
	private Long creator;

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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getMenuImg() {
		return menuImg;
	}

	public void setMenuImg(String menuImg) {
		this.menuImg = menuImg;
	}

	public String getMenuTips() {
		return menuTips;
	}

	public void setMenuTips(String menuTips) {
		this.menuTips = menuTips;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public Integer getIsSeperator() {
		return isSeperator;
	}

	public void setIsSeperator(Integer isSeperator) {
		this.isSeperator = isSeperator;
	}

	public Integer getIsMenu() {
		return isMenu;
	}

	public void setIsMenu(Integer isMenu) {
		this.isMenu = isMenu;
	}

	public Integer getIsLocal() {
		return isLocal;
	}

	public void setIsLocal(Integer isLocal) {
		this.isLocal = isLocal;
	}

	public String getOpenType() {
		return openType;
	}

	public void setOpenType(String openType) {
		this.openType = openType;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}
}
