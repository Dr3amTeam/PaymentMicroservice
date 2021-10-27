package com.dhome.paymentmicroservice.command.application.handlers;

import com.dhome.paymentmicroservice.command.infra.PaymentDescription;
import com.dhome.paymentmicroservice.command.infra.PaymentDescriptionRepository;
import com.example.paymentcontracts.events.PaymentOpened;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("paymentDescription")
public class PaymentEventHandler{
    private final PaymentDescriptionRepository paymentDescriptionRepository;

    public PaymentEventHandler(PaymentDescriptionRepository paymentDescriptionRepository){
        this.paymentDescriptionRepository=paymentDescriptionRepository;
    }
    @EventHandler
    public void on(PaymentOpened event){
        paymentDescriptionRepository.save(new PaymentDescription(event.getDescription(),event.getPayMethodId(),
                event.getAppointmentId()));
    }
}
