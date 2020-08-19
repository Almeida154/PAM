package com.example.healthmind;

public class Appointment {

    // properties
    private Patient patient;
    private String date, hour, description, currentDay;
    private int profit = 0;

    // Constructor
    public Appointment(Patient patient, String date, String hour, String description, String currentDay) {
        this.setPatient(patient);
        this.setDate(date);
        this.setHour(hour);
        this.setDescription(description);
        this.setCurrentDay(currentDay);
    }

    // Getters and Setters
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(String currentDay) {
        this.currentDay = currentDay;
    }

    // toString
    @Override
    public String toString() {
        return "Patient: " + this.getPatient().getName() + "\n" +
                "Email: " + this.getPatient().getEmail() + "\n" +
                "Date: " + this.getDate() + " ("+ this.getCurrentDay() +")\n" +
                "Hour: " + this.getHour() + "\n" +
                "Description: " + this.getDescription() ;
    }
}
