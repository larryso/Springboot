package com.larry.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.SerializationUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
                        DateFormat supperDateFormat = super.getDateFormat();
                        dateFormat = SerializationUtils.clone(supperDateFormat);
                        dateFormatThreadLocal.set(dateFormat);
                    }finally {
                        lock.unlock();
                    }
                }
                return dateFormat;
            }
        };
        //exclude null properties
        __mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        __mapper.enable(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY);
        // set Date Serialize, by default, springboot return TimeStamp
        __mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return __mapper;
    }
}
