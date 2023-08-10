package com.eteration.simplebanking.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@Entity
public class PhoneBillPaymentTransaction extends BillPaymentTransaction{
    private String phoneNumber;

    public PhoneBillPaymentTransaction(String payee, Double amount, String phoneNumber){
        super(payee, amount);
        this.phoneNumber = phoneNumber;
    }
}
