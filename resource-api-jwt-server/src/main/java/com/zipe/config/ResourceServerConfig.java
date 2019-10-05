package com.zipe.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${security.oauth2.resource.token-info-uri}")
    private String tokenInfoUri;

    @Value("${security.oauth2.client.access-token-uri}")
    private String accessTokenUri;

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    @Value("${security.oauth2.client.user-authorization-uri}")
    private String userAuthorizationUri;

    @Value("${security.oauth2.resource.id}")
    private static String RESOURCE_ID;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.anonymous().disable()
                .requestMatchers().antMatchers("/**")
                .and().authorizeRequests()
                .antMatchers("/jwt1/**").hasRole("ADMIN")
                .antMatchers("/jwt2/**").authenticated()
                .antMatchers("/jwt3/**").access("#oauth2.hasScope('read')")
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setCheckTokenEndpointUrl(tokenInfoUri);
        tokenServices.setClientId(clientId);
        tokenServices.setClientSecret(clientSecret);
        resources.resourceId(RESOURCE_ID).tokenServices(tokenServices);
    }
}