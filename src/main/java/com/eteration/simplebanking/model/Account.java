package com.eteration.simplebanking.model;


import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    @NonNull
    private String owner;

    @NonNull
    private String accountNumber;

    private Double balance = 0.0;

    private Timestamp createDate = new Timestamp(System.currentTimeMillis());

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private List<Transaction> transactions = new ArrayList<>();

    public void post(Transaction transaction) throws InsufficientBalanceException {
        transaction.process(this);
    }

    public void deposit(double amount) {
        setBalance(getBalance() + amount);
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if(this.balance < amount){
            throw new InsufficientBalanceException();
        }
        setBalance(getBalance() - amount);
    }
}
