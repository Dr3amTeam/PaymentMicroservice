package com.dhome.paymentmicroservice.command.application.dto.transfer;

public class TransferPaymentErrorDto {
    private String message;
    public TransferPaymentErrorDto(){
        this.message="Error with the transfer";
    }
    public TransferPaymentErrorDto(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}
