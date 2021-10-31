package com.dhome.paymentmicroservice.command.api;

import com.dhome.common.api.ApiController;
import com.dhome.common.application.Notification;
import com.dhome.common.application.Result;
import com.dhome.paymentmicroservice.command.application.dto.deposit.DepositPaymentErrorResponseDto;
import com.dhome.paymentmicroservice.command.application.dto.deposit.DepositPaymentOkResponseDto;
import com.dhome.paymentmicroservice.command.application.dto.deposit.DepositPaymentRequestDto;
import com.dhome.paymentmicroservice.command.application.dto.transfer.TransferPaymentErrorDto;
import com.dhome.paymentmicroservice.command.application.dto.transfer.TransferPaymentOkResponseDto;
import com.dhome.paymentmicroservice.command.application.dto.transfer.TransferPaymentRequestDto;
import com.dhome.paymentmicroservice.command.application.dto.withdraw.WithdrawPaymentErrorDto;
import com.dhome.paymentmicroservice.command.application.dto.withdraw.WithdrawPaymentOkResponseDto;
import com.dhome.paymentmicroservice.command.application.dto.withdraw.WithdrawPaymentRequestDto;
import com.dhome.paymentmicroservice.command.domain.OverdraftLimitExceededException;
import com.example.paymentcontracts.command.CreatePaymentTransfer;
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
@Api(tags="Payments")
public class PaymentCommandController {
    private final CommandGateway commandGateway;

    public PaymentCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }
    /*@PostMapping(path= "/deposit")
    public ResponseEntity<Object> deposit(@Validated @RequestBody DepositPaymentRequestDto depositPaymentRequestDto) {

        String paymentId = UUID.randomUUID().toString();
        CreditAccount command = new CreditAccount(
                depositPaymentRequestDto.getAccountId(),
                paymentId,
                depositPaymentRequestDto.getAmount()
        );
        CompletableFuture<Object> future = commandGateway.send(command);
        CompletableFuture<Object> futureResponse = future.handle((ok,ex) -> {
            if(ex != null){
                return new DepositPaymentErrorResponseDto();
            }
            return new DepositPaymentOkResponseDto(paymentId);
        });
        try {
            Object  response = (Object)futureResponse.get();
            if (response instanceof DepositPaymentRequestDto) {
                return new ResponseEntity(response, HttpStatus.OK);
            }
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/withdraw")
    public ResponseEntity<Object> withdraw(@Validated @RequestBody WithdrawPaymentRequestDto withdrawPaymentRequestDto){
        String paymentId = UUID.randomUUID().toString();
        DebitAccount command = new DebitAccount(
                withdrawPaymentRequestDto.getAccountId(),
                paymentId,
                withdrawPaymentRequestDto.getAmount()
        );
        CompletableFuture<Object> future = commandGateway.send(command);
        CompletableFuture<Object> futureResponse = future.handle((ok,ex)->{
            if(ex != null){
                if(ex instanceof OverdraftLimitExceededException){
                    return new WithdrawPaymentErrorDto()("The account exceed the overdraft limit");
                }
                return new WithdrawPaymentErrorDto();
            }
            return new WithdrawPaymentOkResponseDto(paymentId);
        });
        try{
            Object response = (Object)futureResponse.get();
            if(response instanceof WithdrawPaymentOkResponseDto){
                return new ResponseEntity(response, HttpStatus.OK);
            }
            return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    @PostMapping("/transfer")
    public ResponseEntity<Object> transfer(@Validated @RequestBody TransferPaymentRequestDto transferPaymentRequestDto) {
        String paymentId = UUID.randomUUID().toString();
        CreatePaymentTransfer command = new CreatePaymentTransfer(
                paymentId,
                transferPaymentRequestDto.getFromAccountId(),
                transferPaymentRequestDto.getToAccountId(),
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
