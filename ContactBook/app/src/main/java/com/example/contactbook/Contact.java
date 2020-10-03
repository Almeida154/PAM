package com.example.contactbook;

public class Contact {

    // Properties

    private long idContact;
    private String name, email, telephone, point, message;

    // Constructor

    public Contact(String name, String email, String telephone, String point, String message) {
        this.setName(capitalizeName(name).replace(" De ", " de ").replace(" Dos ", " dos ")
                .replace(" Da ", " da "));
        this.setEmail(email.toLowerCase());
        this.setTelephone(capitalizeOthers(telephone));
        this.setPoint(capitalizeOthers(point));
        this.setMessage(capitalizeOthers(message));
    }

    // Methods

    public static String capitalizeOthers(String str){
        if(str == null || str.isEmpty()) return str;
        else return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static String capitalizeName(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for(int i = 0; i < chars.length; i++) {
            if(!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') found = false;
        }
        return String.valueOf(chars);
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + '\n' +
                "Email: " + getEmail() + '\n' +
                "Phone: " + getTelephone() + '\n' +
                "Point: " + getPoint() + '\n' +
                "Message: " + getMessage();
    }
}