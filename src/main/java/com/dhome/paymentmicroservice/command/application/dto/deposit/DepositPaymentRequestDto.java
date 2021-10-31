package com.dhome.paymentmicroservice.command.application.dto.deposit;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class DepositPaymentRequestDto {
    @NotNull
    private String accountId;
    private BigDecimal amount;

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
