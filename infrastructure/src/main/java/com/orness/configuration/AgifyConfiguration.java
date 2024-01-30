package com.orness.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

    @Configuration
    @ConditionalOnProperty("agify.url")
    public class AgifyConfiguration {

        @Bean
        // We could have used WebClient but it was not included in our dependencies
        // so RestTemplate spared us an additional dependency
        public RestTemplate agifyClient(RestTemplateBuilder builder, @Value("${agify.url}") String agifyUrl) {
            // when calling put "/" as the uri so that it uses the base uri
            return builder.rootUri(agifyUrl).build();
        }
    }


