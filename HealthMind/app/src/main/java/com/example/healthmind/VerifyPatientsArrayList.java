package com.example.healthmind;

public abstract class VerifyPatientsArrayList {

    // Properties
    private static boolean firstTime = true;

    // Getter and Setter
    public static boolean isFirstTime() { return
            VerifyPatientsArrayList.firstTime;
    }

    public static void setFirstTime(boolean firstTime) {
        VerifyPatientsArrayList.firstTime = firstTime;
    }
}