package com.gearfound.apigateway.gearfoundapigateway.auth;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Base64;

@Component
public class Oauth2BasicAuthFilter extends ZuulFilter {

    @NotNull
    @Value("${zuul.routes.authorization-service.oauth.client-id}")
    private String clientId;

    @NotNull
    @Value("${zuul.routes.authorization-service.oauth.client-secret}")
    private String secret;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        return (ctx.get("proxy") != null) && ctx.get("proxy").equals("authorization-service");
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.getRequest().getRequestURL();
        String credentialsEncoded = Base64.getEncoder().encodeToString((clientId + ":" + secret).getBytes());
        ctx.addZuulRequestHeader("Authorization", "Basic " + credentialsEncoded);
        return null;
    }
}
