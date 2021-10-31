package com.dhome.paymentmicroservice.command.application.dto.withdraw;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class WithdrawPaymentRequestDto {
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
