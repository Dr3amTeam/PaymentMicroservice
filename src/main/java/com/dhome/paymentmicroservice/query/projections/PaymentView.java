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
    private String fromAccountId;
    @Column(length = 36,nullable = true)
    private String toAccountId;

    private BigDecimal amount;

    @Column(length = 15)
    private String paymentType;

    @Column(length = 15)
    private String paymentStatus;

    private Instant createAt;

    public PaymentView() {
    }

    public PaymentView(String paymentId, String fromAccountId, String toAccountId, BigDecimal amount, String paymentType, String paymentStatus, Instant createAt) {
        this.paymentId = paymentId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
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

    public String getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId;
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
