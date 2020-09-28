package cn.com.doone.tx.cloud.service.config.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.doone.tx.cloud.service.config.dao.provider.FtpAttchProvider;
import cn.com.doone.tx.cloud.service.config.evt.ftpAttch.DelFtpAttchEvt;
import cn.com.doone.tx.cloud.service.config.evt.ftpAttch.EditFtpAttchEvt;
import cn.com.doone.tx.cloud.service.config.evt.ftpAttch.QueryFtpAttchEvt;
import cn.com.doone.tx.cloud.service.config.info.FtpAttchInfo;


/**
 * ftp文件上传map Created by yecz on 2017/1/19 0019.
 */
public interface FtpAttchMapper {

	@Autowired

	@SelectProvider(type = FtpAttchProvider.class, method = "queryFtpAttchCount")
	Integer queryFtpAttchCount(QueryFtpAttchEvt evt);

	@SelectProvider(type = FtpAttchProvider.class, method = "queryFtpAttchByParam")
	List<FtpAttchInfo> queryFtpAttchByParam(QueryFtpAttchEvt evt);

	@UpdateProvider(type = FtpAttchProvider.class, method = "updateFtpAttch")
	Integer doEdit(EditFtpAttchEvt evt);

	@Delete("delete from tf_ftp_attch where id = #{id}")
	Integer doDelete(DelFtpAttchEvt evt);

	/** 查询图片条数 **/
	@SelectProvider(type = FtpAttchProvider.class, method = "queryPicCount")
	Integer queryPicCount(QueryFtpAttchEvt queryFtpAttchEvt);

	/** 查询图片 **/
	@SelectProvider(type = FtpAttchProvider.class, method = "queryPicByParam")
	List<FtpAttchInfo> queryPicByParam(QueryFtpAttchEvt queryFtpAttchEvt);

	/** 逻辑删除 **/
	@UpdateProvider(type = FtpAttchProvider.class, method = "doDeleteByStatus")
	Integer doDeleteByStatus(DelFtpAttchEvt evt);

	/** 查询存在数量 **/
	@SelectProvider(type = FtpAttchProvider.class, method = "queryExistCount")
	Integer queryExistCount(QueryFtpAttchEvt evt);

	/** 查询附件编码attachCode **/
	@SelectProvider(type = FtpAttchProvider.class, method = "queryExistCode")
	String queryExistCode(QueryFtpAttchEvt evt);

}
