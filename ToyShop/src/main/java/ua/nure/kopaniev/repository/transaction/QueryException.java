package ua.nure.kopaniev.repository.transaction;

import ua.nure.kopaniev.AppException;

/**
 * Created by Vladyslav_Kopaniev on 11/18/2015.
 */
public class QueryException extends AppException {
    public QueryException(String message) {
        super(message);
    }

    public QueryException(String message, Throwable cause) {
        super(message, cause);
    }
}
