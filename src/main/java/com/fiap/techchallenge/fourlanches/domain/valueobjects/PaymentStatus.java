package com.fiap.techchallenge.fourlanches.domain.valueobjects;

public enum PaymentStatus {
    WAITING,
    SUCCEEDED;

    public static PaymentStatus getPaymentStatus(boolean paymentApproved){
        return paymentApproved ? PaymentStatus.SUCCEEDED : PaymentStatus.WAITING;
    }

}
