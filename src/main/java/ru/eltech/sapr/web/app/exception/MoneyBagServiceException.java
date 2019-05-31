package ru.eltech.sapr.web.app.exception;

public class MoneyBagServiceException extends RuntimeException
{
    public MoneyBagServiceException(Throwable cause) {
        super(cause);
    }
    public MoneyBagServiceException(String message, Throwable cause) { super(message, cause); }
    public MoneyBagServiceException(String message) {
        super(message);
    }
}

