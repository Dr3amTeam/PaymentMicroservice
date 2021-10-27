package com.dhome.paymentmicroservice.command.application.validators;

import com.dhome.common.application.Notification;
import com.dhome.paymentmicroservice.command.application.dtos.request.RegisterPaymentRequest;
import com.dhome.paymentmicroservice.command.infra.PaymentDescription;
import com.dhome.paymentmicroservice.command.infra.PaymentDescriptionRepository;
import org.springframework.stereotype.Component;

@Component
public class RegisterPaymentValidator {
    private final PaymentDescriptionRepository paymentDescriptionRepository;

    public RegisterPaymentValidator(PaymentDescriptionRepository paymentDescriptionRepository){
        this.paymentDescriptionRepository=paymentDescriptionRepository;
    }
    public Notification validate(RegisterPaymentRequest registerPaymentRequest)
    {
        Notification notification = new Notification();
        String description = registerPaymentRequest.getDescription().trim();
        if(description.isEmpty()){
            notification.addError("Payment description is required");
        }
        String payMethodId = registerPaymentRequest.getPayMethodId().trim();
        if(payMethodId.isEmpty()){
            notification.addError("Payment payMethodId is required");
        }
        String appointmentId = registerPaymentRequest.getAppointmentId().trim();
        if (appointmentId.isEmpty()){
            notification.addError("Payment appointmentId is required");
        }
        if(notification.hasErrors()){
            return notification;
        }
        return notification;
    }

}
