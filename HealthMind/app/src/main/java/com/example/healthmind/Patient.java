package com.example.healthmind;

public class Patient {

    // Properties
    private String name, gender, email, cpf;
    private int appointments = 0, spent = 0;

    // Constructor
    public Patient(String name, String gender, String email, String cpf) {
        this.setName(capitalizeName(name).replace(" De ", " de ").replace(" Dos ", " dos ")
                .replace(" Da ", " da "));
        this.setGender(gender);
        this.setEmail(email.toLowerCase());
        this.setCpf(cpf);
    }

    // Method
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
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getAppointments() {
        return appointments;
    }

    public void setAppointments(int appointments) {
        this.appointments = appointments;
    }

    public int getSpent() {
        return spent;
    }

    public void setSpent(int spent) {
        this.spent = spent;
    }

    // toString
    @Override
    public String toString() {
        return "Name: " + this.getName() + "\n" +
                "Email: " + this.getEmail() + "\n" +
                "Cpf: " + this.getCpf() + "\n" +
                "Appointments: " + this.getAppointments() + " times\n" +
                "Total spent: R$" + this.getSpent() + ",00";
    }
}