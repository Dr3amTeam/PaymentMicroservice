package com.dhome.paymentmicroservice.config;

import com.dhome.paymentmicroservice.command.domain.Payment;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AxonConfig {
    @Bean
    public Repository<Payment> eventSourcingRepository(EventStore eventStore){
        return EventSourcingRepository.builder(Payment.class)
                .eventStore(eventStore)
                .build();
    }
}
