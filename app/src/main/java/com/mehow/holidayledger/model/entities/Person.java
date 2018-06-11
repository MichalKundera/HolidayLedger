package com.mehow.holidayledger.model.entities;

public class Person {
    int id;
    String Name;
    String password;
    Float accountBalance;

    public Person(int id, String name, String password, Float accountBalance) {
        this.id = id;
        Name = name;
        this.password = password;
        this.accountBalance = accountBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Float getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Float accountBalance) {
        this.accountBalance = accountBalance;
    }
}
