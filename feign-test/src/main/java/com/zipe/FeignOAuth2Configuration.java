package com.zipe;

import feign.Logger;
import feign.RequestInterceptor;
import feign.Retryer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import java.util.concurrent.TimeUnit;

@Configuration
public class FeignOAuth2Configuration {

    @Value("${security.oauth2.client.accessTokenUri}")
    private String accessTokenUri;
    @Value("${security.oauth2.client.client-id}")
    private String clientId;
    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;
//    @Value("${security.oauth2.client.scope}")
//    private String scope;

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), clientResourceDetails());
    }

    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(100, TimeUnit.SECONDS.toMillis(1), 5);
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    private OAuth2ProtectedResourceDetails clientResourceDetails() {
        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
        resource.setAccessTokenUri(accessTokenUri);
        resource.setClientId(clientId);
        resource.setClientSecret(clientSecret);
        return resource;
    }
}