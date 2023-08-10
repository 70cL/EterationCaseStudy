package com.eteration.simplebanking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.eteration.simplebanking.controller.AccountController;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.exception.InsufficientBalanceException;
import com.eteration.simplebanking.repository.AccountRepository;
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
import java.util.Optional;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class ServiceTests {

    @Spy
    @InjectMocks
    private AccountService service;
 
    @Mock
    private AccountRepository repository;
    
    @Test
    void givenId_Credit_thenReturnJson()
    throws Exception {
        
        Account account = new Account("Ovunc", "17892");
        doReturn(account).when(service).findAccount( "17892");
        TransactionStatus result = service.credit("17892",1000.0);

        verify(service, times(1)).findAccount("17892");
        verify(repository, times(1)).save(account);

        assertEquals("OK", result.getStatus());
    }

    @Test
    void givenId_CreditAndThenDebit_thenReturnJson()
    throws Exception {

        Account account = new Account("Kerem Karaca", "17892");

        doReturn(account).when(service).findAccount( "17892");
        TransactionStatus result = service.credit("17892",1000.0);
        TransactionStatus result2 = service.debit("17892",50.0);
        verify(service, times(2)).findAccount("17892");
        verify(repository, times(2)).save(account);
        assertEquals("OK", result.getStatus());
        assertEquals("OK", result2.getStatus());
        assertEquals(950.0, account.getBalance(),0.001);
    }

    @Test
    void givenId_CreditAndThenDebitMoreGetException_thenReturnJson()
    throws Exception {
        Assertions.assertThrows( InsufficientBalanceException.class, () -> {
            Account account = new Account("Kerem Karaca", "17892");

            doReturn(account).when(service).findAccount( "17892");
            TransactionStatus result = service.credit("17892",1000.0);
            assertEquals("OK", result.getStatus());
            assertEquals(1000.0, account.getBalance(),0.001);
            verify(repository, times(1)).save(account);
            verify(service, times(1)).findAccount("17892");

            TransactionStatus result2 = service.debit("17892",1001.0);
        });
    }

    @Test
    void givenId_GetAccount_thenReturnJson()
    throws Exception {

        Account account = new Account("Kerem Karaca", "17892");

        doReturn(account).when(service).findAccount( "17892");
        Account result = service.findAccount( "17892");
        verify(service, times(1)).findAccount("17892");
        assertEquals(account.getAccountNumber(), result.getAccountNumber());
    }
}
