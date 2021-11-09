package com.dhome.paymentmicroservice.command.sagas;

import com.example.paymentcontracts.events.PaymentTransferCreated;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import javax.inject.Inject;
import java.math.BigDecimal;

@Saga
public class PaymentSaga {
    private String customerId;
    private String employerId;
    private BigDecimal amount;

    @Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "paymentId")
    public void on(PaymentTransferCreated event){
        this.customerId=event.getCustomerId();
        this.employerId=event.getEmployerId();
        this.amount=event.getAmount();





    }
    @SagaEventHandler(associationProperty = "transactionId")
    @EndSaga
    public void on()
}
