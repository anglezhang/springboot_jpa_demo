package cn.com.doone.tx.cloud.service.user.bean;

import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author wanggl
 * @version V1.0
 * @Title: NationStateBean
 * @Package cn.com.doone.tx.cloud.user.service.bean.sys
 * @Description: (用一句话描述该文件做什么)
 * @date 2017/3/27 9:36
 */
@Entity
@Table(name = "td_nation_state")
@SequenceGenerator(name="common_generator",sequenceName="s_td_nation_state", // oracle 用到的序列名 s_tf_custom_group
        allocationSize=1,initialValue=1) // 主键生成策略,sequenceName为s_表名,其他不用动
public class NationStateBean extends PlatBaseEntity implements Serializable {

    private static final long serialVersionUID = 7238094985839592613L;

    /**国家编码*/
    @Column(name = "NATION_CODE",length = 10,nullable = false)
    private String nationCode;
    /**国家名称*/
    @Column(name = "NATION_NAME",length = 10,nullable = false)
    private String nationName;
    /**缩写*/
    @Column(name = "NATION_SHORT",length = 64,nullable = false)
    private String nationShort;
    /**排序*/
    @Column(name = "SORT",length = 11,nullable = false)
    private Integer sort;

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }

    public String getNationShort() {
        return nationShort;
    }

    public void setNationShort(String nationShort) {
        this.nationShort = nationShort;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
