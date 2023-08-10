package com.eteration.simplebanking.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionRequest {
    private String accountNumber;
    private Double amount;
}
