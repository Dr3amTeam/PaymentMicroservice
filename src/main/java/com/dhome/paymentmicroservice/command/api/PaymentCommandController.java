package com.dhome.paymentmicroservice.command.api;

import com.dhome.common.api.ApiController;
import com.dhome.common.application.Notification;
import com.dhome.common.application.Result;
import com.dhome.paymentmicroservice.command.application.dtos.request.RegisterPaymentRequest;
import com.dhome.paymentmicroservice.command.application.dtos.response.RegisterPaymentResponse;
import com.dhome.paymentmicroservice.command.application.services.PaymentApplicationService;
import com.dhome.paymentmicroservice.command.infra.PaymentDescriptionRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;

import java.util.List;

@RestController
@RequestMapping("/payments")
@Api(tags="Payments")
public class PaymentCommandController {
    private final PaymentApplicationService paymentApplicationService;
    private final CommandGateway commandGateway;
    private final PaymentDescriptionRepository paymentDescriptionRepository;

    public PaymentCommandController(PaymentApplicationService paymentApplicationService, CommandGateway commandGateway, PaymentDescriptionRepository paymentDescriptionRepository) {
        this.paymentApplicationService = paymentApplicationService;
        this.commandGateway = commandGateway;
        this.paymentDescriptionRepository = paymentDescriptionRepository;
    }
    @PostMapping(path= "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> register(@RequestBody RegisterPaymentRequest registerPaymentRequest) {
        try {
            Result<RegisterPaymentResponse, Notification> result = paymentApplicationService.register(registerPaymentRequest);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch(Exception e) {
            return ApiController.serverError();
        }
    }
}
