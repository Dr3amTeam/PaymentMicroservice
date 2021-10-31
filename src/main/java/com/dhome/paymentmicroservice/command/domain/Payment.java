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
import org.springframework.transaction.TransactionStatus;


import java.math.BigDecimal;
import java.time.Instant;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;


@Aggregate
public class Payment {
    @AggregateIdentifier
    private String paymentId;
    private String fromAccountId;
    private String toAccountId;
    private BigDecimal amount;/*
    private String payMethodId;
    private String appointmentId;*/
    private PaymentStatus status;

    protected Payment(){

    }
    @CommandHandler
    public Payment(CreatePaymentTransfer command){
        apply(new PaymentTransferCreated(
            command.getPaymentId(),
                command.getFromAccountId(),
                command.getToAccountId(),
                command.getAmount(),/*
                command.getPayMethodId(),
                command.getAppointmentId()*/Instant.now()
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
    public void returnPayment(CreditFromAccount command){
        Instant now = Instant.now();
        apply(new FromAccountCredited(command.getFromAccountId(), command.getToAccountId(),command.getPaymentId(),
        command.getAmount(),now));
    }
     */
    @EventSourcingHandler
    protected void on(PaymentTransferCreated event){
        this.paymentId=event.getPaymentId();
        this.fromAccountId=event.getFromAccountId();
        this.toAccountId=event.getToAccountId();
        this.amount = event.getAmount();/*
        this.payMethodId=event.getPayMethodId();
        this.appointmentId=event.getAppointmentId();*/
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
