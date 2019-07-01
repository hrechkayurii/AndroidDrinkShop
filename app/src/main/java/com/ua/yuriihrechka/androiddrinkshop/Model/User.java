package com.ua.yuriihrechka.androiddrinkshop.Model;

public class User {

    private String phone;
    private String name;
    private String address;
    private String birthdate;
    private String error_msg;

    public User() {

    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getError_msg() {
        return error_msg;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }
}
