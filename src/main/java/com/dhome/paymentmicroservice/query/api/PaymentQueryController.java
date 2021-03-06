package com.dhome.paymentmicroservice.query.api;

import com.dhome.paymentmicroservice.config.SwaggerConfig;
import com.dhome.paymentmicroservice.query.projections.PaymentView;
import com.dhome.paymentmicroservice.query.projections.PaymentViewRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
@Api(tags={SwaggerConfig.PAYMENTS})
public class PaymentQueryController {
    private final PaymentViewRepository paymentViewRepository;

    public PaymentQueryController(PaymentViewRepository paymentViewRepository) {
        this.paymentViewRepository = paymentViewRepository;
    }
    @GetMapping("")
    @ApiOperation(value = "Obtener todos los pagos", response = List.class)
    public ResponseEntity<List<PaymentView>> getAll(){
        try{
            return new ResponseEntity<List<PaymentView>>(paymentViewRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /*@GetMapping(path = "/amount/{amount}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get Payment by Amount", response = PaymentView.class)
    public ResponseEntity<PaymentView> getByAmount(@PathVariable("amount") Float amount) {
        try {
            Optional<PaymentView> paymentViewOptional = paymentViewRepository.getByAmount(amount);
            if (paymentViewOptional.isPresent()) {
                return new ResponseEntity<PaymentView>(paymentViewOptional.get(), HttpStatus.OK);
            }
            return new ResponseEntity("NOT_FOUND", HttpStatus.NOT_FOUND);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
    @GetMapping("/payment/{paymentId}")
    @ApiOperation(value="Obtener un pago por Id", response = List.class)
    public ResponseEntity<PaymentView> getPaymentById(@PathVariable("paymentId") String paymentId){
        try{
            Optional<PaymentView> paymentViewOptional = paymentViewRepository.findById(paymentId);
            if(paymentViewOptional.isPresent())
            {
                return new ResponseEntity<PaymentView>(paymentViewOptional.get(),HttpStatus.OK);
            }
            return new ResponseEntity("NOT FOUND",HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("paymentType/{paymentType}")
    @ApiOperation(value = "Obtener pagos mediante el tipo de pago asignado", response = List.class)
    public ResponseEntity<List<PaymentView>> getAllByPaymentType(@PathVariable("paymentType")String paymentType) {
        try {
            List<PaymentView> paymentViews = paymentViewRepository.getPaymentViewsByPaymentType(paymentType);
            return new ResponseEntity<List<PaymentView>>(paymentViews, HttpStatus.OK);
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
