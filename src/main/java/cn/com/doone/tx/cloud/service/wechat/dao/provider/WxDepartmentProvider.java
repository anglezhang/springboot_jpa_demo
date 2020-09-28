package cn.com.doone.tx.cloud.service.wechat.dao.provider;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import cn.com.doone.tx.cloud.service.wechat.evt.department.EditWxDepartmentEvt;
import cn.com.doone.tx.cloud.service.wechat.evt.department.QueryWxDepartmentEvt;

public class WxDepartmentProvider {
	
	private final String MAIN_TABLE_NAME = "wx_department";

	public String queryByParam(QueryWxDepartmentEvt evt) {
        SQL sql = new SQL().SELECT("t.ID,t.WX_DEPARTMENT_ID,t.PARENT_ID,t.DEPARTMENT_NAME")
                .FROM(MAIN_TABLE_NAME+" t");
        Long id = evt.getId();
        if(id != null){
            sql.WHERE("t.ID = #{id}");
        }
        Long parentId = evt.getParentId();
        if(parentId != null){
            sql.WHERE("t.PARENT_ID = #{parentId}");
        }
        Long wxDepartmentId = evt.getWxDepartmentId();
        if(wxDepartmentId != null){
            sql.WHERE("t.WX_DEPARTMENT_ID = #{wxDepartmentId}");
        }
        //D为删除状态
        sql.WHERE("t.status!='D'");
        sql.ORDER_BY("t.CREATE_TIME");
        return sql.toString();
    }
	
	
	public String queryDepartmentIsExist(QueryWxDepartmentEvt evt){
        SQL sql = new SQL().SELECT("count(*)").FROM(MAIN_TABLE_NAME);
        Long Id = evt.getId();
        if(Id != null){
            sql.WHERE("ID <> #{id}");//排除自己
        }
        String departmentName = evt.getDepartmentName();
        if (StringUtils.isNotBlank(departmentName)) {
            sql.WHERE("DEPARTMENT_NAME = #{departmentName}");
        }
        sql.WHERE("STATUS!='D'");
        return sql.toString();
    }
	
	
	public String doEdit(EditWxDepartmentEvt evt){
        SQL sql = new SQL().UPDATE(MAIN_TABLE_NAME);
        if (evt.getDepartmentName()!=null){
            sql.SET("DEPARTMENT_NAME=#{departmentName}");
        }
        if (evt.getOperator()!=null){
            sql.SET("OPERATOR=#{operator}");
        }
        if (evt.getStatus()!=null){
            sql.SET("STATUS=#{status}");
        }
        evt.setUpdateTime(new Date());
		sql.SET("UPDATE_TIME=#{updateTime}");
        sql.WHERE("ID=#{id}");
        return sql.toString();
    }
}
