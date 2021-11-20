package com.dhome.paymentmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
//@ComponentScan({"com.dhome.paymentmicroservice.command.api"})
public class PaymentMicroserviceApplication {

    public static void main(String[] args) {

        SpringApplication.run(PaymentMicroserviceApplication.class, args);
    }

}
