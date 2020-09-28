package cn.com.doone.tx.cloud.service.wechat.dao.provider;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import cn.com.doone.tx.cloud.service.wechat.evt.token.EditWxTokenEvt;
import cn.com.doone.tx.cloud.service.wechat.evt.token.QueryWxTokenEvt;

public class WxTokenProvider {
	
	private final String MAIN_TABLE_NAME = "wx_token";

	public String queryWxTokenInfo(QueryWxTokenEvt evt) {
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("t.ID,");
		sqlStr.append("t.WX_CORP_ID,");
		sqlStr.append("t.WX_AGENT_ID,");
		sqlStr.append("t.TOKEN_TYPE,");
		sqlStr.append("t.TOKEN_VALIDITY_TIME,");
		sqlStr.append("t.ACCESS_TOKEN,");
		sqlStr.append("t.STATUS");
		SQL sql = new SQL().SELECT(sqlStr.toString()).FROM(MAIN_TABLE_NAME +" t");
		Long id = evt.getId();
		if (id != null) {
			sql.WHERE("t.ID = #{id}");
		}
		if (StringUtils.isNotBlank(evt.getWxCorpId())) {
			sql.WHERE("t.WX_CORP_ID = #{wxCorpId}");
		}
		if (StringUtils.isNotBlank(evt.getWxAgentId())) {
			sql.WHERE("t.WX_AGENT_ID = #{wxAgentId}");
		}
		if (StringUtils.isNotBlank(evt.getTokenType())) {
			sql.WHERE("t.TOKEN_TYPE = #{tokenType}");
		}
		if (StringUtils.isNotBlank(evt.getStatus())) {
			sql.WHERE("t.STATUS = #{status}");
		}
		sql.WHERE("t.status !='D' ");
		return sql.toString();
	}
	
	public String doEdit(EditWxTokenEvt evt) {
		SQL sql = new SQL().UPDATE(MAIN_TABLE_NAME);
		if(StringUtils.isNotBlank(evt.getAccessToken())) {
			sql.SET(" ACCESS_TOKEN = #{accessToken}");
		}
		if(evt.getTokenValidityTime() != null) {
			sql.SET(" TOKEN_VALIDITY_TIME = #{tokenValidityTime}");
		}
		if (StringUtils.isNotBlank(evt.getWxCorpId())) {
			sql.SET(" WX_CORP_ID = #{wxCorpId}");
		}
		if (StringUtils.isNotBlank(evt.getWxAgentId())) {
			sql.SET(" WX_AGENT_ID = #{wxAgentId}");
		}
		if (StringUtils.isNotBlank(evt.getTokenType())) {
			sql.SET(" TOKEN_TYPE = #{tokenType}");
		}
		if (StringUtils.isNotBlank(evt.getStatus())) {
			sql.SET(" STATUS = #{status}");
		}
		evt.setUpdateTime(new Date());
		sql.SET(" UPDATE_TIME=#{updateTime}");
		Long id = evt.getId();
		if (id != null) {
			sql.WHERE("ID = #{id}");
		}
		return sql.toString();
	}

}
