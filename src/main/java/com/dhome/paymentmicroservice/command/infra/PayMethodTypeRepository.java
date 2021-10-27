package com.dhome.paymentmicroservice.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PayMethodTypeRepository extends JpaRepository<PayMethodType,String> {
    Optional<PayMethodType> getTypeByPayMethodId(String payMethodId);

    @Query(value = "SELECT * FROM PayMethodType payMethod_id WHERE payMethod_id.type= :type",nativeQuery =
            true)
    PayMethodType findPayMethodByType(@Param("type") String type);
}
