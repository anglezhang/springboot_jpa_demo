package cn.com.doone.tx.cloud.service.user.evt.common;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;

import java.io.Serializable;

/**
 * Created by liujx on 2017/2/18 0018.
 */
public class QueryCityEvt extends QueryEvt implements Serializable{

    private static final long serialVersionUID = -2100585206595373177L;
    private String provinceCode;
    private String cityCode;
    private String cityName;
    private String areaCode;
    private String areaName;
    //查询类型，为空为返回单个code对应的值
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public String toString() {
        return "QueryCityEvt{" +
                "provinceCode='" + provinceCode + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", areaName='" + areaName + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
