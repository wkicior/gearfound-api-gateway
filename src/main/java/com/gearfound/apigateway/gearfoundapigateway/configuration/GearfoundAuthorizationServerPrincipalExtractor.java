package com.gearfound.apigateway.gearfoundapigateway.configuration;

import com.gearfound.apigateway.gearfoundapigateway.auth.CurrentUser;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GearfoundAuthorizationServerPrincipalExtractor implements PrincipalExtractor {
    @Override
    public Object extractPrincipal(Map<String, Object> map) {
        String name = (String) map.get("name");
        String id = (String) map.get("id");
        return new CurrentUser(name, id);
    }
}
