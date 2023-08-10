package com.eteration.simplebanking.model;

import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",
        discriminatorType = DiscriminatorType.STRING)
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private Timestamp date;

    private Double amount;

    @Column(insertable = false, updatable = false)
    private String type;

    Transaction(double amount) {
        this.date = new Timestamp(System.currentTimeMillis());
        this.amount = amount;
    }

    private String approvalCode;

    public abstract void process(Account account) throws InsufficientBalanceException;
}
