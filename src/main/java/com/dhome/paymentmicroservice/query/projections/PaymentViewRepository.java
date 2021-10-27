package com.dhome.paymentmicroservice.query.projections;

import lombok.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentViewRepository extends JpaRepository<PaymentView,String> {
    @Query(value = "SELECT * FROM payment_view WHERE payment_id = :paymentId order by created_at",nativeQuery = true)
    List<PaymentView> getPaymentByPaymentId(String paymentId);
}
