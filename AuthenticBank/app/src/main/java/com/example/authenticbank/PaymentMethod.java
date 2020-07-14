package com.example.authenticbank;

public class PaymentMethod {

    // Properties

    private static boolean cardMethod, balanceMethod = true;

    // Getters and Setters

    public static boolean isCardMethod() { return PaymentMethod.cardMethod; }

    public static void setCardMethod(boolean cardMethod) {
        PaymentMethod.cardMethod = cardMethod;
        PaymentMethod.balanceMethod = false;
    }

    public static boolean isBalanceMethod() { return PaymentMethod.balanceMethod; }

    public static void setBalanceMethod(boolean balanceMethod) {
        PaymentMethod.balanceMethod = balanceMethod;
        PaymentMethod.cardMethod = false;
    }
}
