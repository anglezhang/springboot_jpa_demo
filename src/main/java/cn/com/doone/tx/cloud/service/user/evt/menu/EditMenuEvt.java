package cn.com.doone.tx.cloud.service.user.evt.menu;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

import java.io.Serializable;

/**
 * Created by liujx on 2017/2/17 0017.
 */
public class EditMenuEvt extends BaseEvt implements Serializable {

	private static final long serialVersionUID = 5946594148279628900L;
	// @NotNull(message = "菜单id不能为空")
	@Field(value = "菜单id", length = 10, nullable = false)
	private Long id;
	
	@Field(value = "菜单父级id", length = 38, nullable = true)
	private Long parentId;

	// @Length( max = 256 , message = "菜单名称长度不能超过256")
	@Field(value = "菜单名称", length = 256, nullable = true)
	private String menuName;

	@Field(value = "排序", length = 10, nullable = true)
	private Integer sort;

	// @Length( max = 256 , message = "菜单图标路径长度不能超过256")
	@Field(value = "菜单图标路径", length = 256, nullable = true)
	private String menuImg;

	// @Length( max = 256 , message = "提示信息长度不能超过256")
	@Field(value = "提示信息", length = 256, nullable = true)
	private String menuTips;

	// @Length( max = 256 , message = "对应链接长度不能超过256")
	@Field(value = "对应链接长度", length = 256, nullable = true)
	private String menuUrl;

	// @Length( max = 64 , message = "对应链接长度不能超过16")
	@Field(value = "编码", length = 16, nullable = true)
	private String menuCode;

	private Integer isSeperator;

	private Integer isMenu;

	private Integer isLocal;

	// @Length(max = 20 , message = "打开方式长度不能超过20")
	@Field(value = "打开方式", length = 20, nullable = true)
	private String openType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "EditMenuEvt [id=" + id + ", parentId=" + parentId + ", menuName=" + menuName + ", sort=" + sort
				+ ", menuImg=" + menuImg + ", menuTips=" + menuTips + ", menuUrl=" + menuUrl + ", menuCode=" + menuCode
				+ ", isSeperator=" + isSeperator + ", isMenu=" + isMenu + ", isLocal=" + isLocal + ", openType="
				+ openType + "]";
	}

	
}
