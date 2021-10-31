package com.dhome.paymentmicroservice.command.application.dto.withdraw;

public class WithdrawPaymentErrorDto {
    private String message;
    public WithdrawPaymentErrorDto(){
        this.message="Error with the deposit";
    }
    public WithdrawPaymentErrorDto(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}
