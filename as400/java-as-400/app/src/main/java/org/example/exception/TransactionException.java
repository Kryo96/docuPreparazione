package org.example.exception;

public class TransactionException extends Exception{
    public TransactionException(String msg, Exception e) {
        super(msg, e);
    }

    public TransactionException(String msg) {
        super(msg);
    }

}
