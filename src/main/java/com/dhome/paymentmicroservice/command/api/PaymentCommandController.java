package com.dhome.paymentmicroservice.command.api;

import com.dhome.paymentmicroservice.command.application.dto.transfer.TransferPaymentErrorDto;
import com.dhome.paymentmicroservice.command.application.dto.transfer.TransferPaymentOkResponseDto;
import com.dhome.paymentmicroservice.command.application.dto.transfer.TransferPaymentRequestDto;
import com.dhome.paymentmicroservice.config.SwaggerConfig;
import com.example.paymentcontracts.command.CreatePaymentTransfer;
import io.swagger.annotations.ApiOperation;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/payments")
@Api(tags={SwaggerConfig.PAYMENTS})
public class PaymentCommandController {
    private final CommandGateway commandGateway;

    public PaymentCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/transfer")
    @ApiOperation(value = "Realizar transferencia")
    public ResponseEntity<Object> transfer(@Validated @RequestBody TransferPaymentRequestDto transferPaymentRequestDto) {
        String paymentId = UUID.randomUUID().toString();
        CreatePaymentTransfer command = new CreatePaymentTransfer(
                paymentId,
                transferPaymentRequestDto.getCustomerId(),
                transferPaymentRequestDto.getEmployerId(),
                transferPaymentRequestDto.getAmount()
        );
        CompletableFuture<Object> future = commandGateway.send(command);
        CompletableFuture<Object> futureResponse = future.handle((ok, ex) -> {
            if (ex != null) {
                return new TransferPaymentErrorDto();
            }
            return new TransferPaymentOkResponseDto(paymentId);
        });
        try {
            Object response = (Object)futureResponse.get();
            if (response instanceof TransferPaymentOkResponseDto) {
                return new ResponseEntity(response, HttpStatus.OK);
            }
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
