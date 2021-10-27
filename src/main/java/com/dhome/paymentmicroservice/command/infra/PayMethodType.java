package com.dhome.paymentmicroservice.command.infra;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PayMethodType extends CurrencyValue{
    @Id
    @Column(length = 30)
    public String type;
    public String currencyId;

    public PayMethodType(String type, String value, String type1, String currencyId) {
        super(type, value);
        this.type = type1;
        this.currencyId = currencyId;
    }

    public PayMethodType(String type, String currencyId) {
        this.type = type;
        this.currencyId = currencyId;
    }
    public PayMethodType(){}
}
