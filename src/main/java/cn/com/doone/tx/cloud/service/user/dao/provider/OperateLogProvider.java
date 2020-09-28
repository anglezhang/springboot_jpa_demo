package cn.com.doone.tx.cloud.service.user.dao.provider;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import cn.com.doone.tx.cloud.service.user.evt.operateLog.QueryOperateLogEvt;
import cn.com.doone.tx.cloud.tool.service.common.util.SqlGenerateUtil;


public class OperateLogProvider {

    private final String TL_OPERATE_LOG = "tl_operate_log";

    public String queryCount(QueryOperateLogEvt evt) {
        SQL sql = new SQL().SELECT("count(*)").FROM(TL_OPERATE_LOG);
        String startCreateTime = evt.getStartCreateTime();
        String endCreateTime = evt.getEndCreateTime();
        if (StringUtils.isNotBlank(startCreateTime)) {
			sql.WHERE("UPDATE_TIME >= "+SqlGenerateUtil.dateTimeStrToDate("#{startCreateTime}"));						
		}
		if (StringUtils.isNotBlank(endCreateTime)) {								
			sql.WHERE("UPDATE_TIME <= "+SqlGenerateUtil.dateTimeStrToDate("#{endCreateTime}"));
		}
        String  operateName = evt.getOperateName();
        if(StringUtils.isNotBlank(operateName)){
        	evt.setOperateName("%"+operateName+"%");
            sql.WHERE("OPERATE_NAME like #{operateName}");
        }
        String  systemCode = evt.getSystemCode();
        if(StringUtils.isNotBlank(systemCode)){
            sql.WHERE("SYSTEM_CODE = #{systemCode}");
        }
        String  menuCode = evt.getMenuCode();
        if(StringUtils.isNotBlank(menuCode)){
            sql.WHERE("MENU_CODE = #{menuCode}");
        }
        String operateResult = evt.getOperateResult();
        if(StringUtils.isNotBlank(operateResult)){
        	if("0".equals(operateResult)){
        		sql.WHERE("OPERATE_RESULT = #{operateResult }");
        	}else{
        		sql.WHERE(" NOT OPERATE_RESULT = '0'");
        	}
        }
        if(evt.getGroupId() != null) {
        	sql.WHERE("sg.id = #{groupId}");
        }
        sql.WHERE("STATUS = 'E'");
        return sql.toString();
    }
    public String queryByParam(QueryOperateLogEvt evt) {
        SQL sql = new SQL().SELECT("t.ID,t.OPERATE_URL,t.IN_PARAM,t.OUT_PARAM," +
                "t.OPERATOR,t.OPERATE_DESC,t.OPERATE_NAME,t.CREATE_TIME,t.UPDATE_TIME,t.OPERATE_RESULT," +
                "t.OPERATE_IP,t.STATUS,t.ERR_MSG, st.staff_code, st.staff_name, sg.group_name").FROM(TL_OPERATE_LOG + " t")
        		.LEFT_OUTER_JOIN("ts_staff_info st on st.id = t.creator")
        		.LEFT_OUTER_JOIN("tr_group_staff gs on gs.staff_id = st.id")
        		.LEFT_OUTER_JOIN("ts_staff_group sg on sg.id = gs.id");
        String startCreateTime = evt.getStartCreateTime();
        String endCreateTime = evt.getEndCreateTime();
        if (StringUtils.isNotBlank(startCreateTime)) {
			sql.WHERE("t.CREATE_TIME >= "+SqlGenerateUtil.dateTimeStrToDate("#{startCreateTime}"));						
		}
		if (StringUtils.isNotBlank(endCreateTime)) {								
			sql.WHERE("t.CREATE_TIME <= "+SqlGenerateUtil.dateTimeStrToDate("#{endCreateTime}"));
		}
        String  operateName = evt.getOperateName();
        if(StringUtils.isNotBlank(operateName)){
        	evt.setOperateName("%"+operateName+"%");
            sql.WHERE("t.OPERATE_NAME like #{operateName}");
        }
        String  systemCode = evt.getSystemCode();
        if(StringUtils.isNotBlank(systemCode)){
            sql.WHERE("t.SYSTEM_CODE = #{systemCode}");
        }
        String  menuCode = evt.getMenuCode();
        if(StringUtils.isNotBlank(menuCode)){
            sql.WHERE("t.MENU_CODE = #{menuCode}");
        }
        String operateResult = evt.getOperateResult();
        if(StringUtils.isNotBlank(operateResult)){
        	if("0".equals(operateResult)){
        		sql.WHERE("t.OPERATE_RESULT = #{operateResult }");
        	}else{
        		sql.WHERE(" NOT t.OPERATE_RESULT = '0'");
        	}
        }
        if(StringUtils.isNotBlank(evt.getIds())) {
        	sql.WHERE("t.ID in(" + cn.com.doone.tx.cloud.tool.common.util.StringUtils.jointStr(evt.getIds()) + ")");
        }
        if(evt.getGroupId() != null) {
        	sql.WHERE("sg.id = #{groupId}");
        }
        sql.WHERE("t.STATUS = 'E'");
        sql.ORDER_BY("t.CREATE_TIME DESC");
        System.out.println(sql);
        return sql.toString();
    }
}
