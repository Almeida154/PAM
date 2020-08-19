package com.example.healthmind;

public class Schedule {

    // Properties
    private int day, month, year, hour, minute;

    // Constructor
    public Schedule(int day, int month, int hour, int minute) {
        this.setDay(day);
        this.setMonth(month);
        this.setHour(hour);
        this.setMinute(minute);
    }

    // Getters and Setters
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
