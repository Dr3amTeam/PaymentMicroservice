package com.dhome.paymentmicroservice.command.domain;
import com.example.paymentcontracts.command.RegisterPayment;
import com.example.paymentcontracts.events.PaymentRegistered;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Payment {
    @AggregateIdentifier
    private String paymentId;
    private String description;
    private String payMethodId;
    private String appointmentId;

    public Payment(){

    }

    @CommandHandler
    public Payment(RegisterPayment registerPayment){
        Instant now = Instant.now();
        apply(new PaymentRegistered(registerPayment.getPaymentId(),registerPayment.getDescription(),
                registerPayment.getPayMethodId(),
                registerPayment.getAppointmentId(),now));
    }

    @EventSourcingHandler
    protected void on(PaymentRegistered event){
        paymentId = event.getPaymentId();
        description = event.getDescription();
        payMethodId = event.getPayMethodId();
        appointmentId = event.getAppointmentId();
    }
}
