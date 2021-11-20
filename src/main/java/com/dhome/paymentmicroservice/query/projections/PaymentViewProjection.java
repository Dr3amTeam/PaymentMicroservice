package com.dhome.paymentmicroservice.query.projections;
import com.dhome.paymentmicroservice.command.domain.PaymentStatus;
import com.dhome.paymentmicroservice.command.domain.PaymentType;
import com.example.paymentcontracts.events.PaymentTransferCompleted;
import com.example.paymentcontracts.events.PaymentTransferCreated;
import com.example.paymentcontracts.events.PaymentTransferFailed;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

@Component
public class PaymentViewProjection {
    private final PaymentViewRepository paymentViewRepository;

    public PaymentViewProjection(PaymentViewRepository paymentViewRepository) {
        this.paymentViewRepository = paymentViewRepository;
    }

    @EventHandler
    public void on(PaymentTransferCreated event) {
        String paymentId = event.getPaymentId();
        String customerId = event.getCustomerId();
        String employerId = event.getEmployerId();
        BigDecimal amount = event.getAmount();
        String paymentType;
        if(amount.doubleValue()>800){
            paymentType = PaymentType.CREDIT.toString();
        }else {
            paymentType = PaymentType.CASH.toString();
        }
        String paymentStatus = PaymentStatus.CREATED.toString();

        PaymentView paymentView = new PaymentView(paymentId, customerId, employerId, amount, paymentType,
                paymentStatus, event.getOccurredOn());
        paymentViewRepository.save(paymentView);
    }
/*
    @EventHandler
    public void on(FromAccountDebited event) {
        Optional<PaymentView> paymentViewOptional = paymentViewRepository.findById(event.getPaymentId());
        if (paymentViewOptional.isPresent()) {
            PaymentView paymentView = paymentViewOptional.get();
            String paymentStatus = PaymentStatus.IN_PROGRESS.toString();
            paymentView.setPaymentStatus(paymentStatus);
            paymentView.setCreateAt(event.getOccurredOn());
            paymentViewRepository.save(paymentView);
        }
    }
*/
    @EventHandler
    public void on(PaymentTransferFailed event) {
        Optional<PaymentView> paymentViewOptional = paymentViewRepository.findById(event.getPaymentId());
        if (paymentViewOptional.isPresent()) {
            PaymentView paymentView = paymentViewOptional.get();
            String paymentStatus = PaymentStatus.FAILED.toString();
            paymentView.setPaymentStatus(paymentStatus);
            paymentView.setCreateAt(event.getOccurredOn());
            paymentViewRepository.save(paymentView);
        }
    }

    @EventHandler
    public void on(PaymentTransferCompleted event) {
        Optional<PaymentView> paymentViewOptional = paymentViewRepository.findById(event.getPaymentId());
        if (paymentViewOptional.isPresent()) {
            PaymentView paymentView = paymentViewOptional.get();
            String paymentStatus = PaymentStatus.COMPLETED.toString();
            paymentView.setPaymentStatus(paymentStatus);
            paymentView.setCreateAt(event.getOccurredOn());
            paymentViewRepository.save(paymentView);
        }
    }
}