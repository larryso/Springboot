package com.larry.java8Demo.io;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProcessStorage {
    private String fileName;
    private String path;
    private String parentPath;
    private Long size;
}
