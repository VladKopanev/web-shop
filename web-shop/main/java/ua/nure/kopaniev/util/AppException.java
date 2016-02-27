package ua.nure.kopaniev.util;

/**
 * Created by Vladyslav_Kopaniev on 11/13/2015.
 */
public class AppException extends RuntimeException {
    public AppException() {
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}
