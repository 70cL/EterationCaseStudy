package com.eteration.simplebanking.model;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@NoArgsConstructor
@Entity
public class BillPaymentTransaction extends WithdrawalTransaction{
    private String payee;

    public BillPaymentTransaction(String payee, Double amount){
        super(amount);
        this.payee = payee;
    }
}
