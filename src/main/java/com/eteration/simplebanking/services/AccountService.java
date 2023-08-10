package com.eteration.simplebanking.services;

import com.eteration.simplebanking.dto.AccountDTO;
import com.eteration.simplebanking.exception.AccountNotFoundException;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.*;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.response.TransactionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        return accountRepository.findByAccountNumber(accountNumber).orElseThrow(AccountNotFoundException::new);
    }

    @Transactional
    public TransactionStatus credit(String accountNumber, Double amount) throws AccountNotFoundException, InsufficientBalanceException {
        Account account = findAccount(accountNumber);
        String approvalCode = UUID.randomUUID().toString();

        account.post(new DepositTransaction(amount, approvalCode));
        accountRepository.save(account);

        return new TransactionStatus("OK", approvalCode);
    }

    @Transactional
    public TransactionStatus debit(String accountNumber, Double amount) throws AccountNotFoundException, InsufficientBalanceException {
        Account account = findAccount(accountNumber);
        String approvalCode = UUID.randomUUID().toString();

        account.post(new WithdrawalTransaction(amount, approvalCode));
        accountRepository.save(account);

        return new TransactionStatus("OK", approvalCode);
    }


    public TransactionStatus debit(String accountNumber, Transaction transaction) throws AccountNotFoundException, InsufficientBalanceException {
        Account account = findAccount(accountNumber);

        account.post(transaction);
        accountRepository.save(account);

        return new TransactionStatus();
    }
}
