package cn.com.doone.tx.cloud.service.user.info;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/14 0014.
 */
public class CityBean implements Serializable{

    private static final long serialVersionUID = -355412997490894105L;
    /**地市编码*/
    private String cityCode;
    /**地市名称*/
    private String cityName;
    /**地市简称*/
    private String cityShortName;
    /**地市行政区号*/
    private String cityCodeExt;
    /**说明*/
    private String cityRemark;
    /**省份编码*/
    private String provinceCode;
    /**省份编码*/
    private String status;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityShortName() {
        return cityShortName;
    }

    public void setCityShortName(String cityShortName) {
        this.cityShortName = cityShortName;
    }

    public String getCityCodeExt() {
        return cityCodeExt;
    }

    public void setCityCodeExt(String cityCodeExt) {
        this.cityCodeExt = cityCodeExt;
    }

    public String getCityRemark() {
        return cityRemark;
    }

    public void setCityRemark(String cityRemark) {
        this.cityRemark = cityRemark;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CityBean{" +
                "cityCode='" + cityCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", cityShortName='" + cityShortName + '\'' +
                ", cityCodeExt='" + cityCodeExt + '\'' +
                ", cityRemark='" + cityRemark + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
