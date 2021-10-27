package com.dhome.paymentmicroservice.command.application.dtos.response;

import lombok.Value;

@Value
public class RegisterPaymentResponse {
    private String paymentId;
    private String description;
    private String payMethodId;
    private String appointmentId;
}
