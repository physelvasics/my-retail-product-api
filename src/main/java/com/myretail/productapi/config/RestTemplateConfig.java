package com.myretail.productapi.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Value("${rest.template.connection.timeout.ms}")
    private Integer connectionTimeout;

    @Value("${rest.template.read.timeout.ms}")
    private Integer readTimeout;

    @Bean
    public RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();
        factory.setConnectTimeout(connectionTimeout);
        factory.setReadTimeout(readTimeout);
        factory.setHttpClient(httpClient);
        restTemplate.setRequestFactory(factory);
        return restTemplate;
    }
}
