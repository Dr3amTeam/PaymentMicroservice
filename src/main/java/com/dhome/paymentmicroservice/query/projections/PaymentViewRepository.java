package com.dhome.paymentmicroservice.query.projections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentViewRepository extends JpaRepository<PaymentView,String> {

}
