package com.dhome.paymentmicroservice.query.api;

import com.dhome.paymentmicroservice.query.projections.PaymentView;
import com.dhome.paymentmicroservice.query.projections.PaymentViewRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/payments")
@Api(tags = "Payments")
public class PaymentQueryController {
    private final PaymentViewRepository paymentViewRepository;

    public PaymentQueryController(PaymentViewRepository paymentViewRepository) {
        this.paymentViewRepository = paymentViewRepository;
    }
    @GetMapping("")
    @ApiOperation(value = "Get all payments", response = List.class)
    public ResponseEntity<List<PaymentView>> getAll(){
        try{
            return new ResponseEntity<List<PaymentView>>(paymentViewRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/payment/{paymentId}")
    @ApiOperation(value="Get Payment id", response = List.class)
    public ResponseEntity<List<PaymentView>> getPaymentById(@PathVariable("paymentId") String paymentId){
        try{
            List<PaymentView> payments = paymentViewRepository.getPaymentByPaymentId(paymentId);
            return new ResponseEntity<List<PaymentView>>(payments,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
