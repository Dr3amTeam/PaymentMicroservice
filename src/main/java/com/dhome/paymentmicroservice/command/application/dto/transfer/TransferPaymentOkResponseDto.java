package com.dhome.paymentmicroservice.command.application.dto.transfer;

public class TransferPaymentOkResponseDto {
    private String paymentId;

    public TransferPaymentOkResponseDto(String paymentId)
    {
        this.paymentId = paymentId;
    }

    public String getPaymentId() {
        return paymentId;
    }
}
