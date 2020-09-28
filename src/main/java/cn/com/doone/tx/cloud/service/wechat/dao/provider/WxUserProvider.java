package cn.com.doone.tx.cloud.service.wechat.dao.provider;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import cn.com.doone.tx.cloud.service.wechat.evt.department.QueryWxDepartmentEvt;
import cn.com.doone.tx.cloud.service.wechat.evt.user.EditWxUserEvt;
import cn.com.doone.tx.cloud.service.wechat.evt.user.QueryWxUserEvt;

public class WxUserProvider {
	
	private final String MAIN_TABLE_NAME = "wx_user";

	public String queryByParam(QueryWxUserEvt evt) {
        SQL sql = new SQL().SELECT("t.ID,t.WX_USERID,t.USER_NAME,t.GENDER,t.MOBILE,t.EMAIL,t.DEPARTMENT_ID,d.DEPARTMENT_NAME,t.POSITION,t.WX_STATUS,t.STATUS")
                .FROM(MAIN_TABLE_NAME+" t").LEFT_OUTER_JOIN("wx_department d ON t.DEPARTMENT_ID=d.ID AND d.STATUS<>'D'");
        Long id = evt.getId();
        if(id != null){
            sql.WHERE("t.ID = #{id}");
        }
        
        String idStr = evt.getIdStr();
        if(StringUtils.isNotBlank(idStr)) {
			sql.WHERE("t.ID IN ("+ idStr +")");
		}
        	
        Long departmentId = evt.getDepartmentId();
        if(departmentId != null){
            //sql.WHERE("t.DEPARTMENT_ID = #{departmentId}");
        	sql.WHERE("FIND_IN_SET(t.DEPARTMENT_ID, getDepartmentChildList(#{departmentId}))");
        }
        
        String wxUserid = evt.getWxUserid();
        if (StringUtils.isNotBlank(wxUserid)) {
            evt.setWxUserid("%"+wxUserid+"%");
            sql.WHERE("t.WX_USERID like #{wxUserid}");
        }
        
        String userName = evt.getUserName();
        if (StringUtils.isNotBlank(userName)) {
            evt.setUserName("%"+userName+"%");
            sql.WHERE("t.USER_NAME like #{userName}");
        }
        
        String departmentName = evt.getDepartmentName();
        if (StringUtils.isNotBlank(departmentName)) {
            evt.setDepartmentName("%"+departmentName+"%");
            sql.WHERE("d.DEPARTMENT_NAME like #{departmentName}");
        }
        
        String position = evt.getPosition();
        if (StringUtils.isNotBlank(position)) {
            evt.setPosition("%"+position+"%");
            sql.WHERE("t.POSITION like #{position}");
        }
        
        String mobile = evt.getMobile();
        if (StringUtils.isNotBlank(mobile)) {
            evt.setMobile("%"+mobile+"%");
            sql.WHERE("t.MOBILE like #{mobile}");
        }
        
        String email = evt.getEmail();
        if (StringUtils.isNotBlank(email)) {
            evt.setEmail("%"+email+"%");
            sql.WHERE("t.EMAIL like #{email}");
        }
        
        if (StringUtils.isNotBlank(evt.getStatus())) {
            sql.WHERE("t.STATUS = #{status}");
        }
        
        if (StringUtils.isNotBlank(evt.getGender())) {
            sql.WHERE("t.GENDER = #{gender}");
        }
        
        //D为删除状态
        sql.WHERE("t.status!='D'");
        sql.ORDER_BY("t.CREATE_TIME DESC");
        return sql.toString();
    }
	
	public String queryUserCount(QueryWxUserEvt evt) {
        SQL sql = new SQL().SELECT("count(*)").FROM(MAIN_TABLE_NAME +" t").LEFT_OUTER_JOIN("wx_department d ON t.DEPARTMENT_ID=d.ID AND d.STATUS<>'D'");
        Long departmentId = evt.getDepartmentId();
        if(departmentId != null){
            //sql.WHERE("t.DEPARTMENT_ID = #{departmentId}");
        	sql.WHERE("FIND_IN_SET(t.DEPARTMENT_ID, getDepartmentChildList(#{departmentId}))");
        }
        
        String wxUserid = evt.getWxUserid();
        if (StringUtils.isNotBlank(wxUserid)) {
            evt.setWxUserid("%"+wxUserid+"%");
            sql.WHERE("t.WX_USERID like #{wxUserid}");
        }
        
        String userName = evt.getUserName();
        if (StringUtils.isNotBlank(userName)) {
            evt.setUserName("%"+userName+"%");
            sql.WHERE("t.USER_NAME like #{userName}");
        }
        
        String departmentName = evt.getDepartmentName();
        if (StringUtils.isNotBlank(departmentName)) {
            evt.setDepartmentName("%"+departmentName+"%");
            sql.WHERE("d.DEPARTMENT_NAME like #{departmentName}");
        }
        
        String position = evt.getPosition();
        if (StringUtils.isNotBlank(position)) {
            evt.setPosition("%"+position+"%");
            sql.WHERE("t.POSITION like #{position}");
        }
        
        String mobile = evt.getMobile();
        if (StringUtils.isNotBlank(mobile)) {
            evt.setMobile("%"+mobile+"%");
            sql.WHERE("t.MOBILE like #{mobile}");
        }
        
        String email = evt.getEmail();
        if (StringUtils.isNotBlank(email)) {
            evt.setEmail("%"+email+"%");
            sql.WHERE("t.EMAIL like #{email}");
        }
        
        if (StringUtils.isNotBlank(evt.getStatus())) {
            sql.WHERE("t.STATUS = #{status}");
        }
        
        if (StringUtils.isNotBlank(evt.getGender())) {
            sql.WHERE("t.GENDER = #{gender}");
        }
        
        //D为删除状态
        sql.WHERE("t.status!='D'");
        return sql.toString();
    }
	
	public String queryUserIsExist(QueryWxUserEvt evt){
        SQL sql = new SQL().SELECT("count(*)").FROM(MAIN_TABLE_NAME);
        Long Id = evt.getId();
        if(Id != null){
            sql.WHERE("ID <> #{id}");//排除自己
        }
        String wxUserid = evt.getWxUserid();
        if (StringUtils.isNotBlank(wxUserid)) {
            sql.WHERE("WX_USERID = #{wxUserid}");
        }
        String mobile = evt.getMobile();
        if (StringUtils.isNotBlank(mobile)) {
            sql.WHERE("MOBILE = #{mobile}");
        }
        String email = evt.getEmail();
        if (StringUtils.isNotBlank(email)) {
            sql.WHERE("EMAIL = #{email}");
        }
        sql.WHERE("STATUS!='D'");
        return sql.toString();
    }
	
	public String doEdit(EditWxUserEvt evt){
        SQL sql = new SQL().UPDATE(MAIN_TABLE_NAME);
        if (StringUtils.isNotBlank(evt.getUserName())){
            sql.SET("USER_NAME=#{userName}");
        }
        
        if (StringUtils.isNotBlank(evt.getGender())){
            sql.SET("GENDER=#{gender}");
        }
        if (StringUtils.isNotBlank(evt.getMobile())){
            sql.SET("MOBILE=#{mobile}");
        }
        if (StringUtils.isNotBlank(evt.getEmail())){
            sql.SET("EMAIL=#{email}");
        }
        if (StringUtils.isNotBlank(evt.getPosition())){
            sql.SET("POSITION=#{position}");
        }
        if (StringUtils.isNotBlank(evt.getWxStatus())){
            sql.SET("WX_STATUS=#{wxStatus}");
        }
        if (StringUtils.isNotBlank(evt.getStatus())){
            sql.SET("STATUS=#{status}");
        }
        if (evt.getDepartmentId()!=null){
            sql.SET("DEPARTMENT_ID=#{departmentId}");
        }
        if (evt.getOperator()!=null){
            sql.SET("OPERATOR=#{operator}");
        }
        evt.setUpdateTime(new Date());
		sql.SET("UPDATE_TIME=#{updateTime}");
		String idStr = evt.getIdStr();
		if(StringUtils.isNotBlank(idStr)) {
			sql.WHERE("ID IN ("+ idStr +")");
		}else {
			sql.WHERE("ID=#{id}");
		}
        
        return sql.toString();
    }

}
