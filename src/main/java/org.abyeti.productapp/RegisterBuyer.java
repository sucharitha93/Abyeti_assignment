package org.abyeti.productapp;

/**
 * Created by Sucharitha Reddy on 2/3/2015.
 */
public class RegisterBuyer extends Buyer {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "RegisterBuyer{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
