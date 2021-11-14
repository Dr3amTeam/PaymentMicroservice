package com.dhome.paymentmicroservice.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentViewRepository extends JpaRepository<PaymentView,String> {
    Optional<PaymentView> getPaymentViewByPaymentId(String paymentId);
    List<PaymentView> getPaymentViewsByPaymentType(String paymentType);
}
