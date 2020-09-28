package cn.com.doone.tx.cloud.service.config.utils;



import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.LinkedMap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wanggl
 * @version V1.0
 * @Title: TreeUtils
 * @Package com.doone.cloud.config.service.config.utils
 * @Description: (将纵向结构的树封装成二维结构)
 * @date 2016/8/26 11:17
 */
public class TreeUtils {

    /**
     *  包装成树形结构 (全部属性)
     * @param tree
     * @return
     * @throws Exception
     */
    public static List factorTree(List tree,String _fieldId,
                                  String _fieldParentId,String _fieldChildren,Long rootId) throws Exception{
        if(tree != null) {
            List t_list = new ArrayList();
            Map map =new LinkedMap();
            for(Object o:tree) {
                Class clazz = o.getClass();
                Field id = clazz.getDeclaredField(_fieldId);
                if(!id.isAccessible())  {
                    id.setAccessible(true);
                }
                Long lId = (Long)id.get(o);
                map.put(lId,o);
            }

            for(Object o:map.keySet()) {
                Long cId = (Long)o;
                Object obj = map.get(cId);
                Class clazz = obj.getClass();
                Field pId = clazz.getDeclaredField(_fieldParentId);
                if(!pId.isAccessible())  {
                    pId.setAccessible(true);
                }
                Long id = pId.get(obj)!=null?Long.parseLong(pId.get(obj).toString()):null;
                if(id==null||id.intValue()==rootId.intValue()) {
                    t_list.add(obj);
                } else {
                    Object object = map.get(id);
                    if(object==null){
                        continue;
                    }
                    Class clazz1 = object.getClass();
                    Field children = clazz1.getDeclaredField(_fieldChildren);
                    if(!children.isAccessible())  {
                        children.setAccessible(true);
                    }
                    List list = (List)children.get(object);
                    if(CollectionUtils.isEmpty(list)){
                        list=new ArrayList();
                    }
                    list.add(obj);
                    children.set(object,list);
                }
            }
            return t_list;
        }
        return null;
    }
}
