package com.kemalkeskin.product.core.exception;

public class ProblemDetails  {

    private String message;


    public ProblemDetails() {
    }

    public ProblemDetails(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
