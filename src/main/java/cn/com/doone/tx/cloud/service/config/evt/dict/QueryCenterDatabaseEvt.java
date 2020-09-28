package cn.com.doone.tx.cloud.service.config.evt.dict;

import cn.com.doone.tx.cloud.tool.service.common.base.QueryEvt;
import cn.com.doone.tx.cloud.tool.service.common.valid.Field;

/**
 * Created by APPLE on 2017/2/22.
 */
public class QueryCenterDatabaseEvt extends QueryEvt{

    /**
	 * 
	 */
	private static final long serialVersionUID = -2026785163120350959L;

	@Field(nullable = false , length = 255 ,value = "中心名称不能为空")
    private String centerName;

    private String tableName;

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
