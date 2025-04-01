package com.larry.jackson.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder
public class Coffee {
    private String name;
    private String brand;
    private LocalDateTime date;
}
