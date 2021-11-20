package com.dhome.paymentmicroservice.query.projections;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class PaymentView {
    @Id
    @Schema(example = "cfb4657f-7bb1-45cb-a70b-6d69a3877605", description = "ID del pago")
    @Column(length = 36)
    private String paymentId;
    @Schema(example = "eca0b758-b140-490e-8cb7-07fdae1db1b1", description = "ID del cliente")
    @Column(length = 36)
    private String customerId;
    @Schema(example = "eba11849-2daf-453c-96c2-c40fa8c4ea3b", description = "ID del empleado")
    @Column(length = 36,nullable = true)
    private String employerId;
    @Schema(example = "500", description = "Monto de transferencia")
    private BigDecimal amount;
    @Schema(example = "CASH", description = "Tipo de Pago")
    @Column(length = 15)
    private String paymentType;
    @Schema(example = "COMPLETED", description = "Estado del pago")
    @Column(length = 15)
    private String paymentStatus;
    @Schema(example = "2021-11-18T21:51:46.917091Z", description = "Fecha de creación")
    private Instant createAt;

    public PaymentView() {
    }

    public PaymentView(String paymentId, String customerId, String employerId, BigDecimal amount, String paymentType, String paymentStatus, Instant createAt) {
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.employerId = employerId;
        this.amount = amount;
        this.paymentType = paymentType;
        this.paymentStatus = paymentStatus;
        this.createAt = createAt;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmployerId() {
        return employerId;
    }

    public void setEmployerId(String employerId) {
        this.employerId = employerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }
}
