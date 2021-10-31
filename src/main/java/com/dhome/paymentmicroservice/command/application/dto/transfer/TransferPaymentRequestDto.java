package com.dhome.paymentmicroservice.command.application.dto.transfer;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TransferPaymentRequestDto {
    @NotNull
    private String fromAccountId;
    private String toAccountId;
    private BigDecimal amount;

    public String getFromAccountId() {
        return fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
