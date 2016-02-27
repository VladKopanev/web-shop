package ua.nure.kopaniev;

/**
 * Created by Vladyslav_Kopaniev on 11/13/2015.
 */
public class AppException extends Exception {
    public AppException() {
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}
