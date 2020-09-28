package cn.com.doone.tx.cloud.service.user.dao.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.com.doone.tx.cloud.service.user.bean.NationStateBean;
import cn.com.doone.tx.cloud.service.user.dao.provider.StaffGroupProvider;
import cn.com.doone.tx.cloud.service.user.evt.common.QueryAreaEvt;
import cn.com.doone.tx.cloud.service.user.evt.common.QueryCityEvt;
import cn.com.doone.tx.cloud.service.user.evt.common.QueryProvinceEvt;
import cn.com.doone.tx.cloud.service.user.evt.region.QueryRegionEvt;
import cn.com.doone.tx.cloud.service.user.evt.staffGroup.DeleteStaffGroupEvt;
import cn.com.doone.tx.cloud.service.user.evt.staffGroup.EditStaffGroupEvt;
import cn.com.doone.tx.cloud.service.user.evt.staffGroup.QueryStaffGroupEvt;
import cn.com.doone.tx.cloud.service.user.evt.templatetheme.BindStaffGroupThemeEvt;
import cn.com.doone.tx.cloud.service.user.info.AreaBean;
import cn.com.doone.tx.cloud.service.user.info.CityBean;
import cn.com.doone.tx.cloud.service.user.info.DataAuthsInfo;
import cn.com.doone.tx.cloud.service.user.info.ProvinceBean;
import cn.com.doone.tx.cloud.service.user.info.RegionBean;
import cn.com.doone.tx.cloud.service.user.info.RoleInfo;
import cn.com.doone.tx.cloud.service.user.info.StaffGroupInfo;

/**
 * Created by liujx on 2017/2/13 0013.
 */
@Mapper
public interface StaffGroupMapper {

    @SelectProvider(type = StaffGroupProvider.class , method = "queryCount")
    int queryCount(QueryStaffGroupEvt queryStaffGroupEvt);

    @SelectProvider(type = StaffGroupProvider.class , method = "queryByParam")
    List<StaffGroupInfo> queryByParam(QueryStaffGroupEvt queryStaffGroupEvt);

    /**超级管理员 的用户组树*/
    @SelectProvider(type = StaffGroupProvider.class , method = "queryAllGroup")
    List<StaffGroupInfo> queryAllGroup(QueryStaffGroupEvt queryStaffGroupEvt);

    /**查出当前用户的所在用户组id 作为parentId- 用于 用户管理-用户组树展示*/
    @Select("select group_id from tr_group_staff where staff_id= #{staffId}")
    Long queryGroupIdByStaff(QueryStaffGroupEvt evt);

    //递归查询parentId 下对应的用户组
    @SelectProvider(type = StaffGroupProvider.class , method = "queryStaffGroupInfoByParent")
    List<StaffGroupInfo> queryStaffGroupInfoByParent(QueryStaffGroupEvt evt);

    //递归查询parentId 下对应的用户组-用于用户数据权限获取服务
    @SelectProvider(type = StaffGroupProvider.class , method = "queryStaffGroupInfoByParent")
    List<DataAuthsInfo> queryDataAuthsInfoByParent(QueryStaffGroupEvt evt);

    //查询相同名称组织架构数量
    @SelectProvider(type = StaffGroupProvider.class , method = "queryNameIsExist")
    int queryNameIsExist(QueryStaffGroupEvt evt);

    //查询组织架构是否被禁用
    @SelectProvider(type = StaffGroupProvider.class , method = "queryGroupIsForbid")
    int queryGroupIsForbid(QueryStaffGroupEvt evt);

    //------------------------------------------------------------------
    /**1、数据权限-超级管理员展示所有的用户组*/
    @SelectProvider(type = StaffGroupProvider.class , method = "groupTreeForData")
    List<StaffGroupInfo> groupTreeForData(QueryStaffGroupEvt evt);
    /**2、数据权限-普通用户查询其所有角色拥有的(用户组集合)*/
    /**
     * 查出用户角所绑定的角色 及用户所在用户组的角色
     * 上述结果作为条件查出角色绑定的用户组信息
     * */
    @Select(
            "select distinct t5.id,t5.parent_id,t5.group_name from ts_staff_group t5 where t5.id in"+
                    "("+
                    "select t6.group_id from tr_data_role t6 where t6.role_id in"+
                    "("+
                    "SELECT distinct t1.ID" +
                    " from ts_role_info t1 where t1.id in ("+
                    " select T1.role_id from (select role_id from tr_staff_role t3 where t3.staff_id=#{staffId} and t3.STATUS ='E') T1 "+
                    " union select T2.role_id from (select role_id from tr_group_role t4 left join tr_group_staff t5 " +
                    " on t4.GROUP_ID = t5.GROUP_ID where t5.staff_id=#{staffId} AND t4.STATUS ='E' AND t5.STATUS ='E') T2)"+
                    ")"+
                    ") AND t5.ID>0"
    )
    List<StaffGroupInfo> queryDataAuthsInfoByStaff(QueryStaffGroupEvt evt);

    //------------------------------------------------------------------
    @UpdateProvider(type = StaffGroupProvider.class , method = "doEdit")
    int doEdit(EditStaffGroupEvt evt);

    @UpdateProvider(type = StaffGroupProvider.class , method = "doEditCustomGroup")
    int doEditCustomGroup(EditStaffGroupEvt evt);

//    @Delete("delete from ts_staff_group where ID = #{id} OR PARENT_ID = #{id}")
    /**删除 修改状态为D*/
    @Update("update ts_staff_group set status=#{status} where id=#{id} OR PARENT_ID = #{id}")
    int doDelete(DeleteStaffGroupEvt evt);

    /*启用禁用*/
    @Update("update ts_staff_group set status=#{status} where id=#{id}")
    int updateStatus(DeleteStaffGroupEvt evt);

    @Delete("delete from tf_custom_group where GROUP_ID = #{id}")
    int doDeleteCustomGroup(DeleteStaffGroupEvt evt);

    @Delete("delete from tr_group_staff where GROUP_ID = #{id}")
    int deleteGroupStaff(DeleteStaffGroupEvt evt);

    @Select("SELECT AREA_CODE areaCode,AREA_NAME areaName,BSS_AREA_ID BSSAreaId,IS_BUSI ifBusi FROM td_area WHERE STATUS = 'E' AND CITY_CODE = #{cityCode}")
    List<AreaBean> queryAreaByCCode(QueryAreaEvt evt);

    @SelectProvider(type = StaffGroupProvider.class , method = "queryRoleByGroup")
    List<RoleInfo> queryRoleByGroup(Long groupId);

    @Delete("delete from tr_group_role where group_id = #{id}")
    int deleteGroupRole(DeleteStaffGroupEvt evt);
    
	// 主题授权
	@Update("update ts_staff_group set TEMPLATE_THEME_ID=#{selTemplateThemeId} where id=#{staffGroupId}")
	public int doBindStaffGroupTheme(BindStaffGroupThemeEvt evt);

    /**---------------大区域 -省 -城市 -区县--------------------------------------------------------------**/
    @Select("SELECT REGION_CODE,REGION_NAME,REGION_SHORT FROM td_region WHERE STATUS = 'E' ORDER BY SORT ASC")
    List<RegionBean> queryAllRegion();
    @Select("SELECT PROVINCE_CODE provinceCode,PROVINCE_SHORT provinceShort,PROVINCE_NAME provinceName,DSTORGID FROM td_province WHERE STATUS = 'E' AND REGION_CODE = #{regionCode}")
    List<ProvinceBean> queryProvinceByRCode(QueryRegionEvt evt);
    @Select("SELECT PROVINCE_CODE provinceCode,PROVINCE_SHORT provinceShort,PROVINCE_NAME provinceName,DSTORGID FROM td_province WHERE STATUS = 'E' ORDER BY SORT ASC")
    List<ProvinceBean> queryAllProvince();
    @Select("SELECT CITY_CODE cityCode,CITY_NAME cityName,CITY_SHORT_NAME cityShortName,CITY_CODE_EXT cityCodeExt,CITY_REMARK cityRemark FROM td_city WHERE STATUS = 'E' AND PROVINCE_CODE = #{provinceCode}")
    List<CityBean> queryCityByPCode(QueryCityEvt evt);
    @Select("SELECT PROVINCE_NAME provinceName FROM td_province WHERE PROVINCE_CODE = #{provinceCode} AND STATUS = 'E'")

    ProvinceBean queryProvineByCode(String provinceCode);
    @Select("SELECT AREA_NAME areaName FROM td_area WHERE AREA_CODE = #{areaCode} AND STATUS = 'E'")
    AreaBean queryAreaByCode(String areaCode);
    @Select("SELECT CITY_NAME cityName FROM td_city WHERE CITY_CODE = #{cityCode} AND STATUS = 'E'")
    CityBean queryCityByCode(String cityCode);

    @Select("SELECT PROVINCE_CODE provinceCode FROM td_province WHERE PROVINCE_Name = #{provinceName} AND STATUS = 'E'")
    List<ProvinceBean> queryProvineByName(String provinceName);
    @Select("SELECT AREA_CODE areaCode FROM td_area WHERE AREA_NAME = #{areaName} AND STATUS = 'E'")
    List<AreaBean> queryAreaByName(String areaName);
    @Select("SELECT CITY_CODE cityCode FROM td_city WHERE CITY_NAME = #{cityName} AND STATUS = 'E'")
    List<CityBean> queryCityByName(String cityName);

    @Select("SELECT NATION_CODE nationCode,NATION_SHORT nationShort,NATION_NAME nationName FROM td_nation_state WHERE STATUS = 'E' ORDER BY SORT ASC")
    List<NationStateBean> queryAllNation();

    @Select("SELECT PROVINCE_CODE provinceCode,PROVINCE_SHORT provinceShort,PROVINCE_NAME provinceName,DSTORGID FROM td_province WHERE STATUS = 'E' AND NATION_CODE = #{nationCode}")
    List<ProvinceBean> queryProvinceByNCode(QueryProvinceEvt evt);

}
