package com.eteration.simplebanking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionDTO {
    private Timestamp date;
    private Double amount;
    private String type;
    private String approvalCode;
}
