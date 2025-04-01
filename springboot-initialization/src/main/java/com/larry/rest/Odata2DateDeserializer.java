package com.larry.rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Odata2DateDeserializer extends JsonDeserializer<Date> implements Serializable {
    private static final String pattern = "dd MM yyyy HH:mm:ss:SSS z";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(pattern);
    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return null;
    }
    private Date convert(String dateStr){
        if(StringUtils.isEmpty(dateStr))
            return null;
        Date date = null;
        try{
            String dateTmp = dateStr.replace("/Date(", "");
            dateTmp = dateTmp.substring(0, dateTmp.indexOf(")/"));
            date = new Date(Long.valueOf(dateTmp));
        }catch (Exception e){
            Date parseDate = null;
            try{
                parseDate = DATE_FORMAT.parse(dateStr);
            }catch (ParseException e1){
                throw new IllegalArgumentException("");
            }
            throw new IllegalArgumentException("");
        }
        return date;
    }
}
