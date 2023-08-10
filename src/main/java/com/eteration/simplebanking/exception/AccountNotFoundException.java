package com.eteration.simplebanking.exception;


public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(){
        super("Account not found");
    }

}
