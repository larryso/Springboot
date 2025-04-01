package com.larry.rest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.commons.lang3.SerializationUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class ObjectMapperUtils {
    private static ObjectMapper __mapper = _createObjectMapper();

    public static ObjectMapper createObjectMapper(){
        return __mapper;
    }
    private static ObjectMapper _createObjectMapper(){
        __mapper = new ObjectMapper(){
            private ThreadLocal<DateFormat> dateFormatThreadLocal = new ThreadLocal<>();
            private ReentrantLock lock = new ReentrantLock();

            @Override
            public DateFormat getDateFormat() {
                System.out.println("#######################");
                DateFormat dateFormat = dateFormatThreadLocal.get();
                if(dateFormat == null){
                    try{
                        lock.lock();
                        dateFormat = dateFormatThreadLocal.get();
                        if(dateFormat == null){
                            DateFormat supperDateFormat = super.getDateFormat();
                            dateFormat = SerializationUtils.clone(supperDateFormat);
                            dateFormatThreadLocal.set(dateFormat);
                        }

                    }finally {
                        lock.unlock();
                    }
                }
                return dateFormat;
            }
        };
        /**
         * ObjectMapper.setVisibility 是一个 Jackson 库的方法，用于设置 Java 对象的属性可见性。它可以设置序列化和反序列化时的访问级别，例如，可以设置只对 public 属性进行序列化，同时忽略 private 属性。
         * 这个方法可以接受一个枚举类型参数，用于设置访问级别，包括：
         *
         * - JsonAutoDetect.Visibility.ANY：任何可见性的属性都将被序列化和反序列化。
         * - JsonAutoDetect.Visibility.NONE：没有属性会被序列化或反序列化。
         * - JsonAutoDetect.Visibility.NON_PRIVATE：除了 private 属性外，其他可见性的属性都将被序列化和反序列化。
         * - JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC：protected 和 public 属性都将被序列化和反序列化。
         * - JsonAutoDetect.Visibility.PUBLIC_ONLY：只有 public 属性会被序列化和反序列化。
         *
         * 使用 ObjectMapper.setVisibility 方法可以控制序列化和反序列化时 Java 对象的属性可见性，提高代码的安全性和稳定性。
         */
        __mapper.setVisibility(__mapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));

        __mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        __mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, false);
        __mapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, true);
        __mapper.configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
        __mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

        //exclude null properties
        __mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        __mapper.enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
        // set Date Serialize, by default, springboot return TimeStamp
        __mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        //通过给mapper添加module，在module中可以为指定的类型添加序列化器和反序列化器，就可以实现相当于自定义序列化和反序列化的效果
        //here we add OData data format support
        SimpleModule module = new SimpleModule("EBO", new Version(0,9,0,"", "com.larry", ""));
        module.addSerializer(Date.class, new OData2DateSerializer());
        module.addDeserializer(Date.class, new Odata2DateDeserializer());
        __mapper.registerModule(module);

        return __mapper;
    }
}
