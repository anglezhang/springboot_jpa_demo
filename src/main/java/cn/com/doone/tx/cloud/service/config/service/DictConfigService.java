package cn.com.doone.tx.cloud.service.config.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.doone.tx.cloud.service.config.bean.ConfigDict;
import cn.com.doone.tx.cloud.service.config.dao.mapper.DictConfigMapper;
import cn.com.doone.tx.cloud.service.config.evt.dict.AddDictEvt;
import cn.com.doone.tx.cloud.service.config.evt.dict.DeleteDictEvt;
import cn.com.doone.tx.cloud.service.config.evt.dict.EditDictEvt;
import cn.com.doone.tx.cloud.service.config.evt.dict.EditDictTableEvt;
import cn.com.doone.tx.cloud.service.config.evt.dict.QueryCenterDatabaseEvt;
import cn.com.doone.tx.cloud.service.config.evt.dict.QueryDictConfigEvt;
import cn.com.doone.tx.cloud.service.config.evt.dict.QueryDictEvt;
import cn.com.doone.tx.cloud.service.config.info.CenterTableInfo;
import cn.com.doone.tx.cloud.service.config.info.DictInfo;
import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatRepository;

@Service("dictConfigService")
@Transactional
public class DictConfigService {

	@Autowired
	private DictConfigMapper dictConfigMapper;

	@Autowired
	private PlatRepository platRepository;

	public Integer queryDictCount(QueryDictEvt queryDictEvt) {
		return dictConfigMapper.queryDictCount(queryDictEvt);

	}

	public List<DictInfo> queryDictConfig(QueryDictConfigEvt queryDictConfigEvt) {
		List<DictInfo> dict = dictConfigMapper.queryDictConfig(queryDictConfigEvt);

		return dict;
	}

	public ConfigDict doAdd(AddDictEvt addDictEvt) throws InvocationTargetException, IllegalAccessException {
		ConfigDict configDict = new ConfigDict();
		BeanUtils.copyProperties(addDictEvt,configDict);
		configDict.setStatus("E");
		configDict.setCreateTime(new Date());
		configDict.setLanguageType(addDictEvt.getLanguageTypeAdd());
		configDict.setUpdateTime(new Date());
		configDict.setOperator(addDictEvt.getOperator());
		configDict.setCreator(addDictEvt.getOperator());
		return platRepository.save(configDict);
	}

	public List<DictInfo> queryDictConfigByParam(QueryDictEvt queryDictEvt) {
		return dictConfigMapper.queryDictConfigByParam(queryDictEvt);
	}

	public List<DictInfo> queryDictConfigByParamToPage(QueryDictEvt queryDictEvt) {

		return dictConfigMapper.queryDictConfigByParam(queryDictEvt);
	}

	public int queryDictConfigCountByParam(QueryDictEvt queryDictEvt) {
		return dictConfigMapper.queryDictConfigCountByParam(queryDictEvt);
	}

	public Integer doEdit(EditDictEvt editDictEvt) {
		editDictEvt.setUpdateTime(new Date());
		editDictEvt.setOperator(editDictEvt.getOperator());

		return dictConfigMapper.doEdit(editDictEvt);
	}

	public Integer doEditTable(EditDictTableEvt editDictTableEvt) {
		editDictTableEvt.setUpdateTime(new Date());
		editDictTableEvt.setOperator(editDictTableEvt.getOperator());
		return dictConfigMapper.doEditTable(editDictTableEvt);
	}

	public Integer doDeleteByParam(DeleteDictEvt deleteDictEvt) {
		deleteDictEvt.setUpdateTime(new Date());

		return dictConfigMapper.doDeleteByParam(deleteDictEvt);
	}

	public List<CenterTableInfo> getCenterTableName(QueryCenterDatabaseEvt queryCenterDatabaseEvt) {
		return dictConfigMapper.getCenterTableName(queryCenterDatabaseEvt);
	}

	public List<CenterTableInfo> getCenterColumnName(QueryCenterDatabaseEvt queryCenterDatabaseEvt) {
		return dictConfigMapper.getCenterColumnName(queryCenterDatabaseEvt);
	}

}
