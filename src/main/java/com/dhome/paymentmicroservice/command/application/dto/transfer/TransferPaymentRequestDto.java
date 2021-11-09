package com.dhome.paymentmicroservice.command.application.dto.transfer;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TransferPaymentRequestDto {
    @NotNull
    private String customerId;
    private String employerId;
    private BigDecimal amount;

    public String getCustomerId() {
        return customerId;
    }

    public String getEmployerId() {
        return employerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
