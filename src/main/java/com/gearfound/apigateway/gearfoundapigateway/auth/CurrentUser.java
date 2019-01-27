package com.gearfound.apigateway.gearfoundapigateway.auth;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class CurrentUser {
    String name;
    String id;
}
