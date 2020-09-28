package cn.com.doone.tx.cloud.service.config.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;

import cn.com.doone.tx.cloud.service.config.bean.FtpAttchBean;
import cn.com.doone.tx.cloud.service.config.evt.ftpAttch.AddFtpAttchEvt;
import cn.com.doone.tx.cloud.service.config.evt.ftpAttch.DelFtpAttchEvt;
import cn.com.doone.tx.cloud.service.config.evt.ftpAttch.DelFtpAttchMultEvt;
import cn.com.doone.tx.cloud.service.config.evt.ftpAttch.EditFtpAttchEvt;
import cn.com.doone.tx.cloud.service.config.evt.ftpAttch.FtpAttchEvt;
import cn.com.doone.tx.cloud.service.config.evt.ftpAttch.QueryFtpAttchEvt;
import cn.com.doone.tx.cloud.service.config.evt.ftpAttch.QueryFtpAttchInfoEvt;
import cn.com.doone.tx.cloud.service.config.info.FtpAttchInfo;
import cn.com.doone.tx.cloud.service.config.info.UploadInfo;
import cn.com.doone.tx.cloud.service.config.service.FtpAttchService;
import cn.com.doone.tx.cloud.service.config.utils.AttachCode;
import cn.com.doone.tx.cloud.tool.common.enums.CommonResultCode;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.log.annotation.ServiceAround;
import cn.com.doone.tx.cloud.tool.service.common.base.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by yecz on 2017/3/2. 文件上传数据库操作controller
 */
@RestController
@RequestMapping("/ftpAttch")
@Api(description = "Ftp文件服务")
public class FtpAttchController extends FtpFileBaseController {
	Logger logger = Logger.getLogger(FtpAttchController.class);
	@Autowired
	private FtpAttchService ftpAttchService;

	@ServiceAround
	@ApiOperation(value = "上传文件至服务器#受理", notes = "")
	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	public ServerResp<Object> uploadFile(@RequestBody Message<FtpAttchEvt> message) {
		FtpAttchEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		String webUploadPath = evt.getWebUploadPath();
		String orgFileName = evt.getOrgFileName();
		String saveFileName = evt.getSaveFilename();
		File targetFile = new File(webUploadPath, saveFileName);
		// 上传至服务器
		try {
			ServerResp<UploadInfo> resp1 = uploadFile(targetFile, saveFileName, "");
			if (resp1.isSuccess()) {
				UploadInfo uploadInfo = resp1.getBody();
				// 封装文件信息保存至数据库
				AddFtpAttchEvt addFtpAttchEvt = new AddFtpAttchEvt();
				addFtpAttchEvt.setStatus("E");
				addFtpAttchEvt.setCreateTime(new Date());
				addFtpAttchEvt.setUpdateTime(new Date());
				addFtpAttchEvt.setOperator(1L);// 操作人员
				addFtpAttchEvt.setOrgFileName(orgFileName);// 原文件名
				addFtpAttchEvt.setSaveFileName(saveFileName);// 保存文件名
				addFtpAttchEvt.setFileSuffix(uploadInfo.getFileSuffix());// 文件后缀名
				addFtpAttchEvt.setFilePath(uploadInfo.getFilePath());// 文件保存路径
				addFtpAttchEvt.setFtpUrl(uploadInfo.getFtpUrl());// ftp路径
				addFtpAttchEvt.setWebUrl(uploadInfo.getWebUrl());// web访问路径
				FtpAttchBean ftpAttchBean = ftpAttchService.add(addFtpAttchEvt);
				if (ftpAttchBean.getId() != null) {
					return resp.success(ftpAttchBean.getId());
				} else {
					return resp.error("文件上传失败");
				}
			} else {
				return resp.error("文件上传失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return resp.error("文件上传失败");
		}
	}

	@ServiceAround
	@ApiOperation(value = "上传文件至服务器#受理，生成唯一编码", notes = "")
	@RequestMapping(value = "uploadFileGetCode", method = RequestMethod.POST)
	public ServerResp<Object> uploadFileGetCode(@RequestBody Message<FtpAttchEvt> message) {
		FtpAttchEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		String webUploadPath = evt.getWebUploadPath();
		String orgFileName = evt.getOrgFileName();
		String saveFileName = evt.getSaveFilename();
		File targetFile = new File(webUploadPath, saveFileName);
		// 上传至服务器
		try {
			ServerResp<UploadInfo> resp1 = uploadFile(targetFile, saveFileName, "");
			if (resp1.isSuccess()) {
				UploadInfo uploadInfo = resp1.getBody();
				// 封装文件信息保存至数据库
				AddFtpAttchEvt addFtpAttchEvt = new AddFtpAttchEvt();
				// 唯一编码，不能重复
				String attachCode = new AttachCode().nextId();

				addFtpAttchEvt.setAttachCode(attachCode);
				addFtpAttchEvt.setStatus("E");
				addFtpAttchEvt.setCreateTime(new Date());
				addFtpAttchEvt.setUpdateTime(new Date());
				addFtpAttchEvt.setOperator(1L);// 操作人员
				addFtpAttchEvt.setOrgFileName(orgFileName);// 原文件名
				addFtpAttchEvt.setSaveFileName(saveFileName);// 保存文件名
				addFtpAttchEvt.setFileSuffix(uploadInfo.getFileSuffix());// 文件后缀名
				addFtpAttchEvt.setFilePath(uploadInfo.getFilePath());// 文件保存路径
				addFtpAttchEvt.setFtpUrl(uploadInfo.getFtpUrl());// ftp路径
				addFtpAttchEvt.setWebUrl(uploadInfo.getWebUrl());// web访问路径
				FtpAttchBean ftpAttchBean = ftpAttchService.add(addFtpAttchEvt);
				if (ftpAttchBean.getId() != null) {
					return resp.success(ftpAttchBean.getId());
				} else {
					return resp.error("文件上传失败");
				}
			} else {
				return resp.error("文件上传失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return resp.error("文件上传失败");
		}
	}

	@ServiceAround
	@ApiOperation(value = "查询Ftp文件数量#查询", notes = "")
	@RequestMapping(value = "ftpAttchCount", method = RequestMethod.POST)
	public ServerResp<Object> ftpAttchCount(@RequestBody Message<QueryFtpAttchEvt> message) {
		QueryFtpAttchEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		int cnt = ftpAttchService.queryFtpAttchCount(evt);
		return resp.success(cnt);
	}

	@ServiceAround
	@ApiOperation(value = "查询Ftp文件参数#查询", notes = "")
	@RequestMapping(value = "queryFtpAttchByParam", method = RequestMethod.POST)
	public ServerResp<Object> queryFtpAttchByParam(@RequestBody Message<QueryFtpAttchEvt> message) {
		QueryFtpAttchEvt evt = message.getBody();
		PageHelper.startPage(evt.getQueryPage(), evt.getQuerySize());
		ServerResp<Object> resp = new ServerResp<Object>();
		Integer countSize = 0;
		countSize = ftpAttchService.queryExistCount(evt);
		if (countSize > 0) {
			String atachCode = evt.getAttachCode();
			String attachCodeData = ftpAttchService.queryExistCode(evt);
			// 没有编码，或者编码一致才能进行下一步
			if (attachCodeData == null || "".equals(attachCodeData)) {
				List<FtpAttchInfo> ftpAttchInfos = ftpAttchService.queryFtpAttchByParam(evt);
				resp.success(ftpAttchInfos);
			} else {
				if (atachCode != null && !("".equals(atachCode))) {
					if (attachCodeData.equals(atachCode)) {
						List<FtpAttchInfo> ftpAttchInfos = ftpAttchService.queryFtpAttchByParam(evt);
						resp.success(ftpAttchInfos);
					} else {
						resp.error("编码错误");
					}
				} else {
					resp.error("编码缺失");
				}
			}
		} else {
			resp.error("该文件不存在");
		}

		return resp;
	}

	@ServiceAround
	@ApiOperation(value = "根据id列表查询多个文件", notes = "")
	@RequestMapping(value = "queryFtpAttchListByParam", method = RequestMethod.POST)
	public ServerResp<Object> queryFtpAttchListByParam(@RequestBody Message<QueryFtpAttchEvt> message) {
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			QueryFtpAttchEvt evt = message.getBody();
			PageHelper.startPage(evt.getQueryPage(), evt.getQuerySize());
			List<FtpAttchInfo> ftpAttchInfos = ftpAttchService.queryFtpAttchByParam(evt);
			resp.success(ftpAttchInfos);
		} catch (Exception e) {
			logger.error("根据id列表查询多个文件功能异常：", e);
			resp.error("查询失败");
		}
		return resp;
	}

	@ServiceAround
	@ApiOperation(value = "根据附件Id查询Ftp文件信息#查询", notes = "respCode 0-成功 -1失败")
	@RequestMapping(value = "queryFtpAttchByAttchId", method = RequestMethod.POST)
	public ServerResp<Object> queryFtpAttchByAttchId(@RequestBody Message<QueryFtpAttchInfoEvt> message) {
		QueryFtpAttchInfoEvt queryFtpAttchInfoEvt = message.getBody();
		QueryFtpAttchEvt evt = new QueryFtpAttchEvt();
		System.out.println(">>>>>>>>>>>>>>根据附件Id查询Ftp文件信息<<<<<<<<<<<<<:" + queryFtpAttchInfoEvt.getAttchId());
		evt.setId(queryFtpAttchInfoEvt.getAttchId());
		PageHelper.startPage(evt.getQueryPage(), evt.getQuerySize());
		ServerResp<Object> resp = new ServerResp<Object>();
		List<FtpAttchInfo> ftpAttchInfos = new ArrayList<FtpAttchInfo>();
		try {
			Integer countSize = ftpAttchService.queryExistCount(evt);
			if (countSize > 0) {
				String atachCode = evt.getAttachCode();
				String attachCodeData = ftpAttchService.queryExistCode(evt);
				// 没有编码，或者编码一致才能进行下一步
				if (StringUtils.isBlank(attachCodeData) || attachCodeData.equals(atachCode)) {
					ftpAttchInfos = ftpAttchService.queryFtpAttchByParam(evt);
					resp.success(ftpAttchInfos);
				} else {
					resp.error("重要编码缺失");

				}

			} else {
				resp.error(CommonResultCode.countError);

			}

		} catch (Exception e) {
			e.printStackTrace();
			return resp.error("查询失败");
		}
		return resp;
	}

	@ServiceAround
	@ApiOperation(value = "添加Ftp文件参数#受理", notes = "respCode 1-成功")
	@RequestMapping(value = "doAdd", method = RequestMethod.POST)
	public ServerResp<Object> doAdd(@RequestBody Message<AddFtpAttchEvt> message) {
		AddFtpAttchEvt evt = message.getBody();
		String getCode = evt.getGetCode();
		if ("Y".equals(getCode)) {
			String attachCode = new AttachCode().nextId();
			evt.setAttachCode(attachCode);
		}
		// 唯一编码，不能重复

		evt.setStatus("E");
		evt.setCreateTime(new Date());
		evt.setUpdateTime(new Date());
		// System.out.println("增加Ftp文件参数"+evt.getOrgFileName());
		// System.out.println("增加Ftp文件参数"+evt.getSaveFileName());
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			/*
			 * if(result.hasErrors()){ System.out.println(result.getFieldError()); return
			 * resp.setBindError(result); }
			 */
			return resp.success(ftpAttchService.add(evt));
		} catch (Exception e) {
			e.printStackTrace();
			return resp.error();
		}
	}

	@ServiceAround
	@ApiOperation(value = "编辑Ftp文件参数#受理", notes = "respCode 1-成功")
	@RequestMapping(value = "doEdit", method = RequestMethod.POST)
	public ServerResp<Object> doEdit(@RequestBody Message<EditFtpAttchEvt> message) {
		EditFtpAttchEvt evt = message.getBody();
		evt.setUpdateTime(new Date());
		ServerResp<Object> resp = new ServerResp<Object>();
		int n = ftpAttchService.doEdit(evt);
		return resp.success(n);
	}

	@ServiceAround
	@ApiOperation(value = "删除Ftp文件参数#受理", notes = "respCode 1-成功")
	@RequestMapping(value = "doDelete", method = RequestMethod.POST)
	public ServerResp<Object> doDelete(@RequestBody Message<DelFtpAttchEvt> message) {
		DelFtpAttchEvt evt = message.getBody();
		// System.out.println("delId:"+evt.getId());
		ServerResp<Object> resp = new ServerResp<Object>();
		int n = ftpAttchService.doDelete(evt);
		// System.out.println("n:"+n);
		return resp.success(n);
	}

	@ServiceAround
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询图片数量#查询", notes = "")
	@RequestMapping(value = "queryPicCount", method = RequestMethod.POST)
	public ServerResp<Object> queryPicCount(@RequestBody Message<QueryFtpAttchEvt> message) {
		QueryFtpAttchEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		int cnt = ftpAttchService.queryPicCount(evt);
		return resp.success(cnt);
	}

	@ServiceAround
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询图片数据#查询", notes = "")
	@RequestMapping(value = "queryPicByParam", method = RequestMethod.POST)
	public ServerResp<Object> queryPicByParam(@RequestBody Message<QueryFtpAttchEvt> message) {
		QueryFtpAttchEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			resp.success(ftpAttchService.queryPicByParam(evt));
			return resp;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return resp.error();
		}
	}

	@ServiceAround
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "逻辑删除#受理", notes = "")
	@RequestMapping(value = "doDeleteByStatus", method = RequestMethod.POST)
	public ServerResp<Object> doDeleteByStatus(@RequestBody Message<DelFtpAttchMultEvt> message) {
		DelFtpAttchMultEvt evt = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			// 保存
			return resp.success(ftpAttchService.doDeleteByStatus(evt));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return resp.error("保存失败");
		}
	}

}
