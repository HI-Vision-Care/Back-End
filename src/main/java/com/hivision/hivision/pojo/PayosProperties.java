package com.hivision.hivision.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "payos")
@Data
public class PayosProperties {
    private String clientId;
    private String apiKey;
    private String checksumKey;
    private String baseUrl;
//    private String payoutClientId;
//    private String payoutApiKey;
//    private String payoutChecksumKey;
    private String logLevel;
}
