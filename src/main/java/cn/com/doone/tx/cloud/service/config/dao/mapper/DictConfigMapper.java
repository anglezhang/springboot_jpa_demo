package cn.com.doone.tx.cloud.service.config.dao.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.com.doone.tx.cloud.service.config.dao.provider.DictProvider;
import cn.com.doone.tx.cloud.service.config.evt.dict.DeleteDictEvt;
import cn.com.doone.tx.cloud.service.config.evt.dict.EditDictEvt;
import cn.com.doone.tx.cloud.service.config.evt.dict.EditDictTableEvt;
import cn.com.doone.tx.cloud.service.config.evt.dict.QueryCenterDatabaseEvt;
import cn.com.doone.tx.cloud.service.config.evt.dict.QueryDictConfigEvt;
import cn.com.doone.tx.cloud.service.config.evt.dict.QueryDictEvt;
import cn.com.doone.tx.cloud.service.config.info.CenterTableInfo;
import cn.com.doone.tx.cloud.service.config.info.DictInfo;

/**
 * Created by APPLE on 2017/1/19.
 */
@Mapper
public interface DictConfigMapper {

	// 查询字典表配置数量
	@SelectProvider(type = DictProvider.class, method = "queryDictCount")
	Integer queryDictCount(QueryDictEvt queryDictEvt);

	// 查询字典表配置列表
	@SelectProvider(type = DictProvider.class, method = "queryDictConfig")
	List<DictInfo> queryDictConfig(QueryDictConfigEvt queryDictConfigEvt);

	// 根据参数查询字典表配置
	@SelectProvider(type = DictProvider.class, method = "queryDictConfigByParam")
	List<DictInfo> queryDictConfigByParam(QueryDictEvt queryDictEvt);

	// 根据参数查询字典表数量
	@SelectProvider(type = DictProvider.class, method = "queryDictConfigCountByParam")
	Integer queryDictConfigCountByParam(QueryDictEvt queryDictEvt);

	// 编辑字典表值配置
	@Update("UPDATE td_dict set column_remark=#{columnRemark},dict_value=#{dictValue},value_remark=#{valueRemark},extend_remark=#{extendRemark},language_Type=#{languageTypeAdd},update_time=#{updateTime} where id=#{dictId}")
	Integer doEdit(EditDictEvt editDictEvt);

	// 编辑字典表表名字段配置
	@Update("UPDATE td_dict set column_remark=#{columnRemark},table_name=#{tableName},column_name=#{columnName},update_time=#{updateTime},center_database=#{centerDatabase} where table_name=#{oldTableName} and column_name=#{oldColumnName}")
	Integer doEditTable(EditDictTableEvt editDictTableEvt);

	// 删除字典表配置
	@UpdateProvider(type = DictProvider.class, method = "doDeleteByParam")
	Integer doDeleteByParam(DeleteDictEvt deleteDictEvt);

	// 根据中心名称查询表名
	@SelectProvider(type = DictProvider.class, method = "getCenterTableName")
	List<CenterTableInfo> getCenterTableName(QueryCenterDatabaseEvt queryCenterDatabaseEvt);

	// 根据中心名称查询字段名
	@SelectProvider(type = DictProvider.class, method = "getCenterColumnName")
	List<CenterTableInfo> getCenterColumnName(QueryCenterDatabaseEvt queryCenterDatabaseEvt);

}
