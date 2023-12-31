package com.eteration.simplebanking.model;


import lombok.NoArgsConstructor;

import javax.persistence.*;
@NoArgsConstructor
@Entity
@DiscriminatorValue("DepositTransaction")
public class DepositTransaction extends Transaction{

    public DepositTransaction(double amount) {
        super(amount);
    }

    @Override
    public void process(Account account) {
        account.deposit(this.getAmount());
        account.getTransactions().add(this);
    }
}
