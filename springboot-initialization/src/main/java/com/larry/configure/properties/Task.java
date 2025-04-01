package com.larry.configure.properties;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task {
    @NotNull
    private String threadNamePrefix;
    @NotNull
    private int poolCoreSize;
    @NotNull
    private int poolMaxSize;
    @NotNull
    private int queueCapacity;
    @NotNull
    private int batchSize;
    @NotNull
    private int batchChunkDelay;
}
