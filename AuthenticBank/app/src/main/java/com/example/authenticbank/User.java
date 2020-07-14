package com.example.authenticbank;
import java.util.Random;

public class User {

    // Properties

    public String email, password, firstName, lastname, fullname;
    public double balance = 0, saveMoney = 0, cardInvoice = 0, cardLimit = 0;
    public boolean cardStatus = false, saveMoneyStatus = false;
    int id;

    // Construct

    public User(String firstName, String lastname, String email, String password) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.setFullname(firstName, lastname);
        setId();
    }

    // Behavior

    public static String capitalize(String str){
        if(str == null || str.isEmpty()) return str;
        else return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    // Getters

    public String getFullName() { return this.fullname; }

    public String getPassword() { return this.password; }

    public String getEmail() { return this.email; }

    public String getFirstName() { return capitalize(this.firstName); }

    public double getBalance() { return this.balance; }

    public double getCardInvoice() { return this.cardInvoice; }

    public double getCardLimit() { return this.cardLimit; }

    public boolean isCardStatus() { return this.cardStatus; }

    public boolean isSaveMoneyStatus() { return this.saveMoneyStatus; }

    public double getSaveMoney() { return this.saveMoney; }

    public double getAvailableBalance() {
        return this.getBalance() - this.getSaveMoney();
    }

    public int getId() { return this.id; }

    // Setters

    public void setFullname(String firstName, String lastname){
        firstName = firstName.trim();
        lastname = lastname.trim();
        String fn = capitalize(firstName) + " " + capitalize(lastname);
        this.fullname = fn;
    }

    public void setBalance(double balance){ this.balance = balance; }

    public void setCardStatus(boolean cardStatus) { this.cardStatus = cardStatus; }

    public void setCardInvoice(double cardInvoice) { this.cardInvoice = cardInvoice; }

    public void setCardLimit(double cardLimit) { this.cardLimit = cardLimit; }

    public void setEmail(String email) { this.email = email; }

    public void setPassword(String password) { this.password = password; }

    public void setSaveMoneyStatus(boolean saveMoneyStatus) {
        this.saveMoneyStatus = saveMoneyStatus;
        this.saveMoney = 0;
    }

    public void setSaveMoney(double saveMoney) {
        this.saveMoney += saveMoney;
    }

    public void setId() {
        Random rand = new Random();
        this.id = rand.nextInt((999999 - 100000) + 1) + 999999;
    }
}