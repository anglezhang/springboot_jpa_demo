package cn.com.doone.tx.cloud.service.config.evt.extend;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/21 0021.
 */
public class QueryExtendInfoEvt  extends QueryEvt implements Serializable{


    /**
	 * 
	 */
	private static final long serialVersionUID = 6218429757390575610L;

	private String tableName;
    
    private String extendCode;

	private Long baseId;
	
	private String fieldCode;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getExtendCode() {
		return extendCode;
	}

	public void setExtendCode(String extendCode) {
		this.extendCode = extendCode;
	}
	
    public Long getBaseId() {
        return baseId;
    }

    public void setBaseId(Long baseId) {
        this.baseId = baseId;
    }

	public String getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	
}
