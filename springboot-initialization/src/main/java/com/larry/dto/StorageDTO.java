package com.larry.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StorageDTO {
    private String bucket;
    private String bucketType;
    private String antivirusStatus;
    private Long fileSize;
}
