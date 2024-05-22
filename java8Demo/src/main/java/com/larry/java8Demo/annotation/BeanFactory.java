package com.larry.java8Demo.annotation;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanFactory {
    public static Map<String, Object> beans = new HashMap<>();
    private String scanPackage = "com.larry.java8Demo.annotation.entity";
    private static String END_SUFFIX = "_SUFFIX_CLS";

    public void init() throws Exception {
        Set<Class<?>> tempSet = AnnotationClassUtils.loadAnnotatedClasses(scanPackage);
        for (Class<?> cls : tempSet) {
            Entity entity = cls.getAnnotation(Entity.class);
            if(entity == null)
                continue;
            String value = entity.value();
            boolean lazyLoad = entity.isLazyLoad();
            if(value == null || "".equals(value))
                value=cls.getName();
            if(lazyLoad)
                beans.put(value+END_SUFFIX, cls);
            else
                beans.put(value, newInstance(cls));
        }
    }
    public Object getBean(String name) throws Exception {
        if(beans.containsKey(name)){
            return beans.get(name);
        }else if(beans.containsKey(name+END_SUFFIX)){
            Class clazz = (Class) beans.get(name+END_SUFFIX);
            Object obj = newInstance(clazz);
            beans.remove(name+END_SUFFIX);
            beans.put(name, obj);
            return obj;
        }
        return null;
    }
    private Object newInstance(Class clazz) throws Exception {
        Object obj = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for(Field f: fields){
            if(f.isAnnotationPresent(EntityProperty.class)){
                EntityProperty entityProperty = f.getAnnotation(EntityProperty.class);
                f.setAccessible(true);
                f.set(obj, entityProperty.value());
                f.setAccessible(false);
            }
        }
        return obj;
    }
}
