package cn.com.doone.tx.cloud.service.user.info;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/14 0014.
 */
public class AreaBean implements Serializable{

    private static final long serialVersionUID = -5424231985275655911L;
    /**区域编码*/
    private String areaCode;
    /**区域名称*/
    private String areaName;
    /**地市编码*/
    private String cityCode;
    /**BSS对应的AREAID*/
    private String BSSAreaId;
    /**是否实体营业厅区域，Y-是、N-不是*/
    private String ifBusi;
    /**状态 'E'可用 'D'不可用*/
    private String status;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getBSSAreaId() {
        return BSSAreaId;
    }

    public void setBSSAreaId(String BSSAreaId) {
        this.BSSAreaId = BSSAreaId;
    }

    public String getIfBusi() {
        return ifBusi;
    }

    public void setIfBusi(String ifBusi) {
        this.ifBusi = ifBusi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AreaBean{" +
                "areaCode='" + areaCode + '\'' +
                ", areaName='" + areaName + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", BSSAreaId='" + BSSAreaId + '\'' +
                ", ifBusi='" + ifBusi + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
