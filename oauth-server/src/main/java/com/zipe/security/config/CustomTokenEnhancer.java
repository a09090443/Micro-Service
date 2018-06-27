package com.zipe.security.config;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.DefaultOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer {  
	  
    @Override  
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {  
        if (accessToken instanceof DefaultOAuth2AccessToken) {  
            DefaultOAuth2AccessToken token = ((DefaultOAuth2AccessToken) accessToken);  
            token.setValue(getNewToken());  
              
            OAuth2RefreshToken refreshToken = token.getRefreshToken();  
            if (refreshToken instanceof DefaultOAuth2RefreshToken) {  
                token.setRefreshToken(new DefaultOAuth2RefreshToken(getNewToken()));  
            }  
              
            Map<String, Object> additionalInformation = new HashMap<String, Object>();  
            additionalInformation.put("client_id", authentication.getOAuth2Request().getClientId());  
            token.setAdditionalInformation(additionalInformation);  
              
            return token;  
        }  
        return accessToken;  
    }  
      
    private String getNewToken() {  
        return UUID.randomUUID().toString().replace("-", "");  
    }

}  