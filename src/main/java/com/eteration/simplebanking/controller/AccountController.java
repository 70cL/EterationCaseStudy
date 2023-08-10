package com.eteration.simplebanking.controller;

import com.eteration.simplebanking.dto.AccountDTO;
import com.eteration.simplebanking.dto.TransactionDTO;
import com.eteration.simplebanking.exception.AccountNotFoundException;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.request.TransactionRequest;
import com.eteration.simplebanking.response.BaseResponse;
import com.eteration.simplebanking.response.TransactionStatus;
import com.eteration.simplebanking.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    @GetMapping(value = "/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<Account>> getAccount(@PathVariable String accountId) throws AccountNotFoundException {
        return ResponseEntity.ok(new BaseResponse<>(accountService.findAccount(accountId)));
    }
    @PostMapping(value = "/credit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<TransactionStatus>> credit(@RequestBody TransactionRequest transactionRequest) throws AccountNotFoundException, InsufficientBalanceException {
        return ResponseEntity.ok(new BaseResponse<>(accountService.credit(transactionRequest.getAccountNumber() ,transactionRequest.getAmount())));
    }
    @PostMapping(value = "/debit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<TransactionStatus>> debit(@RequestBody TransactionRequest transactionRequest) throws AccountNotFoundException, InsufficientBalanceException {
        return ResponseEntity.ok(new BaseResponse<>(accountService.debit(transactionRequest.getAccountNumber() ,transactionRequest.getAmount())));
	}

    @PostMapping(value = "/test/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<TransactionStatus>> test(@PathVariable String accountNumber, @RequestBody Transaction transaction) throws AccountNotFoundException, InsufficientBalanceException {
        return ResponseEntity.ok(new BaseResponse<>(accountService.debit(accountNumber, transaction)));
    }
}