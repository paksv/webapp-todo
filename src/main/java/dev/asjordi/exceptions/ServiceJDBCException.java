package dev.asjordi.exceptions;

public class ServiceJDBCException extends RuntimeException {

    public ServiceJDBCException() {}

    public ServiceJDBCException(String message) {
        super(message);
    }

    public ServiceJDBCException(String message, Throwable cause) {
        super(message, cause);
    }
}
