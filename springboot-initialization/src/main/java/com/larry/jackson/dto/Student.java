package com.larry.jackson.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Student {
    private String name;
    private int rollNo;
    private Gender gender;
    private String schoolName;
}
