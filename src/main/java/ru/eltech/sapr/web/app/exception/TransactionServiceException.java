package ru.eltech.sapr.web.app.exception;

public class TransactionServiceException extends RuntimeException
{
    public TransactionServiceException(Throwable cause) {
        super(cause);
    }
    public TransactionServiceException(String message, Throwable cause) { super(message, cause); }
    public TransactionServiceException(String message) {
        super(message);
    }
}

