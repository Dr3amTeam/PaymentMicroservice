package com.dhome.paymentmicroservice.command.infra;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AppointmentDate {
    @Id
    public Long id;
    @Column
    public Double amount;
    @Column(length = 100)
    public String description;
    @Column(length = 20)
    public String date;
    public String appointmentId;

    public AppointmentDate() {
    }

    public AppointmentDate(Long id, Double amount, String description, String date, String appointmentId) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.appointmentId = appointmentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }
}
