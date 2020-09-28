package cn.com.doone.tx.cloud.service.config.dao.provider;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import cn.com.doone.tx.cloud.service.config.evt.ftpAttch.DelFtpAttchEvt;
import cn.com.doone.tx.cloud.service.config.evt.ftpAttch.EditFtpAttchEvt;
import cn.com.doone.tx.cloud.service.config.evt.ftpAttch.QueryFtpAttchEvt;


/**
 * ftp文件上传构造器 yecz
 */
public class FtpAttchProvider {

	private final String TF_FTP_ATTCH = "tf_ftp_attch";
	private final String picSuffix = "'jpg','png','jpeg','bmp','gif'";

	public String queryFtpAttchCount(QueryFtpAttchEvt evt) {
		SQL sql = new SQL().SELECT("count(*)").FROM(TF_FTP_ATTCH);
		Long id = evt.getId();
		if (id != null && id != 0) {
			sql.WHERE("id=#{id}");
		}
		String orgFileName = evt.getOrgFileName();
		if (StringUtils.isNotBlank(orgFileName)) {
			sql.WHERE("ORG_FILENAME=#{orgFileName}");
		}
		// String saveFileName = evt.getSaveFileName();
		String saveFileName = evt.getSaveFilename();
		if (StringUtils.isNotBlank(saveFileName)) {
			sql.WHERE("SAVE_FILENAME=#{saveFileName}");
		}
		return sql.toString();
	}

	public String queryExistCount(QueryFtpAttchEvt evt) {
		SQL sql = new SQL().SELECT("count(*)").FROM(TF_FTP_ATTCH);
		Long id = evt.getId();
		if (id != null && id != 0) {
			sql.WHERE("id=#{id}");
		}

		String attachCode = evt.getAttachCode();
		if (StringUtils.isNotBlank(attachCode)) {
			sql.WHERE("ATTACH_CODE=#{attachCode}");
		}
		return sql.toString();
	}

	public String queryExistCode(QueryFtpAttchEvt evt) {
		SQL sql = new SQL().SELECT("ATTACH_CODE attachCode").FROM(TF_FTP_ATTCH);
		Long id = evt.getId();
		if (id != null && id != 0) {
			sql.WHERE("id=#{id}");
		}

		String attachCode = evt.getAttachCode();
		if (StringUtils.isNotBlank(attachCode)) {
			sql.WHERE("ATTACH_CODE=#{attachCode}");
		}
		return sql.toString();
	}

	public String queryFtpAttchByParam(QueryFtpAttchEvt evt) {
		SQL sql = new SQL().SELECT("*").FROM(TF_FTP_ATTCH);
		Long id = evt.getId();
		if (id != null && id != 0) {
			sql.WHERE("id=#{id}");
		}
		String attachCode = evt.getAttachCode();
		if (StringUtils.isNotBlank(attachCode)) {
			sql.WHERE("ATTACH_CODE=#{attachCode}");
		}
		String orgFileName = evt.getOrgFileName();
		if (StringUtils.isNotBlank(orgFileName)) {
			sql.WHERE("ORG_FILENAME=#{orgFileName}");
		}
		// String saveFileName = evt.getSaveFileName();
		String saveFileName = evt.getSaveFilename();
		if (StringUtils.isNotBlank(saveFileName)) {
			sql.WHERE("SAVE_FILENAME=#{saveFileName}");
		}
		if (StringUtils.isNotBlank(evt.getIds())) {
			sql.WHERE("id in(" + cn.com.doone.tx.cloud.tool.common.util.StringUtils.jointStr(evt.getIds()) + ")");
		}
		return sql.toString();
	}

	public String updateFtpAttch(EditFtpAttchEvt evt) {
		SQL sql = new SQL().UPDATE(TF_FTP_ATTCH);
		String orgFileName = evt.getOrgFileName();
		if (orgFileName != null) {
			sql.SET("ORG_FILENAME=#{orgFileName}");
		}
		String saveFileName = evt.getSaveFileName();
		if (saveFileName != null) {
			sql.SET("SAVE_FILENAME=#{saveFileName}");
		}
		Long id = evt.getId();
		if (id != null && id != 0) {
			sql.WHERE("id=#{id}");
		}
		return sql.toString();
	}

	/** 查询图片条数 **/
	public String queryPicCount(QueryFtpAttchEvt evt) {

		SQL sql = new SQL().SELECT("count(*)").FROM(TF_FTP_ATTCH + " t1 ");

		Long id = evt.getId();
		if (id != null) {
			sql.WHERE("t1.ID = #{id}");
		} else {
			String orgFileName = evt.getOrgFileName();
			if (StringUtils.isNotBlank(orgFileName)) {
				evt.setOrgFileName("%" + orgFileName + "%");
				sql.WHERE("t1.ORG_FILENAME like #{orgFileName}");
			}
			String saveFilename = evt.getSaveFilename();
			if (StringUtils.isNotBlank(saveFilename)) {
				evt.setSaveFilename("%" + saveFilename + "%");
				sql.WHERE("t1.SAVE_FILENAME like #{saveFilename}");
			}
			String fileSuffix = evt.getFileSuffix();
			if (StringUtils.isNotBlank(fileSuffix)) {
				sql.WHERE("t1.FILE_SUFFIX = #{fileSuffix}");
			}
			String filePath = evt.getFilePath();
			if (StringUtils.isNotBlank(filePath)) {
				evt.setFilePath("%" + filePath + "%");
				sql.WHERE("t1.FILE_PATH like #{filePath}");
			}
			sql.WHERE("t1.FILE_SUFFIX in(" + picSuffix + ")");
			sql.WHERE("t1.STATUS = 'E' ");
		}
		return sql.toString();
	}

	/** 查询 **/
	public String queryPicByParam(QueryFtpAttchEvt evt) {
		SQL sql = new SQL().SELECT(
				"t1.ID as id,t1.ORG_FILENAME as orgFileName,t1.ATTACH_CODE as attachCode,t1.SAVE_FILENAME as saveFilename,t1.FILE_SUFFIX as fileSuffix,t1.FILE_PATH as filePath,t1.FTP_URL as ftpUrl,t1.WEB_URL as webUrl,t1.STATUS as status,t1.CREATE_TIME as createTime,t1.UPDATE_TIME as updateTime,t1.OPERATOR as operator")
				.FROM(TF_FTP_ATTCH + " t1 ");

		Long id = evt.getId();
		if (id != null) {
			sql.WHERE("t1.ID = #{id}");
		} else {
			String orgFileName = evt.getOrgFileName();
			if (StringUtils.isNotBlank(orgFileName)) {
				evt.setOrgFileName("%" + orgFileName + "%");
				sql.WHERE("t1.ORG_FILENAME like #{orgFileName}");
			}
			String saveFilename = evt.getSaveFilename();
			if (StringUtils.isNotBlank(saveFilename)) {
				evt.setSaveFilename("%" + saveFilename + "%");
				sql.WHERE("t1.SAVE_FILENAME like #{saveFilename}");
			}
			String fileSuffix = evt.getFileSuffix();
			if (StringUtils.isNotBlank(fileSuffix)) {
				sql.WHERE("t1.FILE_SUFFIX = #{fileSuffix}");
			}
			String filePath = evt.getFilePath();
			if (StringUtils.isNotBlank(filePath)) {
				evt.setFilePath("%" + filePath + "%");
				sql.WHERE("t1.FILE_PATH like #{filePath}");
			}
			sql.WHERE("t1.FILE_SUFFIX in(" + picSuffix + ")");
			sql.WHERE("t1.STATUS = 'E' ");
		}
		sql.ORDER_BY("t1.CREATE_TIME desc");
		return sql.toString();
	}

	/** 逻辑删除 **/
	public String doDeleteByStatus(DelFtpAttchEvt evt) {
		SQL sql = new SQL().UPDATE(TF_FTP_ATTCH);
		sql.SET("status='D' ");
		sql.WHERE("ID=#{id}");
		return sql.toString();
	}
}
