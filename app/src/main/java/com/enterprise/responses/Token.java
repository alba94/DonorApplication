package com.enterprise.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dlika on 6/2/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {

    private Long expires_in;
    private String token_type;
    private String scope;
    private String jti;

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "Token{" +
                "expires_in=" + expires_in +
                ", token_type='" + token_type + '\'' +
                ", scope='" + scope + '\'' +
                ", jti='" + jti + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", access_token='" + access_token + '\'' +
                '}';
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    private String refresh_token;
    private String access_token;

    public Token() {
    }


}
