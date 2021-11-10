package com.dhome.paymentmicroservice.command.sagas;

import com.dhome.registermicroservice.contracts.commands.AccountToEmployee;
import com.dhome.registermicroservice.contracts.events.FromCustomerAccount;
import com.dhome.registermicroservice.contracts.events.ToEmployeeAccount;
import com.example.paymentcontracts.command.MarkPaymentAsCompleted;
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
        AccountToEmployee accountToEmployee = new AccountToEmployee(
                event.getCustomerId(),
                event.getPaymentId(),
                event.getAmount());
        commandGateway.send(accountToEmployee);

    }

    @SagaEventHandler(associationProperty = "paymentId")
    public void on(FromCustomerAccount event) {
        AccountToEmployee command = new AccountToEmployee(customerId, employerId, event.getAmount());
        commandGateway.send(command);
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "paymentId")
    public void on(ToEmployeeAccount event) {
        MarkPaymentAsCompleted command = new MarkPaymentAsCompleted(event.getTransactionId());
        commandGateway.send(command);
    }

}
