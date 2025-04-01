package com.larry.security.oauth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;

@Component
@ConfigurationProperties("app.security.oauth2.resource.nimbus-jwt-decoder")
@Data
@Validated
public class NimbusJWTDecoderProperties {
    private String failoverJwkSetUrl;
    private Duration cacheRefreshTimeout;
    private Duration cacheTtl;
    private Duration reteLimitMinInterval;
    private Duration refreshAheadTime;
    private boolean refreshAheadScheduled;
}
