package com.enterprise.responses;

/**
 * Created by dlika on 6/16/2017.
 */

public class SessionObject {

    private Token token;

    public SessionObject(Token token, User user) {
        this.token = token;
        this.user = user;
    }

    private User user;

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
