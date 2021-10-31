package com.dhome.paymentmicroservice.command.application.dto.withdraw;

public class WithdrawPaymentOkResponseDto {
    private String paymentId;

    public WithdrawPaymentOkResponseDto(String paymentId)
    {
        this.paymentId = paymentId;
    }

    public String getPaymentId() {
        return paymentId;
    }
}
