package com.larry.jackson.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE("1"), FEMALE("0");

    private String sex;
    Gender(String sex){
        this.sex = sex;
    }
    public static Gender getBySex(String sex){
        Gender[] values = values();
        for(Gender gender: values){
            if(gender.sex().equals(sex)){
                return gender;
            }
        }
        return null;
    }
    @JsonCreator
    public static Gender fromObject(String sex){
        return Gender.getBySex(sex);
    }
    @JsonValue
    public String sex(){
        return this.sex;
    }
}
