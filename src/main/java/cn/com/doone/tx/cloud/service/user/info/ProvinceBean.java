package cn.com.doone.tx.cloud.service.user.info;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/14 0014.
 */
public class ProvinceBean implements Serializable{

    private static final long serialVersionUID = -5884187990343109324L;
    /**省份编码*/
    private  String provinceCode;
    /**省份拼音缩写*/
    private  String provinceShort;
    /**省份名称*/
    private  String provinceName;
    /**状态 'E'可用 'D'不可用*/
    private  String status;
    /**排序*/
    private  Integer sort;
    /**省级标识码(电信内部)*/
    private  String  dstorgid;

    private  String nationCode;

    /**下属城市*/
    private List<CityBean> cityList;

    public List<CityBean> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityBean> cityList) {
        this.cityList = cityList;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceShort() {
        return provinceShort;
    }

    public void setProvinceShort(String provinceShort) {
        this.provinceShort = provinceShort;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDstorgid() {
        return dstorgid;
    }

    public void setDstorgid(String dstorgid) {
        this.dstorgid = dstorgid;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    @Override
    public String toString() {
        return "ProvinceBean{" +
                "provinceCode='" + provinceCode + '\'' +
                ", provinceShort='" + provinceShort + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", status='" + status + '\'' +
                ", sort=" + sort +
                ", dstorgid='" + dstorgid + '\'' +
                ", nationCode='" + nationCode + '\'' +
                ", cityList=" + cityList +
                '}';
    }
}
