package cn.com.doone.tx.cloud.service.user.info;

import java.io.Serializable;
import java.util.List;
public class RegionBean implements Serializable {

    /**大区编码*/
    private String regionCode;
    /**大区拼音缩写*/
    private String regionShort;
    /**大区名称*/
    private String regionName;
    /**排序*/
    private Integer sort;

    /**下属省份*/
    private List<ProvinceBean> provinceList;

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionShort() {
        return regionShort;
    }

    public void setRegionShort(String regionShort) {
        this.regionShort = regionShort;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<ProvinceBean> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<ProvinceBean> provinceList) {
        this.provinceList = provinceList;
    }
}
