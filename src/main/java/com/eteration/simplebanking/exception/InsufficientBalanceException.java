package com.eteration.simplebanking.exception;


public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(){
        super("Not Enough Money!");
    }

}
