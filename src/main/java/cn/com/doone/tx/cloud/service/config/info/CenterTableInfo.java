package cn.com.doone.tx.cloud.service.config.info;

/**
 * Created by Administrator on 2017/1/19 0019.
 */
public class CenterTableInfo {

    private String tableName;

    private String columnName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public String toString() {
        return "CenterTableInfo{" +
                "tableName='" + tableName + '\'' +
                ", columnName='" + columnName + '\'' +
                '}';
    }
}
