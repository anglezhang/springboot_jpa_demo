package cn.com.doone.tx.cloud.service.wechat.dao.provider;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import cn.com.doone.tx.cloud.service.wechat.evt.corp.QueryWxCorpEvt;

public class WxCorpProvider {
	
	private final String MAIN_TABLE_NAME = "wx_corp";

	public String queryWxCorpInfo(QueryWxCorpEvt evt) {
		StringBuilder sqlStr = new StringBuilder();
		sqlStr.append("t.ID,");
		sqlStr.append("t.WX_CORP_ID,");
		sqlStr.append("t.CORP_NAME,");
		sqlStr.append("t.MAIL_SECRET,");
		sqlStr.append("t.CONTACT_SECRET,");
		sqlStr.append("t.STATUS");
		SQL sql = new SQL().SELECT(sqlStr.toString()).FROM(MAIN_TABLE_NAME +" t");
		Long id = evt.getId();
		if (id != null) {
			sql.WHERE("t.ID = #{id}");
		}
		if (StringUtils.isNotBlank(evt.getWxCorpId())) {
			sql.WHERE("t.WX_CORP_ID = #{wxCorpId}");
		}
		if (StringUtils.isNotBlank(evt.getStatus())) {
			sql.WHERE("t.STATUS = #{status}");
		}
		sql.WHERE("t.status !='D' ");
		return sql.toString();
	}
}
