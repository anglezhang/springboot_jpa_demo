package cn.com.doone.tx.cloud.service.config.service;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.doone.tx.cloud.service.config.bean.FtpAttchBean;
import cn.com.doone.tx.cloud.service.config.dao.mapper.FtpAttchMapper;
import cn.com.doone.tx.cloud.service.config.evt.ftpAttch.AddFtpAttchEvt;
import cn.com.doone.tx.cloud.service.config.evt.ftpAttch.DelFtpAttchEvt;
import cn.com.doone.tx.cloud.service.config.evt.ftpAttch.DelFtpAttchMultEvt;
import cn.com.doone.tx.cloud.service.config.evt.ftpAttch.EditFtpAttchEvt;
import cn.com.doone.tx.cloud.service.config.evt.ftpAttch.QueryFtpAttchEvt;
import cn.com.doone.tx.cloud.service.config.info.FtpAttchInfo;
import cn.com.doone.tx.cloud.tool.common.util.SFtpUtils;
import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatRepository;

/**
 * Created by yecz on 2017/3/2. 文件上传 数据库操作service
 */
@Service("FtpAttchService")
@Transactional
public class FtpAttchService {
	Logger logger = Logger.getLogger(FtpAttchService.class);
	/** FTP上传 */
	// FtpUtil ftpUtil = new FtpUtil();
	/** 用户名 */
	@Value("${ftpUser:}")
	String user;
	/** 密码 */
	@Value("${ftpPassword:}")
	String password;
	/** IP */
	@Value("${ftpServer:}")
	String server;
	/** 端口 */
	@Value("${ftpPort:}")
	String port;
	/**
	 * 文件缓存路径(容器) 如本机：c:/uploadfile/ 环境：/home/xdw/uploadfile/
	 */
	@Value("${web.upload-path:}")
	String webUploadPath;
	/** 使用FTP\SFTP的标识 */
	@Value("${ftpTool:}")
	String ftpTool;

	@Autowired
	private FtpAttchMapper ftpAttchMapper;

	@Autowired
	private PlatRepository platRepository;

	// 根据id查编码
	public String queryExistCode(QueryFtpAttchEvt evt) {
		return ftpAttchMapper.queryExistCode(evt);
	}

	public Integer queryExistCount(QueryFtpAttchEvt evt) {
		return ftpAttchMapper.queryExistCount(evt);
	}

	public Integer queryFtpAttchCount(QueryFtpAttchEvt evt) {
		return ftpAttchMapper.queryFtpAttchCount(evt);
	}

	// 查询ftp文件
	public List<FtpAttchInfo> queryFtpAttchByParam(QueryFtpAttchEvt evt) {
		return ftpAttchMapper.queryFtpAttchByParam(evt);
	}

	// 增加ftp文件
	public FtpAttchBean add(AddFtpAttchEvt evt) {
		FtpAttchBean bean = new FtpAttchBean();		
		BeanUtils.copyProperties(evt, bean);
		bean.setCreator(evt.getOperator());
		// System.out.println("bean:"+bean.getRoleName());
		return platRepository.save(bean);
	}

	public int doEdit(EditFtpAttchEvt evt) {
		return ftpAttchMapper.doEdit(evt);
	}

	public int doDelete(DelFtpAttchEvt evt) {
		return ftpAttchMapper.doDelete(evt);
	}

	/** 查询图片条数 **/
	// @Cacheable(value = "FtpAttchService.queryCount",keyGenerator =
	// "defaultKeyGenerator")
	public int queryPicCount(QueryFtpAttchEvt evt) {
		return ftpAttchMapper.queryPicCount(evt);
	}

	/** 查询图片 **/
	@SuppressWarnings("deprecation")
	public List<FtpAttchInfo> queryPicByParam(QueryFtpAttchEvt evt) {

		List<FtpAttchInfo> ftpAttchList = ftpAttchMapper.queryPicByParam(evt);
		return ftpAttchList;
	}

	/** 逻辑删除 **/
	@Transactional
	public FtpAttchBean doDeleteByStatus(DelFtpAttchMultEvt evt) {
		String[] ids = evt.getIds().split(",");
		FtpAttchBean bean = new FtpAttchBean();
		try {
			for (String id : ids) {
				QueryFtpAttchEvt queryFtpAttchEvt = new QueryFtpAttchEvt();
				queryFtpAttchEvt.setId(Long.parseLong(id));
				List<FtpAttchInfo> ftpAttchList = ftpAttchMapper.queryFtpAttchByParam(queryFtpAttchEvt);
				if (ftpAttchList != null && ftpAttchList.size() > 0) {
					FtpAttchInfo ftpAttchInfo = ftpAttchList.get(0);

					// 删除数据库
					DelFtpAttchEvt delFtpAttchEvt = new DelFtpAttchEvt();
					delFtpAttchEvt.setId(Long.parseLong(id));
					ftpAttchMapper.doDeleteByStatus(delFtpAttchEvt);

					// 删除缓存
					File attchFile = new File(webUploadPath + ftpAttchInfo.getSaveFilename());
					if (attchFile.exists()) {// 本地存在该文件，删除
						attchFile.delete();
					}

					// 删除ftp
					if ("sftp".equals(ftpTool)) {
						/** sFtp 下载 */
						SFtpUtils sftp = null;
						try {
							sftp = new SFtpUtils(server, Integer.valueOf(port), user, password);
							sftp.connect();
							/** ftp存放目录-文件名-缓存目录-文件名 */
							boolean falg = sftp.deleteFile(ftpAttchInfo.getFilePath() + ftpAttchInfo.getSaveFilename());
						} catch (Exception e) {
							logger.error(e.getMessage());
						} finally {
							/** 释放连接 */
							sftp.disconnect();
						}
					} else {
						/** 默认ftp下载 */
						String ftpUrl = "ftp://" + user + ":" + password + "@" + server + ":" + port
								+ ftpAttchInfo.getFilePath() + ftpAttchInfo.getSaveFilename();
						// ftpUtil.delete(ftpUrl);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return bean;
	}
}
