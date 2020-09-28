package cn.com.doone.tx.cloud.service.config.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.doone.tx.cloud.service.config.evt.dict.QueryDictEvt;
import cn.com.doone.tx.cloud.service.config.evt.extend.AddExtendEvt;
import cn.com.doone.tx.cloud.service.config.evt.extend.QueryExtendInfoEvt;
import cn.com.doone.tx.cloud.service.config.evt.extend.TdExtendField;
import cn.com.doone.tx.cloud.service.config.info.DictInfo;
import cn.com.doone.tx.cloud.service.config.info.TdExtendFieldInfo;
import cn.com.doone.tx.cloud.service.config.service.DictConfigService;
import cn.com.doone.tx.cloud.service.config.service.ExtendConfigService;
import cn.com.doone.tx.cloud.service.config.service.GetExtendInfoService;
import cn.com.doone.tx.cloud.tool.common.invoke.RestUtil;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import cn.com.doone.tx.cloud.tool.log.annotation.ServiceAround;
import cn.com.doone.tx.cloud.tool.service.common.base.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by loser on 2017/2/13.
 */
@RestController
@RequestMapping("/config/extend")
@Api(description = "扩展表配置")
public class ConfigExtendController {

	@Autowired
	private ExtendConfigService extendConfigService;

	@Autowired
	private DictConfigService dictConfigService;

	@Autowired
	private GetExtendInfoService  getExtendInfoService;

	@Autowired
	private RestUtil restUtil;
	
	@Autowired
	private RestUtil webRestUtil;

	@ServiceAround
	@ApiOperation(value="通过参数查询扩展表记录数量#查询", notes="")
	@RequestMapping(value = "getExtendCount" , method = RequestMethod.POST)
	public ServerResp<Object> getExtendCount(@RequestBody Message<TdExtendField> message){
		ServerResp<Object> resp = extendConfigService.queryMenuCount(message.getBody());
		return resp;
	}

	@ServiceAround
	@ApiOperation(value="通过参数查询扩展表#查询", notes="")
	@RequestMapping(value = "getExtendByParam" , method = RequestMethod.POST)
	public ServerResp<Object> getExtendByParam(@RequestBody Message<TdExtendField> message){
		TdExtendField tdExtendField=message.getBody();
		tdExtendField.startPage();;
		ServerResp<Object> resp = extendConfigService.getExtendByParam(tdExtendField);
		return resp;
	}

	@ServiceAround
	@ApiOperation(value="通过参数查询扩展表#查询", notes="")
	@RequestMapping(value = "queryExtendByTableName" , method = RequestMethod.POST)
	public ServerResp<Object> queryExtendByTableName(@RequestBody Message<TdExtendField> message){
		ServerResp<Object> resp = extendConfigService.queryExtendByTableName(message.getBody());
		return resp;
	}

	@ServiceAround
	@ApiOperation(value="添加参数查询扩展表记录#受理", notes="")
	@RequestMapping(value = "doAdd" , method = RequestMethod.POST)
	public ServerResp doAdd(@RequestBody  Message<AddExtendEvt> message){
		ServerResp resp= new ServerResp();
		AddExtendEvt addExtendEvt=message.getBody();
		try {
			return extendConfigService.doAdd(addExtendEvt);
		} catch (Exception e) {
			e.printStackTrace();
			return resp.systemError();
		}
	}

	@ServiceAround
	@ApiOperation(value="更新参数查询扩展表记录#受理", notes="")
	@RequestMapping(value = "doEdit" , method = RequestMethod.POST)
	public ServerResp<Object> doEdit(@RequestBody Message<Map<String, Object>> message){
		ServerResp<Object> resp = null;
		try {
			resp = extendConfigService.doEdit(message.getBody());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	@ServiceAround
	@ApiOperation(value="删除参数查询扩展表记录#受理", notes="")
	@RequestMapping(value = "del" , method = RequestMethod.POST)
	public ServerResp<Object> del(@RequestBody Message<TdExtendField> message){
		ServerResp<Object> resp = extendConfigService.del(message.getBody());
		return resp;
	}

	@ApiOperation(value="查询字典表表名#查询", notes="")
	@RequestMapping(value = "queryDictTableName" , method = RequestMethod.POST)
	public ServerResp<Object> queryDictTableName(){
		ServerResp<Object> resp = extendConfigService.queryDictTableName();
		return resp;
	}
	@ServiceAround
	@ApiOperation(value="根据表名查询字典属性#查询", notes="")
	@RequestMapping(value = "getDictColumnName" , method = RequestMethod.POST)
	public ServerResp getDictColumnName(@RequestBody Message<TdExtendField> message){
		ServerResp resp = extendConfigService.getDictColumnName(message.getBody());
		return resp;
	}

	@ServiceAround
	@ApiOperation(value="通过参数查询扩展字段配置信息#查询")
	@RequestMapping(value = "queryExtendFieldByParam" , method = RequestMethod.POST)
	public ServerResp<Object> queryExtendFieldByParam(@RequestBody Message<TdExtendField> message){
		System.out.println(">>>>通过参数查询扩展字段配置信息<<<<");
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			TdExtendField tdExtendField = message.getBody();
			if(StringUtils.isBlank(tdExtendField.getTableName())){
				resp.getHead().setRespCode(-1);
				resp.getHead().setRespMsg("参数不足");
				return resp;
			}
			List<TdExtendFieldInfo> extendFields = extendConfigService.queryExtendFieldByParam(tdExtendField);
			if(extendFields != null && extendFields.size() > 0){
				resp.getHead().setRespCode(0);
				resp.setBody(extendFields);
			}else{
				resp.getHead().setRespCode(-1);
				resp.getHead().setRespMsg("无扩展字段配置信息");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.getHead().setRespCode(-1);
			resp.getHead().setRespMsg("查询扩展字段配置信息异常！");
		}
		System.out.println(">>>>返回<<<<"+resp);
		return resp;
	}

	@ServiceAround
	@ApiOperation(value = "扩展信息校验#受理")
	@RequestMapping(value = "validateExtendInfo", method = RequestMethod.POST)
	public ServerResp<Object> validateExtendInfo(@RequestBody Message<Map<String, String>> message) {
		Map<String, String> params = message.getBody();
		ServerResp<Object> resp = new ServerResp<Object>();
		try {
			Map<String, Object> data = new HashMap<String, Object>();
			TdExtendField tdExtendField = new TdExtendField();
			tdExtendField.setTableName(params.get("tableName"));
			if(StringUtils.isNotBlank(params.get("extendCode"))){
				tdExtendField.setExtendCode(params.get("extendCode"));
			}
			if(StringUtils.isBlank(tdExtendField.getTableName())){
				return resp.error("参数不足");
			}
			List<TdExtendFieldInfo> TdExtendFields = extendConfigService
					.queryExtendFieldByParam(tdExtendField);
			String tempValue;
			for (TdExtendFieldInfo field : TdExtendFields) {
				tempValue = params.get(field.getFieldCode());
				if (field.getIsMust() == 1 && StringUtils.isBlank(tempValue)) {// 判空
					return resp.error(field.getFieldTitle() + "不能为空！");
				}
				if (StringUtils.isNotBlank(tempValue)) {
					if (field.getFieldValueLength() != null
							&& tempValue.length() > field.getFieldValueLength()) {// 长度判断
						return resp.error(field.getFieldTitle() + "长度不能超过"
								+ field.getFieldValueLength() + "！");
					}
					if (field.getFieldValueType() == 1) {// int类型判断
						try {
							Integer.valueOf(tempValue);
						} catch (Exception e) {
							e.printStackTrace();
							return resp.systemError(field.getFieldTitle()
									+ "必须是int类型的！");
						}
					} else if (field.getFieldValueType() == 3) {// double类型判断
						try {
							Double.valueOf(tempValue);
						} catch (Exception e) {
							e.printStackTrace();
							return resp.systemError(field.getFieldTitle()
									+ "必须是double类型的！");
						}
					}
					if (StringUtils.isNotBlank(field.getRegularExpress())) {// 正则判断
						Pattern p = Pattern.compile(field.getRegularExpress());
						Matcher m = p.matcher(tempValue);
						if (!m.find()) {
							return resp.error(field.getFieldTitle() + "不符合正则表达式"
									+ field.getRegularExpress() + "！");
						}
					}
				}
				data.put(field.getFieldName(), tempValue);
			}
			resp.success(data);
		} catch (Exception e) {
			e.printStackTrace();
			resp.systemError("扩展信息校验异常！");
		}
		return resp;
	}

	@ServiceAround
	@ApiOperation(value = "获取扩展信息页面内容#受理")
	@RequestMapping(value = "getExtendInfoPage",method = RequestMethod.POST)
	public ServerResp<Object> getExtendInfoPage(@RequestBody Message<QueryExtendInfoEvt> message){
		QueryExtendInfoEvt evt = message.getBody();
//		System.out.println(evt.getTableName());
		ServerResp<Object> resp1 = new ServerResp<Object>();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("tableName",evt.getTableName());
		try{
			//获取表对应的扩展字段配置信息
			TdExtendField tdExtendField = new TdExtendField();
			tdExtendField.setTableName(evt.getTableName());
			List<TdExtendFieldInfo> extendFields= extendConfigService.queryExtendFieldByParam(tdExtendField);
			ServerResp resp = new ServerResp();
			Map<String,Object> datas = null;
			if(evt.getBaseId()!=null){
				String service = null;
				QueryDictEvt queryDictEvt = new QueryDictEvt();
				queryDictEvt.setColumnName("EXTEND_SERVICE_CONFIG");
				queryDictEvt.setTableName("COMMON");
				List<DictInfo> services = dictConfigService.queryDictConfigByParam(queryDictEvt);
				for (DictInfo dict : services) {
					if(dict.getDictValue().equals(evt.getTableName())){
						service = dict.getValueRemark();
					}
				}
				if(StringUtils.isNotBlank(service)){
					params.put("baseId", evt.getBaseId());
//					resp = restUtil.callRest("http://" +service, params);
//					if(resp.isSuccess()){
//						datas = (Map<String, Object>) resp.getBody();
//					}
				}
			}
			for (TdExtendFieldInfo field : extendFields) {
				if(datas != null && !datas.isEmpty()){
					field.setValue(datas.get(field.getFieldName()));
				}
//				System.out.println(field.getDictColumnName().toString());
//				System.out.println(field.getDictTableName().toString());
				if(StringUtils.isNotBlank(field.getDictColumnName().toString()) && StringUtils.isNotBlank(field.getDictTableName().toString())){
					QueryDictEvt queryDictEvt = new QueryDictEvt();
					queryDictEvt.setColumnName(field.getDictColumnName());
					queryDictEvt.setTableName(field.getDictTableName());
					field.setDicts(dictConfigService.queryDictConfigByParam(queryDictEvt));
				}
			}
			String html = "";
			for(TdExtendFieldInfo field:  extendFields){
				html += "<tr><td width=\"30%\">";
				html += "<label class=\"control-label no-padding-right\">";
				if(field.getIsMust()==1){
					html += "<span style=\"color:red;\">*</span>";
				}
				html += field.getFieldTitle()+"："+"</label></td><td>";
				//text
				if(field.getFieldType()==1){
					html += "<input type=\"text\" value=\""+(field.getValue()!=null?field.getValue():"")+"\" placeholder=\"\"";
					if(field.getIsMust()==1){
						html += " notNull=\"true\"";
					}
					html += " name=\""+field.getFieldCode()+"\" maxlength=\""+field.getFieldValueLength()+"\" fieldName=\""+field.getFieldTitle()+"\"";
					html += " placeholder=\""+field.getFieldRemark()+"\" regular=\""+field.getRegularExpress()!=null?field.getRegularExpress():""+"\" valueType=\""+field.getFieldType()+"\"/>";
				}else if(field.getFieldType()==2){ //radio
					List<DictInfo> configDicts = field.getDicts();
					for (int i = 0;i<configDicts.size();i++ ){
						html += "<label><input name=\""+field.getFieldCode()+"\"";
						if(i==0 && field.getIsMust()==1){
							html += " checked";
						}
						if(field.getValue()!=null && configDicts.get(i).getDictValue()!=null
								&& field.getValue().toString().equals(configDicts.get(i).getDictValue().toString())){
							html += " checked";
						}
						html += " type=\"radio\" value=\""+configDicts.get(i).getDictValue()+"\" />"+configDicts.get(i).getValueRemark()+"</label>";
					}
				}else if(field.getFieldType()==3){//checkbox
					List<DictInfo> configDicts = field.getDicts();
					for (int i = 0;i<configDicts.size();i++ ) {
						html += "<label><input name=\"" + field.getFieldCode() + "\" type=\"checkbox\" value=\"" + configDicts.get(i).getDictValue() + "\" />"+configDicts.get(i).getValueRemark()+" </label>";
					}
				}else if(field.getFieldType()==4){//select
					html += "<select name=\""+field.getFieldCode()+"\">";
					if(field.getIsMust()==0) {
						html += "<option value=\"\">请选择</option>";
					}
					List<DictInfo> configDicts = field.getDicts();
					for (int i = 0;i<configDicts.size();i++ ) {
						html += "<option value=\""+configDicts.get(i).getDictValue()+"\"";
						if(field.getValue()!=null && configDicts.get(i).getDictValue()!=null
								&& field.getValue().toString() .equals( configDicts.get(i).getDictValue())){
							html += "selected";
						}
						html += ">"+configDicts.get(i).getValueRemark()+"</option>";
					}
				}else if(field.getFieldType()==5){//file

				}else if(field.getFieldType()==6){//date

				}else if(field.getFieldType()==7){//textbox

				}
				html += "</td></tr>\n";
				html += "<script type=\"text/javascript\">\n" +
						"\t\n" +
						"    /** 校验方法 */\n" +
						"    function validate(){\n" +
						"     \tvar result = true;\n" +
						"    \tvar value,fieldName;\n" +
						"    \tvar regExp; \n" +
						"    \t//alert($(\"input[type='text']\").size());\n" +
						"    \t$(\"input[type='text']\").each(function(){\n" +
						"    \t\tvalue = $(this).val()\n" +
						"    \t\tfieldName = $(this).attr('fieldName');\n" +
						"    \t\tif($(this).attr('notNull') == 'true' && value == ''){\n" +
						"    \t\t\talert(fieldName+\"不能为空！\");\n" +
						"    \t\t\tresult = false;\n" +
						"    \t\t\treturn false;\n" +
						"    \t\t}\n" +
						"    \t\tif(value != ''){\n" +
						"    \t\t\tif($(this).attr('valueType') == 1){\n" +
						"    \t\t\t\tif(!/^(-?\\d+)$/.test(value)){\n" +
						"    \t\t\t\t\talert(fieldName+\"必须是整型！\");\n" +
						"    \t\t\t\t\tresult = false;\n" +
						"    \t\t\t\t\treturn false;\n" +
						"    \t\t\t\t}\n" +
						"    \t\t\t}else if($(this).attr('valueType') == 3){\n" +
						"    \t\t\t\tif(!/^(-?\\d+)(\\.\\d+)?$/.test(value)){\n" +
						"    \t\t\t\t\talert(fieldName+\"必须是浮点型！\");\n" +
						"    \t\t\t\t\tresult = false;\n" +
						"    \t\t\t\t\treturn false;\n" +
						"    \t\t\t\t}\n" +
						"    \t\t\t}\n" +
						"    \t\t\tif($(this).attr('regular') != '' && $(this).attr('regular') != undefined){\n" +
						"    \t\t\t\tregExp = new RegExp($(this).attr('regular'));\n" +
						"    \t\t\t\tif(!regExp.test(value)){\n" +
						"    \t\t\t\t\talert(fieldName+\"格式不正确！\");\n" +
						"    \t\t\t\t\tresult = false;\n" +
						"    \t\t\t\t\treturn false;\n" +
						"    \t\t\t\t}\n" +
						"    \t\t\t}\n" +
						"    \t\t}\n" +
						"    \t});\n" +
						"    \t\n" +
						"    \treturn result;\n" +
						"    }\n" +
						"\n" +
						"</script>";
			}
			return resp1.success(html);
		}catch(Exception e){
			e.printStackTrace();
			return resp1.systemError();
		}
	}
	
	@ServiceAround
	@ApiOperation(value = "获取扩展信息页面内容#查询")
	@RequestMapping(value = "getExtendInfoItems",method = RequestMethod.POST)
	public ServerResp<Object> getExtendInfoItems(@RequestBody Message<QueryExtendInfoEvt> message){
		QueryExtendInfoEvt evt = message.getBody();
		ServerResp<Object> resp1 = new ServerResp<Object>();
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("tableName",evt.getTableName());
		try{
			//获取表对应的扩展字段配置信息
			TdExtendField tdExtendField = new TdExtendField();
			tdExtendField.setTableName(evt.getTableName());
			if(StringUtils.isNotBlank(evt.getExtendCode())){
				tdExtendField.setExtendCode(evt.getExtendCode());
			}
			if(StringUtils.isNotBlank(evt.getFieldCode())){
				tdExtendField.setFieldCode(evt.getFieldCode());
			}
			List<TdExtendFieldInfo> extendFields= extendConfigService.queryExtendFieldByParam(tdExtendField);
			ServerResp resp = new ServerResp();
			Map<String,Object> datas = null;
			if(evt.getBaseId()!=null){
				String serviceUrl = null;
				QueryDictEvt queryDictEvt = new QueryDictEvt();
				queryDictEvt.setColumnName("EXTEND_SERVICE_CONFIG");
				queryDictEvt.setTableName("COMMON");
				List<DictInfo> services = dictConfigService.queryDictConfigByParam(queryDictEvt);
				for (DictInfo dict : services) {
					if(dict.getDictValue().equals(evt.getTableName())){
						serviceUrl = dict.getValueRemark();
					}
				}
				if(StringUtils.isNotBlank(serviceUrl)){
					params.put("baseId", evt.getBaseId());
//					resp = restUtil.callRest("http://" +service, params);
					resp = getExtendInfoService.getExtendInfo(serviceUrl,params);
					if(resp.isSuccess()){
						datas = (Map<String, Object>) resp.getBody();
					}
				}
			}
			for (TdExtendFieldInfo field : extendFields) {
				if(datas != null && !datas.isEmpty()){
					Object value = datas.get(field.getFieldName());
					if (field.getFieldName().toLowerCase().contains("field")&&Objects.isNull(value)) {
						value = datas.get(field.getFieldName().replace("_", "").toLowerCase());						
					}
					if (!Objects.isNull(value)) {
						field.setValue(value);
					}					
				}
				if(StringUtils.isNotBlank(field.getDictColumnName()) && StringUtils.isNotBlank(field.getDictTableName())){
					QueryDictEvt queryDictEvt = new QueryDictEvt();
					queryDictEvt.setColumnName(field.getDictColumnName());
					queryDictEvt.setTableName(field.getDictTableName());
					field.setDicts(dictConfigService.queryDictConfigByParam(queryDictEvt));
				}
			}
			return resp1.success(extendFields);
		}catch(Exception e){
			e.printStackTrace();
			return resp1.systemError();
		}
	}

}
