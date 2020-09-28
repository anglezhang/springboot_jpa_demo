package cn.com.doone.tx.cloud.service.user.bean;


import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * Created by liujx on 2017/2/16 0016.
 * @Description: (后台菜单主表)
 */
@Entity
@Table(name = "ts_menu_info")
@SequenceGenerator(name="common_generator",sequenceName="s_ts_menu_info", // oracle 用到的序列名 s_ts_menu_info
        allocationSize=1,initialValue=1) // 主键生成策略,sequenceName为s_表名,其他不用动
public class MenuInfoBean extends PlatBaseEntity implements Serializable{


    private static final long serialVersionUID = 6889352408271995942L;
    /**父节点*/
    @Column(name = "PARENT_ID",length = 10,nullable = true)
    private Long parentId;
    /**菜单名称*/
    @Column(name = "MENU_NAME",length = 256,nullable = true)
    private String menuName;
    /**菜单顺序号*/
    @Column(name = "SORT",length = 10,nullable = true)
    private Integer sort;
    /**菜单图标路径*/
    @Column(name = "MENU_IMG",length = 256,nullable = true)
    private String menuImg;
    /**提示信息*/
    @Column(name = "MENU_TIPS",length = 256,nullable = true)
    private String menuTips;
    /**对应链接*/
    @Column(name = "MENU_URL",length = 256,nullable = true)
    private String menuUrl;
    /**菜单模块编号(适用于页面配置模块编号进行权限控制的情况)*/
    @Column(name = "MENU_CODE",length = 64,nullable = true)
    private String menuCode;
    /**是否显示成分割分*/
    @Column(name = "IS_SEPERATOR",length = 1,nullable = true)
    private Integer isSeperator;
    /**是菜单还是功能 1菜单 2功能*/
    @Column(name = "IS_MENU",length = 1,nullable = true)
    private Integer isMenu;
    /**是否本系统菜单 1是 0否*/
    @Column(name = "IS_LOCAL",length = 1,nullable = true)
    private Integer isLocal;
    /**打开方式 _self _blank*/
    @Column(name = "OPEN_TYPE",length = 20,nullable = true)
    private String openType;
    /**创建人员*/
    @Column(name = "CREATOR",length = 10,nullable = true)
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


    @Override
    public String toString() {
        return "MenuInfoBean{" +
                "parentId=" + parentId +
                ", menuName='" + menuName + '\'' +
                ", sort=" + sort +
                ", menuImg='" + menuImg + '\'' +
                ", menuTips='" + menuTips + '\'' +
                ", menuUrl='" + menuUrl + '\'' +
                ", menuCode='" + menuCode + '\'' +
                ", isSeperator=" + isSeperator +
                ", isMenu=" + isMenu +
                ", isLocal=" + isLocal +
                ", openType='" + openType + '\'' +
                ", creator=" + creator +
                '}';
    }
}
