package com.dhome.paymentmicroservice.command.application.dto.transfer;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TransferPaymentRequestDto {
    @NotNull  @Schema(example = "eca0b758-b140-490e-8cb7-07fdae1db1b1", description = "ID del cliente")
    private String customerId;
    @Schema(example = "eba11849-2daf-453c-96c2-c40fa8c4ea3b", description = "ID del empleado")
    private String employerId;
    @Schema(example = "500", description = "Monto de transferencia")
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
