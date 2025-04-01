package com.larry.security;

import com.larry.security.oauth.NimbusJWTDecoderProperties;
import lombok.Data;
import org.codehaus.commons.nullanalysis.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties("app.security")
@Data
@Validated
public class SecurityProperties {
    @NotNull
    private boolean enabled;
    private NimbusJWTDecoderProperties nimbusJWTDecoderProperties;

    @Autowired
    public void setNimbusJwtDecoderProperties(NimbusJWTDecoderProperties nimbusJWTDecoderProperties){
        this.nimbusJWTDecoderProperties = nimbusJWTDecoderProperties;
    }

}
