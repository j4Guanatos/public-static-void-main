package com.j4gdl.model.security;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * Created by Emmanuel_Garcia on 3/30/2017.
 */
public class User {
    @Id
    private String username;
    private String passsword;
    private List<String> roleList;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasssword() {
        return passsword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }
}
