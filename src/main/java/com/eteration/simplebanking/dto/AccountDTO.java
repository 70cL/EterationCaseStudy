package com.eteration.simplebanking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountDTO {
    private String accountNumber;
    private String owner;
    private Double balance;
    private Timestamp createDate;
    List<TransactionDTO> transactionDTOs;
}
