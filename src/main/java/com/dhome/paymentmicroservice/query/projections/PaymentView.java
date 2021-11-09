package com.dhome.paymentmicroservice.query.projections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
public class PaymentView {
    @Id
    @Column(length = 36)
    private String paymentId;

    @Column(length = 36)
    private String customerId;
    @Column(length = 36,nullable = true)
    private String employerId;

    private BigDecimal amount;

    @Column(length = 15)
    private String paymentType;

    @Column(length = 15)
    private String paymentStatus;

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
