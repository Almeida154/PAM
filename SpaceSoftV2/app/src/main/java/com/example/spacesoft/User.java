package com.example.spacesoft;

public class User{

    //Atributos

    public String username;
    public String password;
    public String money;

    //Construct

    public User(String username, String password, String money) {
        this.username = username;
        this.password = password;
        this.money = money;
    }

    //Getters

    public String getUsername() {
        return username;
    }


    public String getMoney() {
        return money;
    }


    public String getPassword() {
        return password;
    }

}
