package com.hivision.hivision.config;

import com.hivision.hivision.pojo.PayosProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import vn.payos.PayOS;
import vn.payos.core.ClientOptions;

@Configuration
@RequiredArgsConstructor
public class PayosConfig {
    private final PayosProperties props;

    @Bean(name = "payosPaymentClient")
    @Primary
    public PayOS payosPaymentClient() {
        ClientOptions options = ClientOptions.builder()
                .clientId(props.getClientId())
                .apiKey(props.getApiKey())
                .checksumKey(props.getChecksumKey())
                .baseURL(props.getBaseUrl())
                .logLevel(ClientOptions.LogLevel.valueOf(props.getLogLevel().toUpperCase()))
                .build();
        return new PayOS(options);
    }
}
