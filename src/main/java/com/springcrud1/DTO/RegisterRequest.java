package com.springcrud1.DTO;

public class RegisterRequest {

    private String username;
    private String password;
    private String vemp_code;

    public String getVemp_code() {
        return vemp_code;
    }

    public void setVemp_code(String vemp_code) {
        this.vemp_code = vemp_code;
    }

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
}
