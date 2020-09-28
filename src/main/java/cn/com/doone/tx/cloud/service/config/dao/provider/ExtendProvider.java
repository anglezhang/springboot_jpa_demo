package cn.com.doone.tx.cloud.service.config.dao.provider;

import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import cn.com.doone.tx.cloud.service.config.evt.extend.TdExtendField;

/**
 * Created by loser on 2017/2/14.
 */

public class ExtendProvider {


    public String doAdd(TdExtendField tdExtendField) throws Exception {
        Class<? extends TdExtendField> clazz = tdExtendField.getClass();
        Table annotation = (Table) clazz.getAnnotation(Table.class);
        SQL sql = new SQL().INSERT_INTO(annotation.name());
        sql.VALUES("TABLE_NAME", "'" + tdExtendField.getTableName() + "'");
        String fieldName = tdExtendField.getFieldName();
        String fieldCode = tdExtendField.getFieldCode();
        Integer fieldValueType = tdExtendField.getFieldValueType();
        Integer fieldValueLength = tdExtendField.getFieldValueLength();
        String fieldTitle = tdExtendField.getFieldTitle();
        String fieldRemark = tdExtendField.getFieldRemark();
        Integer fieldType = tdExtendField.getFieldType();
        Integer sort = tdExtendField.getSort();
        Integer isMust = tdExtendField.getIsMust();
//        Long operator = tdExtendField.getOperator();
        String regularExpress = tdExtendField.getRegularExpress();
        String dictColumnName = tdExtendField.getDictColumnName();
        String dictTableName = tdExtendField.getDictTableName();
        String status = tdExtendField.getStatus();
        if (fieldName != null)
            sql.VALUES("FIELD_NAME", "'" + tdExtendField.getFieldName() + "'");
        if (fieldCode != null)
            sql.VALUES("FIELD_CODE", "'" + fieldCode + "'");
        if (fieldValueType != null)
            sql.VALUES("FIELD_VALUE_TYPE", fieldValueType + "");
        if (fieldValueLength != null)
            sql.VALUES("FIELD_VALUE_LENGTH", fieldValueLength + "");
        if (fieldTitle != null)
            sql.VALUES("FIELD_TITLE", "'" + fieldTitle + "'");
        if (fieldRemark != null)
            sql.VALUES("FIELD_REMARK", "'" + fieldRemark + "'");
        if (fieldType != null)
            sql.VALUES("FIELD_TYPE", fieldType + "");
        if (sort != null)
            sql.VALUES("SORT", sort + "");
        if (isMust != null)
            sql.VALUES("IS_MUST", isMust + "");
        if (regularExpress != null)
            sql.VALUES("REGULAR_EXPRESS", "'" + regularExpress + "'");
        if (dictTableName != null)
            sql.VALUES("DICT_TABLE_NAME", "'" + dictTableName + "'");
        if (dictColumnName != null)
            sql.VALUES("DICT_COLUMN_NAME", "'" + dictColumnName + "'");
        if (status != null)
            sql.VALUES("STATUS", "'" + status + "'");
//        if (operator != null)
//            sql.VALUES("OPERATOR", operator + "");
        sql.VALUES("CREATE_TIME", "now()");
        sql.VALUES("UPDATE_TIME", "now()");
        return sql.toString();
    }


    public String getExtendByParam(TdExtendField tdExtendField) {
        SQL sql = new SQL().SELECT("t.table_name tableName,Max(update_time) updateTime,t.EXTEND_CODE extendCode").FROM("td_extend_field t").GROUP_BY("t.table_name,t.EXTEND_CODE");
        String tableName = tdExtendField.getTableName();
        sql.WHERE("t.status = 'E'");
        if (!StringUtils.isBlank(tableName)) {
            sql.WHERE("t.TABLE_NAME LIKE '%" + tableName + "%' ");
        }
        return sql.toString();
    }

    public String getExtendCount(TdExtendField tdExtendField) {
        String sqlTable = "SELECT COUNT(1) ac  FROM td_extend_field t ";
        String tableName = tdExtendField.getTableName();
        sqlTable +=  "WHERE t.status='E' ";
        if (!StringUtils.isBlank(tableName)) {
            sqlTable += " and t.TABLE_NAME LIKE '%" + tableName + "%' ";
        }
        sqlTable += "GROUP BY t.table_name, t.EXTEND_CODE";
        String sql = "SELECT COUNT(tn.ac) acount FROM (" + sqlTable + ") tn";
        return sql;
    }

    public String queryExtendByTableName(TdExtendField tdExtendField) {
        SQL sql = new SQL().SELECT("FIELD_NAME fieldName,FIELD_CODE fieldCode,FIELD_VALUE_TYPE" +
                " fieldValueType,FIELD_VALUE_LENGTH fieldValueLength,FIELD_TITLE fieldTitle," +
                "FIELD_REMARK fieldRemark,FIELD_TYPE fieldType,SORT sort,IS_MUST isMust," +
                "REGULAR_EXPRESS regularExpress,DICT_TABLE_NAME dictTableName," +
                "DICT_COLUMN_NAME dictColumnName,STATUS status,CREATE_TIME createTime,UPDATE_TIME updateTime," +
                "OPERATOR operator").FROM("td_extend_field t");
        String status = tdExtendField.getStatus();
        if(status==null){
            sql.WHERE("status = 'E'");
        }
        sql.WHERE("t.COL_TYPE is null");
        String tableName = tdExtendField.getTableName();
        String fieldName = tdExtendField.getFieldName();
        if (!StringUtils.isBlank(tableName)) {
            sql.WHERE("t.TABLE_NAME = '" + tableName + "'");
        }
        if(!StringUtils.isBlank(fieldName)){
            sql.WHERE("t.FIELD_NAME = '" + fieldName +"'");
        }
        if(StringUtils.isNotBlank(tdExtendField.getExtendCode())){
    		sql.WHERE("EXTEND_CODE=#{extendCode}");
    	}else{
    		sql.WHERE("EXTEND_CODE is null");
    	}
        return sql.toString();
    }


    public String doEdit(TdExtendField tdExtendField) throws Exception {
        SQL sql = new SQL().UPDATE("td_extend_field");
        sql.WHERE("TABLE_NAME = '" + tdExtendField.getTableName() + "'");
        if(StringUtils.isNotBlank(tdExtendField.getExtendCode())){
    		sql.WHERE("EXTEND_CODE=#{extendCode}");
    	}else{
    		sql.WHERE("EXTEND_CODE is null");
    	}
        sql.WHERE("FIELD_NAME = '" + tdExtendField.getFieldName() + "'");
        String fieldCode = tdExtendField.getFieldCode();
        Integer fieldValueType = tdExtendField.getFieldValueType();
        Integer fieldValueLength = tdExtendField.getFieldValueLength();
        String fieldTitle = tdExtendField.getFieldTitle();
        String fieldRemark = tdExtendField.getFieldRemark();
        Integer fieldType = tdExtendField.getFieldType();
        Integer sort = tdExtendField.getSort();
        Integer isMust = tdExtendField.getIsMust();
//        Long operator = tdExtendField.getOperator();
        String regularExpress = tdExtendField.getRegularExpress();
        String dictColumnName = tdExtendField.getDictColumnName();
        String dictTableName = tdExtendField.getDictTableName();
        String status = tdExtendField.getStatus();
        if (fieldCode != null)
            sql.SET("FIELD_CODE = '"+ fieldCode + "'");
        if (fieldValueType != null)
            sql.SET("FIELD_VALUE_TYPE = " + fieldValueType);
        if (fieldValueLength != null)
            sql.SET("FIELD_VALUE_LENGTH = " + fieldValueLength);
        if (fieldTitle != null)
            sql.SET("FIELD_TITLE = '" + fieldTitle + "'");
        if (fieldRemark != null)
            sql.SET("FIELD_REMARK = '" + fieldRemark + "'");
        if (fieldType != null)
            sql.SET("FIELD_TYPE = " + fieldType);
        if (sort != null)
            sql.SET("SORT = " + sort );
        if (isMust != null)
            sql.SET("IS_MUST = " + isMust);
        if (regularExpress != null)
            sql.SET("REGULAR_EXPRESS = '" + regularExpress + "'");
        if (dictTableName != null)
            sql.SET("DICT_TABLE_NAME = '" + dictTableName + "'");
        if (dictColumnName != null)
            sql.SET("DICT_COLUMN_NAME = '" + dictColumnName + "'");
        if (status != null)
            sql.SET("STATUS = '" + status + "'");
//        if (operator != null)
//            sql.SET("OPERATOR = " + operator );
        sql.SET("UPDATE_TIME = now()");
        return sql.toString();
    }

    public String queryExtendFieldByParam(TdExtendField tdExtendField){
        SQL sql = new SQL().SELECT("*").FROM("td_extend_field");
        String tableName = tdExtendField.getTableName();
        String fieldCode = tdExtendField.getFieldCode();
        if(StringUtils.isNotBlank(tableName)){
            sql.WHERE("TABLE_NAME = #{tableName}");
        }
        if(StringUtils.isNotBlank(fieldCode)){
            sql.WHERE("FIELD_CODE = #{fieldCode}");
        }
        if(StringUtils.isNotBlank(tdExtendField.getExtendCode())){
    		sql.WHERE("EXTEND_CODE=#{extendCode}");
    	}else{
    		sql.WHERE("EXTEND_CODE is null");
    	}
        sql.WHERE("status='E'");
        sql.ORDER_BY("SORT");
        return sql.toString();
    }
    
    public String updateStatusByParam(TdExtendField tdExtendField){
    	SQL sql=new SQL().UPDATE("td_extend_field").SET("status='D'");
    	if(StringUtils.isNotBlank(tdExtendField.getExtendCode())){
    		String extendCode=tdExtendField.getExtendCode();
    		sql.WHERE("EXTEND_CODE='"+extendCode+"'");
    	}else{
    		sql.WHERE("EXTEND_CODE is null");
    	}
    	if(StringUtils.isNotBlank(tdExtendField.getTableName())){
    		String tableName=tdExtendField.getTableName();
    		sql.WHERE("TABLE_NAME='"+tableName+"'");
    		//sql.WHERE("TABLE_NAME=#{tableName}");
    	}
    	return sql.toString();
    }
}
