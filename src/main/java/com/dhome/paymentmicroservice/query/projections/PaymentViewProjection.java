package com.dhome.paymentmicroservice.query.projections;

import com.example.paymentcontracts.events.PaymentRegistered;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class PaymentViewProjection {
    private final PaymentViewRepository paymentViewRepository;
    public  PaymentViewProjection(PaymentViewRepository paymentViewRepository){
        this.paymentViewRepository=paymentViewRepository;
    }
    @EventHandler
    public void on(PaymentRegistered event, @Timestamp Instant timestamp){
        PaymentView paymentView = new PaymentView(event.getPaymentId(),event.getDescription(),event.getPayMethodId(),
                event.getAppointmentId(),event.getOccurredOn());
        paymentViewRepository.save(paymentView);
    }
}
