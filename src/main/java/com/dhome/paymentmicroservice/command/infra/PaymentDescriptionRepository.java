package com.dhome.paymentmicroservice.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PaymentDescriptionRepository extends JpaRepository<PaymentDescription,String> {
    Optional<PaymentDescription> getDescriptionByPayment(String paymentId);

    @Query(value = "SELECT * FROM PaymentDescription payment_id WHERE  payment_id.description= :description",
            nativeQuery = true)
    Optional<PaymentDescription> findPaymentByDescription(@Param("description") String description);
}
