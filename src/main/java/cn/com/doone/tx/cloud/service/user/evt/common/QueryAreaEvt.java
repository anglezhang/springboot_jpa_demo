package cn.com.doone.tx.cloud.service.user.evt.common;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;

import java.io.Serializable;

/**
 * Created by liujx on 2017/2/18 0018.
 */
public class QueryAreaEvt extends QueryEvt implements Serializable {

	private static final long serialVersionUID = -2349676971723168417L;

	private String cityCode;

	private String areaCode;

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
}
