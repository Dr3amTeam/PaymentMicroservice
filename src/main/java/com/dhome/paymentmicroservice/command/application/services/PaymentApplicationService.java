package com.dhome.paymentmicroservice.command.application.services;

import com.dhome.common.application.Notification;
import com.dhome.common.application.Result;
import com.dhome.common.application.ResultType;
import com.dhome.paymentmicroservice.command.application.dtos.request.RegisterPaymentRequest;
import com.dhome.paymentmicroservice.command.application.dtos.response.RegisterPaymentResponse;
import com.dhome.paymentmicroservice.command.application.validators.RegisterPaymentValidator;
import com.dhome.paymentmicroservice.command.infra.PaymentDescriptionRepository;
import com.example.paymentcontracts.command.RegisterPayment;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class PaymentApplicationService {
    private final RegisterPaymentValidator registerPaymentValidator;
    private final CommandGateway commandGateway;
    private final PaymentDescriptionRepository paymentDescriptionRepository;

    public PaymentApplicationService(RegisterPaymentValidator registerPaymentValidator, CommandGateway commandGateway,
                                     PaymentDescriptionRepository paymentDescriptionRepository){
        this.registerPaymentValidator = registerPaymentValidator;
        this.commandGateway=commandGateway;
        this.paymentDescriptionRepository=paymentDescriptionRepository;
    }


    public Result<RegisterPaymentResponse, Notification> register(RegisterPaymentRequest registerPaymentRequest) throws Exception{
        Notification notification = this.registerPaymentValidator.validate(registerPaymentRequest);
        if(notification.hasErrors()){
            return Result.failure(notification);
        }
        String paymentId = UUID.randomUUID().toString();
        RegisterPayment registerPayment = new RegisterPayment(
                paymentId,
                registerPaymentRequest.getDescription().trim(),
                registerPaymentRequest.getPayMethodId().trim(),
                registerPaymentRequest.getAppointmentId().trim()
        );

        CompletableFuture<Object> future = commandGateway.send(registerPayment);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex)->(ex!=null) ? ResultType.FAILURE :
                ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if(resultType == ResultType.FAILURE){
            throw new Exception();
        }
        RegisterPaymentResponse registerPaymentResponseDto = new RegisterPaymentResponse(
                registerPayment.getPaymentId(),
                registerPayment.getDescription(),
                registerPayment.getPayMethodId(),
                registerPayment.getAppointmentId()
        );
        return Result.success(registerPaymentResponseDto);
    }

}
