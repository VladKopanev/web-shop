package com.epam.preprod.kopaniev.repository.transaction;

import com.epam.preprod.kopaniev.AppException;

/**
 * Created by Vladyslav_Kopaniev on 11/13/2015.
 */
public class TransactionException extends AppException {
    public TransactionException(String message) {
        super(message);
    }

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
