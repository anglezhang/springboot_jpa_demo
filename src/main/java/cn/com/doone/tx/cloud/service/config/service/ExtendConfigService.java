package cn.com.doone.tx.cloud.service.config.service;


import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.doone.tx.cloud.service.config.bean.ExtendFieldBean;
import cn.com.doone.tx.cloud.service.config.dao.mapper.ConfigExtendMapper;
import cn.com.doone.tx.cloud.service.config.evt.extend.AddExtendEvt;
import cn.com.doone.tx.cloud.service.config.evt.extend.TdExtendField;
import cn.com.doone.tx.cloud.service.config.info.TdExtendFieldInfo;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatRepository;

/**
 * Created by loser on 2017/2/13.
 */
@Service("extendConfigService")
@Transactional
public class ExtendConfigService {

	@Autowired
	private ConfigExtendMapper configExtendMapper;

	@Autowired
	private PlatRepository webRepository;

	// @Cacheable(value = "ExtendConfigService.queryMenuCount",keyGenerator =
	// "defaultKeyGenerator")
	@Transactional
	public ServerResp<Object> queryMenuCount(TdExtendField tdExtendField) {
		ServerResp<Object> resp = new ServerResp<Object>();
		int extendCount = configExtendMapper.getExtendCount(tdExtendField);
		resp.getHead().setRespCode(0);
		resp.setBody(extendCount);
		return resp;
	}

	// @Cacheable(value = "ExtendConfigService.queryMenuCount",keyGenerator =
	// "defaultKeyGenerator")
	@Transactional
	public ServerResp<Object> getExtendByParam(TdExtendField tdExtendField) {
		ServerResp<Object> resp = new ServerResp<Object>();
		List<TdExtendField> extendByParam = configExtendMapper.getExtendByParam(tdExtendField);
		resp.getHead().setRespCode(0);
		resp.setBody(extendByParam);
		return resp;
	}

	// @Cacheable(value = "ExtendConfigService.queryExtendByTableName",keyGenerator
	// = "defaultKeyGenerator")
	@Transactional
	public ServerResp<Object> queryExtendByTableName(TdExtendField tdExtendField) {
		ServerResp<Object> resp = new ServerResp<Object>();
		List<TdExtendField> extendByParam = configExtendMapper.queryExtendByTableName(tdExtendField);
		resp.getHead().setRespCode(0);
		resp.setBody(extendByParam);

		return resp;
	}

	@Transactional
	public ServerResp<Object> del(TdExtendField tdExtendField) {
		ServerResp<Object> resp = new ServerResp<Object>();
		int delTotal = configExtendMapper.updateStatusByParam(tdExtendField);
		if (delTotal > 0) {
			resp.getHead().setRespCode(0);
		} else {
			resp.getHead().setRespCode(-1);
		}
		return resp;
	}

	@Transactional
	public ServerResp<Object> doAdd(AddExtendEvt addExtendEvt) {
		String tableName = addExtendEvt.getTableName();
		String extendCode = addExtendEvt.getExtendCode();
		List<String> fieldName = addExtendEvt.getFieldName();
		List<String> fieldCode = addExtendEvt.getFieldCode();
		List<String> fieldValueType = addExtendEvt.getFieldValueType();
		List<String> fieldValueLength = addExtendEvt.getFieldValueLength();
		List<String> fieldTitle = addExtendEvt.getFieldTitle();
		List<String> fieldRemark = addExtendEvt.getFieldRemark();
		List<String> fieldType = addExtendEvt.getFieldType();
		List<String> sort = addExtendEvt.getSort();
		List<String> isMust = addExtendEvt.getIsMust();
		List<String> regularExpress = addExtendEvt.getRegularExpress();
		List<String> dictTableName = addExtendEvt.getDictTableName();
		List<String> dictColumnName = addExtendEvt.getDictColumnName();
		List<String> status = addExtendEvt.getStatusa();
		int size = Integer.parseInt(addExtendEvt.getExtendSize().toString());
		ExtendFieldBean extendFieldBean = null;
		for (int i = 0; i < size; i++) {
			extendFieldBean = new ExtendFieldBean();
			extendFieldBean.setTableName(tableName);
			if (StringUtils.isNotBlank(extendCode)) {
				extendFieldBean.setExtendCode(extendCode);
			}
			if (fieldName != null && fieldName.size() >= i)
				extendFieldBean.setFieldName(fieldName.get(i));
			if (fieldCode != null && fieldCode.size() >= i)
				extendFieldBean.setFieldCode(fieldCode.get(i));
			if (fieldValueType != null && fieldValueType.size() >= i)
				extendFieldBean.setFieldValueType(
						fieldValueType.get(i) == "" ? null : Integer.parseInt(fieldValueType.get(i)));
			if (fieldValueLength != null && fieldValueLength.size() >= i)
				extendFieldBean.setFieldValueLength(
						fieldValueLength.get(i) == "" ? null : Integer.parseInt(fieldValueLength.get(i)));
			if (fieldTitle != null && fieldTitle.size() >= i)
				extendFieldBean.setFieldTitle(fieldTitle.get(i));
			if (fieldRemark != null && fieldRemark.size() >= i)
				extendFieldBean.setFieldRemark(fieldRemark.get(i));
			if (fieldType != null && fieldType.size() >= i)
				extendFieldBean.setFieldType(fieldType.get(i) == "" ? null : Integer.parseInt(fieldType.get(i)));
			if (sort != null && sort.size() >= i)
				extendFieldBean.setSort(sort.get(i) == "" ? null : Integer.parseInt(sort.get(i)));
			if (isMust != null && isMust.size() >= i)
				extendFieldBean.setIsMust(isMust.get(i) == "" ? null : Integer.parseInt(isMust.get(i)));
			if (regularExpress != null && regularExpress.size() >= i)
				extendFieldBean.setRegularExpress(regularExpress.get(i));
			if (dictTableName != null && dictTableName.size() >= i)
				extendFieldBean.setDictTableName(dictTableName.get(i));
			if (dictColumnName != null && dictColumnName.size() >= i)
				extendFieldBean.setDictColumnName(dictColumnName.get(i));
			if (status != null && status.size() >= i)
				extendFieldBean.setStatus(status.get(i));
			extendFieldBean.setCreateTime(new Date());
			extendFieldBean.setUpdateTime(new Date());
			webRepository.save(extendFieldBean);
		}
		ServerResp<Object> resp = new ServerResp<Object>();
		resp.getHead().setRespCode(0);
		return resp;
	}

	public ServerResp<Object> doEdit(Map<String, Object> params)
			throws InvocationTargetException, IllegalAccessException {

		// 将删除字段的id，更新状态为D
		String tableName = (String) params.get("tableName");
		String extendCode = null;
		if (params.get("extendCode") != null) {
			extendCode = (String) params.get("extendCode");
		}
		TdExtendField td = new TdExtendField();
		td.setTableName(tableName);
		td.setExtendCode(extendCode);
		configExtendMapper.updateStatusByParam(td);

		ServerResp<Object> resp = new ServerResp<Object>();
		List<String> fieldName = (List<String>) params.get("fieldName");
		List<String> fieldCode = (List<String>) params.get("fieldCode");
		List<String> fieldValueType = (List<String>) params.get("fieldValueType");
		List<String> fieldValueLength = (List<String>) params.get("fieldValueLength");
		List<String> fieldTitle = (List<String>) params.get("fieldTitle");
		List<String> fieldRemark = (List<String>) params.get("fieldRemark");
		List<String> fieldType = (List<String>) params.get("fieldType");
		List<String> sort = (List<String>) params.get("sort");
		List<String> isMust = (List<String>) params.get("isMust");
		List<String> regularExpress = (List<String>) params.get("regularExpress");
		List<String> dictTableName = (List<String>) params.get("dictTableName");
		List<String> dictColumnName = (List<String>) params.get("dictColumnName");
		List<String> status = (List<String>) params.get("status");
		int size = Integer.parseInt(params.get("extendSize").toString());
		TdExtendField tdExtendField = null;
		ExtendFieldBean extendFieldBean = new ExtendFieldBean();
		for (int i = 0; i < size; i++) {
			tdExtendField = new TdExtendField();
			tdExtendField.setTableName(tableName);
			if (StringUtils.isNotBlank(extendCode)) {
				tdExtendField.setExtendCode(extendCode);
			}
			if (fieldName != null && fieldName.size() >= i)
				tdExtendField.setFieldName(fieldName.get(i));
			if (fieldCode != null && fieldCode.size() >= i)
				tdExtendField.setFieldCode(fieldCode.get(i));
			if (fieldValueType != null && fieldValueType.size() >= i)
				tdExtendField.setFieldValueType(
						fieldValueType.get(i) == "" ? null : Integer.parseInt(fieldValueType.get(i)));
			if (fieldValueLength != null && fieldValueLength.size() >= i)
				tdExtendField.setFieldValueLength(
						fieldValueLength.get(i) == "" ? null : Integer.parseInt(fieldValueLength.get(i)));
			if (fieldTitle != null && fieldTitle.size() >= i)
				tdExtendField.setFieldTitle(fieldTitle.get(i));
			if (fieldRemark != null && fieldRemark.size() >= i)
				tdExtendField.setFieldRemark(fieldRemark.get(i));
			if (fieldType != null && fieldType.size() >= i)
				tdExtendField.setFieldType(fieldType.get(i) == "" ? null : Integer.parseInt(fieldType.get(i)));
			if (sort != null && sort.size() >= i)
				tdExtendField.setSort(sort.get(i) == "" ? null : Integer.parseInt(sort.get(i)));
			if (isMust != null && isMust.size() >= i)
				tdExtendField.setIsMust(isMust.get(i) == "" ? null : Integer.parseInt(isMust.get(i)));
			if (regularExpress != null && regularExpress.size() >= i)
				tdExtendField.setRegularExpress(regularExpress.get(i));
			if (dictTableName != null && dictTableName.size() >= i)
				tdExtendField.setDictTableName(dictTableName.get(i));
			if (dictColumnName != null && dictColumnName.size() >= i)
				tdExtendField.setDictColumnName(dictColumnName.get(i));
			if (status != null && status.size() >= i)
				tdExtendField.setStatus(status.get(i));
			tdExtendField.setUpdateTime(new Date());
			List<TdExtendField> tdExtendFields = configExtendMapper.queryExtendByTableName(tdExtendField);
			if (tdExtendFields.size() == 1) {
				// 执行更新操作
				configExtendMapper.doEdit(tdExtendField);
			} else {
				BeanUtils.copyProperties(tdExtendField,extendFieldBean);
				// 执行插入操作
				extendFieldBean.setCreateTime(new Date());
				webRepository.save(extendFieldBean);
			}
		}
		resp.getHead().setRespCode(0);
		return resp;
	}

	@Transactional
	public ServerResp<Object> queryDictTableName() {
		List<String> data = configExtendMapper.queryDictTableName();
		ServerResp<Object> resp = new ServerResp<Object>();
		resp.getHead().setRespCode(0);
		;
		resp.setBody(data);
		return resp;
	}

	@Transactional
	public ServerResp getDictColumnName(TdExtendField tdExtendField) {
		List<String> data = configExtendMapper.getDictColumnName(tdExtendField.getDictColumnName());
		ServerResp resp = new ServerResp();
		resp.getHead().setRespCode(0);
		;
		resp.setBody(data);
		return resp;
	}

	@Transactional
	public List<TdExtendFieldInfo> queryExtendFieldByParam(TdExtendField tdExtendField) {
		List<TdExtendFieldInfo> extendByParam = configExtendMapper.queryExtendFieldByParam(tdExtendField);
		return extendByParam;
	}

}
