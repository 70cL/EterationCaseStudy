package com.eteration.simplebanking.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AccountDTO {
    private String accountNumber;
    private String owner;
    private Double balance;
    private Timestamp createDate;
    List<TransactionDTO> transactionDTOs;
}
