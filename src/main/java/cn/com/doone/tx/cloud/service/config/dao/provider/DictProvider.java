package cn.com.doone.tx.cloud.service.config.dao.provider;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import cn.com.doone.tx.cloud.service.config.evt.dict.DeleteDictEvt;
import cn.com.doone.tx.cloud.service.config.evt.dict.QueryCenterDatabaseEvt;
import cn.com.doone.tx.cloud.service.config.evt.dict.QueryDictConfigEvt;
import cn.com.doone.tx.cloud.service.config.evt.dict.QueryDictEvt;
import cn.com.doone.tx.cloud.tool.service.common.util.SqlGenerateUtil;

public class DictProvider {

	public String doDeleteByParam(DeleteDictEvt deleteDictEvt) {
		SQL sql = new SQL().UPDATE("td_dict set status='D',update_time=#{updateTime}");
		Long id = deleteDictEvt.getDictId();
		String tableName = deleteDictEvt.getTableName();
		String columnName = deleteDictEvt.getColumnName();
		if (id != null && StringUtils.isBlank(tableName) && StringUtils.isBlank(columnName)) {
			sql.WHERE("id = #{dictId}");
		}
		if (id == null && !StringUtils.isBlank(tableName) && !StringUtils.isBlank(columnName)) {
			sql.WHERE("table_name = #{tableName} and column_name= #{columnName}");
		}
		return sql.toString();
	}

	public String queryDictCount(QueryDictEvt queryDictEvt) {
		SQL sql = new SQL().SELECT("count(DISTINCT column_name)").FROM("td_dict");
		String tableName = queryDictEvt.getTableName();
		String columnName = queryDictEvt.getColumnName();
		String columnRemark = queryDictEvt.getColumnRemark();
		if (!StringUtils.isBlank(tableName)) {
			queryDictEvt.setTableName("%" + tableName + "%");
			sql.WHERE("table_name like #{tableName}");
		}
		if (!StringUtils.isBlank(columnName)) {
			queryDictEvt.setColumnName("%" + columnName + "%");
			sql.WHERE("column_Name like #{columnName}");
		}
		if (StringUtils.isNotBlank(columnRemark)) {
			queryDictEvt.setColumnRemark("%" + columnRemark + "%");
			sql.WHERE("column_Remark like #{columnRemark}");
		}
		sql.WHERE("status='E'");
		return sql.toString();
	}

	public String queryDictConfig(QueryDictConfigEvt queryDictConfigEvt) {
		SQL sql = new SQL().SELECT(
				" DISTINCT t.column_name columnName,t.table_name tableName,t.dict_type dictType,t.center_database centerDatabase,MAX(t.update_time) updateTime,t.COLUMN_REMARK columnRemark")
				.FROM("td_dict t");
		String tableName = queryDictConfigEvt.getTableName();
		String columnName = queryDictConfigEvt.getColumnName();
		String columnRemark = queryDictConfigEvt.getColumnRemark();
		if (!StringUtils.isBlank(tableName)) {
			queryDictConfigEvt.setTableName("%" + tableName + "%");
			sql.WHERE("t.table_name like #{tableName}");
		}
		if (!StringUtils.isBlank(columnName)) {
			queryDictConfigEvt.setColumnName("%" + columnName + "%");
			sql.WHERE("t.column_Name like #{columnName}");
		}
		if (StringUtils.isNotBlank(columnRemark)) {
			queryDictConfigEvt.setColumnRemark("%" + columnRemark + "%");
			sql.WHERE("t.column_Remark like #{columnRemark}");
		}

		sql.WHERE("t.status='E'");
		sql.GROUP_BY("t.table_name,t.column_name,t.dict_type,t.center_database,t.COLUMN_REMARK");
		sql.ORDER_BY("updateTime DESC");
		return sql.toString();
	}

	public String queryDictConfigByParam(QueryDictEvt queryDictEvt) {
		SQL sql = new SQL().SELECT("*").FROM("td_dict");
		Long id = queryDictEvt.getDictId();
		Long parentId = queryDictEvt.getParentId();
		String tableName = queryDictEvt.getTableName();
		String columnName = queryDictEvt.getColumnName();
		String dictValue = queryDictEvt.getDictValue();
		String valueRemark = queryDictEvt.getValueRemark();
		String languageType = queryDictEvt.getLanguageType();

		if (id != null) {
			sql.WHERE("id = #{dictId}");
			sql.WHERE("status='E'");
			return sql.toString();
		}
		if (parentId != null) {
			sql.WHERE("parent_id = #{parentId}");
		}
		if (!StringUtils.isBlank(tableName)) {
			sql.WHERE("upper(table_name)=upper(#{tableName})");
		}
		if (!StringUtils.isBlank(columnName)) {
			sql.WHERE("upper(column_name)=upper(#{columnName})");
		}
		if (!StringUtils.isBlank(dictValue)) {
			sql.WHERE("dict_value = #{dictValue}");
		}
		if (!StringUtils.isBlank(valueRemark)) {
			queryDictEvt.setValueRemark("%" + valueRemark + "%");
			sql.WHERE("value_remark = #{valueRemark}");
		}
		if (queryDictEvt.getSort() != null && queryDictEvt.getSort() == 0) {
			sql.WHERE("sort = #{sort}");
		} else if (queryDictEvt.getSort() != null && queryDictEvt.getSort() == -1) {
			sql.WHERE("sort is null");
		}
		sql.WHERE("status='E'");
		sql.ORDER_BY("dict_value ASC,language_type DESC");
		return sql.toString();
	}

	public String queryDictConfigCountByParam(QueryDictEvt queryDictEvt) {
		SQL sql = new SQL().SELECT("count(*)").FROM("td_dict");
		Long id = queryDictEvt.getDictId();
		Long dictExistId = queryDictEvt.getDictExistId();
		Long parentId = queryDictEvt.getParentId();
		String tableName = queryDictEvt.getTableName();
		String columnName = queryDictEvt.getColumnName();
		String dictValue = queryDictEvt.getDictValue();
		String valueRemark = queryDictEvt.getValueRemark();
		String languageTypeAdd = queryDictEvt.getLanguageTypeAdd();
		if (id != null) {
			sql.WHERE("id = #{dictId}");
		}
		// 编辑查重
		if (dictExistId != null) {
			sql.WHERE("id != #{dictExistId}");
		}
		if (parentId != null) {
			sql.WHERE("parent_id = #{parentId}");
		}
		if (!StringUtils.isBlank(tableName)) {
			sql.WHERE("upper(table_name)=upper(#{tableName})");
		}
		if (!StringUtils.isBlank(columnName)) {
			sql.WHERE("upper(column_name)=upper(#{columnName})");
		}
		if (!StringUtils.isBlank(dictValue)) {
			sql.WHERE("dict_value = #{dictValue}");
		}
		if (!StringUtils.isBlank(languageTypeAdd)) {
			sql.WHERE("language_Type = #{languageTypeAdd}");
		}
		if (!StringUtils.isBlank(valueRemark)) {
			sql.WHERE("value_remark like '%" + valueRemark + "%' ");
		}
		if (queryDictEvt.getSort() != null && queryDictEvt.getSort() == 0) {
			sql.WHERE("sort = #{sort}");
		} else if (queryDictEvt.getSort() != null && queryDictEvt.getSort() == -1) {
			sql.WHERE("sort is null");
		}
		sql.WHERE("status='E'");
		return sql.toString();
	}

	public String getCenterTableName(QueryCenterDatabaseEvt queryCenterDatabaseEvt) {

		String centerName = queryCenterDatabaseEvt.getCenterName();
		SQL sql = new SQL();
		if (SqlGenerateUtil.isOracle()) {
			sql.SELECT("upper(t.TABLE_NAME) TABLE_NAME").FROM("All_Tables t");
			if (!StringUtils.isBlank(centerName)) {
				sql.WHERE("t.OWNER=#{centerName}");
			}
		} else if (SqlGenerateUtil.isMysql()) {
			sql.SELECT("upper(TABLE_NAME) TABLE_NAME").FROM("INFORMATION_SCHEMA.TABLES");
			if (!StringUtils.isBlank(centerName)) {
				sql.WHERE("TABLE_SCHEMA=#{centerName}");
			}
		}
		return sql.toString();
	}

	public String getCenterColumnName(QueryCenterDatabaseEvt queryCenterDatabaseEvt) {
		String centerName = queryCenterDatabaseEvt.getCenterName();
		String tableName = queryCenterDatabaseEvt.getTableName();
		SQL sql = new SQL();
		if (SqlGenerateUtil.isOracle()) {
			sql.SELECT("upper(t.COLUMN_NAME) COLUMN_NAME").FROM("dba_TAB_COLUMNS t");
			sql.WHERE("t.TABLE_NAME=#{tableName}");
			if (!StringUtils.isBlank(centerName)) {
				sql.WHERE("t.OWNER=#{centerName}");
			}
		} else if (SqlGenerateUtil.isMysql()) {
			sql.SELECT("upper(COLUMN_NAME) COLUMN_NAME").FROM("information_schema.columns t");
			if (!StringUtils.isBlank(tableName)) {
				sql.WHERE("t.TABLE_NAME=#{tableName}");
			}
			if (!StringUtils.isBlank(centerName)) {
				sql.WHERE("t.TABLE_SCHEMA=#{centerName}");
			}
		}
		return sql.toString();
	}

}
