package cn.com.doone.tx.cloud.service.user.service;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.doone.tx.cloud.service.user.bean.CustomGroupExtendInfoBean;
import cn.com.doone.tx.cloud.service.user.bean.GroupRoleBean;
import cn.com.doone.tx.cloud.service.user.bean.NationStateBean;
import cn.com.doone.tx.cloud.service.user.bean.StaffGroupBean;
import cn.com.doone.tx.cloud.service.user.bean.StaffGroupExtendInfoBean;
import cn.com.doone.tx.cloud.service.user.dao.mapper.CustomGroupExtendInfoMapper;
import cn.com.doone.tx.cloud.service.user.dao.mapper.RoleGroupMapper;
import cn.com.doone.tx.cloud.service.user.dao.mapper.StaffGroupExtendInfoMapper;
import cn.com.doone.tx.cloud.service.user.dao.mapper.StaffGroupMapper;
import cn.com.doone.tx.cloud.service.user.evt.common.QueryAreaEvt;
import cn.com.doone.tx.cloud.service.user.evt.common.QueryCityEvt;
import cn.com.doone.tx.cloud.service.user.evt.common.QueryProvinceEvt;
import cn.com.doone.tx.cloud.service.user.evt.groupRole.AddGroupRoleEvt;
import cn.com.doone.tx.cloud.service.user.evt.region.QueryRegionEvt;
import cn.com.doone.tx.cloud.service.user.evt.roleGroup.DeleteRoleGroupEvt;
import cn.com.doone.tx.cloud.service.user.evt.staffGroup.AddStaffGroupEvt;
import cn.com.doone.tx.cloud.service.user.evt.staffGroup.DeleteStaffGroupEvt;
import cn.com.doone.tx.cloud.service.user.evt.staffGroup.DeleteStaffGroupExtendEvt;
import cn.com.doone.tx.cloud.service.user.evt.staffGroup.EditStaffGroupEvt;
import cn.com.doone.tx.cloud.service.user.evt.staffGroup.QueryStaffGroupEvt;
import cn.com.doone.tx.cloud.service.user.evt.templatetheme.BindStaffGroupThemeEvt;
import cn.com.doone.tx.cloud.service.user.info.AreaBean;
import cn.com.doone.tx.cloud.service.user.info.CityBean;
import cn.com.doone.tx.cloud.service.user.info.ProvinceBean;
import cn.com.doone.tx.cloud.service.user.info.RegionBean;
import cn.com.doone.tx.cloud.service.user.info.RoleInfo;
import cn.com.doone.tx.cloud.service.user.info.StaffGroupInfo;
import cn.com.doone.tx.cloud.tool.common.enums.NormalStatus;
import cn.com.doone.tx.cloud.tool.common.enums.YesOrNo;
import cn.com.doone.tx.cloud.tool.common.util.CapsLookUtil;
import cn.com.doone.tx.cloud.tool.hibernate.repository.PlatRepository;

/**
 * Created by liujx on 2017/2/13 0013.
 */
@Service
//@Transactional
public class StaffGroupService {
    @Autowired
    private StaffGroupMapper staffGroupMapper;
    
//    @Autowired
//    private CustomGroupMapper customGroupMapper;

    @Autowired
    private RoleGroupMapper roleGroupMapper;

    @Autowired
    private PlatRepository platRepository;

//    @Autowired
//    private ExtendInfoMapper extendInfoMapper;

    @Autowired
    private StaffGroupExtendInfoMapper staffGroupExtendInfoMapper;
    @Autowired
    private CustomGroupExtendInfoMapper customGroupExtendInfoMapper;

    //@Cacheable(value = "StaffGroupService.queryGroupCount",keyGenerator = "defaultKeyGenerator")
    public int queryCount(QueryStaffGroupEvt evt){
        return staffGroupMapper.queryCount(evt);
    }

    //@Cacheable(value = "StaffGroupService.queryGroupByParam",keyGenerator = "defaultKeyGenerator")
    public List<StaffGroupInfo> queryByParam(QueryStaffGroupEvt evt){
        List<StaffGroupInfo> staffGroupInfos = staffGroupMapper.queryByParam(evt);
        //查询出所在地区信息
        for (StaffGroupInfo info:
                staffGroupInfos) {
            if(StringUtils.isNotBlank(info.getProvinceCode())) {
                ProvinceBean provinceBean = staffGroupMapper.queryProvineByCode(info.getProvinceCode());
                info.setProvinceName(provinceBean.getProvinceName());
            }
            if(StringUtils.isNotBlank(info.getCityCode())){
                CityBean cityBean =  staffGroupMapper.queryCityByCode(info.getCityCode());
                info.setCityName(cityBean.getCityName());
            }
            if (StringUtils.isNotBlank(info.getAreaCode())) {
                AreaBean areaBean = staffGroupMapper.queryAreaByCode(info.getAreaCode());
                info.setAreaName(areaBean.getAreaName());
            }
         /*   if(info.getCreateTime()!=null) {
            	
            	Date date = info.getCreateTime();
            	System.out.println(date.getTime());
            	System.out.println(String.valueOf(date));
            	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        		String dateString = sdf.format(date);
        		System.out.println(dateString);
            	
            }*/
        }
        return staffGroupInfos;
    }

    public static void main(String[] args) {
		long timesLong = 1533365347000L;
		
		//将Long类型转化为Date
		Date date = new Date(timesLong);
		//将Date类型格式化
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = sdf.format(date);
		System.out.println(dateString);
	}
    /**新增用户组 1、普通用户组 2、自定义用户组*/
    public StaffGroupBean doAdd(AddStaffGroupEvt evt){
        /** 查询当前目录底下的用户组数 */
        QueryStaffGroupEvt queryStaffGroupEvt = new QueryStaffGroupEvt();
        queryStaffGroupEvt.setParentId(evt.getParentId());
        int cnt  = queryCount(queryStaffGroupEvt);
        evt.setSort(++cnt);
        evt.setCreateTime(new Date());
        evt.setUpdateTime(new Date());
        StaffGroupBean bean = new StaffGroupBean();//1、普通用户组
        BeanUtils.copyProperties(evt,bean);
        bean = platRepository.save(bean);
//        Long groupId = bean.getId();
//        Long operator = evt.getOperator();
        /**扩展用户组须入自定义用户组表*/
//        if(evt.getGroupType()==2) {
//            AddCustomGroupEvt addCustomGroupEvt = new AddCustomGroupEvt();
//            addCustomGroupEvt.setUpdateTime(new Date());
//            addCustomGroupEvt.setCreateTime(new Date());
//            addCustomGroupEvt.setStatus("E");
//            addCustomGroupEvt.setGroupId(bean.getId());
//            addCustomGroupEvt.setGroupName(bean.getGroupName());
//            CustomGroupBean customGroupBean = new CustomGroupBean();
//            BeanUtils.copyProperties(addCustomGroupEvt, customGroupBean);
//            platRepository.save(customGroupBean);
//            //插入自定义用户组扩展表
//            Map<String,String> extendMap = evt.getExtendParam();
//            if(extendMap!=null) {
//                saveCustomGroupExtends(extendMap,groupId,operator);
//            }
//        }else {
//            /**入用户组扩展信息*/
//            Map<String,String> extendMap = evt.getExtendParam();
//            if(extendMap!=null) {
//                saveStaffGroupExtends(extendMap,groupId,operator);
//            }
//        }
        return bean;
    }
    /**编辑用户组 1、普通用户组 2、自定义用户组*/
    public int doEdit(EditStaffGroupEvt evt){
        evt.setGroupId(evt.getId());
        evt.setUpdateTime(new Date());
        int n = staffGroupMapper.doEdit(evt);

        //自定义用户组须修改自定义用户组表
        staffGroupMapper.doEditCustomGroup(evt);
        //修改用户组扩展表
        Long groupId = evt.getId();
        Long operator = evt.getOperator();
        if(evt.getExtendParam()!=null) {
//            evt.getExtendParam().put("baseId", evt.getId().toString());
//            evt.getExtendParam().put("operator", evt.getOperator().toString());
            //
            if("2".equals(evt.getGroupType())){
//                evt.getExtendParam().put("tableName", "ts_custom_group_extend_info");
//                evt.getExtendParam().put("baseName", "custom_group");
//                extendInfoMapper.edit(evt.getExtendParam());
                /**扩展用户组扩展信息*/
                Map<String,String> extendMap = evt.getExtendParam();
                if(extendMap!=null) {
                    /** 先删除 扩展用户组扩展表状态置D*/
                    DeleteStaffGroupExtendEvt deleteStaffGroupExtendEvt = new DeleteStaffGroupExtendEvt();
                    deleteStaffGroupExtendEvt.setGroupId(groupId);
                    int cnt = customGroupExtendInfoMapper.doDeleteFromExtend(deleteStaffGroupExtendEvt);
                    /**保存*/
                    saveCustomGroupExtends(extendMap,groupId,operator);
                }
            }else {
//                evt.getExtendParam().put("tableName", "ts_staff_group_extend_info");
//                evt.getExtendParam().put("baseName", "group");
                /**用户组扩展信息*/
                Map<String,String> extendMap = evt.getExtendParam();
                if(extendMap!=null) {
                    /** 先删除 用户组扩展表状态置D*/
                    DeleteStaffGroupExtendEvt deleteStaffGroupExtendEvt = new DeleteStaffGroupExtendEvt();
                    deleteStaffGroupExtendEvt.setGroupId(groupId);
                    int cnt = staffGroupExtendInfoMapper.doDeleteFromExtend(deleteStaffGroupExtendEvt);
                    /**保存*/
                    saveStaffGroupExtends(extendMap,groupId,operator);
                }
            }
        }
        return n;
    }
    /***保存用户组扩展信息*/
    private  Long saveStaffGroupExtends(Map<String,String> extendMap,Long groupId,Long operator){
        StaffGroupExtendInfoBean staffGroupExtendInfoBean = new StaffGroupExtendInfoBean();
        staffGroupExtendInfoBean.setGroupId(groupId);//用户组id
        staffGroupExtendInfoBean.setUpdateTime(new Date());
        staffGroupExtendInfoBean.setCreateTime(new Date());
        staffGroupExtendInfoBean.setOperator(operator);
        staffGroupExtendInfoBean.setStatus(NormalStatus.E.name());
        Class<?> beanClass= StaffGroupExtendInfoBean.class;
        for (String fieldName:extendMap.keySet()){
            String value = extendMap.get(fieldName);
            fieldName = StringUtils.replace(fieldName,"_","");
            fieldName = CapsLookUtil.toUpperCaseFirstOne(fieldName.toLowerCase());
            try {
                Method method = beanClass.getMethod("set" + fieldName,String.class);
                method.invoke(staffGroupExtendInfoBean,value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        StaffGroupExtendInfoBean staffGroupExtendInfoBean1 = platRepository.save(staffGroupExtendInfoBean);
        return staffGroupExtendInfoBean1.getId();
    }
    /***保存扩展用户组扩展信息*/
    private  Long saveCustomGroupExtends(Map<String,String> extendMap,Long groupId,Long operator){
        CustomGroupExtendInfoBean customGroupExtendInfoBean = new CustomGroupExtendInfoBean();
        customGroupExtendInfoBean.setCustomGroupId(groupId);//用户组id
        customGroupExtendInfoBean.setUpdateTime(new Date());
        customGroupExtendInfoBean.setCreateTime(new Date());
        customGroupExtendInfoBean.setOperator(operator);
        customGroupExtendInfoBean.setStatus(NormalStatus.E.name());
        Class<?> beanClass= CustomGroupExtendInfoBean.class;
        for (String fieldName:extendMap.keySet()){
            String value = extendMap.get(fieldName);
            fieldName = StringUtils.replace(fieldName,"_","");
            fieldName = CapsLookUtil.toUpperCaseFirstOne(fieldName.toLowerCase());
            try {
                Method method = beanClass.getMethod("set" + fieldName,String.class);
                method.invoke(customGroupExtendInfoBean,value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        CustomGroupExtendInfoBean customGroupExtendInfoBean1 = platRepository.save(customGroupExtendInfoBean);
        return customGroupExtendInfoBean1.getId();
    }
    /*******************************************************************************/
    public int doDelete(DeleteStaffGroupEvt evt){
        staffGroupMapper.doDeleteCustomGroup(evt);
        staffGroupMapper.deleteGroupRole(evt);
        //删除tr_data_role中 角色与用户组的关系
        DeleteRoleGroupEvt deleteRoleGroupEvt = new DeleteRoleGroupEvt();
//        System.out.println("groupId:"+evt.getId());
        deleteRoleGroupEvt.setGroupId(evt.getId());
        roleGroupMapper.doDeleteDateRoleByGroupId(deleteRoleGroupEvt);
        staffGroupMapper.deleteGroupStaff(evt);

//        //删除用户组扩展表
//        Map<String,String> deExtendMap = new HashMap<>();
//        deExtendMap.put("baseId", evt.getId().toString());
//        deExtendMap.put("tableName", "ts_custom_group_extend_info");
//        deExtendMap.put("baseName", "custom_group");
//        extendInfoMapper.delete(deExtendMap);
//
//        deExtendMap.put("tableName", "ts_staff_group_extend_info");
//        deExtendMap.put("baseName", "group");
//        extendInfoMapper.delete(deExtendMap);
        //删除用户组(修改状态为D)
        evt.setStatus(NormalStatus.D.name());
        int n = staffGroupMapper.doDelete(evt);
        return n;
    }

    public int updateStatus(DeleteStaffGroupEvt evt){
        return staffGroupMapper.updateStatus(evt);
    }

    /**
     * 用户组树
     * 1、超级管理员用户组树展示所有用户组
     * */
    public List<StaffGroupInfo> queryAllGroup(QueryStaffGroupEvt evt){
        return staffGroupMapper.queryAllGroup(evt);
    }
    /**
     * 2、普通用户展示其所在用户组及下级
     * 根据用户id 查出其所在用户组，其所在用户组即为根用户组，再查出此用户组下所有下属用户组
     * */
    public List<StaffGroupInfo> userGroupTree(QueryStaffGroupEvt evt){
        //先查出当前账号所属用户组，其用户组id作为parentId再递归查询
        Long groupId = staffGroupMapper.queryGroupIdByStaff(evt);
        evt.setParentId(groupId);
        List<StaffGroupInfo> list = new ArrayList<>();
        list = setStaffGroupInfoTree(list,evt);
        return list;
    }
   //根据parentId做递归查询
    public List<StaffGroupInfo> setStaffGroupInfoTree(List<StaffGroupInfo> list,QueryStaffGroupEvt evt){
        //每次都根据parentId查询出对应的list
        List<StaffGroupInfo> staffGroupInfoList = queryStaffGroupInfoByParent(evt);
        if (staffGroupInfoList!=null){
            if (staffGroupInfoList.size()>0){
                list.addAll(staffGroupInfoList);
                //遍历所查出的元素，将其id作为parentId继续查询
                for (StaffGroupInfo staffGroupInfo:staffGroupInfoList){
                    QueryStaffGroupEvt evt1 = new QueryStaffGroupEvt();
                    evt1.setParentId(staffGroupInfo.getId());
                    setStaffGroupInfoTree(list,evt1);
                }
            }
        }
        return list;
    }

	public List<StaffGroupInfo> queryStaffGroupInfoByParent(
			QueryStaffGroupEvt evt) {
		return staffGroupMapper.queryStaffGroupInfoByParent(evt);
	}
    //--------------------------------------------------------
    /**
     * 角色分配-数据权限的用户组树
     */
    public List<StaffGroupInfo> groupTreeForData(QueryStaffGroupEvt evt){
        List<StaffGroupInfo> list;
        if (YesOrNo.Y.name().equals(evt.getIsSuperManager())){
            /**1、超级管理员查询所有用户组*/
            list = staffGroupMapper.groupTreeForData(evt);
        }else {
            /**2、普通用户查询其所有角色拥有的数据权限(用户组集合)*/
            list = staffGroupMapper.queryDataAuthsInfoByStaff(evt);
        }
        return list;
    }

    public List<RoleInfo> queryRoleByGroup(Long groupId){
        return staffGroupMapper.queryRoleByGroup(groupId);
    }

    public GroupRoleBean setGroupRole(AddGroupRoleEvt evt){
        GroupRoleBean bean = new GroupRoleBean();
        BeanUtils.copyProperties(evt,bean);
        bean = platRepository.save(bean);
        return bean;
    }
    public int deleteGroupRole(DeleteStaffGroupEvt evt){
        return staffGroupMapper.deleteGroupRole(evt);
    }

    public Map<String,Object> queryExtendInfo(Map<String,String> params){
//        Map<String,Object> extendInfo= extendInfoMapper.queryExtendInfo(params);
//        return extendInfo;
    	return null;
    }
    
    /**新增扩展用户组*/
//    public CustomGroupBean doAddExtends(AddCustomGroupEvt evt){
//    	/** 查询当前目录底下的用户组数 */
//    	AddStaffGroupEvt addStaffGroupEvt = new AddStaffGroupEvt();
//        QueryStaffGroupEvt queryStaffGroupEvt = new QueryStaffGroupEvt();
//        queryStaffGroupEvt.setParentId(evt.getStaffGroupParentId());
//        int cnt  = queryCount(queryStaffGroupEvt);
//        addStaffGroupEvt.setParentId(evt.getStaffGroupParentId());
//        addStaffGroupEvt.setGroupType(2);
//        addStaffGroupEvt.setGroupName(evt.getGroupName());
//        addStaffGroupEvt.setSort(++cnt);
//        addStaffGroupEvt.setCreateTime(new Date());
//        addStaffGroupEvt.setUpdateTime(new Date());
//        addStaffGroupEvt.setOperator(evt.getOperator());
//        addStaffGroupEvt.setCreator(evt.getOperator());
//        addStaffGroupEvt.setStatus(NormalStatus.E.name());        
//        StaffGroupBean bean = new StaffGroupBean();
//        BeanUtils.copyProperties(addStaffGroupEvt,bean);
//        bean = platRepository.save(bean);
//        //自定义用户组须入自定义用户组表
//        evt.setUpdateTime(new Date());
//        evt.setCreateTime(new Date());
//        evt.setGroupId(bean.getId());
//        CustomGroupBean customGroupBean = new CustomGroupBean();
//        BeanUtils.copyProperties(evt, customGroupBean);
//        platRepository.save(customGroupBean);
//        //插入自定义用户组扩展表
//        if(evt.getExtendParam()!=null) {
//            evt.getExtendParam().put("baseId", bean.getId().toString());
//            evt.getExtendParam().put("tableName", "ts_custom_group_extend_info");
//            evt.getExtendParam().put("baseName", "custom_group");
//            evt.getExtendParam().put("operator", evt.getOperator().toString());
//            extendInfoMapper.add(evt.getExtendParam());
//        }
//        return customGroupBean;
//    }
    
    /**编辑自定义用户组*/
//    public CustomGroupBean doEditExtends(EditCustomGroupEvt evt){
//    	CustomGroupBean customGroupBean = new CustomGroupBean();
//    	//自定义用户组须修改自定义用户组表
//        QueryCustomGroupEvt queryCustomGroupEvt = new QueryCustomGroupEvt();
//        queryCustomGroupEvt.setId(evt.getId());
//        List<CustomGroupBean> customGroupInfos = customGroupMapper.queryCustomGroupByParam("", queryCustomGroupEvt);
//        if(customGroupInfos.size()>0){
//        	CustomGroupBean customGroupInfo = customGroupInfos.get(0);
//    		EditStaffGroupEvt editStaffGroupEvt = new EditStaffGroupEvt();
//        	editStaffGroupEvt.setId(customGroupInfo.getGroupId());
//        	editStaffGroupEvt.setUpdateTime(new Date());
//        	editStaffGroupEvt.setGroupName(evt.getGroupName());
//        	editStaffGroupEvt.setOperator(evt.getOperator());
//            staffGroupMapper.doEdit(editStaffGroupEvt);
//            
//            BeanUtils.copyProperties(evt,customGroupBean);
//            customGroupBean.setCreateTime(customGroupInfo.getCreateTime());
//            customGroupBean.setStatus(customGroupInfo.getStatus());
//            customGroupBean.setGroupId(customGroupInfo.getGroupId());
//            customGroupBean.setUpdateTime(new Date());
//            customGroupBean = platRepository.save(customGroupBean);
//            //修改用户组扩展表
//            if(evt.getExtendParam()!=null) {
//                evt.getExtendParam().put("baseId", customGroupBean.getGroupId().toString());
//                evt.getExtendParam().put("operator", evt.getOperator().toString());
//                evt.getExtendParam().put("tableName", "ts_custom_group_extend_info");
//                evt.getExtendParam().put("baseName", "custom_group");
//                extendInfoMapper.edit(evt.getExtendParam());
//            }
//
//        }
//    	
//        return customGroupBean;
//    }

	//主题授权
	public int doBindStaffGroupTheme(BindStaffGroupThemeEvt evt) {
		return staffGroupMapper.doBindStaffGroupTheme(evt);
	}

    /**---------------大区域 -省 -城市 -区县--------------------------------------------------------------**/
    /**查询所有大区域信息*/
    public  List<RegionBean> queryAllRegion(){
        return staffGroupMapper.queryAllRegion();
    }
    /**查询大区域下属的省份信息*/
    public  List<ProvinceBean> queryProvinceByRCode(QueryRegionEvt queryRegionEvt){
        return staffGroupMapper.queryProvinceByRCode(queryRegionEvt);
    }
    /**查询所有省份信息*/
    @Cacheable(value = "StaffGroupService.queryAllProvince",keyGenerator = "defaultKeyGenerator")
    public  List<ProvinceBean> queryAllProvince(){
        return staffGroupMapper.queryAllProvince();
    }
    /**查询省份下属的城市信息*/
    @Cacheable(value = "StaffGroupService.queryCityByPCode",keyGenerator = "defaultKeyGenerator")
    public List<CityBean> queryCityByPCode(QueryCityEvt evt){
        return staffGroupMapper.queryCityByPCode(evt);
    }
    /**查询城市下属区域信息*/
    @Cacheable(value = "StaffGroupService.queryAreaByCCode",keyGenerator = "defaultKeyGenerator")
    public List<AreaBean> queryAreaByCCode(QueryAreaEvt evt){
        return staffGroupMapper.queryAreaByCCode(evt);
    }

    /**查询单个省份信息code*/
    public ProvinceBean queryProvinceByCode(String provinceCode){
        return staffGroupMapper.queryProvineByCode(provinceCode);
    }
    /**查询单个城市信息code*/
    public CityBean queryCityByCode(String cityCode){
        return staffGroupMapper.queryCityByCode(cityCode);
    }
    /**查询单个区域信息code*/
    public AreaBean queryAreaByCode(String areaCode){
        return staffGroupMapper.queryAreaByCode(areaCode);
    }

    /**查询单个省份信息Name*/
    public List<ProvinceBean> queryProvinceByName(String provinceName){
        return staffGroupMapper.queryProvineByName(provinceName);
    }
    /**查询单个城市信息Name*/
    public List<CityBean> queryCityByName(String cityName){
        return staffGroupMapper.queryCityByName(cityName);
    }
    /**查询单个区域信息Name*/
    public List<AreaBean> queryAreaByName(String areaName){
        return staffGroupMapper.queryAreaByName(areaName);
    }

    public JSONArray queryAddressByParam(){
    	JSONArray array=new JSONArray();
    	List<ProvinceBean> provinces=queryAllProvince();
    	if(provinces!=null&&provinces.size()>0){
    		for(ProvinceBean p:provinces){
    			if(p.getProvinceCode().equals("0000")){
    				continue;
    			}
    			JSONObject obj=new JSONObject();
				obj.put("value", p.getProvinceCode());
				obj.put("text", p.getProvinceName());
				
				QueryCityEvt cEvt=new QueryCityEvt();
				cEvt.setProvinceCode(p.getProvinceCode());
				List<CityBean> citys=queryCityByPCode(cEvt);
				JSONArray cityArray=new JSONArray();
				if(citys!=null&&citys.size()>0){
					for(CityBean c:citys){
						JSONObject cObj=new JSONObject();
						cObj.put("value", c.getCityCode());
						cObj.put("text", c.getCityName());
						
						
						QueryAreaEvt aEvt=new QueryAreaEvt();
						aEvt.setCityCode(c.getCityCode());
						List<AreaBean> areas=queryAreaByCCode(aEvt);
						JSONArray areaArray=new JSONArray();
						if(areas!=null&&areas.size()>0){
							for(AreaBean a:areas){
								JSONObject aObj=new JSONObject();
								aObj.put("value",a.getAreaCode());
								aObj.put("text", a.getAreaName());
								areaArray.add(aObj);
							}
							cObj.put("children", areaArray);	
						}
						cityArray.add(cObj);
					}
					obj.put("children", cityArray);
				}
				array.add(obj);
    		}
    	}
    	return array;
    }
    //-----------------------------------------------------------------------------------------------

    /**查询所有国家信息*/
    @Cacheable(value = "StaffGroupService.queryAllNation",keyGenerator = "defaultKeyGenerator")
    public  List<NationStateBean> queryAllNation(){
        return staffGroupMapper.queryAllNation();
    }

    /**查询某国家下属的省份信息*/
    @Cacheable(value = "StaffGroupService.queryProvinceByNCode",keyGenerator = "defaultKeyGenerator")
    public  List<ProvinceBean> queryProvinceByNCode(QueryProvinceEvt evt){
        return staffGroupMapper.queryProvinceByNCode(evt);
    }

    public int queryNameIsExist(QueryStaffGroupEvt evt){
        return staffGroupMapper.queryNameIsExist(evt);
    }

    public int queryGroupIsForbid(QueryStaffGroupEvt evt){
        return staffGroupMapper.queryGroupIsForbid(evt);
    }

}
