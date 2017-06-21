package com.enterprise.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by dlika on 6/16/2017.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionObject {

    private Token token;

    private User user;

    public SessionObject() {
    }

    public SessionObject(Token token, User user) {
        this.token = token;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "SessionObject{" +
                "token=" + token +
                ", user=" + user +
                '}';
    }
}

