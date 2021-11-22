package mx.javasample.designpatterns.recommendations.exceptions;

/**
 * Represents an error with a third party service.
 */
public class HttpClientException extends Exception {
    public HttpClientException(final String message) {
        super(message);
    }

    public HttpClientException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
