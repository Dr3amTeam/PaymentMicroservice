package com.dhome.paymentmicroservice.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AppointmentDateRepository extends JpaRepository<AppointmentDate,String> {
    Optional<AppointmentDate> getDateByAppointmentId(String appointmentId);

    @Query(value = "SELECT * FROM appointment_date WHERE appointment_id <> :appointmentId AND date = date LIMIT 1",nativeQuery = true)
    Optional<AppointmentDate> getByDateForDistinctAppointmentId(String date,String appointmentId);
}
