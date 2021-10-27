package com.dhome.paymentmicroservice.command.application.dtos.request;


import lombok.Value;

@Value
public class RegisterPaymentRequest {
    private String description;
    private String payMethodId;
    private String appointmentId;
}
