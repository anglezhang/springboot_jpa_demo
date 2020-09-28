package cn.com.doone.tx.cloud.service.config.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;

import cn.com.doone.tx.cloud.service.config.bean.ConfigDict;
import cn.com.doone.tx.cloud.service.config.evt.dict.AddDictEvt;
import cn.com.doone.tx.cloud.service.config.evt.dict.DeleteDictEvt;
import cn.com.doone.tx.cloud.service.config.evt.dict.EditDictEvt;
import cn.com.doone.tx.cloud.service.config.evt.dict.EditDictTableEvt;
import cn.com.doone.tx.cloud.service.config.evt.dict.QueryCenterDatabaseEvt;
import cn.com.doone.tx.cloud.service.config.evt.dict.QueryDictConfigEvt;
import cn.com.doone.tx.cloud.service.config.evt.dict.QueryDictEvt;
import cn.com.doone.tx.cloud.service.config.info.DictInfo;
import cn.com.doone.tx.cloud.service.config.service.DictConfigService;
import cn.com.doone.tx.cloud.tool.common.enums.CommonResultCode;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.log.annotation.ServiceAround;
import cn.com.doone.tx.cloud.tool.service.common.base.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by APPLE on 2017/2/4.
 */
@RestController
@RequestMapping("/dictConfig/dict")
@Api(description = "字典表配置服务")
public class DictConfigController {
	private static final Logger lOGGER = Logger.getLogger(DictConfigController.class);

	@Autowired
	private DictConfigService dictConfigService;

	@ServiceAround
	@ApiOperation(value = "获取字典表配置数量#查询", notes = "")
	@RequestMapping(value = "queryDictCount", method = RequestMethod.POST)
	public ServerResp<Object> queryDictCount(@RequestBody Message<QueryDictEvt> message) {
		QueryDictEvt queryDictEvt = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			Integer count = dictConfigService.queryDictCount(queryDictEvt);
			if (count >= 0) {
				resp.success(count);
			} else {

				resp.error(CommonResultCode.countError);
			}
		} catch (Exception e) {
			lOGGER.error("获取字典表配置数量异常：" + e, e);
			return resp.systemError();
		}

		return resp;
	}

	@ServiceAround
	@ApiOperation(value = "查询字典表配置#查询", notes = "")
	@RequestMapping(value = "queryDictConfig", method = RequestMethod.POST)
	public ServerResp<Object> queryDictConfig(@RequestBody Message<QueryDictConfigEvt> message) {
		QueryDictConfigEvt queryDictConfigEvt = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		PageHelper.startPage(queryDictConfigEvt.getQueryPage(), queryDictConfigEvt.getQuerySize());

		try {
			resp.success(dictConfigService.queryDictConfig(queryDictConfigEvt));
		} catch (Exception e) {
			lOGGER.error("查询字典表配置异常：" + e, e);
			return resp.systemError();
		}

		return resp;
	}

	@ServiceAround
	@ApiOperation(value = "新增字典表配置#受理", notes = "")
	@RequestMapping(value = "doAdd", method = RequestMethod.POST)
	public ServerResp<Object> doAdd(@RequestBody Message<AddDictEvt> message) {
		ServerResp<Object> resp = new ServerResp<Object>();
		AddDictEvt evt = message.getBody();
		try {
			// 字典值查重
			QueryDictEvt queryDictEvt = new QueryDictEvt();
			queryDictEvt.setTableName(evt.getTableName());
			queryDictEvt.setColumnName(evt.getColumnName());
			queryDictEvt.setDictValue(evt.getDictValue());
			queryDictEvt.setLanguageTypeAdd(evt.getLanguageTypeAdd());
			int isExist = dictConfigService.queryDictConfigCountByParam(queryDictEvt);
			if (isExist > 0) {
				return resp.error("AUTH_DICT_EXIST");
			}
			ConfigDict dict = dictConfigService.doAdd(evt);
			if (dict.getId() != null) {
				resp.success(null, "GOODS_ADDSUCCESS");
			} else {
				resp.error(CommonResultCode.addFail);

			}
			return resp.success();
		} catch (Exception e) {
			lOGGER.error("新增字典表配置异常：" + e, e);
			return resp.systemError();
		}
	}

	@ServiceAround
	@ApiOperation(value = "编辑字典表值配置#受理", notes = "")
	@RequestMapping(value = "doEdit", method = RequestMethod.POST)
	public ServerResp<Object> doEdit(@RequestBody Message<EditDictEvt> message) {
		ServerResp<Object> resp = new ServerResp<Object>();
		EditDictEvt evt = message.getBody();
		try {
			// 字典值查重
			QueryDictEvt queryDictEvt = new QueryDictEvt();
			queryDictEvt.setTableName(evt.getTableName());
			queryDictEvt.setColumnName(evt.getColumnName());
			queryDictEvt.setDictValue(evt.getDictValue());
			queryDictEvt.setDictExistId(evt.getDictId());
			queryDictEvt.setLanguageTypeAdd(evt.getLanguageTypeAdd());
			int isExist = dictConfigService.queryDictConfigCountByParam(queryDictEvt);
			if (isExist > 0) {
				return resp.error("AUTH_DICT_EXIST");
			}

			Integer count = dictConfigService.doEdit(evt);
			if (count > 0) {
				resp.success(null, "GOODS_GOODS_EDITSUCCESS");

			} else {
				resp.error(CommonResultCode.editFail);
			}
		} catch (Exception e) {
			lOGGER.error("编辑字典表值配置异常：" + e, e);
			return resp.systemError();
		}
		return resp;
	}

	@ServiceAround
	@ApiOperation(value = "编辑字典表表名字段配置#受理", notes = "")
	@RequestMapping(value = "doEditTable", method = RequestMethod.POST)
	public ServerResp<Object> doEditTable(@RequestBody Message<EditDictTableEvt> message) {
		ServerResp<Object> resp = new ServerResp<Object>();
		EditDictTableEvt evt = message.getBody();
		try {
			Integer count = dictConfigService.doEditTable(evt);
			if (count > 0) {
				resp.success(null, "GOODS_GOODS_EDITSUCCESS");

			} else {
				resp.error(CommonResultCode.editFail);
			}

		} catch (Exception e) {
			lOGGER.error("编辑字典表表名字段配置异常：" + e, e);
			return resp.systemError();
		}
		return resp;
	}

	@ServiceAround
	@ApiOperation(value = "根据参数删除字典表配置#受理", notes = "")
	@RequestMapping(value = "doDeleteByParam", method = RequestMethod.POST)
	public ServerResp<Object> doDeleteByParam(@RequestBody Message<DeleteDictEvt> message) {
		ServerResp<Object> resp = new ServerResp<Object>();
		DeleteDictEvt deleteDictEvt = message.getBody();

		try {
			Integer count = dictConfigService.doDeleteByParam(deleteDictEvt);
			if (count > 0) {
				resp.success(null, "GOODS_GOODS_DELETESUCCESS");
			} else {

				resp.error(CommonResultCode.deleteFail);
			}

		} catch (Exception e) {
			lOGGER.error("据参数删除字典表配置异常：" + e, e);
			return resp.systemError();
		}

		return resp;
	}

	@ServiceAround
	@ApiOperation(value = "根据参数查询字典表配置#查询", notes = "")
	@RequestMapping(value = "queryDictConfigByParam", method = RequestMethod.POST)
	public ServerResp<Object> queryDictConfigByParam(@RequestBody Message<QueryDictEvt> message) {
		ServerResp<Object> resp = new ServerResp<Object>();
		QueryDictEvt queryDictEvt = message.getBody();

		try {
			List<DictInfo> list = dictConfigService.queryDictConfigByParam(queryDictEvt);
			return resp.success(list);
		} catch (Exception e) {
			lOGGER.error("根据参数查询字典表配置异常：" + e, e);
			return resp.systemError();
		}
	}

	@ServiceAround
	@ApiOperation(value = "查询某中心下的表名", notes = "")
	@RequestMapping(value = "getCenterTableName", method = RequestMethod.POST)
	public ServerResp<Object> getCenterTableName(@RequestBody Message<QueryCenterDatabaseEvt> message) {
		QueryCenterDatabaseEvt paramEvt = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			resp.success(dictConfigService.getCenterTableName(paramEvt));
		} catch (Exception e) {
			lOGGER.error("查询某中心下的表名异常：" + e, e);
			return resp.systemError();
		}
		return resp;
	}

	@ServiceAround
	@ApiOperation(value = "查询某中心下某表的字段名", notes = "")
	@RequestMapping(value = "getCenterColumnName", method = RequestMethod.POST)
	public ServerResp<Object> getCenterColumnName(@RequestBody Message<QueryCenterDatabaseEvt> message) {
		QueryCenterDatabaseEvt paramEvt = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			resp.success(dictConfigService.getCenterColumnName(paramEvt));
		} catch (Exception e) {
			lOGGER.error("查询某中心下某表的字段名异常：" + e, e);
			return resp.systemError();
		}
		return resp;
	}

	@ServiceAround
	@ApiOperation(value = "根据参数查询字典表配置#查询", notes = "")
	@RequestMapping(value = "queryDictByParam", method = RequestMethod.POST)
	public ServerResp<Object> queryDictByParam(@RequestBody Message<Map<String, String>> message) {
		ServerResp<Object> resp = new ServerResp<Object>();
		Map<String, String> params = message.getBody();
		QueryDictEvt queryDictEvt = new QueryDictEvt();
		queryDictEvt.setTableName(params.get("table_name") + "");
		queryDictEvt.setColumnName(params.get("column_name") + "");
		try {
			return resp.success(dictConfigService.queryDictConfigByParam(queryDictEvt));
		} catch (Exception e) {
			lOGGER.error("根据参数查询字典表配置异常：" + e, e);
			return resp.systemError();
		}
	}

	@ServiceAround
	@ApiOperation(value = "根据参数查询字典表配置(带分页)#查询", notes = "")
	@RequestMapping(value = "queryDictConfigByParamToPage", method = RequestMethod.POST)
	public ServerResp<Object> queryDictConfigByParamToPage(@RequestBody Message<QueryDictEvt> message) {
		ServerResp<Object> resp = new ServerResp<Object>();
		QueryDictEvt queryDictEvt = message.getBody();
		try {
			return resp.success(dictConfigService.queryDictConfigByParamToPage(queryDictEvt));
		} catch (Exception e) {
			lOGGER.error("根据参数查询字典表配置(带分页)异常：" + e, e);
			return resp.systemError();
		}
	}

	@ServiceAround
	@ApiOperation(value = "根据参数查询字典表配置数量#查询", notes = "")
	@RequestMapping(value = "queryDictConfigCountByParam", method = RequestMethod.POST)
	public ServerResp<Object> queryDictConfigCountByParam(@RequestBody Message<QueryDictEvt> message) {
		ServerResp<Object> resp = new ServerResp<Object>();
		QueryDictEvt queryDictEvt = message.getBody();
		try {
			return resp.success(dictConfigService.queryDictConfigCountByParam(queryDictEvt));
		} catch (Exception e) {
			lOGGER.error("根据参数查询字典表配置数量异常：" + e, e);
			return resp.systemError();
		}
	}
}
