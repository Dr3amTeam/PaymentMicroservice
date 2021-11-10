package com.dhome.paymentmicroservice.command.domain;

import com.example.paymentcontracts.command.CreatePaymentTransfer;
import com.example.paymentcontracts.command.MarkPaymentAsCompleted;
import com.example.paymentcontracts.command.MarkPaymentAsFailed;
import com.example.paymentcontracts.events.PaymentTransferCompleted;
import com.example.paymentcontracts.events.PaymentTransferCreated;
import com.example.paymentcontracts.events.PaymentTransferFailed;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;


import java.math.BigDecimal;
import java.time.Instant;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;


@Aggregate
public class Payment {
    @AggregateIdentifier
    private String paymentId;
    private String customerId;
    private String employerId;
    private BigDecimal amount;
    private BigDecimal discount;
    private PaymentStatus status;

    public Payment(){

    }

    @CommandHandler
    public Payment(CreatePaymentTransfer command){
        Instant now = Instant.now();
        apply(new PaymentTransferCreated(
                command.getPaymentId(),
                command.getCustomerId(),
                command.getEmployerId(),
                command.getAmount(),
                now
        ));
    }
    @CommandHandler
    public void handle(MarkPaymentAsCompleted command){
        Instant now = Instant.now();
        apply(new PaymentTransferCompleted(command.getPaymentId(),now));
    }
    @CommandHandler
    public void handle(MarkPaymentAsFailed command){
        Instant now = Instant.now();
        apply(new PaymentTransferFailed(command.getPaymentId(),now));
    }
    /*
    @CommandHandler
    public void returnPayment(CreditFromCustomer command){
        Instant now = Instant.now();
        apply(new FromCustomerCredited(command.getCustomerId(), command.getEmployerId(),command.getPaymentId(),
        command.getAmount(),command.getDiscount(),now));
    }
     */

    @EventSourcingHandler
    public void on(PaymentTransferCreated event){
        this.paymentId=event.getPaymentId();
        this.customerId=event.getCustomerId();
        this.employerId=event.getEmployerId();
        this.amount = event.getAmount();
        this.status= PaymentStatus.CREATED;
    }
    @EventSourcingHandler
    public void on(PaymentTransferFailed event){
        this.status= PaymentStatus.FAILED;
    }
    @EventSourcingHandler
    public void on(PaymentTransferCompleted event){
        this.status= PaymentStatus.COMPLETED;
    }
}
