package com.gearfound.apigateway.gearfoundapigateway.auth;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AddUserIdFilter extends ZuulFilter {
    private static final String USER_HEADER = "X-User-Id";

    @Override
    public int filterOrder() {
        return FilterConstants.SIMPLE_HOST_ROUTING_FILTER_ORDER - 1;
    }

    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;

    }

    @Override
    public boolean shouldFilter() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.isAuthenticated();
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        requestContext.addZuulRequestHeader(USER_HEADER, currentUser.getId());
        return null;
    }
}
