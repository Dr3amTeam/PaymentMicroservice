package com.dhome.paymentmicroservice.command.application.dto.deposit;

public class DepositPaymentErrorResponseDto {
    private String message;
    public DepositPaymentErrorResponseDto(){
        this.message="Error with the deposit";
    }
    public DepositPaymentErrorResponseDto(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}
