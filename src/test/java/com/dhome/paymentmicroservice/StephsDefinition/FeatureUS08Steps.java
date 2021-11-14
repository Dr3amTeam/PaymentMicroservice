package com.dhome.paymentmicroservice.StephsDefinition;

import com.dhome.paymentmicroservice.query.projections.PaymentView;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class FeatureUS08Steps {
    RestTemplate restTemplate = new RestTemplate();
    String fooResourceUrl = "http://localhost:8084/payments/";
    @Given("Dado que como trabajador necesito conocer si el pago será en efectivo o con tarjeta")
    public void dadoQueComoTrabajadorNecesitoConocerSiElPagoSeráEnEfectivoOConTarjeta() {
        ResponseEntity<PaymentView[]> response = restTemplate.getForEntity(fooResourceUrl,PaymentView[].class);
        Assert.assertEquals(response.getBody().length,2);
    }

    @When("Cuando los trabajadores revisen la sección de citas")
    public void cuandoLosTrabajadoresRevisenLaSecciónDeCitas() {
    }

    @Then("Entonces se muestra los detalles como el método de pago")
    public void entoncesSeMuestraLosDetallesComoElMétodoDePago() {
        ResponseEntity<PaymentView[]> response = restTemplate.getForEntity(fooResourceUrl+"paymentType/CASH",
                PaymentView[].class);
        Assert.assertEquals(response.getBody().length,1);
    }
}
