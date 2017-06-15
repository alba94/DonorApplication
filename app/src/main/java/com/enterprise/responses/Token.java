package com.enterprise.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dlika on 6/2/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {

    private final Long expiresIn;
    private final String tokenType;
    private final String scope;
    private final String jti;
    private final String refreshToken;
    private final String accessToken;

    public Token(Long expiresIn, String tokenType, String refreshToken, String accessToken, String scope, String jti) {
        this.expiresIn = expiresIn;
        this.tokenType = tokenType;
        this.scope = scope;
        this.jti = jti;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public String getScope() {
        return scope;
    }

    public String getJti() {
        return jti;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }


}
