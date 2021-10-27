package com.dhome.paymentmicroservice.command.infra;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PaymentDescription extends PayMethodType{
    @Id
    public String paymentId;
    @Column(length = 100)
    public String description;
    public String payMethodId;
    public String appointmentId;

    public PaymentDescription(String type, String value, String type1, String currencyId, String paymentId, String description, String payMethodId, String appointmentId) {
        super(type, value, type1, currencyId);
        this.paymentId = paymentId;
        this.description = description;
        this.payMethodId = payMethodId;
        this.appointmentId = appointmentId;
    }

    public PaymentDescription(String type, String currencyId, String paymentId, String description, String payMethodId, String appointmentId) {
        super(type, currencyId);
        this.paymentId = paymentId;
        this.description = description;
        this.payMethodId = payMethodId;
        this.appointmentId = appointmentId;
    }

    public PaymentDescription(String paymentId, String description, String payMethodId, String appointmentId) {
        this.paymentId = paymentId;
        this.description = description;
        this.payMethodId = payMethodId;
        this.appointmentId = appointmentId;
    }
    public PaymentDescription(){}

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPayMethodId() {
        return payMethodId;
    }

    public void setPayMethodId(String payMethodId) {
        this.payMethodId = payMethodId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }
}
