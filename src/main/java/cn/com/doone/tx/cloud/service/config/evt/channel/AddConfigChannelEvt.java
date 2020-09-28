package cn.com.doone.tx.cloud.service.config.evt.channel;

import cn.com.doone.tx.cloud.tool.service.common.base.BaseEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

/**
 * Created by APPLE on 2017/2/22.
 */
public class AddConfigChannelEvt extends BaseEvt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4278225997835844714L;
	@Field(nullable = false, length = 150, value = "渠道编码不能为空")
	private String channelCode;
	@Field(nullable = false, length = 100, value = "渠道名称不能为空")
	private String channelName;
	@Field(nullable = false, length = 100, value = "parentId不能为空")
	private Integer parentId;
	@Field(nullable = false, length = 100, value = "渠道归属不能为空")
	private String channelAscription;
	@Field(nullable = false, length = 100, value = "屏幕类型不能为空")
	private String screenType;
	@Field(nullable = false, length = 100, value = "渠道扩展类型不能为空")
	private String extendType;

	private String channelNameEng;
	@Field(nullable = false, length = 2, value = "是否CMS站点不能为空")
	private Integer isCms;
	@Field(nullable = false, length = 255, value = "渠道类型不能为空")
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

	public Integer getIsCms() {
		return isCms;
	}

	public void setIsCms(Integer isCms) {
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
		return "AddConfigChannelEvt{" + "channelCode='" + channelCode + '\'' + ", channelName='" + channelName + '\''
				+ ", parentId=" + parentId + ", channelAscription='" + channelAscription + '\'' + ", screenType='"
				+ screenType + '\'' + ", extendType='" + extendType + '\'' + ", channelNameEng='" + channelNameEng
				+ '\'' + ", isCms=" + isCms + ", channelType='" + channelType + '\'' + '}';
	}
}
