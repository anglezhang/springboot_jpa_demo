package cn.com.doone.tx.cloud.service.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.doone.tx.cloud.service.user.bean.OperateLogBean;
import cn.com.doone.tx.cloud.service.user.dao.mapper.OperateLogMapper;
import cn.com.doone.tx.cloud.service.user.evt.operateLog.QueryOperateLogEvt;
import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatRepository;

@Service
@Transactional
public class OperateLogService {

	@Autowired
	private PlatRepository platRepository;
	@Autowired
	private OperateLogMapper operateLogMapper;

	/**
	 * 日志入库
	 * 
	 * @param evt
	 * @return
	 */
	public OperateLogBean addOperateLog(OperateLogBean operateLogBean) {
		return platRepository.save(operateLogBean);
	}

	/**
	 * 查询日志条数
	 * 
	 * @param evt
	 * @return
	 */
	public int queryCount(QueryOperateLogEvt evt) {
		return operateLogMapper.queryCount(evt);
	}

	/**
	 * 查询日志
	 * 
	 * @param evt
	 * @return
	 */
	public List<Map<String, Object>> queryByParam(QueryOperateLogEvt evt) {
		evt.startPage();
		return operateLogMapper.queryByParam(evt);
	}
}
