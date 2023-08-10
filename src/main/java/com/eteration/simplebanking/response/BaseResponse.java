package com.eteration.simplebanking.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.PrintWriter;
import java.io.StringWriter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private T responseData;
    private String message;
    private Boolean hasException=false;
    private String exceptionMessage;

    public BaseResponse(String message, Exception e) {
        this(String.format("%s %n %s", message, e.getMessage()), true);
    }

    public BaseResponse(Exception e) {
        var sw = new StringWriter();
        var pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        this.message=e.getMessage();
        this.setMessage(sw.toString(),true);
    }

    public BaseResponse(String message, boolean isException) {
        this.setMessage(message,isException);
    }

    public BaseResponse(T responseData, String warningMessage) {
        this.responseData = responseData;
        this.message = warningMessage;
    }

    public BaseResponse(T responseData) {
        this.responseData = responseData;
    }

    private void setMessage(String message, boolean isException){
        if (!isException) {
            this.message = message;
            return;
        }
        hasException = true;
        this.exceptionMessage = message;
    }
}