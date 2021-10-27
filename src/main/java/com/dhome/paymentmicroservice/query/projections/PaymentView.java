package com.dhome.paymentmicroservice.query.projections;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Entity
public class PaymentView {
    @Id @Column(length = 50) @Getter @Setter
    private String paymentId;
    @Column(length = 100) @Getter @Setter
    private String description;
    @Column(length = 100) @Getter @Setter
    private String payMethodId;
    @Column(length = 100) @Getter @Setter
    private String appointmentId;
    private Instant createAt;
    @Column(nullable = true)
    @Getter @Setter private Instant updateAt;

    public PaymentView() {
    }

    public PaymentView(String paymentId, String description, String payMethodId, String appointmentId, Instant createAt) {
        this.paymentId = paymentId;
        this.description = description;
        this.payMethodId = payMethodId;
        this.appointmentId = appointmentId;
        this.createAt = createAt;
    }
}
