package cn.com.doone.tx.cloud.service.config.bean;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by liujx on 2017/2/21 0021.
 */
@Entity
@Table(name = "td_channel")
@SequenceGenerator(name="common_generator",sequenceName="s_td_channel", // oracle 用到的序列名 s_config_menu
        allocationSize=1,initialValue=1) // 主键生成策略,sequenceName为s_表名,其他不用动
// mysql 的主键策略
public class ConfigChannelBean extends PlatBaseEntity implements Serializable{

    @Column(name = "PARENT_ID",length = 10,nullable = true )
    private Integer parentId;

    @Column(name = "CHANNEL_CODE",length = 150,nullable = true )
    private String channelCode;

    @Column(name = "CHANNEL_NAME",length = 100,nullable = true)
    private String channelName;

    @Column(name = "CHANNEL_NAME_ENG",length = 50,nullable = true)
    private String channelNameEng;

    @Column(name = "CHANNEL_ASCRIPTION",length = 10,nullable = true)
    private String channelAscription;

    @Column(name = "SCREEN_TYPE",length = 10,nullable = true)
    private String screenType;

    @Column(name = "EXTEND_TYPE",length = 10,nullable = true)
    private String extendType;

    @Column(name = "IS_CMS",length = 2,nullable = true)
    private int isCms;

    @Column(name = "CHANNEL_TYPE",length = 255,nullable = true)
    private String channelType;

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelNameEng() {
        return channelNameEng;
    }

    public void setChannelNameEng(String channelNameEng) {
        this.channelNameEng = channelNameEng;
    }

    public int getIsCms() {
        return isCms;
    }

    public void setIsCms(int isCms) {
        this.isCms = isCms;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getChannelAscription() {
        return channelAscription;
    }

    public void setChannelAscription(String channelAscription) {
        this.channelAscription = channelAscription;
    }

    public String getScreenType() {
        return screenType;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    public String getExtendType() {
        return extendType;
    }

    public void setExtendType(String extendType) {
        this.extendType = extendType;
    }

    @Override
    public String toString() {
        return "ConfigChannelBean{" +
                "parentId=" + parentId +
                ", channelCode='" + channelCode + '\'' +
                ", channelName='" + channelName + '\'' +
                ", channelNameEng='" + channelNameEng + '\'' +
                ", channelAscription='" + channelAscription + '\'' +
                ", screenType='" + screenType + '\'' +
                ", extendType='" + extendType + '\'' +
                ", isCms=" + isCms +
                ", channelType='" + channelType + '\'' +
                '}';
    }
}
