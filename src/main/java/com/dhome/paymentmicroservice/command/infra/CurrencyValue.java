package com.dhome.paymentmicroservice.command.infra;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CurrencyValue {
    @Id
    @Column(length = 20)
    public String type;
    public String value;

    public CurrencyValue(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public CurrencyValue() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
