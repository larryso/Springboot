package com.larry.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public class OData2DateSerializer extends JsonSerializer<Date> implements Serializable {
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(convert(value));
    }
    private String convert(Date o){
        return o==null? null: "/Date("+o.getTime()+")/";
    }
}
