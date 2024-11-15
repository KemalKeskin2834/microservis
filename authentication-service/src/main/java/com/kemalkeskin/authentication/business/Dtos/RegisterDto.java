package com.kemalkeskin.authentication.business.Dtos;

public class RegisterDto {

    private String userName;

    private  String password;

    public RegisterDto() {
    }

    public RegisterDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
