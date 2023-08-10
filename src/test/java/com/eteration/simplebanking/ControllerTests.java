package com.eteration.simplebanking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.eteration.simplebanking.controller.AccountController;
import com.eteration.simplebanking.dto.AccountDTO;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.request.TransactionRequest;
import com.eteration.simplebanking.response.BaseResponse;
import com.eteration.simplebanking.response.TransactionStatus;
import com.eteration.simplebanking.services.AccountService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.Objects;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class ControllerTests  {

    @Spy
    @InjectMocks
    private AccountController controller;
 
    @Mock
    private AccountService service;

    
    @Test
    void givenId_Credit_thenReturnJson()
    throws Exception {
        
        Account account = new Account("Kerem Karaca", "17892");

        doReturn(account).when(service).findAccount( "17892");
        ResponseEntity<BaseResponse<TransactionStatus>> result = controller.credit(new TransactionRequest("17892",1000.0));
        verify(service, times(1)).findAccount("17892");
        assertEquals("OK", Objects.requireNonNull(result.getBody()).getResponseData().getStatus());
    }

    @Test
    void givenId_CreditAndThenDebit_thenReturnJson()
    throws Exception {
        
        Account account = new Account("Kerem Karaca", "17892");

        doReturn(account).when(service).findAccount( "17892");
        ResponseEntity<BaseResponse<TransactionStatus>> result = controller.credit(new TransactionRequest("17892",1000.0));
        ResponseEntity<BaseResponse<TransactionStatus>> result2 = controller.debit(new TransactionRequest("17892",50.0));
        verify(service, times(2)).findAccount("17892");
        assertEquals("OK", Objects.requireNonNull(result.getBody()).getResponseData().getStatus());
        assertEquals("OK", Objects.requireNonNull(result.getBody()).getResponseData().getStatus());
        assertEquals(950.0, account.getBalance(),0.001);
    }

    @Test
    void givenId_CreditAndThenDebitMoreGetException_thenReturnJson()
    throws Exception {
        Assertions.assertThrows( InsufficientBalanceException.class, () -> {
            Account account = new Account("Kerem Karaca", "17892");

            doReturn(account).when(service).findAccount( "17892");
            ResponseEntity<BaseResponse<TransactionStatus>> result = controller.credit(new TransactionRequest("17892",1000.0));
            assertEquals("OK", Objects.requireNonNull(result.getBody()).getResponseData().getStatus());
            assertEquals(1000.0, account.getBalance(),0.001);
            verify(service, times(1)).findAccount("17892");

            ResponseEntity<BaseResponse<TransactionStatus>> result2 = controller.debit(new TransactionRequest("17892",1000.0));
        });
    }

    @Test
    void givenId_GetAccount_thenReturnJson()
    throws Exception {
        
        Account account = new Account("Kerem Karaca", "17892");

        doReturn(account).when(service).findAccount( "17892");
        ResponseEntity<BaseResponse<Account>> result = controller.getAccount( "17892");
        verify(service, times(1)).findAccount("17892");
        assertEquals(account.getAccountNumber(), Objects.requireNonNull(result.getBody()).getResponseData().getAccountNumber());
    }
}
