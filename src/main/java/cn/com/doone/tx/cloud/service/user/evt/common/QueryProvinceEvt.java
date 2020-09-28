package cn.com.doone.tx.cloud.service.user.evt.common;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;

import java.io.Serializable;

/**
 * Created by liujx on 2017/2/18 0018.
 */
public class QueryProvinceEvt extends QueryEvt implements Serializable {

	private static final long serialVersionUID = 7034220879596755997L;
	private String provinceCode;

	private String nationCode;

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getNationCode() {
		return nationCode;
	}

	public void setNationCode(String nationCode) {
		this.nationCode = nationCode;
	}
}
