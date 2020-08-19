package com.example.healthmind;

public abstract class VerifyAppointmentArrayList {

    // Properties
    private static boolean firstTime = true;

    // Getter and Setter
    public static boolean isFirstTime() { return
            VerifyAppointmentArrayList.firstTime;
    }

    public static void setFirstTime(boolean firstTime) {
        VerifyAppointmentArrayList.firstTime = firstTime;
    }
}