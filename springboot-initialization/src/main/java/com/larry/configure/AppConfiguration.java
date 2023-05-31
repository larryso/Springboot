package com.larry.configure;

import com.larry.configure.properties.Task;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Component
@Validated
@ConfigurationProperties(prefix = "app")
@Data
public class AppConfiguration {
    @NotNull
    private Task task;
}
