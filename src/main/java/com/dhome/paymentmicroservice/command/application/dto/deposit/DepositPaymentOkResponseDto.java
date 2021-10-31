package com.dhome.paymentmicroservice.command.application.dto.deposit;

public class DepositPaymentOkResponseDto {
    private String paymentId;
    public DepositPaymentOkResponseDto(String paymentId){
        this.paymentId=paymentId;
    }
    public String getPaymentId(){
        return paymentId;
    }
}
