package com.larry.jackson;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.larry.jackson.dto.Gender;
import com.larry.jackson.dto.Student;

public class AnnotationTest {
    public static void main(String[] args) throws Exception {
        // ---------Test @JsonValue------------------
        ObjectMapper mapper = new ObjectMapper();
//        Student student = Student.builder().
//                gender(Gender.FEMALE).
//                name("Larry").
//                rollNo(1).schoolName("Middle School").build();
//        String studentJson = mapper.writeValueAsString(student);
//        System.out.println(studentJson);
        //---------Test @JsonCreator------------------
        String jsonStr =  "{\"name\":\"Larry Song\",\"rollNo\":12, \"gender\": \"2\", \"schoolName\":\"Middle School\"}";
        Student student = mapper.readValue(jsonStr, Student.class);
        System.out.println(student);
    }
}
