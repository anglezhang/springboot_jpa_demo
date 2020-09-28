package cn.com.doone.tx.cloud.service.config.service;

import cn.com.doone.tx.cloud.tool.common.invoke.BaseService;
import cn.com.doone.tx.cloud.tool.common.invoke.RestUtil;
import cn.com.doone.tx.cloud.tool.common.invoke.ServerResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 获取已保存的扩展信息 Created by YeCongZhi on 2017/4/26.
 */
@Service("getExtendInfoService")
@Transactional
public class GetExtendInfoService extends BaseService {

	@Autowired
	private RestUtil serviceRestUtil;

	public ServerResp<Object> getExtendInfo(String serviceUrl, Map<String, Object> param) {
		String service = serviceUrl.substring(0, serviceUrl.indexOf("/"));
		String url = serviceUrl.substring(serviceUrl.indexOf("/"), serviceUrl.length());
		System.out.println(">>>>>>获取已保存的扩展信息：<<<<<<< " + service + ":" + url);
		return serviceRestUtil.post(service, url, param);
	}
}
